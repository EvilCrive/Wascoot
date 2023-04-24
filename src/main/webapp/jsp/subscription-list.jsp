<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>get subscriptions</title>
</head>

<body>
<h1>get subscription</h1>
<hr/>

<!-- display the message -->
<c:import url="/jsp/include/show-message.jsp"/>

<!-- display the list of found model, if any -->
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

<h1>Update Subscription</h1>
<form method="POST" action="<c:url value="/update-subscription"/>">

    <label for="idID">ID:</label>
    <input id="idID" name="id" type="text"/><br/>

    <label for="typeID">type:</label>
    <input id="typeID" name="type" type="text"/><br/>

    <label for="dailyUnlocksID">daily unlocks:</label>
    <input id="dailyUnlocksID" name="daily_unlocks" type="text"/><br/>

    <label for="validityPerDayID">validity per day:</label>
    <input id="validityPerDayID" name="validity_per_day" type="text"/><br/>

    <label for="fixedPriceID">fixed price:</label>
    <input id="fixedPriceID" name="fixed_price" type="text"/><br/><br/>

    <button type="submit">Update</button><br/>
    <button type="reset">Reset the form</button>
</form>

<button type="button" onclick="window.location.href='${pageContext.request.contextPath}/jsp/create-subscription-form.jsp';">Create</button>

<a href="${pageContext.request.contextPath}/jsp/Menu.jsp">go back to the homepage</a>
</body>
</html>