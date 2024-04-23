package com.licheedev.serialtool.activity.base;

/**
 * @author : LGQ
 * @date : 2020/06/09 19
 * @desc :
 */
public class BeanTest {

    private String name;
    private String id;

    public BeanTest(String name, String id) {
        this.name = name;
        this.id = id;
    }

    @Override
    public String toString() {
        return "BeanTest{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
