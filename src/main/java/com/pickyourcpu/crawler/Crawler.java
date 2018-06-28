package com.pickyourcpu.crawler;

import com.pickyourcpu.dto.Item;
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
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public void parseHTML( String filePath, String uri ) {
        try ( FileOutputStream fos = new FileOutputStream( filePath ) ) {

            URL url = new URL( uri );
            URLConnection con = url.openConnection();
            con.addRequestProperty( "User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.87 Safari/537.36" );

            InputStream is = con.getInputStream();
            BufferedReader br = new BufferedReader( new InputStreamReader( is, "UTF-8" ) );

            String line;
            Writer writer = new BufferedWriter( new OutputStreamWriter( fos, "UTF-8" ) );

            while ( (line = br.readLine()) != null && flag ) {
                if ( line.contains( "<link" ) || line.contains( "<meta" ) ) {
                    continue;
                }
                if ( line.contains( "<br>" ) ) {
                    line = line.replaceAll( "<br>", "" );
                }
                if ( line.contains( "<BR>" ) ) {
                    line = line.replaceAll( "<BR>", "" );
                }
                if ( line.contains( " async " ) ) {
                    line = line.replaceAll( " async ", " " );
                }

                line = preProcess( line );

                writer.write( line + "\n" );
            }
            br.close();
            writer.close();

        } catch ( MalformedURLException e ) {
            e.printStackTrace();
        } catch ( IOException e ) {
            e.printStackTrace();
        }

    }

    public String preProcess( String line ) {
        StringBuffer sb = new StringBuffer( line.length() );

        Pattern imgPattern = Pattern.compile( "(<img.*?)(>)" );
        Matcher imgMatcher = imgPattern.matcher( line );

        while ( imgMatcher.find() ) {
            if ( imgMatcher.group().endsWith( "/>" ) ) continue;
            imgMatcher.appendReplacement( sb, "$1/>" );
        }
        imgMatcher.appendTail( sb );

        return sb.toString();
    }

    public List<Item> parseFile( String filePath ) {
        XMLInputFactory factory = XMLInputFactory.newFactory();
        factory.setProperty( XMLInputFactory.IS_VALIDATING, false );
        factory.setProperty( XMLInputFactory.IS_REPLACING_ENTITY_REFERENCES, false );

        XMLEventReader reader = null;
        List<Item> list = new ArrayList<>();

        try ( FileInputStream fis = new FileInputStream( filePath ) ) {
            reader = factory.createXMLEventReader( new InputStreamReader( fis, "UTF-8" ) );

            boolean inProductTag = false;
            boolean inProductNameTag = false;

            while ( reader.hasNext() && flag ) {
                XMLEvent event = reader.nextEvent();

                if ( event.isStartElement() ) {
                    StartElement element = (StartElement) event;

                    if ( element.getName().toString().equals( "tr" ) ) {
                        inProductTag = true;
                    }

                    if ( element.getName().toString().equals( "td" ) ) {
                        Attribute attr = element.getAttributeByName( new QName( "id" ) );
                        if ( attr != null ) {
                            if ( attr.getValue().contains( "rk" ) ) {
                                inProductNameTag = true;
                            }
                        }
                    }

                    if ( inProductNameTag && inProductTag && element.getName().toString().equals( "a" ) ) {
                        Item item = new Item();
                        item.setName( reader.getElementText().split( "@" )[0] );
                        list.add( item );
                        inProductNameTag = false;
                        inProductNameTag = false;
                    }
                }
            }

        } catch ( IOException e ) {
            e.printStackTrace();
        } catch ( XMLStreamException e ) {
            e.printStackTrace();
        } finally {

        }
        return list;
    }

    public void writeFile( String filePath, List<Item> list ) {
        try ( FileWriter writer = new FileWriter( filePath ) ) {
            for ( Item item : list ) {
                writer.write( item.getName() );
            }
            writer.close();
        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        this.flag = true;

        while ( flag ) {
            parseHTML( "src/main/xml/data.xml", "https://www.cpubenchmark.net/high_end_cpus.html" );
            List<Item> list = parseFile( "src/main/xml/data.xml" );
            writeFile( "src/main/xml/output/processed.xml", list);
            this.flag = false;
        }
    }
}
