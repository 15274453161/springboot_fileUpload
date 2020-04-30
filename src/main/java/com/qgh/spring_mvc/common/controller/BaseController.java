package com.qgh.spring_mvc.common.controller;

import com.alibaba.fastjson.JSON;
import com.qgh.spring_mvc.common.util.Constants;
import com.qgh.spring_mvc.common.util.HttpUtils;
import com.qgh.spring_mvc.common.util.Interface;
import com.qgh.spring_mvc.common.vo.Result;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpStatus;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 控制器基类
 *
 * @author tan
 *
 */
@Slf4j
public abstract class BaseController {


	protected int pageno;
	protected int pagesize;
	protected HttpServletRequest request;
	protected HttpServletResponse response;

	public static String RESULT_MESSAGE_SUCCESS = "200"; // 成功
	public static String RESULT_MESSAGE_ERROR = "0"; // 失败
	public static String RESULT_MESSAGE_NOAUTH = "403"; // 权限不足

	public static String TEMPLATE_SUCCESS_MESSAGE = "操作成功";
	public static String TEMPLATE_ERROR_MESSAGE = "服务器异常，请稍后再试！";
	public static String PARAM_NULL_ERROR_MESSAGE = "参数不能为空！";
	public static String TEMPLATE_NORESULT_MESSAGE = "暂无数据！";
	public static String TEMPLATE_NOAPIURL_MESSAGE = "未找到对应接口地址！";
	public static String TEMPLATE_NOAUTH_MESSAGE = "请先登录！";

	public static String RESULT_STATUS_CODE_KEY = "statusCode！";
	public static String RESULT_MESSAGE_KEY = "message！";
	public static String RESULT_DATA_KEY = "data！";



	@ModelAttribute
	public void set(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.pagesize = getIntParameter("pagesize", 15);
		this.pageno = getIntParameter("pageno", 1);
	}

	public void set(HttpServletRequest request, HttpServletResponse response, Object obj) {
		this.set(request, response);
	}

	public Result result(String result, String message) {
		return result(result, message, null);
	}

	public Result result(String result, String message, Object obj) {
		return new Result(result, message, obj);
	}

	public Result ok(long total, String message, Object obj) {
		return result(RESULT_MESSAGE_SUCCESS, total, message, obj);
	}

	public Result ok(String message, Object obj) {
		return result(RESULT_MESSAGE_SUCCESS, message, obj);
	}

	public Result error(String message, Object obj) {
		return result(RESULT_MESSAGE_ERROR, message, obj);
	}

	public static Result error(String message) {
		return new Result(RESULT_MESSAGE_ERROR, message, null);
	}

	public Result result(String result, long total, String message, Object obj) {
		return new Result(result, total, message, obj);
	}

	public Result result(String result, String message, String message1) {
		return new Result(result, message, message1);
	}


	/**
	 * 接口统一请求方法（post方式）
	 * @Author :dongxiaoyong
	 * @Date :2020/3/17 20:15
     * @param url : 访问接口路径
	 * @param json : json传参
	 * @param request :
     * @return : com.tecsun.sisp.applets.common.vo.Result
	*/
	public static Result doPostWithJsonResult(String url, JSONObject json, HttpServletRequest request) {
		//拼接接口地址根目录
		url = Interface.getInstance().get("baseApiUrl") + url;
        Map<String,String> headers = new HashMap<>();
        javax.servlet.http.Cookie[] cookies = request.getCookies();
        if(null != cookies && cookies.length > 0){
            for(javax.servlet.http.Cookie cookie : cookies){
                //核心系统登录返回ticket（登录后返回到cookie中ticket）
                if("ticket".equals(cookie.getName())){
                    headers.put("ticket",cookie.getValue());
                }
                //用户id
                if("userId".equals(cookie.getName())){
                    headers.put("userId",cookie.getValue());
                }
				//用户类型：1=业务部门，2=个人账户
				if("accountType".equals(cookie.getName())){
					headers.put("accountType",cookie.getValue());
				}
            }
        }

		String jsonResultString = HttpUtils.doPostWithJson( url,json.toString(),headers);
		log.info("======接口 {} ; ==== 响应结果：{}",url,jsonResultString);
		if(StringUtils.isNotBlank(jsonResultString)){
			return JSON.parseObject(jsonResultString, Result.class);
		}else{
			return null;
		}
	}


