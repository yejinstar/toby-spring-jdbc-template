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
        UserDao userDao = new UserDao();
        userDao.add(new User("14","yejin","1234"));
        assertEquals("yejin",userDao.findById("12").getName());
    }
}