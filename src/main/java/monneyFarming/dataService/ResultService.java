package monneyFarming.dataService;

import monneyFarming.model.Result;
import monneyFarming.repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
            if (columList.size() < MAX_COLUM_PER_TABLE) {
                columList.add(column);
            } else {
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
