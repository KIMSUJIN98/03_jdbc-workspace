package com.kh.model.vo;

import java.sql.Date;

public class Book {
	
	// 필드부
	private int mNo;
	private String mId;
	private String mPwd;
	private String mName;
	private String mPhone;
	private int bNo;
	private String bName;
	private String wName;
	private String pName;
	private Date rentDate;
	private Date endDate;

	// 생성자부
	public Book() {}

	public Book(int mNo, String mId, String mPwd, String mName, String mPhone, int bNo, String bName, String wName,
			String pName, Date rentDate, Date endDate) {
		super();
		this.mNo = mNo;
		this.mId = mId;
		this.mPwd = mPwd;
		this.mName = mName;
		this.mPhone = mPhone;
		this.bNo = bNo;
		this.bName = bName;
		this.wName = wName;
		this.pName = pName;
		this.rentDate = rentDate;
		this.endDate = endDate;
	}
	
	public Book(String mId, String mPwd, String mName, String mPhone, int bNo, String bName, String wName,
			String pName) {
		super();
		this.mId = mId;
		this.mPwd = mPwd;
		this.mName = mName;
		this.mPhone = mPhone;
		this.bNo = bNo;
		this.bName = bName;
		this.wName = wName;
		this.pName = pName;
	}

	public int getmNo() {
		return mNo;
	}

	public void setmNo(int mNo) {
		this.mNo = mNo;
	}

	public String getmId() {
		return mId;
	}

	public void setmId(String mId) {
		this.mId = mId;
	}

	public String getmPwd() {
		return mPwd;
	}

	public void setmPwd(String mPwd) {
		this.mPwd = mPwd;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public String getmPhone() {
		return mPhone;
	}

	public void setmPhone(String mPhone) {
		this.mPhone = mPhone;
	}

	public int getbNo() {
		return bNo;
	}

	public void setbNo(int bNo) {
		this.bNo = bNo;
	}

	public String getbName() {
		return bName;
	}

	public void setbName(String bName) {
		this.bName = bName;
	}

	public String getwName() {
		return wName;
	}

	public void setwName(String wName) {
		this.wName = wName;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public Date getRentDate() {
		return rentDate;
	}

	public void setRentDate(Date rentDate) {
		this.rentDate = rentDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Override
	public String toString() {
		return mNo + ", " + mId + ", " + mPwd + ", " + mName + ", " + mPhone
				+ ", " + bNo + ", " + bName + ", " + wName + ", " + pName + ", "
				+ rentDate + ", " + endDate;
	}
	
	
	
}
