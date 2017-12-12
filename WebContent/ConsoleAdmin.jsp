<%@page import="cleal.projet.Livre"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%
	String login = null;
	if (session.getAttribute("login") == null) {
		response.sendRedirect("/Project/bibliotheque?elem=connexion");
	} else if ((boolean) (session.getAttribute("admin")) != true) {
		response.sendRedirect("/Project/bibliotheque?elem=connexion");
	} else {
		login = (String) session.getAttribute("login");
	}

	ArrayList<Livre> resultsRecherche = (ArrayList<Livre>) request.getAttribute("console-results");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Console admin</title>
</head>
<body>

	<jsp:include page="nav.jsp"/>

	<h1>Console admin:</h1>

	<h2>Ajouter un livre:</h2>

	<form action="bibliotheque?elem=consoleAdmin" method="post">
		<input name="form" value="console_add" type="hidden" /> <label
			for="auteur">Auteur :</label><input type="text" name="auteur">
		<label for="titre">Titre :</label><input type="text" name="titre">
		<label for="nombre">Nombre :</label><input type="number" name="nombre"
			min="1">
		<button type="submit">Ajouter</button>

	</form>

	<h2>Rechercher un livre à modifier :</h2>

	<form action="bibliotheque?elem=consoleAdmin" method="POST">
		<input name="form" value="console_recherche" type="hidden" /> <label
			for="auteur">Auteur :</label><input type="text" name="auteur"
			value="<%=request.getParameter("auteur") == null ? "" : request.getParameter("auteur")%>">
		<label for="titre">Titre :</label><input type="text" name="titre"
			value="<%=request.getParameter("titre") == null ? "" : request.getParameter("titre")%>">
		<button type="submit">Rechercher</button>
	</form>

	<ul class="list-results">

		<%
			if (resultsRecherche != null) {
				for (Livre livreResult : resultsRecherche) {
		%>
		<li class="result">

			<div class="auteur">
				Auteur :
				<%=livreResult.getAuteur()%></div>
			<div class="titre">
				Titre :
				<%=livreResult.getTitre()%></div>
			<div class="nbrTotal">
				Nombre d'exemplaires total :
				<%=livreResult.getNbrTotal()%></div>
			<div class="nbrEmprunte">
				Nombre d'exemplaires empruntés :
				<%=livreResult.getNbrEmprunte()%></div>

			<form action="bibliotheque?elem=consoleAdmin" method="post">
				<input name="form" value="console_delete" type="hidden"> <input
					name="livre" value="<%=livreResult.getId()%>" type="hidden">
				<button type="submit">Supprimer</button>
			</form>

			<form action="bibliotheque?elem=consoleAdmin" method="post">
				<input name="form" value="console_changeNumber" type="hidden">
				<input name="livre" value="<%=livreResult.getId()%>" type="hidden">
				<label for="newNombre">Nombre d'exemplaires à ajouter et enlever (positif=ajouter, négatif=enlever):</label> <input
					type="number" name="newNombre"
					value="<%=livreResult.getNbrTotal() - livreResult.getNbrEmprunte()%>">
				<button type="submit">Mettre à jour</button>
			</form>
		</li>

		<%
			}
			}
		%>
	</ul>
</body>
</html>