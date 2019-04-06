package kssZamek;
import java.util.concurrent.TimeUnit;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Lock {
    private String kod;
    private String kodAdmin;
    private int licznik=1;



    public Lock(String kod, String kodAdmin) {
        this.kod=kod;
        this.kodAdmin=kodAdmin;
    }

    public int sprawdz(String kodTest) throws InterruptedException {
        Scanner scan = new Scanner(System.in);
        if(licznik<3) {
            if(kodTest.equals(this.kod)) {
                return odblokujZamek();

            }
            else {
                System.out.println("Zły kod, spróbuj ponownie");
                licznik++;
                return 0;
            }
        }
        else if(licznik==3) {
            if(kodTest.equals(this.kod)) {
                return odblokujZamek();
            }else {
                System.out.println("3 niepoprawne próby, kssZamek.Lock zablokowany na 5s. Proszę czekać...");
                Thread.sleep(5000);
                licznik++;
                return 0;
            }
        }

        else if(licznik==4) {
            if(kodTest.equals(this.kod)) {
                return odblokujZamek();

            }else {
                System.out.println("4 niepoprawne próby, kssZamek.Lock zablokowany na 10s. Proszę czekać...");
                Thread.sleep(10000);
                System.out.println("UWAGA! W przypadku kolejnego niepowodzenia będzie wymagana interwencja administratora!");
                licznik++;
                return 0;
            }
        }
        else {
            if(kodTest.equals(this.kod)) {
                return odblokujZamek();

            }else {
                System.out.println("5 niepoprawnych prób, kssZamek.Lock zablokowany. Poproś o pomoc administratora.");
                return 2;
            }
        }


    }

    public void admin(String kod) throws FileNotFoundException {
        if(kod.equals(kodAdmin)) {
            System.out.println("Przyznano dostęp administratora.");
            System.out.println("Podaj stary kod do odblokowania zamka:");
            Scanner scan = new Scanner(System.in);
            String test = scan.nextLine();

            if(test.equals(getKod())) {

                PrintWriter zapis = new PrintWriter("haslo.txt");

                System.out.println("Podaj nowy kod:");
                test=scan.nextLine();
                setKod(test);
                System.out.println("Nowy kod to: " + test );
                zapis.println(this.kod);
                zapis.println(this.kodAdmin);
                zapis.close();
            }
            else {
                System.out.println("Niepoprawny kod do odblokowania zamka.");
            }


        }
        else
            System.out.println("Zły kod administratora.");
        System.out.println("");
        return;
    }


    private int odblokujZamek() {
        licznik=1;
        Scanner scan = new Scanner(System.in);
        System.out.println("||| ZAMEK ODBLOKOWANY |||");
        System.out.println("Wpisz 0 aby zablokować kssZamek.Lock.");
        String test="";
        while(!test.equals("0"))
            test = scan.nextLine();
        System.out.println("||| ZAMEK ZABLOKOWANY |||");
        System.out.println("");
        return 1;
    }


    public String getKod() {
        return kod;
    }


    public void setKod(String kod) {
        this.kod = kod;
    }


    public String getKodAdmin() {
        return kodAdmin;
    }


    public void setKodAdmin(String kodAdmin) {
        this.kodAdmin = kodAdmin;
    }




}