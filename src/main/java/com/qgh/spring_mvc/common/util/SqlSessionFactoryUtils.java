package com.qgh.spring_mvc.common.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.InputStream;

/**
 * @author 秦光泓
 * @title:创建一个单例模式的SqlSessionFactory工程
 * @projectName spring_mvc_8
 * @description: TODO
 * @date 2020/6/1911:42
 */
public class SqlSessionFactoryUtils {
    /**私有的静态变量*/
    private static volatile SqlSessionFactory sqlSessionFactory;
    /**类锁*/
    private static final Class<SqlSessionFactoryUtils> CLASS_LOCk = SqlSessionFactoryUtils.class;
    /**私有的构造函数 */
    private SqlSessionFactoryUtils(){}
    private static final Logger logger = LoggerFactory.getLogger(SqlSessionFactoryUtils.class);

    /***
    * 构建SqlSessionFactory
    * @return
    * @date 2020/6/19  13:15
    */
    public static SqlSessionFactory initSqlSessionFactory(){
        String resource = "mybatis-config.xml";
        InputStream is = null;
         try {
               is = Resources.getResourceAsStream(resource);
               synchronized (CLASS_LOCk){
                   if (sqlSessionFactory == null){
                       sqlSessionFactory = new  SqlSessionFactoryBuilder().build(is);
                   }
               }
               /**关闭流*/
               is.close();
         }catch (Exception e){
             logger.error(e.getMessage());
         }
         return sqlSessionFactory;
    }
   /***
   * 打开SqlSession
   * @return
   * @date 2020/6/19  13:22
   */

   public static SqlSession openSqlSession(){
       if (sqlSessionFactory == null){
           initSqlSessionFactory();
       }
       return sqlSessionFactory.openSession();
   }
}
