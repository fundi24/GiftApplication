<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@page import="java.util.ArrayList"%>
<%!ArrayList<String> errors = new ArrayList<>();%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ajouter cadeau</title>
</head>
<body>
	<%@ include file="Header.jsp"%>
	<form action="creategift" method="POST" enctype="multipart/form-data">
		<%
		errors = (ArrayList<String>) request.getAttribute("errors");
		
		
		%>
		<div class="mb-3">
			<label for="name" class="form-label">Nom</label>
			 <input type="text"class="form-control" name="name" id="name">
			<div class="form-text"><%=errors.get(0)%></div>
		</div>
		<div class="mb-3">
			<label for="description" class="form-label">Description</label> 
			<input type="text" class="form-control" name="description" id="description">
			<div class="form-text"><%=errors.get(1)%></div>
		</div>
		<div class="mb-3">
			<label for="price" class="form-label">Prix</label> <input
				type="number" class="form-control" name="price" id="price" step="0.01">
			<div class="form-text"><%=errors.get(2)%></div>
		</div>
		<div class="mb-3">
  			<label for="picture" class="form-label">Image(Optionnelle)</label>
  			<input class="form-control" type="file" name="picture" id="picture" accept=".jpg, .jpeg, .png">
  			<div class="form-text"><%=errors.get(3)%></div>
		</div>
		<div class="mb-3">
			<label for="linkToWebsite" class="form-label">Lien vers le site(Optionnelle)</label> 
			<input type="text" class="form-control" name="linkToWebsite" id="linkToWebsite">
		</div>
		<div>
			<input type="hidden" name="idListGift" value="<%= request.getAttribute("idListGift")%>" />
		</div> 
		<button type="submit" name="submit" id="submit"
			class="btn btn-primary">Envoyer</button>

	</form>
	<%
	if (request.getAttribute("createGiftSuccess") != null) {
	%>
	<div class="alert alert-success">
		<p><%=request.getAttribute("createGiftSuccess")%></p>
	</div>
	<%
	}
	%>
	<%
	if (request.getAttribute("createGiftError") != null) {
	%>
	<div class="alert alert-danger">
		<p><%=request.getAttribute("createGiftError")%></p>
	</div>
	<%
	}
	%>
	<div class="mt-1">
		<form action="consultlistgift" method="get"> 
			<input type="hidden" name="idListGift" 
				value="<%= request.getAttribute("idListGift")%>" />
			<td><button type="submit" class = "btn btn-primary btn-sm">Retour</button></td>
		</form>
	</div>
	

</body>
</html>