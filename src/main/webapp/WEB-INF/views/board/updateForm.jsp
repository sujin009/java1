<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>일반 게시판 글수정</title>

</head>
<body>
	<form action="update.do" method="post" id="writeForm">
		<div class="container">
		<!-- 페이지 세팅 -->
		<input type="hidden" name="page" value="${param.page }">
		<input type="hidden" name="perPageNum" value="${param.perPageNum }">
		<input type="hidden" name="key" value="${param.key }">
		<input type="hidden" name="word" value="${param.word }">
			<br> <br> <br>
			<h1><i class="fa fa-pencil"></i> &nbsp;Board Update </h1>
			<br> <br> <br>
			<div class="form-group">
				<label for="no" class="label">번호</label> <input type="text" value="${vo.no }"
					class="form-control" id="no" placeholder="Enter no" name="no" readonly>
			</div>
			<div class="form-group">
				<label for="title" class="label">제목</label> <input type="text" value="${vo.title }"
					class="form-control" id="title" placeholder="Enter title"
					name="title">
			</div>
			<div class="form-group">
				<label for="writer" class="label">작성자</label> <input type="text" value="${vo.writer }"
					class="form-control" id="writer" placeholder="Enter writer"
					name="writer">
			</div>
			<div class="form-group">
				<label for="content" class="label">내용</label>
				<textarea name="content" id="content" placeholder="내용 입력" rows="7"
					class="form-control">${vo.content }</textarea>
			</div>

			<div class="form-group">
				<label for="pw" class="label">비밀번호</label> 
				<input type="password" pattern="^.{3,20}$" title="3~20자 입력 가능"
					class="form-control" id="pw" placeholder="Enter password" name="pw" required maxlength="20"  placeholder="본인 확인용 비밀번호">
						
						
			</div>
			<div class="button-container">
				<button type="submit" class="btn btn-dark">수정</button>
				<button type="reset" class="btn btn-dark">다시 입력</button>
				<button type="button" class="btn btn-dark cancelBtn" onclick="history.back()">취소</button>
			</div>
			<br> <br> <br>
		</div>
	</form>
</body>
</html>