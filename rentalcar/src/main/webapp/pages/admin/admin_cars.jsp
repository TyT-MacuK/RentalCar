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
<title><fmt:message key="page.admin_cars.title" /></title>
</head>
<body>
	<%@ include file="/pages/parts/navbar.jsp"%>
	<div class="container">
		<div class="row">
			<div class="col">
				<br />
				<h3>
					<fmt:message key="page.admin_cars.heading_discount" />
				</h3>
				<form action="${pageContext.request.contextPath}/controller"
					method="post">
					<input type="hidden" name="command"
						value="change_car_discount_command">
					<div class="row mb-3">
						<div class="col-sm-7">
							<input type="text" class="form-control" name="car_id"
								placeholder="<fmt:message
								key="page.admin_cars.id" />"
								required pattern="^\d+$">
						</div>
					</div>
					<div class="row mb-3">
						<div class="col-sm-7">
							<input type="text" class="form-control" name="car_discount"
								placeholder="<fmt:message
								key="page.admin_cars.discount" />"
								required pattern="^\d+$">
						</div>
					</div>
					<button type="submit" class="btn btn-primary">
						<fmt:message key="submit" />
					</button>
					<c:if test="${change_discount_incorrect == true}">
						<p class="text-danger">
							<fmt:message key="change.error" />
						</p>
					</c:if>
				</form>
				<br />
				<h3>
					<fmt:message key="page.admin_cars.heading_cost" />
				</h3>
				<form action="${pageContext.request.contextPath}/controller"
					method="post">
					<input type="hidden" name="command" value="change_car_cost_command">
					<div class="row mb-3">
						<div class="col-sm-7">
							<input type="text" class="form-control" name="car_id"
								placeholder="<fmt:message
								key="page.admin_cars.id" />"
								required pattern="^\d+$">
						</div>
					</div>
					<div class="row mb-3">
						<div class="col-sm-7">
							<input type="text" class="form-control" name="cost"
								placeholder="<fmt:message
								key="page.admin_cars.cost" />"
								required pattern="^\d+$">
						</div>
					</div>
					<button type="submit" class="btn btn-primary">
						<fmt:message key="submit" />
					</button>
					<c:if test="${change_cost_incorrect == true}">
						<p class="text-danger">
							<fmt:message key="change.error" />
						</p>
					</c:if>
				</form>
				<br />
				<h3>
					<fmt:message key="page.admin_cars.heading_status" />
				</h3>
				<form action="${pageContext.request.contextPath}/controller"
					method="post">
					<input type="hidden" name="command"
						value="change_car_status_command">
					<div class="row mb-3">
						<div class="col-sm-7">
							<input type="text" class="form-control" name="car_id"
								placeholder="<fmt:message
								key="page.admin_cars.id" />"
								required pattern="^\d+$">
						</div>
					</div>
					<div class="row mb-3">
						<div class="col-sm-7">
							<label class="visually-hidden" for="autoSizingSelect">Preference</label>
							<select class="form-select" name="car_status">
								<option selected><fmt:message
										key="page.admin.chang_status" /></option>
								<option value="BOOKED"><fmt:message
										key="page.admin_cars.booked" /></option>
								<option value="FREE"><fmt:message
										key="page.admin_cars.free" /></option>
								<option value="CAR_IS_SERVICED"><fmt:message
										key="page.admin_cars.service" /></option>
								<option value="IMPOSSIBLE_TO_RENT"><fmt:message
										key="page.admin_cars.impossible" /></option>
							</select>
						</div>
					</div>
					<button type="submit" class="btn btn-primary">
						<fmt:message key="submit" />
					</button>
					<c:if test="${change_car_status_incorrect == true}">
						<p class="text-danger">
							<fmt:message key="change.error" />
						</p>
					</c:if>
				</form>
				<br />
			</div>
			<div class="col">
				<br />
				<table class="table">
					<tbody>
					<thead>
						<tr>
							<th scope="col"><fmt:message key="page.admin_cars.id" /></th>
							<th scope="col"><fmt:message
									key="page.admin_cars.manufacturer" /></th>
							<th scope="col"><fmt:message key="page.admin_cars.model" /></th>
							<th scope="col"><fmt:message key="page.admin_cars.year" /></th>
							<th scope="col"><fmt:message
									key="page.admin_cars.transmission" /></th>
							<th scope="col"><fmt:message
									key="page.admin_cars.conditioner" /></th>
							<th scope="col"><fmt:message key="page.admin_cars.cost" /></th>
							<th scope="col"><fmt:message key="page.admin_cars.discount" /></th>
							<th scope="col"><fmt:message key="page.admin_cars.status" /></th>
						</tr>
					</thead>
					<c:forEach var="car" items="${cars}">
						<tr>
							<td>${car.getCarId()}</td>
							<td>${car.getCarManufacturer()}</td>
							<td>${car.getModel()}</td>
							<td>${car.getYear()}</td>
							<c:if test="${car.getCarTransmission() == 'AUTOMATIC'}">
								<td><fmt:message
										key="page.admin_cars.transmission.automatic" /></td>
							</c:if>
							<c:if test="${car.getCarTransmission() == 'MANUAL'}">
								<td><fmt:message key="page.admin_cars.transmission.manual" /></td>
							</c:if>
							<c:if test="${car.isConditioner() == true}">
								<td><fmt:message key="page.admin_cars.conditioner_present" /></td>
							</c:if>
							<c:if test="${car.isConditioner() == false}">
								<td>-</td>
							</c:if>
							<td>${car.getCost()}</td>
							<td>${car.getDiscount()}</td>
							<td>${car.getCarStatus()}</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
				 
				<pgn:pagination currentPage="${current_page}"
					maxPage="${max_number_of_pages}" pageType='admin_cars' />
					<br />
					<h4><a
					href="${pageContext.request.contextPath}/controller?command=to_admin_add_car_page_command"
					class="link-primary"><fmt:message key="page.admin_cars.add_car" />
				</a></h4>
			</div>
		</div>
	</div>
</body>
</html>