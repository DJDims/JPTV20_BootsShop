package JPTV20_BootsShop;

import Interfaces.Keeping;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import classes.Customer;
import classes.History;
import classes.Product;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;

import java.util.Date;
import tools.SaverToBase;
import tools.SaverToFile;

public class App {
    Scanner scanner = new Scanner(System.in);
//    Keeping keeping = new SaverToFile();
    Keeping keeping = new SaverToBase();
    
    Calendar calendar = Calendar.getInstance();
    Date date = calendar.getTime();
    
    boolean appRunning = true;
    double shopStonks = 0;
    String[] monthsNames = {"янв", "фев", "мар", "апр", "май", "июн", "июл", "авг", "сен", "окт", "ноя", "дек"};

    List<Customer> customersArray = new ArrayList<>();
    List<History> historysArray = new ArrayList<>();
    List<Product> productsArray = new ArrayList<>();

    public App() {
        customersArray = keeping.loadCustomers();
        historysArray = keeping.loadHistory();
        productsArray = keeping.loadProducts();
        shopStonks = countStonks();
    }
    
    public void run(){
        while (appRunning) {
            
            System.out.print("Опция: ");
            int choise = inputInt();

            switch (choise) {
                case 0:
                    //Выйти из программы
                    System.out.println("Досвидания, приходите еще!");
                    appRunning = false;
                    break;
                case 1:
                    //Добавить товар
                    addProduct();
                    break;
                case 2:
                    //Вывести список товаров
                    showProducts();
                    break;
                case 3:
                    //Добавить покупателя
                    addCustomer();
                    break;
                case 4:
                    //Вывести список покупателей
                    showCustomers();
                    break;
                case 5:
                    //Совершить покупку
                    addHistory();
                    break;

                case 6:
                    //Вывести список покупок
                    showHistorys();
                    break;
                case 7:
                    //Добавить покупателю денег
                    addMoneyToCustomer();
                    break;
                case 8:
                    //Вывести прибыль магазина
                    System.out.println("\nПрибыль магазина " + shopStonks + "€\n");
                    break;
                case 9:
                    //вывести прибыль за определенный месяц
                    showStonksForMonth();
                    break;
                default:
                    System.out.println("Введено неверное значение");
                    break;
            }
        }
    }

    private void showHints(){
        System.out.println("Выберите опцию");
        System.out.println("0) Выход");
        System.out.println("1) Добавить товар");
        System.out.println("2) Вывести список товаров");
        System.out.println("3) Добавить покупателя");
        System.out.println("4) Вывести список покупателей");
        System.out.println("5) Совершить покупку");
        System.out.println("6) Вывести список покупок");
        System.out.println("7) Добавить покупателю денег");
        System.out.println("8) Прибыль магазина");
        System.out.println("9) Прибыль магазина за определенный месяц");
    }
    
    private void addProduct(){
        Product product = new Product();
        
        System.out.println(" ");
        System.out.print("Производитель: ");
        product.setBrand(scanner.next());
        
        System.out.print("Тип: ");
        product.setType(scanner.next());
        
        System.out.print("Размер: ");
        product.setSize(inputInt());
        
        System.out.print("Цена: ");
        product.setPrice(inputDouble());
        System.out.println(" ");
        
        productsArray.add(product);
        keeping.saveProducts(productsArray);
    }
    
    private void showProducts(){
        if (!productsArray.isEmpty()) {
            System.out.println("---------- Список товаров ----------");
            for (int i = 0; i < productsArray.size(); i++) {
                System.out.println(i+1 + ")" + productsArray.get(i).toString());
            }
            System.out.println("---------- Список товаров ----------");
        } else {
            System.out.println("\nНет добавленных товаров\n");
        }
    }
    
    private void addCustomer(){
        Customer customer = new Customer();

        System.out.println(" ");
        System.out.print("Имя: ");
        customer.setFirstname(scanner.next());
        
        System.out.print("Фамилия: ");
        customer.setSurename(scanner.next());
        
        System.out.print("Телефон: ");
        customer.setPhoneNumber(scanner.next());
        
        System.out.print("Счет: ");
        customer.setWallet(inputDouble());
        System.out.println(" ");

        customersArray.add(customer);
        keeping.saveCustomers(customersArray);
    }

