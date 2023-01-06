<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="gift" class="be.giftapplication.javabeans.Gift"
	scope="request"></jsp:useBean>
<jsp:useBean id="listgift" class="be.giftapplication.javabeans.ListGift"
	scope="session"></jsp:useBean>
<%!String booked;
	String multiplePayment;%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cadeau</title>
</head>
<body>
	<%@ include file="Header.jsp"%>
	<%
	booked = gift.isBooked() ? "Réservé" : "Non réservé";
	multiplePayment = gift.isMultiplePayment() ? "Oui" : "Non";
	%>
	<h2 class="m-2">Mon cadeau</h2>
	<p></p>


	<div class="card" style="width: 50rem;">
		<%
		if (!gift.getPicture().equals("null")) {
		%>
		<img class="card-img-top"
			src="data:image/jpg;base64,<%=gift.getPicture()%>" />
		<%
		}
		%>
		<div class="card-body">
			<h5 class="card-title"><%=gift.getName()%></h5>
			<p class="card-text">
				Description :
				<%=gift.getDescription()%></p>
			<p class="card-text">
				Prix :
				<%=gift.getPrice()%>€
			</p>
			<p class="card-text">
				Priorité :
				<%=gift.getPriority()%></p>
			<p class="card-text">
				Statut :
				<%=booked%></p>
			<p class="card-text">
				Paiement Multiple :
				<%=multiplePayment%></p>
			<%if(!gift.getLinkToWebsite().equals("null")){ %>
			<p class="card-text">
				Lien :
				<%=gift.getLinkToWebsite()%></p><%} else{ %>
				<p class="card-text">
				Lien :
			  </p>
			  <%} %>
		</div>
	</div>


	
	<%
	if (!gift.isMultiplePayment()) {
	%>
	<form action="invitationconsultgift" method="post">
		<input type="hidden" name="idGift" value="<%=gift.getIdGift()%>" /> <input
			type="hidden" name="idListGift" value="<%=listgift.getIdListGift()%>" />
		<td><button type="submit" class="btn btn-primary btn-sm m-2">Offrir</button></td>
	</form>
	<%} %>
	<%
	if (gift.calculTotal() < gift.getPrice()) {
	%>
	<form action="multiplepaymentoffer" method="get">
		<input type="hidden" name="idGift" value="<%=gift.getIdGift()%>" /> <input
			type="hidden" name="idListGift" value="<%=listgift.getIdListGift()%>" />
		<td><button type="submit" class="btn btn-primary btn-sm m-2">Offrir
				à plusieurs</button></td>
	</form>
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
	<%
	if (request.getAttribute("error") != null) {
	%>
	<div class="alert alert-danger">
		<p><%=request.getAttribute("error")%></p>
	</div>
	<%
	}
	%>

	<div class="mt-1">
		<form action="invitationlistgift" method="get">
			<input type="hidden" name="idListGift"
				value="<%=listgift.getIdListGift()%>" />
			<td><button type="submit" class="btn btn-primary btn-sm m-2">Retour</button></td>
		</form>
	</div>

</body>
</html>