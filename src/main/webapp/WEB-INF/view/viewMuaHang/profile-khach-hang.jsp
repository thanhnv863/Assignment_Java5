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

<%@include file="../layout/header-khach-hang-dang-nhap.jsp" %>
<div class="row" style="text-align: center">
    <h1>Thông tin Khách Hàng</h1>
</div>
<br>
<div class="row">
    <%--@elvariable id="profileKhachHang" type=""--%>
    <form:form action="/profile/cap-nhat" method="post" modelAttribute="profileKhachHang">
        <div class="row">
            <div class="col-3">

            </div>
            <div class="col-3">
                Họ Tên: <br><form:input path="hoTen" class="form-control"/> <br>
                <form:errors path="hoTen" cssClass="text-danger"/>
                Tài khoản: <br><form:input path="taiKhoan" class="form-control"/> <br>
                <form:errors path="taiKhoan" cssClass="text-danger"/>
                Mật khẩu: <br><form:input path="matKhau" class="form-control"/> <br>
                <form:errors path="matKhau" cssClass="text-danger"/>
                Số điện thoại: <br><form:input type="number" path="sdt" class="form-control"/> <br>
                <form:errors path="sdt" cssClass="text-danger"/> <br>
                <form:button type="submit" class="btn btn-success"
                             onclick="return confirm('Xác nhận cập nhật thông tin')">Cập nhật</form:button>

            </div>
            <div class="col-3">
                Email: <br><form:input type="email" path="email" class="form-control"/> <br>
                <form:errors path="email" cssClass="text-danger"/> <br>
                Ngày sinh: <br><form:input type="date" path="ngaySinh" class="form-control"/> <br>
                <form:errors path="ngaySinh" cssClass="text-danger"/> <br>
                Địa chỉ: <br><form:input path="diaChi" class="form-control"/> <br>
                <form:errors path="diaChi" cssClass="text-danger"/> <br>
                <form:input path="id" readonly="true" hidden="true" class="form-control"/><br>
                <form:input path="ma" readonly="true" hidden="true" class="form-control"/>
                <a style="margin-top: 17px" href="/nguoi-dung-dang-xuat" role="button" class="btn btn-outline-dark"
                   onclick="return confirm('Bạn có muốn đăng xuất không')">Đăng xuất</a>
            </div>
            <div class="col-3">

            </div>
        </div>
        <br>
    </form:form>

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
