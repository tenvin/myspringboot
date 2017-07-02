<!DOCTYPE html>
<html>
<head lang="zh-CN">
    <meta charset="UTF-8"/>
    <title>111</title>

    <link rel="stylesheet" href="/css/bootstrap.css"/>
    <link rel="stylesheet" href="/css/my.css">

    <script src="/js/jquery.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>

</head>
<body>
<div >
    <h1>users</h1>
    <#list users as user>
        用户名：${user.name}
        年 龄: ${user.age}
    </#list>

</div>
</body>
</html>