package com.dao;

import com.pojo.Employee;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author wang
 * @Date 2020/10/14 23:12
 */
@Repository
public interface EmployeeDao {

    /**
     * 添加
     * @param employee
     */
    void insertUserInfo( Employee employee);

    /**
     * 修改
     * @param employee
     */
    void updateUserInfo(Employee employee);

    /**
     * 根据用户手机号查找用户信息
     * @param phone
     * @return
     */
    Employee findUserByPhone(String phone);

    /**
     * 删除
     * @param id
     */
    Integer deleteUserInfo(int id);



}
