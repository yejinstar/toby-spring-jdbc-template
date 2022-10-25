package com.likelion.dao;

import com.likelion.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContextExtensionsKt;
import org.springframework.dao.EmptyResultDataAccessException;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {
    UserDao userDao;
    @BeforeEach
    void setUp(){
        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
        this.userDao = context.getBean("userDao", UserDao.class);
    }

    @Test
    @DisplayName("jdbcContextWithStatementStrategy")
    void addAndGet() throws SQLException, ClassNotFoundException {
        //DaoFactory daoFactory = new DaoFactory();
        //UserDao userDao = context.getBean("userDao", UserDao.class);
        userDao.deleteAll();
        assertEquals(0,userDao.getCount());
        userDao.add(new User("1","yejin","1234"));
        //assertEquals("yejin",userDao.findById("2").getName());
        assertThrows(EmptyResultDataAccessException.class,()->{
            userDao.findById("14");
        });
        assertEquals(1,userDao.getCount());
    }
}