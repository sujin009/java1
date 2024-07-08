package com.webjjang.boardreply.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.webjjang.boardreply.vo.BoardReplyVO;
import com.webjjang.main.controller.Init;
import com.webjjang.util.exe.Execute;

import com.webjjang.util.page.ReplyPageObject;

// Board Module 에 맞는 메뉴 선택 , 데이터 수집(기능별), 예외 처리
public class BoardReplyController {
	// -------- 댓글 ------ [ 등록 , 수정, 삭제 처리 ]
	public String execute(HttpServletRequest request) {
		System.out.println("BoardController.execute()--------------------");
		
		// session을 request에서 부터 꺼내온다.
		HttpSession session = request.getSession();
		// uri
		String uri = request.getRequestURI();
		
		// String 정보
		String jsp = null;

		try { // 정상 처리
			
			// 댓글 페이지 정보 받기 & uri에 붙이기
			ReplyPageObject pageObject = ReplyPageObject.getInstance(request);
			
			// 메뉴 처리 : CRUD DB 처리 - Controller - Service - DAO
			switch (uri) {
			// 1. 댓글 등록
			case "/boardreply/write.do":
				System.out.println("1.일반게시판 댓글 등록 처리");
				// 데이터 수집(사용자->서버 : form - input - name)
				String content = request.getParameter("content");
				String writer = request.getParameter("writer");
				String pw = request.getParameter("pw");
				// 변수 - vo 저장하고 Service
				// 위에 있는걸 vo에 다 저장함 데이터는 각각 다르니까 new해서 따로 생성
				BoardReplyVO vo = new BoardReplyVO();
				// no 수집은 pageObject에 있기 때문에 가져오기만 하면 된다.(vo에 세팅해놨음)
				vo.setNo(pageObject.getNo());
				vo.setContent(content);
				vo.setWriter(writer);
				vo.setPw(pw);
				// 페이지 정보 받기 & uri에 붙이기
				// [BoardController] - BoardWriteService - BoardDAO.write(vo)
				Execute.execute(Init.get(uri), vo);
				// jsp 정보 앞에 redirect가 있으면 redirect(자동페이지이동)를 아니면 jsp로 forward 시킨다.
				// 
				jsp = "redirect:/board/view.do?no="+pageObject.getNo()+"&inc=0&"
						// 일반 게시판의 페이지 정보 & 검색 정보 붙이기
						+"&"+pageObject.getPageObject().getPageQuery();
				// 댓글 등록시 나오는 메세지
				session.setAttribute("msg", "댓글 등록이 완료되었습니다.");
				break;
			case "/boardreply/update.do":
				System.out.println("2.일반게시판 댓글 수정");
				// 수정할 글번호를 받는다. - 데이터 수집
				// 데이터 수집(사용자->서버 : form - input - name)
				Long rno = Long.parseLong(request.getParameter("rno"));
				content = request.getParameter("content");
				writer = request.getParameter("writer");
				pw = request.getParameter("pw");
				// 변수 - vo 저장하고 Service
				// 위에 있는걸 vo에 다 저장함 데이터는 각각 다르니까 new해서 따로 생성
				vo = new BoardReplyVO();
				vo.setRno(rno);
				vo.setContent(content);
				vo.setWriter(writer);
				vo.setPw(pw);

				Execute.execute(Init.get(uri), vo);
			
				// 글보기로 자동 이동 시키기 : 수정할때는 조회수 증가 안 시킨다.
				// jsp 정보를 작성해서 넘긴다.
				jsp = "redirect:/board/view.do?no=" + pageObject.getNo() + "&inc=0" + "&"+pageObject.getPageQuery();
				session.setAttribute("msg", "댓글 수정이 완료되었습니다.");
				break;
			case "/boardreply/delete.do":
				System.out.println("3.일반게시판 댓글 삭제");
				// 데이터 수집 - DB에서 실행에 필요한 데이터 - 글번호, 비밀번호 - BoardVO

				rno = Long.parseLong(request.getParameter("rno"));
				pw = request.getParameter("pw");
				System.out.println(pw);
				BoardReplyVO deleteVO = new BoardReplyVO();
				deleteVO.setRno(rno);
				deleteVO.setPw(pw);
				System.out.println(deleteVO);
				// DB 처리
				Execute.execute(Init.get(uri), deleteVO);
				System.out.println();
				System.out.println("***************************");
				System.out.println("**  " + deleteVO.getRno() + "글이 삭제되었습니다.  **");
				System.out.println("***************************");

				jsp = "redirect:/board/view.do?no=" + pageObject.getNo() + "&inc=0" + "&"+pageObject.getPageQuery() 
				// 댓글 페이지
				+ "&"+pageObject.getPageQuery();
				session.setAttribute("msg", "댓글이 삭제 되었습니다.");
				break;
			case "0":

			default:
				System.out.println("####################################");
				;
				System.out.println("## 잘못된 메뉴를 선택하셨습니다.          ##");
				;
				System.out.println("## [0~5, 0] 중에서 입력하셔야 합니다.    ##");
				;
				System.out.println("####################################");
				;
				break;
			} // end of switch
		} catch (Exception e) {
			// TODO: handle exception
			// e.printStackTrace();
			System.out.println();
			System.out.println("$%@$%@$%@$%@$%@$%@$%@$%@$%@$%@$%@$%@$%@$%@$%@");
			System.out.println("$%@ << 오류 출력 >>                         $%@");
			System.out.println("$%@$%@$%@$%@$%@$%@$%@$%@$%@$%@$%@$%@$%@$%@$%@");
			System.out.println("$%@ 타입 : " + e.getClass().getSimpleName());
			System.out.println("$%@ 내용 : " + e.getMessage());
			System.out.println("$%@ 조치 : 데이터를 확인 후 다시 실행해 보세요.");
			System.out.println("$%@     : 계속 오류가 나면 전산담당자에게 연락하세요.");
			System.out.println("$%@$%@$%@$%@$%@$%@$%@$%@$%@$%@$%@$%@$%@$%@$%@");
		} // end of try~catch
		return jsp;

	} // end of execute()

} // end of class
