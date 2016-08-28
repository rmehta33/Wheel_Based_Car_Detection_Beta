import numpy as np
import cv2

def order_points(pts):

    #organzies the cordinates to Top Left, Top Right, Bottom Right, and Bottom Left
    rect = np.zeros((4,2), dtype="float32")  #4x3 array of zeros are created


    s = pts.sum(axis = 1) #takes the sum of values on axis = 1
    diff = np.diff(pts,axis=1) #takes the difference of values on axis = 1

    rect[0] = pts[np.argmin(s)] #Top Left
    rect[1] = pts[np.argmin(diff)] #Top Right
    rect[2] = pts[np.argmax(s)] #Bottom Right
    rect[3] = pts[np.argmax(diff)] #Bottom Left

    return rect

def fourPtTransformation(image,pts):

    rect = order_points(pts)
    (tl, tr, br, bl) = rect

    #Image Dimensions - uses distance formula
    widthA = np.sqrt(((br[0] - bl[0]) ** 2) + ((br[1] - bl[1]) ** 2))
    widthB = np.sqrt(((tr[0] - tl[0]) ** 2) + ((tr[1] - tl[1]) ** 2))
    maxWidth = max(int(widthA), int(widthB))

    heightA = np.sqrt(((tr[0] - br[0]) ** 2) + ((tr[1] - br[1]) ** 2))
    heightB = np.sqrt(((tl[0] - bl[0]) ** 2) + ((tl[1] - bl[1]) ** 2))
    maxHeight = max(int(heightA), int(heightB))

    dst = np.array([
		[0, 0],
		[maxWidth - 1, 0],
		[maxWidth - 1, maxHeight - 1],
		[0, maxHeight - 1]], dtype = "float32")

    M = cv2.getPerspectiveTransform(rect,dst)
    warped = cv2.warpPerspective(image,M,(maxWidth,maxHeight))

    return warped

