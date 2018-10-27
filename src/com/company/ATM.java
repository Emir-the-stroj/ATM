package com.company;

import java.util.Scanner;
import java.util.ArrayList;

public class ATM {
//arraylist za racune korisnika
    public static ArrayList<Account> users = new ArrayList<Account>();

    public static int generateRacun(){
        int rac = (int)(Math.random() * 100000000);
        return rac;
    }


// provjera da li je generisani broj racuna isti
    public static boolean istiBroj(int broj) {
        boolean uslov = false;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getAccountNumber() == broj) {
                uslov = true;
                break;
            }
            else
                uslov = false;

        }
        return uslov;
    }
//glavna metoda
    public static void main(String[] args) {
//scanner
        Scanner input = new Scanner(System.in);
        int option;
        System.out.println("\n\t- Dobrodosli u bankomat BILD IT Banke -");

//do while petlja u kombinaciji sa svitchom
        do {
            System.out.println("\n1- Kreiraj novi racun  \n2- Transfer novca \n3- Ispis stanja racuna \n0- Izlaz");
            option = input.nextInt();

            switch (option) {
                case 0:
                    break;
                case 1:
                    //kreiranje novog objekta nakon odabira funkcije i dodavanje unesenih podataka korisnika putem settera
                    Account account = new Account();
                    System.out.print("Unesite vase ime: ");
                    String name = input.next();
                    account.setAccountOwner(name);
                    System.out.print("Unesite password koji cete koristiti: ");
                    String pass = input.next();
                    account.setPassword(pass);

                    //while petljom i metodom istiBroj provjeravamo je li isti racun kreiran
                    int accountNumber = generateRacun();
                    while (istiBroj(accountNumber)) {
                        accountNumber = generateRacun();
                    }
                    account.setAccountNumber(accountNumber);

                    /*polaganje sredstava na racun i provjera da li je polozeno vise od 0 i dodjela sredstava te
                    *  dodjela novog usera arraylissti*/
                    System.out.print("Vas broj racuna je " + account.getAccountNumber());
                    System.out.print("\nPolozite novcani iznos na racun molim !");
                    double deposit = input.nextDouble();
                    if (deposit <= 0) {
                        System.out.print("Vas polog na racun mora biti veci od 0");
                        deposit = input.nextDouble();
                    } else {
                        account.addFunds(deposit);
                        users.add(Account.getUkupanBrojRacuna() - 1, account);
                        System.out.print("Vas racun je kreiran, stanje vaseg racuna je " + deposit + "\n");
                    }
                    break;

                case 2:

                    //kreiranje Transfer objekta i prebacivanje novca sa racuna na racun
                    Transfer transfer = new Transfer();
                    System.out.println("Unesite broj racuna sa kojeg zelite da posaljete novac :");
                    transfer.setSourceAccount(input.nextInt());
                    System.out.println("");
                    System.out.println("Unesite racun na koji zelite da posaljete novac :");
                    transfer.setTargetAccount(input.nextInt());
                    System.out.println("Unesite kolicinu novca kojeg saljete: ");
                    transfer.setAmmount(input.nextDouble());
                    transfer.prebacivanje();
                    break;

                case 3:
                    //Ispis racuna uz odradjenu validaciju
                    System.out.println("Unesite broj vaseg racuna: ");
                    int brojRacuna = input.nextInt();
                    System.out.println("Unesite vas password: ");
                    String passw = input.next();
                    for (int i = 0; i < users.size(); i++) {
                        if (users.get(i).getAccountNumber() == brojRacuna) {
                            if (users.get(i).getPassword().equals(passw))
                                System.out.println(users.get(i).toString());
                            else
                                System.out.println("Password nije validan.");
                            break;
                        } else {
                            if (i == users.size() - 1)
                                System.out.println("Broj Racuna nije validan.");
                        }
                    }
                    break;
                default:
                    System.out.println("Vas unos je pogresan !!! Odaberite broj od 0 - 3.\n");
                    break;
            }
        } while (option != 0);
    }
}