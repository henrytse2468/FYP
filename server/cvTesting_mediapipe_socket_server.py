import fcntl
import os
import cv2
import socketio
import mediapipe as mp
import numpy as np
import time
import socket
import subprocess
import json

def non_block_read(output):
    fd = output.fileno()
    fl = fcntl.fcntl(fd, fcntl.F_GETFL)
    fcntl.fcntl(fd, fcntl.F_SETFL, fl | os.O_NONBLOCK)
    try:
        return output.read()
    except:
        return ""


def calculate_angle(a,b,c):
        a = np.array(a) # First
        b = np.array(b) # Mid
        c = np.array(c) # End
        
        radians = np.arctan2(c[1]-b[1], c[0]-b[0]) - np.arctan2(a[1]-b[1], a[0]-b[0])
        angle = np.abs(radians*180.0/np.pi)
        
        if angle >180.0:
            angle = 360-angle
            
        return angle 
false = 0
no_object = 0
true = 0
face_track_status = True

emitted_pause = False
liftControl_output = None

missionList = {}
firstTrue = False
task2 = False
task3 = False
playingState = ""
sockethaha = socket.socket()
print("Socket successfully created")
port = 12349
sockethaha.bind(('', port))
print ("socket binded to %s" %(port))



sio = socketio.Client()



@sio.event
def connect():
    print('Connected! my sid is', sio.sid)
    

@sio.on('/mission/playing/state') 
def catchComplete(data):
    global false 
    global no_object 
    global true

    global face_track_status
    global liftControl_output
    global firstTrue
    global task2
    global task3
    global playingState
    
    playingState = data
    if (data == "PAUSE" and face_track_status == True):
        print("now emit restart")
        sio.emit("/mission/pause/config", {"data" : "RE_START"})

    if(data == "START"):
        firstTrue = True
    if(firstTrue == True) and (data == "STOP") and (task2 == False):
        print("completed task 1")
        firstTrue = False
        task2 = True
        # lift control module here
        proc = subprocess.Popen("node /var/www/html/aiortc/lift_control/socket.js", 
              shell=True, stdout=subprocess.PIPE)
        proc.wait()
        # output = output.decode()
        # output = json.loads(output)

        # output.wait()
        print(non_block_read(proc.stdout).decode())
        # if liftControl_output is None:
        #     liftControl_output = subprocess.check_output("node ./lift_control/socket.js", shell=True)


        #emit task 2 here
        #sio.emit("/mission/start", missionList[-1])
        #print(missionList[-1])
    if(firstTrue == True) and (data == "STOP") and (task2 == True):
        print("completed task 2")
        firstTrue = False
        task3 = True
        #emit task 3 hemissionList = {}
        #sio.emit("/mission/start", missionList[-1])false = 0
        
        print(missionList[-1])

    if(firstTrue == True) and (data == False) and (task3 == True):
        print("completed task 3")
        #emit task 4 here
        #sio.emit("/mission/start", missionList[-1])
        print(missionList[-1])
        del sio.handlers['/']['/mission/playing/state']
        sio.disconnect()
    print(data)

@sio.on('/mission/list')
def on_missionList(data):
    global missionList
    print(data)
    if (data):
        del sio.handlers['/']['/mission/list']
        missionList = data["list"]
        #emit task 1 here
        # sio.emit("/mission/start", {'mission_id': 'Mission_1681899106', 'map_id': 'Map_1681899089'})
        #sio.emit("/mission/start",{'mission_id': 'Mission_1682585248', 'map_id': 'Map_1682585232'})
        sio.emit("/mission/start", {'mission_id': 'Mission_1682593195', 'map_id': 'Map_1682593178'})
        # print({'mission_id': 'Mission_1681899106', 'map_id': 'Map_1681899089'})
        # print({'mission_id': 'Mission_1682409468', 'map_id': 'Map_1682409450'})
        print({'mission_id': 'Mission_1682585248', 'map_id': 'Map_1682585232'})

        
        #time.sleep(5)missionList = {}
        #sio.emit("/mission/pause/config", {"data" : "PAUSE"})
        #time.sleep(5)
        #sio.emit("/mission/pause/config", {"data" : "RE_START"})
        #time.sleep(5)
        
@sio.event
def connect_error(data):
    print("The connection failed!")

@sio.event
def disconnect():
    print("I'm disconnected!")

# define a video capture object
vid = cv2.VideoCapture("http://192.168.4.13:4747/video")
recognizer = cv2.face.LBPHFaceRecognizer_create()
recognizer.read('./Ming.yml')
#define mediapipe object
mp_drawing = mp.solutions.drawing_utils
mp_pose = mp.solutions.pose
counter = 0 
stage = None
counter = 0 
start = None
sosed = False
sockethaha.listen(1000)
print ("socket is listening")
socket_conn, socket_addr = sockethaha.accept()
print ('Got connection from', socket_addr )
print("socket_conn: ", socket_conn)
sio.connect("https://ive0001.r2c2.ai:3000")

