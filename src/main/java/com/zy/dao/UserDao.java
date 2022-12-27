package com.zy.dao;

import com.zy.utils.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    private Connection con;
    public boolean isRegistered(String username,String password) throws SQLException {
        boolean IsRegistered=false;
        con= JdbcUtil.getConnecttion();
        String sql="SELECT * FROM user where username=? and password=?";
        try{
            PreparedStatement psmt =con.prepareStatement(sql);
            psmt.setString(1,username);
            psmt.setString(2,password);
            ResultSet rs=psmt.executeQuery();
            while(rs.next()){
                IsRegistered=true;
            }

        }catch (SQLException e){
            con.rollback();
            e.printStackTrace();
        }finally {
            con.close();
        }
        return IsRegistered;
    }
}
