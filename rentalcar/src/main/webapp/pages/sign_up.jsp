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
<title><fmt:message key="register.title"/></title>
</head>
<body>
	  <div class="container"> 
	  <%@ include file="/pages/parts/navbar.jsp"%>
		<div class="row justify-content-md-center">
			<div class="col col-lg-6 mt-3">
				<h3 class="text-center"><fmt:message key="register.heading"/></h3>
				<form action="${pageContext.request.contextPath}/controller" method="post">
				<input type="hidden" name="command" value="sign_up_page">
					<div class="mb-2">
						<label for="inputEmail1" class="form-label"><fmt:message key="register.email_field"/></label>
						<input type="email" class="form-control" name="email"
						       required pattern="^[-0-9a-zA-Z.+_]+@[-0-9a-zA-Z.+_]+\.[a-zA-Z]{2,4}"/>
					</div>

					<div class="mb-4">
						<label for="inputPassword1" class="form-label"><fmt:message key="register.password"/></label> 
						<input type="password" class="form-control" name="password"
						       required pattern=".{5,64}"/>
					</div>
	
					<div class="input-group mb-2">
						<div class="input-group-prepend">
							<span class="input-group-text" ><fmt:message key="register.names"/></span>
						</div>
						<input type="text" class="form-control" name="first_name" required pattern="[a-zA-Z]*|[ЁёА-я]*"> 
						<input type="text" class="form-control" name="last_name" required pattern="[a-zA-Z]*|[ЁёА-я]*">
					</div>
		
					<div class="form-group mt-3">
						<label for="inputDate"><fmt:message key="register.date_of_birth"/></label> 
						<input type="date" class="form-control" name="date_of_birth">
					</div>

					<div class="input-group flex-nowrap mt-4">
						<span class="input-group-text" name="addon-wrapping">+375</span> 
						<input type="text" class="form-control" name="phone_number"
						       placeholder="<fmt:message key="register.phone_number"/>"
							   aria-describedby="addon-wrapping"
							   required pattern="^[25|44|33|29]\d{8}"/>
					</div>					
					<br/>
					<button type="submit" class="btn btn-primary"><fmt:message key="register.submit"/></button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>