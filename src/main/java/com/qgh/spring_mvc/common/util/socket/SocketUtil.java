package com.qgh.spring_mvc.common.util.socket;

import com.qgh.spring_mvc.common.util.Constants;
import com.qgh.spring_mvc.common.util.StrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;

/**
 * @author 秦光泓
 * @title:socket连接工具包
 * @projectName spring_mvc_8
 * @description: TODO
 * @date 2020/7/1311:44
 */
public class SocketUtil {
    private static final Logger logger = LoggerFactory.getLogger(SocketUtil.class);
    //编码
    public static final String CHARSET_NAME = Constants.BANK_FILE_ENCODING_TYPE;
    /**每次读数据的长度*/
    public static int MAX_BUFFER_LENGTH = 16 * 1024;//下载图片效率过低，所以改大了
    public static int TIME_SLEEP = 300;
    //超时时间
    private static final int CONNECTTIMEOUT = 60*1000;//milliseconds
    private static final int SOTIMEOUT = 60*1000;
    /***
    *
     * @param ip  目标ip
     * @param id  目标id
     * @param connectTimeOut 超时时间
     * @param soTimeOut io超时时间
     * @param data  发送数据
     * @param isRetHex  是否是16进制
    * @return java.lang.String
    * @date 2020/7/13  11:49
    */

    public static String send(String ip, int id,
                              int connectTimeOut, int soTimeOut, byte[] data, boolean isRetHex) throws Exception {
        //检查输入
        if( ip == null || id<0 || data==null || ip.isEmpty() || data.length==0 ){
            throw new Exception( "请检查输入" );
        }
        /**计时*/
        long time = System.currentTimeMillis();
        StringBuilder stringBuilder = new StringBuilder();

        Socket socket = new Socket();
        try {
            SocketAddress remoteAddr = new InetSocketAddress(ip, id);
            socket.connect(remoteAddr, connectTimeOut);
        } catch (IOException e) {
            logger.error("创建socket异常",e);
            throw new Exception( e.getMessage() );
        }
        /**设置IO超时时间*/
        try {
            socket.setSoTimeout(soTimeOut);
        } catch (SocketException e) {
            logger.error("设置socket的IO超时时间异常",e);
            throw new Exception( e.getMessage() );
        }

        DataOutputStream out = null;
        try {
            out = new DataOutputStream(socket.getOutputStream());
            out.write(data);
            out.flush();
        } catch (IOException e) {
            logger.error("socket写入数据异常",e);
            throw new Exception( e.getMessage() );
        }

        DataInputStream in = null;
        try {
            in = new DataInputStream(socket.getInputStream());
            byte[] retData = new byte[ MAX_BUFFER_LENGTH ];
            long timeR = System.currentTimeMillis();
            getInput(isRetHex, stringBuilder, in, retData, timeR, TIME_SLEEP);
        } catch (Exception e) {
            logger.error("socket 短连接发送",e);
        }

        try {
            out.close();
            in.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        logger.debug("SocketUtilRet[" + (System.currentTimeMillis()-time) + "]：" + stringBuilder.toString());
        return stringBuilder.toString();
    }
    /***
    *
     * @param isRetHex 是否是16进制
     * @param stringBuilder
     * @param in
     * @param retData
     * @param timeR
     * @param timeSleep  间隔时间
    * @return void
    * @date 2020/7/13  13:16
    */

    public static void getInput(boolean isRetHex, StringBuilder stringBuilder, DataInputStream in, byte[] retData, long timeR, int timeSleep) throws IOException, InterruptedException {
        int retLen;
        /**只要流中还有数据就一直读取*/
        while ((retLen = in.read(retData, 0, retData.length)) > 0){
            //有返回数据
            if( isRetHex ){
                stringBuilder.append( StrUtil.toHex(retData, retLen) );//没有工具注释
            }else{
                stringBuilder.append( new String( retData, 0, retLen, CHARSET_NAME) );//注意调研那个方编码
            }
//            System.out.println( "retLen["+ (System.currentTimeMillis()-timeR) +"]" + retLen );
            if ( retLen < MAX_BUFFER_LENGTH ){
//                    Tools.sleep( TIME_SLEEP );
                /**没看懂*/
                Thread.sleep(timeSleep);
            }
//            timeR = System.currentTimeMillis();
            if ( in.available()<=0 ){
                break;
            }
        }
    }
}
