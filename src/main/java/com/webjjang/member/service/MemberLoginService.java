package com.webjjang.member.service;

import com.webjjang.login.vo.LoginVO;
import com.webjjang.main.dao.DAO;
import com.webjjang.main.service.Service;
import com.webjjang.member.dao.MemberDAO;

public class MemberLoginService implements Service {
	
	private MemberDAO dao;
	
	//dao setter
	public void setDAO(DAO dao) {
		this.dao = (MemberDAO) dao;
	}
	@Override
	public LoginVO service(Object obj) throws Exception {
		// DB 처리는 DAO에서 처리 - BoardDAO.list()
		// BoardController - (Execute) - [BoardListService] - BoardDAO.view()
		return dao.login((LoginVO) obj);
	}

}