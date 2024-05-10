<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>와이파이 정보 구하기</title>
</head>

<body>
	<h1>북마크 그룹 추가</h1>
	
	<a href="hello.jsp">홈</a> |
	<a href="request2.html">위치 히스토리 목록</a> |
	<a href="request.html">Open API 와이파이 정보 가져오기</a> |
	<a href="group.html">북마크 그룹 관리</a> |
	<a href="mark.html">북마크 보기</a>
	
	<br><br>
	
	<form name="group_add" method="post" enctype="UTF-8">
	
		<table width=1870>

		<tr>
			<td style="color:white; font-weight : bold;" bgcolor="#3cb371" width="10%">북마크 이름</td>
			<td><input type='text' name='name'></td>
		</tr>
		
		<tr>
			<td style="color:white; font-weight : bold;" bgcolor="#3cb371" width="10%">순서</td>
			<td><input type='text' name='num'></td>
		</tr>
		
		<tr>
		<td colspan="2">ㅤㅤㅤㅤㅤㅤㅤㅤㅤㅤ<input type="submit" value="추가" formaction="group_add"></td>
		</tr>

		</table>
		
	</form>


</body>
</html>
