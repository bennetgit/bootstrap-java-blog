<html>
<head>
<#include "head_header.ftl">
</head>
<body>
<#include "header.ftl">
<div class="container">
    <div class="row">
        <div class="col-sm-8 blog-main">
            <div id="blogdata">
            <#--adddata to this position-->
            ${aboutContent}

            <#if edit??>
                <a href="/editAbout">edit</a>
            </#if>
            <#--test build.-->
            </div>
</body>
        <#include "footerjs.ftl">

<script type="text/javascript">
    $(document).ready(function () {
        $("#_aboutLink").parent().addClass("active");
    });
</script>
</html>
