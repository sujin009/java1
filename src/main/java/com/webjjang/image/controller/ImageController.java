package com.webjjang.image.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.webjjang.board.vo.BoardVO;
import com.webjjang.image.vo.ImageVO;
import com.webjjang.login.vo.LoginVO;
import com.webjjang.main.controller.Init;
import com.webjjang.util.exe.Execute;

import com.webjjang.util.page.PageObject;

// Board Module 에 맞는 메뉴 선택 , 데이터 수집(기능별), 예외 처리
public class ImageController {

	public String execute(HttpServletRequest request) {
		System.out.println("imageController.execute()--------------------");

		HttpSession session = request.getSession();
		// uri
		String uri = request.getRequestURI();

		Object result = null;
		// String 정보
		String jsp = null;

		// 로그인 정보 꺼내기
		LoginVO loginVO = (LoginVO) session.getAttribute("login");
		String id = null;
		// 로그인 한 경우에만 꺼내기
		if(loginVO != null) id = loginVO.getId();
		// 파일 업로드 설정 --------------------------------------------
		// 파일의 상대적인 저장 위치
		String savePath = "/upload/image";
		// 파일 시스템에서는 절대 저장 위치가 필요하다. - 파일 업로드시 필요
		String realSavePath = request.getServletContext().getRealPath(savePath);
		// 업로드 파일 용량 제한
		int sizeLimit = 100 * 1024 * 1024;
		// 입력 받는 데이터 선언
		Long no = 0L;

		try { // 정상 처리

			// 메뉴 처리 : CRUD DB 처리 - Controller - Service - DAO
			switch (uri) {
			case "/image/list.do":
				// [BoardController] - (Execute) - BoardListService - BoardDAO.list()
				System.out.println("1.이미지 게시판 리스트");
				// 페이지 정보, 검색 정보 가져오기
				PageObject pageObject = PageObject.getInstance(request);
				// 이미지 게시판의 한 페이지 이미지의 개수의 기본값을 6으로 처리하기
				// 중복 처리 - 앞의 데이터에 덮어쓰기가 된다. // 중복 처리를 안 하면 뒤에 저장되어있는것으로 써진다
				String strPerPageNum = request.getParameter("perPageNum");
				// 앞에서 getInstance가 안 들어온 경우에 6으로 처리하자
				if(strPerPageNum == null) pageObject.setPerPageNum(6);
				// 페이지 네이션 5 페이지까지 보여주기
				pageObject.setPerGroupPageNum(5L);
				// DB에서 데이터 가져오기 - 가져온 데이터는 List<BoardVO>
				result = Execute.execute(Init.get(uri), pageObject);
				// pageObject 데이터 확인
				System.out.println("ImageController.execute().pageObject = " + pageObject);
				// 가져온 데이터 출력하기
				// 가져온 데이터를 request에 저장 -> jsp까지 전달된다.
				request.setAttribute("list", result);
				// pageObject 담기 -> list.jsp 로 넘긴다.
				request.setAttribute("pageObject", pageObject);
				// WEB-INF/views/ + board.list + .jsp
				// 실질적으로는 디스패처서블릿으로 이동되는거임.
				// 변수가 있냐 없냐에 따라서 샌드리다이렉트로 사용, 데이터 한 개만 전달 될 수 있다.
				jsp = "image/list";

				break;

			case "/image/view.do":
				System.out.println("2.이미지 보기");
				String strNo = request.getParameter("no");
				// long타입으로 변환해서 가져옴
				no = Long.parseLong(strNo);
				// 조회수 증가
				// 전달 데이터 - 글번호, 조회수 증가 여부(1:증가, 0:증가 안함) : 배열 또는 Map
				result = Execute.execute(Init.get(uri), no);
				// 가져온 데이터를 JSP로 보내기 위해서 request에 담는다.
				request.setAttribute("vo", result);
				jsp = "image/view";

				break;

			case "/image/writeForm.do":
				System.out.println("3-1.이미지 게시판 등록 폼");
				jsp = "image/writeForm";
				break;

			case "/image/write.do":
				System.out.println("3-2.이미지 게시판 등록 처리");
				// 이미지 업로드 처리
				// new MultipartRequest(request, 실제 저장 위치(realPath), 사이즈제한, encoding, 중복처리객체-파일명 뒤에 cnt 붙임(숫자))
				// 단점 : file 객체 업로드 시 input의 name이 같으면 1개만 처리 가능하다. get.fileName에 대한 이미지 파일은 배열로 받을 수 없다,
				// name을 다르게 해서 업로드하기. ex) file1 , file2...
				// multi가 아니라 get으로 받아오면 null이 나온다. 
				MultipartRequest multi = new MultipartRequest(request, realSavePath, sizeLimit, "utf-8", new DefaultFileRenamePolicy());
				// 일반 데이터 수집(사용자->서버 : form - input - name) : multi에서 
				String title = multi.getParameter("title");
				String content = multi.getParameter("content");
				String fileName = multi.getFilesystemName("imageFile");
				// id 는 session에서 받아와야 한다. <수정, 삭제할때도 받아와야해서 로그인이 되어있을때 꺼내 사용하자>
				// 변수 - vo 저장하고 Service
				// 위에 있는걸 vo에 다 저장함 데이터는 각각 다르니까 new해서 따로 생성
				ImageVO vo = new ImageVO();
				vo.setTitle(title);
				vo.setContent(content);
				vo.setId(id);
				// fileName은 위치 정보 + 파일명 (절대적인 위치가 아님 상대적인 위치다.)
				vo.setFileName(savePath + "/" + fileName);
				// 페이지 정보 받기 & uri에 붙이기
				// [ImageController] - ImageWriteService - ImageDAO.write(vo)
				Execute.execute(Init.get(uri), vo);
				// jsp 정보 앞에 redirect가 있으면 redirect(자동페이지이동)를 아니면 jsp로 forward 시킨다.
				jsp = "redirect:list.do?perPageNum=" + multi.getParameter("perPageNum");
				// 메세지
				session.setAttribute("msg", "이미지가 등록 되었습니다.");
				break;
			case "/image/updateForm.do":
				System.out.println("4-1.이미지 게시판 수정 폼");

				// 사용자 -> 서버 : 글번호 수집
				no = Long.parseLong(request.getParameter("no"));
				// no에 맞는 데이터 가져오기 DB(execute service dao) BoardViewService
				// 전달 데이터 - 글번호, 조회수 증가 여부(1:증가, 0:증가 안함) : 배열 또는 Map
				result = Execute.execute(Init.get("/image/view.do"),no);
				// 가져온 데이터를 JSP로 보내기 위해서 request에 담는다.
				request.setAttribute("vo", result);
				// jsp 정보
				jsp = "image/updateForm";
				break;
			case "/image/update.do":
				System.out.println("4-2.이미지 게시판 수정");
				// 수정할 글번호를 받는다. - 데이터 수집
				// 데이터 수집(사용자->서버 : form - input - name)
				no = Long.parseLong(request.getParameter("no"));
				title = request.getParameter("title");
				content = request.getParameter("content");
				// 변수 - vo 저장하고 Service
				// 위에 있는걸 vo에 다 저장함 데이터는 각각 다르니까 new해서 따로 생성
				vo = new ImageVO();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setContent(content);
				vo.setId(id); // session의 로그인 정보에서 꺼낸다. 상단 코드 참조

				Execute.execute(Init.get(uri), vo);
				// 페이지 정보 받기 & uri에 붙이기
				pageObject = PageObject.getInstance(request);
				// 글보기로 자동 이동 시키기 : 수정할때는 조회수 증가 안 시킨다.
				// jsp 정보를 작성해서 넘긴다.
				jsp = "redirect:view.do?no=" + no + "&inc=0" + "&" + pageObject.getPageQuery();
				session.setAttribute("msg", "게시글이 수정 되었습니다.");
				break;
				
			case "/image/delete.do":
				System.out.println("5.이미지 게시판 글삭제");
				// 데이터 수집 - DB에서 실행에 필요한 데이터 - 글번호, 아이디(session에있다.) - ImageVO
				no = Long.parseLong(request.getParameter("no"));
				
				ImageVO deleteVO = new ImageVO();
				deleteVO.setNo(no);
				deleteVO.setId(id);

				// DB 처리
				Execute.execute(Init.get(uri), deleteVO);
				System.out.println();
				System.out.println("***************************");
				System.out.println("**  " + deleteVO.getNo() + "글이 삭제되었습니다.  **");
				System.out.println("***************************");
				// 파일 삭제
				// 삭제할 파일 이름 : DB 처리가 되면 삭제
				String deleteFileName = request.getParameter("deleteFileName");
				File deleteFile = new File(request.getServletContext().getRealPath(deleteFileName));
				if(deleteFile.exists()) deleteFile.delete();
				jsp = "redirect:list.do?perPageNum=" + request.getParameter("perPageNum");
				session.setAttribute("msg", "게시글이 삭제 되었습니다.");
				break;
				
			case "/image/changeImage.do":
				System.out.println("6.이미지 바꾸기 처리");
				
				// 파일 업로드 cos 라이브러리 - MultipartRequest
				// new MultipartRequest(request, 실제 저장 위치(realPath), 사이즈제한, encoding, 중복처리객체-파일명 뒤에 cnt 붙임(숫자))
				// 단점 : file 객체 업로드 시 input의 name이 같으면 1개만 처리 가능하다. get.fileName에 대한 이미지 파일은 배열로 받을 수 없다,
				// name을 다르게 해서 업로드하기. ex) file1 , file2...
				// multi가 아니라 get으로 받아오면 null이 나온다. 
				multi = new MultipartRequest(request, realSavePath, sizeLimit, "utf-8", new DefaultFileRenamePolicy());
				
				// 수정할 글번호를 받는다. - 데이터 수집
				// 데이터 수집(사용자->서버 : form - input - name)
				no = Long.parseLong(multi.getParameter("no"));
				fileName = multi.getFilesystemName("imageFile");
				// 삭제하기
				deleteFileName = multi.getParameter("deleteFileName");
				// 변수 - vo 저장하고 Service로 보낸다 : DB에 처리할 데이터만. SQL ?에 맞춰서 사용.
				// 위에 있는걸 vo에 다 저장함 데이터는 각각 다르니까 new해서 따로 생성
				vo = new ImageVO();
				vo.setNo(no);
				vo.setFileName(savePath + "/" +fileName);

				Execute.execute(Init.get(uri), vo);
				
				// 지난 이미지 파일이 존재하면 지운다. <realPath 사용> boolean 타입
				deleteFile = new File(request.getServletContext().getRealPath(deleteFileName));
				// 존재 할 때만 지운다. <exists 사용>
				if(deleteFile.exists())deleteFile.delete();
				
				// 처리 결과 메세지
				session.setAttribute("msg", "이미지가 변경 되었습니다.");
				
				// 페이지 정보 받기 & uri에 붙이기
				pageObject = PageObject.getInstance(request);
				// 글보기로 자동 이동 시키기 : 수정할때는 조회수 증가 안 시킨다.
				// jsp 정보를 작성해서 넘긴다.
				jsp = "redirect:view.do?no=" + no + "&" + pageObject.getPageQuery();
				break;
				
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
