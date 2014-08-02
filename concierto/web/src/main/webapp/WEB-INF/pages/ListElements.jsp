<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
 
<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">
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
</tiles:putAttribute>
</tiles:insertDefinition>