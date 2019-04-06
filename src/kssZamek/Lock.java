package kssZamek;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Lock {
    private String code;
    private String adminCode;
    private int counter = 1;


    public Lock(String code, String adminCode) {
        this.code = code;
        this.adminCode = adminCode;
    }

    public int check(String kodTest) throws InterruptedException {
        Scanner scan = new Scanner(System.in);
        if (counter < 3) {
            if (kodTest.equals(this.code)) {
                return unlock();

            } else {
                System.out.println("Zły kod, spróbuj ponownie");
                counter++;
                return 0;
            }
        } else if (counter == 3) {
            if (kodTest.equals(this.code)) {
                return unlock();
            } else {
                System.out.println("3 niepoprawne próby, zamek zablokowany na 5s. Proszę czekać...");
                Thread.sleep(5000);
                counter++;
                return 0;
            }
        } else if (counter == 4) {
            if (kodTest.equals(this.code)) {
                return unlock();

            } else {
                System.out.println("4 niepoprawne próby, zamek zablokowany na 10s. Proszę czekać...");
                Thread.sleep(10000);
                System.out.println("UWAGA! W przypadku kolejnego niepowodzenia będzie wymagana interwencja administratora!");
                counter++;
                return 0;
            }
        } else {
            if (kodTest.equals(this.code)) {
                return unlock();

            } else {
                System.out.println("5 niepoprawnych prób, zamek zablokowany. Poproś o pomoc administratora.");
                return 2;
            }
        }


    }

    public void admin(String kod) throws FileNotFoundException {
        if (kod.equals(adminCode)) {
            System.out.println("Przyznano dostęp administratora.");
            System.out.println("Podaj stary kod do odblokowania zamka:");
            Scanner scan = new Scanner(System.in);
            String test = scan.nextLine();

            if (test.equals(getCode())) {

                PrintWriter writer = new PrintWriter("src/kssZamek/haslo.txt");

                System.out.println("Podaj nowy kod:");
                test = scan.nextLine();
                setCode(test);
                System.out.println("Nowy kod to: " + test);
                writer.println(this.code);
                writer.println(this.adminCode);
                writer.close();
            } else {
                System.out.println("Niepoprawny kod do odblokowania zamka.");
            }


        } else
            System.out.println("Zły kod administratora.");
        System.out.println("");
        return;
    }


    private int unlock() {
        counter = 1;
        Scanner scan = new Scanner(System.in);
        System.out.println("||| ZAMEK ODBLOKOWANY |||");
        System.out.println("Wpisz 0 aby zablokować zamek.");
        String test = "";
        while (!test.equals("0"))
            test = scan.nextLine();
        System.out.println("||| ZAMEK ZABLOKOWANY |||");
        System.out.println("");
        return 1;
    }


    public String getCode() {
        return code;
    }


    public void setCode(String code) {
        this.code = code;
    }


    public String getAdminCode() {
        return adminCode;
    }


    public void setAdminCode(String adminCode) {
        this.adminCode = adminCode;
    }


}