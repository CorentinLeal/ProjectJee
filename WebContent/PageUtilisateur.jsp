<%@page import="cleal.projet.Livre"%>
<%@page import="cleal.projet.Action"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%
	String login = null;
	if (session.getAttribute("login") == null) {
		response.sendRedirect("/Project/bibliotheque?elem=connexion");
	} else {
		login = (String) session.getAttribute("login");
	}

	ArrayList<Action> emprunts = (ArrayList<Action>) request.getAttribute("emprunts");
	ArrayList<Action> reserv = (ArrayList<Action>) request.getAttribute("reserv");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<jsp:include page="nav.jsp"/>

	<h1>Mon compte :</h1>
	<p><%=session.getAttribute("nom")%></p>

	<h2>Réservations :</h2>
	<ul>
		<%
			if (reserv != null) {
				for (Action item : reserv) {
					Livre livre = item.getLivre();
		%>
		<li>
			<div>
				Auteur :<%=livre.getAuteur()%></div>
			<div>
				Titre :<%=livre.getTitre()%></div>
			<form action="bibliotheque?elem=pageUtilisateur" method="post">
				<input name="form" value="pageUtilisateur_annuler" type="hidden">
				<input name="reserv" value="<%=item.getId()%>" type="hidden">
				<button type="submit">Annuler</button>
			</form>
		</li>
		<%
			}
			}
		%>

	</ul>

	<h2>Emprunts :</h2>
	<ul>
		<%
			if (emprunts != null) {
				for (Action item : emprunts) {
					Livre livre = item.getLivre();
		%>
		<li>
			<div>
				Auteur :<%=livre.getAuteur()%></div>
			<div>
				Titre :<%=livre.getTitre()%></div>
		</li>
		<%
			}
			}
		%>

	</ul>
</body>
</html>