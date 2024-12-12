package BackendCode;

import java.util.ArrayList;

/**
 * CustomerService class handles the logic for customer validation and other business operations.
 * Responsibility: Perform customer-related validation and business logic.
 */
public class CustomerService {

    public static boolean isCustomerValid(Customer customer) {
        return Validator.isCNICValid(customer.getCNIC()) &&
               Validator.isContactNoValid(customer.getContact_No()) &&
               Validator.isNameValid(customer.getName());
    }

    public static ArrayList<Customer> searchByName(String name) {
        ArrayList<Customer> customers = CustomerRepository.getCustomers();
        ArrayList<Customer> results = new ArrayList<>();
        for (Customer customer : customers) {
            if (customer.getName().equalsIgnoreCase(name)) {
                results.add(customer);
            }
        }
        return results;
    }

    public static Customer searchByCNIC(String cnic) {
        ArrayList<Customer> customers = CustomerRepository.getCustomers();
        for (Customer customer : customers) {
            if (customer.getCNIC().equalsIgnoreCase(cnic)) {
                return customer;
            }
        }
        return null;
    }

    public static Customer searchByID(int id) {
        ArrayList<Customer> customers = CustomerRepository.getCustomers();
        for (Customer customer : customers) {
            if (customer.getID() == id) {
                return customer;
            }
        }
        return null;
    }
}
