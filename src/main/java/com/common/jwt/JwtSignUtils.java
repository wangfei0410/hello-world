package com.common.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.sun.org.apache.xml.internal.security.utils.Base64;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wang
 * @Date 2020/10/14 23:10
 */
public class JwtSignUtils {
    public static void main(String[] args) throws Exception {
        String username="sdfjkl";
        int userId = 12232112;
        String phone="3333333";
        String token = sign(username,userId);
        System.out.println(token);
        System.out.println(verify(token));
    }


    /**
     * 超时时间15分钟
     */
    private static final long EXPIRE_TIME = 15*60*1000;
    /**
     * token私钥
     */
    private static final String TOKEN_SECRET = "5adfb1bfc5da4031976c75ec5ab18a0f";

    /**
     * 生成签名,15min后过期
     * return 加密token
     * @throws Exception 
     */
    public static String sign(String userName, Integer userId) throws Exception {

        //过期时间
        Date data = new Date(System.currentTimeMillis()+EXPIRE_TIME);
        //私钥及加密算法
        Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
        //设置头部信息
        Map<String, Object> header = new HashMap<>();
        header.put("type","JWT");
        header.put("algo", "HS256");
        //附带userID，userName信息，生成签名
        String token =  JWT.create()
                .withHeader(header)
                .withClaim("loginName", userName)
                .withClaim("userId",  userId)
                .withExpiresAt(data)
                .sign(algorithm);
        return  token;
    }

    /**
     * token解码
     */
    public static boolean verify(String token) {

        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            verifier.verify(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 获取token中的userName
     */
    public static String getUserName(String token) {
        DecodedJWT decode = JWT.decode(token);
        String loginName = decode.getClaim("loginName").asString();
        return loginName;
    }
    /**
     * 获取token中的userId
     */
    public static String getUserId(String token) throws UnsupportedEncodingException {

        DecodedJWT decode = JWT.decode(token);
        String userId = decode.getClaim("userId").asString();
        return userId;
    }
}
