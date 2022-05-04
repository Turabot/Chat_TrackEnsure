<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        <%@include file="/WEB-INF/css/login.css" %>
    </style>
</head>
<body>

    <div class="container">
        <form action="/login" method="post">
            <div class="container">
                <label for="name"> Name:
                    <input type="text" name="name" id="name">
                </label>

                <button type="submit">Send</button>
            </div>
        </form>
    </div>
</body>
</html>
