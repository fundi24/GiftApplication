<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%!ArrayList<String> errors = new ArrayList<>();%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Créer une liste</title>
</head>
<body>
    <%@ include file="Header.jsp"%>
    <form action="createlistgift" method="POST">
        <%
            errors = (ArrayList<String>) request.getAttribute("errors");
        %>
        <div class="mb-3">
            <label for="name" class="form-label">Nom</label>
             <input type="text" class="form-control" name="name" id="name">
            <div class="form-text"><%=errors.get(0)%></div>
        </div>
        <div class="mb-3">
            <label for="deadline" class="form-label">Date limite (optionnelle)</label> 
            <input type="date" class="form-control" name="deadline" id="deadline">
            <div class="form-text"><%=errors.get(1)%></div>
        </div>
        <div class="mb-3">
            <label for="theme" class="form-label">Theme</label> 
            <input type="text" class="form-control" name="theme" id="theme">
            <div class="form-text"><%=errors.get(2)%></div>
        </div>
        <button type="submit" name="submit" id="submit" class="btn btn-primary">Submit</button>

    </form>
	<%
        if (request.getAttribute("createListGiftSuccess") != null) {%>
        <div class="alert alert-success">
       <p><%= request.getAttribute("createListGiftSuccess")%></p> 
       </div>
    <% 
        }
    %>
    <%
        if (request.getAttribute("createListGiftError") != null) {%>
        <div class="alert alert-danger">
       <p><%= request.getAttribute("createListGiftError")%></p> 
       </div>
        <% 
        }
    %>
    <a href="mygiftlists" class="btn btn-primary btn-sm active">Retour</a>

</body>
</html>