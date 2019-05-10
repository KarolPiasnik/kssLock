package kssZamek;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;


public class Main extends Application {

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
        launch();
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


    @Override
    public void start(Stage primaryStage) throws Exception {
        Line line = new Line();
        line.setStartX(100.0);
        line.setStartY(150.0);
        line.setEndX(500.0);
        line.setEndY(150.0);

        Text text = new Text();
        //Setting font to the text
        text.setFont(new Font(45));
        //setting the position of the text
        text.setX(50);
        text.setY(150);
        text.setText("Welcome to Tutorialspoint");

        Group root = new Group(line, text);
        //Retrieving the observable list object
        ObservableList list = root.getChildren();
        StackPane pane = new StackPane();
        Scene scene = new Scene(root, 600, 300);
        scene.setFill(Color.BROWN);



        //Setting the title to Stage.
        primaryStage.setTitle("Sample application");

        //Setting the scene to Stage
        primaryStage.setScene(scene);

        //Displaying the stage
        primaryStage.show();


//Setting the text object as a node

    }
}


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

