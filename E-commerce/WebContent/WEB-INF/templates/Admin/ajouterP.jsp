<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
<meta name="description"
	content="Vali is a responsive and free admin theme built with Bootstrap 4, SASS and PUG.js. It's fully customizable and modular.">
<!-- Twitter meta-->
<meta property="twitter:card" content="summary_large_image">
<meta property="twitter:site" content="@pratikborsadiya">
<meta property="twitter:creator" content="@pratikborsadiya">
<!-- Open Graph Meta-->
<meta property="og:type" content="website">
<meta property="og:site_name" content="Vali Admin">
<meta property="og:title" content="Vali - Free Bootstrap 4 admin theme">
<meta property="og:url"
	content="http://pratikborsadiya.in/blog/vali-admin">
<meta property="og:image"
	content="http://pratikborsadiya.in/blog/vali-admin/hero-social.png">
<meta property="og:description"
	content="Vali is a responsive and free admin theme built with Bootstrap 4, SASS and PUG.js. It's fully customizable and modular.">
<title>Ecommerce | Admin</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Main CSS-->
<link rel="stylesheet" type="text/css" href="static/Admin/css/main.css">
<!-- Font-icon css-->
<link rel="stylesheet" type="text/css"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="icon" href="static/images/logo.png" type="image/x-icon" />
</head>
<body class="app sidebar-mini rtl">
	<!-- Navbar-->
	<c:import url="/WEB-INF/templates/Fragments/header.jsp">
		<c:param name="page" value="aP" />
	</c:import>
	<!-- Sidebar menu-->
	<div class="app-sidebar__overlay" data-toggle="sidebar"></div>
	<c:import url="/WEB-INF/templates/Fragments/aside.jsp">
		<c:param name="page" value="ajouterP" />
	</c:import>
	<main class="app-content">
		<div class="app-title">
			<div>
				<h1>
					<i class="fa fa-dashboard"></i> Ajouter Produit
				</h1>
				<p>donner les details d'un produit</p>
			</div>
			<ul class="app-breadcrumb breadcrumb">
				<li class="breadcrumb-item"><i class="fa fa-home fa-lg"></i></li>
				<li class="breadcrumb-item"><a
					href="<c:url value ="/Admin-index"/>">Acceuill</a></li>
			</ul>
		</div>
		<c:if test='${response.status == 200}'>
			<div class="alert alert-info alert-dismissible fade show text-center"
				role="alert">
				<strong>succes!</strong> ${response.message} 
				<button type="button" class="close" data-dismiss="alert"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
		</c:if>
		<c:if test='${response.status == 500}'>
			<div class="alert alert-danger alert-dismissible fade show text-center"
				role="alert">
				<strong>error!</strong> ${response.message}
				!
				<button type="button" class="close" data-dismiss="alert"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
		</c:if>

		<div class="bs-component">
			<ul class="nav nav-tabs">
				<li class="nav-item"><a class="nav-link active"
					data-toggle="tab" href="#addP"><b>Ajouter Produit</b></a></li>
				<li class="nav-item"><a class="nav-link" data-toggle="tab"
					href="#addCategory"><b>Ajouter Category</b></a></li>
				<li class="nav-item"><a class="nav-link" data-toggle="tab"
					href="#addPicture"><b>Ajouter Produit Images</b></b></a></li>
			</ul>
			<div class="tab-content" id="myTabContent">
				<div class="tab-pane fade active show" id="addP">

					<div class="row">
						<div class="col-md-12">
							<div class="tile">
								<div class="tile-body text-center">Tappez les informations
									d'un produit</div>

								<div class="row">
									<div class="col-lg-12">
										<form action="Admin-Produit" method="post"
											enctype="multipart/form-data">
											<div class="form-group">
												<label for="exampleFormControlInput1">Nom Produit</label> <input
													type="text" class="form-control"
													id="exampleFormControlInput1" name="libelle"
													placeholder="titre de produit" />
											</div>
											<div class="form-group">
												<label for="qty">Quantite</label> <input type="number"
													class="form-control" id="qty" min="1"
													placeholder="quantite" name="qty" />
											</div>
											<div class="form-group">
												<label for="prix">Prix</label> <input type="number"
													class="form-control" id="qty" min="1" placeholder="prix"
													name="prix" />
											</div>
											<div class="form-group ">
												<!--<label for="prix">Category</label> <input type="text"
					                        	class="form-control" id="category"  placeholder="category"  name="category"/>  -->
												<div class="col-md-12">
													<div class="tile">
														<div class="tile-body">
															<label for="category">Category</label> <select  tabindex="1"
																class="form-control" id="demoSelect" multiple="multiple"
																name="category">
                                                                  <c:forEach items = "${categories}"  var="category">
																   <option value ="${category.name}">${category.name}</option>
																 </c:forEach>

															</select>

														</div>
													</div>
												</div>
											</div>
											<div class="custom-file mb-2">
												<input type="file" class="custom-file-input" id="customFile"
													name="file"> <label class="custom-file-label"
													for="customFile">Produit image</label>
											</div>

											<div class="form-group">
												<label for="exampleFormControlTextarea1">Produit
													Description</label>
												<textarea class="form-control"
													id="exampleFormControlTextarea1" rows="3"
													name="description"></textarea>
											</div>
											<input type="text" hidden="hidden" name ="action" value="addP"/>
											<button type="submit" class="btn btn-primary mb-2">Ajouter
												Produit</button>
										</form>
									</div>
								</div>
							</div>
						</div>
					</div>
                 </div>
                 
                 <div class="tab-pane fade" id="addCategory">
					<div class="row">
						<div class="col-md-12">
							<div class="tile">
								<div class="tile-body text-center">Tappez les informations
									d'une category</div>

								<div class="row">
									<div class="col-lg-12">
										<form action="Admin-Produit" method="post">
											<div class="form-group">
												<label class="form-control-label" for="inputSuccess1">titre</label>
												<input class="form-control " id="inputValid" type="text"
													name="name">

											</div>

											<div class="form-group">
                                             <input type="text" hidden="hidden" name ="action" value="addCategory"/>
												<input class=" btn btn-primary" id="inputSmall"
													type="submit" value="ajouter">
											</div>

										</form>
									</div>
								</div>
							</div>
						</div>

					</div>
                 </div>
					<div class="tab-pane fade" id="addPicture">
					
					<div class="row">
						<div class="col-md-12">
							<div class="tile">
								<div class="tile-body text-center">Tappez les informations
									d'une image</div>

								<div class="row mt-2">
									<div class="col-lg-12">
										<form action="Admin-Produit" method="post"
										enctype="multipart/form-data">
											<div class="form-group">
												<label for="prix">Product</label> <select
													class="form-control"
													name="product-id">
													<c:forEach items="${products}" var="product">
														<option value="${product.id}">${product.libelle}</option>
													</c:forEach>

												</select>
											</div>
											<div class="form-group">
												<div class="custom-file mb-2">
													<input type="file" class="custom-file-input"
														id="customFile" name="file"> <label
														class="custom-file-label" for="customFile">Produit
														image</label>
												</div>
											</div>

											<div class="form-group">
                                             <input type="text" hidden="hidden" name ="action" value="addAlbum"/>
												<input class=" btn btn-primary" id="inputSmall"
													type="submit" value="ajouter">
											</div>

										</form>
									</div>
								</div>
							</div>
						</div>

					</div> 
					
					</div>

				</div>
                 
				</div>
				
			</div>
	</main>
	<!-- Essential javascripts for application to work-->
	<script src="static/Admin/js/jquery-3.2.1.min.js"></script>
	<script src="static/Admin/js/popper.min.js"></script>
	<script src="static/Admin/js/bootstrap.min.js"></script>
	<script src="static/Admin/js/main.js"></script>
	<!-- The javascript plugin to display page loading on top-->
	<script src="static/Admin/js/plugins/pace.min.js"></script>
	<script type="text/javascript"
		src="static/Admin/js/plugins/select2.min.js"></script>

	<!-- Google analytics script-->
	<script type="text/javascript">
      if(document.location.hostname == 'pratikborsadiya.in') {
      	(function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
      	(i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
      	m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
      	})(window,document,'script','//www.google-analytics.com/analytics.js','ga');
      	ga('create', 'UA-72504830-1', 'auto');
      	ga('send', 'pageview');
      }
      
      $('#demoSelect').select2();
    </script>

</body>
</html>