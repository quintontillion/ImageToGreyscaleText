package com.me.main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ImageToText {
    int currentImage = 0;
    public void Translate(BufferedImage img) {
        File save = new File("src/com/me/main/outputimages/Output" + currentImage);
        try {
            save.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            FileWriter write = new FileWriter("src/com/me/main/outputimages/Output" + currentImage);
            write.write(ConvertToText(img));
            write.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public ImageToText(BufferedImage img) {
        File save = new File("src/com/me/main/outputimages/Output" + currentImage);
        try {
            save.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            FileWriter write = new FileWriter("src/com/me/main/outputimages/Output" + currentImage);
            write.write(ConvertToText(img));
            write.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    // this string returns the alpha of the image in text form
    public String ConvertToText(BufferedImage img) {
        // creates an image that is grayscale
        BufferedImage gray = new BufferedImage(img.getWidth()/2,img.getHeight()/2,BufferedImage.TYPE_BYTE_GRAY);
        StringBuilder test = new StringBuilder();
        for (int i = 0; i < img.getHeight()/2; i++) {
            for (int x = 0; x < img.getWidth()/2; x++) {
                // this will loop through all the individual pixels and change them to their alpha variant
                Color c1, c2, c3, c4;
                if (i==0 && x==0) {
                    c1 = new Color(img.getRGB(0,1), true);
                    c2 = new Color(img.getRGB(1,1), true);
                    c3 = new Color(img.getRGB(0,0), true);
                    c4 = new Color(img.getRGB(1,0), true);
                }else if (i == 0) {
                    c1 = new Color(img.getRGB(x*1-1,1), true);
                    c2 = new Color(img.getRGB(x*1,1), true);
                    c3 = new Color(img.getRGB(x*1-1,0), true);
                    c4 = new Color(img.getRGB(x*1,0), true);
                } else if (x == 0) {
                    c1 = new Color(img.getRGB(0,i), true);
                    c2 = new Color(img.getRGB(1,i*2-1), true);
                    c3 = new Color(img.getRGB(0,i*2), true);
                    c4 = new Color(img.getRGB(1,i*2), true);
                }else {
                    c1 = new Color(img.getRGB(x*2-1,i*2-1), true);
                    c2 = new Color(img.getRGB(x*2,i*2-1), true);
                    c3 = new Color(img.getRGB(x*2-1,i*2), true);
                    c4 = new Color(img.getRGB(x*2,i*2), true);
                }
                int average1 = (c1.getRed() + c1.getBlue() + c1.getBlue())/3;
                int average2 = (c2.getRed() + c2.getBlue() + c2.getBlue())/3;
                int average3 = (c3.getRed() + c3.getBlue() + c3.getBlue())/3;
                int average4 = (c4.getRed() + c4.getBlue() + c4.getBlue())/3;
                int average = (average1 + average2 + average3 + average4)/4;
                Color New = new Color(average,average,average);
                gray.setRGB(x,i,New.getRGB());
                if (average < 26 && average >= 0) {
                    test.append(" ");
                }
                if (average > 25 && average < 53) {
                    test.append(".");
                }if (average < 79 && average > 52) {
                    test.append(":");
                }if (average > 78 && average < 104) {
                    test.append("-");
                }if (average > 103 && average < 129) {
                    test.append("=");
                }if (average > 128 && average < 155) {
                    test.append("+");
                }if (average > 154 && average < 179) {
                    test.append("*");
                }if (average > 178 && average < 204) {
                    test.append("#");
                }if (average > 203 && average < 229) {
                    test.append("%");
                }if (average > 228 && average < 257) {
                    test.append("@");
                }

                //System.out.println(c.getAlpha());
            }
            test.append("\n");
        }
        return test.toString();
    }
    public static void main(String args[]) {
        try {
            new ImageToText(ImageIO.read(new File("src/com/me/main/elon.jpg")));
        } catch (Exception e) {
           e.printStackTrace();
        }
    }
}