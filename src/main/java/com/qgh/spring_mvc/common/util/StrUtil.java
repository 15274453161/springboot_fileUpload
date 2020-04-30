package com.qgh.spring_mvc.common.util;

import java.io.UnsupportedEncodingException;
import java.util.Collection;


/**
 * 字符串、数组,工具类
 * V1.0 created by zhuwenquan on
 */
public class StrUtil {
	
	private static final String CHARSETNAME = Constants.BANK_ENCODING_TYPE;
	private static final String BANK_FILE_ENCODING_TYPE = Constants.BANK_FILE_ENCODING_TYPE;

	// 提供外部使用begin---------------------------------------------------------------------
	public static boolean isEmpty(CharSequence str) {
		if (str == null || str.length() == 0)
			return true;
		else
			return false;
	}

	/**
	 * 平台格式（hex）
	 * @param request
	 * @param retquestLen
	 */
	public static String toHex(byte[] request, int retquestLen){
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < retquestLen; i++){
			builder.append( Integer
					.toString((request[i] & 0xff) + 0x100, 16)
					.substring(1) ) ;
		}
		return builder.toString();
	}

	/**
	 * 将给定的 BCD 码数组转换为字符串 输入: byte[] bytes -- 要转换的 BCD 码串 返回值：将输入的 BCD 码串转换为的字符串
	 * 例： 0xAB --> "AB" 0xa1 --> "A1" 0x12ab --> "12AB"
	 */
	public static String bcd2Str(byte[] bytes) {
		StringBuilder ret = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			byte highByte = (byte) ((bytes[i] & 0xf0) >>> 4);
			byte lowByte = (byte) (bytes[i] & 0x0f);
			ret.append(Integer.toHexString(highByte));
			ret.append(Integer.toHexString(lowByte));
		}
		return ret.toString();
	}
	
	public static String bcd2Str(byte b) {
		StringBuilder ret = new StringBuilder();
		byte highByte = (byte) ((b & 0xf0) >>> 4);
		byte lowByte = (byte) (b & 0x0f);
		ret.append(Integer.toHexString(highByte));
		ret.append(Integer.toHexString(lowByte));
		
		return ret.toString();
	}

	/**
	 * 十六进制字符数组转换为对应的十六进制值数组(BCD) 输入：byte[] asc -- 给定的字符数组 int asc_len
	 * 给定的字符数组的长度 返回值：给定的字符数组对应的十六进制数组 例： "9aB" --> 0x09 0xAB
	 */
	public static byte[] asc2Bcd(byte[] ascii, int asc_len) {
		byte[] bcd = new byte[asc_len / 2];
		int j = 0;
		for (int i = 0; i < (asc_len + 1) / 2; i++) {
			bcd[i] = _asc2bcd(ascii[j++]);
			bcd[i] = (byte) (((j >= asc_len) ? 0x00 : _asc2bcd(ascii[j++])) + (bcd[i] << 4));
		}
		return bcd;
	}
	
	
	/**
	 * 左靠bcd码，右补零
	 */
	public static String toBcd( String src ){
		StringBuilder ret = new StringBuilder();
		if ( src!=null && src.length()>0 ) {
			byte[] temp = str2Bcd(src);
			try {
				ret.append( new String(temp, CHARSETNAME) );
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				ret.append( temp );
			}
		}
//		LogUtils.Log( "toBcd[" + (System.currentTimeMillis() - time) );
		return ret.toString();
	}
	
	/**
	 * 左靠bcd码，右补零
	 * 十六进制字符串 转换为对应的 十六进制值数组(BCD) 输入：String asc
	 * 给定的字符数组的长度 返回值：给定的字符数组对应的十六进制数组 例： "9aB" --> 0x09 0xAB
	 * 
	 */
	public static byte[] str2Bcd(String asc) {
		if ( asc == null ) {
			return null;
		}

		int len = asc.length();
		int mod = len % 2;
		if (mod != 0) {
			asc += "0";
			len = asc.length();
		}
		byte abt[] = new byte[len];
		if (len >= 2) {
			len = len / 2;
		}
		byte bbt[] = new byte[len];
		abt = asc.getBytes();
		int j, k;
		for (int p = 0; p < asc.length() / 2; p++) {
			if ((abt[2 * p] >= '0') && (abt[2 * p] <= '9')) {
				j = abt[2 * p] - '0';
			} else if ((abt[2 * p] >= 'a') && (abt[2 * p] <= 'z')) {
				j = abt[2 * p] - 'a' + 0x0a;
			} else {
				j = abt[2 * p] - 'A' + 0x0a;
			}
			if ((abt[2 * p + 1] >= '0') && (abt[2 * p + 1] <= '9')) {
				k = abt[2 * p + 1] - '0';
			} else if ((abt[2 * p + 1] >= 'a') && (abt[2 * p + 1] <= 'z')) {
				k = abt[2 * p + 1] - 'a' + 0x0a;
			} else {
				k = abt[2 * p + 1] - 'A' + 0x0a;
			}
			int a = (j << 4) + k;
			byte b = (byte) a;
			bbt[p] = b;
		}
		return bbt;
	}
	

	/**
	 * 将给定的十六进制字符串转换为对应的十六进制值字符串 输入：String s -- 给定的字符串
	 * 返回值：给定的十六进制字符串对应的十六进制数值字符串 例： "9aB" --> "090A0B"
	 */
	public static String toHexStr(String src) {
		return toHex(src, null);
	}

	/**
	 * 把打印出16进制内存值 如：“程”-->"0xB3 0xCC"
	 * @param src
	 * @param charsetName   编码格式，默认“GBK”
	 */
	public static String toHex(String src, String charsetName ){
		StringBuilder ret = new StringBuilder();
		if ( charsetName == null ){
			charsetName = CHARSETNAME;
		}

		try {
			byte[] b = src.getBytes(charsetName);
			for (int i = 0; i < b.length; i++) {
				ret.append( Integer.toString(( b[i] & 0xff ) + 0x100, 16).substring(1) ) ;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret.toString().toUpperCase();
	}

	/**
	 * 将指定字符串src，以每两个字符分割转换为16进制形式 如："2B44EFD9" --> byte[]{0x2B, 0x44, 0xEF,
	 * 0xD9}
	 * 
	 * @param src
	 *            String
	 * @return byte[]
	 */
	public static byte[] hex2Bytes(String src) {
		int len = src.length();
		byte[] ret = new byte[len / 2];
		byte[] tmp = src.getBytes();
		for (int i = 0; i < len / 2; i++) {
			ret[i] = uniteBytes(tmp[i * 2], tmp[i * 2 + 1]);
		}
		return ret;
	}

	/**
	 * 将两个ASCII字符合成一个字节； 如："EF"--> 0xEF
	 */
	public static byte uniteBytes(byte src0, byte src1) {
		byte _b0 = Byte.decode("0x" + new String(new byte[] { src0 }))
				.byteValue();
		_b0 = (byte) (_b0 << 4);
		byte _b1 = Byte.decode("0x" + new String(new byte[] { src1 }))
				.byteValue();
		byte ret = (byte) (_b0 ^ _b1);
		return ret;
	}

	/** 8位 异或 */
	public static byte[] XOR(byte[] edata, byte[] temp) {
		byte[] result = new byte[8];
		
		if ( edata!=null && temp!=null && edata.length>=8 && temp.length>=8 ) {
			
			for (int i = 0, j = result.length; i < j; i++) {
				result[i] = (byte) (edata[i] ^ temp[i]);
			}
		}
		
		return result;
	}

	public static String XORStr(String str1, String str2) {
		if ( str1 != null && str2 !=null ){
			return bcd2Str( XOR( hex2Bytes(str1) , hex2Bytes(str2)) );
		}
		return null;
	}
	
	/** 补全 */
	public static byte[] competion( byte [] input, int outLen, byte app ) {
		if( input != null ){
			int _inputLen = input.length;
			if( outLen > _inputLen ){
				byte [] ret = new byte[outLen];
				for (int i = 0; i < _inputLen; i++) {
					ret[i] = input[i];
				}
				for (int i = _inputLen; i < outLen; i++) {
					ret[i] = app;
				}
				
				return ret;
			}
		}
		return input;
	}

	
	/**
	 * 隐藏中间 3分之一 的数据
	 * @param input
	 */
	public static String getHint( String input ){
		StringBuilder ret = new StringBuilder();
		if( input != null ){
			int length = input.length();
			int length_1_3 = length/3;
			if (length_1_3 > 0)
			{
				ret.append( input.substring(0, length_1_3) );
				
				for (int i = 0; i < length-2*length_1_3; i++)
				{
					ret.append( "*" );
				}
				
				ret.append( input.substring( length - length_1_3 ) );
			}
		}
		return ret.toString();
	}
	
	public String toHex( int dataLen ){
		return Integer.toHexString( dataLen );
	}
	
	/**左对齐，右补空格*/
	public static String strToOutlen( String input , int outLen ) {
		StringBuilder ret = new StringBuilder();
		
		if( input != null ){
			ret.append( input );
		}
		for (int i = StrUtil.toHexStr(input).length()/2; i < outLen; i++) {
			ret.append(" ");
		}
		
//		Log.d("", "strToOutlen:" + ret.length());
		return ret.toString();
	}

	
	/**
	 * 十六进制表示长度(拓展)
	 * @param lenInt 长度值
	 * @param lenStrLen 输出字符长度
	 */
	public static String toLen16Str( int lenInt, int lenStrLen ) {
		StringBuilder ret = new StringBuilder();
		if ( lenInt > 0 ) {
			if ( lenStrLen < 0 ) {
				lenStrLen = -lenStrLen;
			}
			
			byte[] bytes = new byte[4];
			bytes[3] = (byte) lenInt;
			bytes[2] = (byte) (lenInt >> 8);
			bytes[1] = (byte) (lenInt >> 8 * 2);
			bytes[0] = (byte) (lenInt >> 8 * 3);
			
			String bytesStr = bcd2Str(bytes);
			int bytesLen = bytesStr.length();
			if ( bytesLen > lenStrLen ) {
				ret.append( bytesStr.substring( bytesLen - lenStrLen ) );
			}else {
				ret.append(bytesStr);
				for (int i = 0; i <  lenStrLen - bytesLen ; i++) {
					ret.append( "0" + ret );
				}
			}
		}
		return ret.toString();
	}
	
	/**
	 * 十进制表示长度
	 * @param lenInt 长度值
	 * @param lenStrLen 输出字符长度
	 */
	public static String toLen10Str( int lenInt, int lenStrLen ) {
		StringBuilder ret = new StringBuilder();
		if ( lenInt > 0 ) {
			if ( lenStrLen < 0 ) {
				lenStrLen = -lenStrLen;
			}
			
			String lenIntStr = "" + lenInt;
			int bytesLen = lenIntStr.length();
			if ( bytesLen > lenStrLen ) {
				ret.append( lenIntStr.substring( bytesLen - lenStrLen ) );
			}else {
				ret.append( lenIntStr );
				for (int i = 0; i <  lenStrLen - bytesLen ; i++) {
					ret.append( "0" + ret );
				}
			}
		}
		return ret.toString();
	}
	
	/**
	 * 分割字符串(默认)
	 * @param src 返回后半段
	 * @param index 分割下标
	 * @return 前半段
	 */
	public static String[] cutStr( String src, int index ) {
		String [] retString = new String[]{
			"",
			""
		};
		
		if ( src != null && src.length()>0 ) {
			if ( index > 0 ) {
				if ( index > src.length() ) {
					index = src.length() - 1;
				}
			}else {
				index = 0;
			}

			//无中文
			if ( getStrLen(src) == src.length() ){
				retString[0] = src.substring(0, index);
				retString[1] = src.substring(index);
			}else{
				retString[0] = substring( src,0, index);
				retString[1] = src.substring(retString[0].length());
			}
//			Log.d(tag, "retString[0]:" + retString[0]);
//			Log.d(tag, "retString[1]:" + retString[1]);
		}
		return retString;
	}

	public static String[] cutStrFile( String src, int index ) {
		String [] retString = new String[]{
				"",
				""
		};

		if ( src != null && src.length()>0 ) {
			if ( index > 0 ) {
				if ( index > src.length() ) {
					index = src.length() - 1;
				}
			}else {
				index = 0;
			}

			//无中文
			if ( getStrLen(src,BANK_FILE_ENCODING_TYPE) == src.length() ){
				retString[0] = src.substring(0, index);
				retString[1] = src.substring(index);
			}else{
				retString[0] = substring( src,0, index, BANK_FILE_ENCODING_TYPE);
				retString[1] = src.substring(retString[0].length());
			}
//			Log.d(tag, "retString[0]:" + retString[0]);
//			Log.d(tag, "retString[1]:" + retString[1]);
		}
		return retString;
	}

	public static String substring(String src, int beginIndex, int endIndex) {
		if ( src!=null ){
			byte[] bytes;
			try {
				bytes = src.getBytes(CHARSETNAME);
			} catch (UnsupportedEncodingException e) {
				bytes = src.getBytes();
			}
			try {
				return new String( bytes, beginIndex, endIndex, CHARSETNAME );
			} catch (UnsupportedEncodingException e) {
				return new String( bytes, beginIndex, endIndex );
			}
		}
		return "";
	}

    public static String substring(String src, int beginIndex, int endIndex, String ecode) {
        if ( src!=null ){
            byte[] bytes;
            try {
                bytes = src.getBytes(ecode);
            } catch (UnsupportedEncodingException e) {
                bytes = src.getBytes();
            }
            try {
                return new String( bytes, beginIndex, endIndex, ecode );
            } catch (UnsupportedEncodingException e) {
                return new String( bytes, beginIndex, endIndex );
            }
        }
        return "";
    }

	/**
	 * 截取字符串 (不区分大小写)
	 * @param src 源字符串
	 * @param tag 起始位标志
	 * @return 从tag到结束,如果找不到返回null
	 */
	public static String subByTag(String src, String tag){
		StringBuilder ret = new StringBuilder();
		if ( src != null && tag!=null && tag.length()>0 && tag.length()<src.length() ) {
			
			int index = src.toUpperCase().indexOf(tag.toUpperCase());
//			int index = src.toUpperCase().lastIndexOf(tag.toUpperCase());//20171110yibin发现密码返回错误帧

			if ( index >= 0 ) {
				ret.append( src.substring( index ) );
			}
		}
		return ret.toString();
	}
	/**
	 * 截取字符串 (不区分大小写)
	 * @param src 源字符串
	 * @param tagB 起始位标志
	 * @param tagE 结束位标志
	 * @return 从tagB到tagE之间的内容,如果找不到返回null
	 */
	public static String subByTag(String src, String tagB, String tagE){
		StringBuilder ret = new StringBuilder();
		if ( src != null && tagB!=null && tagE!=null ){
			int lenB = tagB.length();
			int lenE = tagE.length();
			if( lenB>0 && lenE>0 ) {
				int indexB = src.toUpperCase().indexOf(tagB.toUpperCase());
				int indexE = src.toUpperCase().indexOf(tagE.toUpperCase());
				
				if ( indexB>=0 && indexB<indexE && indexE<src.length() ) {
					ret.append( src.substring( indexB+lenB, indexE ) );
				}
			}
		}
		return ret.toString();
	}
	
	/**
	 * YYYYMMDD --> YYYY-MM-DD
	 * YYMMDD --> YY-MM-DD
	 * @param time
	 */
	public static String toYYYYMMDD( String time ) {
		if ( time != null && time.length()>0 ) {
			
			if ( time.length() >= 8 ) {
				return time.substring(0, 4) + "-" + time.substring(4, 6) + "-" + time.substring(6, 8);	
			}
			
			if ( time.length() == 6 ) {
				return time.substring(0, 2) + "-" + time.substring(2, 4) + "-" + time.substring(4, 6);	
			}
		}
		
		return "";
	}

	//20161109 by zhu

	/**
	 * 计算 10进制 长度
	 * @param src	原数据
	 * @param indexB	长度起始数据下标
	 * @param lenLen	长度所占字节数
     */
	public static long getLen10ByBytes( byte[] src, int indexB, int lenLen ){
		long realLen = 0;

		for (int j = 0; j < lenLen; j++) {
			realLen *= 100;
			int byt = (src[indexB + j] & 0xff);
			int c = ( (byt/ 16) * 10 + byt % 16);
			realLen += c;
		}
		return realLen;
	}

	/**
	 * 计算 16进制 长度
	 * @param src	原数据
	 * @param indexB	长度起始数据下标
	 * @param lenLen	长度所占字节数
	 */
	public static long getLen16ByBytes( byte[] src, int indexB, int lenLen ){
		long realLen = 0;

		for (int j = 0; j < lenLen; j++) {
			realLen *= 0x100;

			int byt = (src[indexB + j] & 0xff);

			System.out.println( "byt:" + byt );

			realLen += byt;

			System.out.println( "realLen:" + realLen );
		}

		return realLen;
	}

	// 提供外部使用end---------------------------------------------------------------------

	// 私有成员begin---------------------------------------------------------------------

	/**
	 * 将给定的十六进制字符转换为对应的十六进制值 输入：byte asc -- 给定的十六进制字符('0'-'9'、'a'-'f'、'A'-'F')
	 * 返回值：给定的十六进制字符对应的十六进制值 '0'-'9' --> 0x00-0x09 'a'-'f' --> 0x0A-0x0F 'A'-'F'
	 * --> 0x0A-0x0F 其他字符 -->
	 */
	private static byte _asc2bcd(byte asc) {
		byte bcd;

		if ((asc >= '0') && (asc <= '9'))
			bcd = (byte) (asc - '0');
		else if ((asc >= 'A') && (asc <= 'F'))
			bcd = (byte) (asc - 'A' + 10);
		else if ((asc >= 'a') && (asc <= 'f'))
			bcd = (byte) (asc - 'a' + 10);
		else
			bcd = (byte) (asc - 48);
		return bcd;
	}

	// 私有成员end---------------------------------------------------------------------

	//code by zhuwenquan on 2018/12/4 09:28
	public static int getInt( String iStr ){
		try {
			iStr = iStr.replace(" ", "");
			iStr = iStr.replace(",", "");
			return Integer.parseInt( iStr );
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 判断字符串是否为空
	 * PS:
	 * 为空的条件：
	 * 1. String对象为空
	 * 2. 没有任何字符的字符串
	 *
	 * @param str 需要判断的字符串
	 * @return 为空(true), 非空(false)
	 */
	public static boolean isEmpty(String str) {
		return null == str || "".equals(str);
	}

	/**
	 * 判断字符串是否为空
	 * PS:
	 * 为空的条件：
	 * 1. String对象为空
	 * 2. 没有任何字符的字符串
	 *
	 * @param str       需要判断的字符串
	 * @param isTrimmed 判断前是否去掉字符串前后的空格：是(true), 否(false)
	 * @return 为空(true), 非空(false)
	 */
	public static boolean isEmpty(String str, boolean isTrimmed) {
		return isTrimmed ? null == str || "".equals(str.trim()) : null == str || "".equals(str);
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
	 * 判断集合是否为空
	 * PS：
	 * 集合为空的条件：
	 * 1. 集合对象为null
	 * 2. 集合中没有元素
	 *
	 * @param collection 需要进行判断的集合
	 * @return 为空(true), 不为空(false)
	 */
	public static boolean isEmpty(Collection<?> collection) {
		return null == collection || collection.size() == 0;
	}

	/**
	 * 判断对象数组是否为空
	 * PS：
	 * 对象数组为空的条件：
	 * 1. 对象数组为null
	 * 2. 对象数组中没有元素
	 *
	 * @param array 需要进行判断的对象数组
	 * @return 为空(true), 不为空(false)
	 */
	public static boolean isEmpty(Object[] array) {
		return null == array || array.length == 0;
	}

	/**
	 * 判断数组是否为空
	 * PS：
	 * 数组为空的条件：
	 * 1. 数组为null
	 * 2. 数组中没有元素
	 *
	 * @param array 需要进行判断的数组
	 * @return 为空(true), 不为空(false)
	 */
	public static boolean isEmpty(long[] array) {
		return array == null || array.length == 0;
	}

	/**
	 * 判断数组是否为空
	 * PS：
	 * 数组为空的条件：
	 * 1. 数组为null
	 * 2. 数组中没有元素
	 *
	 * @param array 需要进行判断的数组
	 * @return 为空(true), 不为空(false)
	 */
	public static boolean isEmpty(int[] array) {
		return array == null || array.length == 0;
	}

	/**
	 * 判断数组是否为空
	 * PS：
	 * 数组为空的条件：
	 * 1. 数组为null
	 * 2. 数组中没有元素
	 *
	 * @param array 需要进行判断的数组
	 * @return 为空(true), 不为空(false)
	 */
	public static boolean isEmpty(short[] array) {
		return array == null || array.length == 0;
	}

	/**
	 * 判断数组是否为空
	 * PS：
	 * 数组为空的条件：
	 * 1. 数组为null
	 * 2. 数组中没有元素
	 *
	 * @param array 需要进行判断的数组
	 * @return 为空(true), 不为空(false)
	 */
	public static boolean isEmpty(char[] array) {
		return array == null || array.length == 0;
	}

	/**
	 * 判断数组是否为空
	 * PS：
	 * 数组为空的条件：
	 * 1. 数组为null
	 * 2. 数组中没有元素
	 *
	 * @param array 需要进行判断的数组
	 * @return 为空(true), 不为空(false)
	 */
	public static boolean isEmpty(byte[] array) {
		return array == null || array.length == 0;
	}

	/**
	 * 判断数组是否为空
	 * PS：
	 * 数组为空的条件：
	 * 1. 数组为null
	 * 2. 数组中没有元素
	 *
	 * @param array 需要进行判断的数组
	 * @return 为空(true), 不为空(false)
	 */
	public static boolean isEmpty(double[] array) {
		return array == null || array.length == 0;
	}

	/**
	 * 判断数组是否为空
	 * PS：
	 * 数组为空的条件：
	 * 1. 数组为null
	 * 2. 数组中没有元素
	 *
	 * @param array 需要进行判断的数组
	 * @return 为空(true), 不为空(false)
	 */
	public static boolean isEmpty(float[] array) {
		return array == null || array.length == 0;
	}

	/**
	 * 判断数组是否为空
	 * PS：
	 * 数组为空的条件：
	 * 1. 数组为null
	 * 2. 数组中没有元素
	 *
	 * @param array 需要进行判断的数组
	 * @return 为空(true), 不为空(false)
	 */
	public static boolean isEmpty(boolean[] array) {
		return array == null || array.length == 0;
	}


	//code by zhuwenquan on 2019/8/31 18:13
	public static String toFileStr(String src, String copyStr, int len){
		return toFileStr( src,  copyStr, len, true, BANK_FILE_ENCODING_TYPE);
	}
	public static String toFileStr(String src, String copyStr, int len,String ecode){
		return toFileStr( src,  copyStr, len, true, ecode);
	}
	
	public static String copyStrLeft(String src, String copyStr, int len) {
		return toFileStr( src,  copyStr, len, false, BANK_FILE_ENCODING_TYPE);
	}
	
	public static String copyStrLeft(String src, String copyStr, int len,String ecode) {
		return toFileStr( src,  copyStr, len, false, ecode);
	}

	/**
	 *
	 * @param src   源字符
	 * @param copyStr 填充字符
	 * @param len   最终长度
	 * @param isLeft    左靠右补全
	 */
	public static String toFileStr(String src, String copyStr,int len, boolean isLeft, String ecode){
		if ( src ==null ){
			src="";
		}
		if ( getStrLen(src, ecode)>len ){
			return src.substring(0, len);
		}else{
			len = len-getStrLen(src,ecode);
			if ( isLeft ){
				src += copy2Len(copyStr, len);
			}else {
				src = copy2Len(copyStr, len) + src;
			}
			return src;
		}
	}

	/**
	 * 目标字符拷贝
	 * @param copyStr
	 * @param len   最终输出长度
	 */
	private static String copy2Len(String copyStr, int len){
		StringBuilder builder = new StringBuilder();
		for ( int i=0; i<len; i++ ){
			builder.append( copyStr );
		}
		return builder.toString();
	}


	public static int getStrLen(String src, String encode){
		try {
			return src.getBytes(encode).length;
		} catch (UnsupportedEncodingException e) {
			return src.length();
		}
	}

	public static int getStrLen(String src){
		try {
			return src.getBytes(CHARSETNAME).length;
		} catch (UnsupportedEncodingException e) {
			return src.length();
		}
	}

	public static void main(String[] args) {
		System.out.println(copyStrLeft("20","0",10));
	}
}
