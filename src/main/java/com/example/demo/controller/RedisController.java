package com.example.demo.controller;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.utils.RedisUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/redis")
public class RedisController {
	private static int ExpireTime = 60;
	
	@Resource
	private RedisUtil redisUtil;
	
	@RequestMapping("set")
	public boolean redisset(String key , String value) {
		User user = new User();
		user.setId(Long.valueOf(1));
		user.setName("lyz");
		user.setAge(String.valueOf(22));
		user.setGuid(String.valueOf(1));
		user.setCreateTime(new Date());
		return redisUtil.set(key, value);
	}
	
	@RequestMapping("get")
    public Object redisget(String key){
        return redisUtil.get(key);
    }

    @RequestMapping("expire")
    public boolean expire(String key){
        return redisUtil.expire(key,ExpireTime);
    }
    
    @RequestMapping("setGet")
    @Transactional
    public Object setGet(String key, String value){
    	try {
	    	redisUtil.set(key, value);
	    	redisUtil.get(key);
    	}catch(Exception e){
    		return "发生异常了：" + e.getMessage();
    	}
        return redisUtil.get(key);
    }
    
    @RequestMapping("hmget")
    public Map<Object , Object> hmget(String key){
    	return redisUtil.hmget(key);
    }
}
