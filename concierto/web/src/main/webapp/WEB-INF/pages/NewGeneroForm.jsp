<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
 
<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">
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
</tiles:putAttribute>
</tiles:insertDefinition>