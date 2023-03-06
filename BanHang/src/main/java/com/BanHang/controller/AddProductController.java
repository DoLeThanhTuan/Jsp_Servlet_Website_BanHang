package com.BanHang.controller;

import java.io.IOException;

import com.BanHang.DAO.ProductDAO;
import com.BanHang.model.Account;
import com.BanHang.model.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@WebServlet(urlPatterns = {"/AddProductController"})
public class AddProductController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String image = req.getParameter("image");
		double price = Double.parseDouble(req.getParameter("price"));
		String title = req.getParameter("title");
		String desc = req.getParameter("description");
		int cateID = Integer.parseInt(req.getParameter("category"));
		HttpSession ss = req.getSession();
		Account ac = (Account) ss.getAttribute("accountLogined");
		int acID = ac.getId();
		ProductDAO.getInstance().insert(name, image, price, title, desc, cateID, acID);
		resp.sendRedirect("./ManagerProductController");
	}
}
