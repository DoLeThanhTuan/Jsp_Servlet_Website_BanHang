<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!--begin of menu-->
<nav class="navbar navbar-expand-md navbar-dark bg-dark">
    <div class="container">
        <a class="navbar-brand" href="./HomeController">Shop</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse justify-content-end" id="navbarsExampleDefault">
            <ul class="navbar-nav m-auto">
                
                <c:if test="${sessionScope.accountLogined != null}">
                <c:if test="${sessionScope.accountLogined.getIsAdmin() == 1}">
                	<li class="nav-item">
	                    <a class="nav-link" href="./ManagerAccountController">Quản lý tài khoản</a>
	                </li>
                </c:if>
                <c:if test="${sessionScope.accountLogined.getIsAdmin() == 1}">
                	<li class="nav-item">
	                    <a class="nav-link" href="./ManagerPost">Quản lý bài đăng</a>
	                </li>
                </c:if>
                <c:if test="${sessionScope.accountLogined.getIsSell() == 1}">
                	<li class="nav-item">
	                    <a class="nav-link" href="./ManagerProductController">Quản lý sản phẩm</a>
	                </li>
                </c:if>
                <li class="nav-item">
                    <a class="nav-link" href="#">Xin chào ${sessionScope.accountLogined.getUser()}</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="./LogoutController">Đăng xuất</a>
                </li>
                </c:if>
                <c:if test="${sessionScope.accountLogined == null}">
	                <li class="nav-item">
	                    <a class="nav-link" href="Login.jsp">Đăng nhập</a>
	                </li>
                </c:if>
            </ul>

            <form action="./SearchController" method="post" class="form-inline my-2 my-lg-0">
                <div class="input-group input-group-sm">
                    <input value="${txtS}" name="txtSearch" type="text" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm" placeholder="Search...">
                    <div class="input-group-append">
                        <button type="submit" class="btn btn-secondary btn-number">
                            <i class="fa fa-search"></i>
                        </button>
                    </div>
                </div>
                <c:if test="${sessionScope.accountLogined != null}">
	                <a class="btn btn-success btn-sm ml-3" href="./cart">
	                    <i class="fa fa-shopping-cart"></i> Cart
	                    <span class="badge badge-light">${countCart}</span>
	                </a>
                </c:if>
            </form>
        </div>
    </div>
</nav>
<section class="jumbotron text-center">
    <div class="container">
        <h1 class="jumbotron-heading">Website mua bán đồ cũ khu vực Đà Nẵng</h1>
        <p class="lead text-muted mb-0">Uy tín tạo nên thương hiệu, mua hàng nhanh gọn</p>
    </div>
</section>
<!--end of menu-->
