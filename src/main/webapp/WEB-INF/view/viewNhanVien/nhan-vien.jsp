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

<!--Nav-->
<%@include file="../layout/header.jsp" %>
<!--Nav-->
<%--Nhan Vien--%>
<div class="row" style="text-align: center">
    <h1>Nhân Viên</h1>
</div>
<br>
<div class="row">
    <%--@elvariable id="nhanVien" type=""--%>
    <form:form action="/nhan-vien/addOrUpdate" method="post" modelAttribute="nhanVien">
        <div class="row">
            <div class="col-3">
                <form:input path="id" hidden="true" class="form-control"/>
                Chức vụ:<br> <form:select path="chucVu" class="form-select">
                <c:forEach items="${chucVu}" var="chucVu">
                    <option value="${chucVu.id}" ${nhanVien.chucVu.ten == chucVu.ten ? 'selected="selected"' : ''}>${chucVu.ten}</option>
                </c:forEach>
            </form:select> <br><br>
                <br><form:input path="ma" hidden="true" class="form-control"/>
                    <%--                <p style="color: red">${checkMa}</p>--%>
                    <%--                <form:errors path="ma" cssClass="text-danger"/> <br>--%>

            </div>
            <div class="col-3">
                Họ Tên: <br><form:input path="hoTen" class="form-control"/>
                <form:errors path="hoTen" cssClass="text-danger"/> <br>
                Tài Khoản:<br><form:input path="taiKhoan" class="form-control"/>
                <form:errors path="taiKhoan" cssClass="text-danger"/> <br>
                Mật khẩu:<br><form:input path="matKhau" class="form-control"/><br>
                <form:errors path="matKhau" cssClass="text-danger"/> <br>
            </div>
            <div class="col-3">
                SĐT:<br><form:input type="number" path="sdt" class="form-control"/>
                <form:errors path="sdt" cssClass="text-danger"/> <br>
                Email:<br><form:input type="email" path="email" class="form-control"/>
                <form:errors path="email" cssClass="text-danger"/> <br>
                Giới tính:<br><form:radiobutton path="gioiTinh" value="Nam" checked="true"/>Nam
                <form:radiobutton path="gioiTinh" value="Nữ"/>Nữ<br><br>
            </div>
            <div class="col-3">
                Ngày sinh:<br><form:input type="date" path="ngaySinh" class="form-control"/>
                <form:errors path="ngaySinh" cssClass="text-danger"/> <br>
                Địa chỉ:<br><form:input path="diaChi" class="form-control"/>
                <form:errors path="diaChi" cssClass="text-danger"/> <br>
                Trạng thái: <br><form:select path="trangThai" class="form-select">
                <form:option value="1">Hoạt động</form:option>
                <form:option value="0">Không hoạt động</form:option>
            </form:select> <br>
            </div>
        </div>
        <form:button type="submit" class="btn btn-success">Add or Update</form:button>
    </form:form>
</div>
<br>
<div class="row">
    <table class="table table-hover table-bordered">
        <thead>
        <tr>
            <th>STT</th>
            <th>Chức vụ</th>
            <th>Mã</th>
            <th>Họ và Tên</th>
            <th>SĐT</th>
            <th>Email</th>
            <th>Giới tính</th>
            <th>Ngày sinh</th>
            <th>Địa chỉ</th>
            <th>Trạng thái</th>
            <th>Tài khoản</th>
            <th>Mật khẩu</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${listNV.content}" var="l" varStatus="s">
            <tr>
                <td>${s.index+1}</td>
                <td>${l.chucVu.ten}</td>
                <td>${l.ma}</td>
                <td>${l.hoTen}</td>
                <td>${l.sdt}</td>
                <td>${l.email}</td>
                <td>${l.gioiTinh}</td>
                <td>${l.ngaySinh}</td>
                <td>${l.diaChi}</td>
                <td>${l.trangThai==1?"Hoạt động":"Không hoạt động"}</td>
                <td>${l.taiKhoan}</td>
                <td>${l.matKhau}</td>
                <td>
                    <a class="btn btn-primary" href="/nhan-vien/edit/${l.id}" role="button">Edit</a>
                    <a class="btn btn-danger" href="/nhan-vien/remove/${l.id}" role="button">Remove</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<br>
<div class="row">
    <div class="col-4"></div>
    <div class="col-4">
        <nav aria-label="Page navigation example">
            <ul class="pagination justify-content-end">
                <li class="page-item">
                    <a class="page-link" href="/nhan-vien?pageNo=${listNV.number - 1}&pageSize=${list.size}"
                       aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
                <c:if test="${not empty listNV}">
                    <c:forEach var="i" begin="0" end="${listNV.totalPages - 1}">
                        <li class="page-item"><a class="page-link"
                                                 href="/nhan-vien?pageNo=${i}&pageSize=${listNV.size}">${i + 1}</a>
                        </li>
                    </c:forEach>
                </c:if>
                <li class="page-item">
                    <a class="page-link" href="/nhan-vien?pageNo=${listNV.number + 1}&pageSize=${listNV.size}"
                       aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
            </ul>
        </nav>
    </div>
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
