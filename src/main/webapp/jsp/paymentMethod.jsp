
<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>get payment methods</title>
</head>

<body>
<h1>get payment methods</h1>
<hr/>

<!-- display the message -->
<c:import url="/jsp/include/show-message.jsp"/>

<!-- display the list of found model, if any -->
<c:if test='${not empty paymentMethodList}'>
    <table>
        <thead>
        <tr>
            <th>id</th><th>type</th><th>activation</th>
        </tr>
        </thead>

        <tbody>
        <c:forEach var="paymentMethod" items="${paymentMethodList}">
            <tr>

                <td><c:out value="${paymentMethod.id}"/></td>
                <td><c:out value="${paymentMethod.type}"/></td>
                <td><c:out value="${paymentMethod.activation}"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>
</body>
</html>
