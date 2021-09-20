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
<%@ include file="/css/links_to_bootstrap.jsp"%>
<title><fmt:message	key="register.title_confirmation_of_registration" /></title>	
</head>
<body>
	<div class="container">
		<div class="row justify-content-md-center">
			<div class="col col-lg-6 mt-3">
				<form action="${pageContext.request.contextPath}/controller" method="post">
				<input type="hidden" name="command" value="code_entry_page">
					<div class="mb-2">
						<label for="inputCode" class="form-label">
						<fmt:message key="register.code_confirmation"/></label>
						<input type="text" class="form-control" name="code"/>
					</div>
					<br/>
					<button type="submit" class="btn btn-primary"><fmt:message key="register.submit"/></button>
					<c:if test = "${entered_code_error}">
                       <p class="text-danger"> <fmt:message key="register.error.code.message"/></p>
                    </c:if>
				</form>
			</div>
		</div>
	</div>
</body>
</html>