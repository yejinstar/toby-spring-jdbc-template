package com.likelion.dao;

import com.likelion.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContextExtensionsKt;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {

    ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
    @Test
    @DisplayName("add and get success?")
    void addAndGet() throws SQLException, ClassNotFoundException {
        //DaoFactory daoFactory = new DaoFactory();
        UserDao userDao = context.getBean("userDao", UserDao.class);
        userDao.deleteAll();
        assertEquals(0,userDao.getCount());
        userDao.add(new User("1","yejin","1234"));
        assertEquals("yejin",userDao.findById("1").getName());
        assertEquals(1,userDao.getCount());
    }
}