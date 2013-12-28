package com.wenbo.webpiao.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.wenbo.webpiao.model.User;
import com.wenbo.webpiao.service.UserService;

@RequestMapping("/")
@Controller
public class WebPiaoController {
	
	private static final Logger logger = LoggerFactory.getLogger(WebPiaoController.class);
	
	@Autowired
	private UserService userService;

	@ResponseBody
	@RequestMapping("index")
	public User index(){
		logger.info("request!time:"+new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("name","wenbo");
		jsonObject.put("name1","yangxi");
		return userService.getUser();
	}
}
