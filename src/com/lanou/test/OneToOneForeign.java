package com.lanou.test;

import com.lanou.domain.Department;
import com.lanou.domain.Manager;
import com.lanou.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by dllo on 17/10/18.
 */
public class OneToOneForeign {

    private Session session;

    private Transaction transaction;

    @Before
    public void init(){

        session = HibernateUtil.getSession();

        transaction = session.beginTransaction();

    }

    @After
    public void destroy(){

        transaction.commit();

        HibernateUtil.closeSession();

    }

    @Test
    public void save(){

        Department department = new Department("教学部");

        Manager manager = new Manager("老孟");

        //绑定关系
        department.setManager(manager);

        manager.setDepartment(department);

        //保存
        session.save(manager);

    }

}
