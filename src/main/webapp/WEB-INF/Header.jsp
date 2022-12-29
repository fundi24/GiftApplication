
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="true"%>
<%@page import="be.giftapplication.javabeans.Customer"%>
<jsp:useBean id="customer" class="be.giftapplication.javabeans.Customer"
	scope="session"></jsp:useBean>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<div class="container-fluid">
			<a class="navbar-brand">WishList</a>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<%
					if (customer.getIdCustomer() != 0) {
					%>
					<li class="nav-item"><a class="nav-link" href="mygiftlists">Mes
							listes</a></li>
					<li class="nav-item"><a class="nav-link" href="mynotifications">Mes
							notifications</a></li>
<%-- 					<li class="nav-item">
						<form action="mynotifications" method="get">
							<input type="hidden" name="idCustomer"
								value="<%=customer.getIdCustomer()%>" />
							<td><button type="submit" class="btn btn-link nav-link">Mes
									notifications</button></td>
						</form>
					</li> --%>
					<li class="nav-item"><a class="nav-link" href="home">Déconnexion</a></li>
					<%
					}
					%>

				</ul>
				<!-- <form class="d-flex">
                    <input class="form-control me-2" type="search" placeholder="Search"
                        aria-label="Search">
                    <button class="btn btn-outline-success" type="submit">Search</button>
                </form> -->
			</div>
		</div>
	</nav>

</body>
</html>