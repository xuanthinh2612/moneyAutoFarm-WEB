package monneyFarming.dto;

public class OverviewDto {
    public OverviewDto() {
    }

    String winAmount;
    int totalNumber;
    int biggestAmount;

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
}
