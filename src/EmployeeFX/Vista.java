/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EmployeeFX;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import static javafx.scene.shape.StrokeType.OUTSIDE;
import javafx.scene.text.Text;
import static javafx.scene.text.TextAlignment.CENTER;
import javafx.stage.Stage;

/**
 *
 * @author lucas
 */

public class Vista {

    private AnchorPane AnchorPane;
    private Text text1;
    private Text text2;
    private Text text3;
    private Text text4;
    private Text text5;
    private TextField textField1;
    private TextField textField2;
    private TextField textField3;
    private TextField textField4;
    private Button buttonViewEmployee;
    private TableView tableView;

    public AnchorPane getAnchorPane() {
        return AnchorPane;
    }

    public void setAnchorPane(AnchorPane AnchorPane) {
        this.AnchorPane = AnchorPane;
    }

    public Text getText1() {
        return text1;
    }

    public void setText1(Text text1) {
        this.text1 = text1;
    }

    public Text getText2() {
        return text2;
    }

    public void setText2(Text text2) {
        this.text2 = text2;
    }

    public Text getText3() {
        return text3;
    }

    public void setText3(Text text3) {
        this.text3 = text3;
    }

    public Text getText4() {
        return text4;
    }

    public void setText4(Text text4) {
        this.text4 = text4;
    }

    public Text getText5() {
        return text5;
    }

    public void setText5(Text text5) {
        this.text5 = text5;
    }

    public TextField getTextField1() {
        return textField1;
    }

    public void setTextField1(TextField textField1) {
        this.textField1 = textField1;
    }

    public TextField getTextField2() {
        return textField2;
    }

    public void setTextField2(TextField textField2) {
        this.textField2 = textField2;
    }

    public TextField getTextField3() {
        return textField3;
    }

    public void setTextField3(TextField textField3) {
        this.textField3 = textField3;
    }

    public TextField getTextField4() {
        return textField4;
    }

    public void setTextField4(TextField textField4) {
        this.textField4 = textField4;
    }

    public Button getButtonViewEmployee() {
        return buttonViewEmployee;
    }

    public void setButtonViewEmployee(Button buttonViewEmployee) {
        this.buttonViewEmployee = buttonViewEmployee;
    }

    public TableView getTableView() {
        return tableView;
    }

    public void setTableView(TableView tableView) {
        this.tableView = tableView;
    }
    
    public Vista() {
        AnchorPane = new AnchorPane();

        text1 = new Text();
        text1.setStrokeWidth(0.0);
        text1.setStrokeType(OUTSIDE);
        text1.setTextAlignment(CENTER);
        text1.setLayoutX(14.0);
        text1.setLayoutY(129.0);
        text1.setText("Name");
        text1.setWrappingWidth(188.13671875);

        AnchorPane.getChildren().add(text1);
        
        text2 = new Text();
        text2.setStrokeWidth(0.0);
        text2.setStrokeType(OUTSIDE);
        text2.setTextAlignment(CENTER);
        text2.setLayoutX(237.0);
        text2.setLayoutY(133.0);
        text2.setText("Surname");
        text2.setWrappingWidth(188.13671875);

        AnchorPane.getChildren().add(text2);
        
        text3 = new Text();
        text3.setStrokeWidth(0.0);
        text3.setStrokeType(OUTSIDE);
        text3.setTextAlignment(CENTER);
        text3.setLayoutX(457.0);
        text3.setLayoutY(132.0);
        text3.setText("Gender");
        text3.setWrappingWidth(188.13671875);

        AnchorPane.getChildren().add(text3);
        
        text4 = new Text();
        text4.setStrokeWidth(0.0);
        text4.setStrokeType(OUTSIDE);
        text4.setTextAlignment(CENTER);
        text4.setLayoutX(669.0);
        text4.setLayoutY(131.0);
        text4.setText("Department");
        text4.setWrappingWidth(188.13671875);

        AnchorPane.getChildren().add(text4);
        
        textField1 = new TextField();
        textField1.setPrefHeight(10.0);
        textField1.setPrefWidth(149.0);
        textField1.setLayoutX(34.0);
        textField1.setLayoutY(140.0);

        AnchorPane.getChildren().add(textField1);
        
        textField2 = new TextField();
        textField2.setLayoutX(257.0);
        textField2.setLayoutY(140.0);

        AnchorPane.getChildren().add(textField2);
        
        textField3 = new TextField();
        textField3.setLayoutX(477.0);
        textField3.setLayoutY(140.0);

        AnchorPane.getChildren().add(textField3);
        TextField textField8 = new TextField();
        textField8.setLayoutX(689.0);
        textField8.setLayoutY(140.0);

        AnchorPane.getChildren().add(textField8);
        text5 = new Text();
        text5.setStrokeWidth(0.0);
        text5.setStrokeType(OUTSIDE);
        text5.setTextAlignment(CENTER);
        text5.setLayoutX(305.0);
        text5.setLayoutY(44.0);
        text5.setText("EmployeesFX");
        text5.setWrappingWidth(304.13671875);

        AnchorPane.getChildren().add(text5);
        tableView = new TableView();
        tableView.setPrefHeight(388.0);
        tableView.setPrefWidth(810.0);
        tableView.setLayoutX(41.0);
        tableView.setLayoutY(184.0);

        AnchorPane.getChildren().add(tableView);
        
        buttonViewEmployee = new Button();
        buttonViewEmployee.setLayoutX(703.0);
        buttonViewEmployee.setId("viewEmployee");
        buttonViewEmployee.setLayoutY(22.0);
        buttonViewEmployee.setText("Ver todos los empleados");
        buttonViewEmployee.setMnemonicParsing(false);

        AnchorPane.getChildren().add(buttonViewEmployee);
    }

}
