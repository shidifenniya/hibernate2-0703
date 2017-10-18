package com.lanou.test;

import com.lanou.domain.Customer;
import com.lanou.domain.Order;
import com.lanou.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import java.util.Date;

/**
 * Created by dllo on 17/10/18.
 */
public class ManyToOneTest {

    @Test
    public  void single(){

        //先要创建用户对象
        Customer customer = new Customer("李奕鹏","liyipeng","123","♂");

        //创建一个订单对象
        Order order = new Order("L001",18800, new Date());


        //保存用户以及订单对象
        Session session = HibernateUtil.getSession();

        Transaction transaction = session.beginTransaction();

        /* 单向 n-1 ,在进行n的插入是需要确认1已经保存在数据库中,即1是一个持久化状态的对象 */
        /* 此示例中指的是插入订单时需要确保所属用户已经存在 */

        session.save(customer); //保存用户

        session.flush(); //刷新

        //绑定订单所属的用户
        order.setCustomer(customer);

        session.save(order); //保存订单

        transaction.commit();

        HibernateUtil.closeSession();
    }

    @Test
    public void singleFind(){

        Session session = HibernateUtil.getSession();

        Transaction transaction = session.beginTransaction();

        Order order = session.get(Order.class, 1);

        System.out.println("订单的基础属性: " + order);

//        System.out.println("订单所属用户的属性: " +order.getCustomer());

        transaction.commit();

        HibernateUtil.closeSession();

    }

    /**
     *  双向 1-n 中的插入
     */
    @Test
    public void doubleInsert(){

        // 创建用户对象和订单对象
        Customer customer = new Customer("奈亚子","naiyazi","123","♀");

        Order order = new Order("L003",19800,new Date());

        //绑定订单所属的用户
        order.setCustomer(customer);

        Session session = HibernateUtil.getSession();

        Transaction transaction = session.beginTransaction();

        //保存用户与订单对象
        session.save(customer);

        session.save(order);

        transaction.commit();

        HibernateUtil.closeSession();

    }

    /**
     * 双向 1-n 查询
     */
    @Test
    public void doubleFind(){

        Session session = HibernateUtil.getSession();

        Transaction transaction = session.beginTransaction();

        Customer customer = session.get(Customer.class, 4);

        System.out.println("基础属性:" + customer);

        System.out.println("用户名下的订单集合:" + customer.getOrders());

        transaction.commit();

        HibernateUtil.closeSession();

    }

}
