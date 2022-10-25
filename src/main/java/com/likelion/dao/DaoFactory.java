package com.likelion.dao;

public class DaoFactory {

    private ConnectionMaker connectionMaker(){
        LocalConnectionMaker localConnectionMaker =
                new LocalConnectionMaker();
        return localConnectionMaker;
    }
    public UserDao userDao() {
        UserDao userDao = new UserDao(connectionMaker());
        return userDao;
    }
}
