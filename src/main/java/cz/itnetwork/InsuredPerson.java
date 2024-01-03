package cz.itnetwork;

public class InsuredPerson {
    private String firstName;
    private String lastName;
    private int age;
    private String phoneNumber;

    // Constructor to initialize the InsuredPerson object with provided details
    public InsuredPerson(String firstName, String lastName, int age, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.phoneNumber = phoneNumber;
    }

    // Getter method for first name
    public String getFirstName() {
        return firstName;
    }

    // Getter method for last name
    public String getLastName() {
        return lastName;
    }

    // Overridden toString method to return the insured person's details in a formatted string
    @Override
    public String toString() {
        return String.format("Name: %s %s, Age: %d, Phone: %s", firstName, lastName, age, phoneNumber);
    }
}
