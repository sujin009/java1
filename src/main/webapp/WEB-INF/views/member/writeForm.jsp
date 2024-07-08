<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입</title>
<style>
.button-container {
	text-align: center;
}
</style>
<script type="text/javascript">
$(function(){
	// 이벤트 처리
	// 아이디 체크 처리
	$("#id").keyup(function(){
		let id = $("#id").val();
		// data  확인하기
		console.log("id = " + id);
		// 3자 미만인 경우의 처리
		if(id.length < 3){
			// class 지정 - 디자인
			// alert("3자 미만");
			$("#checkIdDiv").removeClass("alert-success alert-danger")
			.addClass("alert-danger");
		} else {
			// alert("3자 이상");
			// 서버에 가서 데이터를 확인하고 온다. - 결과를 JSP로 받는다.
			// $("#checkIdDIV") 안에 넣을 문구를 가져 와서 넣는다.
			$("#checkIdDiv").load("/ajax/checkId.do?id=" + id);
			if($("#checkIdDiv").text().indexOf("중복") >= 0)
				$("#checkIdDiv").removeClass("alert-success alert-danger")
				.addClass("alert-danger");
			else
				$("#checkIdDiv").removeClass("alert-success alert-danger")
				.addClass("alert-success");
			;
		}
	});
});
</script>
</head>
<body>
	<div class="container">
		<br> <br> <br> <br>
		<h1>&nbsp;JOIN</h1>
		<br> <br> <br> <br>

		<form action="write.do" method="post"
			enctype="multipart/form-data">
			<div class="form-group">
				<label for="id" class="id"> 아이디 </label> <span
					class="badge badge-secondary">필수</span> 		<input id="id" name="id" required autocomplete="off"
				class="form-control" maxlength="20"
				pattern="^[a-zA-Z][a-zA-Z0-9]{2,19}$"
				title="맨앞 글자는 영문자 뒤에는 영숫자 입력. 3~20 이내로 입력"
				placeholder="아이디 입력"
			>
			</div>
			<div id="checkIdDiv" class="alert alert-danger">
		  	아이디는 필수 입력 입니다. 3글자 이상입니다.
		  </div>
			<div class="form-group">
				<label for="content" class="label"> 내용 </label> <span
					class="badge badge-secondary">필수</span>
				<textarea rows="10" class="form-control" placeholder="내용 입력"
					id="content" name="content" required></textarea>
			</div>
			<div class="form-group">
				<label for="imageFile" class="label"> 이미지 </label> <span
					class="badge badge-secondary">필수 <i class="fa fa-upload"></i></span>
				<input type="file" class="form-control" placeholder="제목 입력"
					id="imageFile" name="imageFile" required>
			</div>
			<!-- 버튼을 가운데 정렬하는 div -->
			<div class="button-container">
				<button type="submit" class="btn btn-dark" id="writeBtn">등록</button>
				<button type="reset" class="btn btn-dark">다시 입력</button>
				<button type="button" class="btn btn-dark" id="cancelBtn">취소</button>
			</div>

			<br> <br> <br>

		</form>
	</div>
</body>
</html>