<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String msg = (String)request.getAttribute("msg");
%>
<!DOCTYPE html>
<html>
<head>
	<meta name="_csrf_header" th:content="${_csrf.headerName}">
	<meta name="_csrf" th:content="${_csrf.token}">
	<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>

<script src="http://code.jquery.com/jquery-3.3.1.js" integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60=" crossorigin="anonymous"></script>
<script type="text/javascript" src="http://localhost:8080/js/common.js"></script>
<script type="text/javascript">

	
	function signup(){
		
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
		
		var formdata = $("form[name=signForm]").serialize() ;
		
		Common.call("http://localhost:8080/sign/signup", formdata, function(data){
			console.log(data);
		}, false, header, token);
	}
</script>

<form name="signForm" action="post" onsubmit="return signup()">
    <input type="hidden" th:name="${_csrf.parameterName}" th:value="${_csrf.token}" />

    <div>
    	<input type="email" id="userId" name="userId" size="100" placeholder="아이디">
    </div>
    <div>
    	<input type="password" id="password" name="password" size="100" placeholder="비밀번호">
    </div>
    <div>
    	<input type="text" id="name" name="name" size="20" placeholder="이름">
    </div>
    <div>
    	<input type="password" id="fRegNo" name="fRegNo" size="6" placeholder="앞번호"> - <input type="password" id="lRegNo" name="lRegNo" size="7" placeholder="뒷번호">
    </div>
    <input type="submit" value="완료">
    
</form>

</body>
</html>