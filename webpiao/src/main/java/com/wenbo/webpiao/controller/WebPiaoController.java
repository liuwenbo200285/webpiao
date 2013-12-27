package com.wenbo.webpiao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

@RequestMapping("/")
@Controller
public class WebPiaoController {

	@ResponseBody
	@RequestMapping("index")
	public String index(){
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("name","wenbo");
		jsonObject.put("name1","yangxi");
		return jsonObject.toJSONString();
	}
}
