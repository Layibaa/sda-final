package BackendCode;

import java.io.*;
import java.util.ArrayList;

/**
 * CustomerRepository class responsible for handling file operations related to customers.
 * Responsibility: Persist customer data to a file.
 */
public class CustomerRepository {

    private static final String FILE_NAME = "Customer.ser";

    public static void addCustomer(Customer customer) {
        ArrayList<Customer> customers = getCustomers();
        if (customers.isEmpty()) {
            customer.setID(1);
        } else {
            customer.setID(customers.get(customers.size() - 1).getID() + 1); // Auto ID
        }
        customers.add(customer);
        writeToFile(customers);
    }

    public static void updateCustomer(Customer customer) {
        ArrayList<Customer> customers = getCustomers();
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getID() == customer.getID()) {
                customers.set(i, customer);
                break;
            }
        }
        writeToFile(customers);
    }

    public static void removeCustomer(Customer customer) {
        ArrayList<Customer> customers = getCustomers();
        customers.removeIf(c -> c.getID() == customer.getID());
        writeToFile(customers);
    }

    public static ArrayList<Customer> getCustomers() {
        ArrayList<Customer> customers = new ArrayList<>();
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            boolean EOF = false;
            while (!EOF) {
                try {
                    Customer customer = (Customer) inputStream.readObject();
                    customers.add(customer);
                } catch (EOFException end) {
                    EOF = true;
                } catch (ClassNotFoundException | IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
        return customers;
    }

    private static void writeToFile(ArrayList<Customer> customers) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            for (Customer customer : customers) {
                outputStream.writeObject(customer);
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}
