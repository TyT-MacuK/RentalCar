<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="language" value="${sessionScope.locale}" scope="session"/>
<fmt:setLocale value="${language}" scope="session"/>
<fmt:setBundle basename="local.pagecontent"/>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/css/links_to_bootstrap.jsp"%>
<title><fmt:message key="page.title"/></title>
</head>
<body>
 <%@ include file="/pages/parts/navbar.jsp"%>
 <div class="container bootstrap snipets">
   <h1 class="text-center text-success"><fmt:message key="home_page.heading"/></h1>
   <div class="row flow-offset-1">
     <div class="col-xs-6 col-md-4">
       <div class="product tumbnail thumbnail-3"><a href="#"><img src="https://via.placeholder.com/350x280/FFB6C1/000000" alt=""></a>
         <div class="caption">
           <h6><a href="#">${cars.get(0).getCarManufacturer()} ${cars.get(0).getModel()}</a></h6>
           <c:if test = "${cars.get(0).getDiscount() = 0}">
                <span class="price"><del><fmt:message key="home_page.price"/> - ${cars.get(0).getCost()}, </del></span>
                <span class="price sale">${cars.get(0).getCost()*cars.get(0).getDiscount()/100}</span>
           </c:if>
           
             <span class="price"><fmt:message key="home_page.year"/> - ${cars.get(0).getYear()}</span>
         </div>
       </div>
     </div>
     <div class="col-xs-6 col-md-4">
       <div class="product tumbnail thumbnail-3"><a href="#"><img src="https://via.placeholder.com/350x280/87CEFA/000000" alt=""></a>
         <div class="caption">
           <h6><a href="#">${cars.get(1).getCarManufacturer()} ${cars.get(1).getModel()}</a></h6><span class="price">
             <del><fmt:message key="home_page.price"/> - ${cars.get(1).getCost() + 10}, </del></span>
             <span class="price sale">${cars.get(0).getCost()}</span>
             <span class="price"><fmt:message key="home_page.year"/> - ${cars.get(0).getYear()}</span>
         </div>
       </div>
     </div>
     <div class="col-xs-6 col-md-4">
       <div class="product tumbnail thumbnail-3"><a href="#"><img src="https://via.placeholder.com/350x280/FF7F50/000000" alt=""></a>
         <div class="caption">
           <h6><a href="#">${cars.get(2).getCarManufacturer()} ${cars.get(2).getModel()}</a></h6>
           <span class="price">${cars.get(2).getCost()}</span>
           <span class="price"><fmt:message key="home_page.year"/> - ${cars.get(2).getYear()}</span>
         </div>
       </div>
     </div>
     <div class="col-xs-6 col-md-4">
       <div class="product tumbnail thumbnail-3"><a href="#"><img src="https://via.placeholder.com/350x280/20B2AA/000000" alt=""></a>
         <div class="caption">
           <h6><a href="#">${cars.get(3).getCarManufacturer()} ${cars.get(3).getModel()}</a></h6>
           <span class="price">${cars.get(3).getCost()}</span>
           <span class="price"><fmt:message key="home_page.year"/> - ${cars.get(3).getYear()}</span>
         </div>
       </div>
     </div>
     <div class="col-xs-6 col-md-4">
       <div class="product tumbnail thumbnail-3"><a href="#"><img src="https://via.placeholder.com/350x280/8A2BE2/000000" alt=""></a>
         <div class="caption">
           <h6><a href="#">${cars.get(4).getCarManufacturer()} ${cars.get(4).getModel()}</a></h6>
            <span class="price">${cars.get(4).getCost()}</span>
            <span class="price"><fmt:message key="home_page.year"/> - ${cars.get(4).getYear()}</span>
         </div>
       </div>
     </div>
     <div class="col-xs-6 col-md-4">
       <div class="product tumbnail thumbnail-3"><a href="#"><img src="https://via.placeholder.com/350x280/6495ED/000000" alt=""></a>
         <div class="caption">
           <h6><a href="#">${cars.get(5).getCarManufacturer()} ${cars.get(5).getModel()}</a></h6>
           <span class="price">${cars.get(5).getCost()}</span>
           <span class="price"><fmt:message key="home_page.year"/> - ${cars.get(5).getYear()}</span>
         </div>
       </div>
     </div>
   </div>
 </div>
</body>
</html>