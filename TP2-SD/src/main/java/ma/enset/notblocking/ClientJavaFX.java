package ma.enset.notblocking;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.*;
import java.net.Socket;

public class ClientJavaFX extends Application {
    private ObservableList<String> data= FXCollections.observableArrayList();
    private  boolean client=false;
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane root=new BorderPane();
        ListView<String> listView=new ListView<>(data);
        TextField textFieldMwssage=new TextField("Aa");
        textFieldMwssage.setStyle("-fx-background-color: #f0f0f0;-fx-background-radius: 12px;-fx-border-radius: 12px;-fx-pref-width: 300px");
        root.setStyle("-fx-background: linear-gradient(to left, #0052d4, #4364f7, #6fb1fc);");
        Image image = new Image(getClass().getResourceAsStream("/images/agoujil.png"));
        Image image1=new Image(getClass().getResourceAsStream("/images/enset3.png"));
        Image imgClose=new Image(getClass().getResourceAsStream("/images/close3.png"));
        Image imgSend=new Image(getClass().getResourceAsStream("/images/send.png"));
        Image imgIcons=new Image(getClass().getResourceAsStream("/images/icon.jpg"));
        Image imgIMenu=new Image(getClass().getResourceAsStream("/images/menu2.gif"));
        ImageView imageClose=new ImageView(imgClose);
        ImageView imageMenu=new ImageView(imgIMenu);
        ImageView imageIcons=new ImageView(imgIcons);
        ImageView imageSend=new ImageView(imgSend);
        imageMenu.setFitHeight(20);imageMenu.setFitWidth(400);
        imageSend.setFitHeight(25);imageSend.setFitWidth(25);
        imageIcons.setFitHeight(25);imageIcons.setFitWidth(80);
        imageClose.setFitHeight(60);imageClose.setFitWidth(50);
        ImageView imageView1=new ImageView(image1);
        listView.setOpacity(0.9);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(60);imageView.setFitHeight(60);
        imageView1.setFitWidth(200);imageView1.setFitHeight(60);
        Label label=new Label("Onligne");
        label.setStyle("-fx-padding: 10px;-fx-font: normal bold 20px 'serif';-fx-text-fill: #1aff1a");
        HBox hBox=new HBox();
        HBox hBox1=new HBox();
        hBox1.setStyle("-fx-background-color: #094c82;");
        hBox.setStyle("-fx-background-color: #094c82");
        hBox1.getChildren().addAll(imageView,label,imageView1,imageClose);
        hBox.getChildren().addAll(imageIcons,textFieldMwssage,imageSend);
        VBox vBox=new VBox();
        vBox.getChildren().addAll(hBox,imageMenu);
        root.setBottom(vBox);
        root.setTop(hBox1);
        root.setCenter(listView);
        Scene scene=new Scene(root,400,600);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
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
        imageClose.setOnMouseClicked(actionEvent->{
            Platform.exit();
            System.exit(0);
        });
        imageSend.setOnMouseClicked(actionEvent->{
            client=true;
            String msgSend=textFieldMwssage.getText();
            data.addAll(msgSend);
            pr.println(msgSend);
            client=false;
        });
    }catch (Exception e){
        e.printStackTrace();
    }
        listView.setCellFactory(param -> new ListCell<String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                    setStyle("");
                } else if (client){
                    setText(item);
                    //setTextFill(Color.BLUE); // Changer la couleur de la police
                    setStyle("-fx-font: 20px 'serif';-fx-text-fill: white;-fx-background-color:linear-gradient(to bottom, #8a29d8,#2663fc);-fx-background-radius: 20px;");
                    setPadding(new Insets(0,0,0,300));// Définit la largeur de la cellule
                    setWrapText(true); // Permet au texte de se mettre à la ligne si nécessaire

                }else {
                    setText(item);
                    setStyle("-fx-font: 20px;-fx-text-fill: black;-fx-background-color:linear-gradient(to bottom, #c1bfbf,#f0f0f0) ; -fx-background-radius: 20px;");// Définit la largeur de la cellule
                    setWrapText(true); // Permet au texte de se mettre à la ligne si nécessaire

                }
            }
        });
    }
    private void showMessage(String message){
        data.clear();
        data.addAll(message);
    }

}
