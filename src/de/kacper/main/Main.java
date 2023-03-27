package de.kacper.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    static final DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String line = reader.readLine();
            while(!line.equals("stop")) {
                String[] input = line.split(" ");
                List<Person> list = parseInput(input);
                list.forEach(Person::printPersonalInfo);
                line = reader.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Person> parseInput(String[] input){
        List<Person> list = new ArrayList<>();

        for(int i = 0; i < input.length / 4; i++) {
            LocalDate birthday;
            String firstName;
            String lastName;
            int height;

            try {
                birthday = LocalDate.parse(input[i*4], format);
                firstName = input[i*4 +1];
                lastName = input[i*4 +2];
                height = Integer.parseInt(input[i*4 +3].replace("cm", ""));
            } catch (DateTimeParseException | NumberFormatException e) {
                continue;
            }

            Person person = new Person(birthday, height, firstName, lastName);
            if(firstName.length() < 3 || firstName.length() > 12) {
                continue;
            }

            if(lastName.length() < 3 || lastName.length() > 12) {
                continue;
            }

            if(height < 150 || height > 200) {
                continue;
            }

            int age = person.getAge();
            if(age < 18 || age > 100) {
                continue;
            }

            list.add(person);
        }

        List<Person> sortedList = Person.sortByAge(list, false);
        sortedList = Person.sortByHeight(sortedList, false);
        return sortedList;

    }
}
