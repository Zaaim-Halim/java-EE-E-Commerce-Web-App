<?xml version="1.0" encoding="utf-8" ?>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<head>
<meta http-equiv="Content-Type" content="text/html; utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1">
 <title>Acceuil</title>
 <link href="static/css/bootstrap.css" rel="stylesheet"/> 
 <link href="static/css/main.css" rel="stylesheet"/> 
 <link href="static/fonts/fontawesome/css/font-awesome.css " rel="stylesheet"/>
 <link href="static/fonts/fontawesome/css/all.css " rel="stylesheet"/>
 <link rel="icon" href="static/images/logo.png" type="image/x-icon" />

</head>
<body>
  <c:import url="/WEB-INF/templates/Fragments/menuE.jsp">
    <c:param name="page" value="acceuil"/>
  </c:import>
  
  <div class="container-fluid mt-2 mb-2 pl-4 pr-4">
		<div class=" row">
			<div class="col-md-3 bg-white border rounded">
				<form action="search" method="get">
					<h5 class="mt-2 side-text">filtre par gategory</h5>
					<div class="mb-3">
					<input type="text" class="form-control-sm is-valid" name="action"
							value="byCategoryCretiria" hidden="hidden" />
							
						<input type="text" class="form-control-sm is-valid" placeholder="category"
							id="exampleInputEmail1"  name="creteria" />

					</div>

					<button type="submit"
						class="btn btn-outline-success btn-sm form-control">chercher</button>
				</form>
				<hr />
				<form action="search" method="get">
					<h5 class="side-text">fillter par le prix</h5>
					<div class="mb-3">
                         <input type="text" class="form-control-sm" name="action" hidden="hidden"
							value="Byprice" />
						<input type="number" class="form-control-sm is-valid" name="price"
							id="exampleInputPassword1" placeholder="prix" />
							
					  
					</div>
					<button type="submit"
						class="btn btn-outline-success btn-sm form-control">chercher</button>
				</form>
				<hr />
				<form action="search" method="get">
					<h5 class="side-text">fillter par deux prix</h5>
					<div class="mb-3">
					<div class="form-row">
                         <div class="col-md-6 mb-2">
                         <input type="text" class="form-control-sm" name="action" hidden="hidden"
							value="btnprice" />
						<input type="number" class="form-control-sm is-valid" name="price1"
						id="exampleInputPassword1" placeholder="prix2" />
						</div>
						<div class="col-md-6 mb-2">
							
							<input type="number" class="form-control-sm is-valid" name="price2"
							id="exampleInputPasswork;" placeholder="prix1" />
						</div>
					</div>
					</div>
					<button type="submit"
						class="btn btn-outline-success btn-sm form-control">chercher</button>
				</form>
               <hr>
			</div>
			<div class="col-md-9">
				<c:forEach items="${products}" var="product">

					<div class="row p-2 bg-white border rounded">
						<div class="col-md-3 mt-1">
							<img class="img-fluid img-responsive rounded product-image"
								src="data:image/jpeg;base64,${product.image}" />
						</div>
						<div class="col-md-6 mt-1">
							<h5 class="side-text">${product.libelle}</h5>
							<div class="d-flex flex-row">
								<div class="ratings mr-2">
									<i class="fa fa-star"></i><i class="fa fa-star"></i><i
										class="fa fa-star"></i><i class="fa fa-star"></i>
								</div>
								<span>310</span>
							</div>
							<div class="mt-1 mb-1 spec-1">
								<c:forEach items="${product.categories}" var="category">
									<span>${category.name}</span>
									<span class="dot"></span>

								</c:forEach>


							</div>
							<!-- <div class="mt-1 mb-1 spec-1">
							<span>Unique design</span><span class="dot"></span><span>For
								men</span><span class="dot"></span><span>Casual<br></span>
						</div> -->
							<p class="text-justify text-truncate para mb-0">
								${product.description} <br><br>
							</p>
						</div>
						<div
							class="align-items-center align-content-center col-md-3 border-left mt-1">
							<div class="d-flex flex-row align-items-center">
								<h4 class="mr-1">${product.price}</h4>
								<span class="strike-text">${product.oldPrice}</span>
							</div>
							<h6 class="text-success">Free shipping</h6>
							<div class="d-flex flex-column mt-4">
								<a class="btn btn-primary btn-sm"
									href="<c:url value ="/index?action=Pdetails&Pid=${product.id}"/>">Details</a>
								<button type="button" class="btn btn-outline-primary btn-sm mt-2" data-toggle="modal" data-target="#carModal${product.id}">Ajouter
									au pannier </button>
							</div>
						</div>

						<!-- ------------------- CART MODAL ------------------ -->

						<div class="modal fade" id="carModal${product.id}" data-backdrop="static"
							data-keyboard="false" tabindex="-1"
							aria-labelledby="staticBackdropLabel" aria-hidden="true">
							<div class="modal-dialog modal-dialog-centered">
								<div class="modal-content">
									<div class="modal-header">
										
										<button type="button" class="close" data-dismiss="modal"
											aria-label="Close">
											<span aria-hidden="true">
												&times;
											</span>
										</button>
									</div>
									<div class="modal-body">
									<form action="cart" method="get">
                                         <div class="form-group">
                                          <label for="qty">quantity</label>
                                          <input type="number" min="1" class="form-control form-control-sm" name="qty" id="qty"/>
                                           <input type="number"  hidden="hidden" value="${product.id}" class="form-control-file" name="id"/>
                                            <input type="text"  hidden="hidden" class="form-control-file" name="action" value="add"/>
                                              <input type="text"  hidden="hidden" class="form-control-file" name="page" value="index"/>
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

				</c:forEach>
			</div>
		 
		</div>
		<div class="d-flex justify-content-end to-the-end">
		<nav aria-label="Page navigation example">
		<ul class="pagination">
			<li class="page-item"><a class="page-link" href="#">Previous</a></li>
			<li class="page-item"><a class="page-link" href="#">1</a></li>
			<li class="page-item"><a class="page-link" href="#">2</a></li>
			<li class="page-item"><a class="page-link" href="#">3</a></li>
			<li class="page-item"><a class="page-link" href="#">Next</a></li>
		</ul>
		</nav>
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