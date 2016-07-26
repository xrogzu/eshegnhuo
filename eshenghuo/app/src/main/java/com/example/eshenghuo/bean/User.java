package com.example.eshenghuo.bean;

/**
 * Created by Administrator on 2016/6/22.
 */
public class User extends BaseBean {
    private int result;
    private String name;
    private String password;


    public String getName() {
        return name;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
