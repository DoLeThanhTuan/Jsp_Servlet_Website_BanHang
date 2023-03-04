package com.BanHang.controller;

import java.io.IOException;
import java.util.ArrayList;

import com.BanHang.DAO.CategoryDAO;
import com.BanHang.DAO.ProductDAO;
import com.BanHang.model.Category;
import com.BanHang.model.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet(urlPatterns = {"/SearchController"})
public class SearchController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String txtSearch = req.getParameter("txtSearch");
		ArrayList<Product> listPro = ProductDAO.getInstance().selectByName(txtSearch);
		req.setAttribute("listPro", listPro);
		ArrayList<Category> listCate = CategoryDAO.getInstance().selectAll();
		req.setAttribute("listCate", listCate);
		Product proTop1 = ProductDAO.getInstance().selectTop1();
		req.setAttribute("proTop1", proTop1);
		req.setAttribute("txtS", txtSearch);
		req.getRequestDispatcher("./Home.jsp").forward(req, resp);
	}
}
