<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="enums.Roots" %>
<%@ page import="enums.Country" %>

<%
    Roots root = (Roots)request.getSession().getAttribute("roots");

    boolean isShown = false;
    if(root == Roots.ADMIN){
        isShown  = true;
    }
    request.setAttribute("toShowAdminPanel", isShown);
%>

<html>
<head>
    <title>Travel agency</title>
    <style><jsp:directive.include file="/pages/css/tables.css" /></style>
</head>
<body>
<div class="main_content">
    <h1>Travel agency: Routes</h1>
    <hr/>
    <c:if test="${toShowAdminPanel}">
        <form class="insert_div" action="${pageContext.request.contextPath}/routes?actionID=0" method="post">

            <label>From</label>
            <select name="from_field">
                <c:forEach var="country" items="${Country.values()}">
                    <option>${country}</option>
                </c:forEach>
            </select>

            <label>To</label>
            <select name="to_field">
                <c:forEach var="country" items="${Country.values()}">
                    <option>${country}</option>
                </c:forEach>
            </select>
            <label>Cost</label>
            <input type="number" name="cost_field" min="0" id="cost_field"/>

            <button class ="servise_btn" type="submit">Insert</button>
        </form>
        <hr/>
    </c:if>

    <form class="search" action="${pageContext.request.contextPath}/routes?actionID=1" method="post">
        <input type="text" name="search_q" placeholder="Enter something..."/>
        <div class="r_buttons">
            <input type="radio" name="search_par" id="rb1" value="1"/>
            <label for="rb1">Departure country</label>
            <input type="radio" name="search_par" id="rb2" value="2"/>
            <label for="rb2">Country of arrival</label>
            <input type="radio" name="search_par" id="rb3" value="3"/>
            <label for="rb3">Cost</label>
        </div>
        <button class="servise_btn">Search</button>
    </form>
    <hr/>
    <div class="table_div">
        <table>
            <thead>
            <th>Route from</th>
            <th>Route to</th>
            <th>Cost (USD)</th>
            <c:if test="${toShowAdminPanel}">
                <th>Edit</th>
                <th>Delete</th>
            </c:if>
            </thead>
            <tbody>
                <c:forEach var="route" items="${routes}">
                    <c:if test="${toShowAdminPanel}">
                        <tr>
                            <form action="${pageContext.request.contextPath}/routes?actionID=3&routeID=${route.id}" method="post">
                            <td>
                                <select class="td_inputs_select" name="${route.id}_from">
                                    <c:forEach var="country" items="${Country.values()}">
                                        <option <c:if test="${route.pointBegin == country}">selected</c:if>>${country}</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <td>
                                <select class="td_inputs_select" name="${route.id}_to">
                                    <c:forEach var="country" items="${Country.values()}">
                                        <option <c:if test="${route.pointEnd == country}">selected</c:if>>${country}</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <td>
                                <input type="number" min="0" class="td_inputs_num" value="${route.cost}" name="${route.id}_cost"/>
                            </td>
                            <td><button class="servise_btn">Edit</button></td>
                            <td><form action="${pageContext.request.contextPath}/routes?actionID=2&routeID=${route.id}" method="post"><button class="servise_btn">Delete</button></form></td>
                            </form>
                        </tr>
                    </c:if>
                    <c:if test="${!toShowAdminPanel}">
                        <tr>
                            <td>${route.pointBegin}</td>
                            <td>${route.pointEnd}</td>
                            <td>${route.cost}</td>
                        </tr>
                    </c:if>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>