package com.qgh.spring_mvc.util;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by zhangqingjie on 15-5-18.
 */
public class CommUtil {

    public static String pattern="yyyyMMddHHmmss";
    public static String nextPattern="yyyyMMddHHmmssS";
    private static Logger logger = LoggerFactory.getLogger(CommUtil.class);

    /**
     * 时间转换 字符串 转  DATE
     *
     * @param date
     * @return
     * @throws Exception
     */
    public static Date parseDate(String date) throws Exception {
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);
        } catch (ParseException e) {
            throw new Exception("时间转换失败");
        }
    }
    public static Date parseDate(String dateStr,String parten) {
        if(StringUtils.isBlank(parten)){
            parten = "yyyy-MM-dd HH:mm:ss";
        }
        DateFormat df = new SimpleDateFormat(parten);
        try {
            return df.parse(dateStr);
        } catch (ParseException e) {
            return new Date();
        }
    }
    /**
     * 获取现在时间
     *
     * @return返回字符串格式 yyyy-MM-dd HH:mm:ss
     */
    public static String getNowDateLongStr() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 获取指定格式的当前时间
     * @param parten
     * @return
     */
    public static String getNowDateLongStr(String parten) {
        Date currentTime = new Date();
        if(StringUtils.isBlank(parten)){
            parten="yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat formatter = new SimpleDateFormat(parten);
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 格式化指定格式的日期时间
     * @param date
     * @param parten
     * @return
     */
    public static String getFormatDate(Date date, String parten){
        if(date==null)
            return null;
        if(StringUtils.isBlank(parten)) {
            parten = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat formatter = new SimpleDateFormat(parten);
        String dateValue = formatter.format(date);
        return dateValue;
    }

    /**
     * 检测字符串是否符合时间格式
     * @param dateString
     * @param parten
     * @return
     */
    public static String getFormatDateString(String dateString, String parten) throws Exception {
        if (StringUtils.isBlank(dateString)) {
            return null;
        }
        if (StringUtils.isBlank(parten)) {
            parten = "yyyy-MM-dd HH:mm:ss";
        }
        DateFormat df = new SimpleDateFormat(parten);
        Date date = df.parse(dateString);
        SimpleDateFormat formatter = new SimpleDateFormat(parten);
        String dateValue = formatter.format(date);
        return dateValue;
    }



    /**
     * 格式化指定格式的金额数字,保留两位小数
     * @param value
     * @return
     */
    public static String formatNumberic(String value){
    	
    	Double d = 0.00;
    	try
    	{
			d = Double.parseDouble(value);
		}catch(Exception ex){
			return value;
		}
    	
    	return formatNumberic(d, "");
    }
        
    /**
     * 格式化指定格式的金额数字,保留两位小数
     * @param value
     * @return
     */
    public static String formatNumberic(double value){
    	return formatNumberic(value, "");
    }
    
    /**
     * 格式化指定格式的金额数字
     * @param value
     * @param parten
     * @return
     */
    public static String formatNumberic(double value, String parten){
    	
    	if(parten.isEmpty())
    		parten="###,##0.00";
    	
    	DecimalFormat df = new DecimalFormat(parten);
    	try{
    		String result = df.format(Double.valueOf(value));
    		return result;
    	}catch(Exception ex){
    		return "";
    	}
    }

    /**
     * 获取IP地址
     *
     * @param request HttpServletRequest
     * @return String
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Real-IP");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("x-forwarded-for");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
    /**
     * 获取当前网络ip
     * @param request
     * @return
     */
    public static String getIpAddress(HttpServletRequest request){
        String ipAddress = request.getHeader("x-forwarded-for");
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if(ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")){
                //根据网卡取本机配置的IP
                InetAddress inet=null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ipAddress= inet.getHostAddress();
            }
        }
        //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if(ipAddress!=null && ipAddress.length()>15){ //"***.***.***.***".length() = 15
            if(ipAddress.indexOf(",")>0){
                ipAddress = ipAddress.substring(0,ipAddress.indexOf(","));
            }
        }
        return ipAddress;
    }
    /**
     * 通过IP地址获取MAC地址
     *
     * @param ip String,127.0.0.1格式
     * @return mac String
     * @throws Exception
     */
    public static String getMACAddress(String ip) throws Exception {
        String line = "";
        String macAddress = "";
        final String MAC_ADDRESS_PREFIX = "MAC Address = ";
        final String LOOPBACK_ADDRESS = "127.0.0.1";
        //如果为127.0.0.1,则获取本地MAC地址。
        if (LOOPBACK_ADDRESS.equals(ip)) {
            InetAddress inetAddress = InetAddress.getLocalHost();
            //貌似此方法需要JDK1.6。
            byte[] mac = NetworkInterface.getByInetAddress(inetAddress).getHardwareAddress();
            //下面代码是把mac地址拼装成String
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < mac.length; i++) {
                if (i != 0) {
                    sb.append("-");
                }
                //mac[i] & 0xFF 是为了把byte转化为正整数
                String s = Integer.toHexString(mac[i] & 0xFF);
                sb.append(s.length() == 1 ? 0 + s : s);
            }
            //把字符串所有小写字母改为大写成为正规的mac地址并返回
            macAddress = sb.toString().trim().toUpperCase();
            return macAddress;
        }
        //获取非本地IP的MAC地址
        try {
            Process p = Runtime.getRuntime().exec("nbtstat -A " + ip);
            InputStreamReader isr = new InputStreamReader(p.getInputStream());
            BufferedReader br = new BufferedReader(isr);
            while ((line = br.readLine()) != null) {
                if (line != null) {
                    int index = line.indexOf(MAC_ADDRESS_PREFIX);
                    if (index != -1) {
                        macAddress = line.substring(index + MAC_ADDRESS_PREFIX.length()).trim().toUpperCase();
                    }
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
        return macAddress;
    }

    /**
     * 判断对象是否为空
     *
     * @param obj 需要进行判断的对象
     * @return 为空(true), 不为空(false)
     */
    public static boolean isEmpty(Object obj) {
        return null == obj || "".equals(obj);
    }

    /**
     * 判断一个字符串是否为空 null 和 空字符
     *
     * @param str String
     * @return Boolean 为空返回True 不为空返回 False
     */
    public static boolean isEmptyStr(Object str) {
        return str == null || str.toString().trim().length() < 1 ? true : false;
    }

    private static final char[] hexCode = "0123456789abcdef".toCharArray();

    /**
     * 用md5加密字符串
     *
     * @return
     * @author wuzhongliang 2014年10月9日
     */
    public static String generateValue(String param) {
        try {
            MessageDigest algorithm = MessageDigest.getInstance("MD5");
            algorithm.reset();
            algorithm.update(param.getBytes());
            byte[] messageDigest = algorithm.digest();
            return toHexString(messageDigest);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String toHexString(byte[] data) {
        if (data == null) {
            return null;
        }
        StringBuilder r = new StringBuilder(data.length * 2);
        for (byte b : data) {
            r.append(hexCode[(b >> 4) & 0xF]);
            r.append(hexCode[(b & 0xF)]);
        }
        return r.toString();
    }

    /**
     * 获得一个UUID
     *
     * @return String UUID
     */
    public static String getUUID() {
        String s = UUID.randomUUID().toString();
        //去掉“-”符号
        return s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18) + s.substring(19, 23) + s.substring(24);
    }

    /**
     * 获得指定数目的UUID
     *
     * @param number int 需要获得的UUID数量
     * @return String[] UUID数组
     */
    public static String[] getUUID(int number) {
        if (number < 1) {
            return null;
        }
        String[] ss = new String[number];
        for (int i = 0; i < number; i++) {
            ss[i] = getUUID();
        }
        return ss;
    }
    
    /**
     * 通过身份证号获取出生年月
     * @param certNum
     * @return
     */
    public static String getBirthdayByCertNum(String certNum){
        String birth = "";
        if(StringUtils.isNotBlank(certNum)&&certNum.length()>=15) {
            if (certNum.length() == 15) certNum = certNum.substring(0, 6) + "19" + certNum.substring(6);
            if(certNum.length() == 17||certNum.length() == 18) {
                String year = certNum.substring(6, 10);
                String mouth = certNum.substring(10, 12);
                String day = certNum.substring(12, 14);
                birth = year + "-" + mouth + "-" + day;
            }
        }
        return birth;
    }

    //获取时间差
    public static long dayDiff(String endtime,String starttime,String parten) {
        long diff = -99999;
        if(StringUtils.isBlank(parten)) parten="yyyy-MM-dd HH:mm:ss";
        if( StringUtils.isBlank(endtime)||StringUtils.isBlank(starttime))
            return diff;
        SimpleDateFormat format = new SimpleDateFormat(parten);
        Date d1 = null;
        Date d2 = null;
        try {
            d1 = format.parse(starttime);
            d2 = format.parse(endtime);
            diff =d2.getTime() - d1.getTime();
        } catch (Exception e) {
            logger.error("获取时间差失败：",e);
            diff=-99999;
        }
        return diff;
    }

    //比较和当前时间之间的相差天数
    public static String DateUtil(String date) throws Exception {
        if (StringUtils.isBlank(date)) {
            return null;
        }
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date d1 = sdf.parse(date);
        Date d2 = sdf.parse(sdf.format(now));
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(d1);
        Calendar cal11 = Calendar.getInstance();
        cal11.setTime(d2);
        long time = cal1.getTimeInMillis();
        long time1 = cal11.getTimeInMillis();
        if ((time1 - time) / (1000 * 3600 * 24) < 1) {
            date = "今天";
        } else if ((time1 - time) / (1000 * 3600 * 24) < 2) {
            date = "前1天";
        } else if ((time1 - time) / (1000 * 3600 * 24) < 3) {
            date = "前2天";
        } else if ((time1 - time) / (1000 * 3600 * 24) < 4) {
            date = "前3天";
        } else {
            SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date time2=sd.parse(date);
            date = sd.format(time2);
        }
        return date;
    }
    
	/**
	 * 文件下载
	 * 
	 * @param fileName
	 * @param filePath
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public static void download(String fileName, String filePath, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 设置响应头和客户端保存文件名
		response.setCharacterEncoding("utf-8");
		response.setContentType("multipart/form-data");
		response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);
		try {
			// 打开本地文件流
			InputStream inputStream = new FileInputStream(filePath + fileName);
			// 激活下载操作
			OutputStream os = response.getOutputStream();

			// 循环写入输出流
			byte[] b = new byte[2048];
			int length;
			while ((length = inputStream.read(b)) > 0) {
				os.write(b, 0, length);
			}

			os.close();
			inputStream.close();
		} catch (Exception e) {
			throw e;
		}
	}

    /** V1.0 Created By dongxiaoyong on 2019/8/8
     * description 反转数组
     * @param array
     * @return java.lang.String[]
     */
    public static String[] reverseArray(String[] array){
        String [] newArray = new String[array.length];
        for(int i=0; i<newArray.length; i++){
            newArray[i] = array[array.length - i - 1];
        }
        return newArray;
    }


    /** V1.0 Created By dongxiaoyong on 2019/6/10
     * description 将源List按照指定元素数量拆分为多个List
     * @param source 源List
     * @param splitItemNum 每个List中元素数量
     * @return java.util.List<java.util.List<T>>
     */
    public static <T> List<List<T>> splitList( List<T> source, int splitItemNum )
    {
        List<List<T>> result = new ArrayList<List<T>>();

        if ( source != null && source.size() > 0 && splitItemNum > 0 ){
            if ( source.size() <= splitItemNum ){
                // 源List元素数量小于等于目标分组数量
                result.add( source );
            }else{
                // 计算拆分后list数量
                int splitNum = ( source.size() % splitItemNum == 0 ) ? ( source.size() / splitItemNum ) : ( source.size() / splitItemNum + 1 );

                List<T> value = null;
                for ( int i = 0; i < splitNum; i++ ){
                    if ( i < splitNum - 1 ){
                        value = source.subList( i * splitItemNum, ( i + 1 ) * splitItemNum );
                    }else{
                        // 最后一组
                        value = source.subList( i * splitItemNum, source.size() );
                    }

                    result.add( value );
                }
            }
        }

        return result;
    }
    
    /**
     * 姓名脱敏
     * 
     * @param value
     * @return
     */
    public static String toConcealName(String value) {
    	if (StringUtils.isNotBlank(value)) {
			if (value.length() < 4) {
				value = "*" + value.substring(1, value.length());
			} else {
				StringBuffer sb = new StringBuffer();
				for (int i = 0; i < value.length() - 2; i++) {
					sb.append("*");
				}
				value = sb.toString() + value.substring(value.length() - 2, value.length());
			}
		}
    	return value;
    }
    
    /**
     * 身份证脱敏
     * 
     * @param value
     * @return
     */
    public static String toConcealIdCard(String value) {
    	if (StringUtils.isNotBlank(value) && value.length() > 14) {
    		String head = value.substring(0, 6);
    		String tail = value.substring(value.length() - 2, value.length());
    		StringBuffer sb = new StringBuffer();
			for (int i = 0; i < value.length() - 8; i++) {
				sb.append("*");
			}
			value = head + sb.toString() + tail;
    	}
    	return value;
    }
    
    /**
	 * 将一组数据固定分组，每组n个元素
	 * 
	 * @param source
	 *            要分组的数据源
	 * @param n
	 *            每组n个元素
	 * @param <T>
	 * @return
	 */
	public static <T> List<List<T>> fixedGrouping(List<T> source, int n) {

		if (null == source || source.size() == 0 || n <= 0) {
			return null;
		}
		List<List<T>> result = new ArrayList<List<T>>();

		int sourceSize = source.size();
		int size = (source.size() / n) + 1;
		for (int i = 0; i < size; i++) {
			List<T> subset = new ArrayList<T>();
			for (int j = i * n; j < (i + 1) * n; j++) {
				if (j < sourceSize) {
					subset.add(source.get(j));
				}
			}
			result.add(subset);
		}
		return result;
	}
    
}
