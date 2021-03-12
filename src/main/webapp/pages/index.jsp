<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <title>Travel agency</title>
    <style>
        <jsp:directive.include file="/pages/css/homePage.css" />
    </style>
</head>
<body>
<div class="mid_content">
    <h1>Travel agency</h1>
    <hr/>
    <div class="buttons">
        <a href="${pageContext.request.contextPath}/login"><button id="login_btn">Sign in</button></a><br/>
        <a href="${pageContext.request.contextPath}/register"><button id="register_btn">Sign up</button></a>
    </div>
</div>
</body>
</html>
