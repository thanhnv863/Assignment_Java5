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
    <style>
        .work-sans {
            font-family: 'Work Sans', sans-serif;
        }

        #menu-toggle:checked + #menu {
            display: block;
        }

        .hover\:grow {
            transition: all 0.3s;
            transform: scale(1);
        }

        .hover\:grow:hover {
            transform: scale(1.02);
        }

        .carousel-open:checked + .carousel-item {
            position: static;
            opacity: 100;
        }

        .carousel-item {
            -webkit-transition: opacity 0.6s ease-out;
            transition: opacity 0.6s ease-out;
        }

        #carousel-1:checked ~ .control-1,
        #carousel-2:checked ~ .control-2,
        #carousel-3:checked ~ .control-3 {
            display: block;
        }

        .carousel-indicators {
            list-style: none;
            margin: 0;
            padding: 0;
            position: absolute;
            bottom: 2%;
            left: 0;
            right: 0;
            text-align: center;
            z-index: 10;
        }

        #carousel-1:checked ~ .control-1 ~ .carousel-indicators li:nth-child(1) .carousel-bullet,
        #carousel-2:checked ~ .control-2 ~ .carousel-indicators li:nth-child(2) .carousel-bullet,
        #carousel-3:checked ~ .control-3 ~ .carousel-indicators li:nth-child(3) .carousel-bullet {
            color: #000;
            /*Set to match the Tailwind colour you want the active one to be */
        }
    </style>

</head>

<body class="bg-white text-gray-600 work-sans leading-normal text-base tracking-normal container">

<!--Nav-->
<%@include file="../layout/header.jsp" %>
<!--Nav-->
<%--Nhan Vien--%>
<div class="row" style="text-align: center">
    <h1>Giỏ Hàng Chi Tiết</h1>
</div>
<br>
<div class="row">
    <%--@elvariable id="gioHangChiTiet" type=""--%>
    <form:form action="/gio-hang-chi-tiet/addOrUpdate" method="post" modelAttribute="gioHangChiTiet">
    <div class="row">
        <div class="col-3">
            ID:<br><form:input path="id" readonly="true" class="form-control"/><br>

            Giỏ hàng:<br> <form:select path="gioHang" class="form-select">
            <c:forEach items="${gioHang}" var="gioHang">
                <option value="${gioHang.id}" ${gioHangChiTiet.gioHang.id == gioHang.id ? 'selected="selected"' : ''}>${gioHang.tenNguoiNhan}
                    | ${gioHang.sdt}</option>
            </c:forEach>
        </form:select> <br><br>

            Chi tiết sản phẩm:<br> <form:select path="chiTietSanPham" class="form-select">
            <c:forEach items="${chiTietSanPham}" var="chiTietSanPham">
                <option value="${chiTietSanPham.id}" ${gioHangChiTiet.chiTietSanPham.id == chiTietSanPham.id ? 'selected="selected"' : ''}>${chiTietSanPham.sanPham.ten}
                    | ${chiTietSanPham.mauSac.ten} | ${chiTietSanPham.kichCo}</option>
            </c:forEach>
        </form:select> <br><br>


        </div>
        <div class="col-3">
            Số lượng: <br><form:input type="number" path="soLuong" class="form-control"/>
            <form:errors path="soLuong" cssClass="text-danger"/> <br>

            Đơn giá:<br><form:input type="number" path="donGia" class="form-control"/>
            <form:errors path="donGia" cssClass="text-danger"/> <br>
        </div>
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
            <th>Nhân viên</th>
            <th>Người nhận</th>
            <th>Số điện thoại</th>
            <th>Sản phẩm</th>
            <th>Màu sắc</th>
            <th>Kích cỡ</th>
            <th>Số lượng</th>
            <th>Đơn giá</th>
            <th>Thành tiền</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${listGHCT.content}" var="l" varStatus="s">
            <tr>
                <td>${s.index+1}</td>
                <td>${l.gioHang.nhanVien.hoTen}</td>
                <td>${l.gioHang.khachHang.hoTen}</td>
                <td>${l.gioHang.sdt}</td>
                <td>${l.chiTietSanPham.sanPham.ten}</td>
                <td>${l.chiTietSanPham.mauSac.ten}</td>
                <td>${l.chiTietSanPham.kichCo}</td>
                <th>${l.soLuong}</th>
                <th>${l.donGia}</th>
                <th>${l.soLuong * l.donGia}</th>
                <td>
                    <a class="btn btn-primary" href="/gio-hang-chi-tiet/edit/${l.id}" role="button">Edit</a>
                    <a class="btn btn-danger" href="/gio-hang-chi-tiet/remove/${l.id}" role="button">Remove</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<br>
<div class="row">
    <div class="col-3"></div>
    <div class="col-4">

        <nav aria-label="Page navigation example">
            <ul class="pagination justify-content-end">
                <li class="page-item">
                    <a class="page-link"
                       href="/gio-hang-chi-tiet?pageNo=${listGHCT.number - 1}&pageSize=${listGHCT.size}"
                       aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>

                <c:if test="${not empty listGHCT}">
                    <c:forEach var="i" begin="0" end="${listGHCT.totalPages }">
                        <li class="page-item"><a class="page-link"
                                                 href="/gio-hang-chi-tiet?pageNo=${i}&pageSize=${listGHCT.size}">${i + 1}</a>
                        </li>
                    </c:forEach>
                </c:if>

                <li class="page-item">
                    <a class="page-link"
                       href="/gio-hang-chi-tiet?pageNo=${listGHCT.number + 1}&pageSize=${listGHCT.size}"
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
