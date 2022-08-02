<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/purecss@2.1.0/build/pure-min.css">
<meta charset="UTF-8">
<title>Product</title>
</head>
<body style="padding: 15px">

	<form class="pure-form" method="post" action="/spring.mvc/mvc/product/rest/${index}">
		<fieldset>
			<legend>Product 表單</legend>
			<input type="hidden" id="_method"  name="_method" value="${_method}">
			<input type="text" placeholder="商品名稱" id="productName"
				name="productName"  value="${ product.productName }"/>
			<p />
			<input type="number" placeholder="商品數量" id="amount" name="amount" value="${ product.amount }"/>
			<p />

			<input type="number" placeholder="商品價格" id="price" name="price"  value="${ product.price }"/>
			<p />

			<button type="submit" class="pure-button pure-button-primary">${_method}</button>
		</fieldset>
	</form>

	<p />

	<form class="pure-form">
		<fieldset>
		 <legend>Product 列表</legend>
		 <table class="pure-table pure-table-bordered">
		  <thead>
		   <tr>
		    <th>#</th>
			<th>商品名稱</th>
			<th>商品數量</th>
			<th>商品價格</th>
			<th>商品小記</th>
			<th>更新</th>
			<th>刪除</th>
		   </tr>
		  </thead>
		  <tbody>
		  <c:forEach var="product" items="${ products }" varStatus="status">
		   <tr>
		    <td> ${ status.index }</td> 
		    <td> ${ product.productName }</td> 
		    <td> ${ product.amount }</td> 
		    <td> ${ product.price }</td> 
		    <td> $ ${product.amount * product.price }</td> 
		    <td>
		     <button type="button" onclick="window.location.href='/spring.mvc/mvc/product/rest/${status.index}';">更新</button>
		    </td>
		    <td>
		     <button type="button" onclick="window.location.href='/spring.mvc/mvc/product/rest/${status.index}?delete=true';">刪除</button>
		    </td>
		   </tr>
		  </c:forEach> 
		  </tbody>
		 </table>
		</fieldset>
	</form>

</body>
</html>