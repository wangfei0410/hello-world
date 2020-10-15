package com.common;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

/**
 * @author wang
 * @Date 2020/10/15 0:03
 */
public class SmsService {
    public static final String SMS_164506674 = "SMS_164506674";

    public static void send(String phone, String code) {
//第二个参数为自己独有的accessKeyid，第三个参数为自己独有的accessKeySecret
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou",
                "LTAIZxFHcucB2a6q", "fvPHk5tr8BtaKUsm5QgRTUDw0y07z8");
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();//组装请求对象
        //request.setProtocol(ProtocolType.HTTPS);
        request.setMethod(MethodType.POST);//设置post提交
        request.setDomain("dysmsapi.aliyuncs.com");//短信API产品域名（接口地址固定，无需修改）
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", "农耕入口");//短信签名
        request.putQueryParameter("TemplateCode", SMS_164506674);
        request.putQueryParameter("TemplateParam", "{code:" + code + "}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
            System.out.println("发送成功");
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }
}
