package monneyFarming.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TaiXiuService {


    public static int getCurrentSecond() {
        String ROOT_IMAGE_PATH = "originalImages/";

        List<String> secondImages = new ArrayList<String>();

        for (int i = 0; i < 5; i++) {
            String image = ScreenShootMaster.takeSecondImage();
            sleepBy(100);
            secondImages.add(image);
        }


        for (int i = 0; i < 51; i++) {
            String rootTimeImage = ROOT_IMAGE_PATH + i + ".png";
            for (String image : secondImages) {
                if (ImageComparator.isTheSameImageByPath(rootTimeImage, image)) {

                    for (String secondImage : secondImages) {
                        FileUtils.deleteFile(new File(secondImage));
                    }
                    return i;
                }
            }
        }

        for (String secondImage : secondImages) {
            FileUtils.deleteFile(new File(secondImage));
        }
        return -1;
    }


    public static void sleepBy(int sleepTime) {
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            System.out.println("an error occurred, unable to sleep thread!");
            e.printStackTrace();
        }
    }
}
