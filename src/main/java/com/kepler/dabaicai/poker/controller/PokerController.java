package com.kepler.dabaicai.poker.controller;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
}
