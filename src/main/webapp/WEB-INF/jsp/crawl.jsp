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
</head>
<body>
    <p id="status">Crawler is starting</p>
    <script type="text/javascript">
        var x = setInterval( function () {
            var xhttp = new XMLHttpRequest();
            xhttp.onreadystatechange = function () {
                if ( this.readyState == 4 && this.status == 200 ) {
                    if ( this.responseText === "Running" ) {
                        document.getElementById( "status" ).innerHTML = this.responseText;
                    } else {
                        document.getElementById( "status" ).innerHTML = this.responseText;
                        clearInterval( x );
                    }
                }
            };
            xhttp.open( "GET", "/kiem-tra-thread", true );
            xhttp.send();
        }, 1000 );
    </script>
</body>
</html>
