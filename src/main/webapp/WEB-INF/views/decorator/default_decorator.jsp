<!-- sitemesh 사용을 위한 설정 파일 -->
<!-- 작성자 : 함수진 -->
<!-- 작성일 : 2017-01-12 -->
<!-- 최종수정일 : 2024-06-28 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="decorator"
	uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 개발자가 작성한 title을 가져다가 사용 -->
<title>웹짱:<decorator:title />
</title>
<!-- Bootstrap 4 + jquery 라이브러리 등록 - CDN 방식 -->
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<!-- 아이콘 라이브러리 -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<style type="text/css">
/* 외부 폰트 사용 import */
@import
	url('https://fonts.googleapis.com/css2?family=Merriweather:ital,wght@0,300;0,400;0,700;0,900;1,300;1,400;1,700;1,900&display=swap')
	;

pre {
	background: white;
	border: 0px;
}

/* Remove the navbar's default margin-bottom and rounded borders */
.navbar {
	margin-bottom: 0;
	border-radius: 0;
}

/* Add a gray background color and some padding to the footer */
footer {
	background-color: #f2f2f2;
	padding: 25px;
}

.carousel-inner img {
	width: 100%; /* Set width to 100% */
	margin: auto;
	min-height: 200px;
}

/* Hide the carousel text when the screen is less than 600 pixels wide */
@media ( max-width : 600px) {
	.carousel-caption {
		display: none;
	}
}

article {
	min-height: 795px;
}

#welcome {
	color: grey;
	margin: 0 auto;
}
/* 내 리스트 스타일----------- */
.dataRow:hover {
	cursor: pointer; /* 게시글에 마우스를 올리면 커서가 포인터로 바뀐다.*/
}

h1 {
	text-align: center; /* h1 태그 텍스트 가운데 정렬 */
}

.nav-item {
	margin: 0 10px; /* 네비게이션 바 버튼 간격 조정하기 */
}

.container {
	text-align: left; /* 게시판 텍스트 가운데 정렬*/
}

.bg-custom {
	background-color: #d2d9e9; /* 네비게이션 바 색상 설정*/
}

/* !important : CSS의 우선순위 규칙을 무시하고 해당 스타일이 먼저 적용된다.*/
.nav-link {
	color: #444 !important; /* nav-item 텍스트 색상을 검정색으로 변경 */
	font-weight: bold; /* nav-item 텍스트를 굵게 변경 */
}

.pagination .page-link {
	color: #000 !important; /* 부트스트랩 페이지네이션 폰트 색상 변경*/
}

.merriweather-bold { /* 외부 폰트 설정*/
	font-family: "Merriweather", serif;
	font-weight: 700;
	font-style: normal;
}
</style>

