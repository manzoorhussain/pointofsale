<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Main Panel</title>

<style>
.text-alig
{
text-align:center;
color:white;
font-family: "Arial";
font-size: 40px;
font-weight: bold;
border: 3px solid green;
padding: 65px 50px 75px 100px;
}

.container-margin{
margin-top: 0px;
  margin-bottom: 100px;
  margin-right: 0px;
  margin-left: 0px;
}
</style>

</head>
<body>

<%@include file="../jsp/header.jsp" %>
<%@include file="../jsp/pageheader.jsp" %>
<br><br><br><br>


<div class="container container-margin" id="container">

  
  <div class="row row-no-gutters" style="height:200px">
    <div class="col-sm-6 text-alig" style="background-color:#212529;">Admin</div>
    <div class="col-sm-6 text-alig" style="background-color:#28a745;" onclick='ajaxCallWithHeader("${baseURL}/product-panel","null","product-panel","${userToken}","container")'>Product</div>
  </div>
  <br><br><br>
  
  <div class="row row-no-gutters" style="height:200px">
    <div class="col-sm-6 text-alig" style="background-color:#28a745;">Inventory</div>
    <div class="col-sm-6 text-alig" style="background-color:#212529;">Pending</div>
  </div>
 
 
</div>

  <%@include file="../jsp/footer.jsp" %>
</body>
</html>