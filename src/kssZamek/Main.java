package kssZamek;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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

import java.io.File;
import java.io.FileNotFoundException;



public class Main extends Application {



    public static void main(String[] args) {
        launch();

    }


    @Override
    public void start(Stage primaryStage) {
        Lock lockObject = new Lock("1111", "1234");

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

        buttonOne.setOnAction(e -> textField.setText(textField.getText().concat(((Button)(e.getTarget())).getText())));
        buttonTwo.setOnAction(e -> textField.setText(textField.getText().concat(((Button)(e.getTarget())).getText())));
        buttonThree.setOnAction(e -> textField.setText(textField.getText().concat(((Button)(e.getTarget())).getText())));
        buttonFour.setOnAction(e -> textField.setText(textField.getText().concat(((Button)(e.getTarget())).getText())));
        buttonFive.setOnAction(e -> textField.setText(textField.getText().concat(((Button)(e.getTarget())).getText())));
        buttonSix.setOnAction(e -> textField.setText(textField.getText().concat(((Button)(e.getTarget())).getText())));
        buttonSeven.setOnAction(e -> textField.setText(textField.getText().concat(((Button)(e.getTarget())).getText())));
        buttonEight.setOnAction(e -> textField.setText(textField.getText().concat(((Button)(e.getTarget())).getText())));
        buttonNine.setOnAction(e -> textField.setText(textField.getText().concat(((Button)(e.getTarget())).getText())));
        buttonZero.setOnAction(e -> textField.setText(textField.getText().concat(((Button)(e.getTarget())).getText())));





        lock.getChildren().add(keyboard);


        VBox info = new VBox();

        Text messege = new Text("SIEMA");
        File lockedImage = new File(getClass().getResource("/resources/img/locked.png").getPath());
        ImageView img = new ImageView(lockedImage.toURI().toString());
        img.setFitHeight(300);
        img.setFitWidth(300);
        info.getChildren().add(messege);
        info.getChildren().add(img);


        buttonHash.setOnAction(e -> {
            try {
                lockObject.enterAdminMode(textField.getText());
            } catch (AlreadyInAdminModeException e1) {
                messege.setText("Jesteś już zalogowany jako administrator.");

            }
        });

        buttonAsterisk.setOnAction(e -> {
            try {
                if(lockObject.unlock(textField.getText())){
                    File unlockedImage = new File(getClass().getResource("/resources/img/unlocked.png").getPath());
                    img.setImage(new Image(unlockedImage.toURI().toString()));
                    messege.setText("Zamek odblokowany");
                }
            } catch (AlreadyUnlockedException e1) {
                img.setImage(new Image(lockedImage.toURI().toString()));
                lockObject.lock();
                messege.setText("Zamek zablokowany.");
            } catch (AdminBlockedException e1) {
                messege.setText("Zamek został zablokowany z powodu wielokrotnego wpisania złego hasła! Możesz go odblokować logując się jako administrator.");
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


//Setting the text object as a node

    }
}



