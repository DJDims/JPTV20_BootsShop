package classes;

import java.io.Serializable;

public class Customer implements Serializable {
    private String firstname;
    private String surename;
    private String phoneNumber;
    private double wallet;

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
    public String toString() {
        return "Customer{" + "firstname=" + firstname + ", surename=" + surename + ", phoneNumber=" + phoneNumber + ", wallet=" + wallet + '}';
    }
}
