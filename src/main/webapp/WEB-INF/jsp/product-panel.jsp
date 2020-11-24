<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Product Panel </title>

<style type="text/css">

.container-margin {
  margin-left: -61px;
  margin-top: -46px;
} 
</style>

</head>
<%@include file="../jsp/header.jsp" %>
  <body>
    <!-- Side Navbar -->
<%@include file="../jsp/sidenavbar.jsp" %>
   <!-- Side Navbar end -->
  
  
   
    <div class="page">
  <%@include file="../jsp/pageheader.jsp" %>
      <!-- Counts Section -->
      <section class="dashboard-counts section-padding">
        <div class="container-fluid" id="show-container" style="margin-left: -61px;margin-top: -46px;width:1256px">
      
        </div>
      </section>
     
      
           
      <footer class="main-footer">
        <div class="container-fluid">
          <div class="row">
            <div class="col-sm-6">
              <p>Your company &copy; 2017-2020</p>
            </div>
            <div class="col-sm-6 text-right">
              <p>Design by <a href="https://bootstrapious.com/p/bootstrap-4-dashboard" class="external">Bootstrapious</a></p>
              <!-- Please do not remove the backlink to us unless you support further theme's development at https://bootstrapious.com/donate. It is part of the license conditions and it helps me to run Bootstrapious. Thank you for understanding :)-->
            </div>
          </div>
        </div>
      </footer>
    </div>
   
     <%@include file="../jsp/footer.jsp" %>
  </body>


</html>