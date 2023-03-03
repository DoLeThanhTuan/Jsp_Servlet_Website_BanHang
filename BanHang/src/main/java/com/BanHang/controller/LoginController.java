package com.BanHang.controller;

import java.io.IOException;
import java.io.PrintWriter;

import com.BanHang.DAO.AccountDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
			req.getRequestDispatcher("./ManagerProduct.jsp").forward(req, resp);
		}
		else {
			req.setAttribute("error", 1);
			req.getRequestDispatcher("./Login.jsp").forward(req, resp);
		}
	}
}
