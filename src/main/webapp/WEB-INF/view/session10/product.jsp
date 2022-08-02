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

	<form class="pure-form" method="post" action="/spring.mvc/mvc/product/">
		<fieldset>
			<legend>Product 表單</legend>
			<input type="text" placeholder="商品名稱" id="productName"
				name="productName" />
			<p />
			<input type="number" placeholder="商品數量" id="amount" name="amount" />
			<p />

			<input type="number" placeholder="商品價格" id="price" name="price" />
			<p />

			<button type="submit" class="pure-button pure-button-primary">提交</button>
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
		     <button type="button" onclick="window.location.href='/spring.mvc/mvc/product/get/${status.index}';">更新</button>
		    </td>
		    <td>
		     <button type="button" onclick="window.location.href='/spring.mvc/mvc/product/get/${status.index}?delete=true';">刪除</button>
		    </td>
		   </tr>
		  </c:forEach> 
		  </tbody>
		 </table>
		</fieldset>
	</form>

</body>
</html>