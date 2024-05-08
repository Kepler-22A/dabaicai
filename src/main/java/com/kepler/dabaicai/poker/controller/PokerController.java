package com.kepler.dabaicai.poker.controller;

import com.alibaba.fastjson.JSONObject;
import com.kepler.dabaicai.poker.dto.ResultBaseVO;
import com.kepler.dabaicai.poker.dto.TestVO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
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
    public ResultBaseVO tellMeSex(HttpServletRequest request){
        String sex = request.getParameter("sex");
        ResultBaseVO resultBaseVO = new ResultBaseVO(ResultBaseVO.CODE_SUCCESS, ResultBaseVO.MESSAGE_SUCCESS);
        if (StringUtils.isBlank(sex)){
            resultBaseVO.setCode(ResultBaseVO.CODE_FAILED);
            resultBaseVO.setMessage(ResultBaseVO.MESSAGE_FAILED);
            resultBaseVO.setData("你没有告诉我性别！");
        }else {
            resultBaseVO.setData("好的，你的性别是："+sex);
        }
        return resultBaseVO;
    }

    @RequestMapping(method = RequestMethod.POST, value = "tell-me-name-post")
    @ResponseBody
    public ResultBaseVO tellMeNamePost(@RequestBody TestVO testVO){
        String name = testVO.getName();
        ResultBaseVO resultBaseVO = new ResultBaseVO(ResultBaseVO.CODE_SUCCESS, ResultBaseVO.MESSAGE_SUCCESS);
        if (StringUtils.isBlank(name)){
            resultBaseVO.setCode(ResultBaseVO.CODE_FAILED);
            resultBaseVO.setMessage(ResultBaseVO.MESSAGE_FAILED);
            resultBaseVO.setData("你没有告诉我名字！");
        }else {
            resultBaseVO.setData("好的，你的名字是："+name);
        }
        return resultBaseVO;
    }

    @RequestMapping(method = RequestMethod.POST, value = "tell-me-age-post")
    @ResponseBody
    public ResultBaseVO tellMeAgePost(@RequestBody TestVO testVO){
        Integer age = testVO.getAge();
        ResultBaseVO resultBaseVO = new ResultBaseVO(ResultBaseVO.CODE_SUCCESS, ResultBaseVO.MESSAGE_SUCCESS);
        if (null == age){
            resultBaseVO.setCode(ResultBaseVO.CODE_FAILED);
            resultBaseVO.setMessage(ResultBaseVO.MESSAGE_FAILED);
            resultBaseVO.setData("你没有告诉我年龄！");
        }else {
            resultBaseVO.setData("好的，你的年龄是："+age);
        }
        return resultBaseVO;
    }


    @RequestMapping(method = RequestMethod.POST, value = "tell-user-data")
    @ResponseBody
    public ResultBaseVO tellUserData(@RequestBody TestVO testVO){
        Integer height = testVO.getHeight();
        String sex = testVO.getSex();
        Integer age = testVO.getAge();
        String name = testVO.getName();
        ResultBaseVO resultBaseVO = new ResultBaseVO(ResultBaseVO.CODE_SUCCESS, ResultBaseVO.MESSAGE_SUCCESS);
        if (null == height){
            resultBaseVO.setCode(ResultBaseVO.CODE_FAILED);
            resultBaseVO.setMessage(ResultBaseVO.MESSAGE_FAILED);
            resultBaseVO.setData("你没有告诉我身高！");
            return resultBaseVO;
        }
        if (StringUtils.isBlank(sex)){
            resultBaseVO.setCode(ResultBaseVO.CODE_FAILED);
            resultBaseVO.setMessage(ResultBaseVO.MESSAGE_FAILED);
            resultBaseVO.setData("你没有告诉我性别！");
            return resultBaseVO;
        }
        if (null == age){
            resultBaseVO.setCode(ResultBaseVO.CODE_FAILED);
            resultBaseVO.setMessage(ResultBaseVO.MESSAGE_FAILED);
            resultBaseVO.setData("你没有告诉我年龄！");
            return resultBaseVO;
        }
        if (StringUtils.isBlank(name)){
            resultBaseVO.setCode(ResultBaseVO.CODE_FAILED);
            resultBaseVO.setMessage(ResultBaseVO.MESSAGE_FAILED);
            resultBaseVO.setData("你没有告诉我名字！");
            return resultBaseVO;
        }

        resultBaseVO.setData("好的，你叫："+name+"，性别："+sex+"，年龄："+age+"岁，身高："+height+"公分");
        return resultBaseVO;
    }
}