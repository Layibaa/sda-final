package BackendCode;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * The CarOwner class represents a car owner with personal details and balance.
 * Responsibility is focused on the owner and their balance management.
 */
public class CarOwner extends Person implements Serializable {

    private int balance; // Balance increases after every hour when the owner's car(s) is booked

    public CarOwner() {
        super();
    }

    public CarOwner(int balance, int ID, String CNIC, String Name, String Contact_No) {
        super(ID, CNIC, Name, Contact_No);
        this.balance = balance;
    }

    // Getters and Setters for Balance
    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return super.toString() + " CarOwner{" + "balance=" + balance + '}';
    }

    // Add CarOwner to the list (Persistence Handling Delegated to CarOwnerPersistence)
    public void add() {
        CarOwnerPersistence.addCarOwner(this);
    }

    // Update CarOwner in the list (Persistence Handling Delegated to CarOwnerPersistence)
    public void update() {
        CarOwnerPersistence.updateCarOwner(this);
    }

    // Remove CarOwner from the list (Persistence Handling Delegated to CarOwnerPersistence)
    public void remove() {
        CarOwnerPersistence.removeCarOwner(this);
    }

    // Search for CarOwner by Name
    public static ArrayList<CarOwner> searchByName(String name) {
        return CarOwnerPersistence.searchCarOwnersByName(name);
    }

    // Search for CarOwner by ID
    public static CarOwner searchByID(int id) {
        return CarOwnerPersistence.searchCarOwnerByID(id);
    }

    // Search for CarOwner by CNIC
    public static CarOwner searchByCNIC(String carOwnerCNIC) {
        return CarOwnerPersistence.searchCarOwnerByCNIC(carOwnerCNIC);
    }
}
