/**
 * Copyright (c) 2013-2033 by takeseem.com
 * All rights reserved.
 * @author <a href="mailto:takeseem@gmail.com">takeseem.com</a>
 */
package com.takeseem.opensource.util;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

/**
 * 基于jackson json的工具集
 */
public class UtilJson {
	public static final ObjectMapper JSON_MAPPER = newObjectMapper();
	private static ObjectMapper newObjectMapper() {
		ObjectMapper result = new ObjectMapper();
		result.configure(JsonGenerator.Feature.QUOTE_FIELD_NAMES, true);
		result.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		result.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
		result.getSerializationConfig().withSerializationInclusion(Inclusion.NON_NULL);
		return result;
	}
	
	/** @see #JSON_MAPPER */
	public static ObjectMapper getObjectMapper() {
		return JSON_MAPPER;
	}
	/** @see #JSON_MAPPER_WEB */
	public static ObjectMapper getObjectMapperWeb() {
		return JSON_MAPPER;
	}
	
	/** @return value == null ? null : json串 */
	public static String writeValueAsString(Object value) {
		try {
			return value == null ? null : JSON_MAPPER.writeValueAsString(value);
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
	}
	
	public static String writeValueAsPrettyString(Object value) {
		try {
			return value == null ? null : JSON_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(value);
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
	}
}
