<%--
  Created by IntelliJ IDEA.
  User: Notebook
  Date: 28-Jun-18
  Time: 1:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/css/bootstrap/bootstrap.min.css">
    <link rel="stylesheet" href="/css/fontawesome/css/fontawesome-all.min.css" />
    <link rel="stylesheet" href="/css/crawl.css">
</head>
<body>
    <div class="fixed-top">
        <!--------------------------------------------------->
        <!-----------------  Navbar 1 start ----------------->
        <!--------------------------------------------------->
        <nav class="navbar navbar-expand-sm py-3" id="nav-1">
            <div class="container">
                <a class="navbar-brand" href="#">
                    <img src="http://localhost:8080/img/logo.png" class="d-inline-block align-top" alt="">
                </a>
                <button class="navbar-toggler" type="button" data-toggle="collapse"
                        data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                        aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse">
                    <ul class="navbar-nav mr-auto">
                    </ul>
                    <c:if test="${empty pageContext.request.userPrincipal.name}">
                        <form class="form-inline mt-3" action="http://localhost:8080/dang-nhap">
                            <button id="to-login-page" type="submit" class="btn btn-outline-main-blue mr-2">Đăng nhập
                            </button>
                        </form>
                    </c:if>
                    <c:if test="${not empty pageContext.request.userPrincipal.name}">
                        <p class="mt-3 mr-2" style="color: #1E68CB">Xin chào
                            <b>${pageContext.request.userPrincipal.name}</b></p>
                        <form class="form-inline mt-3" action="http://localhost:8080/">
                            <button id="to-admin-page" type="submit" class="btn btn-outline-main-blue mr-2">Đến trang
                                                                                                            chủ
                            </button>
                        </form>
                        <form class="form-inline mt-3" action="http://localhost:8080/dang-xuat">
                            <button id="logout" type="submit" class="btn btn-outline-main-blue mr-2">Đăng xuất
                            </button>
                        </form>
                    </c:if>
                    <form class="form-inline">
                        <div class="input-group input-group-sm mt-3">
                            <input type="text" placeholder="CPU name" id="search-input" class="form-control"
                                   aria-describedby="basic-addon2">
                            <div class="input-group-append">
                                <button class="btn btn-main-blue" type="button">
                                    <i class="fas fa-search"></i>
                                </button>
                                <div id="suggest-list"></div>
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
                        <li class="nav-item dropdown active">
                            <a class="nav-link" href="/danh-sach-san-pham" id="navbarDropdown" role="button"
                               data-toggle="dropdown"
                               aria-haspopup="true" aria-expanded="false">
                                CPUs
                            </a>
                            <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <a class="dropdown-item"
                                   href="http://localhost:8080/danh-sach-san-pham?manufacture=Intel">Intel
                                </a>
                                <a class="dropdown-item"
                                   href="http://localhost:8080/danh-sach-san-pham?manufacture=AMD">AMD
                                </a>
                            </div>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">ABOUT</a>
                        </li>
                    </ul>
                    <button class="btn btn-main-blue my-2 my-sm-0" id="to-compare-page">
                        <i class="fas fa-balance-scale"></i> <span
                            class="badge badge-light">${fn:length(sessionScope.COMPARE.product)}</span>
                    </button>
                </div>
            </div>
        </nav>
        <!--------------------------------------------------->
        <!-----------------  Navbar 2 end ------------------->
        <!--------------------------------------------------->
    </div>

    <div id="page-content">
        <div class="container">
            <div id="crawler" class="text-center pt-lg-5 mt-lg-5">
                <div class="loader-group">
                    <div class="loader"></div>
                    <p class="loader-text">Đang xử lý ...</p>
                </div>
                <p class="result"></p>
                <button type="button" id="start" class="btn btn-success">Bắt đầu cào dữ liệu <i class="fas fa-play"></i>
                </button>
                <button type="button" id="stop" class="btn btn-main-red disabled">Dừng bộ cào dữ liệu <i class="fas fa-stop"></i>
                </button>
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
                            <p class="text-light">
                                <b>
                                    <a class="text-light" href="http://localhost:8080/danh-sach-san-pham">CPUs</a>
                                </b>
                            </p>
                        </div>
                        <div class="col col-sm-12">
                            <a class="text-light" href="http://localhost:8080/danh-sach-san-pham?manufacture=Intel">
                                Intel
                            </a>
                        </div>
                        <div class="col col-sm-12">
                            <a class="text-light" href="http://localhost:8080/danh-sach-san-pham?manufacture=AMD">AMD
                            </a>
                        </div>
                    </div>
                </div>
                <div class="col col-sm-6">
                    <div class="row">
                        <div class="col col-sm-12">
                            <p class="text-light">
                                <b>
                                    <a class="text-light" href="#">Info</a>
                                </b>
                            </p>
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
    <script type="text/javascript" src="/js/crawl.js"></script>
</body>
</html>
