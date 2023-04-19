package com.BanHang.controller;

import java.io.IOException;
import java.util.ArrayList;

import com.BanHang.DAO.CategoryDAO;
import com.BanHang.DAO.ProductDAO;
import com.BanHang.model.Category;
import com.BanHang.model.PageModel;
import com.BanHang.model.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet(urlPatterns = {"/HomeController"})
public class HomeController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ArrayList<Category> listCate = CategoryDAO.getInstance().selectAll();
		req.setAttribute("listCate", listCate);
		Product proTop1 = ProductDAO.getInstance().selectTop1();
		req.setAttribute("proTop1", proTop1);
		PageModel page = new PageModel();
		// số lượng trang
		int countPage = (int) Math.ceil(ProductDAO.getInstance().CountAll()/6.0);
		page.setPageNumbers(countPage);
		// vị trí hiện tại của trang
		int index = 1;
		if(req.getParameter("index") != null) {
			index = Integer.parseInt(req.getParameter("index"));
		}
		page.setIndex(index);
		// vị trí trang bắt đầu
		page.setStartPages(index);
		req.setAttribute("page", page);
		ArrayList<Product> listPro = ProductDAO.getInstance().selectByNumPage(index);
		req.setAttribute("listPro", listPro);
		req.setAttribute("urlController", "HomeController");
		req.getRequestDispatcher("./Home.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
