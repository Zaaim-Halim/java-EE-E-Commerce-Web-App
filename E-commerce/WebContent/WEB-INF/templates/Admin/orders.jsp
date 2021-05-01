<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

 <head>
    <meta name="description" content="Vali is a responsive and free admin theme built with Bootstrap 4, SASS and PUG.js. It's fully customizable and modular.">
    <!-- Twitter meta-->
    <meta property="twitter:card" content="summary_large_image">
    <meta property="twitter:site" content="@pratikborsadiya">
    <meta property="twitter:creator" content="@pratikborsadiya">
    <!-- Open Graph Meta-->
    <meta property="og:type" content="website">
    <meta property="og:site_name" content="Vali Admin">
    <meta property="og:title" content="Vali - Free Bootstrap 4 admin theme">
    <meta property="og:url" content="http://pratikborsadiya.in/blog/vali-admin">
    <meta property="og:image" content="http://pratikborsadiya.in/blog/vali-admin/hero-social.png">
    <meta property="og:description" content="Vali is a responsive and free admin theme built with Bootstrap 4, SASS and PUG.js. It's fully customizable and modular.">
    <title>Ecommerce | Admin</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Main CSS-->
    <link rel="stylesheet" type="text/css" href="static/Admin/css/main.css">
    <!-- Font-icon css-->
    <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
   <!-- <link rel="stylesheet" type="text/css" href="static/Admin/css/jquery.table-shrinker.css">  --> 
   <link rel="icon" href="static/images/logo.png" type="image/x-icon" />
</head>
  <body class="app sidebar-mini rtl">
    <!-- Navbar-->
    
  <c:import url="/WEB-INF/templates/Fragments/header.jsp">
    <c:param name="page" value="ind"/>
  </c:import>
    <!-- Sidebar menu-->
    <div class="app-sidebar__overlay" data-toggle="sidebar"></div>
    
  <c:import url="/WEB-INF/templates/Fragments/aside.jsp">
    <c:param name="page" value="index"/>
  </c:import>
    <main class="app-content">
      <div class="app-title">
        <div>
          <h1><i class="fa fa-dashboard"></i> Orders</h1>
          <p>liste des Orders</p>
        </div>
        <ul class="app-breadcrumb breadcrumb">
          <li class="breadcrumb-item"><i class="fa fa-home fa-lg"></i></li>
          <li class="breadcrumb-item"><a href="<c:url value ="/Admin-index"/>">Acceuill</a></li>
        </ul>
      </div>
      <div class="row">
        <div class="col-md-12">
          <div class="tile">
            <div class="tile-body">List des Orders</div>
            
             <table class="table mt-4 jsmartable">
                    <thead>
                        <tr>
                            <th>Produit</th>
                            <th>Date</th>
                            <th data-breakpoint="xs">Nombre</th>
                            <th data-breakpoint="xs">Quantite</th>
                            <th data-breakpoint="sm">Etat</th>
                            <th data-breakpoint="md">Client email</th>
                            <th data-breakpoint="md">Client adress</th>
                            <th data-breakpoint="md">Client tell</th>
                            <th data-breakpoint="md">changer L'etat</th>
                           
                        </tr>
                    </thead>
                    <tbody>
                    
                     <c:forEach items = "${orders}"  var="order">
                        <tr>
                           <td >
                              ${order.product.libelle}
                             </td>
                            <td>${order.orderDate}</td>
                           
                              <td> 
                               ${order.orderNumb}
                               </td>
                            
                            <td>${order.quantity}</td>
                            <td>${order.status}</td>
                            <td>${order.user.email}</td>
                            <td>${order.orderDetail.adress}</td>
                            <td>${order.orderDetail.tell}</td>
                           
                            <td><a  href="<c:url value ="/Admin-index?action=changetOrderS&id=${order.id}"/>" class="btn btn-danger btn-sm">changer l'etat</a> </td>
                        </tr>
                     </c:forEach>
                    </tbody>
                </table>
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
    
    <!-- ---- FOTABLE TABLE ------->
     <script src="static/Admin/js/jsmartable.js"></script>
     <!--  <script src="static/Admin/js/jquery.table-shrinker.js"></script> -->
    <!-- Page specific javascripts-->
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
    </script>

</body>
</html>