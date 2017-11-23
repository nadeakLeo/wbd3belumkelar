<%--
  Created by IntelliJ IDEA.
  User: ASUS ROG
  Date: 11/7/2017
  Time: 7:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="utility.CookieChecker" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%
    if (request.getParameter("error") != null)
        response.sendRedirect("/signin?error=" + request.getParameter("error"));
    else
        response.sendRedirect("/signin");
%>