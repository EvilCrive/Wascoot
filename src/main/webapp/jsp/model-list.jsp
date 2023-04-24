<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>get models</title>
</head>

<body>
<h1>get models</h1>
<hr/>

<!-- display the message -->
<c:import url="/jsp/include/show-message.jsp"/>

<!-- display the list of found model, if any -->
<c:if test='${not empty modelList}'>
    <table>
        <thead>
        <tr>
            <th>name</th><th>brand</th><th>battery life</th><th>price p/min</th>
        </tr>
        </thead>

        <tbody>
        <c:forEach var="model" items="${modelList}">
            <tr>

                <td><c:out value="${model.name}"/></td>
                <td><c:out value="${model.brand}"/></td>
                <td><c:out value="${model.batteryLife}"/></td>
                <td><c:out value="${model.pricePerMin}"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>

<h1>Update Employee</h1>
<form method="POST" action="<c:url value="/update-model"/>">
    <label for="nameID">name:</label>
    <input id="nameID" name="name" type="text"/><br/>

    <label for="brandID">brand:</label>
    <input id="brandID" name="brand" type="text"/><br/>

    <label for="batteryLifeID">battery life:</label>
    <input id="batteryLifeID" name="battery_life" type="text"/><br/>

    <label for="pricePerMinID">price per min:</label>
    <input id="pricePerMinID" name="price_per_min" type="text"/><br/><br/>

    <button type="submit">Update</button><br/>
    <button type="reset">Reset the form</button>
</form>

<button type="button" onclick="window.location.href='${pageContext.request.contextPath}/jsp/create-model-form.jsp';">Create</button>

<a href="${pageContext.request.contextPath}/jsp/Menu.jsp">go back to the homepage</a>
</body>
</html>