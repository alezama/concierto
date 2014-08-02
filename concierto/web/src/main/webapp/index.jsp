<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
 
<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">
	<h1>Bienvenido</h1>
	<p>Este sitio es un proyecto final para el curso de Desarrollo Web con Spring 
	impartido en la Escuela Superior de Computo del IPN.</p>
	<p>Las tecnologías usadas en este sitio icluyen</p>
	<ul>
		<li>Spring MVC</li>
		<li>Maven</li>
		<li>Apache Tiles</li>
	</ul>
</tiles:putAttribute>
</tiles:insertDefinition>