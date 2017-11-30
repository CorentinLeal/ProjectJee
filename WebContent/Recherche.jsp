<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="cleal.projet.Livre"%>

<%
	String uid = null;
	if (session.getAttribute("uid") != null) {
		uid = session.getAttribute("uid").toString();
	}

	ArrayList<Livre> results = (ArrayList<Livre>) request.getAttribute("results");
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Recherche</title>
</head>
<body>
	<h1>Recherche</h1>
	<form action="recherche" method="get">
		<label for="auteur">Auteur :</label><input type="text" name="auteur"
			value="<%=request.getParameter("auteur") == null ? "" : request.getParameter("auteur")%>">
		<label for="titre">Titre :</label><input type="text" name="titre"
			value="<%=request.getParameter("titre") == null ? "" : request.getParameter("titre")%>">
		<button type="submit">Rechercher</button>
	</form>


	<h1>Résultats :</h1>
	<ul class="list-results">

		<%
			if (results != null) {
				for (Livre livreResult : results) {
		%>
		<li class="result">

			<div class="auteur"><%=livreResult.getAuteur()%></div>
			<div class="titre"><%=livreResult.getTitre()%></div>
			<div class="nbrTotal"><%=livreResult.getNbrTotal()%></div>
			<div class="nbrEmprunte"><%=livreResult.getNbrEmprunte()%></div>

		</li>

		<%
			}
				}
		%>
	</ul>
</body>
</html>