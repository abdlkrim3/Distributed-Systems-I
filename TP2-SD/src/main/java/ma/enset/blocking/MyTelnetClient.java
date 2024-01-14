package ma.enset.blocking;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class MyTelnetClient extends Application {
    private ObservableList<String> data= FXCollections.observableArrayList();

    public static void main(String[] args) throws IOException {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane root=new BorderPane();
        ListView<String> listView=new ListView<>(data);
        listView.setStyle("-fx-background-color:transparent;");
        TextField textFieldMwssage=new TextField();
        listView.setCellFactory(param -> new ListCell<String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(item);
                    setStyle("-fx-background-color: #2663fc; -fx-text-fill: white; -fx-padding: 10px; -fx-background-radius: 20px;");
                    setPrefWidth(100); // Définit la largeur de la cellule
                    setWrapText(true); // Permet au texte de se mettre à la ligne si nécessaire

                    setText(item);
                    setStyle("-fx-background-color: #f0f0ee; -fx-text-fill: black; -fx-padding: 10px; -fx-background-radius: 20px;");

                    //setTextFill(Color.BLUE); // Changer la couleur de la police
                    setStyle("-fx-text-fill: blue;-fx-font-size: 20px");
                    setPadding(new Insets(0,0,0,300));
                }
            }
        });

        Button buttonSend=new Button("Send");
        buttonSend.setStyle("-fx-background-color: blue; -fx-text-fill: white;-fx-border-radius: 8px");
        textFieldMwssage.setStyle("-fx-background-color: #5db9ef");
        root.setStyle("-fx-background: linear-gradient(to bottom, #1488cc, #2b32b2);");
        Label labelMessage=new Label("Message");
        labelMessage.setStyle("-fx-font: normal bold 20px 'serif';");
        Image image = new Image(getClass().getResourceAsStream("/images/default.png"));
        ImageView imageView = new ImageView(image);
        root.setCenter(imageView);
        HBox hBox=new HBox();
        hBox.getChildren().addAll(labelMessage,textFieldMwssage,buttonSend);
        HBox hBox1=new HBox();
        root.setBottom(hBox);
        root.setCenter(listView);
        Scene scene=new Scene(root,400,300);
        stage.setScene(scene);
        stage.setTitle("Client");
        stage.show();

            buttonSend.setOnAction(actionEvent -> {
                String msgSend=textFieldMwssage.getText();
                data.addAll(msgSend);
            });


    }
    private void showMessage(String message){
        data.clear();
        data.addAll(message);
    }

}
