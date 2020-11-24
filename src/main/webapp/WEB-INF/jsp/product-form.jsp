<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1" isELIgnored="false"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Products</title>

<style>
.pagination {
  display: inline-block;
}

.pagination a {
  color: black;
  float: left;
  padding: 8px 16px;
  text-decoration: none;
}

.pagination a.active {
  background-color: #4CAF50;
  color: white;
}

.pagination a:hover:not(.active) {background-color: #ddd;}
</style>


</head>
<body>
<c:set var="req" value="${pageContext.request}" />
<c:set var="baseURL" value="${req.scheme}://${req.serverName}:${req.serverPort}${req.contextPath}"  scope="session"/>


<%@include file="../jsp/header.jsp" %>

<!-- Modal -->
<div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLongTitle">Preview</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
         <%-- <img src="data:image/jpg;base64,${resp.displayImage}" width="20%" height="20%"  alt="No Image"/> --%>
           <img src="" width="100%" height="50%"  alt="No Image" id="productImage"/>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <!-- <button type="button" class="btn btn-primary">Save changes</button> -->
      </div>
    </div>
  </div>
</div>

<div class="container" id="product-id">
  <div class="row row-no-gutters">
    <div class="col-sm-12" id="product-form">
    
      <div class="card">
                <div class="card-header d-flex align-items-center">
                  <h4>Add Product</h4>
                </div>
                <div class="card-body">
                  
                  
                  <form:form  enctype="multipart/form-data" method="POST" class="text-left form-validate" id="productForm" action="${baseURL}/add-product" onsubmit="formSubmitWithImage('productForm','prodMenuId')">
                			
                 
                     <div class="form-group row">
                      <label class="col-sm-2 form-control-label">Category</label>
                      <div class="col-sm-12 mb-3">
                        <select name="categoryId" class="form-control" id="categoryId">
                         <option value="-1">Select</option>
                          <c:forEach items="${productResponse.categories}" var="categoryList">
                          <option value="${categoryList.id}">${categoryList.name}</option>
                          </c:forEach>
                          
                      </select>
                      </div>
                      </div>
                    
                    
                     
                    <div class="form-group">
                      <div class="form-group-material">
                          <input placeholder="Product ID" id="productId" type="text" name="productId" required class="input-material" />
                         
                        </div>
                      
                    </div>
                    
                    
                    <div class="form-group">
                      <div class="form-group-material">
                          <input placeholder="Name" id="name" type="text" name="name" required class="input-material" />
                         
                        </div>
                      
                    </div>
                   
                  
                   <div class="form-group">
                   
                      <div class="form-group-material">
                          <input placeholder="Description" id="desc" type="text" name="desc" required class="input-material"/>
                         </div>
                      
                    </div>
                    
                     <div class="form-group">
                   
                      <div class="form-group-material">
                          <input placeholder="Price" id="price" type="text" name="price" required class="input-material"/>
                         </div>
                      
                    </div>
                  
                  
                   
                    
                    <div class="form-group row">
                      <label class="col-sm-2 form-control-label">Status</label>
                      <div class="col-sm-12 mb-3">
                        <select name="status" class="form-control" id="status">
                          <option value="A">Active</option>
                           <option value="I">InActive</option>
                      </select>
                      </div>
                      </div>
                      
                      
                       <div class="form-group row">
                      <div class="col-sm-12 mb-3">
                         <input type="file" name="productImage" value=""  id="productImage"/>
   					  </div>
                      </div>
                      
                   
                   <input type="hidden" name="userToken" value="${userToken}"/> 
                    <div class="form-group">       
                      <input type="submit" value="Save" class="btn btn-primary">
                    </div>
              
                  </form:form>
                </div>
              </div>
     
    </div>
    
  
 </div>

   <div class="row row-no-gutters">
 <div class="col-sm-12" id="category-list">
      <div class="card">
                <div class="card-header">
                  <h4>Products</h4>
                </div>
                <div class="card-body">
                  <div class="table-responsive">
                    <table class="table table-striped">
                      <thead>
                        <tr>
                          <th>#</th>
                          <th>Category</th>
                          <th>Product Id</th>
                          <th>Name</th>
                           <th>Price</th>
                          <th>Description</th>
                          <th>Status</th>
                          <th>Image</th>
                           <th></th>
                            <th></th>
                        </tr>
                      </thead>
                      
                      <tbody>
                      <c:forEach items ="${productResponse.products}" var = "resp" varStatus="loop">
                        <tr>
                          <td scope="row">${resp.serialNo}</td>
                         <td>${resp.categoryDesc}</td>
                         <td>${resp.prodId}</td>
                         <td>${resp.prodName}</td>
                          <td>${resp.prodPrice}</td>
                          <td>${resp.prodDesc}</td>
                          <td>${resp.prodStatus}</td>
                           <td>
                       
            				   <%--  <img src="data:image/jpg;base64,${resp.displayImage}" width="20%" height="20%"  alt="No Image"/> --%>
            				      <!-- Button trigger modal -->
		<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModalCenter" onclick='previewModel("${resp.displayImage}")'>Preview</button>
                           </td>
                          <td>
                          
                          <button type="button" class="btn btn-primary" onclick='editProduct("${baseURL}/edit-product","${userToken}","categoryId=${resp.categoryId}&productId=${resp.prodId}")'>Edit</button>
                          </td>
                          <td>
                          
                          <button type="button" class="btn btn-danger"onclick='deleteCat("${baseURL}/delete-product","${userToken}","categoryId=${resp.categoryId}&productId=${resp.prodId}","prodMenuId")'>Delete</button>
                          </td>
                          
                        </tr>
                        </c:forEach>
                        
                      </tbody>
                    </table>
                  </div>
                </div>
              </div>
    
    </div>
  </div> 

<nav aria-label="Page navigation example">
  <ul class="pagination" style="float: right">
    <li class="page-item"><a class="page-link" onclick='pagination("P","${baseURL}","${userToken}")'>Previous</a></li>
    
    
    <li class="page-item"><a class="page-link" onclick='pagination("N","${baseURL}","${userToken}")'  style="margin: 0 0 0px 10px">Next</a></li>
  </ul>
</nav>

  </div>


 
  <%@include file="../jsp/footer.jsp" %>
  
  
</body>
</html>