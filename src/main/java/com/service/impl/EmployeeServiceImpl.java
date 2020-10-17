package com.service.impl;

import com.common.CommonRest;
import com.common.MD5Utils;
import com.common.SmsService;
import com.common.jwt.JwtSignUtils;
import com.common.redis.RedisUtil;
import com.dao.EmployeeDao;
import com.pojo.Employee;
import com.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wang
 * @Date 2020/10/14 23:15
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeDao employeeDao;


    @Override
    public Map<String,Object> addUserInfo(Employee employee) {
        Map<String,Object> map =new HashMap<>();
        try {
            String password = employee.getPassword();
            String pwd = MD5Utils.string2MD5(password);
            employee.setPassword(pwd);
            employeeDao.insertUserInfo(employee);
            map.put(CommonRest.SUCCESS,true);
            map.put(CommonRest.MSG,"添加成功");
            return map;
        }catch (Exception e){
            e.printStackTrace();
            map.put(CommonRest.SUCCESS,false);
            map.put(CommonRest.MSG,"数据存入异常，请检查");
            return  map;
        }
    }

    /**
     * 修改用户信息
     * @param employee
     * @return
     */
    @Override
    public Map<String, Object> upDateUserInfo(Employee employee) {
        Map<String,Object> map =new HashMap<>();
         try {
           employeeDao.updateUserInfo(employee);
             map.put(CommonRest.SUCCESS,true);
             map.put(CommonRest.MSG,"修改成功");
             return map;
         }catch (Exception e){
             map.put(CommonRest.SUCCESS,false);
             map.put(CommonRest.MSG,"用户信息修改失败");
             return  map;
         }
    }

    /**
     * 注销用户信息
     * @param id
     * @return
     */
    @Override
    public Map<String,Object> deleteUserInfo(int id) {
        Map<String, Object> map = new HashMap<>();
        try {
            employeeDao.deleteUserInfo(id);
            map.put(CommonRest.SUCCESS, true);
            map.put(CommonRest.MSG, "注销成功");
        }catch (Exception e){
            map.put(CommonRest.SUCCESS,false);
            map.put(CommonRest.MSG,"注销失败");
    }
        return map;
    }

    /**
     * 手机发送验证码
     * @param phone
     * @return
     */
    @Override
    public String phoneSms(String phone) {
            int k1 = (int) ((Math.random() * 9 + 1) * 100000);
            String code = String.valueOf(k1);
            return code;
    }

    /**
     * 手机号登录
     * @param phone
     * @return
     */
    @Override
    public Map<String, Object> phoneLogin(String phone) {
        Map<String, Object> map = new HashMap<>();
        try {
            Employee employee = employeeDao.findUserByPhone(phone);
            String   token = JwtSignUtils.sign(employee.getUserName(),employee.getId());
            map.put(CommonRest.SUCCESS,true);
            map.put(CommonRest.MSG,"登录成功");
            map.put(CommonRest.DATA,token);
            return map;
        }catch (Exception e){
            map.put(CommonRest.SUCCESS,false);
            map.put(CommonRest.MSG,"登录失败，请稍后重试-------");
            return map;
        }
    }
}
