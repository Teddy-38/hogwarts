package com.example.hogwarts.controller;

import com.example.hogwarts.service.InfoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/info")
public class InfoController {

    private final InfoService infoService;

    public InfoController(InfoService infoService) {
        this.infoService = infoService;
    }

    @GetMapping("/fast-sum")
    public long getSumOptimized() {
        return infoService.calculateSumOptimized();
    }
}