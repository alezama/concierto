<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		<div class="contact_form">
		<ul>
		<li><h1>Compras por Cliente</h1></li>
		<form method="POST" action="processClienteBusqueda">
		<li><label for="cliente">Clientes: </label>
			<select name="cliente" id="cliente">
				<option value="">-- SELECCIONE --</option>
				<c:forEach items="${clientesList}" var="cliente">
					<option value="${cliente.idCliente}">${cliente.nombre}</option>
				</c:forEach>
			</select>
		</li>
			<c:if test="${not empty errorMessage}">
					<li class="errorMessage"><c:out value="${errorMessage}"></c:out></li>
			</c:if>
			<button class="submit" type="submit">Buscar</button>
		</ul>
		</form>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>