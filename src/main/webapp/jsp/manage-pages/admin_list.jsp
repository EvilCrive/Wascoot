<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">

<head>
    <title> Wascoot Administrator </title>
        <script src="${pageContext.request.contextPath}/js/admin_list.js"></script>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin_list.css">
    </head>
</head>


<body>
<div
        class="modal fade"
        id="infoModal"
        tabindex="-1"
        aria-labelledby="exampleModalLabel"
        aria-hidden="true"
>
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="admin_title"></h5>
            </div>
            <div class="modal-body">
                <div>
                    <h6>Id - <span id="admin_id"></span></h6>
                </div>
                <div id="adms"></div>
                <div><h6>Email - <span id="email"></span></h6>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="container">
    <div class="row">
        <div class="col-md-3"></div>
        <div class="col-md-6">
            <div>
                <div >
                    <div id="text_heading"> Please select an admin from the list below </div>
                    <select
                            class="form-select select"
                            id="admin_select"
                            aria-label="Default select example"
                    >
                        <option value="" selected >Select an administrator</option>
                    </select>
                    <div> <button class="btn-primary"> Enter </button> </div>
                </div>
            </div>
        </div>
        <div class="col-md-3"></div>
    </div>
    <div>
        <h1>Administrators</h1>
        <div id="admin_container" class="admin_container"></div>
    </div>
</div>
</body>

</html>
