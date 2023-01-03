<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>

<%@page import="be.giftapplication.javabeans.Customer"%>
<%!ArrayList<Customer> customers;%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Invitation</title>
</head>
<body>
<%@ include file="Header.jsp"%>
<% customers = (ArrayList<Customer>)request.getAttribute("customers");%>

<table class="table">
	<tr>
		<th>Nom d'utilisateur</th>
		<th></th>
	</tr>
	<% for(Customer c : customers){ %>
	
	<tr>
		<td><%= c.getUsername() %></td>
		
		<form action="invite" method="post"> 
			<input type="hidden" name="idCustomer" 
				value="<%= c.getIdCustomer() %>" />
			<input type="hidden" name="idListGift" 
				value="<%= request.getAttribute("idListGift") %>" />
			<td><button type="submit" class = "btn btn-primary btn-sm">Inviter</button></td>
		</form>
	</tr>
	<%} %>
	</table>
	
	<div class="mt-1">
		<form action="consultlistgift" method="get"> 
			<input type="hidden" name="idListGift" 
				value="<%= request.getAttribute("idListGift")%>" />
			<td><button type="submit" class = "btn btn-primary btn-sm">Retour</button></td>
		</form>
	</div>
	
	<%
	if (request.getAttribute("Error") != null) {
	%>
	<div class="alert alert-danger">
		<p><%=request.getAttribute("Error")%></p>
	</div>
	<%
	}
	%>
	
	<%	
	if (request.getAttribute("Error2") != null) {
	%>
	<div class="alert alert-danger">
		<p><%=request.getAttribute("Error2")%></p>
	</div>
	<%
	}
	%>
	<%
	if (request.getAttribute("success") != null) {
	%>
	<div class="alert alert-success">
		<p><%=request.getAttribute("success")%></p>
	</div>
	<%
	}
	%>
	
	

</body>
</html>