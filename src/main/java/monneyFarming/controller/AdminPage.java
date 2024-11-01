package monneyFarming.controller;

import monneyFarming.dataService.InitValueService;
import monneyFarming.dataService.ResultService;
import monneyFarming.dto.OverviewDto;
import monneyFarming.model.InitValue;
import monneyFarming.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
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

    @ModelAttribute("initValue1")
    public InitValue getInitValue1() {
        return initValueService.findAll().get(0);
    }

    @ModelAttribute("initValue2")
    public InitValue getInitValue2() {
        return initValueService.findAll().get(1);
    }

    @ModelAttribute("initValue3")
    public InitValue getInitValue3() {
        return initValueService.findAll().get(2);
    }

    @GetMapping("/")
    public String homePage(Model model) {
        // default time
        String startTime = "00:00:00";
        String endTime = "23:59:59";
        // default is today date
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String date = formatter.format(new Date());
        List<List<List<Result>>> tableList = resultService.handleListResult(date, startTime, endTime);

        model.addAttribute("selectedDate", date);
        model.addAttribute("startTime", startTime);
        model.addAttribute("endTime", endTime);
        model.addAttribute("tableList", tableList);
        // set up data to show
        int overByNumber = getInitValue1().getMaxLoseNum() - 1; // maxLoseNumber - 1 = coverAbleNumber.(EX: maxLoseNumber = 7 -> cover 6 time
        OverviewDto overview = resultService.getOverviewInfo(date, startTime, endTime, overByNumber);
        model.addAttribute("overview", overview);

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
            // set up data to show
            int overByNumber = getInitValue1().getMaxLoseNum() - 1; // maxLoseNumber - 1 = coverAbleNumber.(EX: maxLoseNumber = 7 -> cover 6 time
            OverviewDto overview = resultService.getOverviewInfo(date, startTime, endTime, overByNumber);
            model.addAttribute("overview", overview);
        }

        return "index";
    }

    @PostMapping("/updateInitValue1")
    public String updateInitValue1(@ModelAttribute("initValue1") InitValue initValue, Model model) {
        initValueService.save(initValue);
        return "redirect:/";
    }

    @PostMapping("/updateInitValue2")
    public String updateInitValue2(@ModelAttribute("initValue2") InitValue initValue, Model model) {
        initValueService.save(initValue);
        return "redirect:/";
    }

    @PostMapping("/updateInitValue3")
    public String updateInitValue3(@ModelAttribute("initValue3") InitValue initValue, Model model) {
        initValueService.save(initValue);
        return "redirect:/";
    }


}
