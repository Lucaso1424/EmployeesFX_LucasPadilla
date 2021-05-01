/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EmployeeFX;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author lucas
 */
public class Main extends Application {

    public void start(Stage primaryStage) {
        Model m = new Model();
        Vista v = new Vista();
        Controlador c = new Controlador(v);
        c.initController();

        primaryStage.setTitle("EmployeeFX - Lucas Padilla Hidalgo");
        primaryStage.setScene(new Scene(v.getAnchorPane(), 900, 600));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}