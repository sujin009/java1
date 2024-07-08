<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="pageNav" tagdir="/WEB-INF/tags" %>
<div class="card">
	<div class="card-header">
		<span class="btn btn-dark float-right" id="replyWriteBtn">등록</span>
		<h5 style="margin-top: 5px;">댓글</h5>
	</div>
	<div class="card-body">
		<!-- 데이터가 비어있는 경우 -->
		<c:if test="${empty replyList }">
   		데이터가 존재하지 않습니다.
   </c:if>
		<c:if test="${!empty replyList }">
			<!-- 데이터가 있는 만큼 반복되는 처리 시작 -->
			<c:forEach items="${replyList }" var="replyVO">
				<!-- rno 숨기는 곳 -->
				<div class="card replyDataRow" data-rno="${replyVO.rno }"
					style="margin: 15px 0;">
					<div class="card-header">
						<span class="float-right">${replyVO.writeDate }</span> <b
							class="replyWriter">${replyVO.writer }</b>
					</div>
					<div class="card-body">
						<pre class="replyContent">${replyVO.content }</pre>
					</div>
					<div class="card-footer">
						<!-- closest("repltDataRow") -->
						<button class="btn btn-dark replyUpdateBtn">수정</button>
						<button class="btn btn-danger replyDeleteBtn">삭제</button>
					</div>
				</div>
			</c:forEach>
		</c:if>
	</div>
	<!-- 데이터가 있는 만큼 반복되는 처리 끝 -->
		<div class="card-footer">
			<!-- 댓글 페이지 네이션 처리 -->
			<pageNav:replayPageNav listURI="view.do" pageObject="${replyPageObject }" query="&inc=0" />
		</div>
</div>
<br>
<br>
<br>
<br>

<!-- 댓글 등록 / 수정 / 삭제를 위한 모달창 -->
<div class="modal" id="boardReplyModal">
	<div class="modal-dialog">
		<div class="modal-content">

			<!-- Modal Header -->
			<div class="modal-header">
				<!-- 버튼에 따라서 제목을 수정해서 사용한다. - .text(제목) -->
				<h4 class="modal-title">댓글 등록</h4>
				<button type="button" class="close" data-dismiss="modal">&times;</button>
			</div>
			<!-- 데이터를 넘기는 form 태그를 감싼다 -->
			<form id="boardReplyForm" method="post">
				<input type="hidden" name="rno" id="rno"> 
				<input type="hidden" name="no" value="${param.no }">
					<!-- 페이지 정보 보내기 -->
					<input type="hidden" name="page" value="${param.page }">
					<input type="hidden" name="perPageNum" value="${param.perPageNum }">
					<input type="hidden" name="key" value="${param.key }">
					<input type="hidden" name="word" value="${param.word }">
				<!-- Modal body -->
				<div class="modal-body">
					<!-- 내용, 작성자, 비밀번호를 받아야 한다. -->
					<div class="form-group" id="contentDiv">
						<label for="content">내용:</label>
						<textarea class="form-control" rows="3" id="content"
							name="content"></textarea>
					</div>
					<div class="form-group" id="writerDiv">
						<label for="writer">작성자:</label> <input type="text"
							class="form-control" id="writer" name="writer">
					</div>
					<div class="form-group" id="pwDiv">
						<label for="pw">비밀번호:</label> <input type="password"
							class="form-control" id="pw" name="pw">
					</div>
				</div>

				<!-- Modal footer -->
				<div class="modal-footer">
					<button id="replyModalWriteBtn" class="btn btn-dark" type="button">등록</button>
					<button id="replyModalUpdateBtn" class="btn btn-dark" type="button">수정</button>
					<button id="replyModalDeleteBtn" class="btn btn-dark" type="button">삭제</button>
					<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
				</div>
			</form>
		</div>
	</div>
</div>

