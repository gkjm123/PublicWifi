<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>와이파이 정보 구하기</title>
</head>

<body>
	<h1>북마크 그룹 수정</h1>

	<a href="hello.jsp">홈</a> |
	<a href="request2.html">위치 히스토리 목록</a> |
	<a href="request.html">Open API 와이파이 정보 가져오기</a> |
	<a href="group.html">북마크 그룹 관리</a> |
	<a href="mark.html">북마크 보기</a>

	<br>
	<br>

	<form name="group_update" method="post" enctype="UTF-8">
	
		<table width=1870>

		<tr>
			<td style="color:white; font-weight : bold;" bgcolor="#3cb371" width="10%">북마크 이름</td>
			<td><input type='text' name='name' id ='name'></td>
		</tr>
		
		<tr>
			<td style="color:white; font-weight : bold;" bgcolor="#3cb371" width="10%">순서</td>
			<td><input type='text' name='num' id='num'></td>
		</tr>
		
		<tr>
		<td colspan="2">ㅤㅤㅤㅤㅤㅤㅤ<a href="group.html">돌아가기</a> | <input type="submit" value="수정" formaction="group_update">
		</td>
		</tr>

		</table>
		
		<input type='hidden' name='id' value='<%=request.getParameter("id")%>'>
		

	</form>

	<script>
		window.onload = function() {
			const url = new URL(window.location.href);
			const urlParams = url.searchParams;
			document.getElementById("name").value = urlParams.get('name');
			document.getElementById("num").value = urlParams.get('num');
		}
	</script>


</body>
</html>
