import cv2
import numpy as np
import os
from PIL import Image
import sys
print(sys.argv)
idFromPHP = sys.argv[1]
print("getValue:", idFromPHP)
os.chdir("/home/ivetm/faceDataset")
id = str(idFromPHP)
path = str('folder' + id)
os.makedirs(path, mode=0o777, exist_ok=True)
os.chdir("/home/ivetm/faceDataset/folder"+id)

video = cv2.VideoCapture("/home/ivetm/faceDataset/"+id+".mp4")
imgCount = 0


def getImageAndLabels(path):
    facesSamples = []
    ids = []
    imagePaths = [os.path.join(path,f) for f in os.listdir(path)]
    #itrainerPath = 'trainer/'
    #os.makedirs(trainerPath)

    face_detector = cv2.CascadeClassifier("/var/www/html/haarcascade_frontalface_default.xml")
    print("faceDetect")
    for imagePath in imagePaths:
        
        print("image:",imagePath)
        face_img = Image.open(imagePath).convert('L')
        face_numpy = np.array(face_img, 'uint8')
        #faces = face_detector.detectMultiScale(face_numpy)
        id2 = os.path.split(imagePath)[-1].split('.')[1]
        #print("faces:", faces)
        ids.append(id2)
        facesSamples.append(face_numpy)
        #for x, y, w, h in faces:
        #    ids.append(id2)
        #    print("id:",ids)
        #    print("x:", x, ", y:", y, " w:", w, " h:", h)
        #    facesSamples.append(face_numpy[y:y+h, x:x+w])

    print('id', id2)
    print('faceSample', facesSamples)
    return facesSamples, ids



while True:
    ret, frame = video.read()

    if not ret:
        break

    imageCap = cv2.cvtColor(frame, cv2.COLOR_BGR2GRAY)
    cascade_classifier = cv2.CascadeClassifier("/var/www/html/haarcascade_frontalface_default.xml")
    detected_face = cascade_classifier.detectMultiScale(imageCap, 1.1, 3, 0, minSize = (100, 100))

    for x, y, w, h in detected_face:
        imgCount = imgCount+1
        imageName= 'user.'+str(id)+'.'+str(imgCount)+'.jpg'
        print(imageName)
        if not (cv2.imwrite(imageName, imageCap[y:y+h, x:x+w])):
            print("cannot save")
        #cv2.rectangle(frame, (x, y), (x+w, y+h), (0, 255, 0), 5)
    #cv2.imshow('FaceCapture', frame)

    #cv2.waitKey(1) 
    if imgCount>10:
        break

video.release()
#cv2.destroyAllWindows()


path = os.getcwd()
print(path)
faces, ids = getImageAndLabels(path)
print("test")
recognizer = cv2.face.LBPHFaceRecognizer_create()
print("recong")
print(np.array([1]*len(ids)))
recognizer.train(faces, np.array([1]* len(ids) )) 
recognizer.save(id + '.yml')
