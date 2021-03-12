<%@ page import="entities.User" %>
<%@ page import="enums.Roots" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    User u = (User)request.getSession().getAttribute("currentUser");
    boolean isDisabled = true;
    if(u.getRoots() == Roots.ADMIN){
        isDisabled = false;
    }
%>

<html>
<head>
    <title>Home</title>
    <style><jsp:directive.include file="/pages/css/homePage.css" /></style>
</head>
<body>
<div class="mid_content">
    <h1>Travel agency</h1>
    <hr/>
    <div class="buttons">
        <a href="${pageContext.request.contextPath}/routes"><button>Routes</button></a><br/>
        <a href="${pageContext.request.contextPath}/trips" ><button>Trips</button></a><br/>
        <a href="${pageContext.request.contextPath}/clients" ><button <%out.print(isDisabled?"disabled":"");%>>Clients</button></a><br/>
        <a href="${pageContext.request.contextPath}/logout" ><button >Logout</button></a>
    </div>
</div>
</body>
</html>
