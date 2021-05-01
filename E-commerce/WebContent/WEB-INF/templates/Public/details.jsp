<?xml version="1.0" encoding="utf-8" ?>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Details</title>
<link href="static/css/bootstrap.css" rel="stylesheet" />
<link href="static/css/main.css" rel="stylesheet" />
<link href="static/fonts/fontawesome/css/font-awesome.css "
	rel="stylesheet" />
<link href="static/fonts/fontawesome/css/all.css " rel="stylesheet" />
<link rel="icon" href="static/images/logo.png" type="image/x-icon" />
</head>
<body>
	<c:import url="/WEB-INF/templates/Fragments/menuE.jsp">
		<c:param name="page" value="" />
	</c:import>

	<div class="container-fluid mt-2 mb-2 pl-4 pr-4" style="min-height: 440px">
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
				<div class="row bg-white border rounded">
					<!-- ------------- ------------------------->
					<div class="col-md-6 mt-4">
						<div id="carouselExampleControls" class="carousel slide"
							data-ride="carousel">
							<div class="carousel-inner">
								<div class="carousel-item active">
									<img src="data:image/jpeg;base64,${product.image}"
										class="d-block w-100" alt="...">
								</div>
								<c:forEach items="${product.albums}" var="album">
								<div class="carousel-item">
									<img src="data:image/jpeg;base64,${album.image}" class="d-block w-100" alt="..."/>
								</div>
							 </c:forEach>
							</div>
							<a class="carousel-control-prev" href="#carouselExampleControls"
								role="button" data-slide="prev"> <span
								class="carousel-control-prev-icon" aria-hidden="true"></span> <span
								class="sr-only">Previous</span>
							</a> <a class="carousel-control-next" href="#carouselExampleControls"
								role="button" data-slide="next"> <span
								class="carousel-control-next-icon" aria-hidden="true"></span> <span
								class="sr-only">Next</span>
							</a>
						</div>

					</div>
					<div class="col-md-6 mt-4">

						<div class="col mt-4">
							<h5 class="side-text">${product.libelle}</h5>
							<div class="d-flex flex-row">
								<div class="ratings mr-2">
									<i class="fa fa-star"></i><i class="fa fa-star"></i><i
										class="fa fa-star"></i><i class="fa fa-star"></i>
								</div>
								<span>310</span>
							</div>

							<div class="mt-1  spec-1">
								<c:forEach items="${product.categories}" var="category">
									<span>${category.name}</span>
									<span class="dot"></span>

								</c:forEach>


							</div>
							<p class="text-justify  para mb-0">
								${product.description} <br><br>
							</p>
							<div class="align-items-center align-content-center ">
								<div class="d-flex flex-row align-items-center">
									<h4 class="mr-1">${product.price}</h4>
									<span class="strike-text">${product.oldPrice}</span>
									<h6 class="text-success ml-4">Free shipping</h6>
								</div>
								<div class="d-flex flex-column mb-4">

									<button type="button"
										class="btn btn-outline-primary btn-sm mt-2"
										data-toggle="modal" data-target="#carModal${product.id}">Ajouter
										au pannier</button>

									<!-- ------------------- CART MODAL ------------------ -->

									<div class="modal fade" id="carModal${product.id}"
										data-backdrop="static" data-keyboard="false" tabindex="-1"
										aria-labelledby="staticBackdropLabel" aria-hidden="true">
										<div class="modal-dialog modal-dialog-centered">
											<div class="modal-content">
												<div class="modal-header">

													<button type="button" class="close" data-dismiss="modal"
														aria-label="Close">
														<span aria-hidden="true"> &times;
														</span>
													</button>
												</div>
												<div class="modal-body">
													<form action="cart" method="get">
														<div class="form-group">
															<label for="qty">quantity</label> <input type="number"
																min="1" class="form-control-file" name="qty" id="qty" />
															<input type="number" hidden="hidden"
																value="${product.id}" class="form-control-file"
																name="id" /> <input type="text" hidden="hidden"
																class="form-control-file" name="action" value="add" /> <input
																type="text" hidden="hidden" class="form-control-file"
																name="page" value="index" />
														</div>
												</div>
												<div class="modal-footer">

													<button type="submit" class="btn btn-primary btn-sm">Ajouter</button>
												</div>
												</form>
											</div>
										</div>
									</div>
									<!-- ------------------------------------------------  -->
								</div>

							</div>
						</div>

					</div>

					<!-- -------------------------------------->

				</div>
			</div>

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