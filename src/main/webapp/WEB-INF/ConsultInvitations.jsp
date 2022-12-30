<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@page import="java.util.ArrayList"%>
 <%@page import="be.giftapplication.javabeans.ListGift"%>
 <jsp:useBean id="listgift" class="be.giftapplication.javabeans.ListGift" scope="request"></jsp:useBean>
 <%! ArrayList<Customer> customers; %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Invitations</title>
</head>
<body>
<%@ include file="Header.jsp"%>
<% customers = listgift.getInvitations(); %>
<h2>Invitations de <%= listgift.getName() %></h2>
<table class="table">
	<tr>
		<th>Nom</th>
		<th>Prénom</th>
		<th></th>
	</tr>
	<% for(Customer c : customers){ %>
	
	<tr>
		<td><%= c.getLastName() %></td>
		<td><%= c.getFirstName() %></td>
	</tr>
	<%} %>
	</table>

</body>
</html>