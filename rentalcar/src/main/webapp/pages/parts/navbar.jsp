<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <div class="container-fluid">	
    <a class="navbar-brand" href="${pageContext.request.contextPath}/controller?command=to_home_page_command">
      <p class="text-success">Car4U</p>
    </a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
      data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
      aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="#"><fmt:message key="navbar.profile"/></a>
        </li>
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="#"><fmt:message key="navbar.orders"/></a>
        </li>
        <li class="nav-item">
          <a class="nav-link active" aria-current="page"
             href="${pageContext.request.contextPath}/controller?command=to_sign_up_page_command">Registration</a>
        </li>
        <li class="nav-item dropdown">
           <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown"
              aria-expanded="false">
            <fmt:message key="navbar.language"/>
          </a>
          <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
            <li><a class="dropdown-item"
              href="${pageContext.request.contextPath}/controller?command=change_language_to_english_command">
              <fmt:message key="navbar.english"/></a>
            </li>
            <li><a class="dropdown-item" 
              href="${pageContext.request.contextPath}/controller?command=change_language_to_russian_command">
              <fmt:message key="navbar.russian"/></a>
            </li>
          </ul>
        </li>
      </ul>
      <form class="d-flex">
        <input class="form-control me-2" type="search" placeholder=<fmt:message key="navbar.search_field"/>
         aria-label="Search">
        <button class="btn btn-outline-success" type="submit"><fmt:message key="navbar.search"/></button>
      </form>
    </div>
  </div>
</nav>