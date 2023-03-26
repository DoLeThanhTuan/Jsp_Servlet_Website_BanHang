package com.BanHang.model;

import java.util.ArrayList;

public class Order {
	private int userID;
	private ArrayList<OrderProduct> listOrderProduct;
	private double sumPrice;
	
	public Order(int userID, ArrayList<OrderProduct> listOrderProduct) {
		this.userID = userID;
		this.listOrderProduct = listOrderProduct;
		this.CalSumPrice();
	}
	public Order() {
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public ArrayList<OrderProduct> getListOrderProduct() {
		return listOrderProduct;
	}
	public void setListOrderProduct(ArrayList<OrderProduct> listOrderProduct) {
		this.listOrderProduct = listOrderProduct;
	}
	public double getSumPrice() {
		return sumPrice;
	}
	public void setSumPrice(double sumPrice) {
		this.sumPrice = sumPrice;
	}
	
	public void CalSumPrice() {
		this.sumPrice = 0;
		for (OrderProduct orderProduct : listOrderProduct) {
			this.sumPrice += orderProduct.getAmount()*orderProduct.getProduct().getPrice();
		}
	}
	public int countProduct() {
		int count = 0;
		for (OrderProduct orderProduct : listOrderProduct) {
			count++;
		}
		return count;
	}
	
}
