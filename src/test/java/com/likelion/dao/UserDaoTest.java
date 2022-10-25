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
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {
    UserDao userDao;
    @BeforeEach
    void setUp(){
        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
        this.userDao = context.getBean("userDao", UserDao.class);
    }

    @Test
    @DisplayName("findById에 try-catch 적용 ")
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

    @Test
    @DisplayName("없을 떄 빈 리스트 리턴하는지, 있을 때 개수만큼 리턴하는지?")
    void getAllTest() throws SQLException, ClassNotFoundException {
        User user1 = new User("1","Yejin","1234");
        User user2 = new User("2","Gun","4321");
        User user3 = new User("3","Mimi","2314");
        userDao.deleteAll();
        List<User> users = userDao.getAll();
        assertEquals(0,users.size());
        userDao.add(user1);
        userDao.add(user2);
        userDao.add(user3);
    }

}