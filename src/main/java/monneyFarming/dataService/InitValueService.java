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


    public void save(InitValue initValue) {
        initValueRepository.save(initValue);
    }

    public InitValue findById(Long id) {
        return initValueRepository.findById(id).orElse(null);
    }

    public List<InitValue> findAll() {
        return initValueRepository.findAll();
    }
}
