<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>와이파이 정보 구하기</title>
</head>

<style>
table {
	border-collapse: collapse;
}

th, td {
	text-align: center;
	border: 1px solid #FFFFFF;
	height: 30px;
}
</style>

<body>
	<h1>와이파이 정보 구하기</h1>

	<a href="hello.jsp">홈</a> |
	<a href="request2.html">위치 히스토리 목록</a> |
	<a href="request.html">Open API 와이파이 정보 가져오기</a> |
	<a href="group.html">북마크 그룹 관리</a> |
	<a href="mark.html">북마크 보기</a>

	<br>
	<br>

	<form name="mark_add" method="post" enctype="UTF-8">

		<%=request.getAttribute("html1")%>
		
		<input type='hidden' name='wifi_id' value='<%=request.getAttribute("wifi_id")%>'>
		<input type='hidden' name='wifi_name' value='<%=request.getAttribute("wifi_name")%>'>
		
		<input type="submit" value="북마크 추가하기" formaction="mark_add">

	</form>

	<br>
	<br>

	<table width=800>

		<%=request.getAttribute("html")%>

	</table>

</body>
</html>
