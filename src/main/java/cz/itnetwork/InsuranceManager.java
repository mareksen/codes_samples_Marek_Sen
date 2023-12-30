package cz.itnetwork;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class InsuranceManager {
    private final List<InsuredPerson> insuredList;

    public InsuranceManager() {
        insuredList = new ArrayList<>();
    }

    // Adds an insured person to the list
    public void addInsuredPerson(InsuredPerson person) {
        insuredList.add(person);
    }

    // Finds all insured persons with the given name
    public List<InsuredPerson> findInsuredPersons(String firstNamePrefix, String lastNamePrefix) {
        return insuredList.stream()
                .filter(p -> p.getFirstName().toLowerCase().startsWith(firstNamePrefix.toLowerCase()) &&
                        p.getLastName().toLowerCase().startsWith(lastNamePrefix.toLowerCase()))
                .collect(Collectors.toList());
    }

    // Gets the list of all insured persons
    public List<InsuredPerson> getInsuredList() {
        return Collections.unmodifiableList(insuredList);
    }
}




