import cv2
import numpy as np

switch = True

def function(x):

    #waits for esc to be pressed to quit the function
    k = cv2.waitKey(1)
    if k == 27:
        return 0
    #

    #finding hsv filter values from trackbars
    lower_blue = np.array([cv2.getTrackbarPos("LB1","image"),cv2.getTrackbarPos("LB2","image"),cv2.getTrackbarPos("LB3","image")])
    upper_blue = np.array([cv2.getTrackbarPos("HB1","image"),cv2.getTrackbarPos("HB2","image"),cv2.getTrackbarPos("HB3","image")])
    #

    #mask is set as the filter
    mask = cv2.inRange(hsv,lower_blue,upper_blue)



'''

Use this to find correct HSV value for an image - by default the program is set to auto-find this

v2.namedWindow('image')
cv2.createTrackbar("LB1","image",0,255,function)
cv2.createTrackbar("LB2","image",0,255,function)
cv2.createTrackbar("LB3","image",0,255,function)
cv2.createTrackbar("HB1","image",0,255,function)
cv2.createTrackbar("HB2","image",0,255,function)
cv2.createTrackbar("HB3","image",0,255,function)
'''

global img
img = cv2.imread('Original.jpg')
#autofinds hsv values
hsv = cv2.cvtColor(img, cv2.COLOR_RGB2HSV)



#cv2.imshow("res", res)
cv2.imshow("title",hsv)

cv2.waitKey(0)