<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<jsp:include page="nav.jsp" />

	<h1>Connexion</h1>

	<form action="bibliotheque?elem=connexion" method="post">
		<input name="form" value="connexion" type="hidden" /> <label>Login
			:</label><input type="text" name="login" /> <label>Mot de passe :</label><input
			type="password" name="password" /> <input type="submit"
			value="connexion" />
	</form>
</body>
</html>