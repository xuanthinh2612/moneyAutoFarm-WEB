package monneyFarming.service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class ScreenShootMaster {

    final static String PROCESSING_FOLDER = "processingImage/";

    public static String takeSecondImage() {
        // TIME
        int xTime = 600; // X
        int yTime = 445; //  Y
        int widthTime = 130; //
        int heightTime = 95; //

        String photoName = "time" + generateRandomHexString(6);
        return takePhoto(xTime, yTime, widthTime, heightTime, photoName);
    }

    public static String takeXiuImage() {
        //  XIU
        int xXiu = 800; //  X
        int yXiu = 380; // Y
        int widthXiu = 140; //
        int heightXiu = 80; //
        String photoName = "xiu";

        return takePhoto(xXiu, yXiu, widthXiu, heightXiu, photoName);
    }

    public static String takeTaiImage() {
        // tai
        int xTai = 400; // X
        int yTai = 380; //  Y
        int widthTai = 140; //
        int heightTai = 80; //

        String photoName = "tai";
        return takePhoto(xTai, yTai, widthTai, heightTai, photoName);
    }

    public static String takePhoto(int x, int y, int width, int height, String photoName) {
        String fileNameFullPath = PROCESSING_FOLDER + photoName + ".png";

        try {
            Robot robot = new Robot();
            BufferedImage screenshot = robot.createScreenCapture(new Rectangle(x, y, width, height));

//          file name
            File output = new File(fileNameFullPath);
            ImageIO.write(screenshot, "png", output);
        } catch (AWTException | IOException e) {
            e.printStackTrace();
        }

        return fileNameFullPath;
    }

    public static String generateRandomHexString(int length) {
        StringBuilder hexString = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            // Tạo một số ngẫu nhiên từ 0 đến 15 (tương đương với một ký tự hex)
            int randomValue = random.nextInt(16);
            // Chuyển đổi số nguyên thành ký tự hex và nối vào chuỗi hex
            hexString.append(Integer.toHexString(randomValue));
        }
        return hexString.toString();
    }
}
