<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Nueva Banda</title>
</head>
<body>
	<h1>Nueva Banda</h1>
	<form:form method="POST" commandName="bandaForm" action="registroBandaResult">
		<form:label path="nombre">Nombre</form:label>
		<form:input path="nombre" required="true" type="text"/>
		<form:label path="ranking">Ranking</form:label>
		<form:input path="ranking" required="true" type="number" min="1" max="3" step="1"/>
		<label for="genero">Genero</label>
		<select name="generoOption" id="genero">
			<option value="NONE" label="--Seleccionar---"></option>
			<c:forEach items="${generoList}" var="genero">
				<option value="${genero.idGenero}" label="${genero.descripcion}"></option>
			</c:forEach> 
		</select>
		<input type="submit" value="Registrar">
	</form:form>
</body>
</html>