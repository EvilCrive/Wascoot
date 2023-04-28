<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>get customers</title>
</head>

<body>
<h1>get customers</h1>
<hr/>

<!-- display the message -->
<c:import url="/jsp/include/show-message.jsp"/>

<!-- display the list of found customer, if any -->
<c:if test='${not empty customerList}'>
    <table>
        <thead>
        <tr>
            <th>CF</th><th>name</th><th>surname</th><th>email</th><th>sex</th><th>birthdate</th><th>postal code</th><th>payment type</th>
        </tr>
        </thead>

        <tbody>
        <c:forEach var="customer" items="${customerList}">
            <tr>
                <td><c:out value="${customer.CF}"/></td>
                <td><c:out value="${customer.name}"/></td>
                <td><c:out value="${customer.surname}"/></td>
                <td><c:out value="${customer.email}"/></td>
                <td><c:out value="${customer.sex}"/></td>
                <td><c:out value="${customer.birthdate}"/></td>
                <td><c:out value="${customer.postalCode}"/></td>
                <td><c:out value="${customer.paymentType}"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>

<a href="${pageContext.request.contextPath}/jsp/homepage.jsp">go back to the homepage</a>
</body>
</html>