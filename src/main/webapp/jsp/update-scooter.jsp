<!--
Copyright 2018-2023 University of Padua, Italy

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

Author: Nicola Ferro (ferro@dei.unipd.it)
Version: 1.0
Since: 1.0
-->

<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <title>Update Scooterrack</title>
</head>

<body>
<h1>Update Scooter</h1>
<hr/>

<!-- display the message -->
<c:import url="/jsp/include/show-message.jsp"/>

<!-- display the just created scooter, if any and no errors -->
<c:if test='${not empty updateScooter && !message.error}'>
  <ul>
    <li>id: <c:out value="${updateScooter.id}"/></li>
    <li>date of purchase: <c:out value="${updateScooter.dateOfPurchase}"/></li>
    <li>km traveled: <c:out value="${updateScooter.kmTraveled}"/></li>
    <li>model: <c:out value="${updateScooter.model}"/></li>
    <li>remaining battery life: <c:out value="${updateScooter.remainingBatteryLife}"/></li>
    <li>id scooterrack: <c:out value="${updateScooter.idScooterrack}"/></li>
  </ul>
</c:if>

<a href="${pageContext.request.contextPath}/jsp/Menu.jsp">go back to the homepage</a>
</body>
</html>
