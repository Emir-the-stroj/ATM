package com.company;
//import java.io.File;
public class Account {
    //polja podataka
    private int accountNumber = (int)(Math.random()*100000);
    private String accountOwner;
    private String password;
    private double ammountOnAccount;
    private static int ukupanBrojRacuna;

//konstruktori
    public Account(){ukupanBrojRacuna++;}
    public Account(int accountNumber, String accountOwner, double ammountOnAccount) {
        this.accountNumber = accountNumber;
        this.accountOwner = accountOwner;
        this.ammountOnAccount = ammountOnAccount;
        ukupanBrojRacuna++;
    }

//getteri i setteri
    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountOwner() {
        return accountOwner;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAccountOwner(String accountOwner) {
        this.accountOwner = accountOwner;
    }

    public double getAmmountOnAccount() {
        return ammountOnAccount;
    }

    public void setAmmountOnAccount(double ammountOnAccount) {
        this.ammountOnAccount = ammountOnAccount;
    }

    public static int getUkupanBrojRacuna() {
        return ukupanBrojRacuna;
    }

    // metode za dodavanje i skidanje s racuna
    public void addFunds(double ammount) {
       ammountOnAccount+=ammount;
    }

    public void removeFunds(double ammount)  {
        ammountOnAccount-=ammount;
    }

    //stanje racuna
    public String toString() {
        return "\n\t"
                + "Broj racuna: " +accountNumber +"\n\t"
                + "Vlasnik racuna: " +accountOwner +"\n\t"
               // + "Password: " + password +"\n\t"
                + "Stanje na racunu: " +ammountOnAccount
                + "\n";
    }
}
