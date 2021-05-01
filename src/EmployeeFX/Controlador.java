/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EmployeeFX;

/**
 *
 * @author lucas
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

public class Controlador {

    private Vista vista;
    private Model model;

    public Controlador(Vista v) {
        vista = v;
        initView();
    }

    public void initView() {
        
    }

    public enum Gender {
        M, F;
    }

    public void initController() {
        // CARGAMOS LA VISTA, E INSERTAMOS UN NUEVO EVENTO setOnAction 
        // PARA GENERAR UNA CONSULTA DE LA TABLA, E INSERTARLA EN LA TABLA
        vista.getButtonViewEmployee().setOnAction((event) -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String url = "jdbc:mysql://172.16.0.100:3306/employees";
            Properties connectionProperties = new Properties();
            // DEFINIMOS LAS PROPERTIES DE LA BBDD
            connectionProperties.setProperty("user", "admin");
            connectionProperties.setProperty("password", "1234");
            // NOS CONECTAMOS A LA BBDD A PARTIR DE UN TRY CATCH
            try (Connection con = DriverManager.getConnection(url, connectionProperties); Statement st = con.createStatement();) {
                System.out.println("Base de dades connectada!");
                // REALIZAMOS UNA CONSULTA DE LA TABLA employees
                try (ResultSet rs = st.executeQuery("SELECT * FROM employees");) {
                    System.out.println("Dades de la taula ");
                    // EN UN BUCLE, REALIZAMOS UNA COMPROBACIÓN DEL RESULTADO DE LA QUERY (rs)
                    while (rs.next()) {
                        int empNo = rs.getInt("emp_no");
                        LocalDate birthDate = rs.getDate("birth_date").toLocalDate();
                        String firstName = rs.getString("first_name");
                        String lastName = rs.getString("last_name");
                        Gender gender = Gender.valueOf(rs.getString("gender"));
                        LocalDate hireDate = rs.getDate("hire_date").toLocalDate();
                        // CARGAMOS LA VISTA, PARA HACER EL getTableView, Y CON UN getItems() HACEMOS EL .add(), Y PASAMOS TODAS LAS VARIABLES
                        vista.getTableView().getItems().add(new Model(empNo, birthDate, firstName, lastName, gender, hireDate));
                    }
                }
            } catch (SQLException e) {
                System.err.println("Error SQL: " + e.getMessage());
            }
        });
    }
}
