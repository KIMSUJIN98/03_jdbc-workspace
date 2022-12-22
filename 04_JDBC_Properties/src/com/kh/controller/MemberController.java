package com.kh.controller;

import java.util.ArrayList;

import com.kh.model.service.MemberService;
import com.kh.model.vo.Member;
import com.kh.view.MemberMenu;

/*
 * Controller : View를 통해서 사용자가 요청한 기능에 대해서 처리하는 담당
 * 				해당 메소드로 전달된 데이터 [가공처리 한 후] DAO로 전달하면서 호출
 * 				DAO로 부터 반환받은 결과에 따라 성공인지 실패인지 판단 후 응답화면 결정 (view 메소드 호출)
 * 
 */
public class MemberController {
	
	/**
	 * 사용자의 회원 추가 요청을 처리해주는 메소드
	 * @param userId ~ hobby : 사용자가 입력했던 정보들이 담겨있는 매개변수
	 */
	public void insertMember(String userId, String userPwd, String userName, String gender, String age, String email, String phone, String address, String hobby) {
		Member m = new Member(userId, userPwd, userName, gender, Integer.parseInt(age), email, phone, address, hobby);
		
		int result = new MemberService().insertMember(m);
		
		if(result > 0) {
			new MemberMenu().displaySuccess("회원 추가 성공!");
		}else {
			new MemberMenu().displayFail("회원 추가 실패!ㅠㅠ");
		}
		
	}
	
	/**
	 * 사용자의 회원 전체 조회요청을 처리해주는 메소드
	 */
	public void selectList() {
		ArrayList<Member> list = new MemberService().selectList();
		if(list.isEmpty()) {
			new MemberMenu().displayNoData("조회된 회원이 없습니다.");
		}else {
			new MemberMenu().displayMemberList(list);
		}
		
	}
	
	/**
	 * 사용자의 이름으로 정보 조회요청을 처리해주는 메소드
	 * @param userName
	 */
	public void searchMember(String userName) {
		
	}
	
	/**
	 * 사용자의 아이디로 정보 조회요청을 처리해주는 메소드
	 * @param userId  : 조회하고자 하는 회원 아이디
	 */
	public void selectByUserId(String userId) {
		Member m = new MemberService().selectByUserId(userId);
		if(m == null) {
			new MemberMenu().displayNoData(userId + "로 조회된 회원이 없습니다.");
		}else {
			new MemberMenu().displayMember(m);
		}
	}

	/**
	 * 사용자의 이름으로 키워드 검색 요청시 처리해주는 메소드
	 * @param keyword
	 */
	public void selectByUserName(String keyword) {
		ArrayList<Member> list = new MemberService().selectByUserName(keyword);
		if(list.isEmpty()) {
			new MemberMenu().displayNoData(keyword + "로 조회된 회원이 없습니다.");
		}else {
			new MemberMenu().displayMemberList(list);
		}
	}
	
	/**
	 * 정보 변경 요청 처리해주는 메소드
	 * @param userId  : 변경하고자 하는 회원 아이디
	 * @param userPwd : 변경할 비밀번호
	 * @param email	  : 변경할 이메일
	 * @param phone	  : 변경할 전화번호
	 * @param address : 변경할 주소
	 */
	public void updateMember(String userId, String userPwd, String email, String phone, String address) {
		Member m = new Member();
		m.setUserId(userId);
		m.setUserPwd(userPwd);
		m.setEmail(email);
		m.setPhone(phone);
		m.setAddress(address);
		
		int result = new MemberService().updateMember(m);
		if(result > 0) {
			new MemberMenu().displaySuccess("회원 정보 변경 성공!");
		}else {
			new MemberMenu().displayFail("회원 정보 변경 실패 ㅠㅠㅠ");
		}
	}
	
	/**
	 * 사용자의 회원 정보 삭제요청을 처리해주는 메소드
	 * @param userId  : 삭제하고자 하는 회원 아이디
	 */
	public void deleteMember(String userId) {
		int result = new MemberService().deleteMember(userId);
		if(result > 0) {
			new MemberMenu().displaySuccess("회원 정보 삭제 성공!");
		}else {
			new MemberMenu().displayFail("회원 정보 삭제 실패 ㅠㅠㅠ");
		}
	}
	
	public void loginMember(String userId, String userPwd) {
		String userName = new MemberService().loginMember(userId, userPwd);
		
		if(userName == null) {
			new MemberMenu().displayFail("로그인 실패!");
		}else {
			new MemberMenu().displaySuccess("로그인 성공! " + userName + "님 환영합니다!");
		}
	}
	
}
