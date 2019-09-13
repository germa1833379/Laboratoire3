package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.util.Timer;
import java.util.TimerTask;


public class Main extends Application {
    int pts=0;
    int multi=1;
    int auto5Sec=0;
    Label nbDePts = new Label(Integer.toString(pts));
    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Cookie Clicker");
        primaryStage.setWidth(1920);
        primaryStage.setHeight(1080);
        primaryStage.setResizable(false);


        primaryStage.setScene(
                new Scene(root()
                )
        );

        primaryStage.show();
        primaryStage.setFullScreen(true);
    }
    public Group root(){

        nbDePts.setScaleX(7);
        nbDePts.setScaleY(7);
        nbDePts.setTranslateX(960);
        nbDePts.setTranslateY(350);
        Button clicker = new Button("Click Here");
        clicker.setTranslateX(930);
        clicker.setTranslateY(520);
        clicker.setScaleX(5);
        clicker.setScaleY(5);
        clicker.setOnAction(((event) -> {pts+=multi;}));




        //Les upgrades sont tous pareil ou presque.

        Button up1 = new Button("Auto Clicker 1");
        up1.setTranslateY(30);
        final int[] priceup1 = {10};
        Label l1 = new Label(Integer.toString(priceup1[0]));
        up1.setOnAction((event -> {if(buy(priceup1[0])) {auto5Sec+=1;
            priceup1[0] =(int)(priceup1[0] *1.1);}}));

        Button up2 = new Button("More Clicks 2");
        up2.setTranslateY(60);
        final int[] priceup2 = {100};
        Label l2 = new Label(Integer.toString(priceup2[0]));
        up2.setOnAction((event -> {if(buy(priceup2[0])) {auto5Sec+=3;
            priceup2[0] =(int)(priceup2[0] *1.1);}}));
        Button up3 = new Button("Better Clicks 1");
        up3.setTranslateY(90);
        final int[] priceup3 = {1000};
        Label l3 = new Label(Integer.toString(priceup3[0]));
        up3.setOnAction((event -> {if(buy(priceup3[0])) {multi+=1;
            priceup3[0] =(int)(priceup3[0] *1.1);}}));
        Button up4 = new Button("Better Clicks 2");
        up4.setTranslateY(120);
        final int[] priceup4 = {10000};
        Label l4 = new Label(Integer.toString(priceup2[0]));
        up4.setOnAction((event -> {if(buy(priceup4[0])) {multi+=5;
            priceup4[0] =(int)(priceup4[0] *1.1);}}));
        Button up5 = new Button("Quantum Computer");

        Label l5 = new Label("1 000 000");
        up5.setTranslateY(150);
        up5.setOnAction((event -> {if(buy(1000000)) {nbDePts.setText("WIIIIIIIINNNNNNNNNNNNN");}}));

        l1.setTranslateY(35);
        l2.setTranslateY(65);
        l3.setTranslateY(95);
        l4.setTranslateY(125);
        l5.setTranslateY(155);
        l1.setTranslateX(100);
        l2.setTranslateX(100);
        l3.setTranslateX(100);
        l4.setTranslateX(100);
        l5.setTranslateX(130);

        Timer timer = new Timer();
        final double[] restant = {0};
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                double rate =(auto5Sec*multi)/500.0;
                if(((double)rate+restant[0]<1)){
                    restant[0] +=rate;
                }else{
                    pts+=(int)(rate+restant[0]);
                    restant[0]=(double)(rate+restant[0])-(int)(rate+restant[0]);
                }
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        nbDePts.setText(Integer.toString(pts));
                        l1.setText(Integer.toString(priceup1[0]));
                        l2.setText(Integer.toString(priceup2[0]));
                        l3.setText(Integer.toString(priceup3[0]));
                        l4.setText(Integer.toString(priceup4[0]));
                    }
                });
            }
        }, 0, 10);

        Group all = new Group(clicker,up1,up2,up3,up4,up5,nbDePts,l1,l2,l3,l4,l5);
        return all;
    }
    public boolean buy(int price){
        if(pts>=price){
            pts-=price;
            return true;
        }else
            return false;
    }
    public static void main(String[] args) {
        launch(args);
    }
}
