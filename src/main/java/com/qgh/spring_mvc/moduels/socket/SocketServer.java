package com.qgh.spring_mvc.moduels.socket;

import com.qgh.spring_mvc.common.util.Interface;
import com.qgh.spring_mvc.common.util.StrUtil;
import com.qgh.spring_mvc.common.util.socket.SocketUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import static com.qgh.spring_mvc.common.util.socket.SocketUtil.TIME_SLEEP;

/**
 * @author 秦光泓
 * @title:服务器端
 * @projectName spring_mvc_8
 * @description: TODO
 * @date 2020/7/1311:12
 */
@Slf4j
public class SocketServer extends Thread {
    private ServerSocket serverSocket;
    private boolean isRetHex = false;// 是否16进制字符
    public static final int TIME_SLEEP = 300;// 读取时间间隔
    /**
     * 构造方法进行初始化
     */
    public SocketServer() {
        initServerSocket();
    }

    /***
     * 初始化服务器端
     * @return void
     * @date 2020/7/13  11:14
     */

    public void initServerSocket() {
        /**从配置文件中获取端口*/
        int PORT = Integer.parseInt(Interface.getInstance().get(""));
        /***/
        try {
            if (serverSocket == null) {
                serverSocket = new ServerSocket(PORT);
                log.info("ServerSocket " + PORT);
            }
        } catch (Exception e) {
            log.error("服务器初始化失败", e);
        }
    }

    @Override
    public void run() {
        while (!this.isInterrupted()) {
            try {
                if (serverSocket == null) {
                    initServerSocket();
                    Socket socket = serverSocket.accept();
                    if (socket != null && !socket.isClosed()) {
                       new HandlerThread(socket);
                    }
                }
            } catch (Exception e) {
                log.error("服务器");
            }
        }
    }

    /**
     * 进行数据通信的类
     */
    private class HandlerThread implements Runnable {
        private Socket socket;

        public HandlerThread(Socket socket) {
            this.socket = socket;
            /**调用run方法*/
            new Thread(this).start();
        }

        @Override
        public void run() {
            /***
            try()可以自动在语句结束后关闭流对象
            */
         try(DataInputStream in = new DataInputStream(socket.getInputStream())){
             /**服务器向客户端发送数据*/
             DataOutputStream out = new DataOutputStream(socket.getOutputStream());
             StringBuilder stringBuilder = new StringBuilder();
             // 这里要注意和客户端输出流的写方法对应,否则会抛 EOFException
             byte [] data = new byte[SocketUtil.MAX_BUFFER_LENGTH];
             long startTime = System.currentTimeMillis();
             /**客户端读取服务器端发送的消息*/
             SocketUtil.getInput(isRetHex, stringBuilder, in, data, startTime, TIME_SLEEP);
             String message = stringBuilder.toString();
             if (StringUtils.isNotBlank(message)) {
                 log.info("socket发送内容为：" + message);
                 String result = saveMessage(message);
                 out.write(result.getBytes(SocketUtil.CHARSET_NAME));
             }
         }catch (Exception e){
             log.error("",e);
         }
        }
    }
    /***
    * 将服务器端的string类型的响应进行解析
     * 银行返回的是xml格式的报文
     * @param message
    * @return java.lang.String
    * @date 2020/7/13  13:34
    */

    public String saveMessage(String message) throws Exception {
        // 解析请求报文
       /* BankMessageReq bankMessageReq = JaxbUtil.converyToJavaBean(message.substring(9, message.length()), BankMessageReq.class);
        BankBaseHeadReq bankBaseHeadReq = bankMessageReq.getBankBaseHeadReq();
        BankBaseHeadRes bankBaseHeadRes = new BankBaseHeadRes(bankBaseHeadReq.getReqFlag(),
                bankBaseHeadReq.getMsgType(), bankBaseHeadReq.getTrCode(), bankBaseHeadReq.getUserNo(),
                SequenceUtils.getSequenceNumber(), CommUtil.getNowDateLongStr("yyyy-MM-dd"),
                CommUtil.getNowDateLongStr("HHmmss"), "0", "", BankProcess.BANK_SUCCESS_CODE, "", "");
        BankMessageRes bankMessageRes = new BankMessageRes(bankBaseHeadRes, new BankBodyRes());
        String result = JaxbUtil.convertToXml(bankMessageRes, Constants.BANK_FILE_ENCODING_TYPE);
        // 响应报文
        result = StrUtil.copyStrLeft(BankProcess.getStringLength(result) + "", BankProcess.ZERO_STR, 8)
                + BankProcess.ENCRYPTION_0 + result;

        BankBusinessInfo bankBusinessInfo = new BankBusinessInfo(UID.uuid(), "", message,
                bankMessageReq.getBankBodyReq().getFileName(), "", result, bankMessageReq.getBankBodyReq().getRetCode(),
                "0", BankProcess.BANK_MESSAGE_TYPE_2);
        BankBusinessServiceImpl bankBusinessService = (BankBusinessServiceImpl) SpringTool
                .getBean("bankBusinessService");
        if (null != bankBusinessService) {
            bankBusinessService.insertBankBusiness(bankBusinessInfo); // 报文记录
        } else {
            result = "";
        }*/
        return "result";
    }

}
