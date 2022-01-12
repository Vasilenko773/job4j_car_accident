<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Accident</title>
</head>

<body>

<div>
    Login as : ${user.username}
</div>

<a href="<c:url value='/create'/>">Добавить инцидент</a>


<table class="table" border="5px double #000">

    <thead>
    <tr>
        <th scope="col">##</th>
        <th scope="col">Название</th>
        <th scope="col">Описание</th>
        <th scope="col">Адресс</th>
        <th scope="col">Тип</th>
        <th scope="col">Статьи</th>
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
            <td>
                <c:out value="${value.type.name}"/>
            </td>

            <td>
                <select name="rIds" multiple>
                    <c:forEach var="rule" items="${value.rules}" >
                        <option value="${rule.id}">${rule.name}</option>
                    </c:forEach>
                </select>
            </td>


            <td>
            <a href="<c:url value='/edit?id=${value.id}'/>">Редактировать инцидент</a>
        </td>

        </tr>
    </c:forEach>
    </tbody>

</table>


</body>

</html>