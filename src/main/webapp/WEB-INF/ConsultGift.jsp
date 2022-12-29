<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<jsp:useBean id="gift" class="be.giftapplication.javabeans.Gift"
	scope="request"></jsp:useBean>
<%! 
	String booked;
	String multiplePayment;
	String link = "";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mon cadeau</title>
</head>
<body>
	<%@ include file="Header.jsp"%>
	<%
		booked = gift.isBooked() ? "Réservé" : "Non réservé";
		multiplePayment = gift.isMultiplePayment() ? "Oui" : "Non";
		
		if(gift.getLinkToWebsite().equals(null)){
			link = gift.getLinkToWebsite();
		}
	
	%>
	<h2 class="m-2">Mon cadeau</h2>

	<div class="card" style="width: 50rem;">
		<div class="card-body">
			<h5 class="card-title"><%= gift.getName() %></h5>
			<p class="card-text"> Description : <%= gift.getDescription() %></p>
			<p class="card-text"> Prix : <%= gift.getPrice() %>€</p>
			<p class="card-text"> Priorité : <%= gift.getPriority() %></p>
			<p class="card-text"> Statut : <%= booked %></p>
			<p class="card-text"> Paiement Multiple : <%= multiplePayment %></p>
			<%-- <p class="card-text"> Somme payée : <%=  %></p> --%>
			<p class="card-text"> Lien : <%= link %></p>
		</div>
	</div>
	
	<div class="m-3" >
		<% if(!gift.isBooked()) { %>
		<a href="modifyGift" class="btn btn-primary btn-sm">Modifier</a>
		<%} else { %>
		<a href="Consultparticipations" class="btn btn-primary btn-sm">Consulter participations</a>
		<% } %>
	</div>
	
</body>
</html>