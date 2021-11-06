import Interfaces.Keeping;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import classes.Customer;
import classes.History;
import classes.Product;
import tools.SaverToFile;

public class App {
    Scanner scanner = new Scanner(System.in);
    Keeping keeping = new SaverToFile();
    
    boolean appRunning = true;
    double shopStonks = 0;

    List<Customer> customersArray = new ArrayList<>();
    List<History> historysArray = new ArrayList<>();
    List<Product> productsArray = new ArrayList<>();

    public App() {
        customersArray = keeping.loadCustomers();
        historysArray = keeping.loadHistory();
        productsArray = keeping.loadProducts();
        shopStonks = keeping.loadStonks();
    }
    
    public void run(){
        while (appRunning) {
            System.out.println("\nВыберите опцию\n0) Выход\n1) Добавить товар\n2) Вывести список товаров\n3) Добавить покупателя\n4) Вывести список покупателей\n5) Совершить покупку\n6) Вывести список покупок\n7) Прибыль магазина\n8) Вывести подсказки\n");
            int choise = scanner.nextInt();

            switch (choise) {
                case 0:
                    //Выйти из программы
                    System.out.println("Досвидания, приходите еще!");
                    appRunning = false;
                    break;
                case 1:
                    //Добавить товар
                    productsArray.add(addProduct());
                    keeping.saveProducts(productsArray);
                    break;
                case 2:
                    //Вывести список товаров
                    if (!productsArray.isEmpty()) {
                        System.out.println("---------- Список товаров ----------");
                        for (int i = 0; i < productsArray.size(); i++) {
                            System.out.println(i+1 + ")" + productsArray.get(i).toString());
                        }
                        System.out.println("---------- Список товаров ----------");
                    } else {
                        System.out.println("\nНет добавленных товаров\n");
                    }
                    break;
                case 3:
                    //Добавить покупателя
                    customersArray.add(addCustomer());
                    keeping.saveCustomers(customersArray);
                    break;
                case 4:
                    //Вывести список покупателей
                    if (!customersArray.isEmpty()) {
                        System.out.println("---------- Список покупателей ----------");
                        for (int i = 0; i < customersArray.size(); i++) {
                            System.out.println(i+1 + ")" + customersArray.get(i).toString());
                        }
                        System.out.println("---------- Список покупателей ----------");
                    } else {
                        System.out.println("\nНет добавленных покупателей\n");
                    }
                    break;
                case 5:
                    //Совершить покупку
                    if (productsArray.isEmpty() || customersArray.isEmpty()) {
                        System.out.println("\nОперация невозможна\n");
                    }else{
                        //----- Выбор товара -----
                        System.out.println("Выберите товар:");
                        for (int i = 0; i < productsArray.size(); i++) {
                            System.out.println(i+1 + ")" + productsArray.get(i).getBrand()+" "+ productsArray.get(i).getPrice()+"€");
                        }
                        int productChoise = scanner.nextInt();

                        if (productChoise <= productsArray.size()) {
                            //----- Выбор покупателя -----
                            System.out.println("Выберите покупателя");
                            for (int i = 0; i < customersArray.size(); i++) {
                                System.out.println(i+1 + ")" + customersArray.get(i).getFirstname() +" "+ customersArray.get(i).getWallet()+"€");
                            }
                            int customerChoise = scanner.nextInt();

                            if (customerChoise <= customersArray.size()){
                                if (customersArray.get(customerChoise-1).getWallet() >= productsArray.get(productChoise-1).getPrice()) {
                                    historysArray.add(addHistory(productsArray.get(productChoise-1), customersArray.get(customerChoise-1)));
                                    customersArray.get(customerChoise-1).setWallet(customersArray.get(customerChoise-1).getWallet() - productsArray.get(productChoise-1).getPrice());
                                    shopStonks += productsArray.get(productChoise-1).getPrice();
                                    keeping.saveHistorys(historysArray);
                                    keeping.saveCustomers(customersArray);
                                    keeping.saveStonks(shopStonks);
                                }else{
                                    System.out.println("Недостаточно денег");
                                }
                            }else{
                                System.out.println("Ошибочный номер покупателя");
                            }
                        }else{
                            System.out.println("Ошибочный номер продукта");
                        }
                    }
                    break;

                case 6:
                    //Вывести список покупок
                    if (!historysArray.isEmpty()) {
                        System.out.println("---------- Список историй покупок ----------");
                        for (int i = 0; i < historysArray.size(); i++) {
                            System.out.println(i+1 + ")" + historysArray.get(i).toString());
                        }
                        System.out.println("---------- Список историй покупок ----------");
                    } else {
                        System.out.println("\nНет историй покупок\n");
                    }
                    break;
                case 7:
                    //Вывести прибыль магазина
                    System.out.println("\nПрибыль магазина " + shopStonks + "€\n");
                    break;
                default:
                    System.out.println("Введено неверное значение");
                    break;
            }
        }
    }

    public Product addProduct(){
        Product product = new Product();
        
        System.out.println(" ");
        System.out.print("Производитель: ");
        product.setBrand(scanner.next());
        
        System.out.print("Тип: ");
        product.setType(scanner.next());
        
        System.out.print("Размер: ");
        product.setSize(scanner.nextInt());
        
        System.out.print("Цена: ");
        product.setPrice(scanner.nextDouble());
        System.out.println(" ");
        
        return product;
    }
    
    public Customer addCustomer(){
        Customer customer = new Customer();

        System.out.println(" ");
        System.out.print("Имя: ");
        customer.setFirstname(scanner.next());
        
        System.out.print("Фамилия: ");
        customer.setSurename(scanner.next());
        
        System.out.print("Телефон: ");
        customer.setphoneNumber(scanner.next());
        
        System.out.print("Счет: ");
        customer.setWallet(scanner.nextDouble());
        System.out.println(" ");

        return customer;
    }

    public History addHistory(Product product, Customer customer){
        History history = new History();
        history.setProduct(product);
        history.setCustomer(customer);

        return history;
    }
}
