package com.BanHang.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.BanHang.context.connectDB;
import com.BanHang.model.Product;

public class ProductDAO {
	Connection cnt ;
	ResultSet rs ;
	PreparedStatement pst;
	
	public static ProductDAO getInstance() {
		return new ProductDAO();
	}
	
	public ArrayList<Product> selectAll(){
		ArrayList<Product> l = new ArrayList<>();
		try {
			this.cnt = connectDB.getConnectionSqlServer();
			String cauLenh = "select * from product";
			pst = cnt.prepareStatement(cauLenh);
			rs = pst.executeQuery();
			while(rs.next()) {
				Product prd = new Product(rs.getInt(1),
										rs.getString(2),
										rs.getString(3),
										rs.getDouble(4),
										rs.getString(5),
										rs.getString(6));
				l.add(prd);
			}
			return l;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			connectDB.closeConnectionSqlSever(cnt);
		}
		return null;
	}
	public Product selectTop1() {
		cnt = connectDB.getConnectionSqlServer();
		String cauLenh = "select top(1) * from product order by id desc";
		try {
			pst = cnt.prepareStatement(cauLenh);
			rs = pst.executeQuery();
			while(rs.next()) {
				Product pro = new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(5), rs.getString(6));
				return pro;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public ArrayList<Product> selectByCateId(String Cid){
		ArrayList<Product> list = new ArrayList<>();
		cnt = connectDB.getConnectionSqlServer();
		String cauLenh = "select * from product where cateID = ?";
		try {
			pst = cnt.prepareStatement(cauLenh);
			pst.setInt(1, Integer.parseInt(Cid));
			rs = pst.executeQuery();
			while(rs.next()) {
				Product pro = new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(5));
				list.add(pro);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public Product selectById(String id) {
		cnt = connectDB.getConnectionSqlServer();
		String cauLenh = "select * from product where id = ?";
		try {
			pst = cnt.prepareStatement(cauLenh);
			pst.setInt(1, Integer.parseInt(id));
			rs = pst.executeQuery();
			while(rs.next()) {
				Product pro = new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(5), rs.getString(6));
				return pro;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public ArrayList<Product> selectByName(String txtSearch){
		ArrayList<Product> list = new ArrayList<>();
		cnt = connectDB.getConnectionSqlServer();
		String cauLenh = "select  * from product where name like ?";
		try {
			pst = cnt.prepareStatement(cauLenh);
			pst.setString(1,"%"+ txtSearch+"%");
			rs = pst.executeQuery();
			while(rs.next()) {
				Product pro = new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(5));
				list.add(pro);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Product> selectByIdSell(int i){
		ArrayList<Product> list = new ArrayList<>();
		cnt = connectDB.getConnectionSqlServer();
		String cauLenh = "select p.* from product as p where p.sell_ID = ?";
		try {
			pst = cnt.prepareStatement(cauLenh);
			pst.setInt(1,i);
			rs = pst.executeQuery();
			while(rs.next()) {
				Product pro = new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(5));
				list.add(pro);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public void DeleteByid(int id){
		cnt = connectDB.getConnectionSqlServer();
		String cauLenh = "delete Product where id = ?";
		try {
			pst = cnt.prepareStatement(cauLenh);
			pst.setInt(1,id);
			pst.executeUpdate();
		} catch (Exception e) {
			connectDB.closeConnectionSqlSever(cnt);
			e.printStackTrace();
		}
	}
}
