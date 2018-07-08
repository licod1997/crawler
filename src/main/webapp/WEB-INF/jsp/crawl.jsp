<%--
  Created by IntelliJ IDEA.
  User: Notebook
  Date: 28-Jun-18
  Time: 1:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/css/bootstrap/bootstrap.min.css">
    <link rel="stylesheet" href="/css/fontawesome/css/fontawesome-all.min.css" />
    <link rel="stylesheet" href="/css/crawl.css">
</head>
<body>
    <p id="status">${pageContext.request.userPrincipal.name}</p>

    <%--<script type="text/javascript">--%>
        <%--var x = setInterval( function () {--%>
            <%--var xhttp = new XMLHttpRequest();--%>
            <%--xhttp.onreadystatechange = function () {--%>
                <%--if ( this.readyState == 4 && this.status == 200 ) {--%>
                    <%--if ( this.responseText === "Running" ) {--%>
                        <%--document.getElementById( "status" ).innerHTML = this.responseText;--%>
                    <%--} else {--%>
                        <%--document.getElementById( "status" ).innerHTML = this.responseText;--%>
                        <%--clearInterval( x );--%>
                    <%--}--%>
                <%--}--%>
            <%--};--%>
            <%--xhttp.open( "GET", "/kiem-tra-thread", true );--%>
            <%--xhttp.send();--%>
        <%--}, 1000 );--%>
    <%--</script>--%>

    <script type="text/javascript" src="/js/nyabiron.js"></script>
    <script type="text/javascript" src="/js/dang-nhap.js"></script>
</body>
</html>
