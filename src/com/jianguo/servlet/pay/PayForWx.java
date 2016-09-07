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
     * Pingpp ����ƽ̨��Ӧ�� API Key��api_key ��ȡ��ʽ����¼ [Dashboard](https://dashboard.pingxx.com)->�������ƽ̨���Ͻǹ�˾����->������Ϣ-> Secret Key
     */
    private final static String apiKey = "sk_test_LeTSG8vXTSa11SabXDWzfrTK";

    /**
     * Pingpp ����ƽ̨��Ӧ��Ӧ�� ID��app_id ��ȡ��ʽ����¼ [Dashboard](https://dashboard.pingxx.com)->����㴴����Ӧ��->Ӧ����ҳ->Ӧ�� ID(App ID)
     */
    private final static String appId = "app_P0WHOGK4yj1Cfvn1";

    /**
   * ��������ǩ����Կ����Կ����Ҫ���Լ��� openssl �������ɣ�������ɿ��Բο��������ģ�https://help.pingxx.com/article/123161��
   * ������Կ����Ҫ�ڴ�������������ǩ����˽Կ(rsa_private_key.pem)��
   * Ȼ���¼ [Dashboard](https://dashboard.pingxx.com)->������Ͻǹ�˾����->������Ϣ->�̻���Կ�������̻������֤��
   * ����Ĺ�Կ����ճ����ȥ���ұ���->������ Test ģʽ���в���->����ͨ�������� Live ģʽ
   */

    // �����ɵ�˽Կ·��
    private final static String privateKeyFilePath = "D:/tomcat/webapps/JianGuo_Server/pingppKey/rsa_private_key.pem";
    /**
     * ������ҵת��
     *
     * ������ҵת����Ҫ����һ�� map �� Transfer.create();
     * map ��д�ľ�����ܿ��Բο���https://pingxx.com/document/api#api-t-new
     *
     * @return
     */
    public static Transfer transfer() {
    	  Pingpp.apiKey = apiKey;

          // ����˽Կ·������������ǩ��
          Pingpp.privateKeyPath = privateKeyFilePath;
        Transfer transfer = null;
        String orderNo = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date()) + randomString(7);
        Map<String, Object> transferMap = new HashMap<String, Object>();
        transferMap.put("channel", "wx_pub");// Ŀǰ֧�� wx(������)�� wx_pub
        transferMap.put("order_no", orderNo);// ��ҵת��ʹ�õ��̻��ڲ������š�wx(������)��wx_pub �涨Ϊ 1 ~ 50 λ�����ظ���������ĸ���
        transferMap.put("amount", "200");// �����ܽ��, ����ҵ�λ���֣��綩���ܽ��Ϊ 1 Ԫ���˴����� 100,��ҵ������С���ͽ��Ϊ 1 Ԫ��
        transferMap.put("type", "b2c");// �������ͣ���ǰ��֧�� b2c ��ҵ����
        transferMap.put("currency", "cny");
        transferMap.put("recipient", "oT3RUw2iFUe_9bX7DNWhuvt67Tfk");// ������ id�� Ϊ�û��� wx(������)��wx_pub �µ� open_id
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
    
   
        //����·����Json�ļ����洢��Ӳ��
    public static  Boolean writeJson(String path,Object json,String fileName){
            BufferedWriter writer = null;
            File file = new File(path + fileName + ".json");
            //����ļ������ڣ����½�һ��
            if(!file.exists()){
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            //д��
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
            //System.out.println("�ļ�д��ɹ���");
            return true;
        }
    }

