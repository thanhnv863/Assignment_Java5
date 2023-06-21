<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="for" uri="http://www.springframework.org/tags/form" %>
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

    <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css"
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

<%@include file="../layout/header-khach-hang.jsp" %>
<%--Gio Hang--%>
<div class="row" style="text-align: center">
    <h1>Thanh Toán</h1>
</div>
<br>
<hr>
<br>
<h3>Thanh Toán</h3>
<br>
<%--@elvariable id="hoaDonKhachHang" type=""--%>
<form:form action="/hoan-tat" method="post" modelAttribute="hoaDonKhachHang">
    <div class="row">
        <div class="col-4" style="border: solid black">
            <div class="row">
                <h5 class="badge bg-warning">1. Địa chỉ giao hàng</h5>
            </div>
            <h5>Thông tin thanh toán</h5>
            <a href="/login" class="btn btn-outline-primary" role="button">Đăng ký tài khoản</a> | <a
                class="btn btn-outline-success" role="button" href="/login">Đăng nhập</a>
            <br>
            <br>
            <h5>Mua hàng không cần tài khoản</h5>
            <form:input path="tenNguoiNhan" placeholder="Họ & tên" class="form-control"
                        title="Vui lòng chỉ sử dụng chữ cái" required="true"/> <br>
                <%--        <form:errors path="tenNguoiNhan" cssClass="text-danger"/> <br>--%>
            <form:input type="text" path="sdt" placeholder="Số điện thoại" class="form-control" pattern="[0-9]{10}"
                        title="Vui lòng chỉ sử dụng số, nhập đủ 10 số" required="true"/> <br>
                <%--        <form:errors path="sdt" cssClass="text-danger"/> <br>--%>
            <form:input type="email" path="email" placeholder="Email" class="form-control" required="true"/> <br>
                <%--        <form:errors path="email" cssClass="text-danger"/> <br>--%>
            <form:input path="diaChi" placeholder="Địa chỉ" class="form-control"
                        title="Vui lòng chỉ sử dụng chữ cái và số, tối đa 10 ký tự" required="true"/> <br>
                <%--        <form:errors path="diaChi" cssClass="text-danger"/> <br>--%>

        </div>
        <div class="col-4" style="border: solid black">
            <div class="row">
                <h5 class="badge bg-danger">2. Thanh toán</h5>
            </div>
            <h5>Thanh toán</h5>
            <input:radiobutton path="hinhThucThanhToan" value="Chuyển khoản momo" onclick="showImage('momo.jpg')"/>
            Chuyển khoản momo <br>
            <input:radiobutton path="hinhThucThanhToan" value="Chuyển khoản ngân hàng"
                               onclick="showImage('mbbank.jpg')"/> Chuyển khoản ngân hàng <br>
            <input:radiobutton path="hinhThucThanhToan" value="Thanh toán khi nhận hàng"
                               onclick="showImage('thanh-toan-khi-nhan-hang.jpg')" checked="true"/> Thanh toán khi nhận
            hàng <br>
            <br>
            <p>Nội dung chuyển khoản: Số điện thoại_HọTên, Mọi thắc mắc liên hệ <i class="fa-solid fa-phone"> hotline:
                0988542315</i></p>
            <a href="https://www.facebook.com/rp23.bewoat/"><i class="fa-brands fa-facebook"></i></a>
            <a href="https://www.facebook.com/rp23.bewoat/"><i class="fa-brands fa-facebook-messenger"></i></a>
            <a href="https://www.instagram.com/ctrl_z.exe/"><i class="fa-brands fa-instagram"></i></a>

            <div id="imageContainer"></div>
        </div>
        <div class="col-4" style="border: solid black">
            <div class="row">
                <h5 class="badge bg-dark">3. Thông tin đơn hàng</h5>
            </div>
            <c:set var="subtotal" value="0"/>
            <c:forEach items="${cart}" var="cart">
                <div>
                    <h5><img style="width: 100px; height: 100px" src="../images/${cart.anh}">x ${cart.soLuong} <p
                            style="float: right">${cart.soLuong * cart.gia}đ</p></h5>
                </div>
                <hr>
                <c:set var="subtotal" value="${subtotal + (cart.soLuong * cart.gia)}"/>
            </c:forEach>
            <div>
                <h5>Thành tiền <p style="float: right"> ${subtotal} đ </p></h5>
            </div>
            <hr>
            <div>
                <h5>Thanh toán <p style="float: right">${subtotal} đ</p></h5>
            </div>
            <hr>
                <%--        <a style="float: right" class="btn btn-dark" role="button"  href="/hoan-tat">Đặt hàng</a>--%>
                <%--        <button style="float: right" class="btn btn-dark" type="submit">Đặt hàng</button>--%>
            <form:button style="float: right" class="btn btn-dark" type="submit"
                         onClick="return datHang()">Đặt hàng</form:button>
        </div>
    </div>
</form:form>
<%@include file="../layout/footer.jsp" %>

<script>
    function showImage(imageUrl) {
        var imageContainer = document.getElementById("imageContainer");
        imageContainer.innerHTML = "<img  style='width: 350px;height=300px; margin:20px 20px' src='/images/" + imageUrl + "' />";
    }

    function datHang() {
        let option = confirm("Bạn có chăc muốn đặt hàng");
        if (option === true) {
            return true;
        } else {
            alert("Bạn đã hủy đặt hàng");
            return false;
        }
    }
</script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"
        integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"
        integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF"
        crossorigin="anonymous"></script>
</body>
</html>

