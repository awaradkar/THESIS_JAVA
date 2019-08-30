package com.masters.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.masters.dao.CommonDao;

import java.util.List;

@Repository
public class CommonDaoImpl implements CommonDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Object> saveObjList(List<Object> objList) {
        return null;
    }
}
