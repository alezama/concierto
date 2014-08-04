<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">
	<span class="contact_form">
		<ul>
			<li><h1>Realiza tu Compra</h1></li>
			<li>Cliente <c:out value="${sessionScope.nombreCliente}" />
			</li>
			<c:if test="${not empty sessionScope.nombreBanda}">
				<li>Conciertos para la banda <c:out
						value="${sessionScope.nombreBanda}" /></li>
			</c:if>
			<c:if test="${not empty sessionScope.nombreLugar}">
				<li>Conciertos para el lugar <c:out
						value="${sessionScope.nombreLugar}" /></li>
			</c:if>
			<form:form method="POST" action="processCompraFinish"
				commandName="formCompra">
				<li><form:label path="idConcierto">Listas Conciertos </form:label> </li>
					<li><ul class="listConcert">
						<c:forEach items="${listaConciertos}" var="concierto">
							<c:if test="${not empty sessionScope.nombreBanda}">
							<li><form:radiobutton path="idConcierto"
									value="${concierto.idConcierto}" />${concierto.lugar.nombre} el <fmt:formatDate type="both" pattern="EEEE, yyyy/MM/dd" value="${concierto.fecha}"/> <br></li>
							</c:if>
							<c:if test="${not empty sessionScope.nombreLugar}">
							<li><form:radiobutton path="idConcierto"
									value="${concierto.idConcierto}" />${concierto.banda.nombre} el <fmt:formatDate type="both" pattern="EEEE, yyyy/MM/dd" value="${concierto.fecha}"/> <br></li>
							</c:if>
						</c:forEach>
					</ul></li>
				<li><form:label path="numeroBoletos">Número de Boletos</form:label>
					<form:input type="number" max="5" min="1" value="1"
						path="numeroBoletos" id="numeroBoletos" /></li>
				<c:if test="${not empty errorMessage}">
					<li class="errorMessage"><c:out value="${errorMessage}"></c:out></li>
			</c:if>
				<li><button class="submit" type="submit">Comprar</button></li>
			</form:form>
		</ul>
	</span>
</tiles:putAttribute>
</tiles:insertDefinition>