<?xml version="1.0" encoding="UTF-8" standalone="yes"?>

<xsl:stylesheet xmlns:p="http://www.pickyourcpu.vn/schema/products"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                version="1.0">
    <xsl:output indent="yes" method="html" encoding="UTF-8"/>
    <xsl:template match="/">
        <div class="col col-sm-12">
            <div class="alert bg-light" role="alert">
                <span style="font-size: 1.3rem">
                    <b>Danh sách sản phẩm</b>
                </span>
            </div>
        </div>
        <xsl:apply-templates select="p:response/p:products/p:product"/>
    </xsl:template>

    <xsl:template match="p:response/p:products/p:product">
        <div class="col col-sm-3">
            <div class="card">
                <xsl:element name="a">
                    <xsl:attribute name="href">
                        /chi-tiet-san-pham?id=<xsl:value-of select="p:id"/>
                    </xsl:attribute>
                    <xsl:choose>
                        <xsl:when test="contains(p:name, 'Intel')">
                            <img class="card-img-top" src="/img/1200px-Intel-logo.svg.png"/>
                        </xsl:when>
                        <xsl:when test="contains(p:name, 'AMD')">
                            <img class="card-img-top" src="/img/AMD-red-white-logo.png"/>
                        </xsl:when>
                        <xsl:otherwise>
                            <i class="fas fa-question"></i>
                        </xsl:otherwise>
                    </xsl:choose>
                </xsl:element>
                <div class="card-body">
                    <xsl:element name="a">
                        <xsl:attribute name="class">product-name</xsl:attribute>
                        <xsl:attribute name="href">
                            /chi-tiet-san-pham?id=<xsl:value-of select="p:id"/>
                        </xsl:attribute>
                        <p class="card-text" style="font-size: 13px">
                            Bộ vi xử lý
                            <xsl:value-of select="p:name"/>&#160;<xsl:if test="not(p:clockspeed = '')">(<xsl:value-of
                                select="p:clockspeed"/> GHz<xsl:if test="not(p:turbospeed = '')">/<xsl:value-of select="p:turbospeed"/> GHz</xsl:if>)
                        </xsl:if>
                        </p>
                    </xsl:element>
                    <div class="product-action">
                        <xsl:element name="a">
                            <xsl:attribute name="class">benchmark</xsl:attribute>
                            <xsl:attribute name="href">
                                /chi-tiet-san-pham?id=<xsl:value-of select="p:id"/>
                            </xsl:attribute>
                            <b>Điểm: </b>
                            <b class="result">
                                <xsl:value-of select="p:benchmark"/>
                            </b>
                        </xsl:element>
                        <br/>
                        <xsl:element name="a">
                            <xsl:attribute name="class">price</xsl:attribute>
                            <xsl:attribute name="href">
                                /chi-tiet-san-pham?id=<xsl:value-of select="p:id"/>
                            </xsl:attribute>
                            <b>Giá: </b>
                            <b class="result">
                                <xsl:for-each select="p:shops/p:shop">
                                    <xsl:variable name="i" select="position()"/>
                                    <xsl:if test="$i = 1">
                                        <xsl:value-of
                                                select="translate(format-number(p:price, '###,###'), ',', '.')"/> ₫
                                    </xsl:if>
                                </xsl:for-each>
                            </b>
                        </xsl:element>
                        <xsl:element name="button">
                            <xsl:attribute name="class">btn btn-main-blue col-sm-12 compare</xsl:attribute>
                            <xsl:attribute name="value">
                                <xsl:value-of select="p:id"/>
                            </xsl:attribute>So sánh <i class="fas fa-plus"></i>
                        </xsl:element>
                    </div>
                </div>
            </div>
        </div>
    </xsl:template>
</xsl:stylesheet>