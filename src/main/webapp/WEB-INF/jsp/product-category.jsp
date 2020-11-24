<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1" isELIgnored="false"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Product Category</title>

</head>
<body>
<c:set var="req" value="${pageContext.request}" />
<c:set var="baseURL" value="${req.scheme}://${req.serverName}:${req.serverPort}${req.contextPath}"  scope="session"/>


<%@include file="../jsp/header.jsp" %>


<div class="container" id="category-id">
  <div class="row row-no-gutters">
    <div class="col-sm-12" id="category-form">
    
      <div class="card">
                <div class="card-header d-flex align-items-center">
                  <h4>Add Category</h4>
                </div>
                <div class="card-body">
                  
                  
                  <form:form method="POST" class="text-left form-validate" id="categoryForm" action="${baseURL}/add-category" onsubmit="formSubmit('categoryForm','catMenuId')">
                			
                  	<div class="form-group row has-danger">
                      <div class="col-sm-12">
                        <div class="form-group-material">
                           <input type="text"  placeholder="ID" id="id" name="id" required class="input-material"/>
                         
                       </div>
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
                  
                   
                    
                    <div class="form-group row">
                      <label class="col-sm-2 form-control-label">Status</label>
                      <div class="col-sm-12 mb-3">
                        <select name="status" class="form-control" id="status">
                          <option value="A">Active</option>
                           <option value="I">InActive</option>
                      </select>
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
                  <h4>Product Categories</h4>
                </div>
                <div class="card-body">
                  <div class="table-responsive">
                    <table class="table table-striped">
                      <thead>
                        <tr>
                          <th>#</th>
                          <th>ID</th>
                          <th>Name</th>
                          <th>Desc</th>
                          <th>Status</th>
                           <th></th>
                            <th></th>
                        </tr>
                      </thead>
                      
                      <tbody>
                      <c:forEach items ="${response}" var = "resp" varStatus="loop">
                        <tr>
                          <td scope="row">${loop.index+1}</td>
                          <td>${resp.id}</td>
                         <td>${resp.name}</td>
                          <td>${resp.desc}</td>
                          <td>${resp.status}</td>
                          <td>
                          
                          <button type="button" class="btn btn-primary" onclick='editCategory("${baseURL}/edit-category","${userToken}","id=${resp.id}")'>Edit</button>
                          </td>
                          <td>
                          
                          <button type="button" class="btn btn-danger"onclick='deleteCat("${baseURL}/delete","${userToken}","id=${resp.id}","catMenuId")'>Delete</button>
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
  </div>

 
  <%@include file="../jsp/footer.jsp" %>
</body>
</html>