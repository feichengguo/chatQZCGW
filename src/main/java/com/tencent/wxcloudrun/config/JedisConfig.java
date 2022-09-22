package com.tencent.wxcloudrun.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Service
@Slf4j
public class JedisConfig {
    @Bean
    public JedisPoolConfig jedisPoolConfig(@Value("${jedis.pool.config.maxTotal}") int maxActive,
                                           @Value("${jedis.pool.config.maxIdle}") int maxIdle,
                                           @Value("${jedis.pool.config.minIdle}") int minIdle,
                                           @Value("${jedis.pool.config.maxWaitMillis}") long maxWaitMillis,
                                           @Value("${jedis.pool.config.testOnBorrow}") boolean testOnBorrow) {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(maxActive);
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMinIdle(minIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        jedisPoolConfig.setTestOnBorrow(testOnBorrow);
        return jedisPoolConfig;
    }

    @Bean("jedisPool")
    public JedisPool jedisPool(@Value("${jedis.pool.host}") String host,
                               @Value("${jedis.pool.password}") String password,
                               @Value("${jedis.pool.port}") int port,
                               @Value("${jedis.pool.timeout}") int timeout, JedisPoolConfig jedisPoolConfig) {
        log.info("=====创建JedisPool连接池=====");
        if (StringUtils.isNotEmpty(password)) {
            return new JedisPool(jedisPoolConfig, host, port, timeout, password);
        }
        return new JedisPool(jedisPoolConfig, host, port, timeout);
    }
}
