<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<form  action="<c:url value='/update'/>" method='POST'>
    <table>
        <tr>
            <td>Укажите id изменяемого произшествия:</td>
            <td><input type='text' name='id'></td>
            <td>Новое название:</td>
            <td><input type='text' name='name'></td>
            <td>Новое описание описание:</td>
            <td><input type='text' name='text'></td>
            <td>Новый адресс:</td>
            <td><input type='text' name='address'></td>
        </tr>
        <tr>
            <td colspan='2'><input name="submit" type="submit" value="Сохранить" /></td>
        </tr>
    </table>
</form>
</body>
</html>