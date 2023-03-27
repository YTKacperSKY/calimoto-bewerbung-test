package de.kacper.main;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Grandparent extends Person {

    private final Set<Person> children;

    public Grandparent(LocalDate birthday, int height, String firstName, String lastName) {
        super(birthday, height, firstName, lastName);
        children = new HashSet<>();
    }

    public void addChild(Person child) {
        children.add(child);
    }

    public void removeChild(Person child) {
        children.remove(child);
    }

    public Set<Person> getChildren() {
        return children;
    }

    public void printChildren() {
        children.forEach(Person::printPersonalInfo);
    }

    public void printGrandchildren() {
        for (Person child : children) {
            if(child instanceof Parent) {
                Parent parent = (Parent) child;
                parent.printChildren();
            }
        }
    }

    @Override
    public void printInfo() {
        super.printInfo();
        System.out.println("Children:");
        printChildren();
        System.out.println("Grandchildren:");
        printGrandchildren();
    }
}
