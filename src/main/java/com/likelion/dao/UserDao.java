package com.likelion.dao;

import com.likelion.domain.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;
import java.util.Map;

public class UserDao {
    private JdbcTemplate jdbcTemplate;
    private JdbcContext jdbcContext;
    private DataSource dataSource;
    private ConnectionMaker connectionMaker;

    public UserDao(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }

    public UserDao() {
        this.connectionMaker = new LocalConnectionMaker();
    }

    public UserDao(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
//        this.jdbcContext = new JdbcContext(dataSource);
    }

    public UserDao(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }

    public void jdbcContextWithStatementStrategy(StatementStrategy stmt) throws SQLException, ClassNotFoundException {
        Connection c = null;
        PreparedStatement pstmt = null;

        try{
            c = dataSource.getConnection();
            pstmt = stmt.makePreparedStatement(c);
            pstmt.executeUpdate();
        } catch (SQLException e){
            throw e;
        }finally {
            if(pstmt != null){
                try{
                    pstmt.close();
                } catch (SQLException e){}
            }
            if(c != null){
                try{
                    c.close();
                } catch (SQLException e){}
            }
        }
    }

    public void add (User user) throws ClassNotFoundException, SQLException{
        this.jdbcTemplate.update(
                "INSERT INTO users(id, name, password) values(?, ?, ?)",
                user.getId(),user.getName(), user.getPassword()
        );
        /*this.jdbcContext.workWithStatementStrategy(new StatementStrategy() {
            @Override
            public PreparedStatement makePreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(
                        "INSERT INTO users(id, name, password) values(?, ?, ?)");
                ps.setString(1, user.getId());
                ps.setString(2, user.getName());
                ps.setString(3, user.getPassword());
                return ps;
            }
        });*/

        /*jdbcContextWithStatementStrategy(new StatementStrategy() {
            @Override
            public PreparedStatement makePreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(
                        "INSERT INTO users(id, name, password) values(?, ?, ?)");
                ps.setString(1, user.getId());
                ps.setString(2, user.getName());
                ps.setString(3, user.getPassword());
                return ps;
            }
        });*/

        /*AddStrategy addStrategy = new AddStrategy(user);
        jdbcContextWithStatementStrategy(addStrategy);*/

        /*jdbcContextWithStatementStrategy(new StatementStrategy() {
            @Override
            public PreparedStatement makePreparedStatement(Connection connection) throws SQLException {
                PreparedStatement pstmt = null;
                pstmt = connection.prepareStatement(
                        "insert into users(id, name, password) values (?,?,?)");
                pstmt.setString(1, user.getId());
                pstmt.setString(2, user.getName());
                pstmt.setString(3, user.getPassword());
                return pstmt;
            }
        });*/

        /*Connection c = null;
        PreparedStatement pstmt = null;
        try{
            c = connectionMaker.makeConnection();
            pstmt = c.prepareStatement(
                    "insert into users(id, name, password) values (?,?,?)");
            pstmt.setString(1, user.getId());
            pstmt.setString(2, user.getName());
            pstmt.setString(3, user.getPassword());

            pstmt.executeUpdate();
        } catch (SQLException e){
            throw e;
        }
        finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                }
            }
            if (c != null) {
                try {
                    c.close();
                } catch (SQLException e) {
                }
            }
        }*/
    }

    public void deleteAll() throws ClassNotFoundException, SQLException{
        this.jdbcTemplate.update("delete from users");
        /*this.jdbcContext.executeSql("delete from users");*/

        /*this.jdbcContext.workWithStatementStrategy(new StatementStrategy() {
            @Override
            public PreparedStatement makePreparedStatement(Connection connection) throws SQLException {
                return connection.prepareStatement("delete from users");
            }
        });*/

        /*jdbcContextWithStatementStrategy(new StatementStrategy() {
            @Override
            public PreparedStatement makePreparedStatement(Connection connection) throws SQLException {
                return connection.prepareStatement("delete from users");
            }
        });*/

        /*DeleteAllStrategy deleteAllStrategy = new DeleteAllStrategy();
        jdbcContextWithStatementStrategy(deleteAllStrategy);*/

        /*jdbcContextWithStatementStrategy(new StatementStrategy() {
            @Override
            public PreparedStatement makePreparedStatement(Connection connection) throws SQLException {
                PreparedStatement pstmt = null;
                pstmt = connection.prepareStatement(
                        "delete from users");
                return pstmt;
            }
        });*/

        /*Connection c = null;
        PreparedStatement pstmt = null;

        try {
            c = connectionMaker.makeConnection();
            pstmt = c.prepareStatement(
                    "delete from users");
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                }
            }
            if (c != null) {
                try {
                    c.close();
                } catch (SQLException e) {
                }
            }
        }*/
    }

    public int getCount() throws ClassNotFoundException,SQLException{
        return this.jdbcTemplate.queryForObject(
                "select count(*) from users",
                Integer.class
        );

        /*Connection c = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            c = dataSource.getConnection();
            pstmt = c.prepareStatement(
                    "select count(*) from users");
            rs = pstmt.executeQuery();
            rs.next();
            int count = rs.getInt(1);
            return count;
        } catch (SQLException e) {
            throw e;
        } finally {
            if(rs!=null){
                try{
                    rs.close();
                }catch (SQLException e){}
            }
            if(pstmt!=null){
                try{
                    pstmt.close();
                }catch (SQLException e){}
            }
            if(c!=null){
                try{
                    c.close();
                }catch (SQLException e){}
            }
        }*/
    }

    public User findById(String id) throws ClassNotFoundException, SQLException{
        String sql = "SELECT id, name, password FROM users WHERE id = ?";
        RowMapper<User> rowMapper = new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User user = new User(
                        rs.getString("id"), rs.getString("name"),
                        rs.getString("password")
                );
                return user;
            }
        };
        return this.jdbcTemplate.queryForObject(sql,rowMapper,id);

        /*Connection c = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            c = dataSource.getConnection();
            pstmt = c.prepareStatement(
                    "select * from users where id = ?"
            );
            pstmt.setString(1, id);

            rs = pstmt.executeQuery();
            User user = null;
            if (rs.next()) {
                user = new User(rs.getString("id"), rs.getString("name"),
                        rs.getString("password"));
            }
            if (user == null) throw new EmptyResultDataAccessException(1);
            return user;
        } catch (SQLException e) {
            throw e;
        } finally {
            if (rs != null){
                try{
                    rs.close();
                } catch (SQLException e){}
            }
            if (pstmt != null){
                try{
                    pstmt.close();
                } catch (SQLException e){}
            }
            if (c != null){
                try{
                    c.close();
                } catch (SQLException e){}
            }
        }*/

    }
    public List<User> getAll(){
        String sql = "SELECT * FROM users ORDER BY id";
        RowMapper<User> rowMapper = new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User user = new User(rs.getString("id"),
                        rs.getString("name"), rs.getString("password"));
                return user;
            }
        };
        return this.jdbcTemplate.query(sql, rowMapper);
    }
}
