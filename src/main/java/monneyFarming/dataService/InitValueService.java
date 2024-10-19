package monneyFarming.dataService;

import monneyFarming.model.InitValue;
import monneyFarming.repository.InitValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InitValueService {
    @Autowired
    InitValueRepository initValueRepository;


    public InitValue save(InitValue initValue) {
        return initValueRepository.save(initValue);
    }

    public InitValue findInitValueById(Long id) {
        return initValueRepository.findById(id).orElse(null);
    }

    public InitValue findFirstValue() {
        List<InitValue> initValueList = initValueRepository.findAll();
        if (initValueList.size() > 0) {
            return initValueList.get(0);
        } else {
            return new InitValue();
        }
    }
}
