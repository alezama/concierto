<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Nuevo Concierto</title>
</head>
<body>
	<h1>Nuevo Concierto</h1>
	<form:form method="GET" commandName="conciertoForm" action="registroConciertoResult">
		<label for="banda">Banda</label>
		<select name="bandaOption" id="banda">
			<option value="NONE" label="--Seleccionar---"></option>
			<c:forEach items="${bandaList}" var="banda">
				<option value="${banda.idBanda}" label="${banda.nombre}"></option>
			</c:forEach> 
		</select>
		<form:label path="fecha">Fecha</form:label>
		<form:input path="fecha" type="date"/>
		<label for="lugar">Lugar</label>
		<select name="lugarOption" id="lugar">
			<option value="NONE" label="--Seleccionar---"></option>
			<c:forEach items="${lugarList}" var="lugar">
				<option value="${lugar.idLugar}" label="${lugar.nombre}"></option>
			</c:forEach> 
		</select>
		<input type="submit" value="Registrar">
	</form:form>
</body>
</html>