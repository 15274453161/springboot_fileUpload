package com.qgh.spring_mvc.common.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/***
 用来读取配置文件中的属性
* @date 2020/4/21  14:07
*/

public class Interface {
	private Log log = LogFactory.getLog(Interface.class);
	//配置文件的位置
	private static String CONIFG_FILE = "/application.properties";
	private static Interface instance = null;
	private Properties props = new Properties();

	private Interface() {
		this.init();
	}

	private void init() {
		InputStream in = null;
		
		try {
			in = Interface.class.getResourceAsStream(CONIFG_FILE);
			this.props.load(in);
			System.out.println("接口配置文件加载完成");
		} catch (IOException var11) {
			if (this.log.isErrorEnabled()) {
				this.log.error("加载 "+CONIFG_FILE+"失败!!!", var11);
			}
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException var10) {
					var10.printStackTrace();
				}
			}

		}

	}

	public static Interface getInstance() {
		if (instance == null) {
			instance = new Interface();
		}

		return instance;
	}

	public String get(String key) {
		return this.get(key, (String) null);
	}

	public String get(String key, String defaultValue) {
		return this.props.getProperty(key, defaultValue);
	}

}
