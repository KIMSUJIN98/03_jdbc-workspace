package com.kh.service;

import static com.kh.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.model.dao.BookDao;
import com.kh.model.vo.Book;

public class BookService {
	
	public Book adminOk(String adminId) {
		Connection conn = getConnection();
		Book b = new BookDao().adminOk(conn, adminId);
		
		close(conn);
		return b;
	}
	
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
	
	public ArrayList<Book> selectList() {
		Connection conn = getConnection();
		ArrayList<Book> list = new BookDao().selectList(conn);
		
		close(conn);
		return list;
	}
	
	public Book selectByUserId(String mId) {
		Connection conn = getConnection();
		Book b = new BookDao().selectByUserId(conn, mId);
		
		close(conn);
		return b;
	}
	
	public ArrayList<Book> selectByKeyword(String keyword) {
		Connection conn = getConnection();
		ArrayList<Book> list = new BookDao().selectByKeyword(conn, keyword);
		
		close(conn);
		return list;
	}
	
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
