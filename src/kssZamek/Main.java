package kssZamek;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
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
        TextField textField = new TextField();
        HBox layout = new HBox();
        VBox lock = new VBox();
        lock.getChildren().add(textField);

        Button buttonOne = new Button("1");
        Button buttonTwo = new Button("2");
        Button buttonThree = new Button("3");
        Button buttonFour = new Button("4");
        Button buttonFive = new Button("5");
        Button buttonSix = new Button("6");
        Button buttonSeven = new Button("7");
        Button buttonEight = new Button("8");
        Button buttonNine = new Button("9");
        Button buttonZero = new Button("0");
        Button buttonHash = new Button("#");
        Button buttonAsterisk = new Button("*");

        GridPane keyboard = new GridPane();

        //Setting size for the pane
        keyboard.setMinSize(1000, 500);

        //Setting the padding
        keyboard.setPadding(new Insets(10, 10, 10, 10));

        //Setting the vertical and horizontal gaps between the columns
        keyboard.setVgap(5);
        keyboard.setHgap(5);

        //Setting the Grid alignment
        keyboard.setAlignment(Pos.CENTER);
        keyboard.add(buttonOne, 0,0);
        keyboard.add(buttonTwo, 1,0);
        keyboard.add(buttonThree, 2,0);
        keyboard.add(buttonFour, 0,1);
        keyboard.add(buttonFive, 1,1);
        keyboard.add(buttonSix, 2,1);
        keyboard.add(buttonSeven, 0,2);
        keyboard.add(buttonEight, 1,2);
        keyboard.add(buttonNine, 2,2);
        keyboard.add(buttonHash, 0,3);
        keyboard.add(buttonZero, 1,3);
        keyboard.add(buttonAsterisk, 2,3);

        lock.getChildren().add(keyboard);


        VBox info = new VBox();

        Text messege = new Text("SIEMA");
        File file = new File(getClass().getResource("/resources/img/locked.png").getPath());
        ImageView img = new ImageView(file.toURI().toString());
        img.setFitHeight(300);
        img.setFitWidth(300);
        info.getChildren().add(messege);
        info.getChildren().add(img);


        layout.getChildren().addAll(lock, info);

        Scene scene = new Scene(layout);


        //Setting the title to Stage.
        primaryStage.setTitle("Electronic lock simulator");

        //Setting the scene to Stage
        primaryStage.setScene(scene);

        //Displaying the stage
        primaryStage.show();


//Setting the text object as a node

    }
}



