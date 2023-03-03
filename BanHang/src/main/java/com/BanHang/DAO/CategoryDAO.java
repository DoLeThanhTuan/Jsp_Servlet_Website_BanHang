package com.BanHang.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.BanHang.context.connectDB;
import com.BanHang.model.Category;

public class CategoryDAO {
	Connection cnt ;
	ResultSet rs ;
	PreparedStatement pst;
	
	public static CategoryDAO getInstance() {
		return new CategoryDAO();
	}
	
	public ArrayList<Category> selectAll(){
		ArrayList<Category> list = new ArrayList<>();
		cnt = connectDB.getConnectionSqlServer();
		String cauLenh = "select * from Category";
		try {
			pst = cnt.prepareStatement(cauLenh);
			rs = pst.executeQuery();
			while(rs.next()) {
				Category ct = new Category(rs.getInt(1), rs.getString(2));
				list.add(ct);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			connectDB.closeConnectionSqlSever(cnt);
		}
		
		return null;
	}
}
