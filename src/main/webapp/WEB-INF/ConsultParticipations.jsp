<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.util.ArrayList"%>
 <%@page import="be.giftapplication.javabeans.Gift"%>
  <%@page import="be.giftapplication.javabeans.Participation"%>
 <jsp:useBean id="gift" class="be.giftapplication.javabeans.Gift" scope="request"></jsp:useBean>
 <%! ArrayList<Participation> participations; %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Participations</title>
</head>
<body>
<%@ include file="Header.jsp"%>

<% participations = gift.getParticipations(); %>
<h2>Participations pour <%= gift.getName() %></h2>
<table class="table">
	<tr>
		<th>Nom</th>
		<th>Prénom</th>
		<th>Montant</th>
	</tr>
	<% for(Participation p : participations){ %>
	
	<tr>
		<td><%= p.getCustomer().getLastName() %></td>
		<td><%= p.getCustomer().getFirstName() %></td>
		<td><%= p.getAmountPaid() %></td>
	</tr>
	<%} %>
	</table>
	
	
	<p>Total : <%= gift.calculTotal() %></p>
	
	<div class="mt-1">
		<form action="consultgift" method="get"> 
			<input type="hidden" name="idGift" 
				value="<%= gift.getIdGift() %>" />
			<td><button type="submit" class = "btn btn-primary btn-sm">Retour</button></td>
		</form>
	</div>
	

</body>
</html>