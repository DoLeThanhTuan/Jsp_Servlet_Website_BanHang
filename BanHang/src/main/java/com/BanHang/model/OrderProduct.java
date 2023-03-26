package com.BanHang.model;

public class OrderProduct {
	private int userID;
	private Product product;
	private int amount;
	public OrderProduct(int userID, Product product, int amount) {
		this.userID = userID;
		this.product = product;
		this.amount = amount;
	}
	public OrderProduct() {
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	
	
}
