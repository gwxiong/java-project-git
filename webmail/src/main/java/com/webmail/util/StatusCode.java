package com.webmail.util;

public enum StatusCode {
	/*
	 * 200-299》》》请求成功
	 * 400-499 》》》客户端的错误
	 * 500-599 》》》服务器错误
	 */
	OK(200,"Success"),							//成功
	NOTHINGNESS(201,"Result does not exist"),	//结果不存在
	ECHO(202,"More than one"),					//数据不止一个
	NOT_MATCHING(203,"Inconsistent info")		//信息不匹配
	;
	private int code;		//状态码
	private String descript;	//描述
	private StatusCode(int code, String descript) {
		this.code = code;
		this.descript = descript;
	}
	public int getCode() {
		return code;
	}
	public String getDescript() {
		return descript;
	}
}
