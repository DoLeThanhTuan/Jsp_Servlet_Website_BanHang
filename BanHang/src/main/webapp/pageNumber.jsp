<%@page import="com.BanHang.model.PageModel"%>
<%@page import="com.BanHang.DAO.ProductDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
  
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>jQuery Pagination plugin</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/js/bootstrap.min.js"></script>
    <script src="./jquery/jquery.twbsPagination.js" type="text/javascript"></script>
</head>
<body>
<div class="container">
    <nav aria-label="Page navigation">
        <div>
        <form action="./${urlController}" method="get" id="formIndex">
			<ul class="pagination" id="pagination"></ul>
        	<input type="hidden" val="" id="indexPage" name="index">
        </form>
        </div>
    </nav>
</div>
<%PageModel p = (PageModel) request.getAttribute("page"); %>
<script type="text/javascript">
    $(function () {
        window.pagObj = $('#pagination').twbsPagination({
            totalPages: <%=p.getPageNumbers()%>,
            visiblePages: <%=p.getVisiblePages()%>,
            startPage: <%=p.getStartPages()%>,
            onPageClick: function (event, page) {
            	if(<%=p.getStartPages()%> != page){
            		$('#indexPage').val(page),
            		$('#formIndex').submit()
            	}
            }
        }).on('page', function (event, page) {
            console.info(page + ' (from event listening)');
        });
    });
</script>
</body>
</html>
