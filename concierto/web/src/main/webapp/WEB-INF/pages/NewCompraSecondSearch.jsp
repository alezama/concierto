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
<title>Nueva Compra</title>
</head>
<body>
	<span class="contact_form">
		<ul>
			<li><h1>Realiza tu Compra</h1></li>
			<li>Cliente <c:out value="${sessionScope.nombreCliente}" />
			</li>
			<c:if test="${not empty sessionScope.nombreBanda}">
				<li>Conciertos para la banda <c:out
						value="${sessionScope.nombreBanda}" />
			</c:if>
			<c:if test="${not empty sessionScope.nombreLugar}">
				<li>Conciertos para el lugar <c:out
						value="${sessionScope.nombreLugar}" />
			</c:if>
			<form:form method="POST" action="processCompraFinish"
				commandName="formCompra">
				<li><form:label path="idConcierto">Listas Conciertos </form:label>
					<ul class="listConcert">
						<c:forEach items="${listaConciertos}" var="concierto">
							<li><form:radiobutton path="idConcierto"
									value="${concierto.idConcierto}" />${concierto.toString()}<br></li>
						</c:forEach>
					</ul></li>
				<li><form:label path="numeroBoletos">Número de Boletos</form:label>
					<form:input type="number" max="5" min="1" value="1"
						path="numeroBoletos" id="numeroBoletos" /></li>
				<li><input type="submit" value="Comprar"></li>
			</form:form>
		</ul>
	</span>
</body>
</html>