package com.wenbo.webpiao.redis;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

@Service
public class RedisCacheManger implements RedisCache {  
	
	private static final Logger logger = LoggerFactory.getLogger(RedisCacheManger.class);
    
	@Autowired
    private ShardedJedisPool pool ;  
  
    @SuppressWarnings("unchecked")
	public String getRedisCacheInfo(String key) {  
        try {  
        	logger.info("get from redisCache :"+key);  
            ShardedJedis jedis = pool.getResource();  
            pool.returnResource(jedis);  
            return (String)jedis.get(key);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return null;  
    }  
    
    
    public void getRedisCacheKeys(){
    	Collection<JedisShardInfo> jedisShardInfos = pool.getResource().getAllShardInfo();
    	for(JedisShardInfo jedisShardInfo:jedisShardInfos){
    		logger.info("host:"+jedisShardInfo.getHost());
    		logger.info("port:"+jedisShardInfo.getPort());
    		logger.info("password:"+jedisShardInfo.getPassword());
    		Set<String> keys = jedisShardInfo.createResource().keys("*");
    		Iterator<String> iterator = keys.iterator();
    		while(iterator.hasNext()){
    			String key = iterator.next();
    			logger.info("key:"+key+",value:"+getRedisCacheInfo(key));
    		}
    	}
    }
   
  
    public <T> boolean setRedisCacheInfo(String key, T value) {  
        try {  
            logger.info("add to redisCache :"+key);  
            ShardedJedis jedis = pool.getResource();  
            jedis.set(key, (String)value);
            pool.returnResource(jedis);  
            return true;  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return false;  
    }  
    public static void main(String[] args) {  
        new RedisCacheManger().setRedisCacheInfo("12345", "asdfg");  
    }  
}
