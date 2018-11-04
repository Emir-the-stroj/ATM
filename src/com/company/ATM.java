package com.company;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;
public class ATM {
    static Scanner input = new Scanner(System.in); // skener
//arraylist za racune korisnika
    public static ArrayList<Account> users = new ArrayList<Account>();

    //generisanje racuna
    public static int generateRacun(){
        int rac = (int)(Math.random() * 100000000);
        return rac;
    }

public static double isDouble() { //  exception za double brojeve
		while (true) {
			try {
				return input.nextDouble();
			} catch (InputMismatchException e) {
				input.next();
				System.out.println("Vas unos nije odgovarajuci. Probajte ponovo: ");
			}
		}
	}
	public static int isInteger(){//exception za integere
        while(true){
            try {
                return input.nextInt();
            } catch (InputMismatchException e){
                input.next();
                System.out.println("Vas unos nije odgovarajuci. Probajte ponovo: ");
            }
        }
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
    public static void main(String[] args)throws Exception {
//scanner
        Scanner input = new Scanner(System.in);
        int option;
        System.out.println("\n\t- Dobrodosli u bankomat BILD IT Banke -");

//do while petlja u kombinaciji sa svitchom
        do {
            System.out.println("\n1- Kreiraj novi racun  \n2- Transfer novca \n3- Ispis stanja racuna \n0- Izlaz");
            option = isInteger();

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
                    double deposit = isDouble();
                    if (deposit <= 0) {
                        System.out.print("Vas polog na racun mora biti veci od 0");
                        deposit = isDouble();
                    } else {
                        account.addFunds(deposit);
                        users.add(Account.getUkupanBrojRacuna() - 1, account);
                        System.out.print("Vas racun je kreiran, stanje vaseg racuna je " + deposit + "\n");
                    } //Korisnikov fajl karton
                    File karton = new File("Karton/" + account.getAccountOwner());
                    PrintWriter output = new PrintWriter(karton);
                    output.println("Karton korisnika " + account.getAccountOwner());
                    output.println();
                    output.println("Broj racuna: " + account.getAccountNumber());
                    output.println("Pocetno stanje racuna: " + account.getAmmountOnAccount());
                    output.close();

                    break;

                case 2:

                    //kreiranje Transfer objekta i prebacivanje novca sa racuna na racun
                    Transfer transfer = new Transfer();
                    System.out.println("Unesite broj racuna sa kojeg zelite da posaljete novac :");

                    transfer.setSourceAccount(isInteger());
                    System.out.println("");
                    System.out.println("Unesite racun na koji zelite da posaljete novac :");
                    transfer.setTargetAccount(isInteger());
                    System.out.println("Unesite kolicinu novca kojeg saljete: ");
                    transfer.setAmmount(isDouble());
                    transfer.prebacivanje();
                    //Upisivanje transakcije
                    File transaction = new File("Transaction/" + transfer.getAmmount());
                    PrintWriter outputTransaction = new PrintWriter(transaction);
                    outputTransaction.println("Racun br: " + transfer.getSourceAccount() + " umanjen za:\n");
                    outputTransaction.println(transfer.getAmmount() + " KM.");
                    outputTransaction.println("Racun br. " + transfer.getTargetAccount() + " uvecan za:\n");
                    outputTransaction.println(transfer.getAmmount() + " KM.");
                    outputTransaction.close();

                    break;

                case 3:
                    //Ispis racuna uz odradjenu validaciju
                    System.out.println("Unesite broj vaseg racuna: ");
                    int brojRacuna = isInteger();
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