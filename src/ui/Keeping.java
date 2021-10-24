package ui;

import classes.Customer;
import classes.History;
import classes.Product;

public interface Keeping {
    public void saveProducts(Product[] products);
    public Product[] loadProducts();

    public void saveCustomers(Customer[] customers);
    public Customer[] loadCustomers();

    public void saveHistorys(History[] historys);
    public History[] loadHistory();
}
