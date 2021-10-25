<%@ page isErrorPage="true" language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Error 500</title>
</head>
<body>
	<h1>Error 500</h1>
	Request from ${pageContext.errorData.requestURI} is failed
	<br />
	<br /> Servlet name: ${pageContext.errorData.servletName}
	<br />
	<br /> Status code: ${pageContext.errorData.statusCode}
	<br />
	<br /> Exception: ${pageContext.exception}
	<br />
	<br /> Message from exception: ${pageContext.exception.message}
	<br />
	<br /> Stack trace:
	<br />
	<br />
	<br />
	<a
		href="${pageContext.request.contextPath}/controller?command=to_home_page_command&page=1">
		Back to home page</a>
</body>
</html>