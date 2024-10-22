package monneyFarming.controller;

import monneyFarming.dataService.InitValueService;
import monneyFarming.dataService.ResultService;
import monneyFarming.model.InitValue;
import monneyFarming.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
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

    @ModelAttribute("selectedDate")
    public String selectedDateDefault() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        // default is today date
        return formatter.format(new Date());
    }

    @ModelAttribute("startTime")
    public String getStartTime() {
        // Default start time is 00:00:00
        return "00:00:00";
    }

    @ModelAttribute("endTime")
    public String getEndTime() {
        // Default end time is 23:59:59
        return "23:59:00";
    }

    @ModelAttribute("tableList")
    public List<List<List<Result>>> getTableList() {
        String today = selectedDateDefault();
        return resultService.handleListResult(today, getStartTime(), getEndTime());
    }

    @GetMapping("/")
    public String homePage() {
        return "index";
    }

    @PostMapping("/")
    public String homePage(@RequestParam("date") String date,
                           @RequestParam("startTime") String startTime,
                           @RequestParam("endTime") String endTime,
                           Model model) {
        if (StringUtils.hasText(date) && StringUtils.hasText(startTime) && StringUtils.hasText(endTime)) {
//            startTime = startTime + ":00"; // Convert "HH:mm" to "HH:mm:ss"
//            endTime = endTime + ":00";

            model.addAttribute("selectedDate", date);
            model.addAttribute("startTime", startTime);
            model.addAttribute("endTime", endTime);
            List<List<List<Result>>> tableList = resultService.handleListResult(date, startTime, endTime);
            model.addAttribute("tableList", tableList);
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
