<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<header class="section-header">
	<section class="header-main border-bottom">
		<div class="container-fluid">
			<div class="row align-items-center">
				<div class="col-lg-3 col-sm-4 col-md-4 col-5">
					<a href="#" class="brand-wrap" data-abc="true"> <!-- <img class="logo" src="http://ampexamples.com/data/upload/2017/08/bootstrap2_logo.png"> -->
						<span class="logo">Ecommerce</span>
					</a>
				</div>
				<div class="col-lg-4 col-xl-5 col-sm-8 col-md-4 d-none d-md-block">
					<form action="search" class="search-wrap" action="get">
						<div class="input-group w-100">
							<input type="text" class="form-control search-form" name="creteria"
								style="width: 55%;" placeholder="Search">
							<input type="text" name="action" value="byCretiria" hidden="hidden"/>	
							<div class="input-group-append">
								<button class="btn btn-success search-button" type="submit">
									<i class="fa fa-search"></i>
								</button>
							</div>
						</div>
					</form>
				</div>
				<div class="col-lg-5 col-xl-4 col-sm-8 col-md-4 col-7">
					<div class="d-flex justify-content-end">
						<!-- <span class="vl"> </span> --> <a href="<c:url value ="/cart?action=show&page=panier"/>"
							class="nav-link widget-header">
							<c:if test="${shoppingCart.itemsNumber != null}">
							 <span class="cartCount">${shoppingCart.itemsNumber}</span>
							</c:if>
							<c:if test="${shoppingCart.itemsNumber == null}">
							 <span class="cartCount">0</span>
							</c:if>
							
						    <i
							class="fas fa-shopping-cart"></i></a>&nbsp;	&nbsp;	&nbsp; <!--<span class="vl"></span>  -->
						<!-- <div class="dropdown btn-group">
							<a class="nav-link nav-icons" href="#"><i
								class="fas fa fa-bell"></i></a>

						</div> -->
						<!--  <span class="vl"> </span> --><!-- <a class="btn btn-success" href="#"
							data-toggle="modal" data-target="#login-modal"><span
							class="login">Login</span></a>  --> <!-- <span class="vl"> </span> -->
							<a class="btn btn-success" data-toggle="tooltip" title="Login" data-bs-placement="bottom" href="<c:url value ="/login"/>"><i class="fa fa-user fa-lg"></i><!-- <span
							class="login">Login</span>--></a>

						<!-- Modal -->
						<div class="modal fade" id="login-modal" tabindex="-1"
							aria-labelledby="login-modal" aria-hidden="true">
							<div class="modal-dialog modal-dialog-centered">
								<div class="modal-content">

									<div class="card card0 border-0">
										<div class="row "></div>
										<div class="col-lg-12">
											<div class="card2 card border-0 px-4 py-5">
												<div class="row mb-4 px-3">
													<h6 class="mb-0 mr-4 mt-2">Sign in with</h6>
													<div class="facebook text-center mr-3">
														<div class="fa fa-facebook"></div>
													</div>
													<div class="twitter text-center mr-3">
														<div class="fa fa-twitter"></div>
													</div>
													<div class="linkedin text-center mr-3">
														<div class="fa fa-linkedin"></div>
													</div>
												</div>
												<div class="row px-3 mb-4">
													<div class="line"></div>
													<small class="or text-center">Or</small>
													<div class="line"></div>
												</div>
												<div class="row px-3">
													<label class="mb-1">
														<h6 class="mb-0 text-sm">Email Address</h6>
													</label> <input class="mb-4" type="text" name="email"
														placeholder="Enter a valid email address">
												</div>
												<div class="row px-3">
													<label class="mb-1">
														<h6 class="mb-0 text-sm">Password</h6>
													</label> <input type="password" name="password"
														placeholder="Enter password">
												</div>
												<div class="row px-3 mb-4">
													<div
														class="custom-control custom-checkbox custom-control-inline">
														<input id="chk1" type="checkbox" name="chk"
															class="custom-control-input"> <label for="chk1"
															class="custom-control-label text-sm">Remember me</label>
													</div>
													<a href="#" class="ml-auto mb-0 text-sm">Forgot
														Password?</a>
												</div>
												<div class="row mb-3 px-3">
													<button type="submit" class="btn btn-blue text-center">Login</button>
												</div>
												<div class="row mb-4 px-3">
													<small class="font-weight-bold">Don't have an
														account? <a class="text-danger ">Register</a>
													</small>
												</div>
											</div>
										</div>
									</div>

								</div>

							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<nav class="navbar navbar-expand-md navbar-main navtt border-bottom">
		<div class="container-fluid">
			<button class="navbar-toggler collapsed" type="button"
				data-toggle="collapse" data-target="#dropdown6"
				aria-expanded="false">
				<span class="navbar-toggler-icon"> </span>
			</button>
			<form class="d-md-none my-2" action="search" method="get">
				<div class="input-group">
					<input type="text" class="form-control search-form" name="creteria"
								style="width: 55%;" placeholder="Search">
							<input type="text" name="action" value="byCretiria" hidden="hidden"/>	
					<div class="input-group-append">
						<button type="submit" class="btn btn-secondary">
							<i class="fa fa-search"></i>
						</button>
					</div>
				</div>
			</form>
			<div class="navbar-collapse collapse" id="dropdown6" style="">
				<ul class="navbar-nav mr-auto">
					<li class="nav-item"><a
						class='nav-link ${param.page == "acceuil"?"active":""}' href="<c:url value ="/index"/>"
						data-abc="true">Acceuil</a></li>
					<li class="nav-item"><a
						class='nav-link ${param.page == "pannier"?"active":""}' href="<c:url value ="/cart?action=show&page=panier"/>"
						data-abc="true">Panier</a></li>
					<li class="nav-item">
					   <c:if test="${shoppingCart != null}">
					     <a
						class='nav-link ${param.page == "paiement"?"active":""}' href="<c:url value ="/order?id=${shoppingCart.id}"/>"
						data-abc="true">Order</a>
					 </c:if>
					 <c:if test="${shoppingCart == null}">
					     <a
						class='nav-link ${param.page == "paiement"?"active":""}' href="<c:url value ="/order?id=none"/>"
						data-abc="true">Order</a>
					 </c:if>
						
						</li>
					<li class="nav-item"><a
						class='nav-link ${param.page == "about"?"active":""}' href="<c:url value ="/about"/>"
						data-abc="true">a propos de nous</a></li>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="" data-toggle="dropdown"
						data-abc="true" aria-expanded="false">Categories</a>
						<div class="dropdown-menu">
						  <c:forEach items="${categories}" var="category">
						   	<a class="dropdown-item" href="<c:url value ='/search?action=category&name=${category.name}'/>" data-abc="true">${category.name}</a>
						  </c:forEach>
						</div></li>

				</ul>
			</div>
		</div>
	</nav>
</header>