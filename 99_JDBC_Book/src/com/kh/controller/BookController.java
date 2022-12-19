package com.kh.controller;

import java.util.ArrayList;

import com.kh.model.dao.BookDao;
import com.kh.model.vo.Book;
import com.kh.view.BookMenu;

public class BookController {
	
	public void insertRent(String mId, String mPwd, String mName, String phone, String bNo, String bName, String wName, String pName) {
		
		Book b = new Book(mId, mPwd, mName, phone, Integer.parseInt(bNo), bName, wName, pName);
		
		int result = new BookDao().insertRent(b);
		
		if(result > 0) { // 성공
			new BookMenu().displaySuccess("성공적으로 대여목록에 추가되었습니다.");
		}else {
			new BookMenu().displayFail("대여 추가에 실패했습니다..");
		}
	}
	
	public void selectList() {
		ArrayList<Book> list = new BookDao().selectList();
		
		// 조회 결과가 있는지 없는지 판단한 후 사용자가 보게될 응답화면 지정
		if(list.isEmpty()) { // 텅 비어있을 경우 == 조회된 데이터가 없을 경우
			new BookMenu().displayNoData("전체 조회 결과가 없습니다.");
		}else {
			new BookMenu().displayBookList(list);
		}
		
	}


}
