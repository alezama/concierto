<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="${pageContext.request.contextPath}/css/formStyle.css"
	rel="stylesheet" type="text/css">
<title>Nueva Compra</title>
</head>
<body>
	<span class="contact_form">
		<ul>
			<li><h1>Nuevo Cliente</h1>
		</li> <form:form method="POST" commandName="clienteForm"
			action="registerCliente">
			<li><form:label path="nombre">Nombre</form:label> <form:input
					path="nombre" required="required" placeholder="Nombre completo" /></li>
			<li><form:label path="">Edad</form:label> <form:input
					path="edad" type="number" value="1" max="115" min="1" /></li>
			<li><button class="submit" type="submit">Registrar</button></li>
		</form:form>
		</ul>
		</span>
</body>
</html>