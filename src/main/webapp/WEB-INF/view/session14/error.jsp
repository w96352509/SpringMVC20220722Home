<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<link rel="stylesheet" href="https://unpkg.com/purecss@2.0.6/build/pure-min.css">
		<meta charset="UTF-8">
		<title>錯誤管理頁面</title>
	</head>
	<body style="padding: 15px">
		<form class="pure-form" action="${ referer }">
			<fieldset>
				<legend>錯誤管理</legend>
				Referer: ${ referer }<p />
				錯誤訊息: ${ ex }<p />
				<button type="submit" class="pure-button pure-button-primary">返回</button>
			</fieldset>
			
		</form>
	</body>
</html>