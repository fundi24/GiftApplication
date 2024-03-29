<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%!ArrayList<String> errors = new ArrayList<>();%>
<%!String lastNameSave = ""; %>
<%!String firstNameSave = ""; %>
<%!String dateOfBirthSave = ""; %>
<%!String usernameSave = ""; %>
<%!String passwordSave1 = ""; %>
<%!String passwordSave2 = ""; %>
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
        	if(request.getAttribute("lastNameSave") != null){
				lastNameSave = (String) request.getAttribute("lastNameSave");
			}
       		 if(request.getAttribute("firstNameSave") != null){
       			firstNameSave = (String) request.getAttribute("firstNameSave");
    		}
    		if(request.getAttribute("dateOfBirthSave") != null){
    			dateOfBirthSave = (String) request.getAttribute("dateOfBirthSave");
    		}
        
	        if(request.getAttribute("usernameSave") != null){
	    		usernameSave = (String) request.getAttribute("usernameSave");
	    	}
	    	if(request.getAttribute("passwordSave1") != null){
	    		passwordSave1 = (String) request.getAttribute("passwordSave1");
	    	}
	    	if(request.getAttribute("passwordSave2") != null){
	    		passwordSave2 = (String) request.getAttribute("passwordSave2");
	    	}
        %>
        <div class="mb-3">
            <label for="lastName" class="form-label">Nom</label>
             <input type="text" class="form-control" name="lastName" id="lastName" value="<%= lastNameSave %>">
            <div class="form-text"><%=errors.get(0)%></div>
        </div>
        <div class="mb-3">
            <label for="firstName" class="form-label">Prénom</label> 
            <input type="text" class="form-control" name="firstName" id="firstName" value="<%= firstNameSave %>">
            <div class="form-text"><%=errors.get(1)%></div>
        </div>
               <div class="mb-3">
            <label for="dateOfBirth" class="form-label">Date de naissance</label>
             <input type="date" class="form-control" name="dateOfBirth" id="dateOfBirth" value="<%= dateOfBirthSave %>">
            <div class="form-text"><%=errors.get(2)%></div>
        </div>
        <div class="mb-3">
            <label for="username" class="form-label">Nom d'utilisateur</label> 
            <input type="text" class="form-control" name="username" id="username" value="<%= usernameSave %>">
            <div class="form-text"><%=errors.get(3)%></div>
        </div>
               <div class="mb-3">
            <label for="password1" class="form-label">Mot de passe</label>
             <input type="password" class="form-control" name="password1" id="password1" value="<%= passwordSave1 %>">
            <div class="form-text"><%=errors.get(4)%></div>
        </div>
        <div class="mb-3">
            <label for="password2" class="form-label">Confirmer le mot de passe</label> 

            <input type="password" class="form-control" name="password2" id="password2" value="<%= passwordSave2 %>">


            <div class="form-text"><%=errors.get(5) %> </div>
            <div class="form-text"><%=errors.get(6) %> </div>
        </div>
        <button type="submit" name="submit" id="submit" class="btn btn-primary">Envoyer</button>

    </form>
	<%
        if (request.getAttribute("registerSuccess") != null) {%>
        <div class="alert alert-success">
       <p><%= request.getAttribute("registerSuccess")%></p> 
       </div>
    <% 
        }
    %>
    	<%
        if (request.getAttribute("registerError") != null) {%>
        <div class="alert alert-danger">
       <p><%= request.getAttribute("registerError")%></p> 
       </div>
    <% 
        }
    %>
    <div class="mt-1">
    	<a href="home" class="btn btn-primary">Retour</a>
    </div>
    

</body>
</html>