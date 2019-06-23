<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<body>
	<div class="main-content">
		<form action="<c:url value='/admin-news'/>" id="formSubmit" method="get">
			<div class="main-content">
				<div class="main-content-inner">
					<div class="breadcrumbs ace-save-state" id="breadcrumbs">
						<ul class="breadcrumb">
							<li><i class="ace-icon fa fa-home home-icon"></i> <a href="#">Trang
									chủ</a></li>
						</ul>
						<!-- /.breadcrumb -->
					</div>
					<div class="page-content">
						<div class="row">
							<div class="col-xs-12">
								<div class="table-responsive">
									<table class="table table-bordered">
										<thead>
											<tr>
												<th>Ten bai viet</th>
												<th>Mo ta ngan</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="item" items="${model.getListResult()}">
												<tr>
													<td>${item.title}</td>
													<td>${item.shortDescription}</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
									<ul class="pagination" id="pagination"></ul>
									<input type="hidden" id="page" name="page" value="">
									<input type="hidden" id="maxPageItem" name="maxPageItem" value="">
									<input type="hidden" id="sortName" name="sortName" value="">
									<input type="hidden" id="sortBy" name="sortBy" value="">
									
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>
	<!-- /.main-content -->
	<script type="text/javascript">
		$(function() {
			var totalpage = ${model.totalpage};
			var currentPage = ${model.page};
			var limit = 2;
			window.pagObj = $('#pagination').twbsPagination({
				totalPages : totalpage,
				visiblePages : 10,
				startPage: currentPage,
				onPageClick : function(event, page) {
					if (currentPage != page) {
						$('#maxPageItem').val(limit);
						$('#page').val(page);
						$('#sortName').val('title');
						$('#sortBy').val('desc');
						$('#formSubmit').submit();
					}
				}
			}).on('page', function(event, page) {
				console.info(page + ' (from event listening)');
			});
		});
	</script>
</body>

</html>