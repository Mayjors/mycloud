package com.eu.util.result;

import java.io.Serializable;
import java.text.MessageFormat;

/**
 * 
 * @author xufeng
 * @since 2015年6月3日
 */
public class ResultMessage implements Serializable {

	private static final long serialVersionUID = -2350298598585751484L;
	
	/**
     * 国际化配置 key
     */
    private String code = RESULT_SUCCESS;
    /**
     * 国际化信息参数
     */
    private transient Object[] args;
    
    /** 未处理的消息 */
    private String message;
    
    /** 成功 */
    public static final String RESULT_SUCCESS = "0";

    public static final String RESULT_FAIL = "-1";
    
    
    /** 返回去的值， 调用者自定义其意义 */
    private Object value;

    public ResultMessage() {
    	this.code = ResultMessage.RESULT_SUCCESS;
    }

    public ResultMessage(String code) {
        this.code = code;
    }
    public ResultMessage(String code, String message) {
        this.code = code;
        this.message = message;
      
    }
    
    
    public ResultMessage(String code, String message, Object... args) {
        this.code = code;
        this.message = message;
        this.args = args;
    }
    
    
    public void setMessage(String message,Object... args) {
        this.message = message;
        this.args = args;
    }
    
    public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Object[] getArgs() {
		return args;
	}

	public void setArgs(Object... args) {
		this.args = args;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

//	public String getShowMessage() {
//		return showMessage;
//	}
//
//	public void setShowMessage(String showMessage) {
//		this.showMessage = showMessage;
//	}

//	public String getType() {
//		return type;
//	}
//
//	public void setType(String status) {
//		this.type = status;
//	}

	public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public String toString(){
        if(message != null &&  message != "" && args != null && args.length > 0){
            if(message.contains("{}")){
              int i = 0;
              while( message.contains("{}") )  {
                  message = message.replace("{}", "{" + i++  +"}");
              }
            }
            return "{code:" + code+",message:" + MessageFormat.format(message,args)+ (value == null ? "" : ",value:" + value) +"}";
        }
        else{
            return "{code:" + code+",message:" + message+ (value == null ? "" : ",value:" + value ) +"}";
        }
    }
}
