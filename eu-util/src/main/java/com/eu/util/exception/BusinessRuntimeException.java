package com.example.util.exception;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 业务运行时异常(unchecked exception)
 * 
 * @author xufeng
 * @version 1.0
 * @since 2017.7.10
 * 
 */
public class BusinessRuntimeException extends RuntimeException {

	protected static Logger LOG = LoggerFactory.getLogger(BusinessRuntimeException.class);
	
    private static final long serialVersionUID = 746264109957384560L;


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
    private transient  Object[] params;
    
    /**打印在控制台上的消息*/
    private String consoleMessage;
    
    public BusinessRuntimeException(){
    	super();
    }
    
    /**
     * 构造函数
     * 
     * @param errCode 异常代码
     * @param errMsg 异常消息
     */
    public BusinessRuntimeException(String errCode, String errMsg) {
        this(errCode, errMsg, (Object[]) null);
    }
    
    /**
     * 构造函数
     * 
     * @param errCode 异常代码
     */
    public BusinessRuntimeException(String errCode) {
        this(errCode, null, (Object[]) null);
    }

    public BusinessRuntimeException(String errCode, String errMsg, Object... params) {
    	this("",errCode,errMsg,params);
    }
    
    private String getErrorType(String errCode){
    	return "";
    }
    
    /**
     * 构造函数
     * @param errCode 异常代码
     * @param errMsg 异常消息
     * @param params 消息参数
     */
    public BusinessRuntimeException(String status, String errCode, String errMsg, Object... params) {
    	this.type = status;
        this.errCode = errCode;
        this.errMsg = errMsg;
        this.params = params;
    }


    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;//ArrayUtils.clone(params);
    }
    
    public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


    private String getErrMessage(){
        if(consoleMessage == null){
            int len = 0;
            if(params != null && params.length > 0){
                len += params.length;
            }
            if(len == 0 && errCode == null){
                return "";
            }
            Object[] newparams = new Object[len+1];
            if(errCode != null){
                newparams[0] = errCode;
            }
            if(params != null && params.length > 0){
                System.arraycopy(params, 0, newparams, 1, params.length);
            }
            String m = "errCode:{},"+errMsg;//params;
            for(Object obj : newparams){
                if(m.contains("{}")){
                    m = m.replaceFirst("\\{\\}",obj == null ? "" : obj.toString());
                }
            }
            consoleMessage = m;
        }
        return consoleMessage;
    }

	@Override
    public String getMessage() {
        String message = null;
        if (errCode == null || errCode.equals("")) {
            message = getErrMessage();
        } else {
            message = this.errMsg;
        }
        BusinessExceptionDTO businessExceptionDTO = new BusinessExceptionDTO(type, errCode, message, params);
        return JSON.toJSONString(businessExceptionDTO);
    }
}

