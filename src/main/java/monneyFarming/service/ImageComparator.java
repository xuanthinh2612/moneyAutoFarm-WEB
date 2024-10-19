package monneyFarming.service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class ImageComparator {

    final static String TAI_ORIGIN = "originalImages/taiOriginal.png";
    final static String XIU_ORIGIN = "originalImages/xiuOriginal.png";

    // Check if Xiu is Win
    public static boolean isTaiWin(String resultFilePath) {
        try {
            BufferedImage imgA = ImageIO.read(new File(resultFilePath));
            BufferedImage imgB = ImageIO.read(new File(TAI_ORIGIN));

            return !bufferedImagesEqual(imgA, imgB);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // check if Tai is Win
    public static boolean isXiuWin(String resultFilePath) {
        try {
            BufferedImage imgA = ImageIO.read(new File(resultFilePath));
            BufferedImage imgB = ImageIO.read(new File(XIU_ORIGIN));

            return !bufferedImagesEqual(imgA, imgB);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // compare image by full path name
    public static boolean isTheSameImageByPath(String imagePathA, String imagePathB) {
        try {
            BufferedImage imgA = ImageIO.read(new File(imagePathA));
            BufferedImage imgB = ImageIO.read(new File(imagePathB));

            return bufferedImagesEqual(imgA, imgB);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
            return false;
        }
    }

    // compare 2 image by RGB
    private static boolean bufferedImagesEqual(BufferedImage img1, BufferedImage img2) {
        if (img1.getWidth() == img2.getWidth() && img1.getHeight() == img2.getHeight()) {
            for (int x = 0; x < img1.getWidth(); x++) {
                for (int y = 0; y < img1.getHeight(); y++) {
                    if (img1.getRGB(x, y) != img2.getRGB(x, y)) return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }
}