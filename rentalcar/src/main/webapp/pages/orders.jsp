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
<title><fmt:message key="page.orders.titel" /></title>
</head>
<body>
	<%@ include file="/pages/parts/navbar.jsp"%>
	<div class="row justify-content-md-center">
		<div class="col col-lg-6 mt-3">
			<h1 class="text-center text-success">
				<fmt:message key="page.orders.heading" />
			</h1>
			<table class="table">
				<tbody>
				<thead>
					<tr>
						<th scope="col"><fmt:message key="page.orders.manufacturer" /></th>
						<th scope="col"><fmt:message key="page.orders.model"/></th>
						<th scope="col"><fmt:message key="page.orders.pick_up_date" /></th>
						<th scope="col"><fmt:message key="page.orders.return_date" /></th>
						<th scope="col"><fmt:message key="page.orders.price" /></th>
						<th scope="col"><fmt:message key="page.orders.order_status" /></th>
					</tr>
				</thead>
				<c:forEach var="order" items="${orders}">
					<tr>
						<td>${map_cars.get(order.getCarId()).getCarManufacturer()}</td>
						<td>${map_cars.get(order.getCarId()).getModel()}</td>
						<td>${order.getPickUpDate()}</td>
						<td>${order.getReturnDate()}</td>
						<td>${order.getPrice()}</td>
						<td>${order.getOrderStatus()}</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<pgn:pagination currentPage="${current_page}"
		maxPage="${max_number_of_pages}" pageType='orders' />
</body>
</html>