package com.BanHang.controller;

import java.io.IOException;

import com.BanHang.DAO.ProductDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet(urlPatterns = {"/EditProductController"})
public class EditProductController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int pid = Integer.parseInt(req.getParameter("pid"));
		String name = req.getParameter("name");
		String image = req.getParameter("image");
		double price = Double.parseDouble(req.getParameter("price"));
		String title = req.getParameter("title");
		String desc = req.getParameter("description");
		int cateID = Integer.parseInt(req.getParameter("category"));
		ProductDAO.getInstance().update(name, image, price, title, desc, cateID, pid);
		resp.sendRedirect("./ManagerProductController");
	}
}
