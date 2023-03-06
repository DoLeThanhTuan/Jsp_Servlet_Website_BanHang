package com.BanHang.controller;

import java.io.IOException;
import java.util.ArrayList;

import com.BanHang.DAO.AccountDAO;
import com.BanHang.model.Account;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/ManagerAccountController"})
public class ManagerAccountController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ArrayList<Account> listMA = new ArrayList<>();
		HttpSession ss = req.getSession();
		Account ac = (Account) ss.getAttribute("accountLogined");
		listMA = AccountDAO.getInstance().selectAll(ac.getUser());
		req.setAttribute("listMA",listMA );
		req.getRequestDispatcher("./ManagerAccount.jsp").forward(req, resp);
	}
}
