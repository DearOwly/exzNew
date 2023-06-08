package org.example.tasks;

import org.example.model.Entity;
import org.example.model.ResultEntity;
import org.example.parser.InputParser;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.OptionalDouble;

public class Tasks {

    private final InputParser parser;

    public Tasks(InputParser parser) {
        this.parser = parser;
    }

    public List<Entity> task1(){
        return parser.parseInfo();
    }

    public void task2(){
        double averageOfWeight = task1()
                .stream()
                .filter(entity -> entity.getBwt() != null)
                .mapToInt(e -> Integer.parseInt(e.getBwt()))
                .average().orElseThrow(() -> new RuntimeException("ou"));
    }

    public boolean task3(){
        double averageOfSmokers = task1()
                .stream()
                .filter(entity -> entity.getGender().equals("F")
                        && entity.getSmoker().equals("Y"))
                .mapToInt(e -> Integer.parseInt(e.getBwt()))
                .average().orElseThrow(() -> new RuntimeException("ou"));
        double averageOfNonSmoker = task1()
                .stream()
                .filter(entity -> entity.getGender().equals("F")
                        && entity.getSmoker().equals("N"))
                .mapToInt(e -> Integer.parseInt(e.getBwt()))
                .average().orElseThrow(() -> new RuntimeException("ou"));
        return averageOfNonSmoker > averageOfSmokers;
    }

    public void task4(){
        task1().stream().map(entity -> ResultEntity.builder()
                .educationTime(entity.getEducation())
                .motherRace(entity.getRace())
                .build().toString()/*.getBytes(StandardCharsets.UTF_8)*/)
                .forEach(System.out::println);
    }

}
