package monneyFarming.herlper;

import monneyFarming.model.Result;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static monneyFarming.herlper.CommonConst.TAI;
import static monneyFarming.herlper.CommonConst.XIU;

public class SimulatedService {

    public static void simulatedBetByFibonacci(List<Result> resultList, int overByNumber, Model model) {

        // simulate
        int betBaseAmount = 1;
        int maxBetAmountTai = 0;
        int maxBetAmountXiu = 0;

//        int loseNumber = 0;

        int totalTaiWinAmount = 0;
        int totalXiuWinAmount = 0;

        int totalTaiBetAmount = 0;
        int totalXiuBetAmount = 0;

        int betAmountInTai = betBaseAmount;
        int betAmountInXiu = betBaseAmount;

        int taiConstantlyLoseNum = 0;
        int xiuConstantlyLoseNum = 0;

        for (Result result : resultList) {
            // bet cả 2 server theo quy tắc dãy số fibonacci
            if (TAI.equals(result.getBetResult())) {
                totalTaiBetAmount += betAmountInTai;
                totalXiuBetAmount += betAmountInXiu;
                totalTaiWinAmount += betAmountInTai * 2;

                xiuConstantlyLoseNum++;
                taiConstantlyLoseNum = 0;


                if (xiuConstantlyLoseNum > overByNumber) {
                    xiuConstantlyLoseNum = 0;
                }

                betAmountInTai = betBaseAmount;
                // tính lại tiền bet sau mỗi ván
                betAmountInXiu = betBaseAmount * findFibonacciByIndex(xiuConstantlyLoseNum);

            }
            if (XIU.equals(result.getBetResult())) {
                totalTaiBetAmount += betAmountInTai;
                totalXiuBetAmount += betAmountInXiu;
                totalXiuWinAmount += betAmountInXiu * 2;

                xiuConstantlyLoseNum = 0;
                taiConstantlyLoseNum++;

                if (taiConstantlyLoseNum > overByNumber) {
                    taiConstantlyLoseNum = 0;
                }

                // tính lại tiền bet sau mỗi ván
                betAmountInXiu = betBaseAmount;
                betAmountInTai = betBaseAmount * findFibonacciByIndex(taiConstantlyLoseNum);
            }

            if (betAmountInTai >= maxBetAmountTai) {
                maxBetAmountTai = betAmountInTai;
            }
            if (betAmountInXiu >= maxBetAmountXiu) {
                maxBetAmountXiu = betAmountInXiu;
            }


        }

        model.addAttribute("totalTaiWinAmountFibonacci", totalTaiWinAmount);
        model.addAttribute("totalXiuWinAmountFibonacci", totalXiuWinAmount);
        model.addAttribute("totalTaiBetAmountFibonacci", totalTaiBetAmount);
        model.addAttribute("totalXiuBetAmountFibonacci", totalXiuBetAmount);
        model.addAttribute("NETFibonacci", totalXiuWinAmount + totalTaiWinAmount - totalTaiBetAmount - totalXiuBetAmount);
        model.addAttribute("maxBetAmountTaiFibonacci", maxBetAmountTai);
        model.addAttribute("maxBetAmountXiuFibonacci", maxBetAmountXiu);
    }

