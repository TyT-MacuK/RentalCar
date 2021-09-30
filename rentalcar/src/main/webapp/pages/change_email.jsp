<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="language" value="${sessionScope.locale}" scope="session" />
<fmt:setLocale value="${language}" scope="session" />
<fmt:setBundle basename="local.pagecontent" />
<!DOCTYPE html>
<html>
<head>
<%@ include file="/css/links_to_bootstrap.jsp"%>
<title><fmt:message key="page.change_email.title" /></title>
</head>
<body>
<%@ include file="/pages/parts/navbar.jsp"%>
	<div class="row justify-content-md-center">
		<div class="col col-lg-6 mt-3">
			<h3 class="text-center">
				<fmt:message key="page.change_email.heading" />
			</h3>
			<p class="text-danger">
				<fmt:message key="page.change_email.warning" />
			</p>
			<form action="${pageContext.request.contextPath}/controller"
				method="post">
				<input type="hidden" name="command" value="change_email_page">
				<div class="mb-2">
					<label for="inputEmail1" class="form-label"><fmt:message
							key="register.email_field" /></label> <input type="email"
						class="form-control" name="email" required
						pattern="^[-0-9a-zA-Z.+_]+@[-0-9a-zA-Z.+_]+\.[a-zA-Z]{2,4}" />
				</div>
				<div class="mb-4">
					<label for="inputPassword1" class="form-label"><fmt:message
							key="register.password" /></label> <input type="password"
						class="form-control" name="password" required pattern=".{5,64}" />
				</div>
				<button type="submit" class="btn btn-primary">
					<fmt:message key="submit" />
				</button>
				<c:if test="${change_error}">
					<p class="text-danger">
						<fmt:message key="change.error" />
					</p>
				</c:if>
			</form>
		</div>
	</div>
</body>
</html>