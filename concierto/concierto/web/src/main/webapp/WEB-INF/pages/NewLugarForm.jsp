<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Nuevo Lugar</title>
</head>
<body>
	<form:form method="POST" commandName="lugarForm" action="registroLugarResult">
		<table>
			<tr>
				<td>Nombre:</td>
				<td><form:input path="nombre" type="text" required="true" /></td>
			</tr>
			<tr>
				<td>Dirección:</td>
				<td><form:input path="direccion" type="text" required="true"/></td>
			</tr>
			<tr>
				<td>Capacidad</td>
				<td><form:input path="capacidad" type="number" min="1" max="1000000" required="true"/> </td>
			</tr>
			<tr>
				<td>Restricción Edad</td>
				<td> 
					<form:radiobutton path="restriccionEdad" value="true" label="SÍ" checked="true"/>
					<form:radiobutton path="restriccionEdad" value="false" label="NO"/>
				</td>
			</tr>
			<tr>
				<td><input type="submit" value="Registrar"></td>
			</tr>
		</table>
	</form:form>

</body>
</html>