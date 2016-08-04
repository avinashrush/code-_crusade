<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
  xmlns:xs="http://www.w3.org/2001/XMLSchema"
  xmlns:f="Functions"
  version="2.0">

  <xsl:variable name="properties" select="unparsed-text('Input.properties')" as="xs:string"/>

  <xsl:template match="/" name="main">
    <xsl:value-of select="f:getProperty('language')"/>
  </xsl:template>

  <xsl:function name="f:getProperty" as="xs:string?">
    <xsl:param name="key" as="xs:string"/>
    <xsl:variable name="lines" as="xs:string*" select="
      for $x in 
        for $i in tokenize($properties, '\n')[matches(., '^[^!#]')] return
          tokenize($i, '=')
        return translate(normalize-space($x), '\', '')"/>
    <xsl:sequence select="$lines[index-of($lines, $key)+1]"/>
  </xsl:function>

</xsl:stylesheet>