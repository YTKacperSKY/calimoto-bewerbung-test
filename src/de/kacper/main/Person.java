package de.kacper.main;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.ToIntFunction;

public class Person {

    private final LocalDate birthday;
    private final DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private final int height;
    private final String firstName;
    private final String lastName;

    public Person(LocalDate birthday, int height, String firstName, String lastName) {
        this.birthday = birthday;
        this.height = height;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getAge() {
        return (int) ChronoUnit.YEARS.between(birthday, LocalDate.now());
    }

    public int getHeight() {
        return height;
    }

    public String getName() {
        return firstName + " " + lastName;
    }

    public void printInfo() {
        printPersonalInfo();
    }

    public void printPersonalInfo() {
        System.out.printf("name: %s %s, birthday: %s, height: %d\n", firstName, lastName, birthday.format(format), height);
    }

    private static List<Person> sort(List<Person> list, ToIntFunction<? super Person> function, boolean desc) {
        list.sort(Comparator.comparingInt(function));
        if(desc) {
            Collections.reverse(list);
        }
        return list;
    }

    public static List<Person> sortByAge(List<Person> list, boolean desc) {
        return sort(list, Person::getAge, desc);
    }

    public static List<Person> sortByHeight(List<Person> list, boolean desc) {
        return sort(list, Person::getHeight, desc);
    }
}
