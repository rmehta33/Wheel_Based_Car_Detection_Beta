package Internship;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class PointBasedDetection {

    static int range = 7;
    static int points[][];
    static int dimensionsOfImage[];

    public static boolean similarTest(Color a, Color b, int intensity) {
        return (a.getRed() < b.getRed() + intensity && a.getRed() > b.getRed() - intensity) ||
                (a.getBlue() < b.getBlue() + intensity && a.getBlue() > b.getBlue() - intensity) ||
                (a.getGreen() < b.getGreen() + intensity && a.getGreen() > b.getGreen() - intensity);
    }

    public static void main(String[] args){
        BufferedImage originalImg;
        BufferedImage outputImg;

        File input = new File("D:\\OneDrive\\11th Grade\\Internship\\Original.jpg");
        File output =  new File("D:\\OneDrive\\11th Grade\\Internship\\outputCar.jpg");

        Color[][] originalImgArray;

        int intensity = 50;


        try {
            originalImg = ImageIO.read(input);

            dimensionsOfImage[0] = originalImg.getWidth();
            dimensionsOfImage[1] = originalImg.getHeight();

            outputImg = new BufferedImage(originalImg.getWidth(),originalImg.getHeight(),BufferedImage.TYPE_INT_RGB);
            originalImgArray = new Color[originalImg.getWidth()][originalImg.getHeight()];
            points = new int[originalImg.getWidth()][originalImg.getHeight()];

            for(int a = 0; a<originalImgArray.length; a++){
                for (int b = 0; b<originalImgArray[0].length; b++){
                    originalImgArray[a][b] = new Color(originalImg.getRGB(a,b));
                    outputImg.setRGB(a,b,originalImg.getRGB(a,b));
                }
            }

            for(int a = range; a<originalImgArray.length - range; a = a + range){
                for (int b = range; b<originalImgArray[0].length - range; b = b + range){
                    if(similarTest(originalImgArray[a][b], originalImgArray[a][b-range], intensity)){
                        if (similarTest(originalImgArray[a][b], originalImgArray[a][b+range], intensity)){
                            if (similarTest(originalImgArray[a][b], originalImgArray[a-range][b], intensity)){
                                if (similarTest(originalImgArray[a][b], originalImgArray[a+range][b], intensity)){
                                    continue;
                                }
                            }
                        }
                    }
                    outputImg.setRGB(a, b, new Color(0, 200, 0, 200).getRGB());
                    points[a][b] = 1;
                    System.out.printf("%d %d\n", a,b);
                }
            }
            //tracer();
            ImageIO.write(outputImg, "jpg", output);


        } catch (IOException e) {
            System.out.println("read errors");
        }
    }
}
