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
<title><fmt:message key="page.change_phone_number.title" /></title>
</head>
<body>
	<%@ include file="/pages/part/navbar.jsp"%>
	<div class="row justify-content-md-center">
		<div class="col col-lg-6 mt-3">
			<h3 class="text-center">
				<fmt:message key="page.change_phone_number.heading" />
			</h3>
			<form action="${pageContext.request.contextPath}/controller"
				method="post">
				<input type="hidden" name="command" value="change_phone_number_page">
				<div class="input-group flex-nowrap mt-4">
					<span class="input-group-text">+375</span> <input type="text"
						class="form-control" name="phone_number"
						placeholder="<fmt:message key="register.phone_number"/>"
						aria-describedby="addon-wrapping" required
						pattern="^[25|44|33|29]\d{8}" />
				</div>
				<br />
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