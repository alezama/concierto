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
<title>Nuevo Lugar</title>
</head>
<body>
<c:import var="myData" url="/inserts/nav.html"/>
	<c:out value="${myData}" escapeXml="false"/>
<span class="contact_form">
	<ul>
	<li><h1>Nuevo Lugar</h1></li>
	<form:form method="POST" commandName="lugarForm" action="registroLugarResult">	
				<li><form:label path="nombre">Nombre</form:label>
				<form:input path="nombre" type="text" required="true"/></li>
			
				<li><form:label path="direccion">Dirección</form:label>
				<form:input path="direccion" type="text" required="true"/></li>
				
				<li><form:label path="capacidad">Capacidad</form:label>
				<form:input path="capacidad" type="number" min="1" step="100" max="1000000" value="0" required="true"/></li> 
		
				<li><form:label path="restriccionEdad">Restricción Edad</form:label></li>
				<li><ul id="radioButtons">
					<li><form:radiobutton path="restriccionEdad" value="true" label="SÍ" checked="true"/></li>
					<li><form:radiobutton path="restriccionEdad" value="false" label="NO"/></li>
				</ul></li>
				
				<li><button class="submit" type="submit">Registrar</button></li>
	</form:form>
	</ul>
	</span>
</body>
</html>