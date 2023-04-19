

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="col-sm-3">
	<div class="card bg-light mb-3">
		<div class="card-header bg-primary text-white text-uppercase">
			<i class="fa fa-list"></i> Danh mục
		</div>
		<ul class="list-group category_block">
			<c:forEach items="${listCate}" var="o">
				<li class="list-group-item text-white ${o.getCid()==Cid ? "active" : ""}"><a
					href="./CategoryController?Cid=${o.getCid()}">${o.getCname()}</a></li>
			</c:forEach>

		</ul>
	</div>
	<div class="card bg-light mb-3">
		<div class="card-header bg-success text-white text-uppercase">Sản phẩm mới đăng</div>
		<div class="card-body">
			<img class="img-fluid" src="${proTop1.getImage()}" />
			<h5 class="card-title">${proTop1.getName()}</h5>
			<p class="card-text">${proTop1.getDescription()}</p>
			<p class="bloc_left_price">${proTop1.getPrice()} $</p>
		</div>
	</div>
</div>