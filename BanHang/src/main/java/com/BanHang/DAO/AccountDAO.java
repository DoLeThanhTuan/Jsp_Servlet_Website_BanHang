package com.BanHang.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.BanHang.context.connectDB;
import com.BanHang.model.Account;

public class AccountDAO {
	private Connection cnt;
	private ResultSet rs;
	private PreparedStatement pst;
	public static AccountDAO getInstance() {
		return new AccountDAO();
	}
	public Account selectByUserAndPass(String user,String pass) {
		cnt = connectDB.getConnectionSqlServer();
		try {
			String cauLenh = "select * from Account where [user] = ? and pass = ?";
			pst = cnt.prepareStatement(cauLenh);
			pst.setString(1, user);
			pst.setString(2, pass);
			rs = pst.executeQuery();
			while(rs.next()) {
				Account ac = new Account(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getInt(5));
				return ac;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			connectDB.closeConnectionSqlSever(cnt);
		}
		return null;
	}
	public void insertAc(String user,String pass) {
		cnt = connectDB.getConnectionSqlServer();
		try {
			String cauLenh = "insert Account values(?,?,?,?)";
			pst = cnt.prepareStatement(cauLenh);
			pst.setString(1, user);
			pst.setString(2, pass);
			pst.setInt(3, 0);
			pst.setInt(4, 0);
			int kq = pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			connectDB.closeConnectionSqlSever(cnt);
		}
	}
	public Account checkExistAc(String user) {
		cnt = connectDB.getConnectionSqlServer();
		try {
			String cauLenh = "select * from Account where [user] = ?";
			pst = cnt.prepareStatement(cauLenh);
			pst.setString(1, user);
			rs = pst.executeQuery();
			while(rs.next()) {
				Account ac = new Account(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getInt(5));
				return ac;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			connectDB.closeConnectionSqlSever(cnt);
		}
		return null;
	}
}
