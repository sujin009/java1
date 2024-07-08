<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이미지 게시판 등록</title>
<style>
.button-container {
	text-align: center;
}
</style>
</head>
<body>
	<div class="container">
		<br> <br> <br> <br>
		<h1>
			<i class="fa fa-pencil"></i> &nbsp;Image Write
		</h1>
		<br> <br> <br> <br>

		<form action="write.do" method="post" id="writeForm"
			enctype="multipart/form-data">
			<input name="perPageNum" value="${param.perPageNum }" type="hidden">

			<div class="form-group">
				<label for="title" class="label"> 제목 </label> <span
					class="badge badge-secondary">필수</span> <input type="text"
					class="form-control" placeholder="제목 입력" id="title" name="title" required>
			</div>
			<div class="form-group">
				<label for="content" class="label"> 내용 </label> <span
					class="badge badge-secondary">필수</span>
				<textarea rows="10" class="form-control" placeholder="내용 입력"
					id="content" name="content" required></textarea>
			</div>
			<div class="form-group">
				<label for="imageFile" class="label"> 이미지 </label> <span
					class="badge badge-secondary">필수 <i class="fa fa-upload"></i></span> <input type="file"
					class="form-control" placeholder="제목 입력" id="imageFile" name="imageFile" required>
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