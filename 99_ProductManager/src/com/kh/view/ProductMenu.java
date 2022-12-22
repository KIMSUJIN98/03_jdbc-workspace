package com.kh.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.kh.controller.ProductController;
import com.kh.model.vo.Product;

public class ProductMenu {
	
	private Scanner sc = new Scanner(System.in);
	
	private ProductController pc = new ProductController();
	
	public void mainMenu() { // --mainMenu start
		
		while(true) {
			System.out.println("\n===== 상품 관리 프로그램 =====");
			
			System.out.println("1. 전체 조회 하기");
			System.out.println("2. 상품 추가 하기");
			System.out.println("3. 상품 수정 하기 (상품 id로 조회하고 수정)");
			System.out.println("4. 상품 삭제 하기 (상품 id로 조회해서 삭제)");
			System.out.println("5. 상품 검색 하기 (상품 이름으로 키워드 검색)");
			System.out.println("0. 프로그램 종료하기");
			System.out.println("======================");
			System.out.print(">> 메뉴 선택 : ");
			int menu = sc.nextInt();
			
			sc.nextLine();
			
			switch(menu) {
			
			case 1:
				pc.selectList();
				break;
			case 2:
				insertProduct();
				break;
			case 3:
				updateProduct();
				break;
			case 4:
				deleteProduct();
				break;
			case 5:
				searchProduct();
				break;
			case 0:
				System.out.println("프로그램을 종료합니다. 이용해주셔서 감사합니다.");
				return;
			default:
				System.out.println("잘못 입력하셨습니다.");
				break;
					
			}
			
		}
		
	} // --mainMenu end
	
	public String insertId() {
		System.out.print("상품 아이디 : ");
		String pId = sc.nextLine();
		return pId;
	}
	
	public void insertProduct() {
		System.out.println("\n===== 상품 추가하기 =====");
		String pId = insertId();
		System.out.print("상품명 : ");
		String pName = sc.nextLine();
		System.out.print("상품가격 : ");
		String price = sc.nextLine();
		System.out.print("상품 상세 정보 : ");
		String description = sc.nextLine();
		System.out.print("재고 : ");
		String stock = sc.nextLine();
		
		pc.insertProduct(pId, pName, price, description, stock);
	}
	
	public void updateProduct() {
		System.out.println("\n===== 상품 수정하기 =====");
		String pId = insertId();
		System.out.print("변경할 상품명 : ");
		String pName = sc.nextLine();
		System.out.print("변경할 상품가격 : ");
		String price = sc.nextLine();
		System.out.print("변경할 상품 상세 정보 : ");
		String description = sc.nextLine();
		System.out.print("변경할 재고 : ");
		String stock = sc.nextLine();
		
		pc.updateProduct(pId, pName, price, description, stock);
	}
	
	public void deleteProduct() {
		System.out.println("\n===== 상품 삭제하기 =====");
		String pId = insertId();
		
		pc.deleteProduct(pId);
	}
	
	public void searchProduct() {
		System.out.println("\n===== 키워드로 상품 검색 =====");
		System.out.print("검색할 키워드 : ");
		String keyword = sc.nextLine();
		
		pc.searchProduct(keyword);
	}
	
	// ======================================= display =============================================
	
	public void displayNoData(String message) {
		System.out.println("\n" + message);
	}
	
	public void displayProductList(ArrayList<Product> list) {
		System.out.println("\n조회된 데이터는 다음과 같습니다.");
		for(Product p: list) {
			System.out.println(p);
		}
	}
	
	public void displaySuccess(String message) {
		System.out.println("\n서비스 요청 성공 : " + message);
	}
	
	public void displayFail(String message) {
		System.out.println("\n서비스 요청 실패 : " + message);
	}
}
