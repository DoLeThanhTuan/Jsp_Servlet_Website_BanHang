package com.BanHang.controller;

import java.io.IOException;

import com.BanHang.DAO.CartDAO;
import com.BanHang.DAO.ProductDAO;
import com.BanHang.model.Account;
import com.BanHang.model.OrderProduct;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@WebServlet(urlPatterns = {"/unionCart"})
public class UnionCartController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int pid = Integer.valueOf(req.getParameter("pid"));
		HttpSession session = req.getSession();
		Account ac = (Account)session.getAttribute("accountLogined");
		int amount = CartDAO.getInstance().getAmountByID(ac.getId(), pid);
		OrderProduct orPro = new OrderProduct(ac.getId(), ProductDAO.getInstance().selectById(req.getParameter("pid")), amount);
		if(amount == 1) {
			CartDAO.getInstance().delete(orPro);
		}
		else {
			orPro.setAmount(amount-1);
			CartDAO.getInstance().update(orPro);
		}
		req.getRequestDispatcher("./cart").forward(req, resp);
	}
}
