<nav id="header" class="w-full z-30 top-0 py-1">

    <div class="w-full container mx-auto flex flex-wrap items-center justify-between mt-0 px-6 py-3">

        <label for="menu-toggle" class="cursor-pointer md:hidden block">
            <svg class="fill-current text-gray-900" xmlns="http://www.w3.org/2000/svg" width="20" height="20"
                 viewBox="0 0 20 20">
                <title>menu</title>
                <path d="M0 3h20v2H0V3zm0 6h20v2H0V9zm0 6h20v2H0v-2z"></path>
            </svg>
        </label>
        <input class="hidden" type="checkbox" id="menu-toggle"/>
        <div class="order-2 md:order-3 flex items-center" id="nav-content">

            <a class="inline-block no-underline hover:text-black" href="/trang-chu/admin">
                <svg class="fill-current hover:text-black" xmlns="http://www.w3.org/2000/svg" width="24" height="24"
                     viewBox="0 0 24 24">
                    <circle fill="none" cx="12" cy="7" r="3"/>
                    <path d="M12 2C9.243 2 7 4.243 7 7s2.243 5 5 5 5-2.243 5-5S14.757 2 12 2zM12 10c-1.654 0-3-1.346-3-3s1.346-3 3-3 3 1.346 3 3S13.654 10 12 10zM21 21v-1c0-3.859-3.141-7-7-7h-4c-3.86 0-7 3.141-7 7v1h2v-1c0-2.757 2.243-5 5-5h4c2.757 0 5 2.243 5 5v1H21z"/>
                </svg>
            </a>

            <a class="pl-3 inline-block no-underline hover:text-black" href="/trang-chu/admin">
                <svg class="fill-current hover:text-black" xmlns="http://www.w3.org/2000/svg" width="24" height="24"
                     viewBox="0 0 24 24">
                    <path d="M21,7H7.462L5.91,3.586C5.748,3.229,5.392,3,5,3H2v2h2.356L9.09,15.414C9.252,15.771,9.608,16,10,16h8 c0.4,0,0.762-0.238,0.919-0.606l3-7c0.133-0.309,0.101-0.663-0.084-0.944C21.649,7.169,21.336,7,21,7z M17.341,14h-6.697L8.371,9 h11.112L17.341,14z"/>
                    <circle cx="10.5" cy="18.5" r="1.5"/>
                    <circle cx="17.5" cy="18.5" r="1.5"/>
                </svg>
            </a>

        </div>
        <div class="order-1 md:order-1">
            <a class="flex items-center tracking-wide no-underline hover:no-underline font-bold text-gray-800 text-xl "
               href="/trang-chu/admin">
                <svg class="fill-current text-gray-800 mr-2" xmlns="http://www.w3.org/2000/svg" width="24" height="24"
                     viewBox="0 0 24 24">
                    <path d="M5,22h14c1.103,0,2-0.897,2-2V9c0-0.553-0.447-1-1-1h-3V7c0-2.757-2.243-5-5-5S7,4.243,7,7v1H4C3.447,8,3,8.447,3,9v11 C3,21.103,3.897,22,5,22z M9,7c0-1.654,1.346-3,3-3s3,1.346,3,3v1H9V7z M5,10h2v2h2v-2h6v2h2v-2h2l0.002,10H5V10z"/>
                </svg>
                Thanh Hi Shop
            </a>
        </div>
        <div class="row">
            <ul class="nav justify-content-center">
                <li class="nav-item" style=" margin-right: 20px;   font-weight: bold;">
                    <div class="btn-group">
                        <button type="button" class="btn btn-info dropdown-toggle" data-bs-toggle="dropdown"
                                aria-expanded="false">
                            Tai Khoan
                        </button>
                        <ul class="dropdown-menu">
                            <li><a class="dropdown-item" href="/nhan-vien">Nhan Vien</a></li>
                            <li><a class="dropdown-item" href="/khach-hang">Khach Hang</a></li>
                        </ul>
                    </div>
                </li>
                <li class="nav-item" style=" margin-right: 20px;   font-weight: bold;">
                    <a class="btn btn-info" href="/chuc-vu">Chuc Vu</a>
                </li>
                <li class="nav-item" style=" margin-right: 20px;   font-weight: bold;">
                    <div class="btn-group">
                        <button type="button" class="btn btn-info dropdown-toggle" data-bs-toggle="dropdown"
                                aria-expanded="false">
                            Thong tin san pham
                        </button>
                        <ul class="dropdown-menu">
                            <li><a class="dropdown-item" href="/chi-tiet-san-pham">Chi Tiet San Pham</a></li>
                            <li><a class="dropdown-item" href="/san-pham">San Pham</a></li>
                            <li><a class="dropdown-item" href="/mau-sac">Mau Sac</a></li>
                            <li><a class="dropdown-item" href="/de-giay">De Giay</a></li>
                            <li><a class="dropdown-item" href="/dong-san-pham">Dong San Pham</a></li>
                        </ul>
                    </div>
                </li>
                <li class="nav-item" style=" margin-right: 20px;   font-weight: bold;">
                    <div class="btn-group">
                        <button type="button" class="btn btn-info dropdown-toggle" data-bs-toggle="dropdown"
                                aria-expanded="false">
                            Thong tin don hang
                        </button>
                        <ul class="dropdown-menu">
                            <li><a class="dropdown-item" href="/gio-hang">Gio Hang</a></li>
                            <li><a class="dropdown-item" href="/gio-hang-chi-tiet">Gio Hang Chi Tiet</a></li>
                            <li><a class="dropdown-item" href="/hoa-don">Hoa Don</a></li>
                            <li><a class="dropdown-item" href="/hoa-don-chi-tiet">Hoa Don Chi Tiet</a></li>
                        </ul>
                    </div>
                </li>
            </ul>
        </div>

        <%--            <nav>--%>
        <%--                <ul class="md:flex items-center justify-between text-base text-gray-700 pt-4 md:pt-0">--%>
        <%--                    <li><a class="inline-block no-underline hover:text-black hover:underline py-2 px-4"--%>
        <%--                           href="/nhan-vien">Nhan Vien</a></li>--%>
        <%--                    <li><a class="inline-block no-underline hover:text-black hover:underline py-2 px-4" href="/chuc-vu">Chuc--%>
        <%--                        Vu</a></li>--%>
        <%--                    <li><a class="inline-block no-underline hover:text-black hover:underline py-2 px-4"--%>
        <%--                           href="/khach-hang">Khach Hang</a></li>--%>
        <%--                    <li><a class="inline-block no-underline hover:text-black hover:underline py-2 px-4"--%>
        <%--                           href="/chi-tiet-san-pham">CT San Pham</a></li>--%>
        <%--                    <li><a class="inline-block no-underline hover:text-black hover:underline py-2 px-4" href="/mau-sac">Mau--%>
        <%--                        Sac</a></li>--%>
        <%--                    <li><a class="inline-block no-underline hover:text-black hover:underline py-2 px-4" href="/de-giay">De--%>
        <%--                        giay</a></li>--%>
        <%--                    <li><a class="inline-block no-underline hover:text-black hover:underline py-2 px-4"--%>
        <%--                           href="/dong-san-pham">Dong San Pham</a></li>--%>
        <%--                    <li><a class="inline-block no-underline hover:text-black hover:underline py-2 px-4"--%>
        <%--                           href="/san-pham">San Pham</a></li>--%>
        <%--                    <li><a class="inline-block no-underline hover:text-black hover:underline py-2 px-4" href="/hoa-don">Hoa--%>
        <%--                        Don</a></li>--%>
        <%--                    <li><a class="inline-block no-underline hover:text-black hover:underline py-2 px-4"--%>
        <%--                           href="/hoa-don-chi-tiet">CT Hoa Don</a></li>--%>
        <%--                    <li><a class="inline-block no-underline hover:text-black hover:underline py-2 px-4"--%>
        <%--                           href="/gio-hang">Gio Hang</a></li>--%>
        <%--                    <li><a class="inline-block no-underline hover:text-black hover:underline py-2 px-4"--%>
        <%--                           href="/gio-hang-chi-tiet">CT Gio Hang</a></li>--%>

        <%--                </ul>--%>
        <%--            </nav>--%>

    </div>
    </div>
</nav>
