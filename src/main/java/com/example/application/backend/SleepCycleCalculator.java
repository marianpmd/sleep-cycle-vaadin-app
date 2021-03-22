package com.example.application.backend;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;

@Component
public class SleepCycleCalculator {

    private final int averageFallingAsleepTime = 14;


    public SleepCycleCalculator() {
    }

    public List<String> computeAllCycles(){
        LocalDateTime now = LocalDateTime.now();
        String[] wakeTimes = new String[7];

        for (int i = 0 ;i <= 6 ;i++ ){
            LocalDateTime temp = now.plus(averageFallingAsleepTime + (90 * i)
                    , ChronoUnit.MINUTES);
            wakeTimes[i] = temp.format(DateTimeFormatter.ofPattern("HH:mm"));
        }
        return Arrays.asList(wakeTimes);
    }



}
