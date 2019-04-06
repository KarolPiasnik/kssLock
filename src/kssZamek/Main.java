package kssZamek;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;


public class Main {

    public static void rob(Lock lock) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        String testing;
        int test = 0;
        while (test == 0) {
            System.out.println("Podaj kod do odblokowania zamka. Wpisz 0 aby wrocic do menu.");
            testing = scanner.nextLine();
            if (testing.equals("0"))
                return;
            else
                test = lock.check(testing);
        }
        if (test == 2) {
            System.out.println("Elo admin. Co teraz?");
        }
    }

    public static void main(String[] args) throws InterruptedException, FileNotFoundException {

        Path passwordFile = Paths.get("src/kssZamek/haslo.txt");
        Scanner in = new Scanner(passwordFile.toFile());
        String password, passwordA;
        password = in.nextLine();
        passwordA = in.nextLine();

        Lock lock = new Lock(password, passwordA);

        Scanner scan = new Scanner(System.in);
        String testing;
        int test1;

        while (true) {
            System.out.println("--- MENU ---");
            System.out.println("Wybierz czynność:");
            System.out.println("1- Odblokuj kssZamek.Lock");
            System.out.println("2- Zmien hasło");
            System.out.println("0- Zakończ program");
            testing = scan.nextLine();

            if (testing.equals("0")) {
                return;
            } else if (testing.equals("1")) {
                rob(lock);
                continue;
            } else if (testing.equals("2")) {
                System.out.println("Podaj kod administratora. Wpisz '0' jeśli chcesz wrócić do głównego menu");
                testing = scan.nextLine();
                if (testing.equals("0")) {
                    continue;
                } else
                    lock.admin(testing);

            } else {
                System.out.println("Wpisano niepoprawną wartość.");
                System.out.println("");
                continue;
            }
        }
    }


}


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

