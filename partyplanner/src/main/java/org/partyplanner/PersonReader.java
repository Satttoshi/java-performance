package org.partyplanner;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PersonReader {
    public List<Person> readAllPersons(String fileName) throws IOException {
        List<String> lines = Files.readAllLines(Path.of(fileName));
        return convertLinesToPersons(lines);
    }

    private List<Person> convertLinesToPersons(List<String> lines) {
        List<Person> people = new ArrayList<>();
        for (String line : lines) {
            String[] parts = line.split(";");
            people.add(new Person(parseDate(parts[4].trim())));
        }
        return people;
    }


    private static LocalDate parseDate(String dateText) {
        return LocalDate.parse(dateText, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }
}
