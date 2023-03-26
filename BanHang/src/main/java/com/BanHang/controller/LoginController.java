package com.BanHang.controller;

import java.io.IOException;
import java.io.PrintWriter;

import com.BanHang.DAO.AccountDAO;
import com.BanHang.DAO.CartDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@WebServlet(urlPatterns = {"/LoginController"})
public class LoginController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String user = req.getParameter("user");
		String pass = req.getParameter("pass");
		if(AccountDAO.getInstance().selectByUserAndPass(user, pass) != null) {
			HttpSession session = req.getSession();
			session.setAttribute("accountLogined", AccountDAO.getInstance().selectByUserAndPass(user, pass));
			session.setAttribute("countCart", CartDAO.getInstance().CountCartByUserID(AccountDAO.getInstance().selectByUserAndPass(user, pass).getId()));
			resp.sendRedirect("./HomeController");
		}
		else {
			req.setAttribute("error", 1);
			req.getRequestDispatcher("./Login.jsp").forward(req, resp);
		}
	}
}
