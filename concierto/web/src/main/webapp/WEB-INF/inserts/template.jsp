<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html>
    <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="${pageContext.request.contextPath}/css/indexPage.css"
	rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/css/tableStyle.css"
	rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/css/formStyle.css"
	rel="stylesheet" type="text/css">
    <title>Default tiles template</title>
<body>
    <div class="page">
        <tiles:insertAttribute name="header" />
        <tiles:insertAttribute name="menu" />
        <div class="content">
            <tiles:insertAttribute name="body" />
        </div>
        <tiles:insertAttribute name="footer" />
    </div>
</body>
</html>