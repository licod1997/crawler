<%--
  Created by IntelliJ IDEA.
  User: Notebook
  Date: 01-Jul-18
  Time: 8:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Danh sách sản phẩm</title>
    <link rel="stylesheet" href="/css/bootstrap/bootstrap.min.css">
    <link rel="stylesheet" href="/css/fontawesome/css/fontawesome-all.min.css" />
    <link rel="stylesheet" href="/css/danh-sach-san-pham.css">
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
    <c:set var="productList" value="${page.content}" />
    <div id="page-content">
        <div class="container">
            <div class="row">
                <div class="col col-sm-3">
                    <div class="alert bg-light" role="alert">
                        <span style="font-size: 1.3rem"><b>Bộ lọc</b></span>
                    </div>
                    <div id="filter">
                        <div class="card" id="card-1">
                            <div class="card-header">
                                <button class="btn btn-link collapsed">
                                    <b>Thương hiệu</b>
                                    <i class="fas fa-chevron-down"></i>
                                </button>
                            </div>

                            <div class="collapse show">
                                <div class="card-body">
                                    <div class="row">
                                        <div class="col col-sm-6">
                                            <input type="checkbox" value="Intel"> Intel
                                        </div>
                                        <div class="col col-sm-6">
                                            <input type="checkbox" value="AMD"> AMD
                                        </div>
                                        <div class="col col-sm-6">
                                            <input type="checkbox" value="Others"> Khác
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="card" id="card-2">
                            <div class="card-header">
                                <button class="btn btn-link collapsed">
                                    <b>Đơn giá</b>
                                    <i class="fas fa-chevron-down"></i>
                                </button>
                            </div>
                            <div class="collapse show">
                                <div class="card-body">
                                    <div class="col col-sm-12">
                                        <input type="checkbox" value="0-5000000"> 0 ₫ - 5.000.000 ₫
                                    </div>
                                    <div class="col col-sm-12">
                                        <input type="checkbox" value="5000000-10000000"> 5.000.000 ₫ - 10.000.000 ₫
                                    </div>
                                    <div class="col col-sm-12">
                                        <input type="checkbox" value="10000000-20000000"> 10.000.000 ₫ - 20.000.000 ₫
                                    </div>
                                    <div class="col col-sm-12">
                                        <input type="checkbox" value="20000000-30000000"> 20.000.000 ₫ - 30.000.000 ₫
                                    </div>
                                    <div class="col col-sm-12">
                                        <input type="checkbox" value="30000000-gt"> Trên 30.000.000 ₫
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="card" id="card-3">
                            <div class="card-header">
                                <button class="btn btn-link collapsed">
                                    <b>Dòng CPU</b>
                                    <i class="fas fa-chevron-down"></i>
                                </button>
                            </div>
                            <div class="collapse show">
                                <div class="card-body">
                                    <div class="col col-sm-6">
                                        <input type="checkbox" value="Pentium"> Pentium
                                    </div>
                                    <div class="col col-sm-6">
                                        <input type="checkbox" value="Core i3"> Core i3
                                    </div>
                                    <div class="col col-sm-6">
                                        <input type="checkbox" value="Core i5"> Core i5
                                    </div>
                                    <div class="col col-sm-6">
                                        <input type="checkbox" value="Core i7"> Core i7
                                    </div>
                                    <div class="col col-sm-6">
                                        <input type="checkbox" value="Core i9"> Core i9
                                    </div>
                                    <div class="col col-sm-6">
                                        <input type="checkbox" value="Xeon"> Xeon
                                    </div>
                                    <div class="col col-sm-6">
                                        <input type="checkbox" value="Ryzen 5"> Ryzen 5
                                    </div>
                                    <div class="col col-sm-6">
                                        <input type="checkbox" value="Ryzen 7"> Ryzen 7
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="card" id="card-4">
                            <div class="card-header">
                                <button class="btn btn-link collapsed">
                                    <b>Socket</b>
                                    <i class="fas fa-chevron-down"></i>
                                </button>
                            </div>
                            <div class="collapse show">
                                <div class="card-body">
                                    <div class="row">
                                        <c:forEach var="socket" items="${socketList}">
                                            <c:if test="${not empty socket}">
                                                <div class="col col-sm-12">
                                                    <input type="checkbox" value="${socket}"> ${socket}
                                                </div>
                                            </c:if>
                                        </c:forEach>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="card" id="card-5">
                            <div class="card-header">
                                <button class="btn btn-link collapsed">
                                    <b>Số nhân</b>
                                    <i class="fas fa-chevron-down"></i>
                                </button>
                            </div>
                            <div class="collapse show">
                                <div class="card-body">
                                    <div class="row">
                                        <c:forEach var="noOfCores" items="${noOfCoresList}">
                                            <c:if test="${not empty noOfCores}">
                                                <div class="col col-sm-6">
                                                    <input type="checkbox" value="${noOfCores}"> ${noOfCores}
                                                </div>
                                            </c:if>
                                        </c:forEach>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col col-sm-9">
                    <div class="row" id="list-content">
                        <div class="col col-sm-12">
                            <div class="alert bg-light" role="alert">
                                <span style="font-size: 1.3rem"><b>Danh sách sản phẩm</b></span>
                            </div>
                        </div>
                        <c:forEach var="product" items="${productList}">
                            <div class="col col-sm-3">
                                <div class="card">
                                    <c:set var="cpuName" value="${product.name}" />
                                    <c:if test="${not empty cpuName}">
                                        <c:choose>
                                            <c:when test="${fn:contains(cpuName, 'Intel')}">
                                                <img class="card-img-top" src="/img/1200px-Intel-logo.svg.png">
                                            </c:when>
                                            <c:when test="${fn:contains(cpuName, 'AMD')}">
                                                <img class="card-img-top" src="/img/AMD-red-white-logo.png">
                                            </c:when>
                                            <c:otherwise>
                                                <i class="fas fa-question"></i>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:if>
                                    <div class="card-body">
                                        <a href="#" class="product-name">
                                            <p class="card-text" style="font-size: 13px">
                                                <c:set var="clockspeed" value="${product.clockspeed}" />
                                                <c:set var="turbospeed" value="${product.turbospeed}" />
                                                Bộ vi xử lý ${product.name}
                                                <c:if test="${not empty clockspeed}">(${clockspeed} GHz<c:if
                                                        test="${not empty turbospeed}">/${turbospeed} GHz</c:if>)</c:if>
                                            </p>
                                        </a>
                                        <div class="product-action">
                                            <a href="#" class="benchmark">
                                                <b>Điểm: </b>
                                                <b class="result">${product.benchmark}</b>
                                            </a>
                                            <br />
                                            <a href="#" class="price">
                                                <b>Giá: </b>
                                                <b class="result">
                                                    <c:forEach var="i" items="${product.shops}">
                                                        <c:forEach var="j" items="${product.shops}">
                                                            <c:if test="${i.price le j.price}">
                                                                <c:set var="min" value="${i.price}" />
                                                            </c:if>
                                                        </c:forEach>
                                                    </c:forEach>
                                                    <fmt:formatNumber var="min" type="number" maxFractionDigits="3"
                                                                      value="${min}" />
                                                        ${fn:replace(min, ',', '.')} <c:if
                                                        test="${not empty min}">₫</c:if>
                                                    <c:set var="min" value="" />
                                                </b>
                                            </a>
                                            <a href="#" class="btn btn-main-blue col-sm-12">
                                                So sánh <i class="fas fa-plus"></i>
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                    <c:if test="${not empty productList}">
                        <nav aria-label="paging" class="row justify-content-md-center">
                            <ul class="pagination col-md-auto">
                                <li class="page-item disabled">
                                    <a class="page-link" href="#">
                                        <i class="fas fa-angle-double-left"></i>
                                    </a>
                                </li>
                                <li class="page-item disabled">
                                    <a class="page-link" href="#">
                                        <i class="fas fa-angle-left"></i>
                                    </a>
                                </li>
                                <c:set var="end" value="${page.totalPages gt 5 ? 5 : page.totalPages}" />
                                <c:forEach var="i" begin="1" end="${end}" varStatus="counter">
                                    <li class="page-item <c:if test="${i eq page.number + 1}">active</c:if>">
                                        <a class="page-link" href="i"><c:out value="${i}"></c:out></a>
                                    </li>
                                </c:forEach>
                                <li class="page-item <c:if test="${page.last}">disabled</c:if>">
                                    <a class="page-link" href="#">
                                        <i class="fas fa-angle-right"></i>
                                    </a>
                                </li>
                                <li class="page-item <c:if test="${page.last}">disabled</c:if>">
                                    <a class="page-link" href="#">
                                        <i class="fas fa-angle-double-right"></i>
                                    </a>
                                </li>
                            </ul>
                        </nav>
                    </c:if>
                </div>
            </div>
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
    <script type="text/javascript" src="/js/danh-sach-san-pham.js"></script>
</body>
</html>
