package Interfaces;

import java.util.List;

import classes.Customer;
import classes.History;
import classes.Product;

public interface Keeping {
    public void saveProducts(List<Product> productsArray);
    public List<Product> loadProducts();

    public void saveCustomers(List<Customer> customersArray);
    public List<Customer> loadCustomers();

    public void saveHistorys(List<History> historysArray);
    public List<History> loadHistory();
}
