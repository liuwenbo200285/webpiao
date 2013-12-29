package com.wenbo.webpiao.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.wenbo.webpiao.mapper.UserMapper;
import com.wenbo.webpiao.model.User;
import com.wenbo.webpiao.redis.RedisCache;

@Service
public class UserService {
	private Logger logger  = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private RedisCache redisCache;
	
	public void save(){
		logger.info("exec save...");
	}
	
	public User getUser(){
		logger.info("select user....");
		User user = userMapper.selectByPrimaryKey(1);
		if(user != null){
			logger.info(user.getUsername());
			redisCache.setRedisCacheInfo(user.getUsername(),JSON.toJSONString(user));
			user = JSON.parseObject((String)redisCache.getRedisCacheInfo(user.getUsername()),User.class);
			return user;
		}
		return null;
	}
}
