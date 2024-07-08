package com.webjjang.main.controller;

import java.util.HashMap;
import java.util.Map;

import com.webjjang.board.dao.BoardDAO;
import com.webjjang.board.service.BoardDeleteService;
import com.webjjang.board.service.BoardListService;
import com.webjjang.board.service.BoardUpdateService;
import com.webjjang.board.service.BoardViewService;
import com.webjjang.board.service.BoardWriteService;
import com.webjjang.boardreply.dao.BoardReplyDAO;
import com.webjjang.boardreply.service.BoardReplyDeleteService;
import com.webjjang.boardreply.service.BoardReplyListService;
import com.webjjang.boardreply.service.BoardReplyUpdateService;
import com.webjjang.boardreply.service.BoardReplyWriteService;
import com.webjjang.image.dao.ImageDAO;
import com.webjjang.image.service.ImageChangeService;
import com.webjjang.image.service.ImageDeleteService;
import com.webjjang.image.service.ImageListService;
import com.webjjang.image.service.ImageUpdateService;
import com.webjjang.image.service.ImageViewService;
import com.webjjang.image.service.ImageWriteService;
import com.webjjang.main.dao.DAO;
import com.webjjang.main.service.Service;
import com.webjjang.member.dao.MemberDAO;
import com.webjjang.member.service.MemberCheckIdService;
import com.webjjang.member.service.MemberLoginService;

public class Init{
	
	// service 생성해서 저장하는 객체 - <String URI>, <service>
	// 계속 new 하면 안되고 생성한것에 주소만 전달해서 넣으면 된다. 서비스쪽에 갖다가 넣어서 해야한다.
	private static Map<String, Service> serviceMap = new HashMap<>();
	// DAO 생성해서 저장하는 객체 - <className, dao>
	private static Map<String, DAO> daoMap = new HashMap<>();
	// static 변수에 데이터를 초기화 시키는 1번만 실행되는 블록
	static {	// 위에 클래스가 불려지면 자동 실행
		
		// ------- [ 일반 게시판 객체 생성과 조립 ] --------------
		// DAO 생성
		daoMap.put("boardDAO", new BoardDAO());
		// service 생성
		serviceMap.put("/board/list.do", new BoardListService());
		serviceMap.put("/board/view.do", new BoardViewService());
		serviceMap.put("/board/write.do", new BoardWriteService());
		serviceMap.put("/board/update.do", new BoardUpdateService());
		serviceMap.put("/board/delete.do", new BoardDeleteService());
		// 조립 dao - service
		serviceMap.get("/board/list.do").setDAO(daoMap.get("boardDAO"));
		serviceMap.get("/board/view.do").setDAO(daoMap.get("boardDAO"));
		serviceMap.get("/board/write.do").setDAO(daoMap.get("boardDAO"));
		serviceMap.get("/board/update.do").setDAO(daoMap.get("boardDAO"));
		serviceMap.get("/board/delete.do").setDAO(daoMap.get("boardDAO"));
		
		// ------- [ 일반 게시판 댓글 생성과 조립 ] --------------
		// DAO 생성
		daoMap.put("replyBoardDAO", new BoardReplyDAO());
		// service 생성
		serviceMap.put("/boardreply/list.do", new BoardReplyListService());
		serviceMap.put("/boardreply/write.do", new BoardReplyWriteService());
		serviceMap.put("/boardreply/update.do", new BoardReplyUpdateService());
		serviceMap.put("/boardreply/delete.do", new BoardReplyDeleteService());
		// 조립 dao - service
		serviceMap.get("/boardreply/list.do").setDAO(daoMap.get("replyBoardDAO"));
		serviceMap.get("/boardreply/write.do").setDAO(daoMap.get("replyBoardDAO"));
		serviceMap.get("/boardreply/update.do").setDAO(daoMap.get("replyBoardDAO"));
		serviceMap.get("/boardreply/delete.do").setDAO(daoMap.get("replyBoardDAO"));
		
		// ------- [ 회원 관리 객체 생성과 조립 ] --------------
		// DAO 생성
		daoMap.put("memberDAO", new MemberDAO());		
		// service 생성
		serviceMap.put("/member/login.do", new MemberLoginService());
		serviceMap.put("/ajax/checkId.do", new MemberCheckIdService());
		// 조립 dao - service
		serviceMap.get("/member/login.do").setDAO(daoMap.get("memberDAO"));
		serviceMap.get("/ajax/checkId.do").setDAO(daoMap.get("memberDAO"));
		
		// ------- [ 이미지 게시판 객체 생성과 조립 ] --------------
		// DAO 생성
		daoMap.put("imageDAO", new ImageDAO());		
//		// service 생성
		serviceMap.put("/image/list.do", new ImageListService());
		serviceMap.put("/image/view.do", new ImageViewService());
		serviceMap.put("/image/write.do", new ImageWriteService());
		serviceMap.put("/image/changeImage.do", new ImageChangeService());
		serviceMap.put("/image/update.do", new ImageUpdateService());
		serviceMap.put("/image/delete.do", new ImageDeleteService());
//		// 조립 dao - service
		serviceMap.get("/image/list.do").setDAO(daoMap.get("imageDAO"));
		serviceMap.get("/image/view.do").setDAO(daoMap.get("imageDAO"));
		serviceMap.get("/image/write.do").setDAO(daoMap.get("imageDAO"));
		serviceMap.get("/image/changeImage.do").setDAO(daoMap.get("imageDAO"));
		serviceMap.get("/image/update.do").setDAO(daoMap.get("imageDAO"));
		serviceMap.get("/image/delete.do").setDAO(daoMap.get("imageDAO"));
		
		System.out.println("Init.static 초기화 블록 ------ 객체 생성과 로딩--------");
		
	}
	 public static Service get(String uri) {
	      return serviceMap.get(uri);
	   }

	
}
