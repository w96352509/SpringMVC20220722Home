<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> success </title>
</head>
<body style="padding: 15px;">
	<form class="pure-form">
	    <fieldset>
	        <legend>Success form</legend>
	        商品名稱：${ product.productName }<p />
	        商品數量：${ product.amount }<p />
	        商品價格：${ product.price }<p />
	        <button type="button"
	        		onclick="window.location.href='/spring.mvc/mvc/product/';" 
	        		class="pure-button pure-button-primary">back</button>
	    </fieldset>
	</form>
</body>
</html>