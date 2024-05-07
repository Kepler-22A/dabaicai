package com.kepler.dabaicai.poker.controller;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@RequestMapping(value = "poker")
@Controller
public class PokerController {
    private final Logger logger = LoggerFactory.getLogger(PokerController.class);

    @RequestMapping(value = "index")
    @ResponseBody
    public String index(HttpServletRequest request){
        String json = null;
        try {
            json = "{\"name\":\"name\nasfawf\nawegaer\"}";
        }catch (Exception e){
            logger.error("转换json失败！");
        }

        JSONObject jo = JSONObject.parseObject(json);

        for (String key : jo.keySet()){
            System.out.println(jo.get(key));
        }
        return "{\"retCode\":1,\"errMsg\":\"转换成功！\"}";
    }

    @RequestMapping(method = RequestMethod.GET, value = "tell-me-sex")
    @ResponseBody
    public String tellMeSex(HttpServletRequest request){
        String sex = request.getParameter("sex");
        if (StringUtils.isBlank(sex)){
            return "你没有告诉我性别！";
        }
        return "好的，你的性别是："+sex;
    }

    @RequestMapping(method = RequestMethod.POST, value = "tell-me-age-post")
    @ResponseBody
    public String tellMeAgePost(HttpServletRequest request){
        String sex = request.getParameter("age");
        if (StringUtils.isBlank(sex)){
            return "你没有告诉我年龄！";
        }
        return "好的，你的年龄是："+sex;
    }
}
