import imp
import os
from twilio.rest import Client

#account_sid = os.environ["TWILIO_ACCOUNT_SID"]
#auth_token = os.environ["TWILIO_ACCOUNT_SID"]
account_sid = 'ACa79385f0f6c3caf934d310730e1d5233'
auth_token = '3773284f1e250e2058ebf0e980b26fe6'

client = Client(account_sid, auth_token)

call = client.calls.create(
    #to = os.environ['MY_PHONE_NUMBER'],
    to = '+85261608786',
    from_ = '+18653452334',
    url = 'http://demo.twilio.com/docs/voice.xml'
)

print(call.sid)