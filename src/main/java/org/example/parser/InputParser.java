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
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InputParser {
    private static final BufferedReader reader;

    static {
        try {
            reader = new BufferedReader(new FileReader("input.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @SneakyThrows
    public static List<Entity> parseInfo(){
        List<Entity> result = new ArrayList<>();
        String lineIn;
        while ((lineIn = reader.readLine()) != null){
            String[] splitLine = lineIn.split(" ");
            List<String> withoutEmpties = emptyRemover(splitLine);
            result.add(Entity.builder()
                    .gender(withoutEmpties.get(0))
                    .plural(Integer.parseInt(withoutEmpties.get(1)))
                    .age(Integer.parseInt(withoutEmpties.get(2)))
                    .race(withoutEmpties.get(3))
                    .parity(Integer.parseInt(withoutEmpties.get(4)))
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
                    .build());
        }
        return result;
    }
    
    private static List<String> emptyRemover(String[] array){
       return Stream.of(array)
                .filter(part -> part.length() != 0)
                .collect(Collectors.toList());
    }
    
}
