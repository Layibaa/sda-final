package BackendCode;

import java.io.Serializable;
import java.util.ArrayList;

public class Customer extends Person implements Serializable {
    private int Bill; // Tracks the total bill for booked cars

    public Customer() {
        super();
    }

    public Customer(int Bill, int ID, String CNIC, String Name, String Contact_No) {
        super(ID, CNIC, Name, Contact_No);
        this.Bill = Bill;
    }

    public int getBill() {
        return Bill;
    }

    public void setBill(int Bill) {
        this.Bill = Bill;
    }

    @Override
    public String toString() {
        return super.toString() + "Customer{" + "Bill=" + Bill + '}' + "\n";
    }

    @Override
    public void Add() {
        CustomerRepository.addCustomer(this);
    }

    @Override
    public void Update() {
        CustomerRepository.updateCustomer(this);
    }

    @Override
    public void Remove() {
        CustomerRepository.removeCustomer(this.ID);
    }

    // Static methods for search and retrieval
    public static ArrayList<Customer> SearchByName(String name) {
        return CustomerRepository.searchByName(name);
    }

    public static Customer SearchByCNIC(String CustomerCNIC) {
        return CustomerRepository.searchByCNIC(CustomerCNIC);
    }

    public static Customer SearchByID(int id) {
        return CustomerRepository.searchByID(id);
    }

    public static ArrayList<Customer> View() {
        return CustomerRepository.getAllCustomers();
    }
}
