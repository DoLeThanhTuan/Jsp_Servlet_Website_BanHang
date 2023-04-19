package com.BanHang.controller;

import java.io.IOException;
import java.util.ArrayList;

import com.BanHang.DAO.CategoryDAO;
import com.BanHang.DAO.ProductDAO;
import com.BanHang.model.Account;
import com.BanHang.model.PageModel;
import com.BanHang.model.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@WebServlet(urlPatterns = {"/ManagerPost"})
public class ManagerPost extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("listCate", CategoryDAO.getInstance().selectAll());
		int index = 1;
		if(req.getParameter("index")!=null)
			index = Integer.parseInt(req.getParameter("index"));
		PageModel page = PageModel.getInfor(ProductDAO.getInstance().selectAll(), index);
		ArrayList<Product> listPro = ProductDAO.getInstance().selectByNumPage(index);
		req.setAttribute("listPro", listPro);
		req.setAttribute("page", page);
		req.setAttribute("urlController", "./ManagerPost");
		req.getRequestDispatcher("./ManagerPost.jsp").forward(req, resp);
	}
}
