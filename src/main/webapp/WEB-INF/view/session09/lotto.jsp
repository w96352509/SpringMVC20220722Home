<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/purecss@2.1.0/build/pure-min.css">
<meta charset="UTF-8">
<title>Lotto</title>
<style type="text/css">
td, th {
	text-align: center;
}
</style>
</head>
<body style="padding: 15px">
	<button type="button"
		onclick="window.location.href='/spring.mvc/mvc/lotto/get';"
		class="pure-button pure-button-primary">
		Lotto 539 電腦選號
	</button>
	<p />
	
	<h1>統計紀錄表單 : </h1>
	<table class="pure-table pure-table-bordered">
	 <thead>
	   <tr>
	    <th nowrap>號碼</th>
	    <c:forEach var="s" items="${ stat }">
	     <th>${ s.key }</th>
	    </c:forEach>
	   </tr>
	 </thead>
	 <tbody>
	   <tr>
	    <td nowrap>次數</td>
	    <c:forEach var="s" items="${ stat }">
	     <td>${ s.value }</td>
	    </c:forEach>
	   </tr>
	 </tbody>
	</table>
	
	<p />
	
	<h1>歷史紀錄表單 : </h1>
	<table class="pure-table pure-table-bordered">
	 <thead>
	   <tr>
	    <th>#</th>
	    <th>號碼1</th>
	    <th>號碼2</th>
	    <th>號碼3</th>
	    <th>號碼4</th>
	    <th>號碼5</th>
	    <th>更新</th>
	    <th>刪除</th>
	   </tr>
	 </thead>
	 <tbody>
	  <c:forEach var="lotto" items="${ lottos }" varStatus="status">
	  <tr>
	    <td>${status.index}</td>
	    <c:forEach var="num" items="${ lotto }" >
	    <td>${ num }</td>
	    </c:forEach>
	    <td>
	     <button type="button" class="pure-button pure-button-primary"
	             onclick="window.location.href='/spring.mvc/mvc/lotto/update/${status.index}';">
	      更新
	     </button>
	    </td>
	    <td>
	     <button type="button" class="pure-button pure-button-primary"
	             onclick="window.location.href='/spring.mvc/mvc/lotto/delete/${status.index}';">
	      刪除
	     </button>
	    </td>
	  </tr> 
	   </c:forEach>
	 </tbody>
	</table>
</body>
</html>