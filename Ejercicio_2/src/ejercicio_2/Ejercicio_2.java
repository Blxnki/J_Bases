package ejercicio_2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Ejercicio_2 {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/seguridad_db"; 
        String usuario = "root"; // Usuario
        String password = "root"; // contraseña

        Scanner t = new Scanner(System.in);

        // Solicitar usuario y contraseña desde la consola
        System.out.print("Introduce tu nombre de usuario: ");
        String usuarioIngresado = t.nextLine();

        System.out.print("Introduce tu contraseña: ");
        String passwordIngresada = t.nextLine();

        // Consulta SQL para verificar el usuario y la contraseña
        String consultaSQL = "SELECT * FROM users WHERE username = '"+usuarioIngresado+"' AND password = '"+passwordIngresada+"'";

        try (Connection conexion = DriverManager.getConnection(url, usuario, password);
             PreparedStatement stmt = conexion.prepareStatement(consultaSQL)) {
            // Mostrar la consulta SQL
            System.out.println("Consulta ejecutada: " + stmt);

            // Ejecutar la consulta
            ResultSet rs = stmt.executeQuery();

            // Verificar si se encontró el usuario
            if (rs.next()) {
                System.out.println("Inicio de sesión correcto.");
            } else {
                System.out.println("Usuario o contraseña incorrectos.");
            }

        } catch (Exception e) {
            System.err.println("Error al conectarse a la base de datos");
            e.getMessage();
        }
    }
}
