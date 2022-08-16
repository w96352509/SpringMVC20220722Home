<!-- 小技巧：讓 jsp 可以支援所有 HTTP 方法 -->
<%@ page isErrorPage="true" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
	<title>Job Form</title>
	<style type="text/css">
		.error {
			color: #FF0000;
		}
	</style>
	<script type="text/javascript">
		function changeMethodAndSubmit(methodValue) {
			document.getElementById("_method").value = methodValue;
			document.getElementById("job").submit();
		}
	</script>
	 <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
	  <script type="text/javascript">
	  google.charts.load('current', {'packages':['corechart']}); // 固定
      google.charts.setOnLoadCallback(drawChart);                // 固定
 
      function drawChart() { 
	    jobs(1);    // 繪圖方法 
       }
       function jobs(chartId) {
	    	var data = google.visualization.arrayToDataTable([ // 固定
		        ['ename', 'job_count'],                        // key and Value
		        <c:forEach var="emp" items="${ employees }">   // 拆解 employees 內容
		          <c:if test="${ fn:length(emp.jobs) > 0 }">
		        		// 判斷第一筆工作的 jid 不可以是 null
				        <c:if test="${ emp.jobs[0].jid != null }">
				        	['${ emp.ename }', ${ fn:length(emp.jobs) }],
				        </c:if>
				        <c:if test="${ emp.jobs[0].jid == null }">
				        	['${ emp.ename }', 0],
				        </c:if>	
			        </c:if>            
		        </c:forEach>
			]);
		
			var options = {
				title: 'Jobs'                                // 標題
			};
			
			var chart = new google.visualization.BarChart(document.getElementById('piechart'));
			switch(chartId) {
        	case 2:
        		chart = new google.visualization.PieChart(document.getElementById('piechart'));
        		break;
        	case 3:
        		chart = new google.visualization.ColumnChart(document.getElementById('piechart'));
        		break;
        	case 4:
        		chart = new google.visualization.LineChart(document.getElementById('piechart'));
        		break;	
        }
        
        chart.draw(data, options);
    } 
      </script> 
</head>
<body style="padding: 15px;">
	<table >
		<tr>
			<!-- Job Form -->
			<td valign="top">
		     <spform:form class="pure-form" method="post" modelAttribute="job" 
		                  action="${pageContext.request.contextPath}/mvc/jdbc/job/">
		      <fieldset>
		       <legend>Job Form</legend>
		       <input type="hidden" id="_method" name="_method" value="${_method}">
		       編號 : <spform:input path="jid" readonly="true"/>  <p />
		              <spform:errors path="jid" cssClass="error"/> 
		              <p />
		       職位 : <spform:input path="jname" />  <p />
		               <spform:errors path="jname" cssClass="error"/> <p /> 
		               <p />
		       員工 : <spform:select path="eid" items="${employees}" itemLabel="ename" itemValue="eid"></spform:select>
		               <p />
		             <button type="submit" ${_method eq 'POST' ? '' : 'disabled' } class="pure-button pure-button-primary">新增</button>
		             <button type="submit" ${_method eq 'PUT'  ? '' : 'disabled' } class="pure-button pure-button-primary">修改</button>
		             <button type="button" class="pure-button pure-button-primary" ${ _method eq 'PUT'?'':'disabled' } onclick="changeMethodAndSubmit('DELETE');">刪除</button>
		      </fieldset>
		     </spform:form>
			</td>
			<!-- Job List -->
			<td valign="top" colspan="2">
				<form class="pure-form">
				 <fieldset>
				  <legend>Job List || 
				   <a href="${pageContext.request.contextPath}/mvc/jdbc/job/page/-1">搜尋全部</a>
				   <c:forEach begin="1" end="${pageCount}" var="num">
				    <a href="${pageContext.request.contextPath}/mvc/jdbc/job/page/${num}">${num}</a>
				   </c:forEach>
				  </legend>
				    <table class="pure-table pure-table-bordered" >
				     <thead>
				      <tr>
				        <th rowspan="2" valign="middle" align="center">編號</th>
						<th rowspan="2" valign="middle" align="center">名稱</th>
						<th colspan="2" align="center">員工資料</th>
				      </tr>
				      <tr style="border-top: 1px solid lightgray;"> <!-- 字畫灰線 -->
						<th>員編</th>
						<th>姓名</th>
					  </tr>
				     </thead>
				     <tbody>
				      <c:forEach items="${jobs}" var="job">
				       <tr>
				        <td>
				         <a href="${pageContext.request.contextPath}/mvc/jdbc/job/${job.jid}">${job.jid}</a>
				        </td>
				        <td>${job.jname}</td>
				        <td>${job.employee.eid}</td>
				        <td>
				         <a href="${pageContext.request.contextPath}/mvc/jdbc/employee/${job.employee.eid}">${job.employee.ename}</a>
				        </td>
				       </tr>
				      </c:forEach>
				     </tbody>
				    </table>
				 </fieldset>
				</form>
			</td>
			<!-- Job Bar Chart -->
			<td valign="top">
				<form class="pure-form">
					<fieldset>
						<legend>
							Salary Chart |
							<a href="#" onclick="jobs(1)">bar</a> |
							<a href="#" onclick="jobs(2)">pie</a> |
							<a href="#" onclick="jobs(3)">column</a> |
							<a href="#" onclick="jobs(4)">line</a>
						</legend>
						<div id="piechart" style="width: 500px; height: 300px;"></div>
					</fieldset>
				</form>
			</td>
		</tr>
	</table>
</body>
</html>