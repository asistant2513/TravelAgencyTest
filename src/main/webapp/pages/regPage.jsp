<%@ page import="enums.Country" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!doctype html>
<html>
<head>
    <title>Registration</title>
    <style><jsp:directive.include file="/pages/css/homePage.css" /></style>
</head>
<body>
<div class="mid_content">
    <h1>Travel agency</h1>
    <hr/>
    <form action="${pageContext.request.contextPath}/register" method="POST" class="login_forms">
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
        <label>Repeat password:</label>
        <input type="password" name="pwd_field_rpt" class="text_inp"/>
        <label>Name:</label>
        <input type="text" name="name_field" class="text_inp"/>
        <label>Surname:</label>
        <input type="text" name="surname_field" class="text_inp"/>
        <label>Age:</label>
        <input type="number" name="age_field" class="text_inp" max="100" min="18" value="18"/>
        <label>Homeland:</label>
        <select class="country_select" name="country_field" >
            <c:forEach var="country" items="${Country.values()}">
                <option>${country}</option>
            </c:forEach>
        </select>
        <button type="submit" class="sign_up_btn">Sign up</button>
    </form>
</div>
</body>
</html>
