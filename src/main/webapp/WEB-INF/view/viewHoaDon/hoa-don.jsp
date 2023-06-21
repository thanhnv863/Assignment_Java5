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
<div class="row" style="text-align: center">
    <h1>Hóa Đơn</h1>
</div>
<br>
<div class="row">
    <%--@elvariable id="hoaDon" type=""--%>
    <form:form action="/hoa-don/addOrUpdate" method="post" modelAttribute="hoaDon">
        <div class="row">
            <div class="col-3">
                <form:input path="id" hidden="true" class="form-control"/>

                Khách hàng:<br> <form:select path="khachHang" class="form-select">
                <c:forEach items="${khachHang}" var="khachHang">
                    <option value="${khachHang.id}" ${hoaDon.khachHang.id == khachHang.id ? 'selected="selected"' : ''}>${khachHang.hoTen}</option>
                </c:forEach>
            </form:select> <br>
                Nhân viên:<br> <form:select path="nhanVien" class="form-select">
                <c:forEach items="${nhanVien}" var="nhanVien">
                    <option value="${nhanVien.id}" ${hoaDon.nhanVien.id == nhanVien.id ? 'selected="selected"' : ''}>${nhanVien.hoTen}</option>
                </c:forEach>
            </form:select> <br>
                <br><form:input path="ma" hidden="true" class="form-control"/>
<%--                <p style="color: red">${checkMa}</p>--%>
<%--                <form:errors path="ma" cssClass="text-danger"/> <br>--%>
            </div>
            <div class="col-3">
                Ngày tạo:<br><form:input type="date" path="ngayTao" class="form-control"/>
                <form:errors path="ngayTao" cssClass="text-danger"/> <br>
                Ngày thanh toán:<br><form:input type="date" path="ngayThanhToan" class="form-control"/>
               <br>
                Ngày ship:<br><form:input type="date" path="ngayShip" class="form-control"/>
             <br>
                Ngày nhận:<br><form:input type="date" path="ngayNhan" class="form-control"/>
              <br>
            </div>
            <div class="col-3">
                Tên người nhận:<br><form:input path="tenNguoiNhan" class="form-control"/>
                <form:errors path="tenNguoiNhan" cssClass="text-danger"/> <br>
                Địa chỉ:<br><form:input path="diaChi" class="form-control"/>
                <form:errors path="diaChi" cssClass="text-danger"/> <br>
                SĐT:<br><form:input type="number" path="sdt" class="form-control"/>
                <form:errors path="sdt" cssClass="text-danger"/> <br>
                Email:<br><form:input type="email" path="email" class="form-control"/>
                <form:errors path="email" cssClass="text-danger"/> <br>
            </div>
            <div class="col-3">

                Hình thức thanh toán:<br><br><form:radiobutton path="hinhThucThanhToan" value="Thanh toán khi nhận hàng"
                                                checked="true"/> Thanh toán khi nhận hàng
                <br> <br>
                <form:radiobutton path="hinhThucThanhToan" value="Chuyển khoản ngân hàng"/>Chuyển khoản ngân hàng<br> <br>
                <form:radiobutton path="hinhThucThanhToan" value="Chuyển khoản momo"/>Chuyển khoản momo<br><br>

                Trạng thái: <br><form:select path="trangThai" class="form-select">
                <form:option value="0">Chờ thanh toán</form:option>
                <form:option value="1">Đơn hàng thành công</form:option>
                <form:option value="2">Đang vận chuyển</form:option>
                <form:option value="3">Đang chuẩn bị hàng</form:option>
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
            <th>Khách hàng</th>
            <th>Nhân viên</th>
            <th>Ngày tạo</th>
            <th>Ngày nhận</th>
            <th>Tên người nhận</th>
            <th>Địa chỉ</th>
            <th>SĐT</th>
            <th>Email</th>
            <th>Hình thức thanh toán</th>
            <th>Trạng thái</th>

            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${listHD.content}" var="l" varStatus="s">
            <c:set var="color" value=""/>
            <c:choose>
                <c:when test="${l.trangThai == 0}">
                    <c:set var="color" value="btn btn-warning"/>
                    <c:set var="statusText" value="Chờ thanh toán"/>
                </c:when>
                <c:when test="${l.trangThai == 1}">
                    <c:set var="color" value="btn btn-success"/>
                    <c:set var="statusText" value="Đơn hàng thành công"/>
                </c:when>
                <c:when test="${l.trangThai == 2}">
                    <c:set var="color" value="btn btn-info"/>
                    <c:set var="statusText" value="Đang vận chuyển"/>
                </c:when>
                <c:when test="${l.trangThai == 3}">
                    <c:set var="color" value="btn btn-danger"/>
                    <c:set var="statusText" value="Đang chuẩn bị hàng"/>
                </c:when>
            </c:choose>

            <c:set var="statusOrder" value="${3 - l.trangThai}" />

            <tr>
                <td>${s.index+1}</td>
                <td>${l.khachHang.hoTen}</td>
                <td>${l.nhanVien.hoTen}</td>
                <td>${l.ngayTao}</td>
                <td>${l.ngayNhan}</td>
                <td>${l.tenNguoiNhan}</td>
                <td>${l.diaChi}</td>
                <td>${l.sdt}</td>
                <td>${l.email}</td>
                <td>${l.hinhThucThanhToan}</td>
                <td>
                    <button class="color: ${color}">${statusText}</button>
                </td>
                <td>
                    <a class="btn btn-primary" href="/hoa-don/edit/${l.id}" role="button">Edit</a>
                    <a class="btn btn-danger" href="/hoa-don/remove/${l.id}" role="button">Remove</a>
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
                            <a class="page-link" href="/hoa-don?pageNo=${listHD.number - 1}&pageSize=${listHD.size}"
                               aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                            </a>
                        </li>
                        <c:if test="${not empty listHD}">
                            <c:forEach var="i" begin="0" end="${listHD.totalPages }">
                                <li class="page-item"><a class="page-link"
                                                         href="/hoa-don?pageNo=${i}&pageSize=${listHD.size}">${i + 1}</a>
                                </li>
                            </c:forEach>
                        </c:if>
                        <li class="page-item">
                            <a class="page-link" href="/hoa-don?pageNo=${listHD.number + 1}&pageSize=${listHD.size}"
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
