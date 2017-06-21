package com.model;

/**
 * Created by dell on 2017/6/8.
 */

public class User {
    protected int id;
    protected String username;
    protected String password;
    protected int authorization;
    protected String name;
    protected int age;
    protected String phone;

    public User() {
    }

    public User(int id, String username, String password, int authorization, String name, int age, String phone) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorization = authorization;
        this.name = name;
        this.age = age;
        this.phone = phone;
    }

    public User(String username, String password, int authorization, String name, int age, String phone) {
        this.username = username;
        this.password = password;
        this.authorization = authorization;
        this.name = name;
        this.age = age;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAuthorization() {
        return authorization;
    }

    public void setAuthorization(int authorization) {
        this.authorization = authorization;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
