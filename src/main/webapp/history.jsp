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
	<h1>위치 히스토리 목록</h1>
	
	<a href="hello.jsp">홈</a> |
	<a href="request2.html">위치 히스토리 목록</a> |
	<a href="request.html">Open API 와이파이 정보 가져오기</a> |
	<a href="group.html">북마크 그룹 관리</a> |
	<a href="mark.html">북마크 보기</a>
	
	<br><br>
	
	<form name="formLogin" method="post" enctype="UTF-8">

		LAT: <input id='la' type='text' name='x'>
		LNT: <input id='long' type='text' name='y'>
		<button type="button" onclick="javascript:show()">내 위치 가져오기</button>
		<input type="submit" value="근처 WIFI 정보 보기" formaction="login">
		
	</form>

	<br>
	<br>

	<table width=1870>

		<tr style="color:white; font-weight : bold;" bgcolor="#3cb371">
			<td width="10%">id</td>
			<td width="25%">X좌표</td>
			<td width="25%">Y좌표</td>
			<td width="30%">조회일자</td>
			<td width="10%">비고</td>
		</tr>

		<%=request.getAttribute("html")%>

	</table>

	<script>

	function show() {
		navigator.geolocation.getCurrentPosition((position) => {
			let latitude = position.coords.latitude;
			let longitude = position.coords.longitude;
			
			document.getElementById("la").value = latitude;
			document.getElementById("long").value = longitude;
		
		}, (err) => {
			
		})
	}
	

</script>

</body>

</html>