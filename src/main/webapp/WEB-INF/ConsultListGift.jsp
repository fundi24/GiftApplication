<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="be.giftapplication.javabeans.ListGift"%>
<%@page import="be.giftapplication.javabeans.Gift"%>
<%@page import="java.time.LocalDate"%>
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
			<form action="consultgift" method="get"> 
			<input type="hidden" name="idGift" 
				value="<%= g.getIdGift() %>" />
			<td><button type="submit" class = "btn btn-primary btn-sm">Consulter</button></td>
			</form>
		</tr>
		<%} %>
		
	<%} %>
	</table>
	
	
	<div class="row g-3 m-2">
		<div class="col-auto">
			<form action="creategift" method="get"> 
				<input type="hidden" name="idListGift" value="<%= listgift.getIdListGift() %>" />
				<td><button type="submit" class = "btn btn-primary btn-sm">Ajouter cadeau</button></td>
			</form>
		</div>
		
		<div class="col-auto">
			<form action="modifypriority" method="get"> 
				<input type="hidden" name="idListGift" value="<%= listgift.getIdListGift() %>" />
				<% if(request.getAttribute("canModifyPriorityError") == null){ %>
					<td><button type="submit" class = "btn btn-primary btn-sm">Modifier priorité</button></td>
				<% } else {%> 
					<td><button type="submit" class = "btn btn-primary btn-sm" disabled>Modifier priorité</button></td>
				<% } %>
			</form>
		</div>
		<div class="col-auto">
			<form action="consultlistgift" method="post"> 
				<input type="hidden" name="idListGift" value="<%= listgift.getIdListGift() %>" />
				<% if(listgift.getDeadline().equals(LocalDate.of(1000, 1, 1))){ %>
				<td><button type="submit" class = "btn btn-primary btn-sm">Activer/Désactiver</button></td>
				<% }%> 
			</form>
			<form action="modifylistgift" method="get"> 
				<input type="hidden" name="idListGift" value="<%= listgift.getIdListGift() %>" />
				<% if(!listgift.getDeadline().equals(LocalDate.of(1000, 1, 1))){ %>
				<td><button type="submit" class = "btn btn-primary btn-sm">Activer/Désactiver</button></td>
				<% }%> 
			</form>
		</div>
		<div class="col-auto">
			<form action="consultinvitations" method="get"> 
				<input type="hidden" name="idListGift" value="<%= listgift.getIdListGift() %>" />
				<td><button type="submit" class = "btn btn-primary btn-sm">Consulter invitations</button></td>
			</form>
		</div>
	</div>
	
	<div class="input-group m-2 ">
	    <span class="input-group-text" id="basic-addon3">Lien</span>
	    <input type="text" class="form-control"  value="<%= request.getAttribute("link") %>" aria-describedby="basic-addon3" readonly>
  	</div>
	
	
	<div class="mt-1">
		<form action="mygiftlists" method="get"> 
			<input type="hidden" name="idListGift" 
				value="<%= listgift.getIdListGift()%>" />
			<td><button type="submit" class = "btn btn-primary btn-sm">Retour</button></td>
		</form>
	</div>
	
</body>
</html>