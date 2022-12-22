package com.kh.controller;

import java.util.ArrayList;

import com.kh.model.service.ProductService;
import com.kh.model.vo.Product;
import com.kh.view.ProductMenu;

public class ProductController {
	
	public void selectList() {
		ArrayList<Product> list = new ProductService().selectList();
		
		if(list.isEmpty()) {
			new ProductMenu().displayNoData("조회된 상품이 없습니다.");
		}else {
			new ProductMenu().displayProductList(list);
		}
	}
	
	public void insertProduct(String pId, String pName, String price, String description, String stock) {
		Product p = new Product(pId, pName, Integer.parseInt(price), description, Integer.parseInt(stock));
		
		int result = new ProductService().insertProduct(p);
		
		if(result > 0) {
			new ProductMenu().displaySuccess(result + "개의 상품 추가 성공!");
		}else {
			new ProductMenu().displayFail("상품 추가 실패..");
		}
	}
	
	public void updateProduct(String pId, String pName, String price, String description, String stock) {
		Product p = new Product(pId, pName, Integer.parseInt(price), description, Integer.parseInt(stock));
		
		int result = new ProductService().updateProduct(p);
		
		if(result > 0) {
			new ProductMenu().displaySuccess(result + "개의 상품 수정 성공!");
		}else {
			new ProductMenu().displayFail("상품 수정 실패..");
		}
	}
	
	public void deleteProduct(String pId) {
		int result = new ProductService().deleteProduct(pId);
		
		if(result > 0) {
			new ProductMenu().displaySuccess(result + "개의 상품 삭제 성공!");
		}else {
			new ProductMenu().displayFail("상품 삭제 실패..");
		}
	}
	
	public void searchProduct(String keyword) {
		ArrayList<Product> list = new ProductService().searchProduct(keyword);
		
		if(list.isEmpty()) {
			new ProductMenu().displayNoData(keyword + "로 조회된 상품이 없습니다.");
		}else {
			new ProductMenu().displayProductList(list);
		}
	}
}
