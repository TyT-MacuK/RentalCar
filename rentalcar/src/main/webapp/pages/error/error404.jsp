<%@ page isErrorPage="true" language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
   <title>Error 404</title>
</head>
<body>
Request from: ${pageContent.errorData.requestURI} is failed
<br/>
Servlet name: ${pageContent.errorData.ServletName}
<br/>
Status code: ${pageContent.errorData.statusCode}
<br/>
Exception: ${pageContent.exception}
<br/>
Message from exception: ${pageContent.exception.message}
<a href="${pageContext.request.contextPath}/index.jsp">Back to index page</a>
</body>
</html>