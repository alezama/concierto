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
			<li>Cliente: <c:out value="${sessionScope.nombreCliente}" />
			</li>
			<form:form method="POST" action="processCompraFirstSearch"
				commandName="formCompra">

				<c:if test="${not empty listLugares}">
					<li><form:label path="idLugar">Lista de Lugares</form:label> <form:select
							path="idLugar" id="lugarSelect">
							<c:forEach items="${listLugares}" var="lugar">
								<form:option value="${lugar.idLugar}" label="${lugar.nombre}"></form:option>
							</c:forEach>
						</form:select></li>
				</c:if>
				<c:if test="${not empty listBandas}">
					<li><form:label path="idBanda">Lista de Bandas</form:label> <form:select
							path="idBanda" id="bandaSelect">
							<c:forEach items="${listBandas}" var="banda">
								<form:option value="${banda.idBanda}" label="${banda.nombre}"></form:option>
							</c:forEach>
						</form:select></li>
				</c:if>
				<c:if test="${not empty errorMessage}">
					<li class="errorMessage"><c:out value="${errorMessage}"></c:out></li>
				</c:if>
				<li><button class="submit" type="submit">Buscar</button></li>
			</form:form>
		</ul>
	</span>
</tiles:putAttribute>
</tiles:insertDefinition>