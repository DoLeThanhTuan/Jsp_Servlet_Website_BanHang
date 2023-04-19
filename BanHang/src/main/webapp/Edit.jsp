<%-- 
    Document   : ManagerProduct
    Created on : Dec 28, 2020, 5:19:02 PM
    Author     : trinh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Bootstrap CRUD Data Table for Database with Modal Form</title>
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link href="css/manager.css" rel="stylesheet" type="text/css" />
<style>
img {
	width: 200px;
	height: 120px;
}
.btn_home {
	margin-left: 168px;
}
</style>
<body>
	<!-- Edit Modal HTML -->
		<div class="modal-dialog">
			<div class="modal-content">
				<form action = "./EditProductController?pid=${pid}" method="post">
					<div class="modal-header">
						<h4 class="modal-title">Chỉnh sửa sản phẩm</h4>
						<a href="./ManagerProductController" type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</a>
					</div>
					<div class="modal-body">
						<div class="form-group">
							<label>ID</label> <input value = "${pid}" readonly type="text" class="form-control"
								required>
						</div>
						<div class="form-group">
							<label>Tên sản phẩm</label> <input name="name" value = "${pro.getName() }" type="text" class="form-control"
								required>
						</div>
						<div class="form-group">
							<label>Hình ảnh</label> <input name="image" value = "${pro.getImage() }" type="text" class="form-control"
								required>
						</div>
						<div class="form-group">
							<label>Giá ($)</label> <input name="price" value = "${pro.getPrice() }" type="text" class="form-control"
								required>
						</div>
						<div class="form-group">
							<label>Tiêu đề</label>
							<textarea name="title"  class="form-control" required>${pro.getDescription()}</textarea>
						</div>
						<div class="form-group">
							<label>Mô tả</label>
							<textarea name="description" class="form-control" required>${pro.getDescription()}</textarea>
						</div>
						<div class="form-group">
							<label>Danh mục</label> <select name="category"
								class="form-select" aria-label="Default select example">
								<c:forEach items="${listCate}" var="o">
									<option value="${o.cid}">${o.cname}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="modal-footer">
						<a href="./ManagerProductController" type="button" class="btn btn-default" data-dismiss="modal"
							>Hủy bỏ</a> 
						<input type="submit" class="btn btn-info"
							value="Lưu">
					</div>
				</form>
			</div>
		</div>
		
	<script src="js/manager.js" type="text/javascript"></script>
	<a href="./HomeController"><button type="button" class="btn btn-primary btn_home">Trở lại trang chủ</button>
</body>
</html>