package com.example.springbootdatatransfer.controller;

import com.example.springbootdatatransfer.service.DataTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataTransferController {
    @Autowired
    private DataTransferService dataTransferService;

    @PostMapping("/transfer-data")
    public void transferData() {
        dataTransferService.transferData();
    }
}

