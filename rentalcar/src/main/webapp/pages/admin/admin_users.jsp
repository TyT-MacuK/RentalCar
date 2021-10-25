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
<title><fmt:message key="page.admin_users.title" /></title>
</head>
<body>
	<%@ include file="/pages/part/navbar.jsp"%>
	<div class="container">
		<div class="row">
			<div class="col">
				<br />
				<h3>
					<fmt:message key="page.admin_users.heading_discount" />
				</h3>
				<form action="${pageContext.request.contextPath}/controller"
					method="post">
					<input type="hidden" name="command"
						value="change_user_discount_command">
					<div class="row mb-3">
						<div class="col-sm-7">
							<input type="text" class="form-control" name="id"
								placeholder="<fmt:message
								key="page.admin_users.id" />"
								required pattern="^\d+$">
						</div>
					</div>
					<div class="row mb-3">
						<div class="col-sm-7">
							<input type="text" class="form-control" name="user_discount"
								placeholder="<fmt:message
								key="page.admin_users.discount" />"
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
					<fmt:message key="page.admin_users.heading_status" />
				</h3>
				<form action="${pageContext.request.contextPath}/controller"
					method="post">
					<input type="hidden" name="command"
						value="change_user_status_command">
					<div class="row mb-3">
						<div class="col-sm-7">
							<input type="text" class="form-control" name="id"
								placeholder="<fmt:message
								key="page.admin_users.id" />"
								required pattern="^\d+$">
						</div>
					</div>
					<div class="row mb-3">
						<div class="col-sm-7">
							<label class="visually-hidden" for="autoSizingSelect">Preference</label>
							<select class="form-select" name="status">
								<option value="choose status"><fmt:message
										key="page.admin.chang_status" /></option>
								<option value="ACTIVE"><fmt:message
										key="page.admin_users.active" /></option>
								<option value="BANNED"><fmt:message
										key="page.admin_users.banned" /></option>
							</select>
						</div>
					</div>
					<button type="submit" class="btn btn-primary">
						<fmt:message key="submit" />
					</button>
					<c:if test="${change_status_incorrect == true}">
						<p class="text-danger">
							<fmt:message key="change.error" />
						</p>
					</c:if>
				</form>
				<br />
				<h3>
					<fmt:message key="page.admin_users.heading_role" />
				</h3>
				<form action="${pageContext.request.contextPath}/controller"
					method="post">
					<input type="hidden" name="command"
						value="change_user_role_command">
					<div class="row mb-3">
						<div class="col-sm-7">
							<input type="text" class="form-control" name="id"
								placeholder="<fmt:message
								key="page.admin_users.id" />"
								required pattern="^\d+$">
						</div>
					</div>
					<div class="row mb-3">
						<div class="col-sm-7">
							<label class="visually-hidden" for="autoSizingSelect">Preference</label>
							<select class="form-select" name="role">
								<option value="choose role"><fmt:message
										key="page.admin.chang_role" /></option>
								<option value="USER"><fmt:message
										key="page.admin_users.user" /></option>
								<option value="ADMIN"><fmt:message
										key="page.admin_users.admin" /></option>
							</select>
						</div>
					</div>
					<button type="submit" class="btn btn-primary">
						<fmt:message key="submit" />
					</button>
					<c:if test="${change_status_incorrect == true}">
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
							<th scope="col"><fmt:message key="page.admin_users.id" /></th>
							<th scope="col"><fmt:message
									key="page.admin_users.first_name" /></th>
							<th scope="col"><fmt:message
									key="page.admin_users.last_name" /></th>
							<th scope="col"><fmt:message
									key="page.admin_users.day_of_birth" /></th>
							<th scope="col"><fmt:message
									key="page.admin_users.phone_number" /></th>
							<th scope="col"><fmt:message key="page.admin_users.discount" /></th>
							<th scope="col"><fmt:message key="page.admin_users.email" /></th>
							<th scope="col"><fmt:message key="page.admin_users.status" /></th>
							<th scope="col"><fmt:message key="page.admin_users.role" /></th>
						</tr>
					</thead>
					<c:forEach var="user" items="${users}">
						<tr>
							<td>${user.getUserId()}</td>
							<td>${user.getFirstName()}</td>
							<td>${user.getLastName()}</td>
							<td>${user.getDateOfBirth()}</td>
							<td>+375${user.getPhoneNumber()}</td>
							<td>${user.getDiscount()}</td>
							<td>${user.getEmail()}</td>
							<td>${user.getStatus()}</td>
							<td>${user.getRole()}</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
				<pgn:pagination currentPage="${current_page}"
					maxPage="${max_number_of_pages}" pageType='admin_users' />
			</div>
		</div>
	</div>
</body>
</html>