<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="css/forgetpw.css" rel="stylesheet" type="text/css" />
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.bundle.min.js"
	rel="stylesheet" id="bootstrap-css">
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"
	rel="stylesheet" id="bootstrap-css">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div class="container padding-bottom-3x mb-2 mt-5">
		<div class="row justify-content-center">
			<div class="col-lg-8 col-md-10">
				<div class="forgot">
					<h2>Quên mật khẩu</h2>
					<p>Thay đổi mật khẩu dễ dàng với 3 bước</p>
					<ol class="list-unstyled">
						<li><span class="text-primary text-medium">1. </span>Nhập email bạn đã đăng ký.</li>
						<li><span class="text-primary text-medium">2. </span>Hệ thống sẽ gửi link reset mật khẩu đến email của bạn</li>
						<li><span class="text-primary text-medium">3. </span>Sử dụng link để lấy lại mật khẩu</li>
					</ol>

				</div>

				<form class="card mt-4">
					<div class="card-body">
						<div class="form-group">
							<label for="email-for-pass">Nhập email của bạn</label> <input
								class="form-control" type="text" id="email-for-pass">
						</div>
					</div>
					<div class="card-footer">
						<a href="./Login.jsp" class="btn btn-success">Lấy lại mật khẩu</a>
						<a href="./Login.jsp" class="btn btn-danger">Trở lại đăng nhập</a>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>