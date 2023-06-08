package org.example.tasks;

import lombok.SneakyThrows;
import org.example.model.Entity;
import org.example.model.ResultEntity;
import org.example.model.ResultEntityVar1;
import org.example.parser.InputParser;

import java.io.*;
import java.util.List;

public class Tasks {

    private final List<Entity> data = InputParser.parseInfo();

    public double averageOfEducationAndNumberOfChildren(int numberOfChildren) {
        double averageOfEducation = data.stream()
                .filter(entity -> (entity.getParity() + entity.getPlural()) == numberOfChildren)
                .mapToInt(e -> Integer.parseInt(e.getEducation()))
                .average().orElseThrow(() -> new RuntimeException("ou"));
        return averageOfEducation;
    }

    public void task1Var1() {
        for (int i = 1; i < 8; i++) {
            System.out.println("Number of children: " + i +
                    " Average education: " + averageOfEducationAndNumberOfChildren(i));
        }
    }

    //помогите сделать это по расам....asian black hispanic other
    public double helper(String race){
        double averageOfWeight = data
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
        double avarageOfMarried = data
                .stream()
                .filter(entity -> entity.getMarried().equals("1"))
                .mapToInt(e -> Integer.parseInt(e.getGestation()))
                .average().orElseThrow(() -> new RuntimeException("ou"));
        double avarageOfNonMarried = data
                .stream()
                .filter(entity -> entity.getMarried().equals("0"))
                .mapToInt(e -> Integer.parseInt(e.getGestation()))
                .average().orElseThrow(() -> new RuntimeException("ou"));
        return avarageOfMarried < avarageOfNonMarried;
    }

    public boolean task2Var2(){
        double averageOfSmokers = data
                .stream()
                .filter(entity -> entity.getSmoker().equals("Y"))
                .mapToInt(e -> Integer.parseInt(e.getGestation()))
                .average().orElseThrow(() -> new RuntimeException("ou"));
        double averageOfNonSmoker = data
                .stream()
                .filter(entity -> entity.getSmoker().equals("N"))
                .mapToInt(e -> Integer.parseInt(e.getGestation()))
                .average().orElseThrow(() -> new RuntimeException("ou"));
        return averageOfNonSmoker > averageOfSmokers;
    }

    @SneakyThrows
    public void task3Var2(){
        List<ResultEntity> newData = data.stream().map(entity -> ResultEntity.builder()
                .educationTime(entity.getEducation())
                .motherRace(entity.getRace())
                .build())
                .toList();

        File file2 = new File("FilesVar2.bin");
        FileOutputStream fos = new FileOutputStream(file2);
        ObjectOutputStream serial = new ObjectOutputStream(fos);
        for (ResultEntity obj: newData) {
            serial.writeObject(obj);
            serial.flush();
        }
        fos.close();
        serial.close();

        FileInputStream ios = new FileInputStream(file2);
        ObjectInputStream deserial = new ObjectInputStream(ios);
        ResultEntity res = null;
        Object obj;
        System.out.println("Race; Education Time");
        while (true) {
            try {
                res = (ResultEntity) deserial.readObject();
                System.out.println(res.getMotherRace() + "; " + res.getEducationTime());
            }
            catch (EOFException e) {
                System.out.println("It's all");
                break;
            }
        }
    }
    @SneakyThrows
    public void task3Var1() {
        List<ResultEntityVar1> newData = data.stream().map(entity -> ResultEntityVar1.builder()
                        .gender(entity.getGender())
                        .married(entity.getMarried())
                        .gestation(entity.getGestation())
                        .build())
                .toList();

        File file1 = new File("FilesVar1.bin");
        FileOutputStream fos = new FileOutputStream(file1);
        ObjectOutputStream serial = new ObjectOutputStream(fos);
        for (ResultEntityVar1 obj: newData) {
            serial.writeObject(obj);
            serial.flush();
        }
        fos.close();
        serial.close();

        FileInputStream ios = new FileInputStream(file1);
        ObjectInputStream deserial = new ObjectInputStream(ios);
        ResultEntityVar1 res = null;
        Object obj;
        System.out.println("Gender; Married; Gestation");
        while (true) {
            try {
                res = (ResultEntityVar1) deserial.readObject();
                System.out.println(res.getGender() + "; " + res.getMarried() + "; " + res.getGestation());
            }
            catch (EOFException e) {
                System.out.println("It's all");
                break;
            }
        }
    }
}
