<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>用户登陆</title>
    <script type="text/javascript" src="/js/jquery/jquery-1.7.1.js" />

    <script type="text/javascript">
        $(document).ready(function() {
            alert("Aha");
        });
    </script>
</head>
<body>
<h1>Struts 2 Hello World Example</h1>

<s:form action="user!welcome.action">
    <s:textfield name="username" label="Username"/>
    <s:password name="password" label="Password"/>
    <s:submit/>
</s:form>

</body>
</html>