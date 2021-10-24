package classes;

public class Customer {
    private String firstname;
    private String surename;
    private String phoneNumber;
    private double wallet;
    
//    public Customer(String firstname, String surename, String phoneNumber, double wallet) {
//        this.firstname = firstname;
//        this.surename = surename;
//        this.phoneNumber = phoneNumber;
//        this.wallet = wallet;
//    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurename() {
        return surename;
    }

    public void setSurename(String surename) {
        this.surename = surename;
    }

    public double getWallet() {
        return wallet;
    }

    public void setWallet(double wallet) {
        this.wallet = wallet;
    }

    public String getphoneNumber() {
        return phoneNumber;
    }

    public void setphoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString(){
        return "Имя " + firstname + "\tФамилия " + surename + "\tКошелек " + wallet + "\tТелефон " + phoneNumber;
    }
}
