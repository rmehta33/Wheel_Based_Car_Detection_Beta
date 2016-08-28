package Internship;


import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacpp.opencv_core.IplImage;
import org.bytedeco.javacv.JavaCV;

import static org.bytedeco.javacpp.opencv_core.*;
import static org.bytedeco.javacpp.opencv_highgui.cvShowImage;
import static org.bytedeco.javacpp.opencv_highgui.cvWaitKey;
import static org.bytedeco.javacpp.opencv_imgcodecs.cvLoadImage;
import static org.bytedeco.javacpp.opencv_imgproc.*;

public class circleDetection {

    private static opencv_core.CvPoint3D32f lastCircle;

    public static void main(String[] args) {
        IplImage src = cvLoadImage("D:\\OneDrive\\11th Grade\\Internship\\car-crash.jpg");
        IplImage gray = cvCreateImage(cvGetSize(src), 8, 1);

        cvCvtColor(src, gray, CV_BGR2GRAY);
        cvSmooth(gray, gray);

        opencv_core.CvMemStorage mem = opencv_core.CvMemStorage.create();

        opencv_core.CvSeq circles = cvHoughCircles(
                gray, //Input image
                mem, //Memory Storage
                CV_HOUGH_GRADIENT, //Detection method
                1, //Inverse ratio
                100, //Minimum distance between the centers of the detected circles
                100, //Higher threshold for canny edge detector
                100, //Threshold at the center detection stage
                15, //min radius
                500 //max radius
        );


        for (int i = 0; i < circles.total(); i++) {
            opencv_core.CvPoint3D32f circle = new opencv_core.CvPoint3D32f(cvGetSeqElem(circles, i));
            opencv_core.CvPoint center = cvPointFrom32f(new opencv_core.CvPoint2D32f(circle.x(), circle.y()));
            int radius = Math.round(circle.z());
            cvCircle(src, center, radius, opencv_core.CvScalar.GREEN, 6, CV_AA, 0);
            lastCircle = circle;

            System.out.println(center);
            System.out.println(circle);
        }

        cvShowImage("Result", src);
        cvWaitKey(0);

        perspectiveT(src,new double[]{lastCircle.x()-lastCircle.z(),lastCircle.y()+lastCircle.z(),
                lastCircle.x()+lastCircle.z(),lastCircle.y()+lastCircle.z(),
                lastCircle.x()-lastCircle.z(),lastCircle.y()-lastCircle.z(),
                lastCircle.x()+lastCircle.z(),lastCircle.y()-lastCircle.z()});
    }

    public static void perspectiveT(IplImage image, double[] src){

        double[] dst = {0.0,0.0,image.width(),0,0,image.height(),image.width(),image.height()};
        IplImage output = cvCreateImage(cvGetSize(image), 8, 1);


        CvMat mmat = cvCreateMat(3,3,CV_32FC1);

        CvMat M = JavaCV.getPerspectiveTransform(src,dst,mmat);
        cvWarpPerspective(image,output,M,image.sizeof(),AbstractCvScalar.BLUE);
        cvShowImage("output",output);

    }

}




