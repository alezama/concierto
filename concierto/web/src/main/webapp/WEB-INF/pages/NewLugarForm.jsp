<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
 
<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">
<div class="contact_form">
	<ul>
	<li><h1>Nuevo Lugar</h1></li>
	<form:form method="POST" commandName="lugarForm" action="registroLugarResult">	
				<li><form:label path="nombre">Nombre</form:label>
				<form:input path="nombre" type="text" required="true"/></li>
			
				<li><form:label path="direccion">Dirección</form:label>
				<form:input path="direccion" type="text" required="true"/></li>
				
				<li><form:label path="capacidad">Capacidad</form:label>
				<form:input path="capacidad" type="number" min="101" step="1" max="1000000" value="101" required="true"/></li> 
		
				<li><form:label path="restriccionEdad">Restricción Edad</form:label></li>
				<li><ul id="radioButtons">
					<li><form:radiobutton path="restriccionEdad" value="true" label="SÍ" checked="true"/></li>
					<li><form:radiobutton path="restriccionEdad" value="false" label="NO"/></li>
				</ul></li>
				
				<li><button class="submit" type="submit">Registrar</button></li>
	</form:form>
	</ul>
</div>
</tiles:putAttribute>
</tiles:insertDefinition>
