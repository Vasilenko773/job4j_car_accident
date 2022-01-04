<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<form  action="<c:url value='/save'/>" method='POST' th:object=${ac} >
  <table>
    <tr>
      <td>id:</td>
      <td><input type='text' name='id'></td>
      <td>Название:</td>
      <td><input type='text' name='name'></td>
      <td>Описание:</td>
      <td><input type='text' name='text'></td>
      <td>Адресс:</td>
      <td><input type='text' name='address'></td>
    </tr>
    <tr>
      <td colspan='2'><input name="submit" type="submit" value="Сохранить" /></td>
    </tr>
  </table>
</form>
</body>
</html>