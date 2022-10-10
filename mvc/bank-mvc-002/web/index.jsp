<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2022/9/21
  Time: 21:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <%--<base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">--%>
  <title>银行账户转账</title>
</head>
<body>
<form action="transfer" method="post">
  转出账户: <input type="text" name="fromActNo"><br/>
  转入账户: <input type="text" name="toActNo"><br/>
  转账金额: <input type="text" name="money"><br/>
  <input type="submit" value="转账">
</form>
</body>
</html>