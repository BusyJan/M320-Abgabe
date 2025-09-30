package kurs;

public abstract class Person {
    private final String firstName;
    private final String lastName;
    private int age;

    public Person(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age >= 0) this.age = age;
    }

    public abstract String getRole();

    @Override
    public String toString() {
        return getFullName() + " (" + getRole() + ", " + age + " Jahre)";
    }
}
