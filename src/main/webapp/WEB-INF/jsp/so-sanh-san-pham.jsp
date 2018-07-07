<%--
  Created by IntelliJ IDEA.
  User: Notebook
  Date: 06-Jul-18
  Time: 9:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>So sánh sản phẩm</title>
    <link rel="stylesheet" href="/css/bootstrap/bootstrap.min.css">
    <link rel="stylesheet" href="/css/fontawesome/css/fontawesome-all.min.css" />
    <link rel="stylesheet" href="/css/so-sanh-san-pham.css">
</head>
<body>
    <div class="fixed-top">
        <!--------------------------------------------------->
        <!-----------------  Navbar 1 start ----------------->
        <!--------------------------------------------------->
        <nav class="navbar navbar-expand-sm py-3" id="nav-1">
            <div class="container">
                <a class="navbar-brand" href="#">
                    <img src="./img/logo.png" class="d-inline-block align-top" alt="">
                </a>
                <button class="navbar-toggler" type="button" data-toggle="collapse"
                        data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                        aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse">
                    <ul class="navbar-nav mr-auto">
                    </ul>
                    <form class="form-inline">
                        <div class="input-group input-group-sm mt-3">
                            <input type="text" placeholder="CPU name" class="form-control"
                                   aria-describedby="basic-addon2">
                            <div class="input-group-append">
                                <button class="btn btn-main-blue" type="button">
                                    <i class="fas fa-search"></i>
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </nav>
        <!--------------------------------------------------->
        <!-----------------  Navbar 1 end  ------------------>
        <!--------------------------------------------------->


        <!--------------------------------------------------->
        <!-----------------  Navbar 2 start ----------------->
        <!--------------------------------------------------->
        <nav class="navbar navbar-expand-sm navbar-light bg-light" id="nav-2">
            <div class="container">
                <button class="navbar-toggler" type="button" data-toggle="collapse"
                        data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                        aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav mr-auto">
                        <li class="nav-item active">
                            <a class="nav-link" href="#">HOME</a>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link" href="#" id="navbarDropdown" role="button" data-toggle="dropdown"
                               aria-haspopup="true" aria-expanded="false">
                                CPUs
                            </a>
                            <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <a class="dropdown-item" href="#">Intel</a>
                            </div>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">ABOUT</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
        <!--------------------------------------------------->
        <!-----------------  Navbar 2 end ------------------->
        <!--------------------------------------------------->
    </div>

    <div id="page-content">
        <div class="container">
            <table class="table table-bordered" id="compare-table">
                <tbody>
                    <tr>
                        <td class="field"></td>
                        <c:forEach var="product" items="${list}">
                            <td class="result">
                                <c:choose>
                                    <c:when test="${fn:contains(product.name, 'Intel')}">
                                        <img class="manufacture-img" src="/img/1200px-Intel-logo.svg.png" />
                                    </c:when>
                                    <c:when test="${fn:contains(product.name, 'AMD')}">
                                        <img class="manufacture-img" src="/img/AMD-red-white-logo.png" />
                                    </c:when>
                                    <c:otherwise>
                                        <i class="fas fa-question manufacture-img"></i>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </c:forEach>
                    </tr>
                    <tr>
                        <td>Tên sản phẩm</td>
                        <c:forEach var="product" items="${list}">
                            <td>${product.name}</td>
                        </c:forEach>
                    </tr>
                    <tr>
                        <td>Socket</td>
                        <c:forEach var="product" items="${list}">
                            <td>${product.socket}</td>
                        </c:forEach>
                    </tr>
                    <tr>
                        <td>Xung nhịp cơ bản</td>
                        <c:forEach var="product" items="${list}">
                            <td class="clockspeed" value="${product.clockspeed}">
                                <c:if test="${not empty product.clockspeed}">
                                    ${product.clockspeed} GHz
                                </c:if>
                            </td>
                        </c:forEach>
                    </tr>
                    <tr>
                        <td>Xung nhịp tối đa</td>
                        <c:forEach var="product" items="${list}">
                            <td class="turbospeed" value="${product.turbospeed}">
                                <c:if test="${not empty product.turbospeed}">
                                    ${product.turbospeed} GHz
                                </c:if>
                            </td>
                        </c:forEach>
                    </tr>
                    <tr>
                        <td>Số nhân</td>
                        <c:forEach var="product" items="${list}">
                            <td class="no-of-cores" value="${product.noOfCores}">${product.noOfCores}</td>
                        </c:forEach>
                    </tr>
                    <tr>
                        <td>Mức tiêu thụ điện</td>
                        <c:forEach var="product" items="${list}">
                            <td class="TDP" value="${product.TDP}"><fmt:formatNumber value="${product.TDP}"
                                                                                     minFractionDigits="0" /> W
                            </td>
                        </c:forEach>
                    </tr>
                    <tr>
                        <td>Điểm</td>
                        <c:forEach var="product" items="${list}">
                            <td class="benchmark" value="${product.benchmark}">
                                    ${product.benchmark}
                            </td>
                        </c:forEach>
                    </tr>
                    <tr>
                        <td>Giá</td>
                        <c:forEach var="product" items="${list}">
                            <c:forEach var="shop" items="${product.shops}" varStatus="counter">
                                <c:if test="${counter.count eq 1}">
                                    <td class="price" value="${shop.price}">
                                        <fmt:formatNumber var="price" pattern="###,###" value="${shop.price}" />
                                        <c:out value="${fn:replace(price, ',', '.')}" /> ₫
                                    </td>
                                </c:if>
                            </c:forEach>
                        </c:forEach>
                    </tr>
                    <tr>
                        <td>Điểm/Giá (x 10.000 ₫)</td>
                        <c:forEach var="product" items="${list}">
                            <td class="benchmark-per-price"></td>
                        </c:forEach>
                    </tr>
                    <tr>
                        <td class="product-detail"></td>
                        <c:forEach var="product" items="${list}">
                            <td class="product-detail">
                                <button class="btn btn-main-blue col-sm-12" value="${product.id}">
                                    Chi tiết sản phầm
                                </button>
                            </td>
                        </c:forEach>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>

    <!--------------------------------------------------->
    <!----------------- Navbar 3 start------------------->
    <!--------------------------------------------------->
    <nav class="navbar navbar-expand-sm navbar-expand-sm py-3" id="nav-3">
        <div class="container">
            <a class="navbar-brand" href="#">
                <img src="/img/logowhite.png" class="d-inline-block align-top" alt="">
            </a>
            <div class="row">
                <div class="col col-sm-6">
                    <div class="row">
                        <div class="col col-sm-12">
                            <p class="text-light"><b>CPUs</b></p>
                        </div>
                        <div class="col col-sm-12">
                            <a class="text-light" href="#">Intel</a>
                        </div>
                        <div class="col col-sm-12">
                            <a class="text-light" href="#">AMD</a>
                        </div>
                    </div>
                </div>
                <div class="col col-sm-6">
                    <div class="row">
                        <div class="col col-sm-12">
                            <p class="text-light"><b>Info</b></p>
                        </div>
                        <div class="col col-sm-12">
                            <a class="text-light" href="#">About Us</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </nav>
    <!--------------------------------------------------->
    <!-----------------  Navbar 3 end ------------------->
    <!--------------------------------------------------->

    <!--------------------------------------------------->
    <!----------------- Navbar 4 start------------------->
    <!--------------------------------------------------->
    <nav class="navbar navbar-expand-sm navbar-expand-lg navbar-dark bg-dark" id="nav-4">
        <div class="container">
            <div class="collapse navbar-collapse">
                <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
                    <li class="nav-item active">
                        <span class="nav-link">© 2018 Made by Nyabiron</span>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
    <!--------------------------------------------------->
    <!-----------------  Navbar 4 end ------------------->
    <!--------------------------------------------------->

    <script type="text/javascript" src="/js/nyabiron.js"></script>
    <script type="text/javascript" src="/js/so-sanh-san-pham.js"></script>
</body>
</html>
