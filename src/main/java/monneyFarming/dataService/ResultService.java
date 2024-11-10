package monneyFarming.dataService;

import monneyFarming.dto.OverviewDto;
import monneyFarming.model.Result;
import monneyFarming.repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static monneyFarming.herlper.CommonConst.TAI;
import static monneyFarming.herlper.CommonConst.XIU;

@Service
public class ResultService {
    @Autowired
    ResultRepository resultRepository;

    public Result save(Result result) {
        return resultRepository.save(result);

    }

    public Result findResultById(Long id) {
        return resultRepository.findById(id).orElse(null);
    }

    public List<Result> findAll(Long id) {
        return resultRepository.findAll();
    }

    public List<Result> findByDate(String date) {
        return resultRepository.findByDate(date);
    }

    public OverviewDto getOverviewInfo(String date, String startTime, String endTime, int overByNumber) {
        OverviewDto overview = new OverviewDto();
        List<Result> totalResultByServer1 = resultRepository.findByDateAndTimeByServer1(date, startTime, endTime);
        List<Result> totalResultByServer2 = resultRepository.findByDateAndTimeByServer2(date, startTime, endTime);
        List<Result> totalResultByServer3 = resultRepository.findByDateAndTimeByServer3(date, startTime, endTime);
        List<Result> totalResult = resultRepository.findByDateAndTimeByAllServer(date, startTime, endTime);

        String winAmount;
        int biggestAmount = 0;
        int totalBetAmount = 0;
        int totalWinAmount = 0;
        for (Result r :
                totalResult) {
            totalBetAmount += r.getBetAmount();
            totalWinAmount += r.getWinAmount();
            // find the biggest bet amount
            if (r.getBetAmount() > biggestAmount) {
                biggestAmount = r.getBetAmount();
            }
        }

        int taiNumber = 0;
        int xiuNumber = 0;
        int undefinedNumber = 0;

        int totalBetAmount1 = 0;
        int totalWinAmount1 = 0;
        for (Result r :
                totalResultByServer1) {
            totalBetAmount1 += r.getBetAmount();
            totalWinAmount1 += r.getWinAmount();
            if (r.getBetResult().equals(TAI)) {
                taiNumber++;
            } else if (r.getBetResult().equals(XIU)) {
                xiuNumber++;
            } else {
                undefinedNumber++;
            }
        }

        int totalBetAmount2 = 0;
        int totalWinAmount2 = 0;
        for (Result r :
                totalResultByServer2) {
            totalBetAmount2 += r.getBetAmount();
            totalWinAmount2 += r.getWinAmount();
        }

        int totalBetAmount3 = 0;
        int totalWinAmount3 = 0;
        for (Result r :
                totalResultByServer3) {
            totalBetAmount3 += r.getBetAmount();
            totalWinAmount3 += r.getWinAmount();
        }

        overview.setWinNetServer1(totalWinAmount1 - totalBetAmount1);
        overview.setWinNetServer2(totalWinAmount2 - totalBetAmount2);
        overview.setWinNetServer3(totalWinAmount3 - totalBetAmount3);

        // check for result of server1 and show in result table
        int changeConstantlyNumber = 0;
        int constantlyChangeCount = 0;
        int outOfCoverNumber = 0;
        int constantlyCount = 1; // start count from 1

        for (int i = 0; i < totalResultByServer1.size() - 1; i++) {
            Result result1 = totalResultByServer1.get(i);
            Result result2 = totalResultByServer1.get(i + 1);
            // find the biggest bet amount
            if (result1.getBetResult().equals(result2.getBetResult())) {
                // count if bet result are same
                constantlyCount++;
                if (constantlyChangeCount > overByNumber) {
                    changeConstantlyNumber++;
                }
                constantlyChangeCount = 0;

            } else {

                // count if bet result change
                constantlyChangeCount++;

                if (constantlyCount > overByNumber) {
                    outOfCoverNumber++;
                }
                constantlyCount = 1;
            }
        }
        winAmount = NumberFormat.getNumberInstance(Locale.US).format(totalWinAmount - totalBetAmount);

        overview.setOverByNumber(overByNumber); // max lose number
        overview.setTotalWinAmount(totalWinAmount); // Total Win
        overview.setTotalBetAmount(totalBetAmount); // Total Bet amount
        overview.setChangeConstantlyNumber(changeConstantlyNumber); // count for constantly change
        overview.setOutOfCoverNumber(outOfCoverNumber); // count for constantly
        overview.setBiggestAmount(biggestAmount);
        overview.setTotalNumber(totalResultByServer1.size());
        overview.setWinAmount(winAmount);
        overview.setTaiNumber(taiNumber);
        overview.setXiuNumber(xiuNumber);
        overview.setUndefinedNumber(undefinedNumber);

        return overview;
    }

    public List<Result> findByDateAndTimeByServer1(String date, String startTime, String endTime) {
        return resultRepository.findByDateAndTimeByServer1(date, startTime, endTime);
    }

    public List<List<List<Result>>> handleListResult(String date, String startTime, String endTime) {
        List<Result> totalResult = resultRepository.findByDateAndTimeByServer1(date, startTime, endTime);

        int COLUM_MAX_SIZE = 5;
        int MAX_COLUM_PER_TABLE = 35;
        List<List<Result>> columList = new ArrayList<>();
        List<List<List<Result>>> tableList = new ArrayList<>();

        for (int i = 0; i < totalResult.size(); i++) {
            List<Result> column = new ArrayList<>();
            for (int j = 0; j < COLUM_MAX_SIZE; j++) {
                if (column.size() == 0) {
                    column.add(totalResult.get(i));
                } else if (column.get(0).getBetResult().equals(totalResult.get(i).getBetResult())) {
                    column.add(totalResult.get(i));
                } else {
                    i--;
                    break;
                }
                if (j != COLUM_MAX_SIZE - 1) {
                    i++;
                }
                if (i >= totalResult.size()) {
                    break;
                }
            }
            columList.add(column);
            if (columList.size() >= MAX_COLUM_PER_TABLE) {
                tableList.add(columList);
                columList = new ArrayList<>();
            }
        }
        if (!columList.isEmpty()) {
            tableList.add(columList);
        }
        return tableList;
    }
}
