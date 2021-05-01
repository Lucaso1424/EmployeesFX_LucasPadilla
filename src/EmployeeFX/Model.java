/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EmployeeFX;

import EmployeeFX.Controller.Gender;
import java.time.LocalDate;
import javafx.scene.control.Button;

/**
 *
 * @author lucas
 */
public class Model {

    // CREAMOS LAS INSTANCIAS PARA LOS VALORES DE LOS CAMPOS DE LA TABLA employees
    private int emp_no;
    private LocalDate birth_date;
    private String first_name;
    private String last_name;
    private Gender gender;
    private LocalDate hire_date;
    private Button button;
    private Button delete_button;

    // REALIZAMOS UN CONSTRUCTOR DEL MODELO PARA PASARLE EN LOS INSERT 
    // Y EL SELECT, PARA PASARLE LOS CAMPOS DE LA TABLA
    public Model(int emp_no, LocalDate birth_date, String first_name, String last_name, Gender gender, LocalDate hire_date, Button delete_button) {
        this.emp_no = emp_no;
        this.birth_date = birth_date;
        this.first_name = first_name;
        this.last_name = last_name;
        this.gender = gender;
        this.hire_date = hire_date;
        this.delete_button = delete_button;
    }

    // REALIZAMOS LOS GETTERS Y SETTERS DE CADA CAMPO DE LA TABLA
    public int getEmp_no() {
        return emp_no;
    }

    public void setEmp_no(int emp_no) {
        this.emp_no = emp_no;
    }

    public LocalDate getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(LocalDate birth_date) {
        this.birth_date = birth_date;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDate getHire_date() {
        return hire_date;
    }

    public void setHire_date(LocalDate hire_date) {
        this.hire_date = hire_date;
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }

    public Button getDelete_button() {
        return delete_button;
    }

    public void setDelete_button(Button delete_button) {
        this.delete_button = delete_button;
    }

}
