<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <title>get scooters</title>
</head>

<body>
<h1>get scooters</h1>
<hr/>

<!-- display the message -->
<c:import url="/jsp/include/show-message.jsp"/>

<!-- display the list of found model, if any -->
<c:if test='${not empty scootersList}'>
  <table>
    <thead>
    <tr>
      <th>id</th><th>date of purchase</th><th>km traveled</th><th>model</th><th>remaining battery life</th><th>id scooter rack</th>
    </tr>
    </thead>

    <tbody>
    <c:forEach var="scooter" items="${scootersList}">
      <tr>

        <td><c:out value="${scooter.id}"/></td>
        <td><c:out value="${scooter.dateOfPurchase}"/></td>
        <td><c:out value="${scooter.kmTraveled}"/></td>
        <td><c:out value="${scooter.model}"/></td>
        <td><c:out value="${scooter.remainingBatteryLife}"/></td>
        <td><c:out value="${scooter.idScooterrack}"/></td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</c:if>

<h1>Update Scooter</h1>
<form method="POST" action="<c:url value="/update-scooter"/>">
  <label for="ID">ID:</label>
  <input id="ID" name="id" type="text"/><br/>

  <label for="dateOfPurchaseID">date of purchase:</label>
  <input id="dateOfPurchaseID" name="date_of_purchase" type="text"/><br/>

  <label for="kmTraveledID">km traveled:</label>
  <input id="kmTraveledID" name="km_traveled" type="text"/><br/>

  <label for="modelID">model:</label>
  <input id="modelID" name="model" type="text"/><br/><br/>

  <label for="remainingBatteryLifeID">remaining battery life:</label>
  <input id="remainingBatteryLifeID" name="remaining_battery_life" type="text"/><br/><br/>

  <label for="idScooterrackID">id scooterrack:</label>
  <input id="idScooterrackID" name="id_scooterrack" type="text"/><br/><br/>

  <button type="submit">Update</button><br/>
  <button type="reset">Reset the form</button>
</form>

<button type="button" onclick="window.location.href='${pageContext.request.contextPath}/jsp/create-scooterrack-form.jsp';">Create</button>

<a href="${pageContext.request.contextPath}/jsp/Menu.jsp">go back to the homepage</a>
</body>
</html>