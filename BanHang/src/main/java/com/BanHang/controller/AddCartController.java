package com.BanHang.controller;

import java.io.IOException;
import java.util.ArrayList;

import com.BanHang.DAO.CartDAO;
import com.BanHang.DAO.ProductDAO;
import com.BanHang.model.Account;
import com.BanHang.model.Order;
import com.BanHang.model.OrderProduct;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/addCart"})
public class AddCartController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		Account ac = (Account)session.getAttribute("accountLogined");
		if(ac == null) {
			resp.sendRedirect("./Login.jsp");
		}
		else {
			int pId = Integer.parseInt(req.getParameter("pId"));
			ArrayList<OrderProduct> list = CartDAO.getInstance().selectByUserID(ac.getId());
			// Kiểm tra xem giỏ hàng đã tồn tại hay chưa ?
			if(list==null) {
				list = new ArrayList<>();
			}
			boolean check = false;
			// trường hợp sản phẩm đã tồn tại trong giỏ hàng
			for (OrderProduct orderProduct : list) {
				if(orderProduct.getProduct().getId() == pId) {
					check = true;
					orderProduct.setAmount(orderProduct.getAmount()+1);
					CartDAO.getInstance().update(orderProduct);
					break;
				}
			}
			// trường hợp sản phẩm chưa tồn tại trong giỏ hàng
			if(check == false) {
				OrderProduct orpro = new OrderProduct(ac.getId(),ProductDAO.getInstance().selectById(String.valueOf(pId)),1);
				list.add(orpro);
				CartDAO.getInstance().insert(orpro);
			}
			Order or = new Order(ac.getId(), list);
			session.setAttribute("order", or);
			session.setAttribute("countCart", CartDAO.getInstance().CountCartByUserID(ac.getId()));
			req.getRequestDispatcher("./HomeController").forward(req, resp);
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
