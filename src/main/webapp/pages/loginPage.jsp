<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html>
<head>
    <title>Login</title>
    <style>
        <jsp:directive.include file="/pages/css/homePage.css" />
    </style>
</head>
<body>
<div class="mid_content">
    <h1>Travel agency</h1>
    <hr/>
    <form action="${pageContext.request.contextPath}/login" method="POST" class="login_forms">
        <%
            if(request.getAttribute("error") != null){
                String err = (String)request.getAttribute("error");
                out.println("<p>" + err + "</p>");
            }
        %>
        <label>Login:</label>
        <input type="text" name="login_field" class="text_inp"/>
        <label>Password:</label>
        <input type="password" name="pwd_field" class="text_inp"/>
        <button type="submit" class="sign_in_btn">Sign in</button>
    </form>
</div>

</body>
</html>
