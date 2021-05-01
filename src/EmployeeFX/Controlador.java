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
        vista.getButtonViewEmployee().setOnAction((event) -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String url = "jdbc:mysql://172.16.0.100:3306/employees";
            Properties connectionProperties = new Properties();
            connectionProperties.setProperty("user", "admin");
            connectionProperties.setProperty("password", "1234");
            try (Connection con = DriverManager.getConnection(url, connectionProperties); Statement st = con.createStatement();) {
                System.out.println("Base de dades connectada!");
                try (ResultSet rs = st.executeQuery("SELECT * FROM employees LIMIT 10");) {
                    System.out.println("Dades de la taula employees:");
                    while (rs.next()) {
                        int empNo = rs.getInt("emp_no");
                        LocalDate birthDate = rs.getDate("birth_date").toLocalDate();
                        String firstName = rs.getString("first_name");
                        String lastName = rs.getString("last_name");
                        Gender gender = Gender.valueOf(rs.getString("gender"));
                        LocalDate hireDate = rs.getDate("hire_date").toLocalDate();
                        vista.getTableView().getItems().add(new Model(empNo, birthDate, firstName, lastName, url, hireDate));
//                        System.out.printf("%8d %14s %15s %15s %6s %14s\n", empNo, birthDate.format(formatter),
//                                firstName, lastName, gender, hireDate.format(formatter));
                    }
                }
            } catch (SQLException e) {
                System.err.println("Error SQL: " + e.getMessage());
            }
        });
    }

public static void connection(String[] args) {
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mariadb://172.16.0.100:3306/sakila?user=admin&password=1234");
            System.out.println("Connexió exitosa a la base de dades!");
        } catch (SQLException e) {
            System.err.println("Error d'establiment de connexió: " + e.getMessage());
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    System.err.println("Error de tancament de connexió: " + ex.getMessage());
                }
            }
        }
    }

    public static void selectAll(String[] args) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String url = "jdbc:mysql://172.16.0.100:3306/employees";
        Properties connectionProperties = new Properties();
        connectionProperties.setProperty("user", "admin");
        connectionProperties.setProperty("password", "1234");
        try (Connection con = DriverManager.getConnection(url, connectionProperties); Statement st = con.createStatement();) {
            System.out.println("Base de dades connectada!");
            try (ResultSet rs = st.executeQuery("SELECT * FROM employees ORDER BY last_name, first_name LIMIT 10");) {
                System.out.println("Dades de la taula employees:");
                while (rs.next()) {
                    int empNo = rs.getInt("emp_no");
                    LocalDate birthDate = rs.getDate("birth_date").toLocalDate();
                    String firstName = rs.getString("first_name");
                    String lastName = rs.getString("last_name");
                    Gender gender = Gender.valueOf(rs.getString("gender"));
                    LocalDate hireDate = rs.getDate("hire_date").toLocalDate();
                    System.out.printf("%8d %14s %15s %15s %6s %14s\n", empNo, birthDate.format(formatter),
                            firstName, lastName, gender, hireDate.format(formatter));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error SQL: " + e.getMessage());
        }
    }

}
