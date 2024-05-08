package com.kepler.dabaicai.poker.dto;

public class ResultBaseVO {
    private String code;
    private String message;
    private String data;

    public final static String CODE_SUCCESS = "200";
    public final static String CODE_FAILED = "500";

    public final static String MESSAGE_SUCCESS = "操作成功！";
    public final static String MESSAGE_FAILED = "操作失败！";

    public ResultBaseVO(String c, String m){
        this.code = c;
        this.message = m;
    }

    public ResultBaseVO(){
        super();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