<script>
	$(function() {

		// 이벤트 처리
		// ------- [ modal 화면 이벤트 처리 ] ---------
		// 댓글 등록 이벤트
		$("#replyWriteBtn").click(function() {
			// 모달창 제목을 댓글 등록으로 바꾼다.
			$("#boardReplyModal").find(".modal-title").text("댓글 등록");
			// input , textarea를 선택한다. 
			$("#boardReplyForm").find("form-group").show();
			// 데이터 지우기 - 아무것도 없는 데이터를 세팅한다.
			$("#boardReplyForm").find(".form-group>input, .form-group>textarea").val("");
			// button을 선택한다.
			// 1. 버튼이 다 보이게 만든다.
			$("#boardReplyForm button").show();
			// 2. 필요 없는 버튼을 안 보이게 한다.
			$("#replyModalUpdateBtn, #replyModalDeleteBtn").hide();
			// 모달창 보이게하기
			$("#boardReplyModal").modal("show");

		});
		// 댓글 수정 이벤트
		$(".replyUpdateBtn").click(function() {
			// 모달창 제목을 댓글 수정으로 바꾼다.
			$("#boardReplyModal").find(".modal-title").text("댓글 수정");
			// input , textarea를 선택한다 - 내용, 작성자, 비밀번호 필요
			$("#boardReplyForm").find(".form-group").show();
			
			// 데이터 지우기 - 아무것도 없는 데이터를 세팅한다.
			$("#boardReplyForm").find(".form-group>input, .form-group>textarea").val("");
			
			// 댓글 번호, 내용, 작성자의 데이터를 수집해서 value값으로 세팅해야 한다.
			let replyDataRow = $(this).closest(".replyDataRow");
			// data("rno") -> tag 안에 data-rno = "값"
			// 데이터 찾기
			let rno = replyDataRow.data("rno");
			let content = replyDataRow.find(".replyContent").text();
			let writer = replyDataRow.find(".replyWriter").text();
			// reply Modal 입력란에 세팅하기
			$("#rno").val(rno);
			$("#content").val(content);
			$("#writer").val(writer);
			// button을 선택한다.
			// 1. 버튼이 다 보이게 만든다.
			$("#boardReplyForm button").show();
			// 2. 필요 없는 버튼을 안 보이게 한다.
			$("#replyModalWriteBtn, #replyModalDeleteBtn").hide();
			// 모달창 보이게하기
			$("#boardReplyModal").modal("show");

		});
		// 댓글 삭제 이벤트
		$(".replyDeleteBtn").click(function() {
			// 모달창 제목을 댓글 삭제로 바꾼다.
			$("#boardReplyModal").find(".modal-title").text("댓글 삭제");
			// input , textarea를 선택한다.
			$("#boardReplyForm").find(".form-group").hide();
			$("#pwDiv").show();			
			
			// 데이터 지우기 - 아무것도 없는 데이터를 세팅한다.
			$("#boardReplyForm").find(".form-group>input, .form-group>textarea").val("");
			
			// data("rno") -> tag 안에 data-rno = "값"
			$("#rno").val($(this).closest(".replyDataRow").data("rno"));
			// 데이터 찾기
			// button을 선택한다.
			// 1. 버튼이 다 보이게 만든다.
			$("#boardReplyForm button").show();
			// 2. 필요 없는 버튼을 안 보이게 한다.
			$("#replyModalWriteBtn, #replyModalUpdateBtn").hide();
			$("#boardReplyModal").modal("show");

		});
		
		// ------- [ modal 화면 안의 처리 버튼 이벤트 처리 ] ---------
		
		$("#replyModalWriteBtn").click(function() {
			
// 			alert("click - replyModalWriterBtn");
			// 액션 - uri에 맞춰서
			// 데이터 전송해서 실행되는 uri를 지정한다.
			$("#boardReplyForm").attr("action","/boardreply/write.do")
			// 데이터를 전송하면서 페이지 이동을 시킨다.
			$("#boardReplyForm").submit();
			
		});
		
		$("#replyModalUpdateBtn").click(function() {
			
// 			alert("click - replyModalUpdateBtn");
			// 데이터 전송해서 실행되는 uri를 지정한다.
			$("#boardReplyForm").attr("action","/boardreply/update.do")
			// 데이터를 전송하면서 페이지 이동을 시킨다.
			$("#boardReplyForm").submit();
		});
		
		$("#replyModalDeleteBtn").click(function() {
			
// 			alert("click - replyModalDeleteBtn");
			// 데이터 전송해서 실행되는 uri를 지정한다.
			$("#boardReplyForm").attr("action","/boardreply/delete.do")
			// 데이터를 전송하면서 페이지 이동을 시킨다.
			$("#boardReplyForm").submit();
			
		});

	});
</script>

