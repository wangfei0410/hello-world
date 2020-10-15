package com.pojo;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author wang
 * @Date 2020/10/14 23:10
 */

public class Employee {

    private Integer id;
    @NotBlank(message="用户名不能为空!")
    @Size(min=4,max=12,message="用户名的长度在4~12之间!")
    private String userName;
    private Integer age;
    private String sex;
    @NotBlank(message="地址不能为空!")
    private String address;
    @NotBlank(message="手机号不能为空!")
    @Size(min=11,max=11,message="手机号的长度是11位!")
    private String phone;
    @NotBlank(message="密码不能为空!")
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
