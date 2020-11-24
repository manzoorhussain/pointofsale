<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <!DOCTYPE html>
<html>
<c:remove var="token" scope="session"/>
<c:remove var="userCode" scope="session"/>


<%@include file="../jsp/header.jsp" %>
  <body>
	<c:set var="req" value="${pageContext.request}" />
  <c:set var="baseURL" value="${req.scheme}://${req.serverName}:${req.serverPort}${req.contextPath}"  scope="session"/>
  
  <c:set var="responseCode" value="${responseCode}" />
    <div class="page login-page">
      <div class="container">
		
        <div class="form-outer text-center d-flex align-items-center">
          <div class="form-inner">
            <div class="logo text-uppercase"><span>Custom</span><strong class="text-primary">Dashboard</strong></div>
            <form method="POST" class="text-left form-validate" id="loginForm" action="${baseURL}/index">
              <div class="form-group-material">
                <input id="userId" type="text" id="userId" name="userId" required data-msg="Please enter your user id" class="input-material">
                <label for="userId" class="label-material">UserId</label>
              </div>
              <div class="form-group-material">
                <input id="password" type="password" id="password" name="password" required data-msg="Please enter your password" class="input-material">
                <label for="password" class="label-material">Password</label>
              </div>
              <div class="form-group text-center">
              <button type="submit" class="btn btn-primary">Login</button>
               </div>
          </form>
         <c:if test="${response.code=='01'}">
         <div class="alert alert-danger" role="alert" id="login-error-msge">${response.message}</div>
		</c:if>
          <div class="copyrights text-center">
            <p>Design by <a href="https://bootstrapious.com/p/bootstrap-4-dashboard" class="external">Bootstrapious</a></p>
            
          </div>
        </div>
      </div>
    </div>
    </div>
   <%@include file="../jsp/footer.jsp" %>
  </body>
</html>