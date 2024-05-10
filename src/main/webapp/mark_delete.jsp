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
	<h1>북마크 삭제</h1>

	<a href="hello.jsp">홈</a> |
	<a href="request2.html">위치 히스토리 목록</a> |
	<a href="request.html">Open API 와이파이 정보 가져오기</a> |
	<a href="group.html">북마크 그룹 관리</a> |
	<a href="mark.html">북마크 보기</a>

	<br>
	<br> 북마크를 삭제하시겠습니까?

	<br>
	<br>

	<table width=800>

		<tr>
			<td style="color:white; font-weight : bold;" bgcolor="#3cb371" width="20%">북마크 이름</td>
			<td><%=request.getParameter("group")%></td>
		</tr>

		<tr>
			<td style="color:white; font-weight : bold;" bgcolor="#3cb371">와이파이명</td>
			<td><%=request.getParameter("name")%></td>
		</tr>

		<tr>
			<td style="color:white; font-weight : bold;" bgcolor="#3cb371">등록일자</td>
			<td><%=request.getParameter("date")%></td>
		</tr>

		<tr>
			<td colspan=2>
				<form name="mark_delete" method="post" enctype="UTF-8">
					<a href="mark.html">돌아가기</a>
					<input type='hidden' name='wifi_id' value='<%=request.getParameter("id")%>'>
					<input type="submit" value="삭제" formaction="mark_delete">
				</form>
			</td>
		</tr>

	</table>

	<br>
	<br>




</body>
</html>
