<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>게시글 작성 페이지</h2>
	<form action="/board/register" method="post">
		<input type="text" name="title" placeholder="제목"></br>
		<textarea rows="10" cols="50" name="content"></textarea></br>
		<input type="text" name="writer" placeholder="작성자"></br>
		<input type="submit" value="작성완료">
	</form>
</body>
</html>