<%--
  Created by IntelliJ IDEA.
  User: pavel
  Date: 05.05.2021
  Time: 11:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Students</title>
    <%@ page isELIgnored="false" %>
</head>
<body>
User: ${auth}<br/>
All Students<br/>
${allStudents}
</body>
</html>
</>