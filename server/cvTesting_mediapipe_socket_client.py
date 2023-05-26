# Import socket module
import socket			


s = socket.socket()	
port = 12349
s.connect(('127.0.0.1', port))

while(True):
    if (s.recv(1024).decode() == 'close'):
        s.close()
        print("End")





#https://569e-118-142-119-146.ngrok-free.app



# standard Python








#sio.connect("https://ive0001.r2c2.ai:3000")

#print('Mission List: ', missionList[-1])
#print("emited:", "/mission/start", missionList[-1])
#sio.disconnect()