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
<title><fmt:message key="page.admin_orders.title" /></title>
</head>
<body>
	<%@ include file="/pages/part/navbar.jsp"%>
	<div class="container">
		<div class="row">
			<div class="col">
				<br />
				<h3>
					<fmt:message key="page.admin_orders.heading_search" />
				</h3>
				<form action="${pageContext.request.contextPath}/controller"
					method="post">
					<input type="hidden" name="command"
						value="find_order_by_id_command">
					<div class="row mb-3">
						<div class="col-sm-7">
							<input type="text" class="form-control" name="order_id"
								placeholder="<fmt:message
								key="page.admin_orders.id" />"
								required pattern="^\d+$">
						</div>
					</div>
					<button type="submit" class="btn btn-primary">
						<fmt:message key="search" />
					</button>
					<c:if test="${order_id_incorrect == true}">
						<p class="text-danger">
							<fmt:message key="change.error" />
						</p>
					</c:if>
				</form>
				<br />
				<h3>
					<fmt:message key="page.admin_orders.heading_status" />
				</h3>
				<form action="${pageContext.request.contextPath}/controller"
					method="post">
					<input type="hidden" name="command"
						value="change_order_status_command">
					<div class="row mb-3">
						<div class="col-sm-7">
							<input type="text" class="form-control" name="id"
								placeholder="<fmt:message
								key="page.admin_orders.id" />"
								required pattern="^\d+$">
						</div>
					</div>
					<div class="row mb-3">
						<div class="col-sm-7">
							<label class="visually-hidden" for="autoSizingSelect">Preference</label>
							<select class="form-select" name="status">
								<option value="choose status"><fmt:message
										key="page.admin.chang_status" /></option>
								<option value="PAID"><fmt:message
										key="page.admin_orders.paid" /></option>
								<option value="DECLINED"><fmt:message
										key="page.admin_orders.declined" /></option>
							</select>
						</div>
					</div>
					<button type="submit" class="btn btn-primary">
						<fmt:message key="submit" />
					</button>
					<c:if test="${change_order_status_incorrect == true}">
						<p class="text-danger">
							<fmt:message key="change.error" />
						</p>
					</c:if>
				</form>
			</div>
			<div class="col">
				<br />
				<table class="table">
					<tbody>
					<thead>
						<tr>
							<th scope="col"><fmt:message key="page.admin_orders.id" /></th>
							<th scope="col"><fmt:message key="page.admin_orders.price" /></th>
							<th scope="col"><fmt:message
									key="page.admin_orders.pick_up_date" /></th>
							<th scope="col"><fmt:message
									key="page.admin_orders.return_date" /></th>
							<th scope="col"><fmt:message key="page.admin_orders.status" /></th>
							<th scope="col"><fmt:message key="page.admin_orders.car_id" /></th>
							<th scope="col"><fmt:message key="page.admin_orders.user_id" /></th>
						</tr>
					</thead>
					<c:forEach var="order" items="${orders}">
						<tr>
							<td>${order.getOrderId()}</td>
							<td>${order.getPrice() }</td>
							<td>${order.getPickUpDate() }</td>
							<td>${order.getReturnDate() }</td>
							<td>${order.getOrderStatus() }</td>
							<td>${order.getCarId()}</td>
							<td>${order.getUserId()}</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
				<pgn:pagination currentPage="${current_page}"
					maxPage="${max_number_of_pages}" pageType='admin_orders' />
			</div>
		</div>
	</div>
</body>
</html>