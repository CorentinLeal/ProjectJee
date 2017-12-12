<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="cleal.projet.Livre"%>

<%
	String login = null;
	if (session.getAttribute("login") != null) {
		login = session.getAttribute("login").toString();
	}

	ArrayList<Livre> results = (ArrayList<Livre>) request.getAttribute("recherche-results");
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Recherche</title>
</head>
<body>

	<jsp:include page="nav.jsp"/>

	<h1>Recherche</h1>
	<form action="bibliotheque?elem=recherche" method="POST">
		<input name="form" value="recherche" type="hidden" /> <label
			for="auteur">Auteur :</label><input type="text" name="auteur"
			value="<%=request.getParameter("auteur") == null ? "" : request.getParameter("auteur")%>">
		<label for="titre">Titre :</label><input type="text" name="titre"
			value="<%=request.getParameter("titre") == null ? "" : request.getParameter("titre")%>">
		<button type="submit">Rechercher</button>
	</form>


	<h1>Résultats :</h1>
	<ul>

		<%
			if (results != null) {
				for (Livre livreResult : results) {
		%>
		<li>

			<div>
				Auteur :
				<%=livreResult.getAuteur()%></div>
			<div>
				Titre :
				<%=livreResult.getTitre()%></div>
			<div>
				Nombre d'exemplaires total :
				<%=livreResult.getNbrTotal()%></div>
			<div>
				Nombre d'exemplaires empruntés :
				<%=livreResult.getNbrEmprunte()%></div> <%
 	if (livreResult.getNbrTotal() - livreResult.getNbrEmprunte() > 0
 					&& session.getAttribute("login") != null) {
 %>
			<form action="bibliotheque?elem=recherche" method="post">
				<input name="form" value="recherche_reserv" type="hidden"> <input
					name="livre" value="<%=livreResult.getId()%>" type="hidden">
				<button type="submit">Réserver</button>
			</form> <%
 	}
 %>
		</li>
		<%
			}
			}
		%>
	</ul>
</body>
</html>