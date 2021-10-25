<%@ page isErrorPage="true" language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Error 403</title>
</head>
<body>
	<h1>Error 403</h1>
	<a
		href="${pageContext.request.contextPath}/controller?command=to_home_page_command&page=1">
		Back to home page</a>
</body>
</html>