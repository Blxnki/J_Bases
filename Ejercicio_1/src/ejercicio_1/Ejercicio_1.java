package ejercicio_1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Ejercicio_1 {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306"; 
        String usuario = "root"; // Usuario
        String password = "root"; // Contrase?a

        try (Connection conexion = DriverManager.getConnection(url, usuario, password);
             Statement stmt = conexion.createStatement()) {

            // Crear la base de datos
            String crearBD = "CREATE DATABASE IF NOT EXISTS seguridad_db";
            stmt.executeUpdate(crearBD);
            System.out.println("Base de datos creada con éxito.");

            // Seleccionar la base de datos
            stmt.executeUpdate("USE seguridad_db");

            // Crear la tabla users
            String crearTabla = "CREATE TABLE IF NOT EXISTS users (" +
                                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                                "username VARCHAR(50) NOT NULL, " +
                                "password VARCHAR(100) NOT NULL)";
            stmt.executeUpdate(crearTabla);
            System.out.println("Tabla 'users' creada con éxito.");

            // Insertar registros en la tabla
            String insertarDatos = "INSERT INTO users (username, password) VALUES " +
                                   "('admin', 'admin123'), " +
                                   "('usuario', 'pass123')";
            stmt.executeUpdate(insertarDatos);
            System.out.println("Datos insertados con éxito.");

        } catch (Exception e) {
            System.err.println("Erro al conectarse a la base de datos");
            e.getMessage();
        }
    }
    
}
