from FourPointTransformation import fourPtTransformation
import numpy as np
import argparse
import cv2

image = cv2.imread("Original.jpg")

#enter cordinates to be transformed to
pts = np.array([
	[0,0],
	[0,0],
	[0,0],
	[0,0],
], dtype="float32")

warped = fourPtTransformation(image, pts)

cv2.imshow("Original", image)
cv2.imshow("Warped", warped)
cv2.waitKey(0)
