<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


	<h2>Ajax 테스트</h2>
	
	<div>
		<div>
			REPLYER<input type="text" name="replyer" id="newReplyWriter">
		</div>
		<div>
			REPLY<input type="text" name="reply" id="newReply">
		</div>
		<button id="replyAddBtn">리플 추가</button>
	</div>
	
	<ul id="replies">
	
	</ul>
	 
	<!-- jquery -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script>
		var bno = 18;
		
		// 비동기 코드
		$("#replyAddBtn").on("click", function() {
			// 선택자로 # 빼먹지 말것
			var replyer = $("#newReplyWriter").val();
			var reply = $("#newReply").val();
				
			
			$.ajax({
				type : 'post' ,
				url :  '/replies/',
				headers:{
					"Content-Type" : "application/json",
					"X-HTTP-Method-Override" : "POST"
				},
				dataType : 'text',
				data : JSON.stringify({
					bno : bno,
					replyer : replyer,
					reply : reply
				}),
				success : function (result) {
					if(result =='SUCCESS'){
						alert("등록 되었습니다.");
						getAllList();
					}
					
				}
			});
			
		});
		
		
		function getAllList() {
			
		
		$.getJSON("/replies/all/" + bno, function(data) {
			
			// str변수 내부에 문자형태로 html 코드를 작성
			var str= "";
			
			str = "<li>test</li>";


			$(data).each(function() {
				// $(data).each()는 향상된 for문처럼 내부데이터를 하나하나 반복
				//내부 this는 댓글 하나하나임
	
				str += "<li data-rno='" + this.rno + "' class='replyLi'>"
					+ this.rno + ":" + this.reply
					+ "<button>수정/삭제</button</li>";
				
				});
			$("#replies").html(str);
			
			// #replies인 ul 태그 내부에 str을 끼워넣음
			// ul 내부에 <li>123</li>를 추가하는 구문
			
			});
		}
		getAllList();
	</script>
</body>
</html>