/**
 * Copyright (c) 2013-2033 by takeseem.com
 * All rights reserved.
 * @author <a href="mailto:takeseem@gmail.com">takeseem.com</a>
 */
package com.takeseem.opensource.util;

import java.util.Date;

/**
 * 相等判断工具方法
 */
public class UtilEquals {
	
	/** 使用语言提供的算法比较 */
	public static boolean equals(Object obj1, Object obj2) {
		if (obj1 == obj2) return true;
		if (obj1 == null || obj2 == null) return false;
		if (obj1 instanceof Date && obj2 instanceof Date) return ((Date) obj1).getTime() == ((Date) obj2).getTime();
		return obj1.equals(obj2);
	}
}
