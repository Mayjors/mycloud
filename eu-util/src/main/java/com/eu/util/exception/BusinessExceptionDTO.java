package com.example.util.exception;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yuanjie
 * @date 2018/7/7 15:58
 */
@Data
public class BusinessExceptionDTO implements Serializable {
    private static final long serialVersionUID = 7863924373914613828L;

    private String type;

    /**
     * 异常代码<br/>
     * 用于定位一个或一类异常，从资源文件中查找异常信息返回给用户
     */
    private String errCode;

    /**
     * 异常消息<br/>
     *
     * 这个异常消息只用于输出日志，或者在API里使用，返回给用户的消息通过errCode查找资源文件获取
     */
    private String errMsg;

    /**
     * 消息参数<br/>
     *
     * 传递异常消息需要的参数
     */
    private  transient  Object[] params;

    public BusinessExceptionDTO() {
    }

    public BusinessExceptionDTO(String status, String errCode, String errMsg, Object... params) {
        this.type = status;
        this.errCode = errCode;
        this.errMsg = errMsg;
        this.params = params;
    }
}
