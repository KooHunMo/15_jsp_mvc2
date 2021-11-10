<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>01_main</title>
</head>

<%--

	# MVC2 데이터베이스 연동예시


	1. 이클립스에서 해당 프로젝트 > WebContent > WEB-INF > lib폴더에 아래의 라이브러리 추가 
		
		commons-collections4-4.1.jar
		commons-dbcp2-2.2.0.jar
		commons-pool2-2.5.0.jar
		jstl-1.2.jar
		mysql-connector-java-8.0.15.jar
		
	
	2. 이클립스에서 Servers폴더에 있는 Context.xml파일에 아래의 내용 추가 
	
		<Resource 
			auth="Container" 
			driverClassName="com.mysql.cj.jdbc.Driver"
			loginTimeout="10" 
			maxWait="5000" 
			name="jdbc/pool" 
			username="root"
			password="1234" 
			type="javax.sql.DataSource"
			url="jdbc:mysql://localhost:3306/MVC2_login_EX?serverTimezone=UTC"
		/> 
 
 --%>

<body>
	
	
	
	<div align="center">
		<c:if test="${id ne null }">
			${id }님 , 환영합니다. <br><br>
			<a href="update.do">입사지원정보 수정</a><br><br>
			<a href="logout.do">로그아웃</a><br><br>
			<a href="delete.do">회원탈퇴</a><br><br>
		</c:if>
		<c:if test="${id eq null }">
			<a href="join.do">회원가입</a><br><br>
			<a href="login.do">로그인</a><br><br>
		</c:if>
	</div>
	
	<hr><br>
	<div align="center">
		<a href="apply.do"><img src="img/applyonline.png" alt="입사지원하기"> </a>
	</div>

</body>
</html>