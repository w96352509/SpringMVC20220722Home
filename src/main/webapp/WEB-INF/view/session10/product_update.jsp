<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@2.1.0/build/pure-min.css">
		<meta charset="UTF-8">
		<title>Product 修改表單</title>
		<script type="text/javascript">
		 function del(){
		  var result = confirm('確認刪除');
		  if(result==true){
		    alert('確認刪除');
		    return true;
		  }else{
		     alert('刪除取消');
		    return false; 
		  }
		 }
		</script>
	</head>
	<body style="padding: 15px">
		<form class="pure-form" method="post" onsubmit="return del() " action="/spring.mvc/mvc/product/${ index }">
			<fieldset>
				<legend> ${_method} 表單</legend>
				<input type="hidden" name="_method" id="_method" value="${_method}">
				<input type="text" placeholder="請輸入商品名稱" value="${ product.productName }" id="productName" name="productName"><p />
				<input type="number" placeholder="請輸入商品數量" value="${ product.amount }" id="amount" name="amount"><p />
				<input type="number" placeholder="請輸入商品價格" value="${ product.price }" id="price" name="price"><p />
				<button type="submit" class="pure-button pure-button-primary"> ${_method} </button>
				<button type="button"
	        		onclick="window.location.href='/spring.mvc/mvc/product/';" 
	        		class="pure-button pure-button-primary">back</button>        
			</fieldset>
		</form>
	</body>
</html>