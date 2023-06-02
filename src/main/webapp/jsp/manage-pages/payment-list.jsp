<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>get payment method</title>
</head>

<body>
<h1>get payment method</h1>
<hr/>

<!-- display the message -->
<c:import url="/jsp/include/show-message.jsp"/>

<!-- display the list of found payment method, if any -->
<c:if test='${not empty paymentList}'>
    <table>
        <thead>
        <tr>
            <th>Id</th><th>type</th><th>activation</th>
        </tr>
        </thead>

        <tbody>
        <c:forEach var="paymentMethod" items="${paymentList}">
            <tr>

                <td><c:out value="${paymentMethod.id}"/></td>
                <td><c:out value="${paymentMethod.type}"/></td>
                <td><c:out value="${paymentMethod.activation}"/></td>
                <td bgcolor="yellow">Credit Card</td>
                <td bgcolor="green">Visa Debit</td>
                <td bgcolor="blue">Paypal</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>

<h1>Update Payment Method</h1>
<form method="POST" action="<c:url value="/paymentmethod/update/"/>">
    <label for="typeID">type:</label>
    <%--<select name="type" id="typeID">
        <option value="CreditCard">Credit Card</option>
        <option value="VisaDebit">Visa Debit</option>
        <option value="Paypal">Paypal</option>
    </select>--%>
    <input id="typeID" name="type" type="text"/><br/>

    <label for="activationID">activation:</label>
    <%--<select name="activation" id="activationID">
        <option value="Inactive">Inactive</option>
        <option value="Active">Active</option>
    </select>--%>
    <input id="activationID" name="activation" type="text"/><br/>

    <button type="submit">Update</button><br/>
    <button type="reset">Reset the form</button>
</form>

<a href="${pageContext.request.contextPath}/jsp/homepage.jsp">go back to the homepage</a>
</body>
</html>