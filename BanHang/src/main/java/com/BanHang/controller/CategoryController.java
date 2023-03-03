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
@WebServlet(urlPatterns = {"/CategoryController"})
public class CategoryController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ArrayList<Product> listPro = ProductDAO.getInstance().selectByCateId(req.getParameter("Cid"));
		req.setAttribute("Cid", req.getParameter("Cid"));
		req.setAttribute("listPro", listPro);
		ArrayList<Category> listCate = CategoryDAO.getInstance().selectAll();
		req.setAttribute("listCate", listCate);
		Product proTop1 = ProductDAO.getInstance().selectTop1();
		req.setAttribute("proTop1", proTop1);
		req.getRequestDispatcher("./Home.jsp").forward(req, resp);
	}
}
