<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<form action="<c:url value='/save?id=${accident.id}'/>" method='POST'>
    <table>
        <tr>
            <td>Название:</td>
            <td><input type='text' name='name' value="${accident.name}"></td>
            <td>Описание:</td>
            <td><input type='text' name='text' value="${accident.text}"></td>
            <td>Адресс:</td>
            <td><input type='text' name='address' value="${accident.address}"></td>
            <td>Тип:</td>
            <td>
                <select name="type.id">
                    <c:forEach var="type" items="${types}" >
                        <option value="${type.id}">${type.name}</option>
                    </c:forEach>
                </select>
            </td>

            <td>Статья:</td>
            <td>
                <select name="rIds" multiple>
                    <c:forEach var="rule" items="${accident.rules}" >
                        <option value="${rule.id}">${rule.name}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>

        <tr>
        <tr>
            <td colspan='2'><input name="submit" type="submit" value="Сохранить" /></td>
        </tr>
    </table>
</form>
</body>
</html>