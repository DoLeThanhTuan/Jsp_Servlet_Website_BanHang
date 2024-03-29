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
		ArrayList<Product> l = new ArrayList();
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
	public ArrayList<Product> selectByNumPage(int numPage){
		ArrayList<Product> l = new ArrayList();
		try {
			this.cnt = connectDB.getConnectionSqlServer();
			String cauLenh = "select * from (select ROW_NUMBER() over (order by id asc) as r, * from product) as x order by x.r offset ? rows Fetch next 6 rows only";
			pst = cnt.prepareStatement(cauLenh);
			pst.setInt(1, numPage*6-6);
			rs = pst.executeQuery();
			while(rs.next()) {
				Product prd = new Product(rs.getInt(2),
						rs.getString(3),
						rs.getString(4),
						rs.getDouble(5),
						rs.getString(6),
						rs.getString(7));
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
	public int CountAll(){
		int kq = 0;
		try {
			this.cnt = connectDB.getConnectionSqlServer();
			String cauLenh = "select count(*) as t from product";
			pst = cnt.prepareStatement(cauLenh);
			rs = pst.executeQuery();
			while(rs.next()) {
				kq = rs.getInt("t");
			}
			return kq;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			connectDB.closeConnectionSqlSever(cnt);
		}
		return 0;
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
		ArrayList<Product> list = new ArrayList();
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
		ArrayList<Product> list = new ArrayList();
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
		ArrayList<Product> list = new ArrayList();
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
	public ArrayList<Product> selectByIdSellAndNumPage(int id,int numPage){
		ArrayList<Product> list = new ArrayList();
		cnt = connectDB.getConnectionSqlServer();
		String cauLenh = "select * from (select ROW_NUMBER() over (order by id asc) as r, * from product where sell_ID = ?) as x order by x.r offset ? rows Fetch next 6 rows only";
		try {
			pst = cnt.prepareStatement(cauLenh);
			pst.setInt(1,id);
			pst.setInt(2, numPage*6-6);
			rs = pst.executeQuery();
			while(rs.next()) {
				Product pro = new Product(rs.getInt(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6), rs.getString(7));
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
	public void insert(String name, String image, double price,String title,
			String desc,int cateID, int sellID) {
		cnt = connectDB.getConnectionSqlServer();
		String cauLenh = "insert product([name],[image],price,title,[description],cateID,sell_ID) values(?,?,?,?,?,?,?)";
		try {
			pst = cnt.prepareStatement(cauLenh);
			pst.setString(1, name);
			pst.setString(2, image);
			pst.setDouble(3, price);
			pst.setString(4, title);
			pst.setString(5, desc);
			pst.setInt(6, cateID);
			pst.setInt(7, sellID);
			int kq = pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		connectDB.closeConnectionSqlSever(cnt);
	}
	public void update(String name, String image, double price,String title,
			String desc,int cateID,int id) {
		cnt = connectDB.getConnectionSqlServer();
		String cauLenh = "update product set [name]= ?, [image] = ? , price = ?, title = ? , [description] = ? , cateID = ? where id = ?";
		try {
			pst = cnt.prepareStatement(cauLenh);
			pst.setString(1, name);
			pst.setString(2, image);
			pst.setDouble(3, price);
			pst.setString(4, title);
			pst.setString(5, desc);
			pst.setInt(6, cateID);
			pst.setInt(7, id);
			int kq = pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		connectDB.closeConnectionSqlSever(cnt);
	}
	
}