with mp_pose.Pose(min_detection_confidence=0.5, min_tracking_confidence=0.5) as pose:
    while(True):
        try:
            
            # Capture the video frame
            # by frame
            ret, frame = vid.read()
        
            # Display the resulting frame
            imageCap = cv2.cvtColor(frame, cv2.COLOR_BGR2GRAY)
            cascade_classifier = cv2.CascadeClassifier("./haarcascade_frontalface_default.xml")
            detected_objects = cascade_classifier.detectMultiScale(imageCap, 1.15, 10, 0, minSize = (150, 150))
            #fall detection
            # Recolor image to RGB
            image = cv2.cvtColor(frame, cv2.COLOR_BGR2RGB)
            image.flags.writeable = False

            if(emitted_pause == True) and (sosed == False):
                # Make detection
                results = pose.process(image)
        
                # Recolor back to BGR
                image.flags.writeable = True
                image = cv2.cvtColor(image, cv2.COLOR_RGB2BGR)
                
                # Extract landmarks
                try:
                    landmarks = results.pose_landmarks.landmark
                    
                    # Get coordinates
                    LEFT_SHOULDER = [landmarks[mp_pose.PoseLandmark.LEFT_SHOULDER.value].x,landmarks[mp_pose.PoseLandmark.LEFT_SHOULDER.value].y]
                    LEFT_HIP = [landmarks[mp_pose.PoseLandmark.LEFT_HIP.value].x,landmarks[mp_pose.PoseLandmark.LEFT_HIP.value].y]
                    LEFT_KNEE = [landmarks[mp_pose.PoseLandmark.LEFT_KNEE.value].x,landmarks[mp_pose.PoseLandmark.LEFT_KNEE.value].y]
                    LEFT_ANKLE = [landmarks[mp_pose.PoseLandmark.LEFT_ANKLE.value].x,landmarks[mp_pose.PoseLandmark.LEFT_ANKLE.value].y]

                    RIGHT_SHOULDER = [landmarks[mp_pose.PoseLandmark.RIGHT_SHOULDER.value].x,landmarks[mp_pose.PoseLandmark.RIGHT_SHOULDER.value].y]
                    RIGHT_HIP = [landmarks[mp_pose.PoseLandmark.RIGHT_HIP.value].x,landmarks[mp_pose.PoseLandmark.RIGHT_HIP.value].y]
                    RIGHT_KNEE = [landmarks[mp_pose.PoseLandmark.RIGHT_KNEE.value].x,landmarks[mp_pose.PoseLandmark.RIGHT_KNEE.value].y]
                    RIGHT_ANKLE = [landmarks[mp_pose.PoseLandmark.RIGHT_ANKLE.value].x,landmarks[mp_pose.PoseLandmark.RIGHT_ANKLE.value].y]
                    
                    # Calculate angle
                    LEFT_angle = calculate_angle(LEFT_HIP, LEFT_KNEE, LEFT_ANKLE)
                    RIGHT_angle = calculate_angle(RIGHT_HIP, RIGHT_KNEE, RIGHT_ANKLE)

                    LEFT_HIP_angle = calculate_angle(LEFT_SHOULDER, LEFT_HIP, RIGHT_HIP)
                    RIGHT_HIP_angle = calculate_angle(RIGHT_SHOULDER, RIGHT_HIP, LEFT_HIP)

                    cv2.putText(frame, str(landmarks[mp_pose.PoseLandmark.LEFT_SHOULDER.value].y), 
                            tuple(np.multiply(LEFT_SHOULDER, [640, 480]).astype(int)), 
                            cv2.FONT_HERSHEY_SIMPLEX, 0.5, (255, 255, 255), 2, cv2.LINE_AA
                                    )
                    cv2.putText(frame, str(landmarks[mp_pose.PoseLandmark.RIGHT_SHOULDER.value].y), 
                                tuple(np.multiply(RIGHT_SHOULDER, [640, 480]).astype(int)), 
                                cv2.FONT_HERSHEY_SIMPLEX, 0.5, (255, 255, 255), 2, cv2.LINE_AA
                                        )
                    cv2.putText(frame, str(landmarks[mp_pose.PoseLandmark.LEFT_HIP.value].y), 
                                tuple(np.multiply(LEFT_HIP, [640, 480]).astype(int)), 
                                cv2.FONT_HERSHEY_SIMPLEX, 0.5, (255, 255, 255), 2, cv2.LINE_AA
                                        )
                    cv2.putText(frame, str(landmarks[mp_pose.PoseLandmark.RIGHT_HIP.value].y), 
                                tuple(np.multiply(RIGHT_HIP, [640, 480]).astype(int)), 
                                cv2.FONT_HERSHEY_SIMPLEX, 0.5, (255, 255, 255), 2, cv2.LINE_AA
                                        )
                    # Curl counter logic
                    if landmarks[mp_pose.PoseLandmark.RIGHT_SHOULDER.value].y>0 and (landmarks[mp_pose.PoseLandmark.RIGHT_HIP.value].y  - landmarks[mp_pose.PoseLandmark.RIGHT_SHOULDER.value].y) <= 0.2 and (landmarks[mp_pose.PoseLandmark.LEFT_HIP.value].y  - landmarks[mp_pose.PoseLandmark.LEFT_SHOULDER.value].y) <= 0.2:
                        counter +=1
                        
                        print(counter)

                        if start is None:
                            start = time.time()
                        else:
                            print(time.time()-start)
                            if (time.time() - start > 15) and (playingState != "STOP" or ""):
                                print("now emit STOP and call")
                                #vid.release()
                                #emit PAUSE and call 
                                sio.emit("/mission/stop", {"data" : " "})
                                sosed = True
                                proc = subprocess.Popen("python3 /var/www/html/aiortc/emergencyCall.py", 
                                    shell=True, stdout=subprocess.PIPE)
                                proc.wait()
                                socket_conn.send('close'.encode())
                                print('connection close')
                    else:
                        counter = 0
                        start = None
                    cv2.rectangle(frame, (0,0), (225,73), (245,117,16), -1)
            
                    # Rep data
                    cv2.putText(frame, 'REPS', (15,12), 
                                cv2.FONT_HERSHEY_SIMPLEX, 0.5, (0,0,0), 1, cv2.LINE_AA)
                    cv2.putText(frame, str(counter), 
                                (10,60), 
                                cv2.FONT_HERSHEY_SIMPLEX, 2, (255,255,255), 2, cv2.LINE_AA)
                    
                    # Stage data
                    cv2.putText(frame, 'STAGE', (65,12), 
                                cv2.FONT_HERSHEY_SIMPLEX, 0.5, (0,0,0), 1, cv2.LINE_AA)
                    cv2.putText(frame, stage, 
                                (60,60), 
                                cv2.FONT_HERSHEY_SIMPLEX, 2, (255,255,255), 2, cv2.LINE_AA)
                    
                    
                    # Render detections
                    mp_drawing.draw_landmarks(frame, results.pose_landmarks, mp_pose.POSE_CONNECTIONS,
                                            mp_drawing.DrawingSpec(color=(245,117,66), thickness=2, circle_radius=2), 
                                            mp_drawing.DrawingSpec(color=(245,66,230), thickness=2, circle_radius=2) 
                                            )
                except:
                    pass
            if len(detected_objects) == 0:
                no_object += 1
                true = 0
                socket_result = 'no object'
            else:
                for x, y, w, h in detected_objects:
                    
                    id, confidence = recognizer.predict(imageCap[y:y+h, x:x+w])
                    cv2.putText(frame, str(w), (x+w, y), cv2.FONT_HERSHEY_DUPLEX, 1.5, (255, 0, 0), 2)
                    if confidence<=80 and w < 150:
                        cv2.rectangle(frame, (x, y), (x+w, y+h), (0, 0, 255), 5)
                        cv2.putText(frame, 'user', (x+20, y-20), cv2.FONT_HERSHEY_DUPLEX, 1.5, (0, 255, 0), 2)
                        cv2.putText(frame, 'Too Far Away', (x+20, y-50), cv2.FONT_HERSHEY_DUPLEX, 1.5, (0, 255, 0), 2)
                        socket_result = 'far'
                        true = 0
                        #Alert API API
                    elif confidence>90:
                        cv2.rectangle(frame, (x, y), (x+w, y+h), (0, 255, 0), 5)
                        cv2.putText(frame, 'unknow', (x+20, y-20), cv2.FONT_HERSHEY_DUPLEX, 1.5, (0, 0, 255), 2)
                        socket_result = 'false'
                        false += 1
                        true = 0
                        #stop API
                    else:
                        cv2.rectangle(frame, (x, y), (x+w, y+h), (0, 255, 0), 5)
                        cv2.putText(frame, 'user', (x+20, y-20), cv2.FONT_HERSHEY_DUPLEX, 1.5, (0, 255, 0), 2)
                        socket_result = 'true'
                        false = 0
                        no_object = 0
                        true += 1
                        #move API           
                
            cv2.imshow('FaceCapture', frame)

            if(false > 100 and emitted_pause == False and playingState != "STOP" or ""):
                print("now emit pause")
                sio.emit("/mission/pause/config", {"data" : "PAUSE"})
                face_track_status = False
                emitted_pause = True
                

            if(no_object > 100 and emitted_pause == False and playingState != "STOP" or ""):
                print("now emit pause")
                sio.emit("/mission/pause/config", {"data" : "PAUSE"})
                face_track_status = False
                emitted_pause = True
            
            if(true > 50 and emitted_pause == True):
                face_track_status = True
                emitted_pause = False

        
            #socket_conn.send(socket_result.encode())
            
            if cv2.waitKey(1) & 0xFF == ord('q'):
                break
        except:
            print('line 268')
            print(str(e))
            sockethaha.listen(5)
            socket_conn, socket_addr = sockethaha.accept()
            print ('Got connection from', socket_addr )
            print("socket_conn: ", socket_conn)

  
# After the loop release the cap object
vid.release()
# Destroy all the windows
cv2.destroyAllWindows()
