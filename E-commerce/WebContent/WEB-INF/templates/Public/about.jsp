<?xml version="1.0" encoding="utf-8" ?>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="static/css/bootstrap.css" rel="stylesheet" />
<link href="static/css/main.css" rel="stylesheet" />
<link href="static/fonts/fontawesome/css/font-awesome.css "
	rel="stylesheet" />
<link href="static/fonts/fontawesome/css/all.css " rel="stylesheet" />
<link rel="icon" href="static/images/logo.png" type="image/x-icon" />
<title>A propos de nous</title>
</head>
<body>
	<c:import url="/WEB-INF/templates/Fragments/menuE.jsp">
		<c:param name="page" value="about" />
	</c:import>

	<div class="container-fluid mt-2 mb-2 pl-4 pr-4"
		style="min-height: 440px">
		<div class="row ">
			<!-- ------------ PAGE CONTENT  ----------------->

			<div class="col-md-3 bg-white border rounded">
				<h5 class="text-center mt-2 side-text">Categories</h5>
				<hr />
				<ul class="list-group">
					<c:forEach items="${categories}" var="category">
						
						<div class="list-group">

							<a href="<c:url value ='/search?action=category?name=${category.name}'/>" class="list-group-item list-group-item-action ">
								${category.name}</a> 
						</div>
					</c:forEach>

				</ul>
			</div>
			

			<div class="col-md-9">
				<div class="row bg-white border rounded" style="min-height: 440px" >
                   <div class="col-auto p-4">
					<h4>Lorem ipsum dolor sit amet</h4>
					<p class="text-justify  para mb-0">Lorem ipsum dolor sit amet, consectetur adipiscing elit.
						Donec varius mi magna, quis tempus orci elementum maximus. Aliquam
						placerat lobortis rhoncus. Quisque erat ex, auctor et sodales et,
						blandit sit amet libero. Mauris ultricies sapien auctor arcu
						elementum eleifend ac vel eros. In quam tortor, blandit semper
						nisl id, accumsan pretium velit. Vestibulum interdum libero leo,
						eget posuere nunc vestibulum at. Donec sed odio lacus. Nulla
						sollicitudin, lacus ac fermentum interdum, orci quam tincidunt
						lectus, eget volutpat erat erat eget purus. Maecenas blandit
						mauris sit amet odio rhoncus semper. Suspendisse lobortis
						ullamcorper velit, sed scelerisque libero mollis quis. Aliquam
						molestie fringilla urna, ac dapibus felis dignissim id.</p>

					<p class="text-justify  para mb-0">Sed non quam id lectus pharetra lobortis ac vel mi. Nunc sed
						sagittis magna. Pellentesque convallis malesuada turpis nec
						feugiat. Pellentesque habitant morbi tristique senectus et netus
						et malesuada fames ac turpis egestas. Fusce condimentum sit amet
						purus tincidunt sagittis. Fusce eu urna in eros pulvinar
						consectetur. Donec efficitur malesuada enim nec elementum. Integer
						rhoncus augue id pharetra blandit.</p>
                      </div>
					<div class="col-12" style="height: 15px;"></div>
				</div>
			</div>
			<!-- ----------------------------------------- -->
		</div>
	</div>

	<c:import url="/WEB-INF/templates/Fragments/footer.jsp">
	</c:import>
	<script src="static/js/bootstrap.js"></script>
	<script src="static/js/jquery.min.js"></script>
	<script src="static/fonts/fontawesome/js/fontawesome.js"></script>
	<script src="static/js/bootstrap.bundle.min.js"></script>
	<script src="static/Admin/js/popper.min.js"></script>
</body>
</html>