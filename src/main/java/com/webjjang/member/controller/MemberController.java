package com.webjjang.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.webjjang.board.vo.BoardVO;
import com.webjjang.login.vo.LoginVO;
import com.webjjang.main.controller.Init;
import com.webjjang.util.exe.Execute;

import com.webjjang.util.page.PageObject;
import com.webjjang.util.page.ReplyPageObject;

// Board Module 에 맞는 메뉴 선택 , 데이터 수집(기능별), 예외 처리
public class MemberController {

	public String execute(HttpServletRequest request) {
		System.out.println("MemberController.execute()--------------------");

		// 로그인 처리를 session으로 처리한다.
		HttpSession session = request.getSession();

		// login된 정보 중에서 id를 많이 사용한다
		String id = null;
		LoginVO login = (LoginVO) session.getAttribute("login");
		// login이 되어 있는 경우만 id를 꺼내 온다.
		if (login != null)
			id = login.getId();

		// uri
		String uri = request.getRequestURI();

		Object result = null;
		// String 정보
		String jsp = null;

		// 입력 받는 데이터 선언
		Long no = 0L;

		try { // 정상 처리

			// 메뉴 처리 : CRUD DB 처리 - Controller - Service - DAO
			switch (uri) {
			case "/member/loginForm.do":
				System.out.println("a. 로그인 폼");
				jsp = "member/loginForm";
				break;

			case "/member/login.do":
				System.out.println("a-1. 로그인 처리");
				// 데이터 수집(사용자->서버 : form - input - name)
				id = request.getParameter("id");
				String pw = request.getParameter("pw");
				// 변수 - vo 저장하고 Service
				// 위에 있는걸 vo에 다 저장함 데이터는 각각 다르니까 new해서 따로 생성
				LoginVO loginVO = new LoginVO();
				loginVO.setId(id);
				loginVO.setPw(pw);
				// 페이지 정보 받기 & uri에 붙이기
				// [MemberController] - MemberLoginService - MemberDAO.login
				// session에 데이터를 담아서 로그인 처리한다.
				session.setAttribute("login", Execute.execute(Init.get(uri), loginVO));
				// jsp 정보 앞에 redirect가 있으면 redirect(자동페이지이동)를 아니면 jsp로 forward 시킨다.
				// 원래는 main 또는 진행하려는 uri로 이동시킨다.
				// 그러나 완성이 안되어있어서 완성되어진 게시판 리스트로 보낸다.
				jsp = "redirect:/board/list.do";
				// 로그인 완료 처리 메세지
				session.setAttribute("msg", "로그인 되었습니다.");
				break;
				
			case "/member/logout.do":
				System.out.println("b. 로그아웃 처리");
				// session의 로그인 내용 지우기
				session.removeAttribute("login");
				// 로그아웃 완료 처리 메세지
				session.setAttribute("msg", "로그아웃 되었습니다.");
				jsp = "redirect:/board/list.do";
				break;
				
			case "/member/list.do":
				// [BoardController] - (Execute) - BoardListService - BoardDAO.list()
				System.out.println("1.일반게시판 리스트");
				// 페이지 정보, 검색 정보 가져오기
				PageObject pageObject = PageObject.getInstance(request);
				// 페이지 네이션 5 페이지까지 보여주기
				pageObject.setPerGroupPageNum(5L);
				// DB에서 데이터 가져오기 - 가져온 데이터는 List<BoardVO>
				result = Execute.execute(Init.get(uri), pageObject);
				// pageObject 데이터 확인
				System.out.println("BoardController.execute().pageObject = " + pageObject);
				// 가져온 데이터 출력하기
				// 가져온 데이터를 request에 저장 -> jsp까지 전달된다.
				request.setAttribute("list", result);
				// pageObject 담기 -> list.jsp 로 넘긴다.
				request.setAttribute("pageObject", pageObject);
				// WEB-INF/views/ + board.list + .jsp
				// 실질적으로는 디스패처서블릿으로 이동되는거임.
				// 변수가 있냐 없냐에 따라서 샌드리다이렉트로 사용, 데이터 한 개만 전달 될 수 있다.
				jsp = "board/list";

				break;

			case "/member/view.do":
				System.out.println("2.일반게시판 글보기");
				// 1. 조회수 1증가(글보기), 2. 일반게시판 글보기 데이터 가져오기 : 글보기, 글수정
				// 넘어오는 글번호와 조회수 1증가를 수집한다. 데이터는 request에 들어있다.
				String strNo = request.getParameter("no");
				// long타입으로 변환해서 가져옴
				no = Long.parseLong(strNo);
				// 조회수 증가
				String strInc = request.getParameter("inc");
				Long inc = Long.parseLong(strInc);
				// 전달 데이터 - 글번호, 조회수 증가 여부(1:증가, 0:증가 안함) : 배열 또는 Map
				result = Execute.execute(Init.get(uri), new Long[] { no, inc });
				// 가져온 데이터를 JSP로 보내기 위해서 request에 담는다.
				request.setAttribute("vo", result);
				// 댓글 페이지 객체
				// 데이터 전달 - page / perPageNum / no / replyPage / replyPerPageNum
				ReplyPageObject replyPageObject = ReplyPageObject.getInstance(request);
				// 가져온 댓글 데이터 request에 담기
				// 가상의 uri 만들기 (서비스를 가져오기 위함)
				request.setAttribute("replyList", Execute.execute(Init.get("/memberreply/list.do"), replyPageObject));
				// 댓글 페이지 객체 담기
				request.setAttribute("replyPageObject", replyPageObject);

				jsp = "board/view";

				break;

			case "/member/writeForm.do":
				System.out.println("3-1.일반게시판 글등록 폼");
				jsp = "member/writeForm";
				break;

			case "/member/write.do":
				System.out.println("3-2.일반게시판 글등록 처리");
				// 데이터 수집(사용자->서버 : form - input - name)
				String title = request.getParameter("title");
				String content = request.getParameter("content");
				String writer = request.getParameter("writer");
				pw = request.getParameter("pw");
				// 변수 - vo 저장하고 Service
				// 위에 있는걸 vo에 다 저장함 데이터는 각각 다르니까 new해서 따로 생성
				BoardVO vo = new BoardVO();
				vo.setTitle(title);
				vo.setContent(content);
				vo.setWriter(writer);
				vo.setPw(pw);
				// 페이지 정보 받기 & uri에 붙이기
				// [MemberController] - MemberCheckIdService - MemberDAO.write(vo)
				Execute.execute(Init.get(uri), vo);
				// jsp 정보 앞에 redirect가 있으면 redirect(자동페이지이동)를 아니면 jsp로 forward 시킨다.
				jsp = "redirect:list.do?perPageNum=" + request.getParameter("perPageNum");
				session.setAttribute("msg", "글이 등록 되었습니다.");
				break;
			case "/member/checkId.do":
				System.out.println("3-3. 아이디 중복 체크 처리");
				// 사용자가 서버한테 보낸 아이디
				id = request.getParameter("id");
				// [MemberController] - MemberCheckIdService - MemberDAO.checkId(id)
				// 서버에서 가져온 아이디
				// 위에 id 변수가 오버라이딩 되는것.
				id = (String) Execute.execute(Init.get(uri), id);
				// request - null이 나와도 담고 id 도 담는.
				request.setAttribute("id", id);
				// jsp 정보 
				jsp = "member/checkId";
				session.setAttribute("msg", "글이 등록 되었습니다.");
				break;
			case "/member/updateForm.do":
				System.out.println("4-1.일반게시판 글수정 폼");

				// 사용자 -> 서버 : 글번호 수집
				no = Long.parseLong(request.getParameter("no"));
				// no에 맞는 데이터 가져오기 DB(execute service dao) BoardViewService
				// 전달 데이터 - 글번호, 조회수 증가 여부(1:증가, 0:증가 안함) : 배열 또는 Map
				result = Execute.execute(Init.get("/member/view.do"), new Long[] { no, 0L });
				// 가져온 데이터를 JSP로 보내기 위해서 request에 담는다.
				request.setAttribute("vo", result);
				// jsp 정보
				jsp = "board/updateForm";
				break;
			case "/member/update.do":
				System.out.println("4-2.일반게시판 글수정");
				// 수정할 글번호를 받는다. - 데이터 수집
				// 데이터 수집(사용자->서버 : form - input - name)
				no = Long.parseLong(request.getParameter("no"));
				title = request.getParameter("title");
				content = request.getParameter("content");
				writer = request.getParameter("writer");
				pw = request.getParameter("pw");
				// 변수 - vo 저장하고 Service
				// 위에 있는걸 vo에 다 저장함 데이터는 각각 다르니까 new해서 따로 생성
				vo = new BoardVO();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setContent(content);
				vo.setWriter(writer);
				vo.setPw(pw);

				Execute.execute(Init.get(uri), vo);
				// 페이지 정보 받기 & uri에 붙이기
				pageObject = PageObject.getInstance(request);
				// 글보기로 자동 이동 시키기 : 수정할때는 조회수 증가 안 시킨다.
				// jsp 정보를 작성해서 넘긴다.
				jsp = "redirect:view.do?no=" + no + "&inc=0" + "&" + pageObject.getPageQuery();
				session.setAttribute("msg", "글이 수정 되었습니다.");
				break;
			case "/member/delete.do":
				System.out.println("5.일반게시판 글삭제");
				// 데이터 수집 - DB에서 실행에 필요한 데이터 - 글번호, 비밀번호 - BoardVO

				no = Long.parseLong(request.getParameter("no"));
				pw = request.getParameter("pw");

				BoardVO deleteVO = new BoardVO();
				deleteVO.setNo(no);
				deleteVO.setPw(pw);

				// DB 처리
				Execute.execute(Init.get(uri), deleteVO);
				System.out.println();
				System.out.println("***************************");
				System.out.println("**  " + deleteVO.getNo() + "글이 삭제되었습니다.  **");
				System.out.println("***************************");

				jsp = "redirect:list.do?perPageNum=" + request.getParameter("perPageNum");
				session.setAttribute("msg", "글이 삭제 되었습니다.");
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
