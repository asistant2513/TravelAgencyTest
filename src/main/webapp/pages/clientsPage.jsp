<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Travel agency</title>
    <style><jsp:directive.include file="/pages/css/tables.css" /></style>
</head>
<body>
<div class="main_content">
    <h1>Travel agency: Clients</h1>
    <hr/>

    <form class="search" action="${pageContext.request.contextPath}/clients?actionID=1" method="post">
        <input type="text" name="search_q" placeholder="Enter something..."/>
        <div class="r_buttons">
            <input type="radio" name="search_par" id="rb1" value="1"/>
            <label for="rb1">Name</label>
            <input type="radio" name="search_par" id="rb2" value="2"/>
            <label for="rb2">Surname</label>
            <input type="radio" name="search_par" id="rb3" value="3"/>
            <label for="rb3">Age</label>
            <input type="radio" name="search_par" id="rb4" value="4"/>
            <label for="rb4">Country</label>
        </div>
        <button class="servise_btn">Search</button>
    </form>
    <hr/>
    <div class="table_div">
        <table>
            <thead>
            <th>Name</th>
            <th>Surname</th>
            <th>Age</th>
            <th>Country</th>
            <th>User login</th>
            </thead>
            <tbody>
            <c:forEach var="client" items="${clients}">
                    <tr>
                        <td>${client.name}</td>
                        <td>${client.surname}</td>
                        <td>${client.age}</td>
                        <td>${client.country}</td>
                        <td>${client.user.login}</td>
                    </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>