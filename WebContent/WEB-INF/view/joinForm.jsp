<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	label{
		width: 120px;
		float: left;
	}
	
	.error, .error2{
		color: red;
		font-size: 12px;
		display: none;
	}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
	$(function(){
		//form태그가 submit 이벤트를 발생시킨다. (만약 여러개면 이름주기)
		//$("form").submit();
		$("#f1").submit(function(){
			//미리 더 없애놓고 에러 떴을 때만 보이게 하고, 에러 안나면 다시 안보이게 함.
			$(".error, .error2").css("display", "none");
			
			//값 들고오기
			var id = $("input[name='memberId']").val();
			var name = $("input[name='name']").val();
			var pass = $("input[name='password']").val();
			var confirmPass = $("input[name='confirmPassword']").val();
			
			
			// ================= 아이디
			//입력하지 않았을 때 비어있는 String
			if(id == ""){	//id를 입력하지 않았을 때
				//에러를 보이게한다. name이 memberId의 형제인 span
				$("input[name='memberId']").nextAll(".error").css("display", "inline");
				//전송되는 것을 막는다.(submit을 막는다.)
				return false;
			}
			
			//id 정규표현식 ==> 영어, 숫자 포함 7~15자
			var regId = /^[a-z0-9]{7,15}$/i;
			
			//id가 정규표현식에 맞지 않으면
			if(regId.test(id) == false){
				//nextAll : 모든 다음 형제들 중 error2 클래스를 찾아서 css효과 적용시킨다.
				$("input[name='memberId']").nextAll(".error2").css("display", "inline");
			
				return false;	//submit되는 것을 막는다.
			}
			
			// ================= 이름
			if(name == ""){
				$("input[name='name']").next().css("display", "inline");
				return false;
			}
			
			//이름 정규 표현식 ==> 한글 2~5자
			var regName = /^[가-힣]{2,5}$/;
		
			if(regName.test(name) == false){
				$("input[name='name']").nextAll(".error2").css("display", "inline");
				return false;
			}
			
			// ================= 비밀번호
			if(pass == ""){
				$("input[name='password']").next().css("display", "inline");
				return false;
			}
			
			//비밀번호 정규 표현식 ==> 영어, 숫자, 특수문자 포함 8~20자
			var regPass = /^[a-zA-Z0-9!@#$%^&]{8,20}$/;
			
			if(regPass.test(pass) == false){
				$("input[name='password']").nextAll(".error2").css("display", "inline");
				return false;
			}
			
			// ================= 비밀번호 확인
			if(confirmPass == ""){
				$("input[name='confirmPassword']").next().css("display", "inline");
				return false;
			}
			
			//비밀번호와 비밀번호 확인이 일치하지 않았을 때
			if(pass != confirmPass){
				$("input[name='confirmPassword']").nextAll(".error2").css("display", "inline");
				return false;
			}
			
			//전송한다.
			return true;
		});
		
		//아이디중복확인 버튼 클릭했을 때
		$("#duplicatedIdBtn").click(function(){
			//입력한 id값 받아온다.
			var id = $("input[name='memberId']").val();
			
			$.ajax({
				url: "duplicatedId.do",
				type: "get",
				data: {"id":id},
				dataType: "json",
				success:function(data){
					//console.log(data);
					if(data.result == true){
						alert("이미 사용 중인 ID입니다.");
					}else{
						alert("사용가능한 ID입니다.");
					}
				}
			})
			
		})
	})
</script>
</head>
<body>
	<form action="join.do" method="post" id="f1">
		<p>
			<label>아이디</label>
			<input type="text" name="memberId">
			<!-- form태그안에 button은 submit이므로 type을 button으로 바꿔준다. -->
			<button type="button" id="duplicatedIdBtn">아이디 중복확인</button>
			<!-- error 떴을 때만 나타나게한다. -->
			<span class="error">ID를 입력하세요.</span>
			<span class="error2">영어, 숫자(7~15자)</span>
		</p>
		<p>
			<label>이름</label>
			<input type="text" name="name">
			<span class="error">이름을 입력하세요.</span>
			<span class="error2">한글(2~5자)</span>
		</p>
		<p>
			<label>비밀번호</label>
			<input type="password" name="password">
			<span class="error">비밀번호를 입력하세요.</span>
			<span class="error2">영어, 숫자, 특수문자(8~20자)</span>
		</p>
		<p>
			<label>비밀번호 확인</label>
			<input type="password" name="confirmPassword">
			<span class="error">비밀번호 확인을 입력하세요.</span>
			<span class="error2">비밀번호가 일치하지 않습니다.</span>
		</p>
		<p>
			<input type="submit" value="회원가입">
		</p>
	</form>
</body>
</html>