<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
</head>
<body>
 <% if(request.getAttribute("registerSuccess") != null){
 		%><p>Inscription reussite !</p> <% 
 	}
 	%>
 <a href="register">Inscription</a>

</body>
</html>