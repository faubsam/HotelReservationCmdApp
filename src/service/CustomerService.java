package service;

import model.Customer;

import java.util.*;

public class CustomerService {
    private static HashMap<String, Customer> customers = new HashMap<>();
    private static CustomerService customerService = null;

    public static CustomerService getInstance() {
        if (customerService == null) {
            customerService = new CustomerService();
        }
        return customerService;
    }

    public static void addCustomer(String firstName, String lastName, String email) {

        Customer customer = new Customer(firstName, lastName, email);

        customers.put(email, customer);
    }
    public static Customer getCustomer(String customerEmail) {
        return customers.get(customerEmail);
    }
    public static Collection<Customer> getAllCustomers() {
        return customers.values();
    }
}
