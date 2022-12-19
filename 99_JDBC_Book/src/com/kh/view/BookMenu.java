package com.kh.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.kh.controller.BookController;
import com.kh.model.vo.Book;

public class BookMenu {
	
	private Scanner sc= new Scanner(System.in);
	
	private BookController bc = new BookController();
	
	public void mainMenu() { //-- mainMenu start
		
		while(true) { //-- while start
			
			System.out.println("\n================= 도서대여 프로그램 =================");
	        System.out.println("1) 대여하기 (데이터 추가)");
	        System.out.println("2) 대여자 전체조회 (전체 목록 조회)");
	        System.out.println("3) 회원 아이디로 대여목록 조회 (아이디 검색)");
	        System.out.println("4) 도서명으로 대여자 검색 (키워드 검색)");
	        System.out.println("5) 대여연장 (반납일 변경)");
	        System.out.println("6) 회원탈퇴");
	        System.out.println("0) 프로그램 종료");
	        System.out.println("================================================");
	        System.out.print(">> 메뉴 선택 : ");
		    int menu = sc.nextInt();
			
			sc.nextLine();
			
			switch(menu) {
			case 1: inputRent(); 
					break;
			case 2: bc.selectList();
					break;
			case 3: 
					break;
			case 4: 
					break;
			case 5: 
					break;
			case 6: 
					break;
			case 0: System.out.println("이용해주셔서 감사합니다.");
					return;
			default : System.out.println("메뉴를 잘못입력하셨습니다. 다시입력해주세요.");
			}
			
		} //-- while end
		
	} //-- mainMenu end
	
	public void inputRent() {
		System.out.println("\n==== 대여하기(추가) ====");
		
		System.out.print("아이디 : ");
		String mId = sc.nextLine();
		
		System.out.print("비밀번호 : ");
		String mPwd = sc.nextLine();
		
		System.out.print("이름 : ");
		String mName = sc.nextLine();
		
		System.out.print("연락처 : ");
		String phone = sc.nextLine();
		
		System.out.print("도서번호 : ");
		String bNo = sc.nextLine();
		
		System.out.print("도서명 : ");
		String bName = sc.nextLine();
		
		System.out.print("작가명 : ");
		String wName = sc.nextLine();
		
		System.out.print("출판사명 : ");
		String pName = sc.nextLine();
		
		bc.insertRent(mId, mPwd, mName, phone, bNo, bName, wName, pName);
	}
	
	
	
	
	
	
	
	
	//----------------------------- 응답 화면------------------------------------
	
	public void displaySuccess(String message) {
		System.out.println("\n 서비스 요청 성공 : " + message);
	}
	
	public void displayFail(String message) {
		System.out.println("\n 서비스 요청 실패 : " + message);
	}
	
	public void displayNoData(String message) {
		System.out.println("\n" + message);
	}
	
	public void displayBookList(ArrayList<Book> list) {
		System.out.println("\n 조회된 데이터는 다음과 같습니다.");
		
		for(Book b:list) {
			System.out.println(b);
		}
	}

}
