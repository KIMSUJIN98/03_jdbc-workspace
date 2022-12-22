package com.kh.service;

import static com.kh.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.model.dao.BookDao;
import com.kh.model.vo.Book;

/**
 * BookService.java
 * @author ksj
 * @since 2022.12.21
 * @version 1.0
 *
 */
public class BookService {
	
	/**
	 * 사용자로부터 관리자 아이디, 비번을 입력받아 일치하는 경우에만 프로그램이 실행되도록 처리하는 메소드
	 * @param adminId	: 사용자로부터 입력받은 아이디
	 * @return			: 사용자가 입력한 아이디로 조회된 Book 객체 하나 반환
	 */
	public Book adminOk(String adminId) {
		Connection conn = getConnection();
		Book b = new BookDao().adminOk(conn, adminId);
		
		close(conn);
		return b;
	}
	
	/**
	 * 사용자로부터 값을 입력받아 새로운 데이터를 추가해주는 메소드
	 * @param b		: 사용자로부터 입력받은 Book 객체 하나
	 * @return		: 수행결과 (처리된 행수)
	 */
	public int insertRent(Book b) {
		Connection conn = getConnection();
		int result = new BookDao().insertRent(conn, b);
		
		if(result > 0) { // 성공시 커밋
			commit(conn);
		}else { // 실패시 롤백
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	/**
	 * 사용자의 전체 리스트 조회 요청을 처리해주는 메소드
	 * @return		: 조회된 전체 리스트
	 */
	public ArrayList<Book> selectList() {
		Connection conn = getConnection();
		ArrayList<Book> list = new BookDao().selectList(conn);
		
		close(conn);
		return list;
	}
	
	/**
	 * 사용자가 입력한 회원 아이디의 Book 객체 조회 요청을 처리해주는 메소드
	 * @param mId		: 사용자가 입력한 회원 아이디
	 * @return			: 사용자가 입력한 회원 아이디를 가진 Book 객체 하나
	 */
	public Book selectByUserId(String mId) {
		Connection conn = getConnection();
		Book b = new BookDao().selectByUserId(conn, mId);
		
		close(conn);
		return b;
	}
	
	/**
	 * 사용자가 입력한 키워드를 가진 도서명을 모두 조회 처리해주는 메소드
	 * @param keyword		: 사용자가 입력한 키워드
	 * @return				: 사용자가 입력한 키워드를 가진 도서명 모두를 담은 리스트
	 */
	public ArrayList<Book> selectByKeyword(String keyword) {
		Connection conn = getConnection();
		ArrayList<Book> list = new BookDao().selectByKeyword(conn, keyword);
		
		close(conn);
		return list;
	}
	
	/**
	 * 사용자가 입력한 회원 아이디의 반납기한 일주일 연장 요청을 처리해주는 메소드
	 * @param mId		: 사용자가 입력한 회원 아이디
	 * @return			: 수행결과 (처리된 행수)
	 */
	public int updateEndDate(String mId) {
		Connection conn = getConnection();
		int result = new BookDao().updateEndDate(conn, mId);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	/**
	 * 사용자가 입력한 회원 아이디의 Book 객체 삭제 요청을 처리해주는 메소드
	 * @param mId		: 사용자가 입력한 회원 아이디
	 * @return			: 수행결과 (처리된 행수)
	 */
	public int deleteBook(String mId) {
		Connection conn = getConnection();
		int result = new BookDao().deleteBook(conn, mId);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

}
