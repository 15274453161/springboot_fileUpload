package com.qgh.spring_mvc.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @ClassName: RedisConfig
 * @Description: redis配置类 使用redis来实现缓存功能
 * @author liuwenfeng
 * @date 2020年1月6日 下午2:27:14
 * 
 */
@Configuration
public class RedisConfig extends CachingConfigurerSupport {
 
    @Value("${spring.redis.host}")
    private String host;
 
    @Value("${spring.redis.port}")
    private int port;
 
    @Value("${spring.redis.jedis.pool.max-active}")
    private int maxTotal;
 
    @Value("${spring.redis.jedis.pool.max-idle}")
    private int maxIdle;
 
    @Value("${spring.redis.jedis.pool.min-idle}")
    private int minIdle;
 
 /*   @Value("${spring.redis.password}")
    private String password;*/
 
    @Value("${spring.redis.timeout}")
    private int timeout;
 
    /**
     * <p>Description:配置redis连接池</p>
     * 
     * @return
     */
    @Bean
    public JedisPool redisPoolFactory() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(maxTotal);
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMinIdle(minIdle);
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout);
        return jedisPool;
    }
}