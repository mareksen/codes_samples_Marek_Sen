package cz.itnetwork;

public class Application {
    public static void main(String[] args) {
        InsuranceManager manager = new InsuranceManager();
        UserInterface ui = new UserInterface(manager);
        // loop for user interface (UserInterface)
        ui.start();
    }
}




