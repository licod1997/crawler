package com.pickyourcpu.crawler;

import com.pickyourcpu.entity.Product;
import com.pickyourcpu.enu.URLEnum;
import org.springframework.stereotype.Service;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class Crawler implements Runnable {

    private volatile boolean flag = false;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag( boolean flag ) {
        this.flag = flag;
    }

    public void stopCrawling() {
        this.flag = false;
    }

    private Crawler() {
    }

    public String selfClosingTag( String str, String tag ) {
        StringBuffer sb = new StringBuffer();

        Pattern imgPattern = Pattern.compile( "(<" + tag + ".*?)(>)" );
        Matcher imgMatcher = imgPattern.matcher( str );

        while ( imgMatcher.find() ) {
            if ( imgMatcher.group().endsWith( "/>" ) ) continue;
            imgMatcher.appendReplacement( sb, "$1/>" );
        }
        imgMatcher.appendTail( sb );

        return sb.toString();
    }

    public String URIResolver( String uri ) {
        try {
            URL url = new URL( uri );
            URLConnection con = url.openConnection();
            con.addRequestProperty( "User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, " +
                    "like Gecko) Chrome/67.0.3396.87 Safari/537.36" );

            return new BufferedReader( new InputStreamReader( con.getInputStream() ) )
                    .lines().collect( Collectors.joining( "\n" ) );
        } catch ( MalformedURLException e ) {
            e.printStackTrace();
        } catch ( IOException e ) {
            e.printStackTrace();
        }
        return null;
    }

    public InputStream preProcessMainWebsite( String rawString ) {
        //get necessary part of html
        int start = rawString.indexOf( "<div id=\"mark\"" );
        int end = rawString.indexOf( "<div id=\"value\"" );
        rawString = rawString.substring( start, end );
        //end

        //replace tags which leading exception
        rawString = rawString.replaceAll( "<br>", "" )
                .replaceAll( "<BR>", "" );
        //end
        return new ByteArrayInputStream( rawString.getBytes( StandardCharsets.UTF_8 ) );
    }

    public InputStream preProcessMainWebsiteDetail( String rawString ) {
        //get necessary part of html
        Pattern startPattern = Pattern.compile( "(<table.*?class=\"desc\".*?)(>)" );
        Matcher startMatcher = startPattern.matcher( rawString );
        startMatcher.find();
        int start = startMatcher.start();
        Pattern endPattern = Pattern.compile( "(<table.*?class=\"price\".*?)(>)" );
        Matcher endMatcher = endPattern.matcher( rawString );
        endMatcher.find();
        int end = endMatcher.start();
        rawString = rawString.substring( start, end );
        //end

        //replace tags which leading exception
        Pattern p = Pattern.compile( "[^\\u0009\\u000A\\u000D\u0020-\\uD7FF\\uE000-\\uFFFD\\u10000-\\u10FFF]+" );
        rawString = p.matcher( rawString ).replaceAll( "" );
        rawString = rawString.replaceAll( "<br>", "" )
                .replaceAll( "<BR>", "" )
                .replaceAll( " & ", " &amp; " );
        //end
        return new ByteArrayInputStream( rawString.getBytes( StandardCharsets.UTF_8 ) );
    }

    public List<Product> parseMainWebsite( InputStream is ) {
        XMLInputFactory factory = XMLInputFactory.newFactory();
        factory.setProperty( XMLInputFactory.IS_VALIDATING, false );
        factory.setProperty( XMLInputFactory.IS_REPLACING_ENTITY_REFERENCES, false );

        XMLEventReader reader;
        List<Product> list = new ArrayList<>();

        try {
            reader = factory.createXMLEventReader( new InputStreamReader( is, "UTF-8" ) );

            boolean inProductTag = false;
            boolean inProductNameTag = false;

            while ( reader.hasNext() ) {
                XMLEvent event = reader.nextEvent();

                if ( event.isStartElement() ) {
                    StartElement element = (StartElement) event;

                    if ( element.getName().toString().equals( "tr" ) ) {
                        inProductTag = true;
                    }

                    if ( element.getName().toString().equals( "td" ) ) {
                        Attribute attrId = element.getAttributeByName( new QName( "id" ) );
                        if ( attrId != null && attrId.getValue().contains( "rk" ) ) {
                            inProductNameTag = true;
                        }
                    }

                    if ( inProductNameTag && inProductTag && element.getName().toString().equals( "a" ) ) {
                        //detail start here
                        Attribute attrHref = element.getAttributeByName( new QName( "href" ) );
                        if ( attrHref != null ) {
                            String detailUrl = URLEnum.MAIN_WEBSITE_HOST.getUrl() + "/" + attrHref.getValue();
                            String htmlDetail = URIResolver( detailUrl );
                            InputStream isDetail = preProcessMainWebsiteDetail( htmlDetail );
                            Product product = parseMainWebsiteDetail( isDetail );
                        }
                        //end
                        inProductTag = false;
                        inProductNameTag = false;
                    }
                }
            }

        } catch ( XMLStreamException e ) {
            e.printStackTrace();
        } catch ( UnsupportedEncodingException e ) {
            e.printStackTrace();
        }
        return list;
    }

    public Product parseMainWebsiteDetail( InputStream is ) {
        XMLInputFactory factory = XMLInputFactory.newFactory();
        factory.setProperty( XMLInputFactory.IS_VALIDATING, false );
        factory.setProperty( XMLInputFactory.IS_REPLACING_ENTITY_REFERENCES, false );

        XMLEventReader reader;
        Product product = new Product();

        try {
            reader = factory.createXMLEventReader( new InputStreamReader( is, "UTF-8" ) );

            int emCount = 0;

            while ( reader.hasNext() ) {
                XMLEvent event = reader.nextEvent();

                if ( event.isStartElement() ) {
                    StartElement element = (StartElement) event;

                    if ( element.getName().toString().equals( "span" ) ) {
                        Attribute spanAttr = element.getAttributeByName( new QName( "class" ) );
                        if ( spanAttr != null ) {
                            product.setName( reader.getElementText() );
                        }
                    }

                    if ( element.getName().toString().equals( "em" ) && emCount == 0 ) {
//                        System.out.println( element.get );
                        emCount++;
                    }
                }
            }

        } catch ( XMLStreamException e ) {
            e.printStackTrace();
        } catch ( UnsupportedEncodingException e ) {
            e.printStackTrace();
        }
        return product;
    }

    public void start() {
        String html = URIResolver( URLEnum.MAIN_WEBSITE_1.getUrl() );
        InputStream is = preProcessMainWebsite( html );
        List<Product> list = parseMainWebsite( is );

    }

    @Override
    public void run() {

    }
}
