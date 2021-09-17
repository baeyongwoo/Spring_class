<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>게시글 수정 페이지</h2>
	<form action="/board/modify" method="post">
		<input type="text" name="bno" value="${getboard.bno}" readonly="readonly">
		<input type="text" name="title" value="${getboard.title}"></br>
		<textarea rows="10" cols="50" name="content">${getboard.content}</textarea></br>
		<input type="text" name="writer" value="${getboard.writer}" readonly="readonly"></br>
		<input type="submit" value="수정완료">
	</form>
</body>
</html>