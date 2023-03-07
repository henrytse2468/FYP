from datetime import datetime

current_date_and_time = datetime.now().time()

print("The current date and time is", current_date_and_time)
print("The current date and time is", datetime.utcnow().time())

def is_time_between(begin_time, end_time, check_time=None):
    # If check time is not given, default to current UTC time
    check_time = check_time or datetime.utcnow().time()
    if begin_time < end_time:
        return check_time >= begin_time and check_time <= end_time
    else: # crosses midnight
        return check_time >= begin_time or check_time <= end_time

# Original test case from OP
#is_time_between(time(10,30), time(16,30))

# Test case when range crosses midnight
#is_time_between(time(22,0), time(4,00))

while True:
    #get nearest timeslot state
    response = requests.get("")
    #if nearest timeslot state == occpuied
    #then emit start