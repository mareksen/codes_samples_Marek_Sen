package cz.itnetwork;

import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.List;

public class UserInterface {
    private final InsuranceManager manager;
    private final Scanner scanner;

    // Constructor for UserInterface, initializes manager and scanner
    public UserInterface(InsuranceManager manager) {
        this.manager = manager;
        this.scanner = new Scanner(System.in);
    }

    // Method to start the user interface loop
    public void start() {
        int choice;
        do {
            // Displaying the menu options
            System.out.println("\nInsurance Management System:");
            System.out.println("1. Add insured person");
            System.out.println("2. Search for insured person");
            System.out.println("3. Show all insured persons");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // clear the buffer

            // Handling user's choice
            switch (choice) {
                case 1:
                    addInsuredPerson();
                    break;
                case 2:
                    searchForInsuredPerson();
                    break;
                case 3:
                    showAllInsuredPersons();
                    break;
                case 4:
                    System.out.println("Exiting the system.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 4); // Loop until the user chooses to exit
    }

    // Method to add an insured person
    private void addInsuredPerson() {
        // Get valid first name and last name
        String[] nameInfo = getUserFirstNameAndLastName();
        // Validate the names
        if (!isValidName(nameInfo[0]) || !isValidName(nameInfo[1])) {
            System.out.println("Invalid name. Names should only contain letters.");
            return;
        }

        // Get and validate age
        int age = getValidAge();
        if (age < 18 || age > 100) {
            System.out.println("Invalid age. Age must be between 18 and 100. You may not apply for insurance.");
            return;
        }

        // Get and validate phone number
        String phoneNumber = getValidPhoneNumber();
        // Add the insured person to the manager
        manager.addInsuredPerson(new InsuredPerson(nameInfo[0], nameInfo[1], age, phoneNumber));
        System.out.println("Insured person added successfully.");
    }

    // Method to search for an insured person
    private void searchForInsuredPerson() {
        // Check if there are any insured persons to search
        if (manager.getInsuredList().isEmpty()) {
            System.out.println("There are no insured persons in the database yet, you need to add some first.");
        } else {
            // Proceed with search logic
            String[] nameInfo = getUserFirstNameAndLastName();
            List<InsuredPerson> foundPersons = manager.findInsuredPersons(nameInfo[0], nameInfo[1]);
            if (!foundPersons.isEmpty()) {
                System.out.println("Found insured persons:");
                for (InsuredPerson person : foundPersons) {
                    System.out.println(person);
                }
            } else {
                System.out.println("No insured persons found with the given name parts.");
            }
        }
    }

    // Method to show all insured persons
    public void showAllInsuredPersons() {
        List<InsuredPerson> insuredList = manager.getInsuredList();
        if (insuredList.isEmpty()) {
            System.out.println("There are no insured persons in the database yet, you need to add some first.");
        } else {
            for (InsuredPerson insured : insuredList) {
                System.out.println(insured);
            }
        }
    }

    // Method to get valid first name and last name from user
    private String[] getUserFirstNameAndLastName() {
        String firstName = "", lastName = "";
        boolean isValidInput = false;
        while (!isValidInput) {
            System.out.print("Enter first name: ");
            firstName = scanner.nextLine();
            if (!isValidName(firstName)) {
                System.out.println("Invalid name. Names should only contain letters. Please try again.");
                continue;
            }

            System.out.print("Enter last name: ");
            lastName = scanner.nextLine();
            if (!isValidName(lastName)) {
                System.out.println("Invalid name. Names should only contain letters. Please try again.");
                continue;
            }
            // Both names are valid
            isValidInput = true;
        }
        return new String[]{firstName, lastName};
    }

    // Method to validate if a name is valid
    private boolean isValidName(String name) {
        return name.matches("[A-Za-záéíóúýčďěňřšťžÁÉÍÓÚÝČĎĚŇŘŠŤŽ]+");
    }

    // Method to get a valid phone number
    private String getValidPhoneNumber() {
        String phoneNumber;
        while (true) {
            System.out.print("Enter phone number: ");
            phoneNumber = scanner.nextLine();

            if (phoneNumber.matches("^\\+?\\d+$")) {
                // Valid phone number
                return phoneNumber; // Valid phone number
            } else {
                System.out.println("Invalid phone number. You can only use numbers and an optional '+'. Please try again.");
            }
        }
    }

    // Method to get a valid age
    private int getValidAge() {
        while (true) {
            System.out.print("Enter age: ");
            try {
                int age = scanner.nextInt();
                // Clear the buffer
                scanner.nextLine();
                // Valid age
                return age;
            } catch (InputMismatchException e) {
                // Clear the buffer
                scanner.nextLine();
                System.out.println("Invalid input. Please enter a number for the age. Please try again.");
            }
        }
    }
}
