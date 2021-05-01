<?xml version="1.0" encoding="utf-8" ?>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 <link rel="icon" href="static/images/logo.png" type="image/x-icon" />
<title>error</title>
</head>
<body>
<c:import url="/WEB-INF/templates/Fragments/menuE.jsp">
    <c:param name="page" value="paiement"/>
 </c:import>
 
    <h1 > <strong>Error!: ${response.status}</strong> ${response.message} </h1>
 
 <c:import url="/WEB-INF/templates/Fragments/footer.jsp"> 
 </c:import>

</body>
</html>