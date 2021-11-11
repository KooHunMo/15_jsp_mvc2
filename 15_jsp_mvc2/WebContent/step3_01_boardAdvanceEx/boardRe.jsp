<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>10_bRe</title>
</head>
<body>
	<div align="center">
		<form action="boardRe.do" method="post">
			<h2>답변글 입력하기</h2>
			<br>
			<table border="1">
				<tr>
					<td>작성자</td>
					<td><input type="text" name="writer" /></td>
				</tr>
				<tr>
					<td>제목</td>
					<td><input type="text" name="subject" /></td>
				</tr>
				<tr>
					<td>이메일</td>
					<td><input type="email" name="email" /></td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td><input type="password" name="password" /></td>
				</tr>
				<tr>
					<td>글내용</td>
					<td><textarea  rows="10" cols="50"  name="content"></textarea></td>
				</tr>
				<tr align="center">
					<td colspan="2">
						<input type="hidden" name="ref" value="${ref }">
						<input type="hidden" name="reStep" value="${reStep}">
						<input type="hidden" name="reLevel" value="${reLevel}">
						<input type="submit" value="답글쓰기" />
						<input type="reset"  value="다시작성" />
						<input type="button" value="전체게시글보기"  onclick="location.href='boardList.do'">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>