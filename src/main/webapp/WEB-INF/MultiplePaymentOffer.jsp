<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="gift" class="be.giftapplication.javabeans.Gift"
	scope="request"></jsp:useBean>
	<jsp:useBean id="listgift" class="be.giftapplication.javabeans.ListGift"
	scope="session"></jsp:useBean>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Paiement multiple</title>
</head>
<body>
	<%@ include file="Header.jsp"%>
	<h1><%=gift.getName()%></h1>
	<form action="multiplepaymentoffer" method="POST">
		<div class="mb-3">
			<label for="price" class="form-label">Prix</label> <input
				type="number" class="form-control" name="price" id="price" min="0"
				step="0.01">
		</div>
		<input type="hidden" name="idGift" value="<%=gift.getIdGift()%>" />
			<input type="hidden" name="idListGift" value="<%= listgift.getIdListGift()%>" />
		<button type="submit" name="submit" id="submit"
			class="btn btn-primary">Payer</button>
	</form>

	<div class="mt-1">
		<form action="invitationconsultgift" method="get">
			<input type="hidden" name="idGift" value="<%=gift.getIdGift()%>" />
			<input type="hidden" name="idListGift" value="<%= listgift.getIdListGift()%>" />
			<td><button type="submit" class="btn btn-primary btn-sm">Retour</button></td>
		</form>
	</div>
</body>
</html>