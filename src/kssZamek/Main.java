package kssZamek;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;

public class Main extends Application {


    public static void main(String[] args) {
        launch();

    }


    @Override
    public void start(Stage primaryStage) throws IOException {
        //read passwords from files
        File passwordFile = new File(getClass().getResource("/resources/text/password.txt").getPath());
        File adminPasswordFile = new File(getClass().getResource("/resources/text/adminPassword.txt").getPath());
        BufferedReader Buff = new BufferedReader(new FileReader(passwordFile));
        String text = Buff.readLine();
        BufferedReader BuffA = new BufferedReader(new FileReader(adminPasswordFile));
        String textA = BuffA.readLine();

        Lock lockObject = new Lock(text, textA);

        TextField textField = new TextField();
        textField.setDisable(Boolean.TRUE);
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
        Button buttonBackspace = new Button("Backspace");


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
        keyboard.add(buttonOne, 0, 0);
        keyboard.add(buttonTwo, 1, 0);
        keyboard.add(buttonThree, 2, 0);
        keyboard.add(buttonFour, 0, 1);
        keyboard.add(buttonFive, 1, 1);
        keyboard.add(buttonSix, 2, 1);
        keyboard.add(buttonSeven, 0, 2);
        keyboard.add(buttonEight, 1, 2);
        keyboard.add(buttonNine, 2, 2);
        keyboard.add(buttonHash, 0, 3);
        keyboard.add(buttonZero, 1, 3);
        keyboard.add(buttonAsterisk, 2, 3);
        keyboard.add(buttonBackspace, 0, 4);

        buttonOne.setOnAction(e -> textField.setText(textField.getText().concat(((Button) (e.getTarget())).getText())));
        buttonTwo.setOnAction(e -> textField.setText(textField.getText().concat(((Button) (e.getTarget())).getText())));
        buttonThree.setOnAction(e -> textField.setText(textField.getText().concat(((Button) (e.getTarget())).getText())));
        buttonFour.setOnAction(e -> textField.setText(textField.getText().concat(((Button) (e.getTarget())).getText())));
        buttonFive.setOnAction(e -> textField.setText(textField.getText().concat(((Button) (e.getTarget())).getText())));
        buttonSix.setOnAction(e -> textField.setText(textField.getText().concat(((Button) (e.getTarget())).getText())));
        buttonSeven.setOnAction(e -> textField.setText(textField.getText().concat(((Button) (e.getTarget())).getText())));
        buttonEight.setOnAction(e -> textField.setText(textField.getText().concat(((Button) (e.getTarget())).getText())));
        buttonNine.setOnAction(e -> textField.setText(textField.getText().concat(((Button) (e.getTarget())).getText())));
        buttonZero.setOnAction(e -> textField.setText(textField.getText().concat(((Button) (e.getTarget())).getText())));
        buttonBackspace.setOnAction(e -> {
            if (textField.getText().length() > 0) {
                textField.setText(textField.getText().substring(0, textField.getText().length() - 1));
            }
        });

        lock.getChildren().add(keyboard);

        VBox info = new VBox();

        Text messege = new Text("Zamek elektroniczny");
        File lockedImage = new File(getClass().getResource("/resources/img/locked.png").getPath());
        ImageView img = new ImageView(lockedImage.toURI().toString());
        img.setFitHeight(300);
        img.setFitWidth(300);
        info.getChildren().add(messege);
        info.getChildren().add(img);


        buttonHash.setOnAction(e -> {
            try {
                if (lockObject.enterAdminMode(textField.getText())){
                  messege.setText("Włączono tryb administratora");
                  textField.setText("");
                }
            } catch (AlreadyInAdminModeException e1) {
                messege.setText("Opuściłes tryb administratora");
                lockObject.leaveAdminMode();
            }
        });

        buttonAsterisk.setOnAction(e -> {
            if (lockObject.getAdminMode()) {
                try {
                    lockObject.changePassword(textField.getText());
                    textField.setText("");
                    messege.setText("Hasło zostało zmienione");
                } catch (NotInAdminModeException e1) {
                    e1.printStackTrace();
                } catch (NotValidPasswordException e1) {
                    messege.setText("Hasło musi składać się z 6 cyfr");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            } else {
                try {
                    if (lockObject.unlock(textField.getText())) {
                        File unlockedImage = new File(getClass().getResource("/resources/img/unlocked.png").getPath());
                        img.setImage(new Image(unlockedImage.toURI().toString()));
                        messege.setText("Zamek odblokowany");
                        textField.setText("");
                    } else {
                        messege.setText("Błędne hasło, zostało ci jeszcze " + lockObject.getAttemptsLeft() + " prób");

                    }
                } catch (AlreadyUnlockedException e1) {
                    img.setImage(new Image(lockedImage.toURI().toString()));
                    lockObject.lock();
                    messege.setText("Zamek zablokowany.");
                } catch (AdminBlockedException e1) {
                    messege.setText("Zamek został zablokowany z powodu wielokrotnego wpisania złego hasła! Możesz go odblokować logując się jako administrator.");
                }
            }
        });

        layout.getChildren().addAll(lock, info);

        Scene scene = new Scene(layout);


        //Setting the title to Stage.
        primaryStage.setTitle("Electronic lock simulator");

        //Setting the scene to Stage
        primaryStage.setScene(scene);

        //Displaying the stage
        primaryStage.show();

    }
}



