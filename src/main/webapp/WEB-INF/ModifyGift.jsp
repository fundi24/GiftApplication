<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
 <%! String errorPrice; %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modification du cadeau</title>
</head>
<body>
<%@ include file="Header.jsp"%>
	<form action="modifygift" method="POST" enctype="multipart/form-data">
		<%
		errorPrice = (String) request.getAttribute("errorPrice");
		
		
		%>
		<div class="mb-3">
			<label for="name" class="form-label">Nom(Optionelle)</label>
			 <input type="text"class="form-control" name="name" id="name">
		</div>
		<div class="mb-3">
			<label for="description" class="form-label">Description(Optionnelle)</label> 
			<input type="text" class="form-control" name="description" id="description">
		</div>
		<div class="mb-3">
			<label for="price"  class="form-label">Prix(Optionnelle)</label> <input
				type="number" class="form-control" name="price" id="price" step="0.01">
				<% if(errorPrice != null){ %>
			<div class="form-text"><%=errorPrice%></div>
			<%} %>
		</div>
		<div class="mb-3">
  			<label for="picture" class="form-label">Image(Optionnelle)</label>
  			<input class="form-control" type="file" name="picture" id="picture" accept=".jpg, .jpeg, .png">
		</div>
		<div class="mb-3">
			<label for="linkToWebsite" class="form-label">Lien vers le site(Optionnelle)</label> 
			<input type="text" class="form-control" name="linkToWebsite" id="linkToWebsite">
		</div>
		<div>
			<input type="hidden" name="idGift" value="<%= request.getAttribute("idGift")%>" />
		</div> 
		<button type="submit" name="submit" id="submit"
			class="btn btn-primary">Envoyer</button>

	</form>
	
	<%
	if (request.getAttribute("modifyGiftSuccess") != null) {
	%>
	<div class="alert alert-success">
		<p><%=request.getAttribute("modifyGiftSuccess")%></p>
	</div>
	<%
	}
	%>
	<%
	if (request.getAttribute("modifyGiftError") != null) {
	%>
	<div class="alert alert-danger">
		<p><%=request.getAttribute("modifyGiftError")%></p>
	</div>
	<%
	}
	%>
	<div class="mt-1">
		<form action="consultgift" method="get"> 
			<input type="hidden" name="idGift" 
				value="<%= request.getAttribute("idGift")%>" />
			<td><button type="submit" class = "btn btn-primary btn-sm">Retour</button></td>
		</form>
	</div>
	

</body>
</html>