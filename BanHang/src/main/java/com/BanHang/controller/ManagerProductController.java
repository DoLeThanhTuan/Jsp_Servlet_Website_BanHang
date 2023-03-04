package com.BanHang.controller;

import java.io.IOException;
import java.util.ArrayList;

import com.BanHang.DAO.ProductDAO;
import com.BanHang.model.Account;
import com.BanHang.model.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@WebServlet(urlPatterns = {"/ManagerProductController"})
public class ManagerProductController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession ss = req.getSession();
		Account ac = (Account) ss.getAttribute("accountLogined");
		ArrayList<Product> listMP = ProductDAO.getInstance().selectByIdSell(ac.getId());
		req.setAttribute("listMP", listMP);
		req.getRequestDispatcher("./ManagerProduct.jsp").forward(req, resp);
	}
}
