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
                <div class="card-body">
                    <a href="#" class="product-name">
                        <p class="card-text" style="font-size: 13px">
                            Bộ vi xử lý
                            <xsl:value-of select="p:name"/>&#160;<xsl:if test="not(p:clockspeed = '')">(<xsl:value-of
                                select="p:clockspeed"/> GHz
                            <xsl:if test="not(p:turbospeed = '')">/<xsl:value-of select="p:turbospeed"/> GHz
                            </xsl:if>
                            )
                        </xsl:if>
                        </p>
                    </a>
                    <div class="product-action">
                        <a href="#" class="benchmark">
                            <b>Điểm:</b>
                            <b class="result">
                                <xsl:value-of select="p:benchmark"/>
                            </b>
                        </a>
                        <br/>
                        <a href="#" class="price">
                            <b>Giá:</b>
                            <b class="result">
                                <xsl:for-each select="p:shops/p:shop">
                                    <xsl:variable name="i" select="position()"/>
                                    <xsl:if test="$i = 1">
                                        <xsl:value-of
                                                select="translate(format-number(p:price, '###,###'), ',', '.')"/> ₫
                                    </xsl:if>
                                </xsl:for-each>
                            </b>
                        </a>
                        <xsl:element name="button">
                            <xsl:attribute name="class">btn btn-main-blue col-sm-12 compare</xsl:attribute>
                            <xsl:attribute name="value">
                                <xsl:value-of select="p:id"/>
                            </xsl:attribute>So sánh<i class="fas fa-plus"></i>
                        </xsl:element>
                    </div>
                </div>
            </div>
        </div>
    </xsl:template>
</xsl:stylesheet>