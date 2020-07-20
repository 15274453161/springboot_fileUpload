package com.qgh.spring_mvc.common.util.encryption;

import com.qgh.spring_mvc.common.util.JsonHelper;
import com.qgh.spring_mvc.common.util.JsonHelpers;
import com.qgh.spring_mvc.moduels.bean.Book;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * Create By dongxiaoyong on /2020/2/15
 * description: AES对称加密和解密工具类
 * @author dongxiaoyong
 */
@Slf4j
public class AESUtil {

    /**
     * AES加密
     * @Authoor :dongxiaoyong
     * @Date :2020/2/15 13:39
      * @param encodeRules : 加密规则（盐值）,长度为16位
     * @param content : 待加密内容
      * @return : java.lang.String
    */
    public static String AESEncode(String encodeRules,String content) throws Exception{
        if(StringUtils.isNotBlank(content)){
            if (encodeRules == null) {
                return null;
            }
            // 判断Key是否为16位
            if (encodeRules.length() != 16) {
                return null;
            }
            byte[] raw = encodeRules.getBytes("utf-8");
            SecretKeySpec encodeRulesSpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");// "算法/模式/补码方式"
            cipher.init(Cipher.ENCRYPT_MODE, encodeRulesSpec);
            byte[] encrypted = cipher.doFinal(content.getBytes("utf-8"));
            return new Base64().encodeToString(encrypted);// 此处使用BASE64做转码功能，同时能起到2次加密的作用。
        }
        return null;
    }



    /**
     * AES解密
     * @Authoor :dongxiaoyong
     * @Date :2020/2/15 13:41
      * @param encodeRules : 加密规则（盐值），长度为16位
     * @param content : 待解密内容
      * @return : java.lang.String
    */
    public static String AESDncode(String encodeRules,String content) throws Exception{
            if(StringUtils.isNotBlank(content)){
                // 判断Key是否正确
                if (encodeRules == null) {
                    return null;
                }
                // 判断Key是否为16位
                if (encodeRules.length() != 16) {
                    return null;
                }
                byte[] raw = encodeRules.getBytes("utf-8");
                SecretKeySpec encodeRulesSpec = new SecretKeySpec(raw, "AES");
                Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
                cipher.init(Cipher.DECRYPT_MODE, encodeRulesSpec);
                byte[] encrypted1 = new Base64().decode(content);// 先用base64解密
                byte[] original = cipher.doFinal(encrypted1);
                String originalString = new String(original, "utf-8");
                return originalString;
            }
        return null;
    }

    public static void main(String[] args) throws Exception {
        //待加密内容
//        String requestStr = "{\"user\":{\"name\":\"董晓勇\",\"logName\":\"dongxiaoyong\",\"description\":\"\",\"idcard\":\"440982199512193237\",\"birthday\":\"1995-12-19 00:00:00\",\"sex\":0,\"phone\":\"18279189435\",\"qq\":\"2627105433\",\"email\":\"2627105433@qq.com\",\"status\":1,\"areaId\":4411010201,\"createId\":1,\"password\":\"Tecsun@2019\"},\"orgIdList\":[708],\"roleIdList\":[59]}";
//        String requestStr = "440982199512193237";

       /* Book book = new Book();
        book.setName("Java虚拟机");


        String requestStr = JsonHelper.javaBeanToJson(book);
        String requestStrs = JsonHelpers.javaBeanToJson(book);*/
        String requestStr =
                "{\n" +
                "\"tokenid\":\"12\",\n" +
                " \"deviceid\":\"12\",\n" +
                " \"channelcode\":\"12\"\n" +
                "}";
        System.out.println(requestStr);
        //加密后内容
        String afterEncryStr = AESUtil.AESEncode("1234567890123456",requestStr);

        //解密后内容
        String afterDncryStr = AESUtil.AESDncode("1234567890123456",afterEncryStr);

        System.out.println("待加密内容：" + requestStr );
        System.out.println("加密后内容：" + afterEncryStr );

        System.out.println("解密后内容：" + afterDncryStr );
    }

}
