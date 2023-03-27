package de.kacper.main;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Child extends Person {

    private final Set<Person> parents;

    public Child(LocalDate birthday, int height, String firstName, String lastName) {
        super(birthday, height, firstName, lastName);
        parents = new HashSet<>();
    }

    public Set<Person> getParents() {
        return parents;
    }

    public void addParent(Person parent) {
        parents.add(parent);
    }

    public void removeParent(Person parent) {
        parents.remove(parent);
    }

    public void printParents() {
        parents.forEach(Person::printPersonalInfo);
    }

    public void printGrandparents() {
        for (Person parent : parents) {
            if(parent instanceof Parent) {
                ((Parent) parent).printParents();
            }
        }
    }

    @Override
    public void printInfo() {
        super.printInfo();
        System.out.println("Parents:");
        printParents();
        System.out.println("Grandparents:");
        printGrandparents();
    }
}
