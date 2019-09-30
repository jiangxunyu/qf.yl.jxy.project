package com.qf.dto;

import java.io.Serializable;

//数据传输对象
public class ResultBean<T> implements Serializable {

    //单例模式
    private static ResultBean resultBean = new ResultBean();

    private Integer errno;
    private T data;
    private String message;

    public static ResultBean error(){
        resultBean.setData(null);
        resultBean.setErrno(1);
        resultBean.setMessage("失败");
        return resultBean;
    }

    public static ResultBean error(String message){
        resultBean.setData(null);
        resultBean.setErrno(1);
        resultBean.setMessage(message);
        return resultBean;
    }

    public static ResultBean success(){
        resultBean.setData(null);
        resultBean.setErrno(0);
        resultBean.setMessage("成功");
        return resultBean;
    }

    public static ResultBean success(String message){
        resultBean.setData(null);
        resultBean.setErrno(0);
        resultBean.setMessage(message);
        return resultBean;
    }

    public static ResultBean success(Object data,String message){
        resultBean.setErrno(0);
        resultBean.setData(data);
        resultBean.setMessage(message);
        return resultBean;
    }

    public Integer getErrno() {
        return errno;
    }

    public void setErrno(Integer errno) {
        this.errno = errno;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
