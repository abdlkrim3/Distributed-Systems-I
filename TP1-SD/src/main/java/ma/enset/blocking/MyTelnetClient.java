package ma.enset.blocking;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
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
        listView.setStyle("-fx-background-color: GRAY;");
        Button buttonSend=new Button("Send");
        buttonSend.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
        TextField textFieldMwssage=new TextField();
        root.setStyle("-fx-background-color: BEIGE;");
        Label labelMessage=new Label("Message");
        labelMessage.setStyle("-fx-font: normal bold 20px 'serif' ");
        HBox hBox=new HBox();
        hBox.getChildren().addAll(labelMessage,textFieldMwssage,buttonSend);
        root.setBottom(hBox);
        root.setCenter(listView);
        Scene scene=new Scene(root,400,300);
        stage.setScene(scene);
        stage.setTitle("Joueur2");
        stage.show();
        Socket socket = new Socket("localhost", 8090);
        try{
            InputStream in = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(in);
            BufferedReader br = new BufferedReader(isr);
            OutputStream out = socket.getOutputStream();
            PrintWriter pr = new PrintWriter(out,true);
            new Thread(()->{
                String response;
                try{
                    while ((response= br.readLine())!=null){
                        data.addAll(response);
                    }
                }catch (Exception e){
                    String message="Server disconnected";
                    showMessage(message);
                }
            }).start();

            String req;
            //Scanner sc  = new Scanner(System.in);
            buttonSend.setOnAction(actionEvent -> {
                String msgSend=textFieldMwssage.getText();
                data.addAll(msgSend);
                pr.println(msgSend);
            });


        }catch (Exception e){
            e.printStackTrace();
        }


    }
    private void showMessage(String message){
        data.clear();
        data.addAll(message);
    }

}
