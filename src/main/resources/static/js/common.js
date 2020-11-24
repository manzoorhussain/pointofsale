var prodMap = new Map();
var pagerMap = new Map();


function showMessage(response){
	
	var obj =JSON.stringify(response);
  	var json = JSON.parse(obj);
  	console.log("json--"+json);
  	
  	var responseCode=json["code"];
  	var responseMessage=json["message"];
  	var responseObject=json["responseObject"];
  	
  	
  
  	
  	if(responseCode=="00"){
  		toastr.success(responseMessage);
  	}else{
  		toastr.error(responseMessage);
  	}
  	
  	
  	
	
	
}

function ajaxCallForLogOut(uri,formData,responeAction){
	console.log("formData--"+formData)
	$.ajax({
	    url: uri+"?userCode="+formData,
	   dataType: 'text',
	    type: 'GET',
	    data: formData,
	    processData: false,
	    success: function(data, textStatus, jQxhr ){
	    console.log("scuss=="+uri);
	       window.location.href = responeAction;
	    
	    },
	    error: function( jqXhr, textStatus, errorThrown ){
	        console.log( errorThrown );
	    }
	});
	
	
}


function ajaxCallWithHeader(uri,formData,responeAction,userToken,responsiveDiv){
	
	
	
	$.ajax({
	    url: uri,
	   dataType: 'text',
	    type: 'GET',
	    data: formData,
	    processData: false,
	    beforeSend: function(xhr)
	    {
	    	xhr.setRequestHeader('token', userToken);
	    	
	    },
	    success: function(data, textStatus, jQxhr ){
	    	$('#'+responsiveDiv).html(data);
	    
	    
	    },
	    error: function( jqXhr, textStatus, errorThrown ){
	        console.log( errorThrown );
	    }
	});
	
}

function formSubmitWithImage(formId,reponseDivId){
	
	$("#"+formId).submit(function(e) {
	    e.preventDefault();    
	    var formData = new FormData(this);

	    $.ajax({
	    	url: $(this).attr("action"),
	        type: 'POST',
	        enctype: 'multipart/form-data',
	        data: formData,
	        success: function (response) {
	        	$('#'+reponseDivId).trigger('click');
	        	showMessage(response)
	        },
	        cache: false,
	        contentType: false,
	        processData: false
	    });
	});	

   

    }


function formSubmit(formId,reponseDivId){

    $("#"+formId).on("submit", function(e){
    	
        $.ajax({
          url: $(this).attr("action"),
          type: $(this).attr("method"),
          data: $(this).serialize(),
          dataType: 'JSON',
          success: function(response) {
        	 

  	    	
  	   showMessage(response);
        	  
          },
          error:function(){
        	  console.log('error-');
          },
          complete:function(){
        	 
        	  console.log('complete-') 
        	   $('#'+reponseDivId).trigger('click');
        	  
          }
          
         });
         e.preventDefault();
    });
    }

function editCategory(uri,userToken,formData){
	
	
	$.ajax({
	    url: uri,
	    dataType: 'JSON',
	    type: 'GET',
	    data: formData,
	    processData: false,
	    beforeSend: function(xhr)
	    {	
	    	
	    	xhr.setRequestHeader('token', userToken);
	    },
	    success: function(response){
	    	var obj =JSON.stringify(response);
	    	var json = JSON.parse(obj);
	    	
	    	var responseCode=json["code"];
	    	var responseMessage=json["message"];
	    	var responseObject=json["responseObject"];
	    	
	    	var userId=responseObject["id"];
	    	var name=responseObject["name"];
	    	var desc=responseObject["desc"];
	    	var status=responseObject["status"];
	    
	    	
	     	
	    	$("#id").val(userId);
	    	$("#name").val(name);
	    	$("#desc").val(desc);
	    	$("#status").val(status);
	    	
	    	
	    },
	    error: function( jqXhr, textStatus, errorThrown ){
	    	console.log( errorThrown );
	    },
	    complete:function(data){
      	  
      	  console.log('complete-'+data) 
        }
	});
	
}

function deleteCat(uri,userToken,formData,responceDivId){
	
	
	$.ajax({
	    url: uri,
	    dataType: 'JSON',
	    type: 'GET',
	    data: formData,
	    processData: false,
	    beforeSend: function(xhr)
	    {	
	    	
	    	xhr.setRequestHeader('token', userToken);
	    },
	    success: function(response, textStatus, jQxhr ){
	    		showMessage(response);
	    		$('#'+responceDivId).trigger('click');
	  
	     },
	    error: function( jqXhr, textStatus, errorThrown ){
	    	console.log( errorThrown );
	    },
	    complete:function(data){
      	  
      	  console.log('complete-'+data) 
        }
	});
	
}

function editProduct(uri,userToken,formData){
	
	
	$.ajax({
	    url: uri,
	    dataType: 'JSON',
	    type: 'GET',
	    data: formData,
	    processData: false,
	    beforeSend: function(xhr)
	    {	
	    	
	    	xhr.setRequestHeader('token', userToken);
	    },
	    success: function(response){
	    	var obj =JSON.stringify(response);
	    	var json = JSON.parse(obj);
	    	
	    	var responseCode=json["code"];
	    	var responseMessage=json["message"];
	    	var responseObject=json["responseObject"];
	    	
	    	
	    	
	    	var productPK=responseObject["productPK"];
	    	
	    	var categoryId=productPK["categoryId"];
	    	var productId=productPK["productId"]
	    	
	    	var name=responseObject["name"];
	    	var desc=responseObject["desc"];
	    	var status=responseObject["status"];
	    	var price=responseObject["price"];
	    
	    
	    	
	     	
	    	$("#categoryId").val(categoryId);
	    	$("#productId").val(productId);
	    	$("#name").val(name);
	    	$("#desc").val(desc);
	    	$("#status").val(status);
	    	$("#price").val(price);
	    	
	    	
	    },
	    error: function( jqXhr, textStatus, errorThrown ){
	    	console.log( errorThrown );
	    },
	    complete:function(data){
      	  
      	  console.log('complete-'+data) 
        }
	});
	
}

