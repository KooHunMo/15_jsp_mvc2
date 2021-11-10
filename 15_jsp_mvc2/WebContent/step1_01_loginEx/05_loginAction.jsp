<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>05_loginAction</title>
</head>
<body>
	
	<c:choose>
		<c:when test="${isLogin eq true }">
			<script>
				alert("${sessionScope.id}님 환영합니다.");
				location.href =  "main.do";
			</script>
		</c:when>
		<c:otherwise>
			<script>
				alert("아이디와 패스워드를 확인해주세요.");
				history.go(-1);
			</script>
		</c:otherwise>
	</c:choose>

</body>
</html>