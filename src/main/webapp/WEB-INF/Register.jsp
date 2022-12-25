<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%!ArrayList<String> errors = new ArrayList<>();%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Inscription</title>
</head>
<body>
    <%@ include file="Header.jsp"%>
    <form action="register" method="POST">
        <%
            errors = (ArrayList<String>) request.getAttribute("errors");
        %>
        <div class="mb-3">
            <label for="lastName" class="form-label">Nom</label>
             <input type="text" class="form-control" name="lastName" id="lastName" required>
            <div class="form-text"><%=errors.get(0)%></div>
        </div>
        <div class="mb-3">
            <label for="firstName" class="form-label">Prénom</label> 
            <input type="text" class="form-control" name="firstName" id="firstName" required>
            <div class="form-text"><%=errors.get(1)%></div>
        </div>
               <div class="mb-3">
            <label for="dateOfBirth" class="form-label">Date de naissance</label>
             <input type="date" class="form-control" name="dateOfBirth" id="dateOfBirth" required>
            <div class="form-text"><%=errors.get(2)%></div>
        </div>
        <div class="mb-3">
            <label for="username" class="form-label">Nom d'utilisateur</label> 
            <input type="text" class="form-control" name="username" id="username" required>
            <div class="form-text"><%=errors.get(3)%></div>
        </div>
               <div class="mb-3">
            <label for="password1" class="form-label">Mot de passe</label>
             <input type="password" class="form-control" name="password1" id="password1" required>
            <div class="form-text"><%=errors.get(4)%></div>
        </div>
        <div class="mb-3">
            <label for="password2" class="form-label">Confirmer le mot de passe</label> 
            <input type="password" class="form-control" name="password2" id="password2" required>
            <div class="form-text"><%=errors.get(5) %> </div>
            <div class="form-text"><%=errors.get(6) %> </div>
        </div>
        <button type="submit" name="submit" id="submit" class="btn btn-primary">Submit</button>

    </form>
	<%
        if (request.getAttribute("registerSuccess") != null) {%>
        <div class="alert alert-success">
       <p><%= request.getAttribute("registerSuccess")%></p> 
       </div>
    <% 
        }
    %>
    <a href="home" class="btn btn-primary btn-sm active">Retour</a>

</body>
</html>