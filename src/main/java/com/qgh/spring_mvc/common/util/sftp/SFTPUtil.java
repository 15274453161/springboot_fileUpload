package com.qgh.spring_mvc.common.util.sftp;

import com.jcraft.jsch.*;
import com.qgh.spring_mvc.common.util.Constants;
import com.qgh.spring_mvc.common.util.sftp.bean.SftpBean;
import lombok.extern.log4j.Log4j;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

/**
 * sftp工具类
 * 
 * @author 2019-08-28 liuwenfeng
 *
 */
@Log4j
public class SFTPUtil extends SftpBean {

	private ChannelSftp sftp;
	private Session session;

	/**
	 * 构造基于密码认证的sftp对象
	 */
	public SFTPUtil(String username, String password, String host, int port) {
		this.username = username;
		this.password = password;
		this.host = host;
		this.port = port;
	}

	/**
	 * 构造基于秘钥认证的sftp对象
	 */
	public SFTPUtil(String username, String host, int port, String privateKey) {
		this.username = username;
		this.host = host;
		this.port = port;
		this.privateKey = privateKey;
	}

	public SFTPUtil() {
	}

	/**
	 * 连接sftp服务器
	 */
	public void login() {
		try {
			JSch jsch = new JSch();
			if (privateKey != null) {
				jsch.addIdentity(privateKey);// 设置私钥
			}

			session = jsch.getSession(username, host, port);

			if (password != null) {
				session.setPassword(password);
			}
			Properties config = new Properties();
			config.put("StrictHostKeyChecking", "no");

			session.setConfig(config);
			session.connect();

			Channel channel = session.openChannel("sftp");
			channel.connect();

			sftp = (ChannelSftp) channel;
		} catch (JSchException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 关闭连接 server
	 */
	public void logout() {
		if (sftp != null) {
			if (sftp.isConnected()) {
				sftp.disconnect();
			}
		}
		if (session != null) {
			if (session.isConnected()) {
				session.disconnect();
			}
		}
	}

	/**
	 * 将输入流的数据上传到sftp作为文件。文件完整路径=basePath+directory
	 * @param basePath
	 *            服务器的基础路径
	 * @param directory
	 *            上传到该目录
	 * @param sftpFileName
	 *            sftp端文件名
	 * @param input
	 *            输入流
	 */
	public void upload(String basePath, String directory, String sftpFileName, InputStream input) throws SftpException {
		try {
			sftp.cd(basePath);
			sftp.cd(directory);
		} catch (SftpException e) {
			// 目录不存在，则创建文件夹
			String[] dirs = directory.split("/");
			String tempPath = basePath;
			for (String dir : dirs) {
				if (null == dir || "".equals(dir))
					continue;
				tempPath += "/" + dir;
				try {
					sftp.cd(tempPath);
				} catch (SftpException ex) {
					sftp.mkdir(tempPath);
					sftp.cd(tempPath);
				}
			}
		}
		sftp.put(input, sftpFileName); // 上传文件
	}

	public void upload( String directory, String sftpFileName, InputStream input) throws SftpException {
		try {
			sftp.cd(directory);
		} catch (SftpException e) {
			// 目录不存在，则创建文件夹
			String[] dirs = directory.split("/");
			String tempPath = directory;
			for (String dir : dirs) {
				if (null == dir || "".equals(dir))
					continue;
				tempPath += "/" + dir;
				try {
					sftp.cd(tempPath);
				} catch (SftpException ex) {
					sftp.mkdir(tempPath);
					sftp.cd(tempPath);
				}
			}
		}
		sftp.put(input, sftpFileName); // 上传文件
	}


	/**
	 * 下载文件。
	 * 
	 * @param directory
	 *            下载目录
	 * @param downloadFile
	 *            下载的文件
	 * @param saveFile
	 *            存在本地的路径
	 */
	public void download(String directory, String downloadFile, String saveFile)
			throws SftpException {
		if (directory != null && !"".equals(directory)) {
			sftp.cd(directory);
		}
		File file = new File(saveFile);
		try ( FileOutputStream out = new FileOutputStream(file)){
			sftp.get(downloadFile, out);
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * 下载文件
	 * 
	 * @param directory
	 *            下载目录
	 * @param downloadFile
	 *            下载的文件名
	 * @return 字节数组
	 */
	public byte[] download(String directory, String downloadFile) throws SftpException, IOException {
		if (directory != null && !"".equals(directory)) {
			sftp.cd(directory);
		}
		try (InputStream is = sftp.get(downloadFile)){
			byte[] fileData = IOUtils.toByteArray(is);
			return fileData;
		}catch (Exception e){
			log.error( "下载文件",e );
			return null;
		}
	}

	/**
	 * 读取sftp文件(行读取)
	 * @param directory	文件夹路径
	 * @param fileName	文件名称
	 */
	public List<String> readLine(String directory, String fileName) throws SftpException, IOException {
		if (directory != null && !"".equals(directory)) {
			sftp.cd(directory);
		}
		return IOUtils.readLines(sftp.get(fileName), Constants.BANK_FILE_ENCODING_TYPE);
	}


	/**
	 * 删除文件
	 * 
	 * @param directory
	 *            要删除文件所在目录
	 * @param deleteFile
	 *            要删除的文件
	 */
	public void delete(String directory, String deleteFile) throws SftpException {
		sftp.cd(directory);
		sftp.rm(deleteFile);
	}

	/**
	 * 列出目录下的文件
	 * 
	 * @param directory
	 *            要列出的目录
	 */
	public Vector<?> listFiles(String directory) throws SftpException {
		return sftp.ls(directory);
	}
}
