package com.BanHang.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.BanHang.context.connectDB;
import com.BanHang.model.OrderProduct;

public class CartDAO {
	private Connection cnt;
	private ResultSet rs;
	private PreparedStatement pst;
	public static CartDAO getInstance() {
		return new CartDAO();
	}
	public ArrayList<OrderProduct> selectByUserID(int uID){
		ArrayList<OrderProduct> list = new ArrayList<>();
		cnt = connectDB.getConnectionSqlServer();
		String cauLenh = "select * from Cart where userID = ?";
		try {
			pst = cnt.prepareStatement(cauLenh);
			pst.setInt(1, uID);
			rs = pst.executeQuery();
			while(rs.next()) {
				OrderProduct orPro = new OrderProduct();
				orPro.setUserID(uID);
				String pID = String.valueOf(rs.getInt(2));
				orPro.setProduct(ProductDAO.getInstance().selectById(pID));
				orPro.setAmount(rs.getInt(3));
				list.add(orPro);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public int CountCartByUserID(int uID){
		int count = 0;
		cnt = connectDB.getConnectionSqlServer();
		String cauLenh = "select Count(*) from Cart where userID = ?";
		try {
			pst = cnt.prepareStatement(cauLenh);
			pst.setInt(1, uID);
			rs = pst.executeQuery();
			while(rs.next()) {
				count = rs.getInt(1);
				return count;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	public void insert(OrderProduct orPro) {
		cnt = connectDB.getConnectionSqlServer();
		String cauLenh = "insert Cart values(?,?,?)";
		try {
			pst = cnt.prepareStatement(cauLenh);
			pst.setInt(1, orPro.getUserID());
			pst.setInt(2, orPro.getProduct().getId());
			pst.setInt(3, orPro.getAmount());
			int kq = pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		connectDB.closeConnectionSqlSever(cnt);
	}
	public void update(OrderProduct orPro) {
		cnt = connectDB.getConnectionSqlServer();
		String cauLenh = "update Cart set amount=? where userID = ? and productID= ?";
		try {
			pst = cnt.prepareStatement(cauLenh);
			pst.setInt(1, orPro.getAmount());
			pst.setInt(2, orPro.getUserID());
			pst.setInt(3, orPro.getProduct().getId());
			int kq = pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		connectDB.closeConnectionSqlSever(cnt);
	}
	public void delete(OrderProduct orPro) {
		cnt = connectDB.getConnectionSqlServer();
		String cauLenh = "delete Cart where userID = ? and productID = ?";
		try {
			pst = cnt.prepareStatement(cauLenh);
			pst.setInt(1, orPro.getUserID());
			pst.setInt(2, orPro.getProduct().getId());
			int kq = pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		connectDB.closeConnectionSqlSever(cnt);
	}
	public int getAmountByID(int userID,int pid) {
		int kq = 0;
		cnt = connectDB.getConnectionSqlServer();
		String cauLenh = "select amount from cart where userID = ? and productID = ?";
		try {
			pst = cnt.prepareStatement(cauLenh);
			pst.setInt(1, userID);
			pst.setInt(2, pid);
			rs = pst.executeQuery();
			while(rs.next()) {
				kq = rs.getInt(1);
				return kq;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		connectDB.closeConnectionSqlSever(cnt);
		return 0;
	}
}