    public static void simulatedBetByMartingale(List<Result> resultList, int overByNumber, Model model) {

        // simulate
        int betBaseAmount = 1;
        int maxBetAmountTai = 0;
        int maxBetAmountXiu = 0;

//        int loseNumber = 0;

        int totalTaiWinAmount = 0;
        int totalXiuWinAmount = 0;

        int totalTaiBetAmount = 0;
        int totalXiuBetAmount = 0;

        int betAmountInTai = betBaseAmount;
        int betAmountInXiu = betBaseAmount;

        int taiConstantlyLoseNum = 0;
        int xiuConstantlyLoseNum = 0;

        for (Result result : resultList) {
            // bet cả 2 server theo quy tắc dãy số fibonacci
            if (TAI.equals(result.getBetResult())) {
                totalTaiBetAmount += betAmountInTai;
                totalXiuBetAmount += betAmountInXiu;
                totalTaiWinAmount += betAmountInTai * 2;

                xiuConstantlyLoseNum++;
                taiConstantlyLoseNum = 0;


                // tính lại tiền bet sau mỗi ván
                if (xiuConstantlyLoseNum > overByNumber) {
                    xiuConstantlyLoseNum = 0;
                    betAmountInXiu = betBaseAmount;
                } else {
                    betAmountInXiu = betAmountInXiu * 2;
                }

                betAmountInTai = betBaseAmount;

            }
            if (XIU.equals(result.getBetResult())) {
                totalTaiBetAmount += betAmountInTai;
                totalXiuBetAmount += betAmountInXiu;
                totalXiuWinAmount += betAmountInXiu * 2;

                xiuConstantlyLoseNum = 0;
                taiConstantlyLoseNum++;

                // tính lại tiền bet sau mỗi ván
                if (taiConstantlyLoseNum > overByNumber) {
                    taiConstantlyLoseNum = 0;
                    betAmountInTai = betBaseAmount;
                } else {
                    betAmountInTai = betAmountInTai * 2;
                }
                betAmountInXiu = betBaseAmount;
            }

            if (betAmountInTai >= maxBetAmountTai) {
                maxBetAmountTai = betAmountInTai;
            }
            if (betAmountInXiu >= maxBetAmountXiu) {
                maxBetAmountXiu = betAmountInXiu;
            }


        }

        model.addAttribute("totalTaiWinAmountMartingale", totalTaiWinAmount);
        model.addAttribute("totalXiuWinAmountMartingale", totalXiuWinAmount);
        model.addAttribute("totalTaiBetAmountMartingale", totalTaiBetAmount);
        model.addAttribute("totalXiuBetAmountMartingale", totalXiuBetAmount);
        model.addAttribute("NETMartingale", totalXiuWinAmount + totalTaiWinAmount - totalTaiBetAmount - totalXiuBetAmount);
        model.addAttribute("maxBetAmountTaiMartingale", maxBetAmountTai);
        model.addAttribute("maxBetAmountXiuMartingale", maxBetAmountXiu);
    }

    public static void simulatedBetByMixed(List<Result> resultList, int overByNumber, Model model) {

        // simulate
        int betBaseAmount = 1;
        int maxBetAmountTai = 0;
        int maxBetAmountXiu = 0;

        int changePoint = 5;

        int totalTaiWinAmount = 0;
        int totalXiuWinAmount = 0;

        int totalTaiBetAmount = 0;
        int totalXiuBetAmount = 0;

        int betAmountInTai = betBaseAmount;
        int betAmountInXiu = betBaseAmount;

        int taiConstantlyLoseNum = 0;
        int xiuConstantlyLoseNum = 0;

        for (Result result : resultList) {
            // bet cả 2 server theo quy tắc dãy số fibonacci
            if (TAI.equals(result.getBetResult())) {
                totalTaiBetAmount += betAmountInTai;
                totalXiuBetAmount += betAmountInXiu;
                totalTaiWinAmount += betAmountInTai * 2;

                xiuConstantlyLoseNum++;
                taiConstantlyLoseNum = 0;

                if (xiuConstantlyLoseNum >= changePoint) {
                    betAmountInXiu = betBaseAmount * findFibonacciByIndex(xiuConstantlyLoseNum);
                    // tính lại tiền bet sau mỗi ván
                    if (xiuConstantlyLoseNum > overByNumber) {
                        xiuConstantlyLoseNum = 0;
                        betAmountInXiu = betBaseAmount;
                    }
                } else {
                    betAmountInXiu = betAmountInXiu * 2;
                }

                betAmountInTai = betBaseAmount;

            }
            if (XIU.equals(result.getBetResult())) {
                totalTaiBetAmount += betAmountInTai;
                totalXiuBetAmount += betAmountInXiu;
                totalXiuWinAmount += betAmountInXiu * 2;

                xiuConstantlyLoseNum = 0;
                taiConstantlyLoseNum++;

                if (taiConstantlyLoseNum >= changePoint) {
                    betAmountInTai = betBaseAmount * findFibonacciByIndex(xiuConstantlyLoseNum);
                    // tính lại tiền bet sau mỗi ván
                    if (xiuConstantlyLoseNum > overByNumber) {
                        taiConstantlyLoseNum = 0;
                        betAmountInTai = betBaseAmount;
                    }
                } else {
                    betAmountInTai = betAmountInTai * 2;
                }
                betAmountInXiu = betBaseAmount;
            }

            if (betAmountInTai >= maxBetAmountTai) {
                maxBetAmountTai = betAmountInTai;
            }
            if (betAmountInXiu >= maxBetAmountXiu) {
                maxBetAmountXiu = betAmountInXiu;
            }


        }

        model.addAttribute("totalTaiWinAmountMixed", totalTaiWinAmount);
        model.addAttribute("totalXiuWinAmountMixed", totalXiuWinAmount);
        model.addAttribute("totalTaiBetAmountMixed", totalTaiBetAmount);
        model.addAttribute("totalXiuBetAmountMixed", totalXiuBetAmount);
        model.addAttribute("NETMixed", totalXiuWinAmount + totalTaiWinAmount - totalTaiBetAmount - totalXiuBetAmount);
        model.addAttribute("maxBetAmountTaiMixed", maxBetAmountTai);
        model.addAttribute("maxBetAmountXiuMixed", maxBetAmountXiu);
    }

