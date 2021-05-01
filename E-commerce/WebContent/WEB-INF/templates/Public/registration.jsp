<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Main CSS-->
    <link rel="stylesheet" type="text/css" href="static/Admin/css/main.css">
    <!-- Font-icon css-->
    <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="icon" href="static/images/logo.png" type="image/x-icon" />
    <title>Registration</title>
  </head>
  <body>
    <section class="material-half-bg">
      <div class="cover"></div>
    </section>
    <section class="login-content">
      <div class="logo">
        <h1>MIDVI</h1>
        <c:if test='${response != null}'>
			<div class="alert alert-info alert-dismissible fade show text-center"
				role="alert">
				<strong>succes!</strong> 
			       ${response.message}
				<button type="button" class="close" data-dismiss="alert"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
		</c:if>
      </div>
      <div class="login-box" style="min-width: 350px;
       min-height: 630px;">
        <form class="login-form" action="registration" method="post"
         name="registrationForm" >
          <h3 class="login-head"><i class="fa fa-lg fa-fw fa-user"></i>REGISTRATION</h3>
          <div class="form-group">
            <label class="control-label">NOM</label>
            <input class="form-control" name="nom" type="text" placeholder="nom" autofocus>
          </div>
          <div class="form-group">
            <label class="control-label">PRENOM</label>
            <input class="form-control" name="prenom" type="text" placeholder="prenom" autofocus>
          </div>
          <div class="form-group">
            <label class="control-label">EMAIL</label>
            <input class="form-control" name="email" type="text" placeholder="Email" autofocus>
          </div>
          <div class="form-group">
            <label class="control-label">PASSWORD</label>
            <input class="form-control" name="password" type="password" placeholder="Password">
          </div>
          <div class="form-group">
            <label class="control-label">CONFIRM PASSWORD</label>
            <input class="form-control" name="passwordConfirm" type="password" placeholder="Password">
          </div>
          <div class="form-group">
            <div class="utility">
            
          </div>
          <div class="form-group btn-container">
            <button class="btn btn-primary btn-block" type="submit"><i class="fa fa-user-plus fa-lg fa-fw"></i>REGISTER</button>
          </div>
           <div class="form-group btn-container my-2">
            <a class="btn btn-primary btn-block" href="<c:url value ="/login"/>"><i class="fa fa fa-sign-in fa-lg fa-fw"></i>SE CONNECTER</a>
          </div>
        </form>
        
      </div>
    </section>
    <!-- Essential javascripts for application to work-->
    <script src="static/Admin/js/jquery-3.2.1.min.js"></script>
    <script src="static/Admin/js/popper.min.js"></script>
    <script src="static/Admin/js/bootstrap.min.js"></script>
    <script src="static/Admin/js/main.js"></script>
    <!-- The javascript plugin to display page loading on top-->
    <script src="static/Admin/js/plugins/pace.min.js"></script>
    <script type="text/javascript">
      // Login Page Flipbox control
      $('.login-content [data-toggle="flip"]').click(function() {
      	$('.login-box').toggleClass('flipped');
      	return false;
      });
    </script>
    <script type = "text/javascript">
   
      // Form validation code will come here.
      function validate() {
      
         if( document.registrationForm.nom.value == "" ) {
            alert( "Please provide your name!" );
            document.registrationForm.nome.focus() ;
            return false;
         }
         if( document.registrationForm.email.value == "" ) {
            alert( "Please provide your Email!" );
            document.registrationForm.email.focus() ;
            return false;
         }
         if( document.registrationForm.password.value == "" || isNaN( document.registrationForm.password.value ) ||
            document.registrationForm.password.value.length > 5 ) {
            
            alert( "Please provide valid password." );
            document.registrationForm.password.focus() ;
            return false;
         }
         if( document.registrationForm.passwordConfirm.value == "" ) {
            alert( "Please confirm password!" );
            document.registrationForm.passwordConfirm.focus() ;
            return false;
         }
         if( document.registrationForm.passwordConfirm.value !=  document.registrationForm.passwordConfirm.value) {
             alert( "unmatched passwords !" );
             document.registrationForm.passwordConfirm.focus() ;
             return false;
          }
           if(!validateEmail()) return false;
         return( true );
      }
      function validateEmail() {
          var emailID = document.registrationForm.email.value;
          atpos = emailID.indexOf("@");
          dotpos = emailID.lastIndexOf(".");
          
          if (atpos < 1 || ( dotpos - atpos < 2 )) {
             alert("Please enter correct email")
             document.registrationForm.email.focus() ;
             return false;
          }
          return( true );
       }
</script>
  </body>
</html>