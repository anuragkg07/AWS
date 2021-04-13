package com.akg.sns.util;

import java.util.Map;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.MessageAttributeValue;
import com.amazonaws.services.sns.model.PublishRequest;

public class AmazonSNSUtil {

	private static AmazonSNS snsClient = AmazonSNSClientBuilder.defaultClient();

	public static void pubTopic( String message, String topicArn, Map<String, MessageAttributeValue> attribute) {		
		PublishRequest request = new PublishRequest();
		request.setMessage(message);
		request.setTopicArn(topicArn);
		request.setMessageAttributes(attribute);
		snsClient.publish(request);
	}
}
