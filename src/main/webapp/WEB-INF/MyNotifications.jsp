<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>

<%@page import="be.giftapplication.javabeans.Notification"%>
<%!ArrayList<Notification> notifications;%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mes notifications</title>
</head>
<body>
	<%@ include file="Header.jsp"%>
	<% notifications = (ArrayList<Notification>)request.getAttribute("notifications"); %>
	
	<table class="table">
	<tr>
		<th>Message</th>
		<th>Lu</th>
		<th>Marqué comme lu</th>
	</tr>
	<% for(Notification n : notifications){ %>
	
	<tr>
		<td><%= n.getMessage() %></td>
		<%if(n.isRead() == true){ %>
			<td>lu</td>
		<%} else{ %>
			<td>pas lu</td>
		<%} %>
		<%-- <form action="consultlistgift" method="get"> 
			<input type="hidden" name="idListGift" 
				value="<%= l.getIdListGift() %>" />
			<td><button type="submit" class = "btn btn-primary btn-sm">Consulter</button></td>
		</form> --%>
	</tr>
	<%} %>
	</table>
</body>
</html>