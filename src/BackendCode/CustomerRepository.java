package BackendCode;

import java.io.*;
import java.util.ArrayList;

public class CustomerRepository {
    private static final String FILE_NAME = "Customer.ser";

    public static void addCustomer(Customer customer) {
        ArrayList<Customer> customers = getAllCustomers();
        if (customers.isEmpty()) {
            customer.setID(1);
        } else {
            customer.setID(customers.get(customers.size() - 1).getID() + 1); // Auto ID
        }
        customers.add(customer);
        saveCustomersToFile(customers);
    }

    public static void updateCustomer(Customer customer) {
        ArrayList<Customer> customers = getAllCustomers();
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getID() == customer.getID()) {
                customers.set(i, customer);
                break;
            }
        }
        saveCustomersToFile(customers);
    }

    public static void removeCustomer(int id) {
        ArrayList<Customer> customers = getAllCustomers();
        customers.removeIf(customer -> customer.getID() == id);
        saveCustomersToFile(customers);
    }

    public static ArrayList<Customer> searchByName(String name) {
        ArrayList<Customer> customers = getAllCustomers();
        ArrayList<Customer> result = new ArrayList<>();
        for (Customer customer : customers) {
            if (customer.getName().equalsIgnoreCase(name)) {
                result.add(customer);
            }
        }
        return result;
    }

    public static Customer searchByCNIC(String cnic) {
        for (Customer customer : getAllCustomers()) {
            if (customer.getCNIC().equalsIgnoreCase(cnic)) {
                return customer;
            }
        }
        return null;
    }

    public static Customer searchByID(int id) {
        for (Customer customer : getAllCustomers()) {
            if (customer.getID() == id) {
                return customer;
            }
        }
        return null;
    }

    public static ArrayList<Customer> getAllCustomers() {
        ArrayList<Customer> customers = new ArrayList<>();
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            while (true) {
                try {
                    customers.add((Customer) inputStream.readObject());
                } catch (EOFException e) {
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error reading customers: " + e.getMessage());
        }
        return customers;
    }

    private static void saveCustomersToFile(ArrayList<Customer> customers) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            for (Customer customer : customers) {
                outputStream.writeObject(customer);
            }
        } catch (IOException e) {
            System.out.println("Error saving customers: " + e.getMessage());
        }
    }
}
