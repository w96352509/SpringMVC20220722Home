<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spform" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
	<head>
		<link rel="stylesheet" href="https://unpkg.com/purecss@2.0.6/build/pure-min.css">
		<meta charset="UTF-8">
		<title>MyStock Form</title>
		<style type="text/css">
			.error {
				color: #FF0000
			}
		</style>
	</head>
	<body style="padding: 15px;">
		<spform:form class="pure-form"
					 method="post"
					 modelAttribute="myStock"
					 action="${ pageContext.request.contextPath }/mvc/mystock/">
			<fieldset>
				<legend>MyStock form</legend>
				股票代號: <spform:input path="symbol" />
					 	<spform:errors path="symbol" cssClass="error" /><p />
				股票價格: <spform:input path="price" type="number" />
					 	<spform:errors path="price" cssClass="error" /><p />
				股票股數: <spform:input path="amount" type="number" />
					 <spform:errors path="amount" cssClass="error" /><p />
				<button type="submit" class="pure-button pure-button-primary">新增</button><p />
				<spform:errors path="*" cssClass="error" /><p />
			</fieldset>
		
		</spform:form>
		${ stocks }
	</body>
</html>