    public static void simulatedBetByParoliBreak(List<Result> resultList, int overByNumber, Model model) {

        // simulate
        int betBaseAmount = 1;
        int maxBetAmountTai = 0;
        int maxBetAmountXiu = 0;

//        int loseNumber = 0;

        int totalTaiWinAmount = 0;
        int totalXiuWinAmount = 0;

        int totalTaiBetAmount = 0;
        int totalXiuBetAmount = 0;

        int betAmountInTai = betBaseAmount;
        int betAmountInXiu = betBaseAmount;

        int taiConstantlyLoseNum = 0;
        int xiuConstantlyLoseNum = 0;

        for (Result result : resultList) {
            // bet cả 2 server theo quy tắc dãy số fibonacci
            if (TAI.equals(result.getBetResult())) {
                totalTaiBetAmount += betAmountInTai;
                totalXiuBetAmount += betAmountInXiu;
                totalTaiWinAmount += betAmountInTai * 2;

                xiuConstantlyLoseNum++;
                taiConstantlyLoseNum = 0;

                // tính lại tiền bet sau mỗi ván
                if (xiuConstantlyLoseNum < overByNumber) {
                    betAmountInTai = betAmountInTai * 2;
                } else {
                    break;
                }

                betAmountInXiu = betBaseAmount;

            }
            if (XIU.equals(result.getBetResult())) {
                totalTaiBetAmount += betAmountInTai;
                totalXiuBetAmount += betAmountInXiu;
                totalXiuWinAmount += betAmountInXiu * 2;

                xiuConstantlyLoseNum = 0;
                taiConstantlyLoseNum++;

                // tính lại tiền bet sau mỗi ván
                if (taiConstantlyLoseNum < overByNumber) {
                    betAmountInXiu = betAmountInXiu * 2;
                } else {
                    break;
                }
                betAmountInTai = betBaseAmount;
            }

            // get max of bet amount
            if (betAmountInTai >= maxBetAmountTai) {
                maxBetAmountTai = betAmountInTai;
            }
            if (betAmountInXiu >= maxBetAmountXiu) {
                maxBetAmountXiu = betAmountInXiu;
            }
        }

        model.addAttribute("totalTaiWinAmountParoliBreak", totalTaiWinAmount);
        model.addAttribute("totalXiuWinAmountParoliBreak", totalXiuWinAmount);
        model.addAttribute("totalTaiBetAmountParoliBreak", totalTaiBetAmount);
        model.addAttribute("totalXiuBetAmountParoliBreak", totalXiuBetAmount);
        model.addAttribute("NETParoliBreak", totalXiuWinAmount + totalTaiWinAmount - totalTaiBetAmount - totalXiuBetAmount);
        model.addAttribute("maxBetAmountTaiParoliBreak", maxBetAmountTai);
        model.addAttribute("maxBetAmountXiuParoliBreak", maxBetAmountXiu);
    }

