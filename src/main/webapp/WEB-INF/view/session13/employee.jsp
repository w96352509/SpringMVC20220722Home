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
	<title>Employee Form</title>
	<style type="text/css">
		.error {
			color: #FF0000;
		}
	</style>
	<script type="text/javascript">
	 function changeMethodAndSubmit(methodValue){
	    document.getElementById("_method").value = methodValue;
		document.getElementById("employee").submit();
	 }
	</script>
	<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
	<script type="text/javascript">
	google.charts.load('current', {'packages':['corechart']}); // 固定
    google.charts.setOnLoadCallback(drawChart);                // 固定
function drawChart() { 
	salary(1);
	jobs();
}

function salary(chartId) {
	    	var data = google.visualization.arrayToDataTable([ // 固定
		        ['ename', 'salary'],                           // key and Value
		        <c:forEach var="emp" items="${ employees }">   // 拆解 employees 內容
		        ['${ emp.ename }', ${emp.salary}],             // key 放入值
		        </c:forEach>
			]);
		
			var options = {
				title: 'Salary'                                // 標題
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

function jobs() {
	    	var data = google.visualization.arrayToDataTable([   // 固定
		        ['ename', 'job_count'],                          // key and Value
		        <c:forEach var="emp" items="${ employees }">     // 拆解 employees 內容
		        	// 判斷工作數量是否  > 0
		        	<c:if test="${ fn:length(emp.jobs) > 0 }">
		        		// 判斷第一筆工作的 jid 不可以是 null
				        <c:if test="${ emp.jobs[0].jid != null }">
				        	['${ emp.ename }', ${ fn:length(emp.jobs) }], // key 放入值 
				        </c:if>
				        <c:if test="${ emp.jobs[0].jid == null }"> 
				        	['${ emp.ename }', 0],                        // key 放入值
				        </c:if>	
			        </c:if>
		        </c:forEach>
			]);
		
			var options = {
				title: 'Jobs'
			};
		
			var chart = new google.visualization.LineChart(document.getElementById('line_chart')); // 調整圖形
			chart.draw(data, options); // 固定	    	
}
	</script>
</head>
<body style="padding: 15px;">

	<table style="width: 100%">
		<tr>
			<!-- Employee Form -->
			<td valign="top">
				<spform:form  class="pure-form"
				              method="post" 
				              modelAttribute="employee"
				              action="${ pageContext.request.contextPath}/mvc/jdbc/employee/">
				<fieldset>
				<legend>
				 <b>Employee Form</b> &nbsp;|&nbsp;
				 <a href="${ pageContext.request.contextPath}/mvc/jdbc/job/">Job Form</a>
				 
				</legend>             
				<input type="hidden" id="_method" name="_method" value="${_method}"/>
				編號 : <spform:input path="eid" readonly="true"/> <p/>
				      <spform:errors path="eid" cssClass="error" /> <p/>
				姓名 : <spform:input path="ename"/> <p/>
				      <spform:errors path="ename" cssClass="error" /> <p/>
				薪資 : <spform:input path="salary" type="number"/> <p/>
				      <spform:errors path="salary" cssClass="error" /> <p/>
				      <button type="submit" ${_method eq 'POST' ? '' : 'disabled' } 
				              class="pure-button pure-button-primary">
				              新增
				      </button>
				      <button type="submit" ${_method eq 'PUT' ? '' : 'disabled' } 
				              class="pure-button pure-button-primary">
				              修改
				      </button>
				      <button type="button" ${_method eq 'PUT' ? '' : 'disabled' } 
				              onclick="changeMethodAndSubmit('DELETE')"
				              class="pure-button pure-button-primary">
				              刪除
				      </button>
				     <font color="red">${ message }</font>  
				</fieldset>       
				</spform:form>
			</td>
			<td valign="top">
				<form class="pure-form">
					<fieldset>
						<legend>
							Salary Chart |
							<a href="#" onclick="salary(1)">bar</a> |
							<a href="#" onclick="salary(2)">pie</a> |
							<a href="#" onclick="salary(3)">column</a> |
							<a href="#" onclick="salary(4)">line</a>
						</legend>
						<div id="piechart" style="width: 500px; height: 300px;"></div>
					</fieldset>
				</form>
			</td>
			<!-- Jobs Line Chart -->
			<td valign="top">
				<form class="pure-form">
					<fieldset>
						<legend>Jobs Line Chart</legend>
						<div id="line_chart" style="width: 400px; height: 250px"></div>
					</fieldset>
				</form>
			</td>
		</tr>
		<tr>
			<!-- Employee List -->
			<td valign="top" colspan="4">
				 <form class="pure-form">
				  <fieldset>
				   <legend>Employee List || 
				   <a href="${ pageContext.request.contextPath}/mvc/jdbc/employee/page/-1">全部查詢</a>
				   <c:forEach end="${pageCount}" begin="1" var ="num">
				     <a href="${ pageContext.request.contextPath}/mvc/jdbc/employee/page/${num}">${num}</a>
				   </c:forEach> </legend>
				   <table class="pure-table pure-table-bordered" style="width: 100%">
				    <thead>
				     <tr>
				      <th>編號</th>
				      <th>姓名</th>
				      <th>薪資</th>
				      <th>工作</th>
				      <th>建立時間</th>
				     </tr>
				    </thead>
				    <tbody>
				     <c:forEach items="${employees}" var="emp">
				     <tr>
				      <td>
				       <a href="${ pageContext.request.contextPath}/mvc/jdbc/employee/${emp.eid }">${emp.eid }</a>
				      </td>
				      <td>${emp.ename }</td>
				      <td>${emp.salary }</td>
				      <td>
				       <c:forEach items="${emp.jobs}" var="job">
				        ${job.jname} 
				       </c:forEach>
				      </td>
				      <td>${emp.createtime}</td>				      
				     </tr>
				     </c:forEach>
				    </tbody>
				   </table>
				  </fieldset>
				 </form>
			</td>
		</tr>
	</table>
</body>
</html>