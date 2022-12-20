<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Inscription</title>
</head>
<body>
	<h1>Formulaire d'inscription</h1>
	<form action="register" method="POST">
		<table border="1" cellspacing="0" cellpadding="5">
			<tr>
				<td>Nom :</td>
				<td><input type="text" name="lastName" id="lastName" value=""
					size="20" /></td>
			</tr>
			<tr>
				<td>Prénom :</td>
				<td><input type="text" name="firstName" id="firstName" value=""
					size="20" /></td>
			</tr>
			<tr>
				<td>Date de naissance :</td>
				<td><input type="date" name="dateOfBirth" id="dateOfBirth" value=""
					size="20" /></td>
			</tr>
			<tr>
				<td>Nom d'utilisateur :</td>
				<td><input type="text" name="username" id="username" value=""
					size="20" /></td>
			</tr>
			<tr>
				<td>Mot de passe :</td>
				<td><input type="password" name="password1" id="password1" value=""
					size="20" /></td>
			</tr>
						<tr>
				<td>Confirmer le mot de passe :</td>
				<td><input type="password" name="password2" id="password2" value=""
					size="20" /></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit"
					name="submit" id="submit" value="Valider" /></td>
			</tr>
			<tr>
				<td colspan="2" align="center"> <a href="home">Page d'accueil</a> </td>
			</tr>
		</table>
	</form>
</body>
</html>