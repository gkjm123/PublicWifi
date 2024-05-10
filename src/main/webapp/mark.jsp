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
}
</style>

<body>
	<h1>북마크 목록</h1>
	
	<a href="hello.jsp">홈</a> |
	<a href="request2.html">위치 히스토리 목록</a> |
	<a href="request.html">Open API 와이파이 정보 가져오기</a> |
	<a href="group.html">북마크 그룹 관리</a> |
	<a href="mark.html">북마크 보기</a>
	
	<br><br>

	<table width=1870>

		<%=request.getAttribute("html")%>

	</table>

</body>
</html>
