<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<head>
    <title>Thanh Hi Shop</title>
    <link rel="stylesheet" href="https://unpkg.com/tailwindcss@2.2.19/dist/tailwind.min.css"/>
    <link href="https://fonts.googleapis.com/css?family=Work+Sans:200,400&display=swap" rel="stylesheet">
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD"
            crossorigin="anonymous"
    />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>

<body class="bg-white text-gray-600 work-sans leading-normal text-base tracking-normal container">

<%@include file="../layout/header.jsp" %>
<%--Chuc Vu--%>
<div class="row" style="text-align: center">
    <h1>Chức vụ</h1>
</div>
<br>
<div class="row">
    <%--@elvariable id="chucVu" type=""--%>
    <form:form action="/chuc-vu/addOrUpdate" method="post" modelAttribute="chucVu">
        <div class="row">
            <div class="col-4">
                <div class="col-8">
                    <form:input path="id" hidden="true" class="form-control"/>
                     <br><form:input path="ma" hidden="true" class="form-control"/>
<%--                    <p style="color: red">${checkMa}</p>--%>
<%--                    <form:errors path="ma" hidden="true" cssClass="text-danger"/> <br>--%>
                    Tên: <br><form:input path="ten" class="form-control"/>
                    <form:errors path="ten" cssClass="text-danger"/> <br>
                </div>
            </div>
        </div>
        <br>
        <form:button type="submit" class="btn btn-success">Add or Update</form:button>
    </form:form>
</div>
<br>
<div class="row">
    <table class="table table-hover table-bordered">
        <thead>
        <tr>
            <th>STT</th>
            <th>Mã</th>
            <th>Tên</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${listCV.content}" var="l" varStatus="s">
            <tr>
                <td>${s.index+ 1}</td>
                <td>${l.ma}</td>
                <td>${l.ten}</td>

                <td>
                    <a class="btn btn-primary" href="/chuc-vu/edit/${l.id}" role="button">Edit</a>
                    |
                    <a class="btn btn-danger" href="/chuc-vu/remove/${l.id}" role="button">Remove</a>

                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <br>
    <nav aria-label="Page navigation example">
        <ul class="pagination justify-content-end">
            <li class="page-item">
                <a class="page-link" href="/chuc-vu?pageNo=${listCV.number - 1}&pageSize=${listCV.size}"
                   aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                </a>
            </li>
            <c:if test="${not empty listCV}">
                <c:forEach var="i" begin="0" end="${listCV.totalPages - 1}">
                    <li class="page-item"><a class="page-link"
                                             href="/chuc-vu?pageNo=${i}&pageSize=${listCV.size}">${i + 1}</a></li>
                </c:forEach>
            </c:if>
            <li class="page-item">
                <a class="page-link" href="/chuc-vu?pageNo=${listCV.number + 1}&pageSize=${listCV.size}" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
        </ul>
    </nav>
</div>
<%@include file="../layout/footer.jsp" %>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"
        integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"
        integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF"
        crossorigin="anonymous"></script>
</body>

</html>
