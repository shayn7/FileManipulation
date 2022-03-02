package main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    private static List<String> table;

    public static void main(String[] args) throws IOException {
        readFile("ibdiagnet_output.txt", 181, 170);
        printWarningAndErrorMessages();
    }

    private static void readFile(String file, int start, int end) throws IOException {
        List<String> lines = Files.lines(Paths.get(file))
                .collect(Collectors.toList());
        table = getTableLines(lines, start, end);
    }

    private static List<String> getTableLines(List<String> lines, int start, int end) {
        lines.subList(start, lines.size()).clear();
        lines.subList(0, end).clear();
        return lines;
    }

    private static void printWarningAndErrorMessages(){
        for (String s : table) {
            String[] elements = s.split("\\s+");
            int numberOfErrors = Integer.parseInt(elements[elements.length - 1]);
            int numberOfWarnings = Integer.parseInt(elements[elements.length - 2]);
            if (numberOfErrors > 0 || numberOfWarnings > 0) {
                System.out.printf("%s section has %s Warnings and %s Errors%n", elements[1], numberOfWarnings, numberOfErrors);
            }
        }
    }
}

