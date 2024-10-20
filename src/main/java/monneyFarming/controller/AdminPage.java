package monneyFarming.controller;

import monneyFarming.dataService.InitValueService;
import monneyFarming.dataService.ResultService;
import monneyFarming.model.InitValue;
import monneyFarming.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
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
    public List<List<List<Result>>> getTableList() {
        String today = selectedDate();
        return resultService.handleListResult(today);
    }

    @ModelAttribute("selectedDate")
    public String selectedDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        // default is today date
        return formatter.format(new Date());
    }

    @GetMapping("/")
    public String homePage() {
        return "index";
    }

    @PostMapping("/")
    public String homePage(@RequestParam("date") String date, Model model) {
        if (!ObjectUtils.isEmpty(date)) {
            model.addAttribute("selectedDate", date);
            model.addAttribute("tableList", resultService.handleListResult(date));
        }

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


}
