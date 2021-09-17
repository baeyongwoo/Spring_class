<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<body>
	
	<div class="container">
		<h2 class="text text-primary">글 상세 조회 페이지</h2>
		<table class="table table-dark">
				<tr align="center" height="30">
					<tr><td>글번호 : ${getboard.bno}</td></tr>
					<tr><td>글제목 : ${getboard.title}</td></tr>
					<tr><td>글내용 : ${getboard.content}</td></tr>
					<tr><td>작성자 : ${getboard.writer}</td></tr>
					<tr><td>작성일 : ${getboard.regdate}</td></tr>
					<tr><td>수정날짜 : ${getboard.updatedate}</td></tr>
					
		</table>
		<a href="/board/list"><button>리스트로</button></a>
		<form action="/board/remove" method="post">
			<input type="hidden" name="bno" value="${getboard.bno}">
			<input type="submit" value="삭제">
		</form>
		<form action="/board/boardmodify" method="post">
			<input type="hidden" name="bno" value="${getboard.bno}">
			<input type="submit" value="수정">
		</form>
	</div>
</body>
</html>