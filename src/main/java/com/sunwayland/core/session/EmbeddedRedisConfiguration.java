package com.sunwayland.core.session;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.Protocol; 
@Configuration
public class EmbeddedRedisConfiguration {

    @Bean
    public RedisServerBean redisServer() {
        return new RedisServerBean();
    }

    class RedisServerBean implements InitializingBean, DisposableBean {
       
    	 
    	
    	private RedisServer redisServer;


        @Override
        public void afterPropertiesSet() throws Exception {
            redisServer = new RedisServer(Protocol.DEFAULT_PORT);
            redisServer.start();
        }

        @Override
        public void destroy() throws Exception {
            if(redisServer != null) {
                redisServer.stop();
            }
        }
    }
}