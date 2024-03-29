<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>

<jsp:useBean id="gift" class="be.giftapplication.javabeans.Gift"
	scope="request"></jsp:useBean>
<%!String booked;
	String multiplePayment;%>
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
	%>
	<h2 class="m-2">Mon cadeau</h2>
	<p></p>


	<div class="card m-2" style="width: 25rem;">
		<%
		if (!gift.getPicture().equals("null")) {
		%>
		<img class="card-img-top"
			src="data:image/jpg;base64,<%= gift.getPicture()%>" />
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

	<div class="m-3">
		<%
		if (!gift.isBooked()) {
		%>
		<div class="mt-1">
			<form action="modifygift" method="get">
				<input type="hidden" name="idGift" value="<%=gift.getIdGift()%>" />
				<td><button type="submit" class="btn btn-primary btn-sm">Modifier</button></td>
			</form>
		</div>
		<%
		} else {
		%>
		<div class="mt-1">
			<form action="consultparticipations" method="get">
				<input type="hidden" name="idGift" value="<%=gift.getIdGift()%>" />
				<td><button type="submit" class="btn btn-primary btn-sm">Consulter
						participations</button></td>
			</form>
		</div>
		<%
		}
		%>
	</div>

	<div class="mt-1">
		<form action="consultlistgift" method="get">
			<input type="hidden" name="idListGift"
				value="<%=request.getAttribute("idListGift")%>" />
			<td><button type="submit" class="btn btn-primary btn-sm">Retour</button></td>
		</form>
	</div>

</body>
</html>