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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;

public class Controller {

    private View vista;
    private Model model;

    public Controller(View v) {
        vista = v;
    }

    public enum Gender {
        M, F;
    }

    public void initController() {
        loadTable();
        filterBySurname();
        filterSelectAll();
        selectEntryTable();
    }

    public void loadTable() {
        // GENERAMOS LA CONEXIÓN A LA BASE DE DATOS
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
            try (ResultSet rs = st.executeQuery("SELECT * FROM employees LIMIT 10");) {
                System.out.println("Dades de la taula ");
                // EN UN BUCLE, REALIZAMOS UNA COMPROBACIÓN DEL RESULTADO DE LA QUERY (rs)
                while (rs.next()) {
                    int empNo = rs.getInt("emp_no");
                    LocalDate birthDate = rs.getDate("birth_date").toLocalDate();
                    String firstName = rs.getString("first_name");
                    String lastName = rs.getString("last_name");
                    Gender gender = Gender.valueOf(rs.getString("gender"));
                    LocalDate hireDate = rs.getDate("hire_date").toLocalDate();
                    Button delete_button = new Button("Delete entry");
                    // CARGAMOS LA VISTA, PARA HACER EL getTableView, Y CON UN getItems() HACEMOS EL .add(), Y PASAMOS TODAS LAS VARIABLES
                    vista.getTableView().getItems().add(new Model(empNo, birthDate, firstName, lastName, gender, hireDate, delete_button));
                    // CREAMOS EL EVENTO DEL BOTON PARA BORRAR LA ENTRADA
                    delete_button.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            // REALIZAMOS LA CONEXIÓN A LA BASE DE DATOS 
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                            String url = "jdbc:mysql://172.16.0.100:3306/employees";
                            Properties connectionProperties = new Properties();
                            // DEFINIMOS LAS PROPERTIES DE LA BBDD
                            connectionProperties.setProperty("user", "admin");
                            connectionProperties.setProperty("password", "1234");
                            try (Connection con = DriverManager.getConnection(url, connectionProperties); Statement st = con.createStatement();) {
                                // REALIZAMOS UNA QUERY PARA BORRAR LA ID (QUE ES LA CLAVE PRIMARIA)
                                // PARA BORRAR TODA LA ENTRADA
                                try (ResultSet rs = st.executeQuery("DELETE FROM employees WHERE emp_no = " + empNo);) {
                                    System.out.println("Se ha borrado");
                                }
                            } catch (SQLException e) {
                                System.err.println("Error SQL: " + e.getMessage());
                            }
                            // HACEMOS UN CLEAR DEL LA TABLA, PARA BORRAR LA TABLA
                            vista.getTableView().getItems().clear();
                            // CARGAMOS LA TABLA DE NUEVO
                            loadTable();
                        }
                    });

