<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
 
<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">
	<span class="contact_form">
		<ul>
			<li><h1>Realiza tu Compra</h1></li>
			<form:form method="POST" action="processCompraStart"
				commandName="formCompra">
				<li><form:label path="idCliente">Cliente</form:label> <form:select
						path="idCliente" id="cliente" readonly="readonly">
						<option value="" label="--Seleccionar---"></option>
						<c:forEach items="${clienteList}" var="cliente">
							<form:option value="${cliente.idCliente}"
								label="${cliente.nombre}">
							</form:option>
						</c:forEach>
					</form:select></li>
				<li><form:label path="seleccionCompra">Tipo de Búsqueda</form:label>
					<form:select path="seleccionCompra">
						<option value="" label="--Seleccionar---"></option>
						<option value="0">Lugar</option>
						<option value="1">Banda</option>
					</form:select></li>
				<c:if test="${not empty errorMessage}">
					<li class="errorMessage"><c:out value="${errorMessage}"></c:out></li>
				</c:if>
				<li><button class="submit" type="submit">Buscar</button></li>
			</form:form>
		</ul>
	</span>
</tiles:putAttribute>
</tiles:insertDefinition>