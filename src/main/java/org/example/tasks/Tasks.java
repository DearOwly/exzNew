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

    public List<Entity> task0(){
        return parser.parseInfo();
    }

    public void task1Var1(){

    }

    //помогите сделать это по расам....asian black hispanic other
    public double helper(String race){
        double averageOfWeight = task0()
                .stream()
                .filter(entity -> entity.getRace().equals(race))
                .mapToInt(e -> Integer.parseInt(e.getBwt()))
                .average().orElseThrow(() -> new RuntimeException("ou"));
        return averageOfWeight;
    }
    public void task1Var2(){
        double averageOfWeightWhite = helper("white");
        double averageOfWeightBlack = helper("black");
        double averageOfWeightHispanic = helper("hispanic");
        double averageOfWeightAsian = helper("asian");
        double averageOfWeightOther = helper("other");
    }

    public boolean task2Var1(){
        double avarageOfMarried = task0()
                .stream()
                .filter(entity -> entity.getMarried().equals("1"))
                .mapToInt(e -> Integer.parseInt(e.getGestation()))
                .average().orElseThrow(() -> new RuntimeException("ou"));
        double avarageOfNonMarried = task0()
                .stream()
                .filter(entity -> entity.getMarried().equals("0"))
                .mapToInt(e -> Integer.parseInt(e.getGestation()))
                .average().orElseThrow(() -> new RuntimeException("ou"));
        return avarageOfMarried > avarageOfNonMarried;
    }

    public boolean task2Var2(){
        double averageOfSmokers = task0()
                .stream()
                .filter(entity -> entity.getSmoker().equals("Y"))
                .mapToInt(e -> Integer.parseInt(e.getGestation()))
                .average().orElseThrow(() -> new RuntimeException("ou"));
        double averageOfNonSmoker = task0()
                .stream()
                .filter(entity -> entity.getSmoker().equals("N"))
                .mapToInt(e -> Integer.parseInt(e.getGestation()))
                .average().orElseThrow(() -> new RuntimeException("ou"));
        return averageOfNonSmoker > averageOfSmokers;
    }

    public void task3Var2(){
        task0().stream().map(entity -> ResultEntity.builder()
                .educationTime(entity.getEducation())
                .motherRace(entity.getRace())
                .build().toString()/*.getBytes(StandardCharsets.UTF_8)*/)
                .forEach(System.out::println);
    }

}
