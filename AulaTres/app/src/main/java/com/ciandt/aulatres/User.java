package com.ciandt.aulatres;

public class User {

private String email = "empty email";
    private String name = "empty name";
    private String password = "empty password";
    private int age = -1;

    public User(){}

    public User(String email, String name, String password, int age) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
