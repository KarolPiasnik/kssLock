package kssZamek;

import java.util.Scanner;
        import java.io.File;
        import java.io.FileNotFoundException;


public class Main {

    public static void rob(Lock zamek) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        String proba;
        int test=0;
        while(test==0) {
            System.out.println("Podaj kod do odblokowania zamka. Wpisz 0 aby wrocic do menu.");
            proba=scanner.nextLine();
            if(proba.equals("0"))
                return;
            else
                test = zamek.sprawdz(proba);
        }
        if(test==2) {
            System.out.println("Elo admin. Co teraz?");
        }
    }

    public static void main(String[] args)  throws InterruptedException, FileNotFoundException  {

        File plik = new File("haslo.txt");
        Scanner in=new Scanner(plik);
        String haslo, hasloA;
        haslo=in.nextLine();
        hasloA=in.nextLine();

        Lock zamek = new Lock(haslo, hasloA);

        Scanner scan = new Scanner(System.in);
        String proba;
        int test1;

        while(true) {
            System.out.println("--- MENU ---");
            System.out.println("Wybierz czynność:");
            System.out.println("1- Odblokuj kssZamek.Lock");
            System.out.println("2- Zmien hasło");
            System.out.println("0- Zakończ program");
            proba=scan.nextLine();

            if(proba.equals("0")) {
                return;
            }
            else if(proba.equals("1")) {
                rob(zamek);
                continue;
            }
            else if(proba.equals("2")) {
                System.out.println("Podaj kod administratora. Wpisz '0' jeśli chcesz wrócić do głównego menu");
                proba=scan.nextLine();
                if(proba.equals("0")) {
                    continue;
                }
                else
                    zamek.admin(proba);

            }
            else{
                System.out.println("Wpisano niepoprawną wartość.");
                System.out.println("");
                continue;
            }
        }
    }


}



//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

