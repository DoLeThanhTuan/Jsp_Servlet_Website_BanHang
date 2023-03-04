package com.BanHang.controller;

import java.io.IOException;

import com.BanHang.DAO.AccountDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet(urlPatterns = {"/SignupController"})
public class SignupController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String user = req.getParameter("user");
		String pass = req.getParameter("pass");
		String repass = req.getParameter("repass");
		if(pass.equals(repass)) {
			if(AccountDAO.getInstance().checkExistAc(user)==null) {
				AccountDAO.getInstance().insertAc(user, repass);
				req.getRequestDispatcher("./HomeController").forward(req, resp);
			}
			else {
				req.setAttribute("errorSU", 1);
				req.getRequestDispatcher("./Login.jsp").forward(req, resp);
			}
		}
		else {
			req.setAttribute("errorSU", 1);
			req.getRequestDispatcher("./Login.jsp").forward(req, resp);
		}
	}
}
