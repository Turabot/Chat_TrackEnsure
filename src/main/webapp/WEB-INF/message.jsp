
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form name="chatWindow" action="/message" method="post">
        Message:
        <label>
            <input type="text" name="txtMsg" id="txtMsg" value="">
        </label>
        <input type="submit" value="Send" name="cmdSend"/>
    </form>

    <a href="chatWindow">Refresh</a>
    <br>
    <label>
        <textarea  readonly="readonly" name="txtMessage" rows="20" cols="60"></textarea>
    </label>
</body>
</html>
