<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<jsp:useBean id="listgift" class="be.giftapplication.javabeans.ListGift"
	scope="request"></jsp:useBean>
<%@page import="be.giftapplication.javabeans.ListGift"%>
<%@page import="java.util.ArrayList"%>
<%!ArrayList<String> errors = new ArrayList<>();%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modification de la date d'expiration</title>
</head>
<body>
	<%@ include file="Header.jsp"%>
	
	<% errors = (ArrayList<String>) request.getAttribute("errors"); %>

	<h1><%= listgift.getName()%></h1>
	
	<form action="modifylistgift" method="post">
		<div class="mb-3">
			<label for="newDeadline" class="form-label">Nouvelle date d'expiration</label>
			<input type="date" class="form-control" name="newDeadline" id="newDeadline">
			<div class="form-text"><%=errors.get(0)%></div>
		</div>
		<div>
		<button type="submit" name="submit" id="submit" class="btn btn-primary">Envoyer</button>		</div>
	</form>
	<%
        if (request.getAttribute("modifyListGiftError") != null) {%>
        <div class="alert alert-danger">
       <p><%= request.getAttribute("modifyListGiftError")%></p> 
       </div>
    <% 
        }
    %>
    
    	<div class="mt-1">
		<form action="consultlistgift" method="get"> 
			<input type="hidden" name="idListGift" 
				value="<%= listgift.getIdListGift()%>" />
			<td><button type="submit" class = "btn btn-primary btn-sm">Retour</button></td>
		</form>
	</div>
</body>
</html>