<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%!ArrayList<String> errors = new ArrayList<>();%>
<%!String usernameSave= ""; %>
<%!String passwordSave= ""; %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Accueil</title>
</head>
<body>
     <%@ include file="Header.jsp"%>
     <h2>Connexion</h2>
    <form action="home" method="POST">
        <%
            errors = (ArrayList<String>) request.getAttribute("errors");
        	if(request.getAttribute("usernameSave") != null){
        		usernameSave = (String) request.getAttribute("usernameSave");
        	}
        	if(request.getAttribute("passwordSave") !=null){
        		passwordSave = (String) request.getAttribute("passwordSave");
        	}
        	
        %>
        <div class="mb-3">
            <label for="username" class="form-label">Nom d'utilisateur</label>
             <input type="text" class="form-control" name="username" id="username" value="<%= usernameSave %>">
            <div class="form-text"><%=errors.get(0)%></div>
        </div>
        <div class="mb-3">
            <label for="password" class="form-label">Mot de passe</label> 
            <input type="password" class="form-control" name="password" id="password" value="<%= passwordSave %>">
            <div class="form-text"><%=errors.get(1)%></div>
        </div>
        <button type="submit" name="submit" id="submit" class="btn btn-primary">Envoyer</button>
    </form>
    <%
        if (request.getAttribute("loginError") != null) {%>
        <div class="alert alert-danger">
       <p><%= request.getAttribute("loginError") %></p> 
       </div>
    <% 
        }
    %>
    <div class="mt-2">
    	<a href="register" class="btn btn-primary">Inscription</a>
    </div>
    
	
</body>
</html>