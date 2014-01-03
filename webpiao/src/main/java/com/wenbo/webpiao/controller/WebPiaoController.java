package com.wenbo.webpiao.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.wenbo.webpiao.model.User;
import com.wenbo.webpiao.redis.RedisCache;
import com.wenbo.webpiao.service.UserService;

@RequestMapping("/")
@Controller
public class WebPiaoController {
	
	private static final Logger logger = LoggerFactory.getLogger(WebPiaoController.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RedisCache redisCache;

	@ResponseBody
	@RequestMapping("index")
	public User index(){
		logger.info("request!time:"+new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("name","wenbo");
		jsonObject.put("name1","yangxi");
		return userService.getUser();
	}
	
	@ResponseBody
	@RequestMapping("set")
	public String set(HttpServletRequest request){
		logger.info("request!time:"+new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
		String key = request.getParameter("key");
		if(StringUtils.isNotBlank(key)){
			if(redisCache.setRedisCacheInfo(key,key)){
				return "{\"message\":\"ok\"}";
			}
		}
		return "{\"message\":\"fail\"}";
	}
	
	@ResponseBody
	@RequestMapping("get")
	public String get(HttpServletRequest request){
		logger.info("request!time:"+new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
		String key = request.getParameter("key");
		if(StringUtils.isNotBlank(key)){
			return redisCache.getRedisCacheInfo(key);
		}
		return "{\"message\":\"fail\"}";
	}
	

	@RequestMapping("test")
	public String test(HttpServletRequest request){
		logger.info("request!time:"+new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
		return "test";
	}
}
