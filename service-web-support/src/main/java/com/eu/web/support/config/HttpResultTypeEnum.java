/**
 * 
 */
package com.eu.web.support.config;

/**
 * @author xufeng
 *
 */
public enum HttpResultTypeEnum {
	/** 处理成功 */
	success,
//	/**条件不满足，没有结果可以返回*/
//	no_result,
	/**未授权  HttpStatus.UNAUTHORIZED*/
	unauthorized,
	/**校验不通过  HttpStatus.PRECONDITION_FAILED */
	validate,
	/** 内部错误   HttpStatus.INTERNAL_SERVER_ERROR */
	internal,
	/**  HttpStatus.EXPECTATION_FAILED */
	unexpected,
//	/** 参数校验不通过 HttpStatus.PRECONDITION_FAILED*/
//	validate_param_fail,
//	/** 权限校验不通过 */
//	validate_auth_fail,
//	/** 内部错误，比如内部数据不完整，数据冲突 */
//	internal_error;
}
