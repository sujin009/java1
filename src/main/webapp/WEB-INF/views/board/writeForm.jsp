<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>일반 게시판 글등록</title>
<style>
.button-container{
text-align: center;
}
</style>
</head>
<body>
	<div class="container">
		<br> <br> <br> <br>
		<h1>
			<i class="fa fa-pencil"></i> &nbsp;Board Write
		</h1>
		<br> <br> <br> <br>

		<form action="write.do" method="post" id="writeForm">
		<input name="perPageNum" value ="${param.perPageNum }" type="hidden">

			<div class="form-group">
				<label for="title" class="label"> 제목 </label> <span
					class="badge badge-secondary">필수</span> <input type="text"
					class="form-control" placeholder="제목 입력" id="title" name="title">
			</div>
			<div class="form-group">
				<label for="writer" class="label"> 작성자 </label> <span
					class="badge badge-secondary">필수</span> <input type="text"
					class="form-control" placeholder="작성자 입력" id="writer" name="writer">
			</div>
			<div class="form-group">
				<label for="content" class="label"> 내용 </label> <span
					class="badge badge-secondary">필수</span>
				<textarea rows="10" class="form-control" placeholder="내용 입력"
					id="content" name="content"></textarea>
			</div>
			<div class="form-group">
				<label for="pw" class="label"> 비밀번호 </label> <span
					class="badge badge-secondary">필수</span> <input type="password"
					class="form-control" placeholder="비밀번호 입력" id="pw" name="pw">
			</div>
			<div class="form-group">
				<label for="pw2" class="label"> 비밀번호 확인 </label> <span
					class="badge badge-secondary">필수</span> <input type="password"
					class="form-control" placeholder="비밀번호 확인 입력" id="pw2" name="pw2">
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