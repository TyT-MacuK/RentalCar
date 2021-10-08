<nav class="navbar navbar-expand-lg navbar-light bg-light">
	<div class="container-fluid">
		<a class="navbar-brand"
			href="${pageContext.request.contextPath}/controller?command=to_home_page_command&page=1">
			<p class="text-success">Car4U</p>
		</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav me-auto mb-2 mb-lg-0">
				<c:if test="${is_authenticated}">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page"
						href="${pageContext.request.contextPath}/controller?command=to_personal_profile_page_command">
							<fmt:message key="navbar.profile" />
					</a></li>

				</c:if>
				<c:if test="${is_authenticated && user.getRole() == 'USER'}">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page"
						href="${pageContext.request.contextPath}/controller?command=to_orders_page_command">
							<fmt:message key="navbar.orders" />
					</a></li>
				</c:if>
				<c:if test="${is_authenticated == false}">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page"
						href="${pageContext.request.contextPath}/controller?command=to_sign_in_page_command">
							<fmt:message key="navbar.sign_in" />
					</a></li>
				</c:if>
				<c:if test="${is_authenticated && user.getRole() == 'ADMIN'}">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page"
						href="${pageContext.request.contextPath}/controller?command=to_admin_users_page_command">
							<fmt:message key="navbar.users" />
					</a></li>
					<li class="nav-item"><a class="nav-link active"
						aria-current="page"
						href="${pageContext.request.contextPath}/controller?command=to_admin_cars_page_command">
							<fmt:message key="navbar.cars" />
					</a></li>
					<li class="nav-item"><a class="nav-link active"
						aria-current="page"
						href="${pageContext.request.contextPath}/controller?command=to_admin_orders_page_command">
							<fmt:message key="navbar.orders" />
					</a></li>
				</c:if>
				<c:if test="${is_authenticated}">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page"
						href="${pageContext.request.contextPath}/controller?command=sign_out_command">
							<fmt:message key="navbar.sign_out" />
					</a></li>
				</c:if>
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
					role="button" data-bs-toggle="dropdown" aria-expanded="false">
						<fmt:message key="navbar.language" />
				</a>
					<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
						<li><a class="dropdown-item"
							href="${pageContext.request.contextPath}/controller?command=change_language_to_english_command">
								<fmt:message key="navbar.english" />
						</a></li>
						<li><a class="dropdown-item"
							href="${pageContext.request.contextPath}/controller?command=change_language_to_russian_command">
								<fmt:message key="navbar.russian" />
						</a></li>
					</ul></li>
			</ul>
			

			
			<form class="d-flex" action="${pageContext.request.contextPath}/controller"
					method="post">
					<input type="hidden" name="command"
						value="find_manufacturer_by_id_command">
				<input class="form-control me-2" name="manufacturer" type="search"
					placeholder=<fmt:message key="navbar.search_field"/>
					aria-label="Search" required
						pattern="^[a-zA-Z]+$">
				<c:if test="${car_manufacturer_incorrect == true}">
					<p class="text-danger">
						<fmt:message key="change.error" />
					</p>
				</c:if>
				<button class="btn btn-outline-success" type="submit">
					<fmt:message key="search" />
				</button>
			</form>
		</div>
	</div>
</nav>