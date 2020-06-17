package com.qgh.spring_mvc.moduels.test;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.Key;
import java.security.Security;

/**
 * @author 秦光泓
 * @title:DES加密
 * @projectName spring_mvc_8
 * @description: TODO
 * @date 2020/5/69:55
 */
public class DesDecrypt {
    //加密字段
    private static String src = "Java资料社区";
    public static void main(String[] args) {
        jdkDES();
       // bcDES();
    }

    /*JDK实现*/
    public static void jdkDES() {
        try {
            //生成KEY
            KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
            keyGenerator.init(56);
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] bytesKey = secretKey.getEncoded();
            //KEY转换
            DESKeySpec desKeySpec = new DESKeySpec(bytesKey);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("DES");
            Key convertSecretKey = factory.generateSecret(desKeySpec);
            //加密
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, convertSecretKey);
            byte[] result = cipher.doFinal(src.getBytes());
            System.out.println("jdk des encrypt : " + new String(Hex.encodeHex(result)));
            //解密
            cipher.init(Cipher.DECRYPT_MODE, convertSecretKey);
            result = cipher.doFinal(result);
            System.out.println("jdk des decrypt : " + new String(result).toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*BC实现*/
   /* public static void bcDES() {
        try {
            Security.addProvider(new BouncyCastleProvider());
            //生成KEY
            KeyGenerator keyGenerator = KeyGenerator.getInstance("DES", "BC");
            keyGenerator.getProvider();
            keyGenerator.init(56);
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] bytesKey = secretKey.getEncoded();
            //KEY转换
            DESKeySpec desKeySpec = new DESKeySpec(bytesKey);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("DES");
            Key convertSecretKey = factory.generateSecret(desKeySpec);
            //加密
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, convertSecretKey);
            byte[] result = cipher.doFinal(src.getBytes());
            System.out.println("bc des encrypt : " + new String(Hex.encode(result)));
            //解密
            cipher.init(Cipher.DECRYPT_MODE, convertSecretKey);
            result = cipher.doFinal(result);
            System.out.println("bc des decrypt : " + new String(result).toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
}
