<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="pageNav" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이미지 게시판 리스트</title>
<style type="text/css">
.dataRow:hover {
	background: #fff;
	cursor: pointer;
	opacity: 80%; /* 투명도 */
}

button:hover {
	cursor: pointer;
}

#word {
	width: 200px; /* 원하는 너비로 조정 */
}

.card-img-top {
	height: 300px; /* 높이 고정 */
	object-fit: cover; /* 이미지 비율 유지하면서 크기 조정 */
}

.title {
	height: 20px;
}
/* .imageDiv{ */
/* 	background: black; */
/* } */
</style>
<script type="text/javascript">
	$(function() {

		// 이미지 사이즈 조정 5:4
		let imgWidth = $(".imageDiv:first").width();
		let imgHeight = $(".imageDiv:first").height();
		console.log("image width=" + imgWidth + ", height=" + imgHeight);

		// 높이 계산 - 너비는 동일하다. : 이미지와 이미지를 감싸고 있는 div의 높이로 사용
		let height = imgHeight / 5 * 4;
		// 전체 imageDiv 의 높이를 조정한다.
		$(".imageDiv").height(height);

		// 이미지 배열로 처리하면 안된다.forEach문 - Jquery 사용시 each() 사용
		$(".imageDiv > img").each(function(idx, image) {
			// 이미지가 계산된 높이보다 크면 줄인다.
			if ($(image).height() > height) {
				let image_width = $(image).width();
				let image_height = $(image).height();
				let width = height / image_height * image_width;
				console.log("changed image_width = " + width);
				// 이미지 높이 줄이기        	
				$(image).height(height);
				// 이미지 너비 줄이기
				$(image).width(width);

			}
		});

		// 1.이벤트 처리(우선 확인 하고 코드 짜기)
		$(".dataRow").click(function() {
			// 글번호 필요 - 수집
			let no = $(this).find(".no").text();
			console.log("no = " + no);
			location = "view.do?no=" + no + "&${pageObject.pageQuery}";
		});

		// perPageNum 처리
		$("#perPageNum").change(function() {
			// page는 1page + 검색 데이터를 전부 보낸다.
			$("#searchForm").submit();
		});

		// 검색 데이터 세팅
		$("#key").val("${(empty pageObject.key)?'t':pageObject.key}");
		$("#perPageNum").val(
				"${(empty pageObject.perPageNum)?'10':pageObject.perPageNum}");
	});
</script>
</head>
<body>
	<div class="container">
		<br> <br>
		<h1>Image Board List</h1>
		<br> <br>
		<form action="list.do" id="searchForm">
			<!-- 검색을 하면 무조건 1페이지로 처음에 나오게 설정 -->
			<input name="page" value="1" type="hidden">
			<div class="row">
				<div class="col-md-8">
					<div class="input-group mb-3">
						<div class="input-group-prepend">
							<select name="key" id="key" class="form-control">
								<option value="t">제목</option>
								<option value="c">내용</option>
								<option value="tc">제목/내용</option>
								<option value="f">파일명</option>
							</select>
						</div>
						<input type="text" class="form-control" placeholder="검색" id="word"
							name="word" value="${pageObject.word}">
						<div class="input-group-append">
							<button class="btn btn-outline-dark">
								<i class="fa fa-search"></i>
							</button>
						</div>
					</div>
				</div>
				<!-- col-md-8의 끝 : 검색 -->
				<div class="col-md-4">
					<!-- 너비를 조정하기 위한 div 추가. float-right : 오른쪽 정렬 -->
					<div style="width: 200px;" class="float-right">
						<div class="input-group mb-3">
							<div class="input-group-prepend">
								<span class="input-group-text">Rows/Page</span>
							</div>
							<select id="perPageNum" name="perPageNum" class="form-control">
								<option>6</option>
								<option>9</option>
								<option>12</option>
								<option>15</option>
							</select>
						</div>
					</div>
				</div>
				<!-- col-md-4의 끝 : 한페이지당 표시 데이터 개수 -->
			</div>
		</form>
		<c:if test="${empty list}">
			<div class="jumbotron">
				<h5>데이터가 존재하지 않습니다.</h5>
			</div>
		</c:if>
		<c:if test="${!empty list}">
			<div class="row">
				<!-- 이미지의 데이터가 있는 만큼 반복해서 표시하는 처리 -->
				<c:forEach items="${list}" var="vo" varStatus="vs">
					<!-- 줄바꿈 처리 - 찍는 인덱스 번호가 3의 배수이면 줄 바꿈을 한다. -->
					<c:if test="${vs.index != 0 && vs.index % 3 == 0}">
						${"</div>" }
						${"<div class='row'>" }
					</c:if>
					<!-- 데이터 표시 -->
					<div class="col-md-4 dataRow mb-3">
						<div class="card" style="width: 100%">
							<div class="imageDiv text-center align-content-center">
								<img class="card-img-top" src="${vo.fileName}" alt="image"
									style="width: 100%">
							</div>
							<div class="card-body">
								<strong class="card-title"> <span class="float-right">${vo.writeDate }</span>
									${vo.name }(${vo.id })
								</strong>
								<p class="card-text text-truncate title">
									<span class="no">${vo.no}</span>.${vo.title }
								</p>
							</div>
						</div>
					</div>
					<!-- 데이터 표시 끝 -->
				</c:forEach>
				<!-- 이미지의 데이터가 있는 만큼 반복해서 표시하는 처리 끝 -->
			</div>
			<!-- 페이지 네이션 처리 및 등록 버튼 -->
			<div class="d-flex justify-content-between align-items-center mt-3">
				<c:if test="${!empty login }">
					<a href="writeForm.do?perPageNum=${pageObject.perPageNum}"
						class="btn btn-dark">등록</a>
				</c:if>
				<div class="mx-auto">
					<pageNav:pageNav listURI="list.do" pageObject="${pageObject}"></pageNav:pageNav>
				</div>
			</div>
		</c:if>
	</div>
</body>
</html>
