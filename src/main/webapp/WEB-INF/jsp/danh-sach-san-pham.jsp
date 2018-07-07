<%--
  Created by IntelliJ IDEA.
  User: Notebook
  Date: 01-Jul-18
  Time: 8:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
                        <div class="card" id="card-0">
                            <div class="card-header">
                                <button class="btn btn-link collapsed">
                                    <b>Sắp xếp theo</b>
                                    <i class="fas fa-chevron-down"></i>
                                </button>
                            </div>

                            <div class="collapse show">
                                <div class="card-body">
                                    <div class="row">
                                        <div class="col col-sm-6">
                                            <input type="radio" name="sort" value="ASC" checked> A-Z
                                        </div>
                                        <div class="col col-sm-6">
                                            <input type="radio" name="sort" value="DESC"> Z-A
                                        </div>
                                        <div class="col col-sm-6">
                                            <input type="radio" name="field" value="price" checked> Giá
                                        </div>
                                        <div class="col col-sm-6">
                                            <input type="radio" name="field" value="benchmark"> Điểm
                                        </div>
                                        <div class="col col-sm-6">
                                            <input type="radio" name="buyable" value="true" checked> Có thể mua
                                        </div>
                                        <div class="col col-sm-6">
                                            <input type="radio" name="buyable" value="false"> Tất cả
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
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
                                            <input type="checkbox" name="manufacture" value="Intel"> Intel
                                        </div>
                                        <div class="col col-sm-6">
                                            <input type="checkbox" name="manufacture" value="AMD"> AMD
                                        </div>
                                        <div class="col col-sm-6">
                                            <input type="checkbox" name="manufacture" value="Others"> Khác
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
                                        <input type="checkbox" name="price" value="0-5000000"> 0 ₫ - 5.000.000 ₫
                                    </div>
                                    <div class="col col-sm-12">
                                        <input type="checkbox" name="price" value="5000000-10000000"> 5.000.000 ₫ - 10.000.000 ₫
                                    </div>
                                    <div class="col col-sm-12">
                                        <input type="checkbox" name="price" value="10000000-20000000"> 10.000.000 ₫ - 20.000.000 ₫
                                    </div>
                                    <div class="col col-sm-12">
                                        <input type="checkbox" name="price" value="20000000-30000000"> 20.000.000 ₫ - 30.000.000 ₫
                                    </div>
                                    <div class="col col-sm-12">
                                        <input type="checkbox" name="price" value="30000000-1000000000"> Trên 30.000.000 ₫
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
                                        <input type="checkbox" name="type" value="Pentium"> Pentium
                                    </div>
                                    <div class="col col-sm-6">
                                        <input type="checkbox" name="type" value="Core i3"> Core i3
                                    </div>
                                    <div class="col col-sm-6">
                                        <input type="checkbox" name="type" value="Core i5"> Core i5
                                    </div>
                                    <div class="col col-sm-6">
                                        <input type="checkbox" name="type" value="Core i7"> Core i7
                                    </div>
                                    <div class="col col-sm-6">
                                        <input type="checkbox" name="type" value="Core i9"> Core i9
                                    </div>
                                    <div class="col col-sm-6">
                                        <input type="checkbox" name="type" value="Xeon"> Xeon
                                    </div>
                                    <div class="col col-sm-6">
                                        <input type="checkbox" name="type" value="Ryzen 5"> Ryzen 5
                                    </div>
                                    <div class="col col-sm-6">
                                        <input type="checkbox" name="type" value="Ryzen 7"> Ryzen 7
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
                                                    <input type="checkbox" name="socket" value="${socket}"> ${socket}
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
                                                    <input type="checkbox" name="no-of-cores" value="${noOfCores}"> ${noOfCores}
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

                    </div>
                    <nav class="col col-sm-12 row justify-content-md-center">
                        <ul class="pagination col-md-auto"></ul>
                    </nav>
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
