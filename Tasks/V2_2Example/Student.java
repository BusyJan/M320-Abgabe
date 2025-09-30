package kurs;

import java.util.ArrayList;
import java.util.List;

public class Student extends Person {
    private final String matrikelNr;
    private String studiengang;
    private final List<Integer> noten = new ArrayList<>();

    public Student(String firstName, String lastName, int age, String matrikelNr, String studiengang) {
        super(firstName, lastName, age);
        this.matrikelNr = matrikelNr;
        this.studiengang = studiengang;
    }

    public String getMatrikelNr() {
        return matrikelNr;
    }

    public String getStudiengang() {
        return studiengang;
    }

    public void setStudiengang(String studiengang) {
        this.studiengang = studiengang;
    }

    public void addNote(int note) {
        if (note >= 1 && note <= 6) noten.add(note);
    }

    public double getDurchschnitt() {
        if (noten.isEmpty()) return 0.0;
        return noten.stream().mapToInt(Integer::intValue).average().orElse(0.0);
    }

    @Override
    public String getRole() {
        return "Student";
    }

    @Override
    public String toString() {
        return super.toString() + " | Matrikel: " + matrikelNr + " | Studiengang: " + studiengang + " | Schnitt: " + String.format("%.2f", getDurchschnitt());
    }
}
