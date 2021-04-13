package com.akg.sns.message;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonPropertyOrder({ "msgId", "msgGroupId", "msgType", "data" })
@JsonRootName(value = "request")
public class SNSRequest<T> implements Serializable {
	
	protected T data;
	protected String msgId;
	protected String msgGroupId;
	protected String msgType;

	public SNSRequest() {
		super();
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public String getMsgGroupId() {
		return msgGroupId;
	}

	public void setMsgGroupId(String msgGroupId) {
		this.msgGroupId = msgGroupId;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	@Override
	public String toString() {
		return "SQSRequest [data=" + data + ", msgId=" + msgId + ", msgGroupId=" + msgGroupId + ", msgType=" + msgType
				+ "]";
	}


	
	/*
	{
   "request":{
      "msgId":"MyMsgId1",
      "msgGroupId":"MyGroupId1",
      "data":{
         "map":{
            "message":"Hi, How are you?",
            "toEmail":"akg@gmail.com",
            "fromEmail":"noReply@gmail.com"
         }
      }
   }
}
	*/
	
}
