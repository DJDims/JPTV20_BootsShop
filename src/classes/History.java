
package classes;

import java.io.Serializable;
import java.time.LocalDate;

public class History implements Serializable {
    private Product product;
    private Customer customer;
    private LocalDate purchase;
    
    public History() {
        this.purchase = LocalDate.now();
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDate getPurchase() {
        return purchase;
    }

    public void setPurchase(LocalDate purchase) {
        this.purchase = LocalDate.now();
    }

    @Override
    public String toString() {
        return "History{" + "product=" + product + ", customer=" + customer + ", purchase=" + purchase + '}';
    }
}

