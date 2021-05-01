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

	<div class="container-fluid mt-2 mb-2 pl-4 pr-4"
		style="min-height: 440px">
		
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
		<div class="row ">
			<!-- ------------ PAGE CONTENT  ----------------->

			<div class="col-md-3 bg-white border rounded">
				<h5 class="text-center mt-2">
					<strong>Prix Total : <c:if
							test="${not empty shoppingCart}">${shoppingCart.total} $</c:if> <c:if
							test="${empty shoppingCart}">0$</c:if>

					</strong>
				</h5>
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
					<a class="btn btn-outline-danger bnt-sm form-control mb-4" href="<c:url value ="/cart?action=show&page=panier"/>">Retour au panier</a>
			</div>

			<div class="col-md-9">
				<div class="row bg-white border rounded" style="min-height: 440px">
                    
                    <c:if test="${emptyCart != null}">
                    
                        <div class="col-6 offset-md-4 " style="margin-top: 100px;">
				     <img src="static/images/empty-cart.png" alt="" height="200px" width="200px" />
				     </br>
				     <strong class="text-center">${emptyCart}</strong>
				   </div>
                    
                    </c:if>
                    
                    
                    <c:if test="${canPass == false}">
                    <div class="col-md-12 order-md-1" style="margin-top: 140px">
                     <div class="row">
								<div class="col-6">

									<div class="card">
										<div class="card-body">
											<form action="order" method="get">

												<fieldset>
													<legend class="text-center">no inscrit</legend>
													<input type="text" hidden="hidden" value="none"
														name="email" /> <input type="text" hidden="hidden"
														value="verify" name="action" /> <input type="number"
														hidden="hidden" value="${shoppingCart.id }" name="id" />
													<button class="btn btn-sm btn-success  form-control" style="margin-top: 40px"
														type="submit">Envoyer</button>
												</fieldset>
											</form>
										</div>
									</div>

								</div>
								<div class="col-6">
									<div class="card" >
										<div class="card-body">
										<form action="order" method="get">
										<fieldset>
											<legend class="text-center">deja inscrit</legend>
											<input type="email" name="email" placeholder="email" class="form-control-sm form-control"
												required="true" /> 
												<c:if test="${userNotFound != null}">
												<div style="color: red" style="width: 100%;">${userNotFound}</div>
												</c:if>
												<input type="text" hidden="hidden"
												value="verify" name="action" /> <input type="number"
												hidden="hidden" value="${shoppingCart.id }" name="id" />
											<button class="btn btn-sm btn-success form-control" 
												type="submit">Envoyer</button>
										</fieldset>
									</form>
											
										</div>
									</div>

								</div>
							</div>
                    </div>
                    </c:if>
                    
                    <c:if test="${exist == 0 && canPass== true}">
                    <div class="col-2 offset-1"></div>
                   
                    
					<div class="col-md-6 order-md-1" style="border: 1ps black solid">
						<h4 class="mb-3 side-text text-center mt-4" style="font-size: 20px">les informations d'order</h4>
						<form class="needs-validation" novalidate="" action="order" method="post">
							<div class="row">
								<div class="col-md-6 mb-3">
									<label for="firstName">Nom</label> <input type="text"
										class="form-control" id="firstName" placeholder="" value="" name="nom"
										required="true">
										<div class="invalid-feedback">Valid first name is
											required.</div>
								</div>
								<div class="col-md-6 mb-3">
									<label for="lastName">Prenom</label> <input type="text"
										class="form-control" id="lastName" placeholder="" value="" name="prenom"
										required="true">
										<div class="invalid-feedback">Valid last name is
											required.</div>
								</div>
							</div>

							<div class="mb-3">
								<label for="username">Email</label>
								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text">@</span>
									</div>
									<input type="text" class="form-control" id="username" name="email"
										placeholder="Username" required="true"> 
										<c:if test="${response.status == 2}">
										<div style="color: red" style="width: 100%;">${response.message}</div>
										</c:if>
								</div>
							</div>
							
							<div class="row">
								<div class="col-md-6 mb-3">
									<label for="pwd">Mot de pass</label> <input type="password"
										class="form-control" id="pwd" placeholder=""  name="password"
										required="true">
										<c:if test="${response.status == 3}">
										<div style="color: red">${response.message}"</div>
										</c:if>
								</div>
								<div class="col-md-6 mb-3">
									<label for="cpwd">Confirmer mot de pass</label> <input type="password"
										class="form-control" id="cpwd" placeholder=""  name="cpassword"
										required="true">
										<c:if test="${response.status == 1}">
										<div style="color: red"> ${response.message}
										</div>
										</c:if>
								</div>
							</div>
							

							<div class="mb-3">
								<label for="tel">Telephone <span class="text-muted">(Optional)</span></label>
								<input type="text" class="form-control" id="tel" name="tel"
									placeholder="0615457879">
									<div class="invalid-feedback">Please enter a valid phone
										number</div>
							</div>

							<div class="mb-3">
								<label for="address">Address</label> <input type="text"
									class="form-control" id="address" placeholder="1234 fes morocco"
									 name="address1" required="required">
									<div class="invalid-feedback">Please enter your shipping
										address.</div>
							</div>

							<div class="mb-3">
								<label for="address2">Address 2 <span class="text-muted">(Optional)</span></label>
								<input type="text" class="form-control" id="address2" name="address2"
									placeholder="Apartment or suite">
							</div>

							<div class="row">
								<div class="col-md-5 mb-3">
									<label for="country">Pays</label> <select
										class="custom-select d-block w-100" id="country" required="" name="pays">
										<option value="">Choisi...</option>
										<option value="Morocco">Morocco</option>
									</select>
									<div class="invalid-feedback">Please select a valid
										country.</div>
								</div>
								<div class="col-md-4 mb-3">
									<label for="state">Ville</label> <select
										class="custom-select d-block w-100" id="state" required="true" name="ville">
										<option value="">Choisi...</option>
										<option value="Fes">Fes</option>
										<option value="Rabat">Rabat</option>
										<option value="Casablanca">Casablanca</option>
										<option value="Tangier">Tangier</option>
									</select>
									<div class="invalid-feedback">Please provide a valid
										state.</div>
								</div>
								<div class="col-md-3 mb-3">
									<label for="zip">Zip</label> <input type="text"
										class="form-control" id="zip" placeholder="" required="true" name="zip">
										<div class="invalid-feedback">Zip code required.</div>
								</div>
							</div>
							<hr class="mb-4">
								
								<input hidden="hidden" type="numbder" name="id" value="${shoppingCart.id}"/>	
								<input hidden="hidden" type="text" name="action" value="add"/>	
										<button class="btn btn-primary btn-lsm btn-block mb-2" type="submit">Continue
											l'order</button>
						</form>
				     </div>
                    </c:if>
                    
                    
                    <c:if test="${exist == 1 && canPass == true}">
                    <div class="col-2 offset-1"></div>
                    
					<div class="col-md-6 order-md-1" style="border: 1ps black solid">
						<h4 class="mb-3 side-text text-center mt-4" style="font-size: 20px">les informations d'order</h4>
						<form class="needs-validation" novalidate="" action="order" method="post">
							<div class="row">
								<div class="col-md-6 mb-3">
									<label for="firstName">Nom</label> <input type="text"
										class="form-control" id="firstName" placeholder="" value="${user.nom}" name="nom" 
										
										readonly="readonly" required="true">
										<div class="invalid-feedback">Valid first name is
											required.</div>
								</div>
								<div class="col-md-6 mb-3">
									<label for="lastName">Prenom</label> <input type="text"
										class="form-control" id="lastName" placeholder="" value="${user.prenom}" name="prenom"
										required="true" readonly="readonly">
										<div class="invalid-feedback">Valid last name is
											required.</div>
								</div>
							</div>

							<div class="mb-3">
								<label for="username">Email</label>
								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text">@</span>
									</div>
									<input type="text" class="form-control" id="username" name="email" value="${user.email}"
										readonly="readonly" placeholder="Username" required="true"> 
										<c:if test="${response.status == 2}">
										<div style="color: red" style="width: 100%;">${response.message}</div>
										</c:if>
								</div>
							</div>
							
							<div class="row">
								<div class="col-md-6 mb-3">
									<label for="pwd">Mot de pass</label> <input type="password" disabled="disabled"
										class="form-control" id="pwd" placeholder=""  name="password" value="p4444wd"
										required="true">
										<c:if test="${response.status == 3}">
										<div style="color: red">${response.message}"</div>
										</c:if>
								</div>
								<div class="col-md-6 mb-3">
									<label for="cpwd">Confirmer mot de pass</label> <input type="password" disabled="disabled"
										 value="pwdchjhh" class="form-control" id="cpwd" placeholder=""  name="cpassword"
										required="true">
										<c:if test="${response.status == 1}">
										<div style="color: red"> ${response.message}
										</div>
										</c:if>
								</div>
							</div>
							

							<div class="mb-3">
								<label for="tel">Telephone <span class="text-muted">(Optional)</span></label>
								<input type="text" class="form-control" id="tel" name="tel"
									placeholder="0615457879">
									<div class="invalid-feedback">Please enter a valid phone
										number</div>
							</div>

							<div class="mb-3">
								<label for="address">Address</label> <input type="text"
									class="form-control" id="address" placeholder="1234 fes morocco"
									 name="address1" required="required">
									<div class="invalid-feedback">Please enter your shipping
										address.</div>
							</div>

							<div class="mb-3">
								<label for="address2">Address 2 <span class="text-muted">(Optional)</span></label>
								<input type="text" class="form-control" id="address2" name="address2"
									placeholder="Apartment or suite">
							</div>

							<div class="row">
								<div class="col-md-5 mb-3">
									<label for="country">Pays</label> <select
										class="custom-select d-block w-100" id="country" required="" name="pays">
										<option value="">Choisi...</option>
										<option value="Morocco">Morocco</option>
									</select>
									<div class="invalid-feedback">Please select a valid
										country.</div>
								</div>
								<div class="col-md-4 mb-3">
									<label for="state">Ville</label> <select
										class="custom-select d-block w-100" id="state" required="true" name="ville">
										<option value="">Choisi...</option>
										<option value="Fes">Fes</option>
										<option value="Rabat">Rabat</option>
										<option value="Casablanca">Casablanca</option>
										<option value="Tangier">Tangier</option>
									</select>
									<div class="invalid-feedback">Please provide a valid
										state.</div>
								</div>
								<div class="col-md-3 mb-3">
									<label for="zip">Zip</label> <input type="text"
										class="form-control" id="zip" placeholder="" required="true" name="zip">
										<div class="invalid-feedback">Zip code required.</div>
								</div>
							</div>
							<hr class="mb-4">
								
								<input hidden="hidden" type="numbder" name="id" value="${shoppingCart.id}"/>	
								<input hidden="hidden" type="text" name="action" value="add"/>	
										<button class="btn btn-primary btn-lsm btn-block mb-2" type="submit">Continue
											l'order</button>
						</form>
				     </div>
                    </c:if>
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