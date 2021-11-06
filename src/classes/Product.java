
package classes;

import java.io.Serializable;

public class Product implements Serializable {
    private String brand;
    private String type;
    private int size;
    private double price;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType(){
        return type;
    }
    
    public void setType(String type){
        this.type = type;
    }
    
    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" + "brand=" + brand + ", type=" + type + ", size=" + size + ", price=" + price + '}';
    }
}
