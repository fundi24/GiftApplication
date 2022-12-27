<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="be.giftapplication.javabeans.ListGift"%>
<%!ArrayList<ListGift> giftLists;%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mes listes</title>
</head>
<body>
	<%@ include file="Header.jsp"%>
	<% giftLists = (ArrayList<ListGift>)request.getAttribute("giftLists"); %>

	<table class="table">
	<tr>
		<th>Nom</th>
		<th>Theme</th>
		<th>Date limite</th>
		<th>Statut</th>
		<th></th>
	</tr>
	<% for(ListGift l : giftLists){ %>
	
	<tr>
		<td><%= l.getName() %></td>
		<td><%= l.getTheme() %></td>
		<td><%= l.getDeadline() %></td>
		<%if(l.isStatus() == true){ %>
			<td>Activée</td>
		<%} else{ %>
			<td>Désactivée</td>
		<%} %>
		<form action="consultlistgift" method="get"> 
			<input type="hidden" name="idListGift" 
				value="<%= l.getIdListGift() %>" />
			<td><button type="submit" name="ConsultListBtn" value="ConsultList" class = "btn btn-primary btn-sm">Consulter</button></td>
		</form>
	</tr>
	<%} %>
	</table>

	<a href="createlistgift" class="btn btn-primary btn-sm active">Ajouter liste</a>

</body>
</html>