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
<title><fmt:message key="page.admin_add_car.title" /></title>
</head>
<body>
	<%@ include file="/pages/parts/navbar.jsp"%>
	<div class="row justify-content-md-center">
		<div class="col col-lg-6 mt-3">
			<h3 class="text-center">
				<fmt:message key="page.admin_add_car.heading" />
			</h3>
			<c:if test="${input_data_incorrect == true}">
				<p class="text-danger">
					<fmt:message key="page.admin_add_car.invalid_data" />
				</p>
			</c:if>
			<c:if test="${invalid_file == true}">
					<p class="text-danger">
						<fmt:message key="page.admin_add_car.invalid_file" />
					</p>
				</c:if>
			<form action="${pageContext.request.contextPath}/upload/image"
				method="post" enctype='multipart/form-data'>
				<input type="hidden" name="command" value="admin_add_car_page">
				<label class="visually-hidden" for="autoSizingSelect">Preference</label>
				<fmt:message key="page.admin_add_car.manufacturer" />
				<select class="form-select" name="manufacturer">
					<option selected><fmt:message
							key="page.admin_add_car.choose_manufacturer" /></option>
					<option value="VOLKSWAGEN">Volkswagen</option>
					<option value="SKODA">Skoda</option>
					<option value="RENAULT">Renault</option>
					<option value="MAZDA">Mazda</option>
					<option value="MERCEDES">Mercedes</option>
				</select> <br />
				<div class="mb-2">
					<label for="inputModel" class="form-label"><fmt:message
							key="page.admin_add_car.model" /></label> <input type="text"
						class="form-control" name="model" required
						pattern="^[\w{1,50}\s]+$" />
				</div>
				<div class="mb-2">
					<label for="inputYear" class="form-label"><fmt:message
							key="page.admin_add_car.year" /></label> <input type="text"
						class="form-control" name="year" required pattern="^\d{4}$" />
				</div>
				<div class="form-check">
					<input class="form-check-input" type="checkbox" value="true"
						name="conditioner" id="flexCheckDefault"> <label
						class="form-check-label" for="flexCheckDefault"><fmt:message
							key="page.admin_add_car.conditioner" /></label>
				</div>
				<br /> <label class="visually-hidden" for="autoSizingSelect">Preference</label>
				<fmt:message key="page.admin_add_car.transmission" />
				<select class="form-select" name="transmission">
					<option selected><fmt:message
							key="page.admin_add_car.choose_transmission" /></option>
					<option value="AUTOMATIC"><fmt:message
							key="page.admin_add_car.automatic" /></option>
					<option value="MANUAL"><fmt:message
							key="page.admin_add_car.manual" /></option>
				</select> <br />
				<div class="mb-2">
					<label for="inputCost" class="form-label"><fmt:message
							key="page.admin_add_car.cost" /></label> <input type="text"
						class="form-control" name="cost" required pattern="^\d{1,5}$" />
				</div>
				<div class="mb-2">
					<label for="inputDiscount" class="form-label"><fmt:message
							key="page.admin_add_car.discount" /></label> <input type="text"
						class="form-control" name="car_discount" required
						pattern="^\d{1,2}$" />
				</div>
				<br /> <label class="visually-hidden" for="autoSizingSelect">Preference</label>
				<fmt:message key="page.admin_add_car.status" />
				<select class="form-select" name="car_status">
					<option selected><fmt:message
							key="page.admin.chang_status" /></option>
					<option value="BOOKED"><fmt:message
							key="page.admin_add_car.booked" /></option>
					<option value="FREE"><fmt:message
							key="page.admin_add_car.free" /></option>
					<option value="CAR_IS_SERVICED"><fmt:message
							key="page.admin_add_car.service" /></option>
					<option value="IMPOSSIBLE_TO_RENT"><fmt:message
							key="page.admin_add_car.impossible_to_rent" /></option>
				</select> <br />
				<div class="mb-3">
					<label for="formFile" class="form-label"> <fmt:message
							key="page.admin_add_car.add_file" /></label> <input class="form-control"
						type="file" accept="image/*" name="image" id="formFile">
				</div>
				<button type="submit" class="btn btn-primary">
					<fmt:message key="submit" />
				</button>
			</form>
		</div>
	</div>
</body>
</html>