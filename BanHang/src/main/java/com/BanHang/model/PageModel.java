package com.BanHang.model;

import java.util.ArrayList;

import jakarta.servlet.http.HttpServlet;

public class PageModel {
	private int index;
	private int pageNumbers;
	private int visiblePages = 3;
	private int startPages;
	public PageModel(int index, int pageNumbers, int visiblePages, int startPages) {
		this.index = index;
		this.pageNumbers = pageNumbers;
		this.visiblePages = visiblePages;
		this.startPages = startPages;
	}
	public PageModel() {
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public int getPageNumbers() {
		return pageNumbers;
	}
	public void setPageNumbers(int pageNumbers) {
		this.pageNumbers = pageNumbers;
	}
	public int getVisiblePages() {
		return visiblePages;
	}
	public void setVisiblePages(int visiblePages) {
		this.visiblePages = visiblePages;
	}
	public int getStartPages() {
		return startPages;
	}
	public void setStartPages(int startPages) {
		this.startPages = startPages;
	}
	public static <T> PageModel getInfor(ArrayList<T> list, int index) {
		PageModel p = new PageModel();
		p.setIndex(index);
		p.setStartPages(index);
		p.setPageNumbers((int) Math.ceil(list.size()/6.0));
		return p;
	}
}
