<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="${pageContext.request.contextPath}/css/indexPage.css"
	rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/css/tableStyle.css"
	rel="stylesheet" type="text/css">

<title>Lista Elementos</title>
</head>
<body>
	<c:import var="myData" url="/inserts/nav.html"/>
	<c:out value="${myData}" escapeXml="false"/>
	<div class="page">
	<h1 id="tablaTitle">${tableTitle}</h1>
		<table>
			<thead>
				<tr>
					<c:forEach items="${tableHeaders}" var="headerEl">
						<th>${headerEl}</th>
					</c:forEach>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${dataList}" var="listItem">
					<tr>
						<c:forEach items="${listItem}" var="item">
							<td>${item}</td>
						</c:forEach>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>