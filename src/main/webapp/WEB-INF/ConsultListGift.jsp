<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="be.giftapplication.javabeans.ListGift"%>
<%@page import="be.giftapplication.javabeans.Gift"%>
<jsp:useBean id="listgift" class="be.giftapplication.javabeans.ListGift" scope="request"></jsp:useBean>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ma liste</title>
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
			<form action="consultgift" method="get"> 
			<input type="hidden" name="idGift" 
				value="<%= g.getIdGift() %>" />
			<td><button type="submit" class = "btn btn-primary btn-sm">Consulter</button></td>
			</form>
			<form action="modifygift" method="get"> 
			<input type="hidden" name="idGift" 
				value="<%= g.getIdGift() %>" />
			<td><button type="submit" class = "btn btn-primary btn-sm">Modifier</button></td>
			</form>
		</tr>
		<%} %>
		
	<%} %>
	</table>
	<div class="row g-3">
		<div class="col-auto">
			<form action="creategift" method="get"> 
				<input type="hidden" name="idListGift" value="<%= listgift.getIdListGift() %>" />
				<td><button type="submit" class = "btn btn-primary btn-sm">Ajouter cadeau</button></td>
			</form>
		</div>
		<div class="col-auto">
			<form action="modifypriority" method="get"> 
				<input type="hidden" name="idListGift" value="<%= listgift.getIdListGift() %>" />
				<td><button type="submit" class = "btn btn-primary btn-sm">Modifier priorité</button></td>
			</form>
		</div>
		<div class="col-auto">
			<form action="modifystatuslist" method="get"> 
				<input type="hidden" name="idListGift" value="<%= listgift.getIdListGift() %>" />
				<td><button type="submit" class = "btn btn-primary btn-sm">Activer/Désactiver</button></td>
			</form>
		</div>
		<div class="col-auto">
			<form action="consultinvitations" method="get"> 
				<input type="hidden" name="idListGift" value="<%= listgift.getIdListGift() %>" />
				<td><button type="submit" class = "btn btn-primary btn-sm">Consulter invitations</button></td>
			</form>
		</div>
	</div>
	
	<div class="mt-1" ><a href="mygiftlists" class="btn btn-primary btn-sm active">Retour</a></div>
	
</body>
</html>