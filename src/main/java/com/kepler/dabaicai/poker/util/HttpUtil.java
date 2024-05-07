package com.kepler.dabaicai.poker.util;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * HttpUtil
 * <p>
 * <p><a href="HttpUtil.java.html"><i>View Source</i></a></p>
 *
 * @author <a href="mailto:afteryuan@gmail.com">Spires</a>
 * @version 1.0
 */
public class HttpUtil {
    private static Logger logger = LoggerFactory.getLogger(HttpUtil.class);

    public static String get(String url) {
        logger.info("请求地址GET：" + url);
        String result = "";
        HttpClient client = HttpClients.createDefault();
        HttpGet method = new HttpGet(url);
        HttpResponse res = null;
        try {
            res = client.execute(method);
        } catch (IOException e) {
            logger.error("服务器请求失败!", e);
        }
        if (res == null) {
            return result;
        }
        return response(res);

    }

    public static String delete(String url) {
        logger.info("请求地址DELETE：" + url);
        String result = "";
        HttpClient client = HttpClients.createDefault();
        HttpDelete method = new HttpDelete(url);
        HttpResponse res = null;
        try {
            res = client.execute(method);
        } catch (IOException e) {
            logger.error("服务器请求失败!", e);
        }
        if (res == null) {
            return result;
        }
        return response(res);

    }

    public static String postToHead(String url, String json, Map<String, String> params) {
        logger.info("请求地址POST：" + url);
        String result = "";
        HttpClient client = HttpClients.createDefault();
        HttpPost method = new HttpPost(url);
        method.setHeader("Content-Type", "application/json;charset=UTF-8");
        for (String p : params.keySet()){
            method.setHeader(p, params.get(p));
        }
        HttpResponse res = null;
        try {
            method.setEntity(new StringEntity(json.toString(), Charset.forName("UTF-8")));
            res = client.execute(method);
        } catch (IOException e) {
            logger.error("服务器请求失败!", e);
        }
        if (res == null) {
            return result;
        }

        return response(res);
    }

    public static String post(String url, String json) {
        logger.info("请求地址POST：" + url);
        String result = "";
        HttpClient client = HttpClients.createDefault();
        HttpPost method = new HttpPost(url);
        method.setHeader("Content-Type", "application/json;charset=UTF-8");
        HttpResponse res = null;
        try {
            method.setEntity(new StringEntity(json.toString(), Charset.forName("UTF-8")));
            res = client.execute(method);
        } catch (IOException e) {
            logger.error("服务器请求失败!", e);
        }
        if (res == null) {
            return result;
        }

        return response(res);
    }

    public static String post(String url, Map<String, String> params) {
        logger.info("请求地址POST：" + url);
        String result = "";
        HttpClient client = HttpClients.createDefault();
        HttpPost method = new HttpPost(url);
        List<NameValuePair> kv = new ArrayList<NameValuePair>();
        for (Map.Entry<String, String> es : params.entrySet()) {
            kv.add(new BasicNameValuePair(es.getKey(), es.getValue()));
        }
        HttpResponse res = null;
        try {
            method.setEntity(new UrlEncodedFormEntity(kv, "UTF-8"));
            res = client.execute(method);
        } catch (IOException e) {
            logger.error("服务器请求失败!", e);
        }
        if (res == null) {
            return result;
        }

        return response(res);
    }

    public static String put(String url, File file) {
        logger.info("请求地址PUT：" + url);
        String result = "";
        HttpClient client = HttpClients.createDefault();
        HttpPut method = new HttpPut(url);
        HttpResponse res = null;
        try {
            method.setEntity((new FileEntity(file)));
            res = client.execute(method);
        } catch (IOException e) {
            logger.error("服务器请求失败!", e);
        }
        if (res == null) {
            return result;
        }
        return response(res);
    }


    private static String response(HttpResponse res) {
        String result = "";
        HttpEntity entity = res.getEntity();
        if (entity != null) {
            logger.debug("Response content length: " + entity.getContentLength());
            // 打印响应内容
            try {
                result = EntityUtils.toString(entity);
            } catch (IOException e) {
                logger.error(e.getMessage(),e);
            }
        }
        return result;
    }

}
