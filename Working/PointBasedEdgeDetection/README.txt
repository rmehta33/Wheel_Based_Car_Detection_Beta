ImageRead - A working countour based model. Should be implemented later

PointBasedDetection - Uses Points to define color differences in the image.

ImageReadTemp - A program that initially runs the point based detection model but then also tries to use linear regression to etch the image with lines. 

	How: Given a standard point, the program will use linear regression to create a line from that point. Once a line is drawn, the program will expand the search area of points and rerun the linear regression until the r^2 value from the previous line is greater than the current. Once a final line is drawn, the points close to it are deactivated so another linear regression model cannot run over it.
	
	Current Situation: The program is not complete. As of now, you can give the program a point and it will draw the line but you cannot sketch the entire image. This is still to come.
	

