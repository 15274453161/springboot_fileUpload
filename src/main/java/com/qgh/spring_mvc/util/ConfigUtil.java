package com.qgh.spring_mvc.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author 秦光泓
 * @title:动态读取配置文件
 * @projectName spring_mvc_8
 * @description: TODO application.properties
 * @date 2020/3/1811:35
 */
public class ConfigUtil {
    private static Logger logger = LoggerFactory.getLogger(ConfigUtil.class);
    private static final String CONIFG_FILE = "/application.properties";
    private static ConfigUtil instance = null;
    private Properties props = new Properties();
    //    配置文件的最后更新时间
    private long lastModified = 0;

    /***
     * 构造函数进行初始化
     * @return
     * @date 2020/3/18  11:38
     */

    private ConfigUtil() {
        this.init();
    }

    private void init() {
        //输入流
        InputStream in = null;
        //获取classpath:目录下的资源的输入流
        in = ConfigUtil.class.getResourceAsStream(CONIFG_FILE);
        try {
            this.props.load(in);
            if (props == null && props.isEmpty())
                logger.error("config.properties  without content. ");
            else
                logger.info("config.properties :");
        } catch (IOException e) {
            e.printStackTrace();
            if (this.logger.isErrorEnabled()) {
                this.logger.error("loading /config.properties fail!", e);
            }
        } finally {//关闭输出流
            if (in != null) {
                try {
                    in.close();
                } catch (IOException var10) {
                    this.logger.error("Config close fail!", var10);
                }
            }
        }

    }

    //获取类的实例
    public static ConfigUtil getInstance() {
        if (instance == null) {
            instance = new ConfigUtil();
        }
        return instance;
    }
   /***
   * 根据key获取配置文件中的value值
    * @param key
   * @return java.lang.String
   * @date 2020/3/18  11:47
   */

    public String get(String key) {
        return this.get(key, (String) null);
    }
  /***
  *
   * @param key
   * @param defaultValue
  * @return java.lang.String
  * @date 2020/3/18  11:47
  */

    public String get(String key, String defaultValue) {
        return this.props.getProperty(key, defaultValue);
    }
}
