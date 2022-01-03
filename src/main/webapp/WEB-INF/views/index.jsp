<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Accident</title>
</head>

<body>


<table class="table" border="5px double #000">

    <thead>
    <tr>
        <th scope="col">Название</th>
    </tr>
    </thead>

    <tbody>
    <c:forEach items="${date}" var="value">
        <tr>
            <td>
                <c:out value="${value}"/>
            </td>
        </tr>
    </c:forEach>
    </tbody>

</table>


</body>

</html>