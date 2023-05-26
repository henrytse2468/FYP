import imp
import os
from twilio.rest import Client

#account_sid = os.environ["TWILIO_ACCOUNT_SID"]
#auth_token = os.environ["TWILIO_ACCOUNT_SID"]
account_sid = 'ACa79385f0f6c3caf934d310730e1d5233'
auth_token = 'c84329f91f4c6a164053150d9fd3f0ab'

client = Client(account_sid, auth_token)

call = client.calls.create(
    #to = os.environ['MY_PHONE_NUMBER'],
    to = '+85261608786',
    from_ = '+16076012512',
    url = 'http://demo.twilio.com/docs/voice.xml'
)

print(call.sid)
