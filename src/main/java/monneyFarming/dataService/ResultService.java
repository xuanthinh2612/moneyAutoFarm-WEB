package monneyFarming.dataService;

import monneyFarming.model.Result;
import monneyFarming.repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public List<Result> findAllLimitBy(Long limitAmount) {
        return resultRepository.findAllLimitBy(limitAmount);
    }
}
