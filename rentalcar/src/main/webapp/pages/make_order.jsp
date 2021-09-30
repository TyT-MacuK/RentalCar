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
<title><fmt:message key="page.make_order.titel" /></title>
</head>
<body>
	<%@ include file="/pages/parts/navbar.jsp"%>
	<h3 class="text-center">
		<fmt:message key="page.make_order.heading" />
	</h3>
	<div class="container">
		<div class="row">
			<div class="col">
				<img src="${pageContext.request.contextPath}${car.getImageUrl()}"
					alt="">
				<h6>${car.getCarManufacturer()} ${car.getModel()}</h6>
				<span class="year text-success">${car.getYear()}</span>
				<c:if test="${car.getCarTransmission() == 'AUTOMATIC'}">
					<span class="transmission text-success"><fmt:message
							key="home_page.transmission.automatic" /></span>
				</c:if>
				<c:if test="${car.getCarTransmission() == 'MANUAL'}">
					<span class="transmission text-success"><fmt:message
							key="home_page.transmission.manual" /></span>
				</c:if>
				<c:if test="${car.isConditioner() == true}">
					<span class="conditioner text-success"><fmt:message
							key="home_page.conditioner" /></span>
				</c:if>
			</div>
			<div class="col">
				<br />
				<h5>
					<fmt:message key="page.make_order.price" />
					:
					<c:if test="${car.getDiscount() > 0}">
			${car.getCost() - car.getCost()*car.getDiscount()/100}
			</c:if>
					<c:if test="${car.getDiscount() == 0}">
			${car.getCost()}
			</c:if>
				</h5>
				<form action="${pageContext.request.contextPath}/controller"
					method="post">
					<input type="hidden" name="command" value="make_order_page">
					<div class="form-group mt-3">
						<label for="inputDate"><fmt:message
								key="page.make_order.pick_up_date" /></label> <input type="date"
							class="form-control" name="pick_up_date">
					</div>
					<div class="form-group mt-3">
						<label for="inputDate"><fmt:message
								key="page.make_order.return_date" /></label> <input type="date"
							class="form-control" name="return_date">
					</div>
					<c:if test="${booked == true}">
					     <p class="text-danger"><fmt:message key="page.make_order.car_booked"/></p>
					</c:if>
					<c:if test="${wrong_date == true}">
					     <p class="text-danger"><fmt:message key="page.make_order.wrong_date"/></p>
					</c:if>
					<br />
					<button type="submit" class="btn btn-primary">
						<fmt:message key="submit" />
					</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>