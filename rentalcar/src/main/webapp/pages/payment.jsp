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
<title><fmt:message key="payment.title" /></title>
</head>
<body>
	<h3 class="text-center">
		<fmt:message key="payment.heading" />
	</h3>
	<div class="row justify-content-md-center">
		<div class="col col-lg-6 mt-3">
			<form action="${pageContext.request.contextPath}/controller"
				method="post">
				<input type="hidden" name="command" value="payment_entry_page">
				<div class="mb-2">
					<label for="cardNumber" class="form-label"><fmt:message
							key="payment.card_number" /></label> <input type="text"
						class="form-control" name="card_number" required
						pattern="^\d{16}$" />
				</div>
				<div class="mb-2">
					<label for="cardNumber" class="form-label"><fmt:message
							key="payment.cvv" /></label> <input type="text" class="form-control"
						name="cvv" required pattern="^\d{3}$" />
				</div>
				<br />
				<button type="submit" class="btn btn-primary">
					<fmt:message key="submit" />
				</button>
			</form>
		</div>
	</div>
</body>
</html>