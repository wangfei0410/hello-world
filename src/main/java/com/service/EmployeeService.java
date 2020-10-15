package com.service;


import com.pojo.Employee;

import java.util.Map;

/**
 * @author wang
 * @Date 2020/10/14 23:15
 */
public interface EmployeeService {

    /**
     * 添加用户信息
     * @param employee
     * @return
     */
    Map<String,Object> addUserInfo(Employee employee);

    /**
     * 修改用户信息
     * @param employee
     * @return
     */
    Map<String,Object> upDateUserInfo(Employee employee);

    /**
     * 发送短息接口
     * @param phone
     * @return
     */
    String phoneSms(String phone);

    /**
     * 手机号登陆
     * @param phone
     * @return
     */
    Map<String,Object> phoneLogin(String phone);
}
