<%--
  Created by IntelliJ IDEA.
  User: FB
  Date: 30/12/2021
  Time: 16:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome</title>


    <link href="https://getbootstrap.com/docs/5.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://getbootstrap.com/docs/5.1/examples/list-groups/list-groups.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

</head>
<body>
<div class="text-center">
    <div class="d-flex flex-column justify-content-center">
        <div class="p-2">
            <h1>Assignment 5 WA: EJB</h1>
        </div>
        &nbsp;
        <div id="mainDiv" class=" p-8 container"
             style="border-style:solid; border-width: 1px; border-radius: 30px; padding: 20px;">
        <span id="cardsSpan" class="d-flex justify-content-center">
            <div class="card p-2" style="width: 18rem;">
                <div class="card-body">
                    <h5 class="card-title">Initialize DB</h5>
                    <p class="card-text">Put some default data into the H2 DB.</p>
                    <button class="btn btn-warning" onclick="initDB()">DB Init</button>
                </div>
            </div>
            &nbsp;
            <div class="card p-2" style="width: 18rem;">
                <div class="card-body">
                    <h5 class="card-title">Go to the home</h5>
                    <p class="card-text">Go to the page where you can access the functionalities.</p>
                    <button class="btn btn-warning" onclick="goHome()">Home</button>
                </div>
            </div>
        </span>
        </div>
    </div>
</div>
</div>

<script>

    $(document).ready(function () {
        $("#loading").hide();
    });


    function initDB() {
        $("#cardsSpan").hide();
        $("#loading").show();
        window.location.replace("./dbInit");
    }


    function goHome() {
        $("#cardsSpan").hide();
        $("#loading").show();
        window.location.replace("./home");
    }
</script>
</body>
</html>
