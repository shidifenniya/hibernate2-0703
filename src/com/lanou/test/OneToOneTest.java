package com.lanou.test;

import com.lanou.domain.IDCard;
import com.lanou.domain.Person;
import com.lanou.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by dllo on 17/10/18.
 */
public class OneToOneTest {

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

        Person person = new Person("yangzhao","12456");

        IDCard idCard = new IDCard("21020115600324657x");

        //设置对应关联属性
        person.setIdCard(idCard);

        idCard.setPerson(person);

        //1.如果只保存idCard, 需要通过设置IDCard.hbm.xml中的cascade级联属性
        //  才能级联保存person对象

        /* 2. 如果只保存person, 需要通过设置Person.hbm.xml中的cascade级联属性
              才能级联保存person对象
           3. 维护外键的一方在不设置cascade级联时,保存单个对象时会抛出异常;
              而引用的一方不会抛出异常, 只不过不保存级联对象*/

        session.save(person);

//        session.save(idCard);

    }

    /*  */
    @Test
    public void find(){

        //查询某个人对应的身份证号
        Person person = session.get(Person.class, 1);

        System.out.println("基础属性 : " + person);

        System.out.println("身份证号码 : " + person.getIdCard());

        IDCard idCard = session.get(IDCard.class,1);

        //查询某个身份证号码所属用户
        System.out.println("基础属性 : " + idCard);

        System.out.println("用户属性 : " + idCard.getPerson());

    }

}
