package org.example.parser;

import lombok.SneakyThrows;
import org.example.model.Entity;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class InputParser {
    
    private final BufferedReader reader;

    public InputParser(String fileNameToParse) {
        try {
            this.reader = new BufferedReader(new FileReader(fileNameToParse));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    
    @SneakyThrows
    public List<Entity> parseInfo(){
        List<Entity> result = new ArrayList<>();
        String lineIn = reader.readLine();
        while (lineIn != null){
            result.add(parseLine(lineIn));
        }
        return result;
    }
    
    private Entity parseLine(String line){
        String[] splitLine = line.split(" ");
        List<String> withoutEmpties = emptyRemover(splitLine);
        return Entity.builder()
                .gender(withoutEmpties.get(0))
                .plural(withoutEmpties.get(1))
                .age(Integer.parseInt(withoutEmpties.get(2)))
                .race(withoutEmpties.get(3))
                .parity(withoutEmpties.get(4))
                .married(withoutEmpties.get(5))
                .bwt(withoutEmpties.get(6))
                .smokeN(Integer.parseInt(withoutEmpties.get(7)))
                .drinkN(Integer.parseInt(withoutEmpties.get(8)))
                .firstep(withoutEmpties.get(9))
                .welfare(withoutEmpties.get(10))
                .smoker(withoutEmpties.get(11))
                .drinker(withoutEmpties.get(12))
                .wpre(withoutEmpties.get(13))
                .wgain(withoutEmpties.get(14))
                .education(withoutEmpties.get(15))
                .gestation(withoutEmpties.get(16))
                .build();
    }
    
    private List<String> emptyRemover(String[] array){
       return Stream.of(array)
                .filter(part -> part.length() != 0)
                .toList();
    }
    
}