    public static void simulatedBetByParoli(List<Result> resultList, int overByNumber, Model model) {

        // simulate
        int betBaseAmount = 1;
        int maxBetAmountTai = 0;
        int maxBetAmountXiu = 0;

//        int loseNumber = 0;

        int totalTaiWinAmount = 0;
        int totalXiuWinAmount = 0;

        int totalTaiBetAmount = 0;
        int totalXiuBetAmount = 0;

        int betAmountInTai = betBaseAmount;
        int betAmountInXiu = betBaseAmount;

        int taiConstantlyLoseNum = 0;
        int xiuConstantlyLoseNum = 0;

        for (Result result : resultList) {
            // bet cả 2 server theo quy tắc dãy số fibonacci
            if (TAI.equals(result.getBetResult())) {
                totalTaiBetAmount += betAmountInTai;
                totalXiuBetAmount += betAmountInXiu;
                totalTaiWinAmount += betAmountInTai * 2;

                xiuConstantlyLoseNum++;
                taiConstantlyLoseNum = 0;

                // tính lại tiền bet sau mỗi ván
                if (xiuConstantlyLoseNum < overByNumber) {
                    betAmountInTai = betAmountInTai * 2;
                } else {
                    betAmountInTai = betBaseAmount;
                }

                betAmountInXiu = betBaseAmount;

            }
            if (XIU.equals(result.getBetResult())) {
                totalTaiBetAmount += betAmountInTai;
                totalXiuBetAmount += betAmountInXiu;
                totalXiuWinAmount += betAmountInXiu * 2;

                xiuConstantlyLoseNum = 0;
                taiConstantlyLoseNum++;

                // tính lại tiền bet sau mỗi ván
                if (taiConstantlyLoseNum < overByNumber) {
                    betAmountInXiu = betAmountInXiu * 2;

                } else {
                    betAmountInXiu = betBaseAmount;
                }
                betAmountInTai = betBaseAmount;
            }

            // get max of bet amount
            if (betAmountInTai >= maxBetAmountTai) {
                maxBetAmountTai = betAmountInTai;
            }
            if (betAmountInXiu >= maxBetAmountXiu) {
                maxBetAmountXiu = betAmountInXiu;
            }
        }

        model.addAttribute("totalTaiWinAmountParoli", totalTaiWinAmount);
        model.addAttribute("totalXiuWinAmountParoli", totalXiuWinAmount);
        model.addAttribute("totalTaiBetAmountParoli", totalTaiBetAmount);
        model.addAttribute("totalXiuBetAmountParoli", totalXiuBetAmount);
        model.addAttribute("NETParoli", totalXiuWinAmount + totalTaiWinAmount - totalTaiBetAmount - totalXiuBetAmount);
        model.addAttribute("maxBetAmountTaiParoli", maxBetAmountTai);
        model.addAttribute("maxBetAmountXiuParoli", maxBetAmountXiu);
    }

    public static void simulatedBetByFollowResult(List<Result> resultList, int overByNumber, Model model) {

        // simulate
        int betBaseAmount = 1;
        int loseNumber = 0;

        int maxLoseAmount = 0;

        int totalWinAmount = 0;

        int totalBetAmount = 0;

        int betAmount = betBaseAmount;

        String betIn = TAI;

        for (Result result : resultList) {
            // bet cả 2 server theo quy tắc dãy số fibonacci
            if (betIn.equals(result.getBetResult())) {
                totalBetAmount += betAmount;
                totalWinAmount += betAmount * 2;

                // tính lại tiền bet sau mỗi ván
                betAmount = betBaseAmount;

            } else {
                loseNumber++;
                if (betIn.equals(TAI)) {
                    betIn = XIU;
                } else {
                    betIn = TAI;
                }
                if (loseNumber >= overByNumber) {
                    betAmount = betBaseAmount;
                } else {
                    betAmount = betAmount * 2;
                }
            }

            if (betAmount > maxLoseAmount) {
                maxLoseAmount = betAmount;
            }
        }

        model.addAttribute("maxLoseAmountFollowResult", maxLoseAmount);
        model.addAttribute("totalWinAmountFollowResult", totalWinAmount);
        model.addAttribute("totalBetAmountFollowResult", totalBetAmount);
        model.addAttribute("NETFollowResult", totalWinAmount - totalBetAmount);
    }

    public static int findFibonacciByIndex(int index) {
        List<Integer> listFibonacci = new ArrayList<>();

        for (int i = 0; i < 1000000000; i++) {
            if (i == 0 || i == 1) {
                listFibonacci.add(1);
            } else {
                int numberA = listFibonacci.get(i - 2);
                int numberB = listFibonacci.get(i - 1);
                listFibonacci.add(numberA + numberB);
            }

            if (index == i) {
                return listFibonacci.get(i);
            }
        }
        return 0;
    }
}