	/**
	 *  接口统一请求方法（get方式）
	 * @Author :dongxiaoyong
	 * @Date :2020/3/17 20:17
     * @param url : 访问接口路径
     * @param request :
     * @return : com.tecsun.sisp.applets.common.vo.Result
	*/
	public static Result doGetResult(String url, HttpServletRequest request) {
		//拼接接口地址根目录
		url = Interface.getInstance().get("baseApiUrl") + url;
		Map<String,String> headers = new HashMap<>();
		if(null != request){
			javax.servlet.http.Cookie[] cookies = request.getCookies();
			if(null != cookies && cookies.length > 0){
				for(javax.servlet.http.Cookie cookie : cookies){
					//核心系统登录返回ticket（登录后返回到cookie中ticket）
					if("ticket".equals(cookie.getName())){
						headers.put("ticket",cookie.getValue());
					}
					//用户id
					if("userId".equals(cookie.getName())){
						headers.put("userId",cookie.getValue());
					}
					//用户类型：1=业务部门，2=个人账户
					if("accountType".equals(cookie.getName())){
						headers.put("accountType",cookie.getValue());
					}
				}
			}
		}
		String jsonResultString = HttpUtils.doGet(url,null,headers);
		log.info("======接口 {}==== ; 响应结果：{}",url,jsonResultString);
		if(StringUtils.isNotBlank(jsonResultString)){
			return JSON.parseObject(jsonResultString, Result.class);
		}else{
			return null;
		}
	}

	/**
	 * 接口统一请求方法（get方式）
	 * @Author :dongxiaoyong
	 * @Date :2020/3/17 20:17
     * @param url : 访问接口路径
	 * @param params : 请求参数
     * @param request :
     * @return : com.tecsun.sisp.applets.common.vo.Result
	*/
	public static Result doGetResult(String url, Map<String, String> params, HttpServletRequest request) {
		//拼接接口地址根目录
		url = Interface.getInstance().get("baseApiUrl") + url;
        Map<String,String> headers = new HashMap<>();
        javax.servlet.http.Cookie[] cookies = request.getCookies();
        if(null != cookies && cookies.length > 0){
            for(javax.servlet.http.Cookie cookie : cookies){
                //核心系统登录返回ticket（登录后返回到cookie中ticket）
                if("ticket".equals(cookie.getName())){
                    headers.put("ticket",cookie.getValue());
                }
                //用户id
                if("userId".equals(cookie.getName())){
                    headers.put("userId",cookie.getValue());
                }
				//用户类型：1=业务部门，2=个人账户
				if("accountType".equals(cookie.getName())){
					headers.put("accountType",cookie.getValue());
				}
            }
        }
		String jsonResultString = HttpUtils.doGet(url,params,headers);
		log.info("======接口 {}==== ; 响应结果：{}",url,jsonResultString);
		if(StringUtils.isNotBlank(jsonResultString)){
			return JSON.parseObject(jsonResultString, Result.class);
		}else{
			return null;
		}
	}


