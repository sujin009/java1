<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이미지 보기</title>
<script>
	$(document).ready(function() {
		$('[data-toggle="tooltip"]').tooltip();
		// 삭제 버튼 이벤트 처리
		$("#deleteBtn").click(function () {
			// js 경고창 - 1. alert : 일반 경고창, 2.conform : 확인/취소 버튼, 3. prompt : 키 입력시
			// 확인 창이 나타나는데 취소 누를시 진행시키지 않는다. (return false)
			if(!confirm("정말 삭제하시겠습니까?")) return false;
		});
	});
</script>
</head>
<body>
	<div class="container">
		<br> <br> <br>
		<h1>
			<i class="fa fa-soundcloud"></i>&nbsp;Image View
		</h1>
		<br> <br> <br>
		<div class="card">
			<div class="card-header">
				<b>${vo.no }. ${vo.title }</b> <span class="float-right">${vo.writeDate }</span>
			</div>
			<div class="card-body">
				<div class="card">
					<div class="card-header">
						<!-- card 아래의 card-img-overlay 위쪽의 이미지 또는 class="card-img-top" card 클래스 밖에도 적용
                	- width의 기본이 100%  -->
						<img src="${vo.fileName }" alt="image">
						<div class="card-img-overlay">
							<!-- 로그인 처리가 되어 있고 올린 사람이 수정해야 할 때 -->
							<c:if test="${login.id == vo.id }">
								<!-- Button to Open the Modal -->
								<button type="button" class="btn btn-outline-dark btn-sm"
									data-toggle="modal" data-target="#changeImageModal">Change
									Img</button>
							</c:if>
							<!-- 누구나 볼 수 있게 해야함 -->
							<a href="${vo.fileName }" class="btn btn-outline-primary btn-sm "
								download>Download</a>
						</div>
					</div>
					<div class="card-body">
						<p class="card-text">
						<pre>${vo.content }</pre>
						</p>
					</div>
				</div>
			</div>
			<div class="card-footer">${vo.name }(${vo.id })</div>
		</div>
		<br>
		<!-- a tag : 데이터를 클릭하면 href의 정보를 가져와서 페이지 이동시킨다. -->
		<!-- 회원이 같을 경우에만 -->
		<c:if test="${!empty login && login.id == vo.id }">
			<a
				href="updateForm.do?no=${param.no }&perPageNum=${param.perPageNum}&key=${param.key}&word=${param.word}"
				class="btn btn-dark" title="이미지를 제외한 정보만 수정가능" data-toggle="tooltip"
				data-placement="top" id="updateBtn">수정</a>
			<a class="btn btn-danger" id="deleteBtn"
				href="delete.do?no=${vo.no }&deleteFileName=${vo.fileName }&perPageNum=${param.perPageNum}">삭제</a>
		</c:if>
		<a
			href="list.do?page=${param.page }&perPageNum=${param.perPageNum}&key=${param.key}&word=${param.word}"
			class="btn btn-dark">리스트</a>
	</div>
	<br>
	<br>
	<!-- contatiner의 끝 -->
	<!-- The Modal -->
	<!-- 원래 있는 이미지는 DB에서 지우고 업로드 하면 업데이트 시켜서 DB에 등록시키는 개념 -->
	<div class="modal" id="changeImageModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">Change Image</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				<form action="changeImage.do" enctype="multipart/form-data"
					method="post">
					<!-- 글(이미지) 번호 -->
					<input name="no" value="${vo.no }" type="hidden">
					<!-- 숨겨서 넘겨야 할 데이터는 no(이미지 번호) / 현재 파일명(삭제해야 할 파일) -->
					<!-- VO에는 포함시키지 않기 때문에 no, fileName만 바꾸고 DAO까지는 안 간다.  -->
					<input name="deleteFileName" value="${vo.fileName }" type="hidden">
					<!-- 페이지 정보도 넘긴다. -->
					<input name="page" value="${param.page }" type="hidden"> <input
						name="perPageNum" value="${param.perPageNum }" type="hidden">
					<input name="key" value="${param.key }" type="hidden"> <input
						name="word" value="${param.word }" type="hidden">

					<!-- Modal body -->
					<div class="modal-body">
						<div class="form-group">
							<label for="imageFile" class="label"> <b>Image</b>
							</label> <span class="badge badge-secondary">필수 <i
								class="fa fa-upload"></i></span> <input type="file"
								class="form-control" placeholder="제목 입력" id="imageFile"
								name="imageFile" required>
						</div>
					</div>
					<!-- Modal footer -->
					<div class="modal-footer">
						<button class="btn btn-dark">Change</button>
						<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
