<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="language" value="${sessionScope.locale}" scope="session"/>
<fmt:setLocale value="${language}" scope="session"/>
<fmt:setBundle basename="local.pagecontent"/>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"></script>
<title><fmt:message
		key="register.title_confirmation_of_registration" /></title>
</head>
<body>
	<div class="container">
		<div class="row justify-content-md-center">
			<div class="col col-lg-6 mt-3">
				<form action="controller" method="post">
				<input type="hidden" name="command" value="code_entry_page">
					<div class="mb-2">
						<label for="inputCode" class="form-label">
						<fmt:message key="register.code_confirmation"/></label>
						<input type="text" class="form-control" name="code"/>
					</div>
					<br/>
					<button type="submit" class="btn btn-primary"><fmt:message key="register.submit"/></button>
				</form>
				<c:if test = "${entered_code_error}">
                    <p class="text-danger"> <fmt:message key="register.error.code.message"/></p>
                </c:if>
			</div>
		</div>
	</div>
</body>
</html>