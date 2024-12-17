package BackendCode;

import java.io.*;
import java.util.ArrayList;

/**
 * Customer class represents a customer entity.
 * Violations are annotated with corresponding Violation IDs.
 * @author @AbdullahShahid01
 */
public class Customer extends Person implements Serializable {

    private int Bill; // V25: Primitive Obsession - Should be encapsulated in a value object.

    private static final String FILE_NAME = "Customer.ser"; // V23: Hard-coded file name moved to a constant.

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
        ArrayList<Customer> customers = Customer.View();
        if (customers.isEmpty()) {
            this.ID = 1;
        } else {
            this.ID = customers.get(customers.size() - 1).ID + 1; // V28: Magic Number - Auto ID logic is hard-coded.
        }
        customers.add(this);
        writeCustomersToFile(customers); // V22: Reused file writing logic.
    }

    @Override
    public void Update() {
        ArrayList<Customer> customers = Customer.View();

        for (int i = 0; i < customers.size(); i++) { // V27: Data Structure Misuse - Can use a for-each loop.
            if (customers.get(i).ID == ID) {
                customers.set(i, this);
            }
        }

        writeCustomersToFile(customers); // V22: Duplicate logic reused.
    }

    @Override
    public void Remove() {
        ArrayList<Customer> customers = Customer.View();

        customers.removeIf(customer -> customer.ID == ID); // Simplified loop with a lambda.

        writeCustomersToFile(customers); // V22: Duplicate logic reused.
    }

    // Utility method to handle file writing logic (reused in Add, Update, Remove)
    private void writeCustomersToFile(ArrayList<Customer> customers) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            for (Customer customer : customers) {
                outputStream.writeObject(customer);
            }
        } catch (IOException ex) {
            // V24: Proper exception handling needed (Logging should be used instead of printing)
            System.err.println("Error writing to file: " + ex.getMessage());
        }
    }

    public static ArrayList<Customer> SearchByName(String name) {
        ArrayList<Customer> customers = Customer.View();
        ArrayList<Customer> result = new ArrayList<>();
        for (Customer customer : customers) { // V27: Data Structure Misuse - Replacing manual for loop.
            if (customer.Name.equalsIgnoreCase(name)) {
                result.add(customer);
            }
        }
        return result;
    }

    public static Customer SearchByCNIC(String CustomerCNIC) {
        return Customer.View().stream()
                .filter(customer -> customer.CNIC.equalsIgnoreCase(CustomerCNIC))
                .findFirst().orElse(null); // V26: Generalized search logic can reduce code duplication.
    }

    public static Customer SearchByID(int id) {
        return Customer.View().stream()
                .filter(customer -> customer.ID == id)
                .findFirst().orElse(null); // V26: Generalized search logic.
    }

    public static ArrayList<Customer> View() {
        ArrayList<Customer> CustomerList = new ArrayList<>();
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            boolean EOF = false;
            while (!EOF) {
                try {
                    Customer myObj = (Customer) inputStream.readObject();
                    CustomerList.add(myObj);
                } catch (EOFException end) {
                    EOF = true;
                } catch (ClassNotFoundException e) {
                    System.err.println("Class not found: " + e.getMessage()); // V24: Use proper logging.
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage()); // V24: Proper exception handling.
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return CustomerList;
    }
}
