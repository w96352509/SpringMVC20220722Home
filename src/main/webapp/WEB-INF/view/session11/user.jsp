<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isErrorPage="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spform" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
		href="https://unpkg.com/purecss@2.0.6/build/pure-min.css">
<meta charset="UTF-8">
<title>User 表單</title>
<script type="text/javascript">

 function editUser(index){
    var getUrl ='${pageContext.request.contextPath}/mvc/user/' + index;
	window.location.href=getUrl;
 }
 
 function deleteUser(index){
    var deleteUrl ='${pageContext.request.contextPath}/mvc/user/' + index;
	 fetch(deleteUrl,{method:'DELETE'})
	      .then(() => window.location.href='${pageContext.request.contextPath}/mvc/user/')
 }

</script>
</head>
<body style="padding: 15px;">
 <spform:form class="pure-form" method="post" modelAttribute="user" action="${ pageContext.request.contextPath}/mvc/user/${ index }" >
  <fieldset>
   <legend>使用者表單</legend>
  <input type="hidden" id="_method" name="_method" value="${_method}" />
  姓名 : <spform:input type="text"   path="name"  /><p/>
  年齡 : <spform:input type="number" path="age"   /><p/>
  生日 : <spform:input type="Date"   path="birth" /><p/>
  學歷 : <spform:select path="education">
         <spform:option value="國中">國中</spform:option>
         <spform:option value="高中">高中</spform:option>
         <spform:option value="大學">大學</spform:option>
         <spform:option value="研究所">研究所</spform:option>
        </spform:select>
  性別 : <spform:radiobutton path="sex" value="男"/>男
        <spform:radiobutton path="sex" value="女"/>女
        <p/>
  
  興趣 : <spform:checkbox path="interest" value="爬山"/>爬山
         <spform:checkbox path="interest" value="看電影"/>看電影
         <spform:checkbox path="interest" value="寫程式"/>寫程式
         <spform:checkbox path="interest" value="飛控"/>飛控
         <p/>
  
  履歷 : <spform:textarea path="resume" /><p/>
  
  <button type="submit" class="pure-button pure-button-primary">${submitbuttonName}</button>             
 </fieldset>
 </spform:form>
 <p />
 <form class="pure-form">
  <fieldset>
   <legend>User Table</legend>
   <table class="pure-table pure-table-bordered">
    <thead>
     <tr>
      <th>#</th>
      <th>姓名</th>
      <th>年齡</th>
      <th>生日</th>
      <th>學歷</th>
      <th>性別</th>
      <th>興趣</th>
      <th>履歷</th>
      <th>修改</th>
      <th>刪除</th>
     </tr>
    </thead>
    <tbody>
     <c:forEach var="user" items="${users}" varStatus="status">
      <tr>
       <td>${status.index}</td>
		       <td>${user.name}</td>
		       <td>${user.age}</td>
		       <td>
		          <fmt:formatDate value="${user.birth}" pattern="yyyy-MM-dd"/>
		       </td>
		       <td>${user.education}</td>
		       <td>${user.sex}</td>
		       <td>
		        <!-- 內自省封裝(物件名稱)+(get(不用寫)方法) -->
		        ${ user.interestToString}
		       </td>
		       <td>
		        ${user.resume}
		       </td>
		        <td><a href="javascript:editUser  (${ status.index })">編輯</a></td>
		        <td><a href="javascript:deleteUser(${ status.index })">刪除</a></td>
      </tr>
     </c:forEach>
    </tbody>
   </table>
  </fieldset>
 </form>
</body>
</html>