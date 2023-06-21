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
            font-family: "Times New Roman";
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

<body class="bg-white text-gray-600 work-sans leading-normal text-base tracking-normal">

<!--Nav-->
<%@include file="../layout/header-khach-hang-dang-nhap.jsp" %>
<section class="bg-white py-8">

    <div class="container mx-auto flex items-center flex-wrap pt-4 pb-12">

        <nav id="store" class="w-full z-30 top-0 px-6 py-1">
            <div class="w-full container mx-auto flex flex-wrap items-center justify-between mt-0 px-2 py-3">

                <a class="uppercase tracking-wide no-underline hover:no-underline font-bold text-gray-800 text-xl "
                   href="#">
                    Store
                </a>

                <div class="flex items-center" id="store-nav-content">

                    <a class="pl-3 inline-block no-underline hover:text-black" href="#">
                        <svg class="fill-current hover:text-black" xmlns="http://www.w3.org/2000/svg" width="24"
                             height="24" viewBox="0 0 24 24">
                            <path d="M7 11H17V13H7zM4 7H20V9H4zM10 15H14V17H10z"/>
                        </svg>
                    </a>

                    <a class="pl-3 inline-block no-underline hover:text-black" href="#">
                        <svg class="fill-current hover:text-black" xmlns="http://www.w3.org/2000/svg" width="24"
                             height="24" viewBox="0 0 24 24">
                            <path d="M10,18c1.846,0,3.543-0.635,4.897-1.688l4.396,4.396l1.414-1.414l-4.396-4.396C17.365,13.543,18,11.846,18,10 c0-4.411-3.589-8-8-8s-8,3.589-8,8S5.589,18,10,18z M10,4c3.309,0,6,2.691,6,6s-2.691,6-6,6s-6-2.691-6-6S6.691,4,10,4z"/>
                        </svg>
                    </a>

                </div>
            </div>
        </nav>
        <c:forEach items="${listCTSP.content}" var="l">
        <div style="margin: 20px">

            <a href="/mua-hang-dang-nhap/${l.id}">
                <img width="350px" height="298px" src="/images/${l.anh}" alt="..." class="hover:grow hover:shadow-lg">
                <div style="text-align: center; margin-top: 20px">
                    <p>${l.sanPham.ten}</p>
                </div>
                <div style="text-align: center; margin-top: 20px">
                    <p>${l.donGia}$</p>
                </div>

                <div style="text-align: center; margin-top: 20px">
                        <%--                <a href="/add-cart/${l.id}" class="btn btn-outline-dark" role="button">Thêm giỏ hàng</a>--%>
                        <%--                <form action="/add-cart-now/${l.id}" method="get">--%>
                    <Button class="btn btn-outline-dark" type="submit">Thêm giỏ hàng</Button>
                        <%--                | <a href="/mua-ngay/${l.id}" class="btn btn-dark" role="button">Mua ngay</a>--%>
                        <%--                </form>--%>
                </div>
            </a>
        </div>
        </c:forEach>
</section>
<div class="row">
    <div class="col-2"></div>
    <div class="col-4">
        <nav aria-label="Page navigation example">
            <ul class="pagination justify-content-end">
                <li class="page-item">
                    <a class="page-link"
                       href="/trang-chu/khach-hang?pageNo=${listCTSP.number - 1}&pageSize=${listCTSP.size}"
                       aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
                <c:if test="${not empty listCTSP}">
                    <c:forEach var="i" begin="0" end="${listCTSP.totalPages - 1}">
                        <li class="page-item"><a class="page-link"
                                                 href="/trang-chu/khach-hang?pageNo=${i}&pageSize=${listCTSP.size}">${i + 1}</a>
                        </li>
                    </c:forEach>
                </c:if>
                <li class="page-item">
                    <a class="page-link"
                       href="/trang-chu/khach-hang?pageNo=${listCTSP.number + 1}&pageSize=${listCTSP.size}"
                       aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
            </ul>
        </nav>
    </div>

</div>
<section class="bg-white py-8">

    <div class="container py-8 px-6 mx-auto">

        <a class="uppercase tracking-wide no-underline hover:no-underline font-bold text-gray-800 text-xl mb-8"
           href="#">
            About
        </a>

        <p class="mt-8 mb-8">This template is inspired by the stunning nordic minamalist design - in particular:
            <br>
            <a class="text-gray-800 underline hover:text-gray-900" href="http://savoy.nordicmade.com/" target="_blank">Savoy
                Theme</a> created by <a class="text-gray-800 underline hover:text-gray-900"
                                        href="https://nordicmade.com/">https://nordicmade.com/</a> and <a
                    class="text-gray-800 underline hover:text-gray-900" href="https://www.metricdesign.no/"
                    target="_blank">https://www.metricdesign.no/</a></p>

        <p class="mb-8">Lorem ipsum dolor sit amet, consectetur <a href="#">random link</a> adipiscing elit, sed do
            eiusmod tempor incididunt ut labore et dolore magna aliqua. Vel risus commodo viverra maecenas accumsan
            lacus vel facilisis volutpat. Vitae aliquet nec ullamcorper sit. Nullam eget felis eget nunc lobortis mattis
            aliquam. In est ante in nibh mauris. Egestas congue quisque egestas diam in. Facilisi nullam vehicula ipsum
            a arcu. Nec nam aliquam sem et tortor consequat. Eget mi proin sed libero enim sed faucibus turpis in. Hac
            habitasse platea dictumst quisque. In aliquam sem fringilla ut. Gravida rutrum quisque non tellus orci ac
            auctor augue mauris. Accumsan lacus vel facilisis volutpat est velit egestas dui id. At tempor commodo
            ullamcorper a. Volutpat commodo sed egestas egestas fringilla. Vitae congue eu consequat ac.</p>

    </div>

</section>

<footer class="container mx-auto bg-white py-8 border-t border-gray-400">
    <div class="container flex px-3 py-8 ">
        <div class="w-full mx-auto flex flex-wrap">
            <div class="flex w-full lg:w-1/2 ">
                <div class="px-3 md:px-0">
                    <h3 class="font-bold text-gray-900">About</h3>
                    <p class="py-4">
                        Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas vel mi ut felis tempus commodo
                        nec id erat. Suspendisse consectetur dapibus velit ut lacinia.
                    </p>
                </div>
            </div>
            <div class="flex w-full lg:w-1/2 lg:justify-end lg:text-right">
                <div class="px-3 md:px-0">
                    <h3 class="font-bold text-gray-900">Social</h3>
                    <ul class="list-reset items-center pt-3">
                        <li>
                            <a class="inline-block no-underline hover:text-black hover:underline py-1" href="#">Add
                                social links</a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</footer>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"
        integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"
        integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF"
        crossorigin="anonymous"></script>
</body>

</html>
