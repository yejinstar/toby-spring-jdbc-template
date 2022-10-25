package com.likelion.dao;

import com.likelion.domain.User;

import java.sql.*;
import java.util.Map;

public class UserDao {

    private ConnectionMaker connectionMaker;

    public UserDao(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }

    public UserDao() {
        this.connectionMaker = new LocalConnectionMaker();
    }

    public void add (User user) throws ClassNotFoundException, SQLException{

        Connection c = connectionMaker.makeConnection();

        PreparedStatement ps = c.prepareStatement(
                "insert into users(id, name, password) values (?,?,?)");
        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());

        ps.executeUpdate();
        ps.close();
        c.close();
    }

    public void deleteAll() throws ClassNotFoundException, SQLException{
        Connection c = connectionMaker.makeConnection();

        PreparedStatement ps = c.prepareStatement(
                "delete from users");
        ps.executeUpdate();
        ps.close();
        c.close();
    }

    public int getCount() throws ClassNotFoundException,SQLException{
        Connection c = connectionMaker.makeConnection();

        PreparedStatement ps = c.prepareStatement(
                "select count (*) from users");
        ResultSet rs = ps.executeQuery();
        rs.next();
        int count = rs.getInt(1);

        rs.close();
        ps.close();
        c.close();

        return count;
    }

    public User findById(String id) throws ClassNotFoundException, SQLException{
        Connection c = connectionMaker.makeConnection();

        PreparedStatement ps = c.prepareStatement(
                "select * from users where id = ?"
        );
        ps.setString(1,id);

        ResultSet rs = ps.executeQuery();
        rs.next();
        User user = new User(rs.getString("id"), rs.getString("name"),
                   rs.getString("password"));
        rs.close();
        ps.close();
        c.close();
        return user;
    }
}
