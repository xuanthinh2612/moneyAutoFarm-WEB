package monneyFarming.controller;

import monneyFarming.dataService.InitValueService;
import monneyFarming.dataService.ResultService;
import monneyFarming.model.InitValue;
import monneyFarming.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class AdminPage {

    @Autowired
    ResultService resultService;

    @Autowired
    InitValueService initValueService;

    @ModelAttribute("initValue")
    public InitValue initValue() {
        return initValueService.findFirstValue();
    }

    @ModelAttribute("tableList")
    public List<List<List<Result>>> getResultList() {
        return handleListResult();
    }

    @GetMapping("")
    public String homePage() {
        return "index";
    }

    @RequestMapping(value = "changeBetAction", params = "startProgram")
    public String startProgram(Model model) {
//        initValue.setReadyStatus(InitValue.START_PROGRAM);
//        initValueService.save(initValue);
//        TaiXiuBetting newProgram = new TaiXiuBetting(initValueService, resultService);
//        newProgram.start();
        return "redirect:/";
    }

    @RequestMapping(value = "changeBetAction", params = "openMiniGame")
    public String openMiniGame() {

        InitValue initValue = initValueService.findFirstValue();
        initValue.setReadyStatus(InitValue.OPEN_MINI_GAME);
        initValueService.save(initValue);

        return "redirect:/";
    }

    @RequestMapping(value = "changeBetAction", params = "stopProgram")
    public String stopProgram(@ModelAttribute("initValue") InitValue initValue) {
        initValue.setReadyStatus(InitValue.STOP_PROGRAM);
        initValueService.save(initValue);

        return "redirect:/";
    }

    @RequestMapping(value = "changeBetAction", params = "startBet")
    public String startBet(@ModelAttribute("initValue") InitValue initValue) {

        initValue.setReadyStatus(InitValue.START_BET);
        initValueService.save(initValue);

        return "redirect:/";
    }

    @RequestMapping(value = "changeBetAction", params = "stopBet")
    public String stopBet(@ModelAttribute("initValue") InitValue initValue) {
        initValue.setReadyStatus(InitValue.STOP_BET);
        initValueService.save(initValue);

        return "redirect:/";
    }


    private List<List<List<Result>>> handleListResult() {

        int COLUM_MAX_SIZE = 5;
        int MAX_COLUM_PER_TABLE = 30;
        List<Result> totalResult = resultService.findAllLimitBy(10000000L);
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
                i++;
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
