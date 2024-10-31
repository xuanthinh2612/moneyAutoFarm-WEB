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

    public OverviewDto getOverviewInfo(String date, String startTime, String endTime) {
        OverviewDto overview = new OverviewDto();
        List<Result> totalResult = resultRepository.findByDateAndTime(date, startTime, endTime);
        // win amount
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
        winAmount = NumberFormat.getNumberInstance(Locale.US).format(totalWinAmount - totalBetAmount);

        overview.setBiggestAmount(biggestAmount);
        overview.setTotalNumber(totalResult.size());
        overview.setWinAmount(winAmount);

        return overview;
    }

    public List<Result> findByDateAndTime(String date, String startTime, String endTime) {
        return resultRepository.findByDateAndTime(date, startTime, endTime);
    }

    public List<List<List<Result>>> handleListResult(String date, String startTime, String endTime) {
        List<Result> totalResult = resultRepository.findByDateAndTime(date, startTime, endTime);

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
