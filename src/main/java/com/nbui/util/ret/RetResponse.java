package com.nbui.util.ret;

import com.nbui.constant.RetCode;

/**
 * @Description: 将结果转换为封装后的对象
 * @author
 * @date 2018/4/19 09:45
 */
public class RetResponse {

    private final static String SUCCESS = "success";

    public static <T> RetResult<T> makeOKRsp() {
        return new RetResult<T>().setCode(RetCode.SUCCESS).setMsg(SUCCESS);
    }

    /**
     * >一般用这个方法,如果真的有人来看源码的话
     * 
     * @param data 返回的对象或者其他什么东西
     * @return
     */
    public static <T> RetResult<T> makeOKRsp(T data) {
        return new RetResult<T>().setCode(RetCode.SUCCESS).setMsg(SUCCESS).setData(data);
    }

    public static <T> RetResult<T> makeErrRsp(String message) {
        return new RetResult<T>().setCode(RetCode.FAIL).setMsg(SUCCESS);
    }

    public static <T> RetResult<T> makeRsp(int code, String msg) {
        return new RetResult<T>().setCode(code).setMsg(msg);
    }

    public static <T> RetResult<T> makeRsp(int code, String msg, T data) {
        return new RetResult<T>().setCode(code).setMsg(msg).setData(data);
    }
}