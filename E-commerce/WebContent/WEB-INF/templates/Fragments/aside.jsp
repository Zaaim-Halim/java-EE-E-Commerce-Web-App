<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<aside class="app-sidebar">
      <div class="app-sidebar__user"><img class="app-sidebar__user-avatar" src="static/images/cart.jpg" width="80px" height="80px" alt="User Image">
        <div>
          <p class="app-sidebar__user-name"><b>${user.nom}</b><b> ${user.prenom}</b></p>
          <p class="app-sidebar__user-designation">${authControl.admin == true ?"Admin" :""}</p>
        </div>
      </div>
      <c:if test="${authControl.admin == true}">
      <ul class="app-menu">
        <li><a class='app-menu__item ${param.page == "index"?"active":""}' href="<c:url value ="/Admin-index"/>"><i class="app-menu__icon fa fa-home"></i></i><span class="app-menu__label">Accueil</span></a></li>
        <li><a class='app-menu__item ${param.page == "ajouterP"?"active":""}' href="<c:url value ="/Admin-Produit"/>"><i class="app-menu__icon fa fa-plus-square"></i><span class="app-menu__label">Ajouter Produit</span></a>
          
        </li>
        <li><a class='app-menu__item ${param.page == "users"?"active":""}' href="<c:url value ="/Admin-index?action=users"/>"><i class="app-menu__icon fa fa-pie-chart"></i><span class="app-menu__label">Gerer les utilisateurs</span></a></li>
        <li><a class='app-menu__item ${param.page == "orders"?"active":""}' href="<c:url value ="/Admin-index?action=orders"/>"><i class="app-menu__icon fa fa-edit "></i><span class="app-menu__label">Orders</span></a>
        </li>
      <!--  <li class="treeview"><a class='app-menu__item ${param.page == "livraison"?"active":""}' href="#" data-toggle="treeview"><i class="app-menu__icon fa fa-ship"></i><span class="app-menu__label">Livraison</span><i class="treeview-indicator fa fa-angle-right"></i></a>
        
        </li>
        <li class="treeview"><a class='app-menu__item ${param.page == "autres"?"active":""}' href="#" data-toggle="treeview"><i class="app-menu__icon fa fa-file-text"></i><span class="app-menu__label">Autres</span><i class="treeview-indicator fa fa-angle-right"></i></a>
          
        </li> --> 
      </ul>
      </c:if>
      <c:if test="${authControl.admin== false}">
        <ul class="app-menu">
        <li><a class='app-menu__item ${param.page == "indexC"?"active":""}' href="<c:url value ="/Client-index"/>"><i class="app-menu__icon fa fa-home"></i></i><span class="app-menu__label">Accueil</span></a></li>
        <li><a class='app-menu__item ${param.page == "clientPannier"?"active":""}' href="<c:url value ="/Client-cart"/>"><i class="app-menu__icon fa fa-plus-square"></i><span class="app-menu__label">Pannier</span></a>
          
        </li>
        <li><a class='app-menu__item ${param.page == "clientProduit"?"active":""}' href="<c:url value ="/Client-produits"/>"><i class="app-menu__icon fa fa-pie-chart"></i><span class="app-menu__label">Produits Acheter</span></a></li>
        <li class="treeview"><a class='app-menu__item ${param.page == "clientOrders"?"active":""}' href="<c:url value ="/Client-orders"/>" data-toggle="treeview"><i class="app-menu__icon fa fa-edit "></i><span class="app-menu__label">Mes Orders</span><i class="treeview-indicator fa fa-angle-right"></i></a>
        </li>
        <!--  <li class="treeview"><a class="app-menu__item" href="#" data-toggle="treeview"><i class="app-menu__icon fa fa-ship"></i><span class="app-menu__label">Livraison</span><i class="treeview-indicator fa fa-angle-right"></i></a>
        
        </li>
        <li class="treeview"><a class="app-menu__item" href="#" data-toggle="treeview"><i class="app-menu__icon fa fa-file-text"></i><span class="app-menu__label">Autres</span><i class="treeview-indicator fa fa-angle-right"></i></a>
          
        </li>-->
      </ul>
      </c:if>  
    </aside>