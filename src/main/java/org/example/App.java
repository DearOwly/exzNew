package org.example;

import org.example.model.Entity;
import org.example.parser.InputParser;

import java.util.Arrays;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        InputParser parser = new InputParser("input.txt");
        List<Entity> entities = parser.parseInfo();
        String line = "F 1 31 hispanic  4 1 3118  0  0 1 0 N N 122  22  5 40";
        System.out.println(Arrays.toString(line.split(" ")));

    }
}
