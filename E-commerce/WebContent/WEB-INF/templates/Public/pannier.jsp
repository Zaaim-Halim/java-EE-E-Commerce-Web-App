<?xml version="1.0" encoding="utf-8" ?>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Pannier</title>
<link href="static/css/bootstrap.css" rel="stylesheet" />
<link href="static/css/main.css" rel="stylesheet" />
<link href="static/fonts/fontawesome/css/font-awesome.css "
	rel="stylesheet" />
<link href="static/fonts/fontawesome/css/all.css " rel="stylesheet" />
<link rel="icon" href="static/images/logo.png" type="image/x-icon" />
</head>
<body>
	<c:import url="/WEB-INF/templates/Fragments/menuE.jsp">
		<c:param name="page" value="pannier" />
	</c:import>

	<div class="container-fluid mt-2 mb-2 pl-4 pr-4" style="min-height: 440px">
		<div class="row ">
			<!-- ------------ PAGE CONTENT  ----------------->

			<div class="col-md-3 bg-white border rounded">
				<h5 class="text-center mt-2"><strong>Prix Total :
					<c:if test="${not empty shoppingCart}">${shoppingCart.total} $</c:if> 
					<c:if test="${empty shoppingCart}">0$</c:if>
					
					</strong></h5>
				<hr />
				<ul class="list-group">
					<c:forEach items="${shoppingCart.cartItems}" var="cartItem">
						<li
							class="list-group-item d-flex justify-content-between align-items-center">
							${cartItem.product.libelle} <span
							class="badge badge-primary badge-pill">${cartItem.qty}</span>
						</li>
					</c:forEach>

				</ul>

				<hr>
				 <c:if test="${not empty shoppingCart}">
				   <a class="btn btn-success bnt-sm form-control mb-4" href="<c:url value ="/order?action=preVerify&id=${shoppingCart.id}"/>">Order</a>
				 </c:if>
					
					
			</div>

			<div class="col-md-9">
				<div class="row bg-white border rounded" style="min-height: 440px">
				  
				  <c:if test="${empty shoppingCart}">
				   <div class="col-6 offset-md-4 " style="margin-top: 100px;">
				     <img src="static/images/empty-cart.png" alt="" height="200px" width="200px" />
				     </br>
				     <strong class="text-center ml-4">&nbsp;
												&nbsp;&nbsp;
												Panier vide!</strong>
				   </div>
				  
				  </c:if>
				   
					<c:forEach items="${shoppingCart.cartItems}" var="cartItem">
						<div class="col-md-4  mt-2">
							<div class="card shadow-sm">
								<img class="bd-placeholder-img card-img-top" width="100%"
									height="230px"
									src="data:image/jpeg;base64,${cartItem.product.image}" />




								<div class="card-body">
									<p class="card-text text-justify text-truncate para mb-2">${cartItem.product.description}</p>
									<span class="badge badge-primary mb-2">prix :
										${cartItem.product.price}</span>
									<span class="badge badge-success  mb-2 ml-2">quantité :
										${cartItem.qty}</span>
									<div class="d-flex justify-content-between align-items-center">

										<div class="btn-group">
											<a class="btn btn-sm btn-outline-danger "
												href="<c:url value ="/cart?action=deleteItem&page=panier&id=${shoppingCart.id}&itemId=${cartItem.id}"/>">
												&nbsp;
												&nbsp;
												&nbsp;Supprimer&nbsp;
												&nbsp;
												&nbsp;
											</a>
											<button type="button" class="btn btn-sm btn-outline-success"
												data-toggle="modal" data-target="#carModal${cartItem.id}">modifier
												quantité</button>
										</div>
										<!-- ------------------- CART MODAL QTY ------------------ -->

										<div class="modal fade" id="carModal${cartItem.id}"
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
														<form action="cart" method="post">
															<div class="form-group">
																<label for="qty">quantité</label> <input type="number"
																	min="1" class="form-control-file" name="qty" id="qty"
																	value="${cartItem.qty}" /> <input type="number" class="form-control-sm form-control"
																	hidden="hidden" value="${shoppingCart.id}"
																	class="form-control-file" name="id" /> <input
																	type="text" hidden="hidden" class="form-control-file"
																	name="action" value="updateQty" /> <input type="text"
																	hidden="hidden" class="form-control-file" name="page"
																	value="panier" /> <input type="number" hidden="hidden"
																	class="form-control-file" name="itemId"
																	value="${cartItem.id}" />
															</div>
													</div>
													<div class="modal-footer">

														<button type="submit" class="btn btn-primary btn-sm">
															&nbsp;
															Modifier
															&nbsp;
														</button>
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
					</c:forEach>

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