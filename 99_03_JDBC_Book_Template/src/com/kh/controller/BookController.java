package com.kh.controller;

import java.util.ArrayList;

import com.kh.model.vo.Book;
import com.kh.service.BookService;
import com.kh.view.BookMenu;

/**
 * BookController.java
 * @author ksj
 * @since 2022.12.21
 * @version 1.0
 *
 */
public class BookController {
	
	/**
	 * 사용자로부터 관리자 아이디, 비번을 입력받아 일치하는 경우에만 프로그램이 실행되도록 처리하는 메소드
	 * @param adminId	: 사용자가 입력한 아이디
	 * @param adminPwd	: 사용자가 입력한 비번
	 * @return			: flag : 관리자 계정 일치여부 반환
	 */
	public boolean adminOk(String adminId, String adminPwd) {
		Book b = new BookService().adminOk(adminId);
		
		boolean flag = false;
		
		if(b == null) {
			new BookMenu().displayNoData("잘못된 관리자 아이디 입니다.");
		}else {
			
			if(adminPwd.equals(b.getmPwd())) {
				flag = true;
			}else {
				flag = false;
			}
		}
		return flag;
	}
	
	/**
	 * 사용자로부터 값을 입력받아 새로운 데이터를 추가해주는 메소드
	 * @param mId	: 사용자가 입력한 회원 아이디
	 * @param mPwd	: 사용자가 입력한 회원 비번
	 * @param mName	: 사용자가 입력한 회원 이름
	 * @param phone	: 사용자가 입력한 회원 연락처
	 * @param bNo	: 사용자가 입력한 도서 번호
	 * @param bName	: 사용자가 입력한 도서명
	 * @param wName : 사용자가 입력한 작가명
	 * @param pName : 사용자가 입력한 출판사명
	 */
	public void insertRent(String mId, String mPwd, String mName, String phone, String bNo, String bName, String wName, String pName) {
		
		Book b = new Book(mId, mPwd, mName, phone, Integer.parseInt(bNo), bName, wName, pName);
		
		int result = new BookService().insertRent(b);
		
		if(result > 0) { // 성공
			new BookMenu().displaySuccess("성공적으로 대여목록에 추가되었습니다.");
		}else {
			new BookMenu().displayFail("대여 추가에 실패했습니다..");
		}
	}
	
	/**
	 * 사용자의 전체 리스트 조회 요청을 처리해주는 메소드
	 */
	public void selectList() {
		ArrayList<Book> list = new BookService().selectList();
		
		// 조회 결과가 있는지 없는지 판단한 후 사용자가 보게될 응답화면 지정
		if(list.isEmpty()) { // 텅 비어있을 경우 == 조회된 데이터가 없을 경우
			new BookMenu().displayNoData("전체 조회 결과가 없습니다.");
		}else {
			new BookMenu().displayBookList(list);
		}
		
	}
	
	/**
	 * 사용자가 입력한 회원 아이디의 Book 객체 조회 요청을 처리해주는 메소드
	 * @param mId		: 사용자가 입력한 회원 아이디
	 */
	public void selectByUserId(String mId) {
		Book b = new BookService().selectByUserId(mId);
		
		if(b == null) {
			new BookMenu().displayNoData(mId + "회원이 대여한 도서는 없습니다.");
		}else {
			new BookMenu().displayRentBook(b);
		}
	}
	
	/**
	 * 사용자가 입력한 키워드를 가진 도서명을 모두 조회 처리해주는 메소드
	 * @param keyword		: 사용자가 입력한 키워드
	 */
	public void selectByKeyword(String keyword) {
		ArrayList<Book> list = new BookService().selectByKeyword(keyword);
		
		if(list.isEmpty()) {
			new BookMenu().displayNoData(keyword + "를 키워드로 가진 도서 대여목록이 없습니다.");
		}else {
			new BookMenu().displayBookList(list);
		}
	}
	
	/**
	 * 사용자가 입력한 회원 아이디의 반납기한 일주일 연장 요청을 처리해주는 메소드
	 * @param mId		: 사용자가 입력한 회원 아이디
	 */
	public void updateEndDate(String mId) {
		int result = new BookService().updateEndDate(mId);
		
		if(result > 0) {
			new BookMenu().displaySuccess("성공적으로 반납기한이 연장되었습니다.");
		}else {
			new BookMenu().displayFail("반납기한 연장에 실패했습니다.");
		}
	}
	
	/**
	 * 사용자가 입력한 회원 아이디의 Book 객체 삭제 요청을 처리해주는 메소드
	 * @param mId		: 사용자가 입력한 회원 아이디
	 */
	public void deleteBook(String mId) {
		int result = new BookService().deleteBook(mId);
		
		if(result > 0) {
			new BookMenu().displaySuccess("성공적으로 반납 처리되었습니다.");
		}else {
			new BookMenu().displayFail("도서 반납에 실패했습니다.");
		}
	}


}
