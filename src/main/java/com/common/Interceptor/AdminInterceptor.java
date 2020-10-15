package com.common.Interceptor;
import com.common.jwt.JwtSignUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author wang
 * @Date 2020/10/14 23:10
 */

@Component
public class AdminInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        System.out.println("-------进入拦截器，拦截器开始工作----------------");
        try {
            //统一拦截
            String accessToken = request.getHeader("accessToken");
            if (accessToken != null) {
                boolean verify = JwtSignUtils.verify(accessToken);
                if (verify) {
                        return true;
                    } else {
                        //重置response
                        response.reset();
                        //设置编码格式
                        response.setCharacterEncoding("UTF-8");
                        response.setContentType("application/json;charset=UTF-8");
                        PrintWriter pw = response.getWriter();
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("code", "501");
                        jsonObject.put("errorMsg", "TOKEN失效!,请重新登录");
                        pw.write(jsonObject.toString());
                        pw.flush();
                        pw.close();
                        return false;
                    }
                } else {
                System.out.println(request.getContextPath()+"请求--------------");
//                response.sendRedirect(request.getContextPath()+"/login");
                //重置response
                response.reset();
                //设置编码格式
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json;charset=UTF-8");

                PrintWriter pw = response.getWriter();

                JSONObject jsonObject = new JSONObject();
                jsonObject.put("code", "501");
                jsonObject.put("errorMsg", "TOKEN失效!,请重新登录");
                pw.write(jsonObject.toString());
                pw.flush();
                pw.close();
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
