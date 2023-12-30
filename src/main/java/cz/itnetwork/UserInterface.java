package cz.itnetwork;

import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.List;

public class UserInterface {
    private final InsuranceManager manager;
    private final Scanner scanner;

    public UserInterface(InsuranceManager manager) {
        this.manager = manager;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        int choice;
        do {
            System.out.println("\nInsurance Management System:");
            System.out.println("1. Add insured person");
            System.out.println("2. Search for insured person");
            System.out.println("3. Show all insured persons");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // clear the buffer

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
        } while (choice != 4);
    }

    private void addInsuredPerson() {
        String[] nameInfo = getUserFirstNameAndLastName();
        if (!isValidName(nameInfo[0]) || !isValidName(nameInfo[1])) {
            System.out.println("Invalid name. Names should only contain letters.");
            return;
        }

        int age = getValidAge();
        if (age < 18 || age > 100) {
            System.out.println("Invalid age. Age must be between 18 and 100. You may not apply for insurance.");
            return;
        }

        String phoneNumber = getValidPhoneNumber();
        manager.addInsuredPerson(new InsuredPerson(nameInfo[0], nameInfo[1], age, phoneNumber));
        System.out.println("Insured person added successfully.");
    }

    private void searchForInsuredPerson() {
        // Check if the list of insured persons is empty before searching
        if (manager.getInsuredList().isEmpty()) {
            System.out.println("There are no insured persons in the database yet, you need to add some first.");
        } else {
            // Continue with search logic if the list is not empty
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

    private boolean isValidName(String name) {
        return name.matches("[A-Za-záéíóúýčďěňřšťžÁÉÍÓÚÝČĎĚŇŘŠŤŽ]+");
    }

    private String getValidPhoneNumber() {
        String phoneNumber;
        while (true) {
            System.out.print("Enter phone number: ");
            phoneNumber = scanner.nextLine();

            if (phoneNumber.matches("^\\+?\\d+$")) {
                return phoneNumber; // Valid phone number
            } else {
                System.out.println("Invalid phone number. You can only use numbers and an optional '+'. Please try again.");
            }
        }
    }

    private int getValidAge() {
        while (true) {
            System.out.print("Enter age: ");
            try {
                int age = scanner.nextInt();
                scanner.nextLine(); // Clear the buffer
                return age; // Valid age
            } catch (InputMismatchException e) {
                scanner.nextLine(); // Clear the buffer
                System.out.println("Invalid input. Please enter a number for the age. Please try again.");
            }
        }
    }
}

