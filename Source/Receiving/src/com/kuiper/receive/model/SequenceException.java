package com.kuiper.receive.model;

public class SequenceException extends Exception {

	private static final long serialVersionUID = 1L;

	private String errCode;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private String errMsg;

	// get, set...
	public SequenceException(String errMsg) {
		this.errMsg = errMsg;
	}

}
