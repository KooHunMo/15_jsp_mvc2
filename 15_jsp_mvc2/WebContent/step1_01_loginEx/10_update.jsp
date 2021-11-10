<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>10_update</title>
</head>
<body>
	<div align="center">
		<c:choose>
			<c:when test="${isFirstApply eq false}">
				<h1>회원정보 수정</h1>
				<p>개인정보와 입사지원 내용을 확인해주세요.</p>
				<hr>
				<form action="update.do" method="post">
					<h4>개인 정보</h4>
					<label for="id">아이디</label>
					<input type="text" id="id" name="id" value="${ memberDto.id }" >
					<br><br>
					<label for="pw">패스워드</label>
					<input type="password" id="pw" name="pw" value="${ memberDto.pw }">
					<br><br>
					<label for="name">이름</label>
					<input type="text" id="name" name="name" value="${ memberDto.name }">
					<br><br>
					<label for="tel">연락처</label>
					<input type="text" id="tel" name="tel" value="${ memberDto.tel }" size="20"> 
					<br><br>
					<label for="email">이메일</label>
					<input type="email" id="email" name="email" value="${ memberDto.email }">
					<h4>지원 분야</h4>
					<label>
						<input type="radio" name="field" value="publishing" 
							<c:if test="${ memberDto.field eq 'publishing'}"> checked </c:if>>웹 퍼블리싱
					</label>
					<label>
						<input type="radio" name="field" value="frontend" 
							<c:if test="${ memberDto.field eq 'frontend'}"> checked </c:if>>프론트앤드
					</label>
					<label>
						<input type="radio" name="field" value="application" 
							<c:if test="${ memberDto.field eq 'application'}"> checked </c:if>>웹 애플리케이션 개발
					</label>
					<h4>기술 능력</h4>
					<label>
						<input type="checkbox" name="skill" value="html"
							<c:if test="${html eq true}"> checked</c:if>>HTML
					</label>
						
					<label>
						<input type="checkbox" name="skill" value="css" 
							<c:if test="${css eq true}"> checked</c:if>> CSS
					</label>
					<label>
						<input type="checkbox" name="skill" value="javascript"
							<c:if test="${javascript eq true}"> checked</c:if>>JAVASCRIPT
					</label>
					<label>
						<input type="checkbox" name="skill" value="java"
							<c:if test="${java eq true}"> checked</c:if>>JAVA
					</label>
					<label>
						<input type="checkbox" name="skill" value="jsp"
							<c:if test="${jsp eq true}"> checked</c:if>>JSP
					</label>
					<label>
						<input type="checkbox" name="skill" value="spring"
							<c:if test="${spring eq true}"> checked</c:if>>SRRING
					</label>
					<h4>전공 분야</h4>
					<label for="major">학과</label>
					<select id="major" name="major">
						<option value="computer" <c:if test="${ memberDto.major eq 'computer' }">selected</c:if> >컴퓨터공학과</option>
						<option value="elec" <c:if test="${ memberDto.major eq 'elec' }">selected</c:if> >전기전자공학과</option>
						<option value="mechanic" <c:if test="${ memberDto.major eq 'mechanic' }">selected</c:if> >기계공학과</option>
						<option value="indust" <c:if test="${ memberDto.major eq 'indust' }">selected</c:if> >산업공학과</option>
					</select>
					<br><br>
					<div>
						<input type="submit" value="수정하기">
						<input type="reset" value="다시 쓰기">
					</div>
				</form>
			</c:when>
			<c:otherwise>
				<script>
					alert("아직 입사지원을 하지 않으셨습니다.");
					history.go(-1);
				</script>
			</c:otherwise>
		</c:choose>	
	</div>	
</body>
</html>