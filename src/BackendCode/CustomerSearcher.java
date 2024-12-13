/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BackendCode;

import java.util.ArrayList;

/**
 *
 * @author dell
 */
public class CustomerSearcher extends Customer {
    
    public static ArrayList<Customer> SearchByName(String name) {
        ArrayList<Customer> customers = Customer.View();
        ArrayList<Customer> s = new ArrayList<>();

        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).Name.equalsIgnoreCase(name)) {
                s.add(customers.get(i));
            }
        }
        return s;
    }

    public static Customer SearchByCNIC(String CustomerCNIC) {
        ArrayList<Customer> customers = Customer.View();
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).CNIC.equalsIgnoreCase(CustomerCNIC)) {
                return customers.get(i);
            }
        }
        return null;
    }

    public static Customer SearchByID(int id) {
        ArrayList<Customer> customers = Customer.View();
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).ID == id) {
                return customers.get(i);
            }
        }
        return null;
    }
}
