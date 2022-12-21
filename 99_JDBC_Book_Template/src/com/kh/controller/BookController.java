package com.kh.controller;

import java.util.ArrayList;

import com.kh.model.dao.BookDao;
import com.kh.model.vo.Book;
import com.kh.service.BookService;
import com.kh.view.BookMenu;

public class BookController {
	
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
	
	public void insertRent(String mId, String mPwd, String mName, String phone, String bNo, String bName, String wName, String pName) {
		
		Book b = new Book(mId, mPwd, mName, phone, Integer.parseInt(bNo), bName, wName, pName);
		
		int result = new BookService().insertRent(b);
		
		if(result > 0) { // 성공
			new BookMenu().displaySuccess("성공적으로 대여목록에 추가되었습니다.");
		}else {
			new BookMenu().displayFail("대여 추가에 실패했습니다..");
		}
	}
	
	public void selectList() {
		ArrayList<Book> list = new BookService().selectList();
		
		// 조회 결과가 있는지 없는지 판단한 후 사용자가 보게될 응답화면 지정
		if(list.isEmpty()) { // 텅 비어있을 경우 == 조회된 데이터가 없을 경우
			new BookMenu().displayNoData("전체 조회 결과가 없습니다.");
		}else {
			new BookMenu().displayBookList(list);
		}
		
	}
	
	public void selectByUserId(String mId) {
		Book b = new BookService().selectByUserId(mId);
		
		if(b == null) {
			new BookMenu().displayNoData(mId + "회원이 대여한 도서는 없습니다.");
		}else {
			new BookMenu().displayRentBook(b);
		}
	}
	
	public void selectByKeyword(String keyword) {
		ArrayList<Book> list = new BookService().selectByKeyword(keyword);
		
		if(list.isEmpty()) {
			new BookMenu().displayNoData(keyword + "를 키워드로 가진 도서 대여목록이 없습니다.");
		}else {
			new BookMenu().displayBookList(list);
		}
	}
	
	public void updateEndDate(String mId) {
		int result = new BookService().updateEndDate(mId);
		
		if(result > 0) {
			new BookMenu().displaySuccess("성공적으로 반납기한이 연장되었습니다.");
		}else {
			new BookMenu().displayFail("반납기한 연장에 실패했습니다.");
		}
	}
	
	public void deleteBook(String mId) {
		int result = new BookService().deleteBook(mId);
		
		if(result > 0) {
			new BookMenu().displaySuccess("성공적으로 반납 처리되었습니다.");
		}else {
			new BookMenu().displayFail("도서 반납에 실패했습니다.");
		}
	}


}
