package com.pickyourcpu.crawler;

import com.pickyourcpu.entity.Product;
import com.pickyourcpu.entity.Shop;
import com.pickyourcpu.enu.URLEnum;
import com.pickyourcpu.jaxb.ProductsJAXB;
import com.pickyourcpu.repository.ProductRepository;
import com.pickyourcpu.util.CrawlHelper;
import com.pickyourcpu.util.EntityToJAXB;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import javax.xml.transform.sax.SAXSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.*;
import java.math.BigDecimal;
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

    private final ProductRepository productRepository;

    @Autowired
    public Crawler( ProductRepository productRepository ) {
        this.productRepository = productRepository;
    }

    private volatile boolean flag = false;
    private boolean breakCondition = false;
    private int result;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag( boolean flag ) {
        this.flag = flag;
    }

    public int getResult() {
        return result;
    }

    public void stopCrawling() {
        this.flag = false;
    }

    public String selfClosingTag( String str, String tag ) {
        StringBuffer sb = new StringBuffer();

        Pattern imgPattern = Pattern.compile( "(<" + tag + "(.|\n)*?)>" );
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

    public InputStream preProcessCPUBenchmark( String rawString ) {
        //get necessary part of html
        int start = rawString.indexOf( "<div id=\"mark\"" );
        int end = rawString.indexOf( "<div id=\"value\"" );
        //end

        if ( start > -1 && end > -1 ) {
            rawString = rawString.substring( start, end );
            //replace tags which leading exception
            rawString = rawString.replaceAll( "<br>", "" )
                    .replaceAll( "<BR>", "" );
            //end
        }
        return new ByteArrayInputStream( rawString.getBytes( StandardCharsets.UTF_8 ) );
    }

    public InputStream preprocessCPUBenchmarkDetail( String rawString ) {
        //get necessary part of html
        Pattern startPattern = Pattern.compile( "(<table.*?class=\"desc\".*?)>" );
        Matcher startMatcher = startPattern.matcher( rawString );
        startMatcher.find();
        int start = startMatcher.start();
        Pattern endPattern = Pattern.compile( "(<table.*?class=\"price\".*?)>" );
        Matcher endMatcher = endPattern.matcher( rawString );
        endMatcher.find();
        int end = endMatcher.start();
        //end

        if ( start > -1 && end > -1 ) {
            rawString = rawString.substring( start, end );
            //replace tags which leading exception
            Pattern p = Pattern.compile( "[^\\u0009\\u000A\\u000D\u0020-\\uD7FF\\uE000-\\uFFFD\\u10000-\\u10FFF]+" );
            rawString = p.matcher( rawString ).replaceAll( "" );
            rawString = rawString.replaceAll( "<br>", "" )
                    .replaceAll( "<BR>", "" )
                    .replaceAll( " & ", " &amp; " );
            //end
        }
        return new ByteArrayInputStream( rawString.getBytes( StandardCharsets.UTF_8 ) );
    }

    public InputStream preprocessLongBinh( String rawString ) {
        int start = rawString.indexOf( "<div class=\"tygh-content clearfix\">" );
        int end = rawString.indexOf( "<div class=\"tygh-footer clearfix\" id=\"tygh_footer\">" );

        if ( start > -1 && end > -1 ) {
            rawString = rawString.substring( start, end );

            rawString = rawString.replaceAll( "®", "" )
                    .replaceAll( "™", "" )
                    .replaceAll( "itemscope", "" )
                    .replaceAll( "</strong>", "" )
                    .replaceAll( "&nbsp;", "" );
        }

        return new ByteArrayInputStream( rawString.getBytes( StandardCharsets.UTF_8 ) );
    }

    public InputStream preprocessPhongVu( String rawString ) {
        int start = rawString.indexOf( "<div class=\"row grid-view\">" );
        int end = rawString.indexOf( "<div class=\"list-view header-hidden\">" );

        if ( start > -1 && end > -1 ) {
            rawString = rawString.substring( start, end );
            rawString = selfClosingTag( rawString, "img" );
        }
        return new ByteArrayInputStream( rawString.getBytes( StandardCharsets.UTF_8 ) );
    }

    public List<Product> parseCPUBenchmark( InputStream is ) {
        XMLInputFactory factory = XMLInputFactory.newFactory();
        factory.setProperty( XMLInputFactory.IS_VALIDATING, false );
        factory.setProperty( XMLInputFactory.IS_REPLACING_ENTITY_REFERENCES, false );

        XMLEventReader reader;
        List<Product> list = new ArrayList<>();

        try {
            reader = factory.createXMLEventReader( new InputStreamReader( is, "UTF-8" ) );

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
                        Attribute attrId = element.getAttributeByName( new QName( "id" ) );
                        if ( attrId != null && attrId.getValue().contains( "rk" ) ) {
                            inProductNameTag = true;
                        }
                    }

                    if ( inProductNameTag && inProductTag && element.getName().toString().equals( "a" ) ) {
                        //detail start here
                        Attribute attrHref = element.getAttributeByName( new QName( "href" ) );
                        if ( attrHref != null ) {
                            String detailUrl = URLEnum.CPUBENCHMARK_HOST.getUrl() + "/" + attrHref.getValue();
                            String htmlDetail = URIResolver( detailUrl );
                            InputStream isDetail = preprocessCPUBenchmarkDetail( htmlDetail );
                            Product product = parseCPUBenchmarkDetail( isDetail );
                            list.add( product );
                            this.result = list.size();
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

    public Product parseCPUBenchmarkDetail( InputStream is ) {
        XMLInputFactory factory = XMLInputFactory.newFactory();
        factory.setProperty( XMLInputFactory.IS_VALIDATING, false );
        factory.setProperty( XMLInputFactory.IS_REPLACING_ENTITY_REFERENCES, false );

        XMLEventReader reader;
        Product product = new Product();

        try {
            reader = factory.createXMLEventReader( new InputStreamReader( is, "UTF-8" ) );

            int emCount = 0;
            int trCount = 0;
            int tdCount = 0;
            int spanCount = 0;
            boolean inTrBenchMark = false;
            boolean inTdBenchMark = false;
            boolean inSpanBenchMark = false;

            while ( reader.hasNext() ) {
                XMLEvent event = reader.nextEvent();

                if ( event.isStartElement() ) {
                    StartElement element = (StartElement) event;

                    if ( element.getName().toString().equals( "span" ) ) {
                        Attribute spanAttr = element.getAttributeByName( new QName( "class" ) );
                        if ( spanAttr != null && spanAttr.getValue().equals( "cpuname" ) ) {
                            product.setName( reader.getElementText().split( "@" )[0].trim() );
                        }
                    }

                    if ( element.getName().toString().equals( "em" ) ) {
                        if ( emCount == 0 ) {
                            String fragment = readElementBody( reader );

                            Pattern socketPattern = Pattern.compile( "<strong>Socket:</strong>(.*?)<br></br>" );
                            Matcher socketMatcher = socketPattern.matcher( fragment );
                            Pattern clockspeedPattern = Pattern.compile( "<strong>Clockspeed:</strong>(.*?)GHz<br></br>" );
                            Matcher clockspeedMatcher = clockspeedPattern.matcher( fragment );
                            Pattern turbospeedPattern = Pattern.compile( "<strong>Turbo Speed:</strong>(.*?)GHz<br></br>" );
                            Matcher turbospeedMatcher = turbospeedPattern.matcher( fragment );
                            Pattern noOfCoresPattern = Pattern.compile( "<strong>No of Cores:</strong>(.*?)(\\((.*?)\\)){0,1}<br></br>" );
                            Matcher noOfCoresMatcher = noOfCoresPattern.matcher( fragment );
                            Pattern TDPPattern = Pattern.compile( "<strong>Typical TDP:</strong>(.*?)W<br></br>" );
                            Matcher TDPMatcher = TDPPattern.matcher( fragment );
                            Pattern descriptionPattern = Pattern.compile( "<div.*?><strong>Description:</strong>(.*?)</div>" );
                            Matcher descriptionMatcher = descriptionPattern.matcher( fragment );

                            if ( socketMatcher.find() ) {
                                product.setSocket( socketMatcher.group( 1 )
                                        .replaceAll( "FCBGA", "BGA" )
                                        .replaceAll( "FCLGA", "LGA" )
                                        .replaceAll( "FCPGA", "PGA" )
                                        .replaceAll( "Socket", "" )
                                        .replaceAll( "FC-BGA", "" )
                                        .replaceAll( "LGA2011-3", "LGA2011-v3" )
                                        .replaceAll( " ", "" ) );
                            }
                            if ( clockspeedMatcher.find() ) {
                                product.setClockspeed( NumberUtils.toDouble( clockspeedMatcher.group( 1 ).trim() ) );
                            }
                            if ( turbospeedMatcher.find() ) {
                                product.setTurbospeed( NumberUtils.toDouble( turbospeedMatcher.group( 1 ).trim() ) );
                            }
                            if ( TDPMatcher.find() ) {
                                if ( NumberUtils.toDouble( TDPMatcher.group( 1 ).trim() ) > 0 ) {
                                    product.setTDP( NumberUtils.toDouble( TDPMatcher.group( 1 ).trim() ) );
                                }
                            }
                            if ( noOfCoresMatcher.find() ) {
                                product.setNoOfCores( NumberUtils.toInt( noOfCoresMatcher.group( 1 ).trim() ) );
                                product.setCoresDescription( noOfCoresMatcher.group( 3 ) );
                            }
                            if ( descriptionMatcher.find() ) {
                                product.setDescription( descriptionMatcher.group( 1 ).trim() );
                            }
                        }
                        emCount++;
                    }

                    if ( element.getName().toString().equals( "tr" ) ) {
                        if ( trCount == 1 ) {
                            inTrBenchMark = true;
                        }
                        trCount++;
                    }

                    if ( element.getName().toString().equals( "td" ) && inTrBenchMark ) {
                        if ( tdCount == 1 ) {
                            inTdBenchMark = true;
                        }
                        tdCount++;
                    }

                    if ( element.getName().toString().equals( "span" ) && inTdBenchMark ) {
                        if ( spanCount == 0 ) {
                            inSpanBenchMark = true;
                        }
                        spanCount++;
                    }

                    if ( inTrBenchMark && inTdBenchMark && inSpanBenchMark ) {
                        product.setBenchmark( NumberUtils.toInt( reader.getElementText() ) );
                        break;
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

    public List<Product> parseLongBinh( InputStream is ) {
        XMLInputFactory factory = XMLInputFactory.newFactory();
        factory.setProperty( XMLInputFactory.IS_VALIDATING, false );
        factory.setProperty( XMLInputFactory.IS_REPLACING_ENTITY_REFERENCES, false );

        XMLEventReader reader;
        List<Product> list = new ArrayList<>();
        Product product = new Product();
        Shop shop = new Shop();
        try {
            reader = factory.createXMLEventReader( new InputStreamReader( is, "UTF-8" ) );

            boolean inProductTag = false;

            while ( reader.hasNext() ) {
                XMLEvent event = reader.nextEvent();

                if ( event.isStartElement() ) {
                    StartElement element = (StartElement) event;

                    Attribute attrProduct = element.getAttributeByName( new QName( "class" ) );

                    if ( attrProduct != null && attrProduct.getValue().equals( "ty-column4" ) ) {
                        inProductTag = true;
                    }

                    if ( inProductTag ) {
                        Attribute attrClass = element.getAttributeByName( new QName( "class" ) );

                        if ( attrClass != null && attrClass.getValue().equals( "product-title" ) ) {
                            Attribute attrUrl = element.getAttributeByName( new QName( "href" ) );
                            Attribute attrName = element.getAttributeByName( new QName( "title" ) );

                            if ( attrUrl != null ) {
                                shop.setUrl( attrUrl.getValue() );
                            }
                            if ( attrName != null ) {
                                product.setName( attrName.getValue() );
                            }
                        }

                        if ( attrClass != null && attrClass.getValue().equals( "ty-price-num" ) ) {
                            String price = reader.getElementText();
                            if ( !price.equals( "đ" ) ) {
                                shop.setPrice( new BigDecimal( price.trim().replaceAll( "\\.", "" ) ) );
                                product.getShops().add( shop );
                                list.add( product );
                                product = new Product();
                                shop = new Shop();
                                inProductTag = false;
                            }
                        }
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

    public List<Product> parsePhongVu( InputStream is ) {
        XMLInputFactory factory = XMLInputFactory.newFactory();
        factory.setProperty( XMLInputFactory.IS_VALIDATING, false );
        factory.setProperty( XMLInputFactory.IS_REPLACING_ENTITY_REFERENCES, false );

        XMLEventReader reader;
        List<Product> list = new ArrayList<>();
        Product product = new Product();
        Shop shop = new Shop();

        try {
            reader = factory.createXMLEventReader( new InputStreamReader( is, "UTF-8" ) );

            boolean inProductTag = false;

            while ( reader.hasNext() ) {
                XMLEvent event = reader.nextEvent();

                if ( event.isStartElement() ) {
                    StartElement element = (StartElement) event;

                    if ( element.getName().toString().equals( "a" ) ) {
                        Attribute attrClass = element.getAttributeByName( new QName( "class" ) );

                        if ( attrClass != null && attrClass.getValue().equals( "col-xs-3 grid-view-item deal-mix" ) ) {
                            inProductTag = true;
                        }

                        if ( inProductTag ) {
                            Attribute attrUrl = element.getAttributeByName( new QName( "href" ) );
                            if ( attrUrl != null ) {
                                shop.setUrl( attrUrl.getValue() );
                            }

                            Attribute attrName = element.getAttributeByName( new QName( "data-name" ) );
                            if ( attrName != null ) {
                                Pattern p = Pattern.compile( "Bộ vi xử lý(\\s)*/ CPU((.*?)(\\(.*?\\))|(.*))" );
                                Matcher m = p.matcher( attrName.getValue() );
                                if ( m.find() ) {
                                    if ( StringUtils.isBlank( m.group( 3 ) ) ) {
                                        product.setName( m.group( 2 ).trim() );
                                    } else {
                                        product.setName( m.group( 3 ).trim() );
                                    }
                                }
                            }

                            Attribute attrPrice = element.getAttributeByName( new QName( "data-price" ) );
                            if ( attrPrice != null ) {
                                shop.setPrice( new BigDecimal( attrPrice.getValue() ).setScale( 0, BigDecimal.ROUND_CEILING ) );
                            }
                            product.getShops().add( shop );
                            list.add( product );
                            shop = new Shop();
                            product = new Product();
                            inProductTag = false;
                        }
                    }
                }
            }

        } catch ( XMLStreamException e ) {
            e.printStackTrace();
        } catch ( UnsupportedEncodingException e ) {
            e.printStackTrace();
        }
        if ( list.size() < 20 ) this.breakCondition = true;
        return list;
    }

    public String readElementBody( XMLEventReader reader ) {
        StringWriter writer = new StringWriter();
        int depth = 0;

        try {
            while ( reader.hasNext() ) {
                XMLEvent event = reader.nextEvent();

                if ( event.isStartElement() ) depth++;
                if ( event.isEndElement() ) {
                    depth--;
                    if ( depth < 0 ) break;
                }

                event.writeAsEncodedUnicode( writer );
            }
        } catch ( XMLStreamException e ) {
            e.printStackTrace();
        }

        return writer.getBuffer().toString();
    }

    public InputStream transformXML( ProductsJAXB productsJAXB ) {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        StringWriter writer = new StringWriter();
        try {
            JAXBContext context = JAXBContext.newInstance( productsJAXB.getClass() );
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty( Marshaller.JAXB_ENCODING, "UTF-8" );
            marshaller.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, true );
            marshaller.marshal( productsJAXB, os );
            marshaller.marshal( productsJAXB, writer );
        } catch ( JAXBException e ) {
            e.printStackTrace();
        }
        return new ByteArrayInputStream( os.toByteArray() );
    }

    public boolean validateXML( InputStream is, ProductsJAXB products ) {
        try {
            JAXBContext context = JAXBContext.newInstance( products.getClass() );
            SchemaFactory factory = SchemaFactory.newInstance( XMLConstants.W3C_XML_SCHEMA_NS_URI );
            Schema schema = factory.newSchema( new File( "src/main/resources/static/xsd/products.xsd" ) );
            Validator validator = schema.newValidator();
            InputSource input = new InputSource( is );
            validator.validate( new SAXSource( input ) );
            return true;
        } catch ( JAXBException e ) {
            e.printStackTrace();
        } catch ( SAXException e ) {
            e.printStackTrace();
        } catch ( IOException e ) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void run() {
        this.flag = true;
        this.breakCondition = false;
        this.result = 0;
        int i = 1;

        System.out.println( "Starting" );

        List<Product> productList = parseCPUBenchmark( preProcessCPUBenchmark( URIResolver( URLEnum.CPUBENCHMARK_1.getUrl() ) ) );
//        productList.addAll( parseCPUBenchmark( preProcessCPUBenchmark( URIResolver( URLEnum.CPUBENCHMARK_2.getUrl() ) ) ) );

        List<Product> productShopList = new ArrayList<>();

        while ( !breakCondition ) {
            String html2 = URIResolver( URLEnum.PHONGVU.getUrl() + "?p=" + i++ );
            InputStream is2 = preprocessPhongVu( html2 );
            productShopList.addAll( parsePhongVu( is2 ) );
        }

        for ( Product product : productList ) {
            for ( Product tempProduct : productShopList ) {
                if ( CrawlHelper.computeMatchingDensity(
                        product.getName()
                                .replaceAll( "\\s", "" )
                                .replaceAll( "-", "" )
                                .toLowerCase(),
                        tempProduct.getName()
                                .replaceAll( "\\s", "" )
                                .replaceAll( "-", "" )
                                .toLowerCase() ) == 100 ) {
                    List<Shop> shopList = tempProduct.getShops();
                    for ( Shop shop : shopList ) {
                        shop.setProduct( product );
                    }
                    product.getShops().addAll( shopList );
                }
            }
        }

        if ( validateXML( transformXML( EntityToJAXB.parseListProductToProductsJAXB( productShopList ) ), EntityToJAXB.parseListProductToProductsJAXB( productShopList ) ) ) {
            productRepository.saveListProducts( productList );
        }
    }
}
