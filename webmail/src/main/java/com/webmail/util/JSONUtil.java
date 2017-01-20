package com.webmail.util;

import net.sf.json.JSONObject;

public final class JSONUtil {
	
	/**
	 * 信息
	 * @return
	 */
	public static String writeMessage(StatusCode StatusCode,String message){
		JSONObject jo = new JSONObject();
		jo.put("status", StatusCode.getCode());
		jo.put("descript", StatusCode.getDescript());
		jo.put("message", message);
		return jo.toString();
	}

	/**
	 * 数据
	 * @return
	 */
	public static String writeData(StatusCode StatusCode,Object data){
		JSONObject jo = new JSONObject();
		jo.put("status", StatusCode.getCode());
		jo.put("descript", StatusCode.getDescript());
		jo.put("data", data);
		return jo.toString();
	}
}
