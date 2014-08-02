<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
 
<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">
	<span class="contact_form">
		<ul>
			<li><h1>Nuevo Concierto</h1></li>
			<form:form method="POST" commandName="conciertoForm"
				action="registroConciertoResult">
				<li><label for="banda">Banda</label> <select name="bandaOption"
					id="banda">
						<option value="" label="--Seleccionar---"></option>
						<c:forEach items="${bandaList}" var="banda">
							<option value="${banda.idBanda}" label="${banda.nombre}"></option>
						</c:forEach>
				</select></li>
				<li><form:label path="fecha">Fecha</form:label> <form:input
						path="fecha" type="date" value="${todaysDate}"/></li>
				<li><label for="lugar">Lugar</label> <select name="lugarOption"
					id="lugar">
						<option value="" label="--Seleccionar---"></option>
						<c:forEach items="${lugarList}" var="lugar">
							<option value="${lugar.idLugar}" label="${lugar.nombre}"></option>
						</c:forEach>
				</select></li>
				<c:if test="${not empty errorMessage}">
					<li class="errorMessage"><c:out value="${errorMessage}"></c:out></li>
				</c:if>
				<li><button class="submit" type="submit">Registrar</button></li>
			</form:form>
		</ul>
	</span>
</tiles:putAttribute>
</tiles:insertDefinition>