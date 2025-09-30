package kurs;

public class Professor extends Person {
    private String fachgebiet;

    public Professor(String firstName, String lastName, int age, String fachgebiet) {
        super(firstName, lastName, age);
        this.fachgebiet = fachgebiet;
    }

    public String getFachgebiet() {
        return fachgebiet;
    }

    public void setFachgebiet(String fachgebiet) {
        this.fachgebiet = fachgebiet;
    }

    @Override
    public String getRole() {
        return "Professor";
    }

    @Override
    public String toString() {
        return super.toString() + " | Fachgebiet: " + fachgebiet;
    }
}
