package com.example.springbootdatatransfer.service;

import com.example.springbootdatatransfer.repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AverageCalculationService {
    @Autowired
    private ResultRepository resultRepository;

    public void calculateAndPublishResults() {
        // This was optional, so its WIP
    }
}
