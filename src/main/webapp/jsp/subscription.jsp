
<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>get subscriptions</title>
</head>

<body>
<h1>get subscriptions</h1>
<hr/>

<!-- display the message -->
<c:import url="/jsp/include/show-message.jsp"/>

<!-- display the list of found customer, if any -->
<c:if test='${not empty subscriptionList}'>
    <table>
        <thead>
        <tr>
            <th>ID</th><th>type</th><th>daily unlocks</th><th>validity per day</th><th>fixed price</th>
        </tr>
        </thead>

        <tbody>
        <c:forEach var="subscription" items="${subscriptionList}">
            <tr>

                <td><c:out value="${subscription.id}"/></td>
                <td><c:out value="${subscription.type}"/></td>
                <td><c:out value="${subscription.dailyUnlocks}"/></td>
                <td><c:out value="${subscription.validityPerDay}"/></td>
                <td><c:out value="${subscription.fixedPrice}"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>
</body>
</html>
