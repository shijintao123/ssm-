package com.sjt.crm.model;

/**
 * Created by Administrator on 2019/4/16.
 */
public class ResultInfo {
    private Integer code=200;
    private  String msg="操作成功";
    private Object result;

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public ResultInfo(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public ResultInfo( String msg) {
        this.code = code;
    }
    public ResultInfo(Integer code) {
        this.code = code;
    }
    public ResultInfo() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "ResultInfo{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
