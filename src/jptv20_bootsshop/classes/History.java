
package jptv20_bootsshop.classes;

import java.time.LocalDate;

public class History {
    public Product product;
    public Customer customer;
    public LocalDate purchase;
    
    // public History(Product product, Customer customer) {
    //     this.product = product;
    //     this.customer = customer;
    //     this.purchase = LocalDate.now();
    // }

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
    public String toString(){
        return product + "\n Покупатель " + customer + "\tКуплено " + purchase;
    }
}

