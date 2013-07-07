/**
 * Copyright (c) 2013-2033 by takeseem.com
 * All rights reserved.
 * @author <a href="mailto:takeseem@gmail.com">takeseem.com</a>
 */
package com.takeseem.opensource.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 为满足各种客户端的Ajax请求工具方法
 */
public class UtilAjax {
	
	public static Map<String, Object> sendFormResult(boolean result, Object data) {
		Map<String, Object> formResult = new HashMap<>();
		formResult.put("success", result);
		formResult.put("data", data);
		return formResult;
	}
}
