<%@ page import="utility.CookieChecker" %>
<%@ page import="java.util.Map" %>
<%--
  Created by IntelliJ IDEA.
  User: ASUS ROG
  Date: 11/7/2017
  Time: 7:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    if (!CookieChecker.check(request.getCookies())) {
        response.sendRedirect("index.jsp");
    } else {
%>
<!DOCTYPE html>
<html>
<head>
    <title>Blackjek Order</title>
    <link rel="stylesheet" type="text/css" href="css/master.css">
    <link rel="icon" href="img/blackjek.png">
</head>
<body>
<div class="order">
    <%@ include file = "navbar.jsp" %>
    <script src="js/master.js"></script>
    <script type="text/javascript">chooseNavbar(0);</script>
<%
    if (session.getAttribute("is_driver").equals("false")) {
%>
    <div class="order-title">
        <div class="font-bebas">
            <h1>Make an order</h1>
        </div>
        <div class="order-steps">
            <div class="step-field flex-center current-step">
                <div class="step-number flex-center">1</div>
                <div class="step-text">Select Destination</div>
            </div>
            <div class="step-field flex-center">
                <div class="step-number flex-center">2</div>
                <div class="step-text">Select a Driver</div>
            </div>
            <div class="step-field flex-center">
                <div class="step-number flex-center">3</div>
                <div class="step-text">Chat driver</div>
            </div>
            <div class="step-field flex-center">
                <div class="step-number flex-center">4</div>
                <div class="step-text">Complete your order</div>
            </div>
        </div>
    </div>
    <div class="trip-form">
        <form method="get" action="order2" onsubmit="return validateTripForm(this)">

            <table class="trip-table-form">
                <tr>
                    <td class="form-input-title">Picking point</td>
                    <td class="paddedcell"><input class="width-full" type="text" name="pick"></td>
                </tr>
                <tr>
                    <td class="form-input-title">Destination</td>
                    <td class="paddedcell"><input class="width-full" type="text" name="dest"></td>
                </tr>
                <tr>
                    <td class="form-input-title">Preferred Driver</td>
                    <td class="paddedcell"><input class="width-full" type="text" name="pref-driver" placeholder="(optional)"></td>
                </tr>
            </table>
            <br><br><br>
            <div class="btn-wrapper">
                <button type="submit" class="green-button">NEXT</button>
            </div>
        </form>
    </div>
</div>
<%
    } else {
        %>
    <a href="findorder">Find Order</a>
<%
    }
%>
</body>
<script src="js/master.js"></script>
</html>
<% } %>