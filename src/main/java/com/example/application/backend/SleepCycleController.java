package com.example.application.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1")
public class SleepCycleController {
    private final SleepCycleCalculator calculator;

    @Autowired
    public SleepCycleController(SleepCycleCalculator calculator) {
        this.calculator = calculator;
    }

    @GetMapping
    public List<String> getHours(){
        return calculator.computeAllCycles();
    }

}
