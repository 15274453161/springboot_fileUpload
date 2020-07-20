package com.qgh.spring_mvc.datasource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 秦光泓
 * @title:
 * @projectName spring_mvc_8
 * @description: TODO
 * @date 2020/7/918:04
 */
//@Configuration
public class DataSourceConfig {
    private static final Logger logger = LoggerFactory.getLogger(DataSourceConfig.class);
   // @Bean(name = "primaryDataSource")
    @Qualifier("primaryDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.base")
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create().build();
    }

  //  @Bean(name = "secondaryDataSource")
    @Qualifier("secondaryDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.slave")
    public DataSource secondaryDataSource() {
        logger.info("secondaryDataSource===========");
        return DataSourceBuilder.create().build();
    }

    /**
     * 获取动态数据源
     * @return
     */
  //  @Bean(name = "dynamicDataSource")
    public DataSource dynamicDataSource() {
        logger.info("dynamicDataSource===========");
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        // 设置默认数据源为first数据源
        dynamicDataSource.setDefaultTargetDataSource(primaryDataSource());
        // 配置多数据源,
        // 添加数据源标识和DataSource引用到目标源映射
        Map<Object, Object> dataSourceMap = new HashMap<>();
        /**key  value的形式*/
        dataSourceMap.put("firstAopDataSource", primaryDataSource());
        dataSourceMap.put("secondAopDataSource", secondaryDataSource());
        dynamicDataSource.setTargetDataSources(dataSourceMap);
        return dynamicDataSource;
    }
    /***
    * 将数据源注册到事务管理器中
    * @return org.springframework.transaction.PlatformTransactionManager
    * @date 2020/7/9  18:13
    */
  //  @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dynamicDataSource());
    }



}
