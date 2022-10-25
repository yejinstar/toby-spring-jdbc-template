package com.likelion.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaoFactory {

    private ConnectionMaker connectionMaker(){
        LocalConnectionMaker localConnectionMaker =
                new LocalConnectionMaker();
        return localConnectionMaker;
    }
    @Bean
    public UserDao userDao() {
        UserDao userDao = new UserDao(connectionMaker());
        return userDao;
    }
}
