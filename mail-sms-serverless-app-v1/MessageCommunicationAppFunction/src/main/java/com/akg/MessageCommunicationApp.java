package com.akg;

import java.util.HashMap;
import java.util.Map;

import com.akg.sns.message.SNSRequest;
import com.akg.sns.util.AmazonSNSUtil;
import com.akg.util.SeDeMessage;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.amazonaws.services.sns.model.MessageAttributeValue;
import com.fasterxml.jackson.core.type.TypeReference;

/**
 * Handler for requests to Lambda function.
 */
public class MessageCommunicationApp {

	public static String SNS_TOPIC_ARN = System.getenv("SNS_TOPIC_ARN");
	AmazonSNSUtil snsUtil=new AmazonSNSUtil(); 	
	SeDeMessage<SNSRequest> sede=new SeDeMessage<SNSRequest>();
	
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent request) {
    	System.out.println("Entered MessageCommunicationApp handleRequest : " + SNS_TOPIC_ARN);
    	APIGatewayProxyResponseEvent response= new APIGatewayProxyResponseEvent();
    	try {
    	System.out.println(request);
    	SNSRequest snsRequest = sede.getObject(request.getBody(), this.getSQSRequestT());
    	
    	//Attribute to filter 
		MessageAttributeValue mav=new MessageAttributeValue();
		mav.setDataType("String");
		mav.setStringValue(snsRequest.getMsgType());
		
		Map<String, MessageAttributeValue> attribute= new HashMap<String, MessageAttributeValue>();
		attribute.put("msgType", mav);
    	
    	
    	snsUtil.pubTopic(request.getBody(), SNS_TOPIC_ARN, attribute);				
		
		response.withStatusCode(201);	
		response.withBody("status : 201");		
		System.out.println("Exited MessageCommunicationApp handleRequest");
    	}catch (Exception e) {
    		System.out.println("Exception In  MessageCommunicationApp handleRequest");
			System.out.println(e);
		}
		return response;
    }

	private TypeReference getSQSRequestT() {
		return new TypeReference<SNSRequest>() {};
	}

}
