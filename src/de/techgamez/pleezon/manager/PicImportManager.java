package de.techgamez.pleezon.manager;


import de.techgamez.pleezon.GameOfLife;
import de.techgamez.pleezon.components.CellAmountSliderLabel;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

public class PicImportManager {
    public static double sensitivity = 0.02;
    public static void processToBoard(File input){
        try{
            ImageReader edgeDetector = new ImageReader();
            BufferedImage img = ImageIO.read(input);
            BufferedImage greyImg = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
            greyImg.getGraphics().drawImage(img, 0, 0, null);
            BufferedImage result = edgeDetector.greyscale(greyImg);
            CellManager.cells = new boolean[Math.max(result.getWidth(),result.getHeight())][Math.max(result.getWidth(),result.getHeight())];
            CellAmountSliderLabel.INSTANCE.setText("Cells: " + Math.max(result.getWidth(),result.getHeight()));
            for(int x=0; x<result.getWidth(); x++){
                for(int y=0; y< result.getHeight(); y++){
                    float greyscale = new Color(result.getRGB(x,y)).getRed()/255F;
                    CellManager.cells[x][y] = greyscale>sensitivity;
                }
            }
            GameOfLife.gameField.repaint();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static class ImageReader {
        private BufferedImage Gx, Gy;

        public BufferedImage detect(BufferedImage img) {
            float[] x1 = {-1, 0, 1, -2, 0, 2, -1, 0, 1};
            float[] y1 = {-1, -2, -1, 0, 0, 0, 1, 2, 1};
            Kernel MatrixA = new Kernel(3, 3, x1);
            Kernel MatrixB = new Kernel(3, 3, y1);
            ConvolveOp convolve1 = new ConvolveOp(MatrixA);
            ConvolveOp convolve2 = new ConvolveOp(MatrixB);

            this.Gx = convolve1.filter(img, null);
            this.Gy = convolve2.filter(img, null);


            for (int i = 0; i < img.getWidth(); i++) {
                for (int j = 0; j < img.getHeight(); j++) {
                    double result = G(i, j);
                    if (result < 20726564.99) {
                        img.setRGB(i, j, Color.white.getRGB());
                    } else {
                        img.setRGB(i, j, Color.black.getRGB());
                    }
                }
            }
            return img;
        }

        public BufferedImage greyscale(BufferedImage img) {
            double max = 23777215;
            float[] x1 = {-1, 0, 1, -2, 0, 2, -1, 0, 1};
            float[] y1 = {-1, -2, -1, 0, 0, 0, 1, 2, 1};
            Kernel MatrixA = new Kernel(3, 3, x1);
            Kernel MatrixB = new Kernel(3, 3, y1);
            ConvolveOp convolve1 = new ConvolveOp(MatrixA);
            ConvolveOp convolve2 = new ConvolveOp(MatrixB);

            this.Gx = convolve1.filter(img, null);
            this.Gy = convolve2.filter(img, null);

            for (int i = 0; i < img.getWidth(); i++) {
                for (int j = 0; j < img.getHeight(); j++) {
                    double result = G(i, j);
                    float greyscaleValue = (float) (result / 23777215);
                    greyscaleValue = 1 - greyscaleValue;
                    float red = 255 * greyscaleValue;
                    float blue = 255 * greyscaleValue;
                    float green = 255 * greyscaleValue;
                    Color gray2 = new Color((int) red, (int) green, (int) blue);
                    img.setRGB(i, j, gray2.getRGB());
                }
            }
            return img;
        }

        private double G(int x, int y) {
            int derp = this.Gx.getRGB(x, y);
            int herp = this.Gy.getRGB(x, y);

            double result = Math.sqrt(Math.pow(derp, 2.0) + Math.pow(herp, 2.0));
            return result;
        }


    }
}
