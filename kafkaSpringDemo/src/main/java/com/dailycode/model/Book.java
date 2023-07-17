package com.dailycode.model;


public class Book {
	
	private String bookName;
	private String isdn;
	
	
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getIsdn() {
		return isdn;
	}
	public void setIsdn(String isdn) {
		this.isdn = isdn;
	}
	
	public Book() {
		super();
	}
	
	public Book(String bookName, String isdn) {
		super();
		this.bookName = bookName;
		this.isdn = isdn;
	}
	
	
	@Override
	public String toString() {
		return "Book [bookName=" + bookName + ", isdn=" + isdn + "]";
	}
	
	

	
	
}