    private void showCustomers(){
        if (!customersArray.isEmpty()) {
            System.out.println("---------- Список покупателей ----------");
            for (int i = 0; i < customersArray.size(); i++) {
                System.out.println(i+1 + ")" + customersArray.get(i).toString());
            }
            System.out.println("---------- Список покупателей ----------");
        } else {
            System.out.println("\nНет добавленных покупателей\n");
        }
    }
    
    private void addHistory(){
        if (!productsArray.isEmpty() || !customersArray.isEmpty()) {
            History history = new History();

            //----- Выбор товара -----
            System.out.println("Выберите товар:");
            for (int i = 0; i < productsArray.size(); i++) {
                System.out.println(i+1 + ")" + productsArray.get(i).getBrand()+" "+ productsArray.get(i).getPrice()+"€");
            }
            int productChoise = inputInt();
            //----- Выбор товара -----

            //----- Выбор покупателя -----
            System.out.println("Выберите покупателя:");
            for (int i = 0; i < customersArray.size(); i++) {
                System.out.println(i+1 + ")" + customersArray.get(i).getFirstname()+" "+ customersArray.get(i).getWallet()+"€");
            }
            int customerChoise = inputInt();
            //----- Выбор покупателя -----

            if (customersArray.get(customerChoise-1).getWallet() >= productsArray.get(productChoise-1).getPrice()) {
                history.setCustomer(customersArray.get(customerChoise-1));
                history.setProduct(productsArray.get(productChoise-1));
                history.setPurchase(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
                customersArray.get(customerChoise-1).setWallet(customersArray.get(customerChoise-1).getWallet() - productsArray.get(productChoise-1).getPrice());
                shopStonks += productsArray.get(productChoise-1).getPrice();
                
                historysArray.add(history);
                keeping.saveHistorys(historysArray);
                keeping.saveCustomers(customersArray);
            } else {
                System.out.println("Недостаточно денег");
            }
        }else{
            System.out.println("\nОперация невозможна\n");
        }
    }
    
    private void showHistorys(){
        if (!historysArray.isEmpty()) {
            System.out.println("---------- Список историй покупок ----------");
            for (int i = 0; i < historysArray.size(); i++) {
                System.out.println(i+1 + ")" + historysArray.get(i).toString());
            }
            System.out.println("---------- Список историй покупок ----------");
        } else {
            System.out.println("\nНет историй покупок\n");
        }
    }
    
    private void addMoneyToCustomer(){
        if (!customersArray.isEmpty()) {
            System.out.println("---------- Список покупателей ----------");
            for (int i = 0; i < customersArray.size(); i++) {
                System.out.println(i+1 + ")" + customersArray.get(i).toString());
            }
            System.out.println("---------- Список покупателей ----------");
            System.out.print("Выберите покупателя --> ");
            int customerToAdd = inputInt();
            System.out.print("Сумма для добавления --> ");
            double moneyToAdd = scanner.nextDouble();
            customersArray.get(customerToAdd-1).setWallet(customersArray.get(customerToAdd-1).getWallet() + moneyToAdd);
            keeping.saveCustomers(customersArray);
        } else {
            System.out.println("\nНет добавленных покупателей\n");
        }
    }
    
    private void showStonksForMonth(){
        System.out.print("Выберите месяц(1-12) -->");
        int month = inputInt();
        double stonks = 0;
        for (int i = 0; i < historysArray.size(); i++) {
            if (historysArray.get(i).getPurchase().getMonth()+1 == month) {
                stonks += historysArray.get(i).getProduct().getPrice();
            }
        }
        if (stonks != 0) {
           System.out.println("Прибыль магазина за " + monthsNames[month-1] + ": " + stonks + "€"); 
        }
    }
    
    private double countStonks(){
        double stonks = 0;
        
        for (int i = 0; i < historysArray.size(); i++) {
            stonks += historysArray.get(i).getProduct().getPrice();
        }
        
        return stonks;
    }
    
    private int inputInt() {
	do {
            try {
                String inputedNumber = scanner.next();
                return Integer.parseInt(inputedNumber);
            } catch(Exception e) {
                System.out.println("Введены неверные данные.");
                System.out.print("Попробуйте еще раз -->");
            }
	} while(true);
    }
    
    private double inputDouble() {
	do {
            try {
                String inputedNumber = scanner.next();
                return Double.parseDouble(inputedNumber);
            } catch(Exception e) {
                System.out.println("Введены неверные данные.");
                System.out.print("Попробуйте еще раз -->");
            }
	} while(true);
    }
}
