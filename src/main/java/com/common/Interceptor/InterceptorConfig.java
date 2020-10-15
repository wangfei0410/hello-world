//package com.common.Interceptor;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class InterceptorConfig implements WebMvcConfigurer {
//
//    @Autowired
//    private AdminInterceptor adminInterceptor;
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        System.out.println("拦截器来了-------------------");
//        registry.addInterceptor(adminInterceptor).addPathPatterns("/**")
//                .excludePathPatterns("/**/*.html")//排除样式、脚本、图片等资源文件
//                .excludePathPatterns("/login")
//                .excludePathPatterns("/**/*.js")
//                .excludePathPatterns("/**/*.css");
//        System.out.println("拦截器工作了");
//
//    }
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        //设置允许跨域的路径
//        registry.addMapping("/**")
//                //设置允许跨域请求的域名
//                .allowedOrigins("*")
//                .allowedHeaders("*")
////                .allowCredentials(true)//是否允许证书 不再默认开启
//                //设置允许的方法
//                .allowedMethods("GET", "POST", "PUT", "DELETE");
////                .maxAge(3600);//跨域允许时间
//    }
//}
