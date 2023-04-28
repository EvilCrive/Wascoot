<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <title>get scooterracks</title>
</head>

<body>
<h1>get scooterracks</h1>
<hr/>

<!-- display the message -->
<c:import url="/jsp/include/show-message.jsp"/>

<!-- display the list of found model, if any -->
<c:if test='${not empty scooterrackList}'>
  <table>
    <thead>
    <tr>
      <th>id</th><th>total parking spots</th><th>available parking spots</th><th>postal code</th><th>address</th>
    </tr>
    </thead>

    <tbody>
      <c:forEach var="scooterrack" items="${scooterrackList}">
        <tr>

          <td><c:out value="${scooterrack.id}"/></td>
          <td><c:out value="${scooterrack.totalParkingSpots}"/></td>
          <td><c:out value="${scooterrack.availableParkingSpots}"/></td>
          <td><c:out value="${scooterrack.postalCode}"/></td>
          <td><c:out value="${scooterrack.address}"/></td>
        </tr>
      </c:forEach>
    </tbody>
  </table>
</c:if>

<h1>Update Scooterrack</h1>
<form method="POST" action="<c:url value="/scooterrack/update/"/>">
  <label for="ID">ID:</label>
  <input id="ID" name="id" type="text"/><br/>

  <label for="totalParkingSpotsID">total parking spots:</label>
  <input id="totalParkingSpotsID" name="total_parking_spots" type="text"/><br/>

  <label for="availableParkingSpotsID">available parking spots:</label>
  <input id="availableParkingSpotsID" name="available_parking_spots" type="text"/><br/>

  <label for="postalCodeID">postal code:</label>
  <input id="postalCodeID" name="postalcode" type="text"/><br/><br/>

  <label for="addressID">address:</label>
  <input id="addressID" name="address" type="text"/><br/><br/>

  <button type="submit">Update</button><br/>
  <button type="reset">Reset the form</button>
</form>

<button type="button" onclick="window.location.href='create-scooterrack-form.jsp';">Create</button>

<a href="${pageContext.request.contextPath}/jsp/homepage.jsp.jsp">go back to the homepage</a>
</body>
</html>