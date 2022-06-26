<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String msg = (String)request.getAttribute("msg");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>

<script type="text/javascript">
	const msg = "<%=msg%>";
	if(msg != "null"){
		alert(msg);
	}
</script>

<h1>로그인</h1>
<hr>
<form action="/getlogin" method="post" enctype="multipart/form-data">
    <input type="hidden" th:name="${_csrf.parameterName}" th:value="${_csrf.token}" />

    <input type="text" name="F_EMAIL" placeholder="아이디">
    <input type="password" name="F_PASS" placeholder="비밀번호">
    <button type="submit">로그인</button>
    
    <a href="/szs/signin">회원가입하기</a>
</form>

</body>
</html>