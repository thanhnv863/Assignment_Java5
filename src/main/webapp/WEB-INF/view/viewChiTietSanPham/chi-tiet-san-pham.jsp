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
    <h1>Chi Tiết Sản Phẩm</h1>
</div>
<br>
<div class="row">

    <%--@elvariable id="chiTietSanPham" type=""--%>
    <form:form method="post" action="/chi-tiet-san-pham/addOrUpdate" modelAttribute="chiTietSanPham"
               enctype="multipart/form-data">

        <div class="row">
            <div class="col-3">
                Sản phẩm:<br> <form:select path="sanPham" class="form-select">
                <c:forEach items="${sanPhamx}" var="sanPhamx">
                    <option value="${sanPhamx.id}" ${chiTietSanPham.sanPham.ten == sanPhamx.ten ? 'selected="selected"' : ''}>${sanPhamx.ten}</option>
                </c:forEach>
            </form:select> <br>
                Đế giày:<br> <form:select path="deGiay" class="form-select">
                <c:forEach items="${deGiayx}" var="deGiayx">
                    <option value="${deGiayx.id}" ${chiTietSanPham.deGiay.ten == deGiayx.ten ? 'selected="selected"' : ''}>${deGiayx.ten}</option>
                </c:forEach>
            </form:select> <br>
                Dòng sản phẩm:<br> <form:select path="dongSP" class="form-select">
                <c:forEach items="${dongSPx}" var="dongSPx">
                    <option value="${dongSPx.id}" ${chiTietSanPham.dongSP.ten == dongSPx.ten ? 'selected="selected"' : ''}>${dongSPx.ten}</option>
                </c:forEach>
            </form:select> <br>
                Màu sắc:<br> <form:select path="mauSac" class="form-select">
                <c:forEach items="${mauSacx}" var="mauSacx">
                    <option value="${mauSacx.id}" ${chiTietSanPham.mauSac.ten == mauSacx.ten ? 'selected="selected"' : ''}>${mauSacx.ten}</option>
                </c:forEach>
            </form:select> <br>
            </div>
            <div class="col-3">
                Ngày nhập hàng:<br><form:input type="date" path="ngayNhapHang" class="form-control"/>
                <form:errors path="ngayNhapHang" cssClass="text-danger"/> <br>
                Đơn giá:<br><form:input type="number" path="donGia" class="form-control"/>
                <form:errors path="donGia" cssClass="text-danger"/> <br>
                Số lượng:<br><form:input type="number" path="soLuong" class="form-control"/>
                <form:errors path="soLuong" cssClass="text-danger"/> <br>
            </div>
            <div class="col-3">
                Ảnh:<br><input type="file" name="anh" accept="image/*" class="form-control-file"
                               onchange="previewImage(event)"/>
                <br>
                <img id="preview" src="" width="100px" height="100px" alt="Ảnh được chọn"/>
                <br>
                Xuất xứ:<br><form:input path="xuatXu" class="form-control"/>
                <form:errors path="xuatXu" cssClass="text-danger"/> <br>
                Kích cỡ:<br><form:input path="kichCo" class="form-control"/>
                <form:errors path="kichCo" cssClass="text-danger"/> <br>
            </div>
            <div class="col-3">
                Trạng thái: <br><form:select path="trangThai" class="form-select">
                <form:option value="1">Đang bán</form:option>
                <form:option value="0">Dừng bán</form:option>
            </form:select> <br> <br>
                ID:<br><form:input path="id" readonly="true" class="form-control"/><br>
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
            <th>Sản phẩm</th>
            <th>Dòng SP</th>
            <th>Đế giày</th>
            <th>Màu sắc</th>
            <th>Ngày nhập hàng</th>
            <th>Đơn giá</th>
            <th>Số lượng</th>
            <th>Ảnh</th>
            <th>Xuất xứ</th>
            <th>Kích cỡ</th>
            <th>Trạng thái</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${listCTSP.content}" var="l" varStatus="s">
            <tr>
                <td>${s.index+1}</td>
                <td>${l.sanPham.ten}</td>
                <td>${l.dongSP.ten}</td>
                <td>${l.deGiay.ten}</td>
                <td>${l.mauSac.ten}</td>
                <td>${l.ngayNhapHang}</td>
                <td>${l.donGia}</td>
                <td>${l.soLuong}</td>
                <td>
                    <img width="150px" height="100px" src="/images/${l.anh}" alt="...">
                </td>
                <td>${l.xuatXu}</td>
                <td>${l.kichCo}</td>
                <td>${l.trangThai==1?"Đang bán":"Dừng bán"}</td>
                <td>
                    <a class="btn btn-primary" href="/chi-tiet-san-pham/edit/${l.id}" role="button">Edit</a>
                    <a class="btn btn-danger" href="/chi-tiet-san-pham/remove/${l.id}" role="button">Remove</a>
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
                    <a class="page-link"
                       href="/chi-tiet-san-pham?pageNo=${listCTSP.number - 1}&pageSize=${listCTSP.size}"
                       aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
                <c:if test="${not empty listCTSP}">
                    <c:forEach var="i" begin="0" end="${listCTSP.totalPages - 1}">
                        <li class="page-item"><a class="page-link"
                                                 href="/chi-tiet-san-pham?pageNo=${i}&pageSize=${listCTSP.size}">${i + 1}</a>
                        </li>
                    </c:forEach>
                </c:if>
                <li class="page-item">
                    <a class="page-link"
                       href="/chi-tiet-san-pham?pageNo=${listCTSP.number + 1}&pageSize=${listCTSP.size}"
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
<script>
    function previewImage(event) {
        var input = event.target;
        var reader = new FileReader();

        reader.onload = function () {
            var dataURL = reader.result;
            var preview = document.getElementById('preview');
            preview.src = dataURL;
        };

        reader.readAsDataURL(input.files[0]);
    }

</script>
