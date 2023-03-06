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

@WebServlet(urlPatterns = {"/ClickEditController"})
public class ClickEditController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ArrayList<Category> listCA = CategoryDAO.getInstance().selectAll();
		req.setAttribute("listCate", listCA);
		String pid = req.getParameter("pid");
		req.setAttribute("pid", pid);
		Product pro = ProductDAO.getInstance().selectById(pid);
		req.setAttribute("pro", pro);
		req.getRequestDispatcher("./Edit.jsp").forward(req, resp);
	}
}
