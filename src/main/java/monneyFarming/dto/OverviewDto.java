package monneyFarming.dto;

public class OverviewDto {
    public OverviewDto() {
    }

    String winAmount;
    int totalNumber;
    int biggestAmount;
    int outOfCoverNumber;
    int overByNumber;
    int changeConstantlyNumber;
    int totalWinAmount;
    int totalBetAmount;
    int winNetServer1;
    int winNetServer2;
    int winNetServer3;

    public int getBiggestAmount() {
        return biggestAmount;
    }

    public void setBiggestAmount(int biggestAmount) {
        this.biggestAmount = biggestAmount;
    }

    public int getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(int totalNumber) {
        this.totalNumber = totalNumber;
    }

    public String getWinAmount() {
        return winAmount;
    }

    public void setWinAmount(String winAmount) {
        this.winAmount = winAmount;
    }

    public int getOutOfCoverNumber() {
        return outOfCoverNumber;
    }

    public void setOutOfCoverNumber(int outOfCoverNumber) {
        this.outOfCoverNumber = outOfCoverNumber;
    }

    public int getOverByNumber() {
        return overByNumber;
    }

    public void setOverByNumber(int overByNumber) {
        this.overByNumber = overByNumber;
    }

    public int getChangeConstantlyNumber() {
        return changeConstantlyNumber;
    }

    public void setChangeConstantlyNumber(int changeConstantlyNumber) {
        this.changeConstantlyNumber = changeConstantlyNumber;
    }

    public int getTotalWinAmount() {
        return totalWinAmount;
    }

    public void setTotalWinAmount(int totalWinAmount) {
        this.totalWinAmount = totalWinAmount;
    }

    public int getTotalBetAmount() {
        return totalBetAmount;
    }

    public void setTotalBetAmount(int totalBetAmount) {
        this.totalBetAmount = totalBetAmount;
    }

    public int getWinNetServer1() {
        return winNetServer1;
    }

    public void setWinNetServer1(int winNetServer1) {
        this.winNetServer1 = winNetServer1;
    }

    public int getWinNetServer2() {
        return winNetServer2;
    }

    public void setWinNetServer2(int winNetServer2) {
        this.winNetServer2 = winNetServer2;
    }

    public int getWinNetServer3() {
        return winNetServer3;
    }

    public void setWinNetServer3(int winNetServer3) {
        this.winNetServer3 = winNetServer3;
    }
}
