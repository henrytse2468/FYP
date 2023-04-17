import cv2
import os
import sys
print(sys.argv)
idFromPHP = sys.argv[1]
print("getValue:", idFromPHP)
os.chdir("/home/ivetm/faceDataset/folderMaster")
id = str(idFromPHP)
path = str(id)
os.makedirs(path, mode=0o777, exist_ok=True)
os.chdir("/home/ivetm/faceDataset/folderMaster/"+id)

cap= cv2.VideoCapture("/home/ivetm/faceDataset/"+id+".mp4")
i=0
while(cap.isOpened() and i <= 100):
    ret, frame = cap.read()
    if ret == False:
        break
    cv2.imwrite("user"+id+str(i)+'.jpg',frame)
    print('user'+id+str(i)+'.jpg')
    i+=1
 
cap.release()
cv2.destroyAllWindows()

