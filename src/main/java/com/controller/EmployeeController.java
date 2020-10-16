package com.controller;

import com.common.CommonRest;
import com.common.SmsService;
import com.common.jwt.JwtSignUtils;
import com.pojo.Employee;
import com.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wang
 * @Date 2020/10/14 23:10
 */
@Controller
@RequestMapping("/sys")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @RequestMapping("/add")
    @ResponseBody
    public Map<String,Object> addUserInfo(@RequestBody Employee employee){
        System.out.println("ji");
        System.out.println(employee.getId());
        return employeeService.addUserInfo(employee);
    }


    /**
     * 修改用户信息
     * @param employee
     * @return
     */
    @RequestMapping("/updateUserInfo")
    @ResponseBody
    public Map<String,Object> updateUserInfo(@RequestBody Employee employee){
        return employeeService.upDateUserInfo(employee);
    }

    /**
     * 短信发送接口
     * @param request
     * @return
     */
    @RequestMapping("/phoneSms")
    @ResponseBody
    public Map<String,Object> phoneSms(HttpServletRequest request){
        Map<String,Object> map =new HashMap<>();
        String phone = request.getParameter("phone");
        if(phone==""||phone ==null){
            map.put(CommonRest.SUCCESS,false);
            map.put(CommonRest.MSG,"短信发送失败");
            return map;
        }
         String code =  employeeService.phoneSms(phone);
         SmsService.send(phone,code);
         request.getSession().setAttribute("code",code);
         request.getSession().setAttribute("CodeDate",new Date());

         map.put(CommonRest.SUCCESS,true);
         map.put(CommonRest.MSG,"短信发送成功");
         return map;
    }

    @RequestMapping(value = "/phoneLogin")
    @ResponseBody
    public Map<String,Object> phoneLogin(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        String token =request.getHeader("accessToken");
        Map<String,Object> map = new HashMap<>();
         String phone = request.getParameter("phone");
         String code  = request.getParameter("code");
         Date date = (Date)request.getSession().getAttribute("CodeDate");
        String oldCode = (String)request.getSession().getAttribute("code");
         Date now = new Date();
         if((now.getTime()-date.getTime())>6000000){
             map.put(CommonRest.SUCCESS,false);
             map.put(CommonRest.MSG,"验证码已过期，请重新发送");
             return map;
        }
         if(!code.equals(oldCode) || code== "" || code==null){
             map.put(CommonRest.SUCCESS,false);
             map.put(CommonRest.MSG,"验证码错误");
             return map;
         }
      return    employeeService.phoneLogin(phone);
    }
    @RequestMapping("/lo")
    public  String login(){
        return "login";
    }

    @RequestMapping("/index")
    public  String logins(){
        return "index";
    }
}
