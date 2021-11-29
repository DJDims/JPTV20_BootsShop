package JPTV20_BootsShop;

import Facade.CustomerFacade;
import Facade.HistoryFacade;
import Facade.ProductFacade;
import Interfaces.Keeping;
import java.util.List;
import java.util.Scanner;

import Classes.Customer;
import Classes.History;
import Classes.Product;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;

import java.util.Date;
import Tools.SaverToBase;

public class App {
    Scanner scanner = new Scanner(System.in);
    Keeping keeping = new SaverToBase();
    
    private CustomerFacade customerFacade;
    private HistoryFacade historyFacade;
    private ProductFacade productFacade;
    
    Calendar calendar = Calendar.getInstance();
    Date date = calendar.getTime();
    
    boolean appRunning = true;
    double shopStonks = 0;
    String[] monthsNames = {"янв", "фев", "мар", "апр", "май", "июн", "июл", "авг", "сен", "окт", "ноя", "дек"};

    public App() {
        init();
    }
    
    private void init(){
        customerFacade = new CustomerFacade(Customer.class);
        historyFacade = new HistoryFacade(History.class);
        productFacade = new ProductFacade(Product.class);
    }
    
    public void run(){
        shopStonks = countStonks();
        while (appRunning) {
            showHints();
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
        product.setBrand(scanner.nextLine());
        
        System.out.print("Тип: ");
        product.setType(scanner.nextLine());
        
        System.out.print("Размер: ");
        product.setSize(inputInt());
        
        System.out.print("Цена: ");
        product.setPrice(inputDouble());
        System.out.println(" ");
        
        productFacade.create(product);
    }
    
    private void showProducts(){
        List<Product> productsArray = productFacade.findAll();
        
        if (productsArray.isEmpty()) {
            System.out.println("\nНет добавленных товаров\n");
            return;
        }
        
        System.out.println("---------- Список товаров ----------");
        for (int i = 0; i < productsArray.size(); i++) {
            System.out.println(i+1 + ")" + productsArray.get(i).toString());
        }
        System.out.println("---------- Список товаров ----------");
    }
    
    private void addCustomer(){
        Customer customer = new Customer();

        System.out.println(" ");
        System.out.print("Имя: ");
        customer.setFirstname(scanner.nextLine());
        
        System.out.print("Фамилия: ");
        customer.setSurename(scanner.nextLine());
        
        System.out.print("Телефон: ");
        customer.setPhoneNumber(scanner.nextLine());
        
        System.out.print("Счет: ");
        customer.setWallet(inputDouble());
        System.out.println(" ");

        customerFacade.create(customer);
    }

    private void showCustomers(){
        List<Customer> customersArray = customerFacade.findAll();
        
        if (customersArray.isEmpty()) {
            System.out.println("\nНет добавленных покупателей\n");
            return;
        }
        
        System.out.println("---------- Список покупателей ----------");
        for (int i = 0; i < customersArray.size(); i++) {
            System.out.println(i+1 + ")" + customersArray.get(i).toString());
        }
        System.out.println("---------- Список покупателей ----------");
    }
    
    private void addHistory(){
        List<Customer> customersArray = customerFacade.findAll();
        List<Product> productsArray = productFacade.findAll();
        
        if (!productsArray.isEmpty() || !customersArray.isEmpty()) {
            History history = new History();

            //----- Выбор товара -----
            System.out.println("Выберите товар:");
            for (int i = 0; i < productsArray.size(); i++) {
                System.out.println(i+1 + ")" + productsArray.get(i).getBrand()+" "+ productsArray.get(i).getPrice()+"€");
            }
            int productChoise = inputInt();
            Product product = productFacade.findById((long)productChoise);
            //----- Выбор товара -----

            //----- Выбор покупателя -----
            System.out.println("Выберите покупателя:");
            for (int i = 0; i < customersArray.size(); i++) {
                System.out.println(i+1 + ")" + customersArray.get(i).getFirstname()+" "+ customersArray.get(i).getWallet()+"€");
            }
            int customerChoise = inputInt();
            Customer customer = customerFacade.findById((long)customerChoise);
            //----- Выбор покупателя -----

            if (customer.getWallet() >= product.getPrice()) {
                history.setCustomer(customer);
                history.setProduct(product);
                history.setPurchase(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
                customer.setWallet(customer.getWallet() - product.getPrice());
                shopStonks += product.getPrice();
                
                customerFacade.edit(customer);
                historyFacade.edit(history);
                
            } else {
                System.out.println("Недостаточно денег");
            }
        }else{
            System.out.println("\nОперация невозможна\n");
        }
    }
    
    private void showHistorys(){
        List<History> historysArray = historyFacade.findAll();
        if (historysArray.isEmpty()) {
            System.out.println("\nНет историй покупок\n");
            return;
        }
        
        System.out.println("---------- Список историй покупок ----------");
        for (int i = 0; i < historysArray.size(); i++) {
            System.out.println(i+1 + ")" + historysArray.get(i).toString());
        }
        System.out.println("---------- Список историй покупок ----------");
    }
    
    private void addMoneyToCustomer(){
        List<Customer> customersArray = customerFacade.findAll();
        
        if (customersArray.isEmpty()) {
            System.out.println("\nНет добавленных покупателей\n");
        }
        
        System.out.println("---------- Список покупателей ----------");
        for (int i = 0; i < customersArray.size(); i++) {
            System.out.println(i+1 + ")" + customersArray.get(i).toString());
        }
        System.out.println("---------- Список покупателей ----------");
        System.out.print("Выберите покупателя --> ");
        int numberOfCustomerToAdd = inputInt();
        Customer customer = customerFacade.findById((long)numberOfCustomerToAdd-1);
        
        System.out.print("Сумма для добавления --> ");
        double moneyToAdd = inputDouble();
        
        customer.setWallet(customer.getWallet() + moneyToAdd);
        customerFacade.edit(customer);
    }
    
    private void showStonksForMonth(){
        List<History> historysArray = historyFacade.findAll();
        
        System.out.print("Выберите месяц(1-12) -->");
        int month = inputInt();
        double stonks = 0;
        for (int i = 0; i < historysArray.size(); i++) {
            if (historysArray.get(i).getPurchase().getMonth()+1 == month) {
                stonks += historysArray.get(i).getProduct().getPrice();
            }
        }
        System.out.println("Прибыль магазина за " + monthsNames[month-1] + ": " + stonks + "€"); 
    }
    
    private double countStonks(){
        double stonks = 0;
        List<History> historysArray = historyFacade.findAll();
        
        for (int i = 0; i < historysArray.size(); i++) {
            stonks += historysArray.get(i).getProduct().getPrice();
        }
        
        return stonks;
    }
    
    private int inputInt() {
	do {
            try {
                String inputedNumber = scanner.nextLine();
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
                String inputedNumber = scanner.nextLine();
                return Double.parseDouble(inputedNumber);
            } catch(Exception e) {
                System.out.println("Введены неверные данные.");
                System.out.print("Попробуйте еще раз -->");
            }
	} while(true);
    }
}
