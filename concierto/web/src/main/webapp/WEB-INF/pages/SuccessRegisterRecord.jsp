<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
 
<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">
	<c:import var="myData" url="/inserts/nav.html"/>
	<c:out value="${myData}" escapeXml="false"/>
	<p>El registro ${objectId} se insertó con éxito</p>
</tiles:putAttribute>
</tiles:insertDefinition>