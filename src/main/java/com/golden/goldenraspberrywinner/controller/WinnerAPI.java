package com.golden.goldenraspberrywinner.controller;

import com.golden.goldenraspberrywinner.bean.WinnersList;
import com.golden.goldenraspberrywinner.service.WinnersService;
import com.golden.goldenraspberrywinner.util.LoadDataBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class WinnerAPI {

    @Autowired
    private LoadDataBase loadDataBase;

    @Autowired
    private WinnersService winnersService;

    @GetMapping("/winners")
    public WinnersList teste() {
        loadDataBase.loadDataBase();
        return winnersService.findWinnersList();
    }

}
