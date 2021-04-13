package com.akg.to;

import java.io.Serializable;

public class SmsDetails implements Serializable {

	private Long to;
	private String message;

	public Long getTo() {
		return to;
	}

	public void setTo(Long to) {
		this.to = to;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "SmsDetails [to=" + to + ", message=" + message + "]";
	}

}
