# sms-mail-serverless-app-v1

This project contains following components.

`Lambda Function`
- MessageCommunicationAppFunction - Function to push data to SNS.
- ProcessMailFunction - Function to process Email Request.
- ProcessSMSFunction - Function to process SMS Request.

`API Gateway`
- EmailApiGateway - To expose MessageCommunicationAppFunction as RestFull service.

`SNS Topic`
- MessageCommunicationTopic - This receives messages from MailAppFunction.


`SAM Templete`
- template.yaml - This contains defination, configuration and integration of AWS services.


## Commands

To build and deploy  application:

```bash
sam build
sam deploy --guided
```

To delete AWSCloudFormation Stack:

```bash
aws cloudformation delete-stack --stack-name sms-mail-app-stack-east
```

