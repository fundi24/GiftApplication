<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="be.giftapplication.javabeans.ListGift"%>
<%@page import="be.giftapplication.javabeans.Gift"%>
<jsp:useBean id="listgift" class="be.giftapplication.javabeans.ListGift"
	scope="session"></jsp:useBean>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Invitation</title>
</head>
<body>
	<%@ include file="Header.jsp"%>
	<% 
	   ArrayList<Gift> gifts = (ArrayList<Gift>)listgift.getGifts();
	%>
	<h1><%= listgift.getName()%></h1>
	
	<table class="table">
		<tr>
			<th>Nom</th>
			<th>Description</th>
			<th>Prix</th>
			<th>Priorité</th>
			<th>Status</th>
			<th></th>
		</tr>
	<%if(listgift.getGifts().size() > 0){ %>
	
		<%for(Gift g : gifts){ %>
		<tr>
			<td><%= g.getName() %></td>
			<td><%= g.getDescription()%></td>
			<td><%= g.getPrice()%></td>
			<td><%= g.getPriority()%></td>
			<%if(g.isBooked() == true){ %>
			<td>Réservé</td>
			<%} else{ %>
			<td>Non réservé</td>
			<%} %>
			<form action="invitationconsultgift" method="get"> 
			<input type="hidden" name="idGift" 
				value="<%= g.getIdGift() %>" />
			<td><button type="submit" class = "btn btn-primary btn-sm">Consulter</button></td>
			</form>
		</tr>
		<%} %>
		
	<%} %>
	</table>

</body>
</html>