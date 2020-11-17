package com.sys.manage.common.exception;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.UUID;

/**
 * 描述 : rest响应对象
 **/
@Data
@ToString
public class RestResponse<T> implements Serializable {

    /**
     * 描述 : id
     */
    private static final long serialVersionUID = 1L;

    /**
     * 描述 : 响应ID
     */
    private String id = UUID.randomUUID().toString();

    /**
     * 描述 : 状态码(业务定义)
     */
    private Integer code = HttpStatus.OK.value();

    /**
     * 描述 : 状态码描述(业务定义)
     */
    private String msg = HttpStatus.OK.getReasonPhrase();

    /**
     * 描述 : 结果集(泛型)
     */
    private T result = null; //NOSONAR

    /**
     * 描述 : 错误
     */
    private ErrorResult error = null;

    /**
     * 描述 : 构造函数
     */
    public RestResponse() {
        super();
    }

    /**
     * 描述 : 构造函数
     *
     * @param result 结果集(泛型)
     */
    public RestResponse(T result) {
        super();
        this.result = result;
    }

    /**
     * 描述 : 构造函数
     *
     * @param code  http状态
     * @param error 错误
     */
    public RestResponse(Integer code, ErrorResult error) {
        super();
        this.code = code;
        this.error = error;
    }

    /**
     * 描述 : 构造函数
     *
     * @param code    状态码(业务定义)
     * @param msg 状态码描述(业务定义)
     * @param error   错误
     */
    public RestResponse(Integer code, String msg, ErrorResult error) {
        super();
        this.code = code;
        this.msg = msg;
        this.error = error;
    }

    /**
     * 描述 : 构造函数
     *
     * @param code    状态码(业务定义)
     * @param msg 状态码描述(业务定义)
     */
    public RestResponse(Integer code, String msg) {
        super();
        this.code = code;
        this.msg = msg;
    }

    /**
     * 描述 : 构造函数
     *
     * @param code    状态码(业务定义)
     * @param msg 状态码描述(业务定义)
     * @param result  结果集(泛型)
     */
    public RestResponse(Integer code, String msg, T result) {
        super();
        this.code = code;
        this.msg = msg;
        this.result = result;
    }
    //静态构造器开始

    /**
     * 构造成功返回结果-只包含提示信息
     *
     * @param msg
     * @return
     */
    public static RestResponse success(String msg) {
        RestResponse<String> restResponse = new RestResponse<>();
        restResponse.setMsg(msg);
        return restResponse;
    }
    /**
     * 构造成功返回结果-只包含默认提示信息
     *
     * @return
     */
    public static RestResponse success() {
        RestResponse<String> restResponse = new RestResponse<>();
        restResponse.setMsg("OK");
        return restResponse;
    }

    /**
     * 构造成功返回结果-单个提示信息或者结果
     *
     * @param result
     * @param singleStrResult
     * @return
     */
    public static RestResponse success(String result, Boolean singleStrResult) {
        RestResponse<String> restResponse = new RestResponse<>();
        restResponse.setMsg(result);
        if (singleStrResult) {
            restResponse.setResult(result);
        }
        return restResponse;
    }

    /**
     * 构造成功返回结果-结果
     *
     * @param result
     * @return
     */
    public static <T> RestResponse success(T result) {
        RestResponse<T> restResponse = new RestResponse<>();
        restResponse.setResult(result);
        return restResponse;
    }

    /**
     * 构造成功返回结果-提示信息和结果
     *
     * @param msg
     * @param result
     * @return
     */
    public static <T> RestResponse success(String msg, T result) {
        RestResponse<T> restResponse = new RestResponse<>();
        restResponse.setMsg(msg);
        restResponse.setResult(result);
        return restResponse;
    }

    /**
     * 构造失败返回结果-提示信息
     *
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> RestResponse error(String msg) {
        RestResponse<T> restResponse = new RestResponse<>();
        restResponse.setMsg(msg);
        restResponse.setCode(HttpStatus.BAD_REQUEST.value());
        return restResponse;
    }

    /**
     * 构造失败返回结果-提示信息和错误码
     *
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> RestResponse error(Integer code, String msg) {
        RestResponse<T> restResponse = new RestResponse<>();
        restResponse.setMsg(msg);
        restResponse.setCode(code);
        return restResponse;
    }
    //静态构造器结束

    public String toJSONStr(){
        return JSONObject.toJSONString(this);
    }
}
