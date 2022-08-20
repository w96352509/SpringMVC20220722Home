<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spform" uri="http://www.springframework.org/tags/form" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Div Form</title>
</head>
<body>

   <spform:form action="${ pageContext.request.contextPath}/mvc/session14/div/" modelAttribute="div" method="post">
   <fieldset>
		<legend>整數除法計算</legend>
		  分子: <spform:input path="x" /><p />
		  分母: <spform:input path="y" /><p />
		<button type="submit" class="pure-button pure-button-primary">計算</button>
	</fieldset>
   </spform:form>
   結果: ${ result }<p />
   全 Model: ${ message }<p />
</body>
</html>