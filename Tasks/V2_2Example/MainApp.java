package kurs;

public class MainApp {
    public static void main(String[] args) {
        University uni = new University("FH Zürich");

        Student s1 = new Student("Lukas", "Müller", 21, "S12345", "Informatik");
        s1.addNote(5);
        s1.addNote(4);
        s1.addNote(6);

        Student s2 = new Student("Sara", "Meier", 23, "S67890", "Wirtschaft");
        s2.addNote(3);
        s2.addNote(5);

        Professor p1 = new Professor("Anna", "Schmid", 45, "Software Engineering");
        Professor p2 = new Professor("Thomas", "Keller", 50, "BWL");

        uni.addPerson(s1);
        uni.addPerson(s2);
        uni.addPerson(p1);
        uni.addPerson(p2);

        uni.listAll();

        System.out.println("\nNur Studenten:");
        for (Student s : uni.getStudents()) {
            System.out.println(s.getFullName() + " | Schnitt: " + s.getDurchschnitt());
        }
    }
}
