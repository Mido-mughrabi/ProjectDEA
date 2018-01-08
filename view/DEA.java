/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dea;

/**
 *
 * @author baraa
 */

import java.io.FileNotFoundException;
import java.util.Hashtable;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class DEA extends Application {
    static Main mains=new Main();
   static String dea;
   
    Circle []new_CircleArray(int anzahl){
        int zeile=0,element=0,height=250;
        Circle []Cir = new Circle[anzahl];
        zeile=(int)anzahl/5;
        if(anzahl%5!=0)zeile++;
        for(int zeile_index=1; zeile_index<=zeile; zeile_index++){
            int plus=5;
            if(zeile_index==zeile){
               if(anzahl%5==0) plus=5;
               else plus=anzahl%5;
            }
            for(int i=element; i<element+plus; i++){
                if(i%5==0){
                    Cir[i] = new Circle(550,height,50); 
                }
                if(i%5==1){
                    Cir[i-1] = new Circle(450,height,50);
                    Cir[i]   = new Circle(650,height,50);
                }
                if(i%5==2){
                    Cir[i-2] = new Circle(350,height,50); 
                    Cir[i-1] = new Circle(550,height,50);
                    Cir[i]   = new Circle(750,height,50); 
                }
                if(i%5==3){
                    Cir[i-3] = new Circle(250,height,50);
                    Cir[i-2] = new Circle(450,height,50);
                    Cir[i-1] = new Circle(650,height,50);
                    Cir[i]   = new Circle(850,height,50);
                }
                if(i%5==4){
                    Cir[i-4] = new Circle(150,height,50);
                    Cir[i-3] = new Circle(350,height,50);
                    Cir[i-2] = new Circle(550,height,50);
                    Cir[i-1] = new Circle(750,height,50);
                    Cir[i]   = new Circle(950,height,50); 
                }
                anzahl--;
            }
            element=element+5;
            height=height+200;   
        }
        for(Circle cir: Cir){
            cir.setStroke(Color.DARKORANGE);
            cir.setStrokeWidth(2);
            cir.setFill(Color.WHITE);
        }
        Cir[0].setStrokeWidth(5);
        return Cir;
    }
   
   void hashtable(Hashtable<String, String> function){
        for (String elem : function.keySet()) { 
            String s = function.get(elem); 
            System.out.println(elem + " -> " + s+"\n"); 
        }
   }
   
    @Override
    public void start(Stage primaryStage) {
        int anzahl=mains.states.size();
        Hashtable<String, String> function=mains.function;
        hashtable(function);
        Circle []Cir = new Circle[8];
        
        Circle []Cir1=new_CircleArray(anzahl);
        
        Polygon []pol = new Polygon[4];
        Label []Lab = new Label[13];
        Rectangle []Rec=new Rectangle[4];
        TextField txt = new TextField();
        Button btn = new Button();
        Button btn1 = new Button();
        FlowPane flowpane=new FlowPane();
        btn.setText("nächster Zustand !");
        btn.setFont(Font.font(15));
        btn1.setText("Reset !");
        btn1.setFont(Font.font(15));
        btn1.setLayoutX(10);
        btn1.setLayoutY(50);
        
        Lab[10]=new Label("Eingabe :");
        txt.setMaxWidth(50.0);
        flowpane.setOrientation(Orientation.HORIZONTAL);
        flowpane.setHgap(10);
        flowpane.setVgap(10);
        flowpane.setLayoutX(10);
        flowpane.getChildren().addAll(Lab[10],txt,btn);
        
        Cir[0] = new Circle(120,250,50);
        Cir[1] = new Circle(350,250,50);
        Cir[2] = new Circle(600,250,50);
        Cir[3] = new Circle(120,430,50);
        Cir[4] = new Circle(350,430,50);
        Cir[5] = new Circle(600,430,50);
        Cir[6] = new Circle(600,250,35);
        Cir[7] = new Circle(350,430,35);
        for(Circle cir: Cir){
            cir.setStroke(Color.DARKORANGE);
            cir.setStrokeWidth(2);
            cir.setFill(Color.WHITE);
        }
        Cir[0].setStrokeWidth(5);
        
        Lab[6]=new Label("m");
            Lab[6].setLayoutX(215);
            Lab[6].setLayoutY(203);
        Lab[7]=new Label("m");
            Lab[7].setLayoutX(313);
            Lab[7].setLayoutY(305);
        Lab[8]=new Label("m");
            Lab[8].setLayoutX(230);
            Lab[8].setLayoutY(383);
        Lab[9]=new Label("k");
            Lab[9].setLayoutX(573);
            Lab[9].setLayoutY(306);
        Lab[11]= new Label("Beschreibung :");
            Lab[11].setLayoutX(375);
        Lab[12]= new Label("");
            Lab[12].setLayoutX(500);
            Lab[12].setLayoutY(50);
            Lab[12].setVisible(false);
            Lab[12].setFont(Font.font(20));
        for(int i=0; i<Lab.length; i++){
            if(i<6){
               Lab[i]=new Label("S"+i);
               Lab[i].setLayoutX(Cir[i].getCenterX()-15);
               Lab[i].setLayoutY(Cir[i].getCenterY()-23);
            }
            if(i!=12)Lab[i].setFont(Font.font(30));
            if(i<10)Lab[i].setTextFill(Color.DARKGRAY);
        }
        
        
        
        
        Rec[0]=new Rectangle(190,243,70,15);
        Rec[1]=new Rectangle(210,423,70,15);
        Rec[2]=new Rectangle(343,306,15,50);
        Rec[3]=new Rectangle(593,306,15,50);
       
        
        for(int i=0; i<pol.length; i++){
            pol[i]=new Polygon();
            pol[i].setFill(Color.DARKGRAY);
            Rec[i].setFill(Color.DARKGRAY);
        }
        pol[0].getPoints().addAll(new Double[]{
            260.0, 233.0,
            260.0, 268.0,
            280.0, 251.0});
        pol[1].getPoints().addAll(new Double[]{
            210.0, 413.0,
            210.0, 448.0,
            190.0, 431.0});
        pol[2].getPoints().addAll(new Double[]{
            333.0, 356.0,
            368.0, 356.0,
            351.0, 376.0});
        pol[3].getPoints().addAll(new Double[]{
            583.0, 356.0,
            618.0, 356.0,
            601.0, 376.0});

        btn.setOnAction((ActionEvent event) -> {
            
            String x=mains.execute(txt.getText().toString());
            if(x.equals("")){
                for(Circle cir: Cir)cir.setStrokeWidth(2);
                Cir[0].setStrokeWidth(5);
                Lab[12].setText("DEA im Zustand: S0");
                Lab[12].setVisible(true);
            }
            if(x.equals("S1")){
                for(Circle cir: Cir)cir.setStrokeWidth(2);
                Cir[1].setStrokeWidth(5);
                Lab[12].setText("DEA im Zustand: S1");
                Lab[12].setVisible(true);
            }
            if(x.equals("S2")){
                for(Circle cir: Cir)cir.setStrokeWidth(2);
                Cir[2].setStrokeWidth(5);
                Cir[6].setStrokeWidth(5);
                Lab[12].setText("DEA im Zustand: S2");
                Lab[12].setVisible(true);
            }
            if(x.equals("S3")){
                for(Circle cir: Cir)cir.setStrokeWidth(2);
                Cir[3].setStrokeWidth(5);
                Lab[12].setText("DEA im Zustand: S3");
                Lab[12].setVisible(true);
            }
            if(x.equals("S4")){
                for(Circle cir: Cir)cir.setStrokeWidth(2);
                Cir[4].setStrokeWidth(5);
                Cir[7].setStrokeWidth(5);
                Lab[12].setText("DEA im Zustand: S4");
                Lab[12].setVisible(true);
            }
            if(x.equals("S5")){
                for(Circle cir: Cir)cir.setStrokeWidth(2);
                Cir[5].setStrokeWidth(5);
                Lab[12].setText("DEA im Zustand: S5");
                Lab[12].setVisible(true);
            }
            if(x.equals("f")){
                Lab[12].setText("Ungültige Eingabe !");
                Lab[12].setVisible(true);
            }
        });
        
         btn1.setOnAction((ActionEvent event) -> {
             mains.initSymbolTable(dea);
             mains.compile();
             for(Circle cir: Cir)cir.setStrokeWidth(2);
             Cir[0].setStrokeWidth(5);
             Lab[12].setVisible(false);
         });
        
        Group root = new Group();
        /*root.getChildren().addAll(flowpane,btn1, Cir[0], Cir[1], Cir[2], Cir[3], Cir[4], Cir[5], Cir[6], Cir[7],
                                  Rec[0],pol[0],Rec[1],pol[1],Rec[2],pol[2],Rec[3],pol[3],
                                  Lab[0],Lab[1],Lab[2],Lab[3],Lab[4],Lab[5],Lab[6],Lab[7],Lab[8],Lab[9],Lab[11],Lab[12]);*/
        root.getChildren().addAll(Cir1);
        Scene scene = new Scene(root, 700, 500);
        primaryStage.setMaxHeight(950);
        primaryStage.setMaxWidth(1100);
        primaryStage.setMinHeight(950);
        primaryStage.setMinWidth(1100);
        primaryStage.setTitle("DEA !");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{
            dea = mains.readFile("test.dea");
	}
	catch(FileNotFoundException e){
            return ;
	}
        mains.initSymbolTable(dea);
        mains.compile();
        launch(args);
    }
}