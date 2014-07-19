<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Nuevo Genero</title>
</head>
<body>
	<h1>Nuevo Genero</h1>
	<form:form method="POST" commandName="generoForm" action="registroGeneroResult">
		<form:label path="descripcion">Descripción:</form:label>
		<form:input tye="text"  path="descripcion" required="true"/>
		<br/>
		<input type="submit" value="Registrar">
	</form:form>	
</body>
</html>