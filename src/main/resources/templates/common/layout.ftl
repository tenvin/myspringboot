<#macro html page_title page_tab="">
<!doctype html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>${page_title!site.name}</title>
    <link rel="icon" href="//favicon.ico">

    <link rel="stylesheet" href="/static/css/bootstrap.css"/>
<#--<link rel="stylesheet/less" href="/static/css/less/bootstrap.less">-->
<#--<script src="//cdn.bootcss.com/less.js/2.7.1/less.min.js"></script>-->
    <link rel="stylesheet" href="/static/css/github.css">
    <link rel="stylesheet" href="/static/css/pybbs.css">
    <script src="/static/js/jquery.min.js"></script>
    <script src="/static/js/bootstrap.min.js"></script>
    <script src="/static/libs/layer/layer.js"></script>
</head>
<body>
<div class="wrapper">
<#include "./header.ftl">
<@header page_tab=page_tab/>
    <div class="container">
    <#nested />
    </div>
</div>

<@footer/>
<script src="/static/js/pybbs.js"></script>
</body>
</html>
</#macro>