function previewModel(imageURL){
	console.log("imageURL--"+imageURL);
	$("#exampleModalCenter .modal-body").children().attr("src","data:image/jpg;base64,"+imageURL)
    $("#exampleModalCenter").modal();
	
}

function ProductModel(productCode,prodName,quantity,price) {
	this.productCode = productCode;
    this.prodName = prodName;
    this.quantity = quantity;
    this.price = price;
   
 }

function getProduct(mapKey,mapValue){
	
	
	
	if(prodMap.get(mapKey)==undefined){
		prodMap.set(mapKey,mapValue);
	}else{
		
		var currentProductCode=mapKey;	
		var currentProdName=prodMap.get(mapKey).prodName;
		var currentQuan=parseInt(prodMap.get(mapKey).quantity)+1;
		var currentPrice=parseInt(prodMap.get(mapKey).price)+parseInt(mapValue.price);
			
		
		var currentObj=new ProductModel(currentProductCode,currentProdName,currentQuan,currentPrice);
		prodMap.set(mapKey,currentObj);
	}
	
		var markup='';
		var outerMarkUp='';
		var totalSum=0;
	for (let [key, value] of prodMap) {
	
		 markup= "<tr><td>"+ value.prodName+"</td><td>" + value.quantity + "</td><td>" + value.price + "</td></tr>";
		 outerMarkUp+=markup;
		 totalSum+=parseInt(value.price);
		 
	}
		
	 //$("table tbody #bill-table").html(outerMarkUp);
	$("#bill-table").html(outerMarkUp);
	 markup='';
	 $("#totalText").text(totalSum);
	 $('#totalText').text()
	
	
	
}

function calculateBill(productCode,prodName,quantity,price){
	
	var currentObj=new ProductModel(productCode,prodName,quantity,price);

	getProduct(productCode,currentObj);
	

  }


function previewBillModel(uri,userToken){
	

	let jsonObject = {}; 
	
	prodMap.forEach((value, key) => {  
	    jsonObject[key] = value  
	}); 

	let passObject = {};
	passObject["bill"]= JSON.stringify(jsonObject);
	passObject["orderNumber"]=$('#orderNumber').val();
	passObject["totalAmount"]=$('#totalText').text()
	
	
	$.ajax({
	    url: uri,
	   dataType: 'text',
	    type: 'POST',
	    data: JSON.stringify(passObject),
	    contentType: "application/json",
	    processData: false,
	    beforeSend: function(xhr)
	    {
	    	xhr.setRequestHeader('token', userToken);
	    },
	    success: function(data, textStatus, jQxhr ){
	    	
	    	
	    	$("#billModel .modal-body").children().attr("src","data:application/pdf;base64,"+data)
	    	
	    	  $("#billModel").modal();
	    	
	    
	    },
	    error: function( jqXhr, textStatus, errorThrown ){
	        console.log( errorThrown );
	    }
	});
	
}

function base64ToArrayBuffer(base64) {
   // var binaryString = window.atob(base64);
    
    var binaryString= window.btoa(unescape(encodeURIComponent(base64)))
    var binaryLen = binaryString.length;
    var bytes = new Uint8Array(binaryLen);
    for (var i = 0; i < binaryLen; i++) {
        var ascii = binaryString.charCodeAt(i);
        bytes[i] = ascii;
    }
    return bytes;
}
function getOrderNo(uri,userToken,formData){
	
	$.ajax({
	    url: uri,
	   dataType: 'text',
	    type: 'GET',
	    data: formData,
	    processData: false,
	    beforeSend: function(xhr)
	    {
	    	xhr.setRequestHeader('token', userToken);
	    },
	    success: function(data, textStatus, jQxhr ){
	    	
	    	var obj =JSON.stringify(data);
	    	var json = JSON.parse(obj);
	    
		$('#orderNumber').val(JSON.parse(json).responseObject);
		$("#bill-table").html('');
		$('#totalText').text('0');
		prodMap.clear();
	    
	    },
	    error: function( jqXhr, textStatus, errorThrown ){
	        console.log( errorThrown );
	    },
	    complete:function(data){
	      	  
	      	  console.log('complete-'+data) 
	        }
	});
	
	
	
}

function clearScreen(){
	$('#orderNumber').val('');
	$("#bill-table").html('');
	$('#totalText').text('0');
	prodMap.clear();
	
}

function pagination(flag,baseURL,userToken){

	if(flag=="N"){
		console.log("if")
		var url=baseURL+"/get-all-product-page/1";
		ajaxCallWithHeader(url,"null","category-panel",userToken,"show-container");
		
		
	}else{
		console.log("else")
		var url=baseURL+"/get-all-product-page/2"
		ajaxCallWithHeader(url,"null","category-panel",userToken,"show-container");
	}
	
}