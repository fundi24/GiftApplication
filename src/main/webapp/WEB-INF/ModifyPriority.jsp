<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="listgift" class="be.giftapplication.javabeans.ListGift"
	scope="request"></jsp:useBean>
<%@page import="java.util.ArrayList"%>
<%@page import="be.giftapplication.javabeans.ListGift"%>
<%@page import="be.giftapplication.javabeans.Gift"%>
<%! ArrayList<Gift> gifts;%>
<%! ArrayList<String> errors;%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modification priorité</title>
</head>
<body>
	<%@ include file="Header.jsp"%>
	<%
		gifts = listgift.getGifts();
		if(request.getAttribute("errors") != null){
			errors = (ArrayList<String>) request.getAttribute("errors");
		}
	%>
	<h2>Modification priorité</h2>
	<%
		for (int i=0; i < gifts.size(); i++) {
	%>
	<form action="modifypriority" method="post"> 
	<div class="m-2">
		<label for="priority-select"><%= gifts.get(i).getName() %></label>
		<select name="priority-select-<%= i %>" class="form-select" aria-label="Default select example">
				
			<% for(int j=1; j <= listgift.getGifts().size(); j++){ %>
				<option value="<%= j %>"><%= j %></option>
			<%} %>
		</select>
	</div>
	<% } %>
	<input type="hidden" name="idListGift" value="<%= listgift.getIdListGift() %>" />
	<div class="m-2"><button type="submit" class = "btn btn-primary btn-sm">Confirmer</button></div>
	</form>
	<% if(request.getAttribute("errors") != null) {%>
		<div class="alert alert-danger"><%= errors.get(0) %></div>
	<%} %>
	
	<% if(request.getAttribute("error") != null) {%>
		<div class="alert alert-danger"><%= request.getAttribute("error") %></div>
	<%} %>
	
	<% if(request.getAttribute("success") != null) {%>
		<div class="alert alert-success"><%= request.getAttribute("success") %></div>
	<%} %>
	
	<div class="mt-1">
		<form action="consultlistgift" method="get"> 
			<input type="hidden" name="idListGift" 
				value="<%= listgift.getIdListGift()%>" />
			<td><button type="submit" class = "btn btn-primary btn-sm">Retour</button></td>
		</form>
	</div>
	
</body>
</html>