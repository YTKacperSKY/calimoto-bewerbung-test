package de.kacper.main;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Parent extends Person {

    private final Set<Person> parents;
    private final Set<Person> children;

    public Parent(LocalDate birthday, int height, String firstName, String lastName) {
        super(birthday, height, firstName, lastName);
        parents = new HashSet<>();
        children = new HashSet<>();
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


    public Set<Person> getChildren() {
        return children;
    }

    public void addChild(Person child) {
        children.add(child);
    }

    public void removeChild(Person child) {
        children.remove(child);
    }

    public void printChildren() {
        children.forEach(Person::printPersonalInfo);
    }

    public void printParents() {
        parents.forEach(Person::printPersonalInfo);
    }

    @Override
    public void printInfo() {
        super.printInfo();
        System.out.println("Parents:");
        printParents();
        System.out.println("Children:");
        printChildren();
    }

}
