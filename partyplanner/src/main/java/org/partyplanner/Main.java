package org.partyplanner;

import java.io.IOException;
import java.time.Instant;
import java.time.MonthDay;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.time.temporal.ChronoUnit.MILLIS;

public class Main {
    public static void main(String[] args) throws IOException {
        Instant startOfThisApplication = Instant.now();
        String inputFile = "employees10000.csv";
        List<Person> people = new PersonReader().readAllPersons(inputFile);
        MonthDay biggestParty = findBiggestParty(people);
        String formattedBiggestParty = biggestParty.format(DateTimeFormatter.ofPattern("dd.MM"));
        System.out.println("Biggest party: " + formattedBiggestParty);
        System.out.println("Time spent: " + MILLIS.between(startOfThisApplication, Instant.now()) + " ms for input file " + inputFile);
    }

    private static MonthDay findBiggestParty(List<Person> people) {
        Map<MonthDay, Integer> birthdayFrequencyMap = new HashMap<>();
        MonthDay biggestParty = null;
        int biggestPartySize = 0;

        // O(n)
        for (Person person : people) {
            MonthDay birthday = MonthDay.from(person.birthday());
            int count = birthdayFrequencyMap.getOrDefault(birthday, 0) + 1;
            birthdayFrequencyMap.put(birthday, count);
            if (count > biggestPartySize) {
                biggestPartySize = count;
                biggestParty = birthday;
            }
        }
        return biggestParty;
    }

}