<!-- 개발자가 작성한 소스의 head 태그를 여기에 넣게 된다. (title은 제외) -->
<decorator:head />
</head>
<body>

	<header>
		<!-- 네비게이션 바 -->
		<nav class="navbar navbar-expand-lg bg-custom">
			<!-- 네비게이션 로고 -->
			<a
				class="navbar-brand ${(empty module || module == '/main')?'active':'' }"
				href="/board/list.do"> <img src="/img/logo.png" alt="Logo"
				style="width: 80px;">
			</a>
			<!-- 모바일일때 네비게이션 바 햄버거 형식으로 설정 -->
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarNav" aria-controls="navbarNav"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<!-- 네비게이션 메뉴버튼 -->
			<div class="collapse navbar-collapse" id="navbarNav">
				<ul class="navbar-nav mr-auto ">
					<li class="nav-item"><a class="nav-link"
						href="/playlist/list.do"
						style="font-family: 'Merriweather', serif; font-weight: bold;">PlayList</a>
					</li>
					<li class="nav-item" ${( module == '/image')?'active':'' }><a class="nav-link" href="/image/list.do"
						style="font-family: 'Merriweather', serif; font-weight: bold;">Image</a>
					</li>
					<li class="nav-item ${( module == '/notice')?'active':'' }"><a
						class="nav-link" href="/notice/list.do"
						style="font-family: 'Merriweather', serif; font-weight: bold;">Notice</a>
					</li>
					<li class="nav-item"><a class="nav-link" href="#"
						style="font-family: 'Merriweather', serif; font-weight: bold;">Top100</a>
					</li>
					<li class="nav-item"><a class="nav-link" href="#"
						style="font-family: 'Merriweather', serif; font-weight: bold;">New</a>
					</li>
					<li class="nav-item ${( module == '/board')?'active':'' }"><a
						class="nav-link" href="/board/list.do"
						style="font-family: 'Merriweather', serif; font-weight: bold;">Board</a>
					</li>
				</ul>
				<ul class="navbar-nav">
					<c:if test="${ empty login }">
						<!-- 로그인을 안했을 때 -->
						<li class="nav-item">
						<a class="nav-link" href="/member/loginForm.do"> <i
								class="fa fa-sign-in"> Login </i>
						</a></li>

						<li class="nav-item"><a class="nav-link" href="/member/writeForm.do"> <i
								class="fa fa-address-card-o"> Join </i>
						</a></li>
						<li class="nav-item"><a class="nav-link" href="/member/searchID.do"> <i
								class="fa fa-search"> Find ID/PW </i>
						</a></li>
					</c:if>
					<c:if test="${ !empty login }">
						<!-- 로그인을 했을 때 -->
						
						<li class="nav-item"><a class="nav-link" href="/member/logout.do"><i class="fa fa-sign-out"></i> Logout </a></li>
						<li class="nav-item"><a class="nav-link" href="/member/view.do"><i class="fa fa-address-card-o"></i> MyPage </a></li>
						<li class="nav-item"><a class="nav-link" href="/cart/list.do"><i class="fa fa-shopping-cart"></i> Cart </a></li>
					</c:if>
				</ul>
<!-- 				eeeeee -->
<!-- 				<div class="dropdown"> -->
<!-- 				로그인 안 했을때 -->
<%-- 				<c:if test="${ empty login }"> --%>
<!-- 					<button type="button" class="btn btn-dark dropdown-toggle" -->
<!-- 						data-toggle="dropdown">Login</button> -->
<!-- 					<div class="dropdown-menu"> -->
<!-- 						<a class="dropdown-item" href="#">Link 1</a> <a -->
<!-- 							class="dropdown-item" href="#">Link 2</a> <a -->
<!-- 							class="dropdown-item" href="#">Link 3</a> -->
<!-- 						<div class="dropdown-divider"></div> -->
<!-- 						<a class="dropdown-item" href="#">Another link</a> -->
<!-- 					</div> -->
<%-- 				</c:if> --%>
<!-- 				</div> -->
				
				
			</div>
		</nav>
	</header>

	<article>
		<!-- 개발자가 작성한 소스의 body 태그를 여기에 넣게 된다. -->
		<decorator:body />
	</article>
	<footer class="container-fluid text-center" style="background: #d2d9e9;">
		<p style="color: white">Copyright © 2024 Musaic All right
			reserved.</p>
	</footer>

	<!-- 메세지를 뿌리는 div -->
	<c:if test="${ !empty msg }">
		<!-- msg를 표시할 모달 창 -->
		<!-- The Modal -->
		<div class="modal" id="msgModal">
			<div class="modal-dialog">
				<div class="modal-content">

					<!-- Modal Header -->
					<div class="modal-header">
						<h4 class="modal-title">처리 결과</h4>
						<button type="button" class="close" data-dismiss="modal">&times;</button>
					</div>

					<!-- Modal body -->
					<div class="modal-body">${msg }</div>

					<!-- Modal footer -->
					<div class="modal-footer">
						<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
					</div>

				</div>
			</div>
		</div>
		<!-- 모달을 보이게 하는 script -->
		<script type="text/javascript">
			$(function() {
				$("#msgModal").modal("show");
			});
		</script>
		<div></div>
	</c:if>
</body>
</html>
<!--  세션 지우기  -->
<%
session.removeAttribute("msg");
%>