                    // REALIZAMOS UN EVENTO DE BOTON PARA LA CREACIÓN DE UN NUEVO EMPLEADO
                    vista.getCreateEmployee().setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            System.out.println("intento de inserción");
                            // REALIZAMOS LA CONEXIÓN A LA BASE DE DATOS 
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                            String url = "jdbc:mysql://172.16.0.100:3306/employees";
                            Properties connectionProperties = new Properties();
                            // DEFINIMOS LAS PROPERTIES DE LA BBDD
                            connectionProperties.setProperty("user", "admin");
                            connectionProperties.setProperty("password", "1234");
                            try (Connection con = DriverManager.getConnection(url, connectionProperties); Statement st = con.createStatement();) {
                                // REALIZAMOS UNA QUERY PARA BORRAR LA ID (QUE ES LA CLAVE PRIMARIA)
                                // PARA BORRAR TODA LA ENTRADA
                                try (ResultSet rs = st.executeQuery("INSERT INTO employees(emp_no, first_name, last_name, birth_date, gender, hire_date) VALUES ('" + vista.getTextField0().getText() + "','" + vista.getTextField1().getText() + "','" + vista.getTextField2().getText() + "','" + vista.getTextField3().getText() + "','" + vista.getTextField4().getText() + "','" + vista.getTextField5().getText() + "');");) {
                                }
                            } catch (SQLException e) {
                                System.err.println("Error SQL: " + e.getMessage());
                            }
                            // HACEMOS UN CLEAR DEL LA TABLA, PARA BORRAR LA TABLA
                            vista.getTableView().getItems().clear();
                            // CARGAMOS LA TABLA DE NUEVO
                            loadTable();
                        }
                    });
                }
            }
        } catch (SQLException e) {
            System.err.println("Error SQL: " + e.getMessage());
        }
    }

    // CREAMOS UNA FUNCIÓN PARA FILTRAR POR LOS APELLIDOS
    public void filterBySurname() {
        vista.getFilterBySurname().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                vista.getTableView().getItems().clear();
                // GENERAMOS LA CONEXIÓN A LA BASE DE DATOS
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                String url = "jdbc:mysql://172.16.0.100:3306/employees";
                Properties connectionProperties = new Properties();
                // DEFINIMOS LAS PROPERTIES DE LA BBDD
                connectionProperties.setProperty("user", "admin");
                connectionProperties.setProperty("password", "1234");
                // NOS CONECTAMOS A LA BBDD A PARTIR DE UN TRY CATCH
                try (Connection con = DriverManager.getConnection(url, connectionProperties); Statement st = con.createStatement();) {
                    // REALIZAMOS UNA CONSULTA DE LA TABLA employees PARA QUE SÓLO FILTRE POR EL APELLIDO 'CAINE'
                    try (ResultSet rs = st.executeQuery("SELECT * FROM employees WHERE last_name = '" + vista.getTextField2().getText() + " ' LIMIT 10");) {
                        // EN UN BUCLE, REALIZAMOS UNA COMPROBACIÓN DEL RESULTADO DE LA QUERY (rs)
                        while (rs.next()) {
                            int empNo = rs.getInt("emp_no");
                            LocalDate birthDate = rs.getDate("birth_date").toLocalDate();
                            String firstName = rs.getString("first_name");
                            String lastName = rs.getString("last_name");
                            Gender gender = Gender.valueOf(rs.getString("gender"));
                            LocalDate hireDate = rs.getDate("hire_date").toLocalDate();
                            Button delete_button = new Button("Delete entry");
                            // CARGAMOS LA VISTA, PARA HACER EL getTableView, Y CON UN getItems() HACEMOS EL .add(), Y PASAMOS TODAS LAS VARIABLES
                            vista.getTableView().getItems().add(new Model(empNo, birthDate, firstName, lastName, gender, hireDate, delete_button));
                            delete_button.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    // REALIZAMOS LA CONEXIÓN A LA BASE DE DATOS 
                                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                                    String url = "jdbc:mysql://172.16.0.100:3306/employees";
                                    Properties connectionProperties = new Properties();
                                    // DEFINIMOS LAS PROPERTIES DE LA BBDD
                                    connectionProperties.setProperty("user", "admin");
                                    connectionProperties.setProperty("password", "1234");
                                    try (Connection con = DriverManager.getConnection(url, connectionProperties); Statement st = con.createStatement();) {
                                        // REALIZAMOS UNA QUERY PARA BORRAR LA ID (QUE ES LA CLAVE PRIMARIA)
                                        // PARA BORRAR TODA LA ENTRADA
                                        try (ResultSet rs = st.executeQuery("DELETE FROM employees WHERE emp_no = " + empNo);) {
                                            System.out.println("Se ha borrado");
                                        }
                                    } catch (SQLException e) {
                                        System.err.println("Error SQL: " + e.getMessage());
                                    }
                                    // HACEMOS UN CLEAR DEL LA TABLA, PARA BORRAR LA TABLA
                                    // CARGAMOS LA TABLA DE NUEVO
                                    filterBySurname();
                                }
                            });
                        }
                    }
                } catch (SQLException e) {
                    System.err.println("Error SQL: " + e.getMessage());
                }
            }
        });
    }

    // CREAMOS UNA FUNCIÓN PARA FILTRAR TODOS LOS EMPLOYEES
    public void filterSelectAll() {
        vista.getFilterAll().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                vista.getTableView().getItems().clear();
                // GENERAMOS LA CONEXIÓN A LA BASE DE DATOS
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                String url = "jdbc:mysql://172.16.0.100:3306/employees";
                Properties connectionProperties = new Properties();
                // DEFINIMOS LAS PROPERTIES DE LA BBDD
                connectionProperties.setProperty("user", "admin");
                connectionProperties.setProperty("password", "1234");
                // NOS CONECTAMOS A LA BBDD A PARTIR DE UN TRY CATCH
                try (Connection con = DriverManager.getConnection(url, connectionProperties); Statement st = con.createStatement();) {
                    // REALIZAMOS UNA CONSULTA DE LA TABLA employees PARA QUE SÓLO FILTRE POR EL APELLIDO 'CAINE'
                    try (ResultSet rs = st.executeQuery("SELECT * FROM employees LIMIT 10");) {
                        // EN UN BUCLE, REALIZAMOS UNA COMPROBACIÓN DEL RESULTADO DE LA QUERY (rs)
                        while (rs.next()) {
                            int empNo = rs.getInt("emp_no");
                            LocalDate birthDate = rs.getDate("birth_date").toLocalDate();
                            String firstName = rs.getString("first_name");
                            String lastName = rs.getString("last_name");
                            Gender gender = Gender.valueOf(rs.getString("gender"));
                            LocalDate hireDate = rs.getDate("hire_date").toLocalDate();
                            Button delete_button = new Button("Delete entry");
                            // CARGAMOS LA VISTA, PARA HACER EL getTableView, Y CON UN getItems() HACEMOS EL .add(), Y PASAMOS TODAS LAS VARIABLES
                            vista.getTableView().getItems().add(new Model(empNo, birthDate, firstName, lastName, gender, hireDate, delete_button));
                            delete_button.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    // REALIZAMOS LA CONEXIÓN A LA BASE DE DATOS 
                                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                                    String url = "jdbc:mysql://172.16.0.100:3306/employees";
                                    Properties connectionProperties = new Properties();
                                    // DEFINIMOS LAS PROPERTIES DE LA BBDD
                                    connectionProperties.setProperty("user", "admin");
                                    connectionProperties.setProperty("password", "1234");
                                    try (Connection con = DriverManager.getConnection(url, connectionProperties); Statement st = con.createStatement();) {
                                        // REALIZAMOS UNA QUERY PARA BORRAR LA ID (QUE ES LA CLAVE PRIMARIA)
                                        // PARA BORRAR TODA LA ENTRADA
                                        try (ResultSet rs = st.executeQuery("DELETE FROM employees WHERE emp_no = " + empNo);) {
                                            System.out.println("Se ha borrado");
                                        }
                                    } catch (SQLException e) {
                                        System.err.println("Error SQL: " + e.getMessage());
                                    }
                                    // HACEMOS UN CLEAR DEL LA TABLA, PARA BORRAR LA TABLA
                                    vista.getTableView().getItems().clear();
                                    // CARGAMOS LA TABLA DE NUEVO
                                    loadTable();
                                }
                            });
                        }
                    }
                } catch (SQLException e) {
                    System.err.println("Error SQL: " + e.getMessage());
                }
            }
        });
    }

    // CREAMOS UNA FUNCIÓN PARA SELECCIONAR LOS VALORES DE UNA ENTRADA
    public void selectEntryTable() {
        vista.getTableView().setOnMouseClicked((event) -> {
            TableView table = vista.getTableView();
            // CREAMOS LA SELECCIÓN MULTIPLE DE LA CLASE TableViewSelectionModel
            TableViewSelectionModel selectItem = table.getSelectionModel();
            // SELECCIONAMOS SOLO 1 ITEM DE LA TABLA
            selectItem.setSelectionMode(SelectionMode.SINGLE);
            // CREAMOS UN NUEVO OBJETO MODEL ITEM PARA SELECCIONAR UN ITEM 
            // Y GUARDARLO EN EL modelItem con el getSelectedItem()
            Model modelItem = (Model) selectItem.getSelectedItem();
            // DESPUES, HACEMOS LOS GETTERS DE LOS CAMPOS DE TextField, Y SETEAMOS 
            // EL TEXTO DE EL modelItem, PASANDOLE UN toString O UN PARSE INT SI ES NECESARIO
            vista.getTextField0().setText(Integer.toString(modelItem.getEmp_no()));
            vista.getTextField1().setText((modelItem.getFirst_name()));
            vista.getTextField2().setText((modelItem.getLast_name()));
            vista.getTextField3().setText((modelItem.getBirth_date()).toString());
            vista.getTextField4().setText((modelItem.getGender().toString()));
            vista.getTextField5().setText((modelItem.getHire_date().toString()));
        });
    }
}
