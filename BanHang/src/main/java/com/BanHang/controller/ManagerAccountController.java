package com.BanHang.controller;

import java.io.IOException;
import java.util.ArrayList;

import com.BanHang.DAO.AccountDAO;
import com.BanHang.model.Account;
import com.BanHang.model.PageModel;

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
		HttpSession ss = req.getSession();
		Account ac = (Account) ss.getAttribute("accountLogined");
		int index = 1;
		if(req.getParameter("index")!=null)
			index = Integer.parseInt(req.getParameter("index"));
		ArrayList<Account> listMA = new ArrayList<>();
		listMA = AccountDAO.getInstance().selectByNumPage(ac.getUser(),index);
		req.setAttribute("listMA",listMA );
		PageModel page = PageModel.getInfor(AccountDAO.getInstance().selectAll(ac.getUser()), index);
		req.setAttribute("page", page);
		req.setAttribute("urlController", "ManagerAccountController");
		req.getRequestDispatcher("./ManagerAccount.jsp").forward(req, resp);
	}
}
