<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="pgn" uri="pagination"%>
<c:set var="language" value="${sessionScope.locale}" scope="session" />
<fmt:setLocale value="${language}" scope="session" />
<fmt:setBundle basename="local.pagecontent" />
<!DOCTYPE html>
<html>
<head>
<%@ include file="/css/links_to_bootstrap.jsp"%>
<title><fmt:message key="page.title" /></title>
</head>
<body>
	<%@ include file="/pages/part/navbar.jsp"%>
	<div class="container bootstrap snipets">
		<h1 class="text-center text-success">
			<fmt:message key="home_page.heading" />
		</h1>
		<div class="row flow-offset-1">
			<c:if test="${list_empty == true}">
				<h2>
					<fmt:message key="value_is_not_found" />
				</h2>
			</c:if>
			<c:forEach var="car" items="${cars}">
				<div class="col-xs-6 col-md-4">
					<div class="product tumbnail thumbnail-3">
						<img src="${pageContext.request.contextPath}${car.getImageUrl()}"
							alt="">
						<div class="caption">
							<h6>${car.getCarManufacturer()} ${car.getModel()}</h6>
							<c:if test="${car.getDiscount() > 0}">
								<span class="price"><fmt:message key="home_page.price" />
									- <del>${car.getCost()}</del></span>
								<br />
								<span class="price_sale text-danger"><fmt:message
										key="home_page.discount_price" /> - ${car.getCost() - car.getCost()*car.getDiscount()/100}</span>
							</c:if>
							<c:if test="${car.getDiscount() == 0}">
								<span class="price"><fmt:message key="home_page.price" />
									- ${car.getCost()}</span>
							</c:if>
							<br /> <span class="year text-success">${car.getYear()}</span>
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
							<br />
							<form action="${pageContext.request.contextPath}/controller"
								method="post">
								<input type="hidden" name="command" value="sign_up_page">

							</form>
							<c:if test="${is_authenticated == true}">
								<a
									href="${pageContext.request.contextPath}/controller?command=to_make_order_page_command&car_id=${car.getCarId()}"
									class="link-primary"><fmt:message key="home_page.order" />
								</a>
							</c:if>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
	<br />
	<pgn:pagination currentPage="${current_page}"
		maxPage="${max_number_of_pages}" pageType='home' />
</body>
</html>
