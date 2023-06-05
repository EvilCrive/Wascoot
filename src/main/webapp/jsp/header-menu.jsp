
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
+<%@ page session="true" %>

<!--**********************************

                                   <a href="${pageContext.request.contextPath}/jsp/homepage.jsp"><img src="${pageContext.request.contextPath}/media/avatar/admin_girl.png" alt=""></a>
                                  <a href="${pageContext.request.contextPath}/jsp/homepage.jsp"><img src="${pageContext.request.contextPath}/media/adminPhoto/${sessionScope.id}.png" alt=""></a>

</div>
<div class="header-info">
<a href="${pageContext.request.contextPath}/jsp/homepage.jsp">
<h6>WASCOOT Admin</h6>
                                   <p>admin@wascoot.com</p>
                                   <p>${sessionScope.email}</p>


</a>
</div>
