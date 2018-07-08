<?xml version="1.0" encoding="UTF-8" standalone="yes"?>

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:p="http://www.pickyourcpu.vn/schema/products"
                version="1.0">
    <xsl:output indent="yes" method="html" encoding="UTF-8"/>
    <xsl:template match="/">
        <xsl:choose>
            <xsl:when test="p:products = ''"></xsl:when>
            <xsl:otherwise>
                <table class="table table-bordered">
                    <tbody>
                        <tr>
                            <td class="field"></td>
                            <xsl:for-each select="p:products/p:product">
                                <td class="result">
                                    <xsl:choose>
                                        <xsl:when test="contains(p:name, 'Intel')">
                                            <img class="manufacture-img" src="/img/1200px-Intel-logo.svg.png"/>
                                        </xsl:when>
                                        <xsl:when test="contains(p:name, 'AMD')">
                                            <img class="manufacture-img" src="/img/AMD-red-white-logo.png"/>
                                        </xsl:when>
                                        <xsl:otherwise>
                                            <i class="fas fa-question manufacture-img"></i>
                                        </xsl:otherwise>
                                    </xsl:choose>
                                    <div class="close-button">
                                        <xsl:element name="i">
                                            <xsl:attribute name="class">fas fa-times-circle remove-item</xsl:attribute>
                                            <xsl:attribute name="value">
                                                <xsl:value-of select="p:id"/>
                                            </xsl:attribute>
                                        </xsl:element>
                                    </div>
                                </td>
                            </xsl:for-each>
                        </tr>
                        <tr>
                            <td>Tên sản phẩm</td>
                            <xsl:for-each select="p:products/p:product">
                                <td>
                                    <xsl:value-of select="p:name"/>
                                </td>
                            </xsl:for-each>
                        </tr>
                        <tr>
                            <td>Socket</td>
                            <xsl:for-each select="p:products/p:product">
                                <td>
                                    <xsl:value-of select="p:socket"/>
                                </td>
                            </xsl:for-each>
                        </tr>
                        <tr>
                            <td>Xung nhịp cơ bản</td>
                            <xsl:for-each select="p:products/p:product">
                                <xsl:element name="td">
                                    <xsl:attribute name="class">clockspeed</xsl:attribute>
                                    <xsl:attribute name="value">
                                        <xsl:value-of select="p:clockspeed"/>
                                    </xsl:attribute>
                                    <xsl:if test="not(p:clockspeed = '')">
                                        <xsl:value-of select="p:clockspeed"/> GHz
                                    </xsl:if>
                                </xsl:element>
                            </xsl:for-each>
                        </tr>
                        <tr>
                            <td>Xung nhịp tối đa</td>
                            <xsl:for-each select="p:products/p:product">
                                <xsl:element name="td">
                                    <xsl:attribute name="class">turbospeed</xsl:attribute>
                                    <xsl:attribute name="value">
                                        <xsl:value-of select="p:turbospeed"/>
                                    </xsl:attribute>
                                    <xsl:if test="not(p:turbospeed = '')">
                                        <xsl:value-of select="p:turbospeed"/> GHz
                                    </xsl:if>
                                </xsl:element>
                            </xsl:for-each>
                        </tr>
                        <tr>
                            <td>Số nhân</td>
                            <xsl:for-each select="p:products/p:product">
                                <xsl:element name="td">
                                    <xsl:attribute name="class">no-of-cores</xsl:attribute>
                                    <xsl:attribute name="value">
                                        <xsl:value-of select="p:noOfCores"/>
                                    </xsl:attribute>
                                    <xsl:value-of select="p:noOfCores"/>
                                </xsl:element>
                            </xsl:for-each>
                        </tr>
                        <tr>
                            <td>Mức tiêu thụ điện</td>
                            <xsl:for-each select="p:products/p:product">
                                <xsl:element name="td">
                                    <xsl:attribute name="class">TDP</xsl:attribute>
                                    <xsl:attribute name="value">
                                        <xsl:value-of select="p:TDP"/>
                                    </xsl:attribute>
                                    <xsl:value-of select="round(p:TDP*100) div 100"/> W
                                </xsl:element>
                            </xsl:for-each>
                        </tr>
                        <tr>
                            <td>Điểm</td>
                            <xsl:for-each select="p:products/p:product">
                                <xsl:element name="td">
                                    <xsl:attribute name="class">benchmark</xsl:attribute>
                                    <xsl:attribute name="value">
                                        <xsl:value-of select="p:benchmark"/>
                                    </xsl:attribute>
                                    <xsl:value-of select="p:benchmark"/>
                                </xsl:element>
                            </xsl:for-each>
                        </tr>
                        <tr>
                            <td>Giá</td>
                            <xsl:for-each select="p:products/p:product">
                                <xsl:for-each select="p:shops/p:shop">
                                    <xsl:variable name="i" select="position()"/>
                                    <xsl:if test="$i = 1">
                                        <xsl:element name="td">
                                            <xsl:attribute name="class">price</xsl:attribute>
                                            <xsl:attribute name="value">
                                                <xsl:value-of select="p:price"/>
                                            </xsl:attribute>
                                            <xsl:value-of
                                                    select="translate(format-number(p:price, '###,###'), ',', '.')"/> ₫
                                        </xsl:element>
                                    </xsl:if>
                                </xsl:for-each>
                            </xsl:for-each>
                        </tr>
                        <tr>
                            <td>Điểm/Giá (x 10.000 ₫)</td>
                            <xsl:for-each select="p:products/p:product">
                                <td class="benchmark-per-price"></td>
                            </xsl:for-each>
                        </tr>
                        <tr>
                            <td class="product-detail"></td>
                            <xsl:for-each select="p:products/p:product">
                                <td class="product-detail">
                                    <xsl:element name="button">
                                        <xsl:attribute name="class">btn btn-main-blue col-sm-12</xsl:attribute>
                                        <xsl:attribute name="value">
                                            <xsl:value-of select="p:id"/>
                                        </xsl:attribute>
                                        Chi tiết sản phầm
                                    </xsl:element>
                                </td>
                            </xsl:for-each>
                        </tr>
                    </tbody>
                </table>
            </xsl:otherwise>
        </xsl:choose>
    </xsl:template>
</xsl:stylesheet>