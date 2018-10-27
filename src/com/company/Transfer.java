package com.company;

import java.util.Scanner;



public class Transfer extends ATM{
    //scanner i polja podataka
    Scanner input = new Scanner(System.in);
private int sourceAccount;
private int targetAccount;
private double ammount;

// konstruktori
public Transfer(){}

    public Transfer(int sourceAccount, int targetAccount, double ammount) {
        this.sourceAccount = sourceAccount;
        this.targetAccount = targetAccount;
        this.ammount = ammount;
    }

// setteri



    public void setSourceAccount(int sourceAccount) {

        this.sourceAccount = sourceAccount;
    }

    public void setTargetAccount(int targetAccount) {
        this.targetAccount = targetAccount;
    }

    public void setAmmount(double ammount) {
        this.ammount = ammount;
    }

    //transfer metoda
    public void prebacivanje(){
    boolean source = false;
    boolean target = false;
    boolean correctPassword = false;
    int indexSource = 0;
    int indexTarget = 0;
    double previousStateSource = 0;

    //for petlja za validaciju korisnika i passworda
    for (int i = 0; i < users.size(); i++){
        if(sourceAccount == users.get(i).getAccountNumber()) {
            System.out.print(users.get(i).getAccountOwner() + " unesite password: ");
            String pass = input.next();
            if (users.get(i).getPassword().equals(pass))
                correctPassword = true;
            else
                break;
            previousStateSource = users.get(i).getAmmountOnAccount();
            if (previousStateSource - ammount > 0) {
                source = true;
                indexSource = i;
            }
        }
        if(targetAccount == users.get(i).getAccountNumber()){
            target = true;
            indexTarget = i;
        }
    }
// uvjetom rjesavamo pojedinacne slucajeve pogresnog unosa brojeva racuna, passworda
    if(source && target && ammount > 0 && correctPassword){
        users.get(indexTarget).addFunds(ammount);
        users.get(indexSource).removeFunds(ammount);
        System.out.println("Prenos novca je uspjesno obavljen.");
    }
    else if(source == false && target && ammount > 0)
        System.out.println("Broj racuna sa kojeg zelite vrsiti transakciju nije vazeci.");
     else if(source && target == false && ammount > 0)
        System.out.println("Broj racuna na koji zelite poslati novac nije validan.");
    else if(source == false && target == false && ammount > 0)
        System.out.println("Brojevi racuna koje ste unijeli za transakciju su nevazeci.");
     else if(source && target && ammount < 0)
        System.out.println(ammount + " nije validan iznos novca za transakciju.");
     else if(source && target && ammount > 0 && correctPassword == false)
        System.out.println("Unijeli ste pogresan password.");
     else
        System.out.println("Ocito je da nesto ne funkcionise. Dodjite sutra.");



    }

}
