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
}
