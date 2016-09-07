package com.jianguo.servlet.pay;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.pingplusplus.Pingpp;
import com.pingplusplus.exception.APIConnectionException;
import com.pingplusplus.exception.APIException;
import com.pingplusplus.exception.AuthenticationException;
import com.pingplusplus.exception.ChannelException;
import com.pingplusplus.exception.InvalidRequestException;
import com.pingplusplus.model.Transfer;


public class PayForWx {
	/**
     * Pingpp 管理平台对应的 API Key，api_key 获取方式：登录 [Dashboard](https://dashboard.pingxx.com)->点击管理平台右上角公司名称->开发信息-> Secret Key
     */
    private final static String apiKey = "sk_test_LeTSG8vXTSa11SabXDWzfrTK";

    /**
     * Pingpp 管理平台对应的应用 ID，app_id 获取方式：登录 [Dashboard](https://dashboard.pingxx.com)->点击你创建的应用->应用首页->应用 ID(App ID)
     */
    private final static String appId = "app_P0WHOGK4yj1Cfvn1";

    /**
   * 设置请求签名密钥，密钥对需要你自己用 openssl 工具生成，如何生成可以参考帮助中心：https://help.pingxx.com/article/123161；
   * 生成密钥后，需要在代码中设置请求签名的私钥(rsa_private_key.pem)；
   * 然后登录 [Dashboard](https://dashboard.pingxx.com)->点击右上角公司名称->开发信息->商户公钥（用于商户身份验证）
   * 将你的公钥复制粘贴进去并且保存->先启用 Test 模式进行测试->测试通过后启用 Live 模式
   */

    // 你生成的私钥路径
    private final static String privateKeyFilePath = "D:/tomcat/webapps/JianGuo_Server/pingppKey/rsa_private_key.pem";
    /**
     * 创建企业转账
     *
     * 创建企业转账需要传递一个 map 给 Transfer.create();
     * map 填写的具体介绍可以参考：https://pingxx.com/document/api#api-t-new
     *
     * @return
     */
    public static Transfer transfer() {
    	  Pingpp.apiKey = apiKey;

          // 设置私钥路径，用于请求签名
          Pingpp.privateKeyPath = privateKeyFilePath;
        Transfer transfer = null;
        String orderNo = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date()) + randomString(7);
        Map<String, Object> transferMap = new HashMap<String, Object>();
        transferMap.put("channel", "wx_pub");// 目前支持 wx(新渠道)、 wx_pub
        transferMap.put("order_no", orderNo);// 企业转账使用的商户内部订单号。wx(新渠道)、wx_pub 规定为 1 ~ 50 位不能重复的数字字母组合
        transferMap.put("amount", "200");// 订单总金额, 人民币单位：分（如订单总金额为 1 元，此处请填 100,企业付款最小发送金额为 1 元）
        transferMap.put("type", "b2c");// 付款类型，当前仅支持 b2c 企业付款
        transferMap.put("currency", "cny");
        transferMap.put("recipient", "oT3RUw2iFUe_9bX7DNWhuvt67Tfk");// 接收者 id， 为用户在 wx(新渠道)、wx_pub 下的 open_id
        transferMap.put("description", "your description");
        Map<String, String> app = new HashMap<String, String>();
        app.put("id", appId);
        transferMap.put("app", app);

        try {
            transfer = Transfer.create(transferMap);
            System.out.println(transfer);
        } catch (AuthenticationException e) {
            e.printStackTrace();
        } catch (InvalidRequestException e) {
            e.printStackTrace();
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIException e) {
            e.printStackTrace();
        } catch (ChannelException e) {
			e.printStackTrace();
		}
        return transfer;
    }
    
    private static SecureRandom random = new SecureRandom();
    public static String randomString(int length) {
        String str = new BigInteger(130, random).toString(32);
        return str.substring(0, length);
    } 
    
   
        //给定路径与Json文件，存储到硬盘
    public static  Boolean writeJson(String path,Object json,String fileName){
            BufferedWriter writer = null;
            File file = new File(path + fileName + ".json");
            //如果文件不存在，则新建一个
            if(!file.exists()){
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            //写入
            try {
                writer = new BufferedWriter(new FileWriter(file));
                writer.write(json.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    if(writer != null){
                        writer.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            //System.out.println("文件写入成功！");
            return true;
        }
    }

