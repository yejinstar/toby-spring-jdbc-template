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
        userDao.add(new User("4","yejin","1234"));
        assertEquals("Mimi",userDao.findById("3").getName());
    }
}