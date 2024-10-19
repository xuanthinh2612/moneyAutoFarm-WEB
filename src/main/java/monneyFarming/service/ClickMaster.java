package monneyFarming.service;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ClickMaster {

    // Calculate position canvas button
    // Element width: 1536, height: 678
    // Element location - X: 0, Y: 0
    // Element boundaries - Right X: 1536, Bottom Y: 678
    // ====================================================================================
    //0.0  -> 768x338
    //Click miniGame on the canvas 1480x630 -> param 712	297
    // TXMiniGame 1273x460  -> param 505	122
    // bet T button 474x407 -> param -294	69
    // bet X button 842x408 -> param 74	70
    // 1k 394x533 -> param -374	195
    // 10k 470x533 -> param -298	195
    // 50k 546x535 -> param -222	197
    // BET 649x601 -> param -119	263
    // Cancel 801x597 -> 33	259
    public static void clickBetTai(WebDriver driver, WebElement canvas) {
        //Bet Tai -294	69
        clickTo(driver, canvas, -294, 69);
    }

    public static void clickBetXiu(WebDriver driver, WebElement canvas) {
        //Bet Xiu 74	70
        clickTo(driver, canvas, 74, 70);
    }

    public static void click1k(WebDriver driver, WebElement canvas) {
        // 1k -374	195
        clickTo(driver, canvas, -374, 195);
    }

    public static void click10k(WebDriver driver, WebElement canvas) {
        //Bet 10k -298	195
        clickTo(driver, canvas, -298, 195);
    }

    public static void click50k(WebDriver driver, WebElement canvas) {
        //Bet 50k -222	197
        clickTo(driver, canvas, -222, 197);
    }

    public static void clickBet(WebDriver driver, WebElement canvas) {
        //Bet -119	263
        clickTo(driver, canvas, -119, 263);
    }

    public static void clickCancel(WebDriver driver, WebElement canvas) {
        //Bet Cancel 33	259
        clickTo(driver, canvas, 33, 259);
    }

    public static void clickTo(WebDriver driver, WebElement canvas, int x, int y) {
        try {
            new Actions(driver).moveToElement(canvas, x, y).click().perform();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Fail When Click!");
        }
    }

    private static void sleepBy(int sleepTime) {
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            System.out.println("Error! Can Not Sleep Thread!");
            e.printStackTrace();
        }

    }

    public static void openGame(WebDriver driver, WebElement canvas) {
        // Click MiniGame 712	297
        clickTo(driver, canvas, 712, 297);
        sleepBy(2000);
        // Click TaiXiu 505,	122
        clickTo(driver, canvas, 505, 122);
        sleepBy(500);
    }


}
