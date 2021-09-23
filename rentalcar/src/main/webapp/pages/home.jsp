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
<title><fmt:message key="page.title" /></title>
</head>
<body>
	<%@ include file="/pages/parts/navbar.jsp"%>
	<div class="container bootstrap snipets">
		<h1 class="text-center text-success">
			<fmt:message key="home_page.heading" />
		</h1>
		<div class="row flow-offset-1">
			<c:forEach var="car" items="${cars}">
				<div class="col-xs-6 col-md-4">
					<div class="product tumbnail thumbnail-3">
						<a href="#"><img
							src="${pageContext.request.contextPath}/img/skoda_rapid_2021.jpg"
							alt=""></a>
						<div class="caption">
							<h6>
								<a href="#">${car.getCarManufacturer()} ${car.getModel()}</a>
							</h6>
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
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
</body>
</html>
