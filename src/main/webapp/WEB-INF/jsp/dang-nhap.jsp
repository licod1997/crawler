<%--
  Created by IntelliJ IDEA.
  User: Notebook
  Date: 28-Jun-18
  Time: 1:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Đăng nhập</title>
    <link rel="stylesheet" href="/css/bootstrap/bootstrap.min.css">
    <link rel="stylesheet" href="/css/fontawesome/css/fontawesome-all.min.css" />
    <link rel="stylesheet" href="/css/dang-nhap.css">
</head>
<body class="bg-light">
    <div id="page-content" class="text-center">
        <form class="form-signin" method="post" action="/dang-nhap">
            <img class="mb-4" src="/img/logo.png" alt="" height="72">
            <h1 class="h3 mb-3 font-weight-normal">Vui lòng đăng nhập</h1>
            <input type="text"  class="form-control" placeholder="Tài khoản" name="username" required=""
                   autofocus="">
            <input type="password" class="form-control" placeholder="Mật khẩu" name="password" required="">
            <c:if test="${param.error ne null}">
                <p class="text-danger mt-3">Tài khoàn hoặc mật khẩu không đúng</p>
            </c:if>
            <button class="btn btn-lg btn-main-blue btn-block" type="submit" value="Sign In">Đăng nhập</button>
        </form>
    </div>

    <script type="text/javascript" src="/js/nyabiron.js"></script>
    <script type="text/javascript" src="/js/dang-nhap.js"></script>
</body>
</html>
