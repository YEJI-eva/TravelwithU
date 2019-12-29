package com.exam.controller;

import java.util.HashMap;
import java.util.Map;

import com.exam.controller.admin.AdminMemberDeleteAction;
import com.exam.controller.admin.AdminWholeBoardFormAction;
import com.exam.controller.admin.AdminWholeMemberFormAction;
import com.exam.controller.board.BoardAction;
import com.exam.controller.board.ContentAction;
import com.exam.controller.board.WelcomeAction;
import com.exam.controller.board.FileContentAction;
import com.exam.controller.board.FileDeleteAction;
import com.exam.controller.board.FileNoticeAction;
import com.exam.controller.board.FileUpdateFormAction;
import com.exam.controller.board.FileWriteAction;
import com.exam.controller.board.FileWriteFormAction;
import com.exam.controller.board.UpdateAction;
import com.exam.controller.board.UpdateFormAction;
import com.exam.controller.board.WriteAction;
import com.exam.controller.board.WriteFormAction;
import com.exam.controller.board.reWriteAction;
import com.exam.controller.board.reWriteFormAction;
import com.exam.controller.member.MainAction;
import com.exam.controller.member.MemberJoinAction;
import com.exam.controller.member.MemberJoinFormAction;
import com.exam.controller.member.MemberLoginAction;
import com.exam.controller.member.MemberLoginFormAction;
import com.exam.controller.member.MemberLogoutAction;

public class ActionFactory {

	private static ActionFactory instance = new ActionFactory();

	public static ActionFactory getInstance() {
		return instance;
	}

	private Map<String, Action> map = new HashMap<String, Action>();

	private ActionFactory() {
		// 생성자에서 미리 컨트롤러(Action 클래스) 등록하기

		map.put("/memberJoinForm", new MemberJoinFormAction());
		map.put("/memberJoin", new MemberJoinAction());
		map.put("/memberLoginForm", new MemberLoginFormAction());
		map.put("/memberLogin",new MemberLoginAction());
		map.put("/memberLogout",new MemberLogoutAction());
		map.put("/main", new MainAction());
		map.put("/welcome", new WelcomeAction());
	


		// map.put("/memberLogout.do", new MemberLogout)
		
		//board명령어 관련 Action 객체 등록
		map.put("/board", new BoardAction());
		map.put("/writeForm",new WriteFormAction());
		map.put("/content",new ContentAction());
		map.put("/update",new UpdateAction());
		map.put("/updateForm",new UpdateFormAction());
		map.put("/write",new WriteAction());
		map.put("/reWriteForm", new reWriteFormAction()); 
		map.put("/reWrite", new reWriteAction()); 
		map.put("/fnotice", new FileNoticeAction()); 
		map.put("/fwriteForm", new FileWriteFormAction());
		map.put("/fwrite", new FileWriteAction());
		map.put("/fcontent", new FileContentAction());
		map.put("/fdelete", new FileDeleteAction());
		map.put("/fupdateForm", new FileUpdateFormAction());
		
		
		
		//admin 관련
		
		map.put("/adminWholeMemberForm", new AdminWholeMemberFormAction());
		map.put("/adminWholeBoardForm", new AdminWholeBoardFormAction());
		map.put("/adminMemberDelete", new AdminMemberDeleteAction());
	}

	public Action getAction(String command) {
		Action action = map.get(command);
		return action;

	} // getAction method

}
