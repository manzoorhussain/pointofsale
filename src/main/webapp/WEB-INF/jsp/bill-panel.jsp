<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1" isELIgnored="false"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Bill Panel</title>

</head>

<style type="text/css">

.price-font{
position: relative;
font-size: .9rem;
font-weight: 300;
color: #fff;
text-decoration: none;
}
</style>

<body>
<c:set var="req" value="${pageContext.request}" />
<c:set var="baseURL" value="${req.scheme}://${req.serverName}:${req.serverPort}${req.contextPath}"  scope="session"/>


<%@include file="../jsp/header.jsp" %>

<!-- <!-- Start Bill Model Here --> -->
<div class="modal fade" id="billModel" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLongTitle">Bill</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body" style="height:500px">
      
          <iframe id="billFrame"  src="" width=450 height=500></iframe>
           
   
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <!-- <button type="button" class="btn btn-primary">Save changes</button> -->
      </div>
    </div>
  </div>

</div>
<!-- End Bill Model Here -->

<c:set var="allProductsForBill" value="${billResponse.products}"/>

  <div class="container-fuild" id="bill-id">
  <!-- This is product area start -->
  
  
  <div class="row">
  
  
   <div class="col-sm-8">
  <div class="card">
    <div class="card-body">
    
   
     <!-- OUTER LOOP START -->
   <c:forEach items="${allProductsForBill.HELLO}" var="mainList" varStatus="loop">  
    <div class="row">
    
     
     

  <c:forEach items="${mainList}" var="innerList" varStatus="innerLoop">  
 <div class="col-md-3">
  <div class="card" style="width:10rem;height:13rem;border-width: medium; border:solid 2px #33b35a" onclick="calculateBill('${innerList.prodId}','${innerList.prodName}','1','${innerList.prodPrice}')">
   <img src="data:image/jpg;base64,${innerList.displayImage}" width="100%" height="50%"  alt="No Image" id="productImage" />
  <div class="card-body" style="border-width: medium;"><p class="card-text" style="font-size:12px;font-weight:bold;color:black">${innerList.prodName}</p></div>
  <div class="card-footer text-muted" style="border-width: medium;font-size:15px;font-weight:bold;color:black;text-align: center;">${innerList.prodPrice}</div>
</div>
</div>
  </c:forEach>
 
   

    
     </div>
   
     </c:forEach>
     <!-- OUTER LOOP END -->
     
     
    
    
    
   </div>
  </div>
   
   
   
   </div>
    <!-- This is product area end -->
   
   
   
   <!-- This is bill area start -->
    <div class="col-sm-4">
    <div class="card">
    <div class="card-header">
    <div>
    <button type="button" class="btn btn-danger" onClick='getOrderNo("${baseURL}/max-orderno","${userToken}","")'>New Order</button>
    <button type="button" class="btn btn-danger" onClick='clearScreen()'>Clear</button>
    </div>
    <br>
    <div>
    <b>Order No:</b>
   <input type="text" value="" id="orderNumber" disabled/>
    </div>
    </div>
    
   <div class="card-body">
   <div class="table-responsive">
   <table class="table table-striped table-sm">
                      <thead>
                        <tr>
                          
                          <th>Product</th>
                          <th>Quantity</th>
                          <th>Price</th>
                        </tr>
                      </thead>
                      <tbody id='bill-table'>
                  
                      </tbody>
   </table>
  
   </div>
   </div>
   <div class="card-footer text-muted">
  
  
  <table class="table table-striped table-sm">
  <thead>
  <tr>
  <th id="totalButton">
  <button type="button" class="btn btn-primary" onclick='previewBillModel("${baseURL}/bill-report","${userToken}")'>Print</button>
  </th>
  <th>Total Amount</th>
  <th id="totalText"><input type="hidden" value="" id="totalHidden"></th>
  </tr>
  </thead>
  </table>
   
   </div>
  </div>
    
   </div>
  <!-- This is bill area start -->
  
   
  
  </div>
 
 
 </div>
  


  

 
  <%@include file="../jsp/footer.jsp" %>
</body>
</html>