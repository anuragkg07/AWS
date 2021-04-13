package com.akg;

import java.util.List;

import com.akg.sns.message.SNSRequest;
import com.akg.to.SmsDetails;
import com.akg.util.SeDeMessage;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SNSEvent;
import com.amazonaws.services.lambda.runtime.events.SNSEvent.SNSRecord;
import com.fasterxml.jackson.core.type.TypeReference;

/**
 * Handler for requests to Lambda function.
 */
public class ProcessSMSApp implements RequestHandler<SNSEvent, Void> {

	SeDeMessage<SNSRequest<SmsDetails>> sede=new SeDeMessage<SNSRequest<SmsDetails>>();

	@Override
	public Void handleRequest(SNSEvent event, Context context) {
		System.out.println("Entered ProcessSMSApp : handleRequest");
		try {
		List<SNSRecord> list = event.getRecords();
		
		for(SNSRecord rec:list) {
			String msg = rec.getSNS().getMessage();
			SNSRequest<SmsDetails> obj = sede.getObject(msg, getSQSRequestT());
			System.out.println("Sending SMS to ........ "+obj.getData().getTo()+"  Message : "+obj.getData().getMessage());
		}
		}catch (Exception e) {
			System.out.println("Error : "+e);
		}
		System.out.println("Exited ProcessSMSApp : handleRequest");
		return null;
		
	}

	private TypeReference getSQSRequestT() {
		return new TypeReference<SNSRequest<SmsDetails>>() {};
	}
}