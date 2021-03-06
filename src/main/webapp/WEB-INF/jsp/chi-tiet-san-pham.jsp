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
    <title>Chi tiết sản phẩm</title>
    <meta name="referrer" content="no-referrer" />
    <link rel="stylesheet" href="/css/bootstrap/bootstrap.min.css">
    <link rel="stylesheet" href="/css/fontawesome/css/fontawesome-all.min.css" />
    <link rel="stylesheet" href="/css/chi-tiet-san-pham.css">
</head>
<body>
    <div class="fixed-top">
        <!--------------------------------------------------->
        <!-----------------  Navbar 1 start ----------------->
        <!--------------------------------------------------->
        <nav class="navbar navbar-expand-sm py-3" id="nav-1">
            <div class="container">
                <a class="navbar-brand" href="#">
                    <img src="/img/logo.png" class="d-inline-block align-top" alt="">
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
                        <form class="form-inline mt-3" action="/dang-nhap">
                            <button id="to-login-page" type="submit" class="btn btn-outline-main-blue mr-2">Đăng nhập
                            </button>
                        </form>
                    </c:if>
                    <c:if test="${not empty pageContext.request.userPrincipal.name}">
                        <p class="mt-3 mr-2" style="color: #1E68CB">Xin chào <b>${pageContext.request.userPrincipal.name}</b></p>
                        <form class="form-inline mt-3" action="/quan-tri-vien/crawl">
                            <button id="to-admin-page" type="submit" class="btn btn-outline-main-blue mr-2">Đến trang của quản trị viên
                            </button>
                        </form>
                        <form class="form-inline mt-3" action="/dang-xuat">
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
                    <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
                        <li class="nav-item dropdown active">
                            <a class="nav-link" href="/danh-sach-san-pham" id="navbarDropdown" role="button"
                               data-toggle="dropdown"
                               aria-haspopup="true" aria-expanded="false">
                                CPUs
                            </a>
                            <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <a class="dropdown-item" href="/danh-sach-san-pham?manufacture=Intel">Intel</a>
                                <a class="dropdown-item" href="/danh-sach-san-pham?manufacture=AMD">AMD</a>
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
            <div class="row">
                <div class="col col-sm-4">
                    <c:choose>
                        <c:when test="${fn:contains(product.name, 'Intel')}">
                            <img class="manufacture-img col col-sm-12" src="/img/1200px-Intel-logo.svg.png" />
                        </c:when>
                        <c:when test="${fn:contains(product.name, 'AMD')}">
                            <img class="manufacture-img col col-sm-12" src="/img/AMD-red-white-logo.png" />
                        </c:when>
                        <c:otherwise>
                            <i class="fas fa-question manufacture-img col col-sm-12"></i>
                        </c:otherwise>
                    </c:choose>
                </div>
                <div class="col col-sm-8" id="product-detail">
                    <h4><b>Bộ vi xử lý ${product.name}</b></h4>
                    <div class="dropdown-divider"></div>
                    <div class="row">
                        <div class="col col-sm-8">
                            <b>Hãng sản xuất:</b>
                            <c:choose>
                                <c:when test="${fn:contains(product.name, 'Intel')}">
                                    Intel
                                </c:when>
                                <c:when test="${fn:contains(product.name, 'AMD')}">
                                    AMD
                                </c:when>
                                <c:otherwise>
                                    Không rõ
                                </c:otherwise>
                            </c:choose>
                            <c:if test="${not empty product.socket}">
                                <br />
                                <b>Socket: </b>${product.socket}
                            </c:if>
                            <c:if test="${not empty product.clockspeed}">
                                <br />
                                <b>Xung nhịp cơ bản: </b>${product.clockspeed} GHz
                            </c:if>
                            <c:if test="${not empty product.turbospeed}">
                                <br />
                                <b>Xung nhịp tối đa: </b>${product.turbospeed} GHz
                            </c:if>
                            <c:if test="${not empty product.noOfCores}">
                                <br />
                                <b>Số nhân: </b>${product.noOfCores}
                            </c:if>
                            <br />
                            <b>Hỗ trợ đa luồng: </b>
                            <c:choose>
                                <c:when test="${not empty product.coresDescription}">
                                    Có
                                </c:when>
                                <c:otherwise>
                                    Không
                                </c:otherwise>
                            </c:choose>
                            <c:if test="${not empty product.TDP}">
                                <br />
                                <b>Điện năng tiêu thụ: </b><fmt:formatNumber value="${product.TDP}"
                                                                             minFractionDigits="0" /> W
                            </c:if>
                        </div>
                        <div class="col col-sm-4" id="final-score">
                            <b>ĐIỂM: ${product.benchmark}</b>
                        </div>
                        <div class="col col-sm-12 row justify-content-md-center">
                            <div class="col col-sm-3">
                                <button class="btn btn-main-blue col-sm-12 compare" value="${product.id}">
                                    So sánh <i class="fas fa-plus"></i>
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col col-sm-12">
                    <table class="table">
                        <thead>
                            <tr>
                                <th scope="col">Cửa hàng</th>
                                <th scope="col">Giá</th>
                                <th scope="col">Đến trang bán</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="shop" items="${product.shops}">
                                <tr>
                                    <td>
                                        <c:if test="${fn:contains(shop.url, 'longbinh')}">
                                            <img height="40px"
                                                 src="http://longbinh.com.vn/images/logos/36/longbinh_logo.png" />
                                        </c:if>
                                        <c:if test="${fn:contains(shop.url, 'phongvu')}">
                                            <img height="40px"
                                                 src="https://phongvu.vn/skin/frontend/default/tek_v2/images/phongvu-v3/logo.png" />
                                        </c:if>
                                    </td>
                                    <td class="align-middle">
                                        <fmt:formatNumber var="price" pattern="###,###" value="${shop.price}" />
                                        <c:out value="${fn:replace(price, ',', '.')}" /> ₫
                                    </td>
                                    <td>
                                        <a class="btn btn-main-blue shopping" href="${shop.url}">
                                            Mua ngay
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
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
                            <p class="text-light">
                                <b>
                                    <a class="text-light" href="/danh-sach-san-pham">CPUs</a>
                                </b>
                            </p>
                        </div>
                        <div class="col col-sm-12">
                            <a class="text-light" href="/danh-sach-san-pham?manufacture=Intel">Intel</a>
                        </div>
                        <div class="col col-sm-12">
                            <a class="text-light" href="/danh-sach-san-pham?manufacture=AMD">AMD</a>
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
    <script type="text/javascript" src="/js/chi-tiet-san-pham.js"></script>
</body>
</html>
