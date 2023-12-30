package cz.itnetwork;

public class InsuredPerson {
    private String firstName;
    private String lastName;
    private int age;
    private String phoneNumber;

    public InsuredPerson(String firstName, String lastName, int age, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return String.format("Name: %s %s, Age: %d, Phone: %s", firstName, lastName, age, phoneNumber);
    }
}
