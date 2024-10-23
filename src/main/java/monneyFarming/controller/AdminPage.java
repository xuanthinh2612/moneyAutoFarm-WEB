package monneyFarming.controller;

import monneyFarming.dataService.InitValueService;
import monneyFarming.dataService.ResultService;
import monneyFarming.model.InitValue;
import monneyFarming.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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

    @GetMapping("/")
    public String homePage(Model model) {
        // default time
        String startTime = "00:00:00";
        String endTime = "23:59:00";
        // default is today date
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String today = formatter.format(new Date());
        List<List<List<Result>>> tableList = resultService.handleListResult(today, startTime, endTime);

        model.addAttribute("selectedDate", today);
        model.addAttribute("startTime", startTime);
        model.addAttribute("endTime", endTime);
        model.addAttribute("tableList", tableList);
        String winAmount = NumberFormat.getNumberInstance(Locale.US).format(resultService.calculateWinAmount(today, startTime, endTime));
        model.addAttribute("winAmount", winAmount);

        return "index";
    }

    @PostMapping("/")
    public String homePage(@RequestParam("date") String date,
                           @RequestParam("startTime") String startTime,
                           @RequestParam("endTime") String endTime,
                           Model model) {
        if (StringUtils.hasText(date) && StringUtils.hasText(startTime) && StringUtils.hasText(endTime)) {

            model.addAttribute("selectedDate", date);
            model.addAttribute("startTime", startTime);
            model.addAttribute("endTime", endTime);
            List<List<List<Result>>> tableList = resultService.handleListResult(date, startTime, endTime);
            model.addAttribute("tableList", tableList);
            model.addAttribute("winAmount", resultService.calculateWinAmount(date, startTime, endTime));
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
