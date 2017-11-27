<%@ page import="utility.CookieChecker" %>
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
<META http-equiv="refresh" content="1;URL=/isOrdered">
<html>
<head>
    <title>Finding your Order</title>
    <link rel="stylesheet" type="text/css" href="css/master.css">
    <link rel="icon" href="img/blackjek.png">
</head>
<body>
<div class="order">
    <%@ include file = "navbar.jsp" %>
    <script src="js/master.js"></script>
    <script type="text/javascript">chooseNavbar(0);</script>
    Finding Your Order ....

</div>
</body>
<script src="js/master.js"></script>
</html>
<% } %>