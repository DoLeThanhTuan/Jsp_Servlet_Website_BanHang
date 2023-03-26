package com.BanHang.controller;

import java.io.IOException;

import com.BanHang.DAO.CartDAO;
import com.BanHang.model.Account;
import com.BanHang.model.Order;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@WebServlet(urlPatterns = {"/cart"})
public class CartController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		Account ac = (Account) session.getAttribute("accountLogined");
		Order order = new Order(ac.getId(), CartDAO.getInstance().selectByUserID(ac.getId()));
		session.setAttribute("order", order);
		session.setAttribute("countCart", CartDAO.getInstance().CountCartByUserID(ac.getId()));
		req.getRequestDispatcher("./Cart.jsp").forward(req, resp);
	}
}
