
package jptv20_bootsshop.classes;

public class Product {
    private String brand;
    private String type;
    private int size;
    private double price;

//    public Product(String brand, String type, int size, double price) {
//        this.brand = brand;
//        this.type = type;
//        this.size = size;
//        this.price = price;
//    }

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
    public String toString(){
        return "Производитель обуви " + brand + "\tТип " + type + "\tРазмер " + size + "\tЦена " + price + "€";
    }
}
