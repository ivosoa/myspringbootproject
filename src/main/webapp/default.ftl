<#ftl encoding="utf-8">
<#assign tiles=JspTaglibs["http://tiles.apache.org/tags-tiles"]>
<!DOCTYPE html>
<html lang="en">
<head>
    <@tiles.insertAttribute name="header"/>
</head>
<body>
    <@tiles.insertAttribute name="content"/>
    <@tiles.insertAttribute name="footer"/>
</body>
</html>