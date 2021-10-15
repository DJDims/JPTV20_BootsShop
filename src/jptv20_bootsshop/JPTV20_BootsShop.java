
package jptv20_bootsshop;

import java.util.Scanner;
import jptv20_bootsshop.classes.Customer;
import jptv20_bootsshop.classes.History;
import jptv20_bootsshop.classes.Product;

public class JPTV20_BootsShop {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Product[] products = new Product[10];
        int countOfProducts = 0;
        Customer[] customers = new Customer[10];
        int countOfCustomers = 0;
        History[] historys = new History[10];
        int countOfHistorys = 0;
        
        double shopStonks = 0;
        
        while (true) {
            System.out.println("Выберите опцию\n0) Выход\n1) Добавить товар\n2) Вывести список товаров\n3) Добавить покупателя\n4) Вывести список покупателей\n5) Совершить покупку\n6) Вывести список покупок\n7) Прибыль магазина");
            int choise = scanner.nextInt();

            if (choise == 0) {
                //Выйти из программы
                System.out.println("Досвидания, приходите еще!");
                break;
            }else if (choise == 1){
                //Добавить товар
                if (countOfProducts < products.length) {
                    System.out.print("Производитель: ");
                    String brand = scanner.next();
                    System.out.print("Тип: ");
                    String type = scanner.next();
                    System.out.print("Размер: ");
                    int size = scanner.nextInt();
                    System.out.print("Цена: ");
                    Double price = scanner.nextDouble();
                    
                    products[countOfProducts] = new Product(brand, type, size, price);
                    countOfProducts++;

                } else {
                    System.out.println("\nМаксимальное количество товаров\n");
                }
                
            }else if (choise == 2){
                //Вывести список товаров
                if (products[0] == null) {
                    System.out.println("\nНет добавленных товаров\n");
                } else {
                    System.out.println("---------- Список товаров ----------");
                    for (int i = 0; i < countOfProducts; i++) {
                        System.out.println(i+1 + ")" + products[i].toString());
                    }
                    System.out.println("---------- Список товаров ----------");
                }

            }else if (choise == 3){
                //Добавить покупателя
                if (countOfCustomers < customers.length) {
                    System.out.print("Имя: ");
                    String firstname = scanner.next();
                    System.out.print("Фамилия: ");
                    String surename = scanner.next();
                    System.out.print("Телефон: ");
                    String phoneNumber = scanner.next();
                    System.out.print("Счет: ");
                    Double wallet = scanner.nextDouble();
                    
                    customers[countOfCustomers] = new Customer(firstname, surename, phoneNumber, wallet);
                    countOfCustomers++;

                } else {
                    System.out.println("\nМаксимальное количество покупателей\n");
                }

            }else if (choise == 4){
                //Вывести список покупателей
                if (customers[0] == null) {
                    System.out.println("\nНет добавленных покупателей\n");
                } else {
                    System.out.println("---------- Список покупателей ----------");
                    for (int i = 0; i < countOfCustomers; i++) {
                        System.out.println(i+1 + ")" + customers[i].toString());
                    }
                    System.out.println("---------- Список покупателей ----------");
                }

            }else if (choise == 5){
                //Совершить покупку
                if (products[0] == null || customers[0] == null) {
                    System.out.println("\nОперация невозможна\n");
                }else{
                    if (countOfHistorys < historys.length) {
                        //----- Выбор товара -----
                        System.out.println("Выберите товар:");
                        for (int i = 0; i < countOfProducts; i++) {
                            System.out.println(i+1 + ")" + products[i].getBrand()+" "+ products[i].getPrice()+"€");
                        }
                        int productChoise = scanner.nextInt();
                        //----- Выбор товара -----

                        //----- Выбор покупателя -----
                        System.out.println("Выберите покупателя");
                        for (int i = 0; i < countOfCustomers; i++) {
                            System.out.println(i+1 + ")" + customers[i].getFirstname() +" "+ customers[i].getWallet()+"€");
                        }
                        int customerChoise = scanner.nextInt();
                        //----- Выбор покупателя -----
                        if (customers[customerChoise-1].getWallet() >= products[productChoise-1].getPrice()) {
                            historys[countOfHistorys] = new History(products[productChoise-1], customers[customerChoise-1]);
                            customers[customerChoise-1].setWallet(customers[customerChoise-1].getWallet() - products[productChoise-1].getPrice());
                            shopStonks += products[productChoise-1].getPrice();
                            countOfHistorys++;
                        }else{
                            System.out.println("Недостаточно денег");
                        }
                        
                    } else {
                        System.out.println("\nМаксимальное количество покупок\n");
                    }
                }

            }else if (choise == 6){
                //Вывести список покупок
                if (historys[0] == null) {
                    System.out.println("\nНет историй покупок\n");
                } else {
                    System.out.println("---------- Список историй покупок ----------");
                    for (int i = 0; i < countOfHistorys; i++) {
                        System.out.println(i+1 + ")" + historys[i].toString());
                    }
                    System.out.println("---------- Список историй покупок ----------");
                }
            }else if (choise == 7){
                //Вывести прибыль магазина
                System.out.println("\nПрибыль магазина " + shopStonks + "€\n");
            }else{
                //Если ввод <0 и >7
                System.out.println("Введено неверное значение\n");
            }
        }
    }
}
