package com.qgh.spring_mvc.common.util.page;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.util.Properties;

/**
 * @author 秦光泓
 * @title:
 * @projectName spring_mvc_8
 * @description: TODO
 * @date 2020/6/1710:40
 */

/***
*
 MyInterceptor类上我们用@Intercepts标记了这是一个Interceptor，
 然后在@Intercepts中定义了两个@Signature，即两个拦截点。
 第一个@Signature我们定义了该Interceptor将拦截Executor接口中参数类型为
 MappedStatement、Object、RowBounds和ResultHandler的query方法；
 第二个@Signature我们定义了该Interceptor将拦截StatementHandler中参数类型为Connection的prepare方法。
*/
//@Component
@Intercepts({
        @Signature(method = "query", type = Executor.class, args = {
                MappedStatement.class, Object.class, RowBounds.class,
                ResultHandler.class}),
        @Signature(method = "prepare", type = StatementHandler.class, args = {Connection.class,Integer.class})})
public class MyInterceptor implements Interceptor {
    private static final Logger logger= LoggerFactory.getLogger(Interceptor.class);
    /***
    *3
    */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object result = invocation.proceed();
        System.out.println("Invocation.proceed()");
        logger.warn(invocation.toString());
        return result;
    }

    @Override
    public Object plugin(Object target) {
        /***
        *2
        *plugin方法中我们是用的Plugin的逻辑来实现Mybatis的逻辑的。
        */
        return Plugin.wrap(target, this);
    }

/***
* 1
 * Configuration初始化当前的Interceptor时就会执行setProperties方法
 * @param properties
* @return void
* @date 2020/6/17  10:45
*/
    @Override
    public void setProperties(Properties properties) {
        String prop1 = properties.getProperty("prop1");
        String prop2 = properties.getProperty("prop2");
        System.out.println(prop1 + "------" + prop2);
    }
}
/***
*
 对于这个拦截器，Mybatis在注册该拦截器的时候就会利用定义好的n个property作为参数调用该拦截器的setProperties方法。
 之后在新建可拦截对象的时候会调用该拦截器的plugin方法来决定是返回目标对象本身还是代理对象。对于这个拦截器而言，
 当Mybatis是要Executor或StatementHandler对象的时候就会返回一个代理对象，其他都是原目标对象本身。
 然后当Executor代理对象在执行参数类型为MappedStatement、Object、RowBounds和ResultHandler的query方法
 或StatementHandler代理对象在执行参数类型为Connection的prepare方法时就会触发当前的拦截器的intercept方法进行拦截，
 而执行这两个接口对象的其他方法时都只是做一个简单的代理。
 ==========================================================================================================
 注册拦截器是通过在Mybatis配置文件中plugins元素下的plugin元素来进行的。
 一个plugin对应着一个拦截器，在plugin元素下面我们可以指定若干个property子元素。
 Mybatis在注册定义的拦截器时会先把对应拦截器下面的所有property通过Interceptor的setProperties方法注入给对应的拦截器。
 所以，我们可以这样来注册我们在前面定义的MyInterceptor：
*/
//============springboot 中让自定义的MyInterceptor拦截器生效===============
/***
       方法一
  直接给自定义拦截器添加一个 @Component注解。
  当调用sql时结果如下，可以看到拦截器生效了，但是启动时候并没有自动调用setProperties方法。
*/

/***
        方法二
  在配置类里添加拦截器，这种方法结果同上，也不会自动调用setProperties方法。
  @Configuration
  public class MybatisConfig {
      @Bean
      ConfigurationCustomizer mybatisConfigurationCustomizer() {
          return new ConfigurationCustomizer() {
              @Override
              public void customize(org.apache.ibatis.session.Configuration configuration) {
                  configuration.addInterceptor(new MyPageInterceptor());
              }
          };
      }
  }
 */

/***
     方法三
 这种方法就是跟以前的配置方法类似，
 在yml配置文件中指定mybatis的xml配置文件,注意config-location属性和configuration属性不能同时指定
 mybatis:
 config-location: classpath:mybatis.xml
 type-aliases-package: me.zingon.pagehelper.model
 mapper-locations: classpath:mapper/*.xml
 mybatis.xml

 <?xml version="1.0" encoding="UTF-8" ?>
 <!DOCTYPE configuration PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
 "http://mybatis.org/dtd/mybatis-3-config.dtd">
 <configuration>
 <typeAliases>
 <package name="me.zingon.pacargle.model"/>
 </typeAliases>
 <plugins>
 <plugin interceptor="me.zingon.pagehelper.interceptor.MyPageInterceptor">
 <property name="dialect" value="oracle"/>
 </plugin>
 </plugins>
 </configuration>
*/

/**
            总结
前两种方法可以在初始化自定义拦截器的时候通过 @Value 注解直接初始化需要的参数。
*/

/***
 Mybatis拦截器只能拦截四种类型的接口：Executor、StatementHandler、ParameterHandler和ResultSetHandler。
 这是在Mybatis的Configuration中写死了的，如果要支持拦截其他接口就需要我们重写Mybatis的Configuration。
 Mybatis可以对这四个接口中所有的方法进行拦截。

 下面将介绍一个Mybatis拦截器的实际应用。Mybatis拦截器常常会被用来进行分页处理。
 我们知道要利用JDBC对数据库进行操作就必须要有一个对应的Statement对象，
 Mybatis在执行Sql语句前也会产生一个包含Sql语句的Statement对象，而且对应的Sql语句是在Statement之前产生的，
 所以我们就可以在它成Statement之前对用来生成Statement的Sql语句下手。
 在Mybatis中Statement语句是通过RoutingStatementHandler对象的prepare方法生成的。
 所以利用拦截器实现Mybatis分页的一个思路就是拦截StatementHandler接口的prepare方法，
 然后在拦截器方法中把Sql语句改成对应的分页查询Sql语句，之后再调用StatementHandler对象的prepare方法，
 即调用invocation.proceed()。更改Sql语句这个看起来很简单，而事实上来说的话就没那么直观，
 因为包括sql等其他属性在内的多个属性都没有对应的方法可以直接取到，它们对外部都是封闭的，是对象的私有属性，
 所以这里就需要引入反射机制来获取或者更改对象的私有属性的值了。对于分页而言，
 在拦截器里面我们常常还需要做的一个操作就是统计满足当前条件的记录一共有多少，这是通过获取到了原始的Sql语句后，
 把它改为对应的统计语句再利用Mybatis封装好的参数和设置参数的功能把Sql语句中的参数进行替换，
 之后再执行查询记录数的Sql语句进行总记录数的统计。先来看一个我们对分页操作封装的一个实体类Page：
*/




