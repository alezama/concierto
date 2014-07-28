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
<link href="${pageContext.request.contextPath}/css/indexPage.css"
	rel="stylesheet" type="text/css">
<title>Nuevo Genero</title>
</head>
<body>
<c:import var="myData" url="/inserts/nav.html"/>
	<c:out value="${myData}" escapeXml="false"/>
	<span class="contact_form">
		<ul>
			<li><h1>Nuevo Genero</h1></li>
			<form:form method="POST" commandName="generoForm"
				action="registroGeneroResult">
				<li><form:label path="descripcion">Descripción:</form:label>
				<form:input tye="text" path="descripcion" required="true" /></li>
				<br />
				<li><button class="submit" type="submit">Registrar</button></li>
			</form:form>
		</ul>
	</span>
</body>
</html>