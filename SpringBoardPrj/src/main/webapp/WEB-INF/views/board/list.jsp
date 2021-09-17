<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.text-red {
    color: red;
}
</style>
<script>
$('#search').keyup(function () {
    var search = $('#search').val();
    $("#title:contains('"+search+"')").each(function () {
        var regex = new RegExp(search,'gi');
        $(this).html( $(this).text().replace(regex, "<span class='text-red'>"+search+"</span>") );
    });
});
</script>

</head>
<body>
	<div class="container">
		<h2 class="text text-primary">글 전체 조회 페이지</h2>
		<a href="/board/register"><button type="button" class="btn btn-primary">글쓰러가기</button></a>
		<form action="/board/list" method="get">
					<input class="search" type="text" name="keyword" placeholder="${keyword}">
					<input type="submit" value="검색">
				</form>
		<table class="table table-striped">
			<thead>
				<tr align="center">
					<td>글번호</td>
					<td>제목</td>
					<td>글쓴이</td>
					<td>작성일</td>
				</tr>
			</thead>
			<c:forEach items="${list}" var="bl">
				<tr align="center" height="30">
					<td><a href="/board/get?bno=${bl.bno}">${bl.bno}</a></td>
					<td id="title">${bl.title}</td>
					<td>${bl.writer}</td>
					<td>${bl.regdate}</td>
			</c:forEach>

		
		</table>
		
		<script>
		//컨트롤러에서 success라는 이름으로 날린 자료가 들어오는 지 확인
		//그냥 list페이지 접근시는 success를 날려주지 않아서
		//아무것도 들어오지 않고
		//remove 로직의 결과로 넘어왔을때만 데이터가 전달됨
			var result= "${result}";
			var bno = "${bno}"
		
			if(result=="success"){
				alert(bno + "번째 글이 삭제되었습니다.");
			}
			console.log(result)
		</script>
	</div>
</body>
</html>