<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Nueva Compra</title>
</head>
<body>
	<h1>Nuevo Cliente</h1>
	<form:form method="POST" commandName="clienteForm" action="registerCliente">
		<form:label path="nombre">Nombre</form:label>
		<form:input path="nombre"/>
		<form:label path="">Edad</form:label>
		<form:input path="edad" type="number" max="115" min="1"/>
		<input type="submit" value="Registrar">
	</form:form>
</body>
</html>