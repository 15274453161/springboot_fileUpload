package com.qgh.spring_mvc.common.util.sftp.bean;

import lombok.Data;

/**
 * created by zhuwenquan on 2019/9/4
 */
@Data
public class SftpBean {
    /** SFTP 登录用户名 */
    protected String username;
    /** SFTP 登录密码 */
    protected String password;
    /** 私钥 */
    protected String privateKey;
    /** SFTP 服务器地址IP地址 */
    protected String host;
    /** SFTP 端口 */
    protected int port;
    /**
     * 路径
     */
    private String path;
    /**
     * 文件名称
     */
    private String fileName;
    /**
     * 银行类型
     */
    private String bankType;

    public SftpBean(){}
    public SftpBean(String username, String password, String host) {
        this.username = username;
        this.password = password;
        this.host = host;
        this.port = 22;
    }
    public SftpBean(String username, String password, String host, int port) {
        this.username = username;
        this.password = password;
        this.host = host;
        this.port = port;
    }
}
