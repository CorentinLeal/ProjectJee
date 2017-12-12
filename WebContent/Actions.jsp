<%@page import="cleal.projet.Action"%>
<%@page import="cleal.projet.Utilisateur"%>
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

	ArrayList<Utilisateur> utilisateurs = (ArrayList<Utilisateur>) request.getAttribute("actions-utilisateurs");
	ArrayList<Action> emprunts = (ArrayList<Action>) request.getAttribute("actions-emprunts");
	ArrayList<Action> reserv = (ArrayList<Action>) request.getAttribute("actions-reserv");
	ArrayList<Livre> resultsRecherche = (ArrayList<Livre>) request.getAttribute("actions-results");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<jsp:include page="nav.jsp"/>

	<h1>Administration:</h1>

	<h2>Sélectionner un utilisateur:</h2>


	<form action="bibliotheque?elem=actions" method="post">
		<input name="form" value="actions_select" type="hidden"> <select
			name="utilisateur">
			<%
				if (utilisateurs != null) {
					for (Utilisateur utilisateur : utilisateurs) {
			%>
			<option
				<%=utilisateur.getLogin().equals(request.getAttribute("selectedUser")) ? "selected" : ""%>
				value="<%=utilisateur.getLogin()%>"><%=utilisateur.getLogin()%></option>
			<%
				}
				}
			%>

		</select>
		<button type="submit">Sélectionner</button>
	</form>
	<%
		if (request.getParameter("utilisateur") != null) {
	%>

	<h2>Emprunts et réservations :</h2>
	<ul>
		<%
			if (emprunts != null) {
		%>
		<h3>Emprunts</h3>
		<%
			for (Action item : emprunts) {
						Livre livre = item.getLivre();
		%>
		<li>
			<div>
				Auteur :<%=livre.getAuteur()%></div>
			<div>
				Titre :<%=livre.getTitre()%></div>
			<div><%=item.getId()%></div>
			<form action="bibliotheque?elem=actions" method="post">
				<input name="form" value="actions_fin_emprunt" type="hidden">
				<input name="emprunt" value="<%=item.getId()%>" type="hidden">
				<input name="utilisateur"
					value="<%=request.getParameter("utilisateur")%>" type="hidden">

				<button type="submit">Rendre</button>
			</form>
		</li>
		<%
			}
				}
		%>
		<%
			if (reserv != null) {
		%>
		<h3>Réservations</h3>
		<%
			for (Action item : reserv) {
						Livre livre = item.getLivre();
		%>
		<li>
			<div>
				Auteur :<%=livre.getAuteur()%></div>
			<div>
				Titre :<%=livre.getTitre()%></div>
			<div><%=item.getId()%></div>
			<form action="bibliotheque?elem=actions" method="post">
				<input name="form" value="actions_annulerReserv" type="hidden"> <input
					name="emprunt" value="<%=item.getId()%>" type="hidden"> <input
					name="utilisateur" value="<%=request.getParameter("utilisateur")%>"
					type="hidden">

				<button type="submit">Emprunter</button>
			</form>
		</li>
		<%
			}
				}
		%>
	</ul>

	<h2>Créer un nouvel emprunt :</h2>
	<form action="bibliotheque?elem=actions" method="POST">
		<input name="form" value="actions_recherche" type="hidden" />
		<input name="utilisateur" value="<%=request.getParameter("utilisateur")%>" type="hidden">
		<label for="auteur">Auteur :</label>
		<input type="text" name="auteur" value="<%=request.getParameter("auteur") == null ? "" : request.getParameter("auteur")%>">
		<label for="titre">Titre :</label>
		<input type="text" name="titre"	value="<%=request.getParameter("titre") == null ? "" : request.getParameter("titre")%>">
		<button type="submit">Rechercher</button>
	</form>

	<ul>

		<%
			if (resultsRecherche != null) {
					for (Livre livreResult : resultsRecherche) {
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

			<form action="bibliotheque?elem=actions" method="post">
				<input name="form" value="actions_emprunter" type="hidden">
				<input name="utilisateur" value="<%=request.getParameter("utilisateur")%>" type="hidden">
				<input name="livre" value="<%=livreResult.getId()%>" type="hidden">
				<button type="submit">Emprunter</button>
			</form> <%
 	}
 %>
		</li>
		<%
			}
				}
		%>
	</ul>
	<%
		}
	%>
</body>
</html>