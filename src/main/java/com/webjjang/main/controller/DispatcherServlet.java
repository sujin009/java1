package com.webjjang.main.controller;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webjjang.ajax.controller.AjaxController;
import com.webjjang.board.controller.BoardController;
import com.webjjang.boardreply.controller.BoardReplyController;
import com.webjjang.image.controller.ImageController;
import com.webjjang.member.controller.MemberController;

/**
 * Servlet implementation class DispatcherServlet
 */
// Servlet을 상속 - 기능 : URL 연결(urlPatterns) - 서버에서 동작하는 프로그램  - 1번만 생성 (싱글톤 프로그램) 톰캣이 관리해준다.
// 웹서버와 연결하기 위해서 servlet으로 등록이 되어 있어야 한다.
// 1. @WebServlet - 어노테이션 : 프로그램 수정 가능하면 사용 / 2) web.xml 등록해서 사용한다 - 프로그램 수정 불가능
//@WebServlet(urlPatterns = "*.do", loadOnStartup = 1)
// DispatcherServlet 클래스 상속 받음(extends) HttpServlet
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Controller 선언과 생성 - 1번만 된다.
	private BoardController boardController = new BoardController();
	private BoardReplyController boardReplyController = new BoardReplyController();
	private MemberController memberController = new MemberController();
	private ImageController imageController = new ImageController();
	private AjaxController  ajaxController = new AjaxController();
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	// 초기화 블록 init()
	public void init(ServletConfig config) throws ServletException {
		// 드라이버 확인 | 객체 생성 처리 - Class.forName(class명)
		// 서버가 실행될 때 먼저 실행되어야만 한다.
		System.out.println("DispatcherServlet.init()-------- 초기화 진행 --------");
		try {
			// -- 객체 생성과 초기화 + 조립
			Class.forName("com.webjjang.main.controller.Init");
			// -- 오라클 드라이버 확인 + 로딩 = DB 클래스 System.exit(1); 은 주석 
			// 메인에서 확인하는 용도로 사용
			Class.forName("com.webjjang.util.db.DB");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 메뉴 출력 후 선택(uri)하고 처리
		// uri - /board 가 앞에 붙어 있으면 boardController로 가게 만든다.
		System.out.println("DispatcherServlet.service()----------------");
		
		String uri = request.getRequestURI();
		System.out.println("uri = " + uri);
		
		// uri = /module/기능 -> /board/list.do
		int pos = uri.indexOf("/",1);
		System.out.println("pos = " + pos);
		// error 
		if(pos == -1) {
			request.getRequestDispatcher("/WEB-INF/views/error/404.jsp").forward(request, response);
			return;
		}
		
		String module = uri.substring(0,pos);
		System.out.println("module = " + module);
		// request에 module을 담아서 어떤 메뉴가 선택되었는지 처리 : defualt_decorator.jsp
		request.setAttribute("module", module);
		
		String jsp = null;
		switch (module) {
		case "/member":
			System.out.println("회원 관리");
			jsp = memberController.execute(request);
			break;
		case "/board":
			System.out.println("게시판");
			jsp = boardController.execute(request);
			break;
		case "/boardreply":
			System.out.println("게시판 댓글");
			jsp = boardReplyController.execute(request);
			break;
		case "/image":
			System.out.println("이미지 게시판");
			jsp = imageController.execute(request);
			break;
		case "/ajax":
			System.out.println("Ajax 처리");
			jsp = ajaxController.execute(request);
			break;

		default:
			request.getRequestDispatcher("/WEB-INF/views/error/404.jsp").forward(request, response);
			return;
		} 
		// jsp 정보앞에 "redirect:" 이 붙어 있으면 자동으로 이동되게 redirect 시킨다. // request.sendRedirect()
		// jsp 정보앞에 "redirect:" 이 붙어 있지 않으면 JSP로 forward(전진) 시킨다. 
		if(jsp.indexOf("redirect:") == 0) { // 리다이렉트가 있으면 0 
			// : redirect 길이 만큼 뒤에 있는 정보를 url로 쓴다. 그 길이 만큼 sendRedirect 시킨다.
			response.sendRedirect(jsp.substring("redirect:".length()));
			// redirect:list.do -> uri로 사용하기 위해 redirect: 는 잘라 버린다.
		}else{
			 // 리다이렉트가 붙어 있지 않는 경우
			// jsp로 forward 한다. 
			request.getRequestDispatcher("/WEB-INF/views/" + jsp + ".jsp" ).forward(request, response);
//			request.getSession().removeAttribute("msg");
		}
		
	}

}
