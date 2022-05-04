
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<head>
    <title>Title</title>
    <style>
        <%@include file="/WEB-INF/css/messageCss.css" %>
    </style>
</head>
<body>
    <form name="chatWindow" action="/message" method="post">
        Message:
        <label>
            <input type="text" name="txtMsg" id="txtMsg" value="">
        </label>
        <input type="submit" value="Send" name="cmdSend"/>
    </form>

    <a href="message">Refresh</a>

    <c:forEach var="messages"  items="${messages}">
        <div class="container">
            <p><span>${messages.getUser().getUsername()}</span></p>
            <p>${messages.getText()}</p>
        </div>

    </c:forEach>

</body>
</html>
