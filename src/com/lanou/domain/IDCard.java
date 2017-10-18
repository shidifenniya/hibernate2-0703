package com.lanou.domain;

/**
 * Created by dllo on 17/10/18.
 */
public class IDCard {

    //主键id
    private int id;

    //身份证号码
    private String number;

    //所属用户
    private Person person;

    public IDCard() {
    }

    public IDCard(int id, String number) {
        this.id = id;
        this.number = number;
    }

    public IDCard(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "IDCard{" +
                "id=" + id +
                ", number='" + number + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
