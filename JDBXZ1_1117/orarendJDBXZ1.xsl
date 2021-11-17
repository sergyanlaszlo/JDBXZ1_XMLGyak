<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:template match="/">
<html> 
<body>
  <h2>Órarend</h2>
  <table border="1">
    <tr bgcolor="#9acd32">
      <th style="text-align:left">Hétfő</th>
      <th style="text-align:left">Kedd</th>
       <th style="text-align:left">Szerda</th>
        <th style="text-align:left">Csütörtök</th>
         <th style="text-align:left">Péntek</th>
    </tr>
    <xsl:for-each select="/class/orarend">
    <tr>
      <td><xsl:value-of select="ora"/></td>
      <td><xsl:value-of select="targy"/></td>
      <td><xsl:value-of select="idopont"/></td>
      <td><xsl:value-of select="helyszin"/></td>
      <td><xsl:value-of select="oktato"/></td>
    </tr>
    </xsl:for-each>
  </table>
</body>
</html>
</xsl:template>
</xsl:stylesheet>
