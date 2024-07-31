package com.example.springbootdatatransfer.controller;

import com.example.springbootdatatransfer.service.AverageCalculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResultController {
    @Autowired
    private AverageCalculationService averageCalculationService;

    @GetMapping("/publish-results")
    public void publishResults() {
        averageCalculationService.calculateAndPublishResults();
    }
}
