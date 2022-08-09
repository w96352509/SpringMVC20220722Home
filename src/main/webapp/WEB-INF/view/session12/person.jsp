<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isErrorPage="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spform" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
 .error{
         color: red;
       }
</style>
<link rel="stylesheet"
		href="https://unpkg.com/purecss@2.0.6/build/pure-min.css">
<meta charset="UTF-8">
<title>Person form</title>
</head>
<body style="padding: 15px;">
 <spform:form class="pure-form" method="post" modelAttribute="person" action="${ pageContext.request.contextPath}/mvc/person/">
  <fieldset>
   <legend>Person 表單</legend>
   姓名 : <spform:input type="text" path="name"/>
         <spform:errors path="name" cssClass="error" />
         <p/>
   年齡 : <spform:input type="number" path="age"/>
         <spform:errors path="age" cssClass="error" />
         <p/>
   生日 : <spform:input type="date" path="birth"/>
         <spform:errors path="birth" cssClass="error" />
         <p/>
   會員 : <spform:radiobutton path="member" value="true"/>會員             
         <spform:radiobutton path="member" value="false"/>非會員
         <spform:errors path="member" cssClass="error" />
         <p/>
         <button type="submit" class="pure-button pure-button-primary">提交</button>
         
  </fieldset> 
 </spform:form>
 ${ people } <p/>
</body>
</html>