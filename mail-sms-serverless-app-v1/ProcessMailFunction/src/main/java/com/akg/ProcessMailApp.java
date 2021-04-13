package com.akg;

import java.util.List;

import com.akg.sns.message.SNSRequest;
import com.akg.to.EmailDetails;
import com.akg.util.SeDeMessage;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SNSEvent;
import com.amazonaws.services.lambda.runtime.events.SNSEvent.SNSRecord;
import com.fasterxml.jackson.core.type.TypeReference;

/**
 * Handler for requests to Lambda function.
 */
public class ProcessMailApp implements RequestHandler<SNSEvent, Void> {

	SeDeMessage<SNSRequest<EmailDetails>> sede=new SeDeMessage<SNSRequest<EmailDetails>>();

	@Override
	public Void handleRequest(SNSEvent event, Context context) {
		System.out.println("Entered ProcessMailApp : handleRequest");
		try {
		List<SNSRecord> list = event.getRecords();
		
		for(SNSRecord rec:list) {
			String msg = rec.getSNS().getMessage();
			SNSRequest<EmailDetails> obj = sede.getObject(msg, getSQSRequestT());
			System.out.println("Sending EMAIL to ........ "+obj.getData().getTo()+"  Message : "+obj.getData().getMessage());
		}
		}catch (Exception e) {
			System.out.println("Error : "+e);
		}
		System.out.println("Exited ProcessMailApp : handleRequest");
		return null;
		
	}
	
	
	private TypeReference getSQSRequestT() {
		return new TypeReference<SNSRequest<EmailDetails>>() {};
	}
	
}
