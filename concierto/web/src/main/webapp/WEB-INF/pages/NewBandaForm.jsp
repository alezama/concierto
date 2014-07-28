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
<title>Nueva Banda</title>
</head>
<body>
<c:import var="myData" url="/inserts/nav.html"/>
	<c:out value="${myData}" escapeXml="false"/>
	<div class="contact_form">
		<ul>
			<li><h1>Nueva Banda</h1></li>
			<form:form method="POST" commandName="bandaForm"
				action="registroBandaResult">
				<li><form:label path="nombre">Nombre</form:label>
				<form:input path="nombre" required="true" type="text" /></li>
				<li><form:label path="ranking">Ranking</form:label>
				<form:input path="ranking" required="true" type="number" min="1"
					max="3" step="1" value="1" /></li>
				<li><label for="genero">Genero</label>
				<select name="generoOption" id="genero">
					<option value="" label="--Seleccionar---"></option>
					<c:forEach items="${generoList}" var="genero">
						<option value="${genero.idGenero}" label="${genero.descripcion}"></option>
					</c:forEach>
				</select></li>
				<c:if test="${not empty errorMessage}">
					<li class="errorMessage"><c:out value="${errorMessage}"></c:out></li>
				</c:if>
				<li><button class="submit" type="submit">Registrar</button></li>
			</form:form>
		</ul>
	</div>
</body>
</html>