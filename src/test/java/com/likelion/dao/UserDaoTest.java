package com.likelion.dao;

import com.likelion.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {
    @Test
    @DisplayName("add and get success?")
    void addAndGet() throws SQLException, ClassNotFoundException {
        DaoFactory daoFactory = new DaoFactory();
        UserDao userDao = daoFactory.userDao();
        userDao.add(new User("13","yejin","1234"));
        assertEquals("Mimi",userDao.findById("3").getName());
    }
}