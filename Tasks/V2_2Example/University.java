package kurs;

import java.util.ArrayList;
import java.util.List;

public class University {
    private final String name;
    private final List<Person> personen = new ArrayList<>();

    public University(String name) {
        this.name = name;
    }

    public void addPerson(Person p) {
        if (p != null && !personen.contains(p)) personen.add(p);
    }

    public void listAll() {
        System.out.println("Universität: " + name);
        personen.forEach(System.out::println);
    }

    public List<Student> getStudents() {
        return personen.stream()
                .filter(p -> p instanceof Student)
                .map(p -> (Student) p)
                .toList();
    }

    public List<Professor> getProfessors() {
        return personen.stream()
                .filter(p -> p instanceof Professor)
                .map(p -> (Professor) p)
                .toList();
    }
}
