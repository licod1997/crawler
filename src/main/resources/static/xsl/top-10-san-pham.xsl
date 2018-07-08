<?xml version="1.0" encoding="UTF-8" standalone="yes"?>

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:p="http://www.pickyourcpu.vn/schema/products"
                version="1.0">
    <xsl:output indent="yes" method="html" encoding="UTF-8"/>
    <xsl:template match="/">
        <xsl:choose>
            <xsl:when test="p:products = ''"></xsl:when>
            <xsl:otherwise>
                <div class="dropdown-menu">
                    <xsl:apply-templates select="p:products/p:product"/>
                </div>
            </xsl:otherwise>
        </xsl:choose>
    </xsl:template>

    <xsl:template match="p:products/p:product">
        <xsl:element name="a">
            <xsl:attribute name="class">dropdown-item</xsl:attribute>
            <xsl:attribute name="href">
                /chi-tiet-san-pham?id=<xsl:value-of select="p:id"/>
            </xsl:attribute>
            <xsl:value-of select="p:name"/>
        </xsl:element>
    </xsl:template>
</xsl:stylesheet>