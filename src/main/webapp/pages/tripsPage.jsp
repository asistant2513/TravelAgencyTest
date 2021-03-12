<%@ page import="enums.Roots" %>
<%@ page import="enums.TravelType" %>
<%@ page import="entities.Route" %>
<%@ page import="java.util.List" %>
<%@ page import="utils.RouteProviderUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    Integer clientID = 0;
    for (Cookie c : request.getCookies()) {
        if(c.getName().equals("clientID")){
            clientID = Integer.parseInt(c.getValue());
        }
    }
    request.setAttribute("clientId", clientID);
    Roots root = (Roots)request.getSession().getAttribute("roots");
    boolean isShown = false;
    if(root == Roots.ADMIN){
        isShown  = true;
    }
    request.setAttribute("toShowAdminPanel", isShown);

    List<Route> routes = new RouteProviderUtil().getAll();
    request.setAttribute("routes", routes);
%>

<html>
<head>
    <title>Travel agency</title>
    <style><jsp:directive.include file="/pages/css/tables.css" /></style>
</head>
<body>
<div class="main_content">
    <h1>Travel agency: Trips</h1>
    <hr/>

    <c:if test="${!toShowAdminPanel}">
        <form class="insert_div" action="${pageContext.request.contextPath}/trips?actionID=0" method="post">

            <label>Date of departure</label>
            <input type="date" name = "dbegin_field"/>

            <label>Date of departure</label>
            <input type="date" name = "dend_field"/>

            <label>Trip type</label>
            <select name="type_field">
                <c:forEach var="type" items="${TravelType.values()}">
                    <option>${type}</option>
                </c:forEach>
            </select>

            <label>Route</label>
            <select name="route_field">
                <c:forEach var="route" items="${routes}">
                <option value="${route.id}">${route.pointBegin} -> ${route.pointEnd}</option>
                </c:forEach>
            </select>
            <button class ="servise_btn" type="submit">Insert</button>
        </form>
        <hr/>
    </c:if>

    <form class="search" action="${pageContext.request.contextPath}/trips?actionID=1" method="post">
        <input type="text" name="search_q" placeholder="Enter something..."/>
        <div class="r_buttons">
            <input type="radio" name="search_par" id="rb1" value="1"/>
            <label for="rb1">Date of departure</label>
            <input type="radio" name="search_par" id="rb2" value="2"/>
            <label for="rb2">Date of arrival</label>
            <input type="radio" name="search_par" id="rb3" value="3"/>
            <label for="rb3">Travel type</label>
        </div>
        <button class="servise_btn">Search</button>
    </form>
    <hr/>
    <div class="table_div">
        <table>
            <thead>
            <th>ID</th>
            <th>Date begin</th>
            <th>Date end</th>
            <th>Trip type</th>
            <c:if test="${!toShowAdminPanel}">
                <th>Route</th>
                <th>Edit</th>
                <th>Delete</th>
            </c:if>
            <c:if test="${toShowAdminPanel}">
                <th>Client</th>
                <th>Route</th>
            </c:if>
            </thead>
            <tbody>
            <c:if test="${toShowAdminPanel}">
            <c:forEach var="trip" items="${trips}">
                <tr>
                    <td>${trip.id}</td>
                    <td>${trip.dateBegin}</td>
                    <td>${trip.dateEnd}</td>
                    <td>${trip.type}</td>
                    <td>${trip.client.name} ${trip.client.surname}</td>
                    <td>${trip.route.pointBegin}  -> ${trip.route.pointEnd}</td>
                </tr>
            </c:forEach>
            </c:if>
            <c:if test="${!toShowAdminPanel}">
                <c:forEach var="trip" items="${trips}">
                    <c:if test="${trip.client.id == clientId}">
                        <tr>
                            <form action="${pageContext.request.contextPath}/trips?actionID=2&tripID=${trip.id}" method="post">
                                <td>${trip.id}</td>
                                <td>${trip.dateBegin}</td>
                                <td>${trip.dateEnd}</td>
                                <td>
                                    <select name="${trip.id}_type">
                                        <c:forEach var="type" items="${TravelType.values()}">
                                            <option <c:if test="${trip.type == type}">selected</c:if>>${type}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td>${trip.route.pointBegin}  -> ${trip.route.pointEnd}</td>
                                <td><button class="servise_btn">Edit</button></td>
                                <td><form action="${pageContext.request.contextPath}/trips?actionID=3&tripID=${trip.id}" method="post"><button class="servise_btn">Delete</button></form></td>
                            </form>
                        </tr>
                    </c:if>
                </c:forEach>
            </c:if>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>