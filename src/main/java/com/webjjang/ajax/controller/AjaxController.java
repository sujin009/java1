package com.webjjang.ajax.controller;

import javax.servlet.http.HttpServletRequest;
import com.webjjang.main.controller.Init;
import com.webjjang.util.exe.Execute;


// Board Module 에 맞는 메뉴 선택 , 데이터 수집(기능별), 예외 처리
public class AjaxController {

	public String execute(HttpServletRequest request) {
		System.out.println("AjaxController.execute()--------------------");

		// login된 정보 중에서 id를 많이 사용한다
		String id = null;
		// uri
		String uri = request.getRequestURI();
		// String 정보
		String jsp = null;

		try { // 정상 처리

			// 메뉴 처리 : CRUD DB 처리 - Controller - Service - DAO
			switch (uri) {
			case "/ajax/checkId.do":
				System.out.println("1. 아이디 중복 체크 처리");
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
				break;
			default:
			jsp = "error/noModule_404";
			break;
			} // end of switch
		} catch (Exception e) {
			request.setAttribute("e", e);
			jsp = "error/noModule_500";
		} // end of try~catch
		return jsp;

	} // end of execute()

} // end of class
