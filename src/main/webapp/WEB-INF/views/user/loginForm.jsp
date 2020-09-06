<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>로그인</title>
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1" />
	<link rel="stylesheet" href="resources/assets/css/main.css?after" />
</head>
<body>
	<section id="main">
		<header id="header">
			<div>로그인</div>
		</header>
		
		<div class="inner">
				<form action="${pageContext.request.contextPath}/loginPass" method="post">
			ID: <input type="text" name="id"><br>
			PW: <input type="password" name="pw">
			<input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}"/>
			<input type="submit" value="로그인">
			</form>

			* <a href="signUpPage">회원가입</a>
			
			<c:if test='${error eq "login"}'>
				<h4 style="color: red">아이디, 비밀번호 오류.</h4>
			</c:if>
			<c:if test='${error eq "duplicate"}'>
				<h4 style="color: red">이미 로그인 중인 사용자.</h4>
			</c:if>
			<c:if test='${error eq "NotFound"}'>
				<h4 style="color: red">등록되지 않은 유저.</h4>
			</c:if>
		</div>
	</section>
</body>
</html>