<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이미지 게시판 수정</title>

</head>
<body>
	<form action="update.do" method="post" id="writeForm">
		<div class="container">
			<br> <br> <br>
			<h1>
				<i class="fa fa-pencil"></i> &nbsp;Image Update Form
			</h1>
			<br> <br> <br>
			<div class="alert alert-danger">
				<strong>이미지를 제외한 나머지 텍스트 데이터를 수정합니다. </strong> 이미지는 상단 이미지 안에 change image 사용하세요.
			</div>
			<!-- 페이지 세팅 -->
			<input type="hidden" name="page" value="${param.page }"> <input
				type="hidden" name="perPageNum" value="${param.perPageNum }">
			<input type="hidden" name="key" value="${param.key }"> <input
				type="hidden" name="word" value="${param.word }">
			<div class="form-group">
				<label for="no" class="label">번호</label> <input type="text"
					value="${vo.no }" class="form-control" id="no"
					placeholder="Enter no" name="no" readonly>
			</div>
			<div class="form-group">
				<label for="title" class="label">제목</label> <input type="text"
					value="${vo.title }" class="form-control" id="title"
					placeholder="Enter title" name="title" required>
			</div>
			<div class="form-group">
				<label for="content" class="label">내용</label>
				<textarea name="content" id="content" placeholder="내용 입력" rows="7"
					class="form-control" required>${vo.content } </textarea>
			</div>
			<div class="button-container">
				<button type="submit" class="btn btn-dark">수정</button>
				<button type="reset" class="btn btn-dark">다시 입력</button>
				<button type="button" class="btn btn-dark cancelBtn"
					onclick="history.back()">취소</button>
			</div>
			<br> <br> <br>
		</div>
	</form>
</body>
</html>