package com.BanHang.controller;

import java.io.IOException;
import java.util.ArrayList;

import com.BanHang.DAO.CategoryDAO;
import com.BanHang.DAO.ProductDAO;
import com.BanHang.model.Account;
import com.BanHang.model.Category;
import com.BanHang.model.PageModel;
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
		ArrayList<Category> listcate = CategoryDAO.getInstance().selectAll();
		req.setAttribute("listCate", listcate);
		PageModel page = new PageModel();
		int index = 1;
		if(req.getParameter("index") != null)
			index = Integer.parseInt(req.getParameter("index"));
		page.setIndex(index);
		ArrayList<Product> listMP = ProductDAO.getInstance().selectByIdSellAndNumPage(ac.getId(),index);
		req.setAttribute("listMP", listMP);
		int pageNumbers = (int) Math.ceil(listMP.size()/6.0);
		page.setPageNumbers(pageNumbers);
		page.setStartPages(index);
		req.setAttribute("page", page);
		req.setAttribute("urlController", "ManagerProductController");
		req.getRequestDispatcher("./ManagerProduct.jsp").forward(req, resp);
	}
}