	/**
	 * 业务部门账号登录，并且把核心系统在cookie中返回的ticket获取出来
	 * @Author :dongxiaoyong
	 * @Date :2020/3/10 10:32
     * @param url :
     * @param params :
     * @return : java.util.Map<java.lang.String,java.lang.Object>
	*/
	public static Map<String,Object> login(String url, Map<String, String> params) throws Exception {
		Map<String,Object> map = new HashMap<>();
		String jsonResultString = "";
		String ticket = "";
		Result result = null;
		//1、拼接接口地址根目录
		url = Interface.getInstance().get("baseApiUrl") + url;
		CookieStore cookieStore = new BasicCookieStore();
		CloseableHttpClient httpClient = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
		//2.构建一个URI对象,根据url及参数
		URIBuilder uriBuilder = new URIBuilder(url);
		//2.1请求参数
		if (params != null && params.size() > 0) {
			for (String key : params.keySet()) {
				uriBuilder.addParameter(key, params.get(key));
			}
		}
		URI uri = uriBuilder.build();
		//3.创建一个get请求
		HttpGet httpGet = new HttpGet(uri);
		//4.执行get请求,得到响应
		CloseableHttpResponse response = httpClient.execute(httpGet);
		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			//5.从响应对象中获取响应数据
			jsonResultString = EntityUtils.toString(response.getEntity(), "UTF-8");
			if(StringUtils.isNotBlank(jsonResultString)){
				result = JSON.parseObject(jsonResultString,Result.class);
				if(null != result && Constants.RESULT_MESSAGE_SUCCESS.equals(result.getStatusCode())){
					JSONObject dataJsonObject = JSONObject.fromObject(result.getData());
					if(null != dataJsonObject){
						map.put("userId",dataJsonObject.get("userId"));
					}
				}
			}
		}
		List<Cookie> cookies = cookieStore.getCookies();
		if(null != cookies && cookies.size() > 0){
			for(Cookie cookie : cookies){
				if("ticket".equals(cookie.getName())){
					ticket = cookie.getValue();
				}
			}
		}
		map.put("result",result);
		map.put("ticket",ticket);
		//登录账号类型，1=业务部门
		map.put("accountType","1");
		return map;
	}

	public int getIntParameter(String key, int defaultValue) {
		if (key != null && !"".equals(key)) {
			String str = request.getParameter(key);
			if (str != null && !"".equals(str)) {
				if (request.getParameter(key).matches("[0-9]+")) {
					return Integer.parseInt(request.getParameter(key));
				}
			}
		}
		return defaultValue;
	}

	/**
	 * 调核心系统接口，校验是否登录
	 * @Author :dongxiaoyong
	 * @Date :2020/3/9 15:26
	 * @param ticket :
	 * @return : com.tecsun.sisp.applets.common.vo.Result
	 */
	public static Result checkLogin(String  ticket) {
		if(StringUtils.isBlank(ticket)){
			return error(PARAM_NULL_ERROR_MESSAGE);
		}
		String apiUrl = Interface.getInstance().get("checkLogin");
		if (StringUtils.isBlank(apiUrl)) {
			return error(TEMPLATE_NOAPIURL_MESSAGE);
		}else{
			apiUrl = apiUrl.replace("{ticket}",ticket);
			Result interfaceResult = doGetResult(apiUrl, null,null);
			if(null != interfaceResult){
				return interfaceResult;
			}else{
				return error(TEMPLATE_ERROR_MESSAGE);
			}
		}
	}



	/**
	 * 个人账号登录，并且把核心系统在cookie中返回的ticket获取出来
	 * @Author :dongxiaoyong
	 * @Date :2020/3/25 9:56
	  * @param url :
	 * @param jsonObject :
	  * @return : java.util.Map<java.lang.String,java.lang.Object>
	*/
	public static Map<String,Object> personAccountLogin(String url, JSONObject jsonObject) throws Exception{
		Map<String,Object> map = new HashMap<>();
		String jsonResultString = "";
		String ticket = "";
		Result result = null;
		//1、拼接接口地址根目录
		url = Interface.getInstance().get("baseApiUrl") + url;
		CookieStore cookieStore = new BasicCookieStore();
        CloseableHttpClient httpClient = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
		HttpPost httpPost = new HttpPost(url);
		//2、封装post请求参数
		int size = jsonObject.size();
		if (jsonObject != null && size > 0) {
			StringEntity entity = new StringEntity(JSON.toJSONString(jsonObject), ContentType.APPLICATION_JSON);
			httpPost.setEntity(entity);
			//3、执行post请求
			CloseableHttpResponse response = null;
			response = httpClient.execute(httpPost);
			//4.从响应对象中获取响应数据
			jsonResultString = EntityUtils.toString(response.getEntity(), "UTF-8");
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				if (StringUtils.isNotBlank(jsonResultString)) {
					result = JSON.parseObject(jsonResultString, Result.class);
					if (null != result && Constants.RESULT_MESSAGE_SUCCESS.equals(result.getStatusCode())) {
						JSONObject dataJsonObject = JSONObject.fromObject(result.getData());
						if (null != dataJsonObject) {
							map.put("accountId", dataJsonObject.get("accountId"));
						}
					}
				}
			}
		}
		List<Cookie> cookies = cookieStore.getCookies();
		if(null != cookies && cookies.size() > 0){
			for(Cookie cookie : cookies){
				if("ticket".equals(cookie.getName())){
					ticket = cookie.getValue();
				}
			}
		}
		map.put("result",result);
		map.put("ticket",ticket);
		//登录账号类型，2=个人账户
		map.put("accountType","2");
		return map;
	}

    /***
    * 西藏卡管Post调用
     * @param url
     * @param json
     * @param request
    * @return com.tecsun.sisp.applets.common.vo.Result
    * @date 2020/4/23  17:20
    */

	public static Result doPostWithJsonResultCard(String url, JSONObject json, HttpServletRequest request) {
		//拼接接口地址根目录 西藏卡管根目录
		url = Interface.getInstance().get("cardBaseApiUrl") + url;
		Map<String,String> headers = new HashMap<>();
		//设置卡管账号、密码
		json.put("name", "yth");
		json.put("pwd", "tec@002908");
		String jsonResultString = HttpUtils.doPostWithJson( url,json.toString(),headers);
		log.info("======接口 {} ; ==== 响应结果：{}",url,jsonResultString);
		if(StringUtils.isNotBlank(jsonResultString)){
			return JSON.parseObject(jsonResultString, Result.class);
		}else{
			return null;
		}
	}

    /***
    * 西藏卡管Get调用
     * @param url
     * @param request
    * @return com.tecsun.sisp.applets.common.vo.Result
    * @date 2020/4/24  9:33
    */

	public static Result doGetResultCard(String url, HttpServletRequest request) {
		//拼接接口地址根目录
		url = Interface.getInstance().get("cardBaseApiUrl") + url;
		Map<String,String> headers = new HashMap<>();
		Map<String,String> params = new HashMap<>();
		String groupId=request.getParameter("groupId");
		if (StringUtils.isNotBlank(groupId)){
			params.put("groupId",groupId);
		}
		String jsonResultString = HttpUtils.doGet(url,params,headers);
		log.info("======接口 {}==== ; 响应结果：{}",url,jsonResultString);
		if(StringUtils.isNotBlank(jsonResultString)){
			return JSON.parseObject(jsonResultString, Result.class);
		}else{
			return null;
		}
	}

}
