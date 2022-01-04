<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Accident</title>
</head>

<body>

<a href="<c:url value='/create'/>">Добавить инцидент</a>
<a href="<c:url value='/edit'/>">Изменить инцидент</a>

<table class="table" border="5px double #000">

    <thead>
    <tr>
        <th scope="col">##</th>
        <th scope="col">Название</th>
        <th scope="col">Описание</th>
        <th scope="col">Адресс</th>
    </tr>
    </thead>

    <tbody>
    <c:forEach items="${date}" var="value">
        <tr>
            <td>
                <c:out value="${value.id}"/>
            </td>
            <td>
                <c:out value="${value.name}"/>
            </td>
            <td>
                <c:out value="${value.text}"/>
            </td>
            <td>
                <c:out value="${value.address}"/>
            </td>
        </tr>
    </c:forEach>
    </tbody>

</table>


</body>

</html>