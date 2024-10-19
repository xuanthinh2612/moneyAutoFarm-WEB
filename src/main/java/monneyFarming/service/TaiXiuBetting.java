package monneyFarming.service;

import monneyFarming.dataService.InitValueService;
import monneyFarming.dataService.ResultService;
import monneyFarming.model.InitValue;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class TaiXiuBetting extends Thread {
    InitValueService initValueService;
    ResultService resultService;

    static final String XIU = "xiu";
    static final String TAI = "tai";
    static final int[] betBaseList = new int[]{1, 2, 3, 5, 10, 20, 30, 50, 100};


    @Autowired
    public TaiXiuBetting(InitValueService initValueService, ResultService resultService) {
        this.initValueService = initValueService;
        this.resultService = resultService;
    }

    public void run() {
        executeProgram(initValueService, resultService);
    }

    private void executeProgram(InitValueService initValueService, ResultService resultService) {

        // Setup Chrome WebDriver
        WebDriver driver = new ChromeDriver();
//        driver.manage().window().setSize(new Dimension(1550, 820));
        driver.manage().window().maximize();
        driver.get("http://sunwin.skin");
        var canvas = driver.findElement(By.id("GameCanvas"));

        System.out.println("Start Program");
        InitValue initValue;

        while (true) {
            TaiXiuService.sleepBy(10000);
            initValue = initValueService.findFirstValue();
            System.out.println("initValue: " + initValue.getReadyStatus());
            System.out.println("OPEN: " + InitValue.OPEN_MINI_GAME);
            if (initValue.getReadyStatus() == InitValue.OPEN_MINI_GAME) {
                System.out.println("Open MiniGame...");
                ClickMaster.openGame(driver, canvas);
                break;
            }
            System.out.println("Waiting for Game Ready! After Login, Click Open Mini Game To Continue ");

        }

        while (true) {
            initValue = initValueService.findFirstValue();
            if (initValue.getReadyStatus() == InitValue.START_BET) {
                try {
                    int betBaseAmount = initValue.getBetAmount();
                    int maxRound = initValue.getBetRound();
                    String betIn = initValue.getBetIn();

                    // start game
                    int betAmount = betBaseAmount;
                    boolean betableFlag = true;
                    int time = 0;
                    int MAX_LOSE_NUM = 5;
                    int loseNum = 0;
                    boolean stopBetFlag = false;

                    while (true) {

                        int currentSecond = TaiXiuService.getCurrentSecond();
                        if (currentSecond > 10) {
                            System.out.println("Count Down: " + currentSecond);

                            if (betableFlag && !stopBetFlag) {
                                System.out.println("Start Bet...");
                                if (TAI.equals(betIn)) {
                                    executeBetTai(driver, canvas, betAmount);
                                } else {
                                    executeBetXiu(driver, canvas, betAmount);
                                }
                                betableFlag = false;
                                time++;
                                System.out.println("Time: " + time);
                            }
                        } else if (currentSecond > 0 && currentSecond < 10) {
                            System.out.println("Time's Up for Betting!");
                            TaiXiuService.sleepBy(3000);
                        } else if (currentSecond == 0) {
                            System.out.println("Time Out 00 ! Waiting for result...!");

                            TaiXiuService.sleepBy(10000);
                            String taiImage = ScreenShootMaster.takeTaiImage();
                            String xiuImage = ScreenShootMaster.takeXiuImage();

                            boolean taiWin = ImageComparator.isTaiWin(taiImage);
                            boolean xiuWin = ImageComparator.isXiuWin(xiuImage);
                            if (!betableFlag & !stopBetFlag) {
                                switch (betIn) {
                                    case TAI -> {
                                        if (taiWin && !xiuWin) {
                                            loseNum = 0;
                                            betAmount = betBaseAmount;
                                            System.out.println("TAI WIN!");
                                        } else {
                                            loseNum++;
                                            if (loseNum >= MAX_LOSE_NUM) {
                                                loseNum = 0;
                                                betAmount = betBaseAmount;
                                            } else {
                                                betAmount = betAmount * 2;
                                            }
                                            System.out.println("XIU WIN!");
                                        }
                                    }
                                    case XIU -> {
                                        if (xiuWin && !taiWin) {
                                            loseNum = 0;
                                            betAmount = betBaseAmount;
                                            System.out.println("XIU WIN!");
                                        } else {
                                            loseNum++;
                                            if (loseNum >= MAX_LOSE_NUM) {
                                                loseNum = 0;
                                                betAmount = betBaseAmount;
                                            } else {
                                                betAmount = betAmount * 2;
                                            }
                                            System.out.println("TAI WIN!");
                                        }
                                    }
                                }
                                betableFlag = true;
                            } else {
                                System.out.println("Skip This Time!");
                            }
                            TaiXiuService.sleepBy(9000);
                            if (maxRound <= time) {
                                driver.quit();
                                System.out.println("Program Completed!");
                                System.exit(0);
                            }
                        } else if (currentSecond == -1) {
                            System.out.println("Unable to Scan Images!");
                        }
                        TaiXiuService.sleepBy(500);

                        initValue = initValueService.findFirstValue();
                        if (initValue.getReadyStatus() == InitValue.STOP_BET) {
                            stopBetFlag = true;
                            System.out.println("Stop Bet!");
                        }
                        if (initValue.getReadyStatus() == InitValue.STOP_PROGRAM) {
                            System.out.println("Stop Program!");
                            System.exit(0);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    driver.quit();
                }
            } else if (initValue.getReadyStatus() == InitValue.STOP_PROGRAM) {
                System.out.println("Stop Program!");
                System.exit(0);
            }

            TaiXiuService.sleepBy(3000);
            System.out.println("Program Started! Wait For Bet!");

        }

    }

    private void executeBetTai(WebDriver driver, WebElement canvas, int clickNumber) {
        TaiXiuService.sleepBy(500);
        System.out.println("Click Bet Tai!");
        ClickMaster.clickBetTai(driver, canvas);
        TaiXiuService.sleepBy(500);

        for (int i = 0; i < clickNumber; i++) {
            ClickMaster.click1k(driver, canvas);
        }

        TaiXiuService.sleepBy(1000);
        ClickMaster.clickBet(driver, canvas);
        System.out.println("Bet Done!!!");
    }

    private void executeBetXiu(WebDriver driver, WebElement canvas, int clickNumber) {
        TaiXiuService.sleepBy(500);
        System.out.println("Click Bet Xiu!");
        ClickMaster.clickBetXiu(driver, canvas);
        TaiXiuService.sleepBy(500);

        for (int i = 0; i < clickNumber; i++) {
            ClickMaster.click1k(driver, canvas);
        }

        TaiXiuService.sleepBy(1000);
        ClickMaster.clickBet(driver, canvas);
        System.out.println("Bet Done!!!");
    }

    private boolean isValidBetBaseAmount(int betAmount) {
        for (int e : betBaseList) {
            if (betAmount == e) {
                return true;
            }
        }
        return false;
    }

}
