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
        String password = "root"; // contrase?a

        Scanner t = new Scanner(System.in);

        // Solicitar usuario y contrase?a desde la consola
        System.out.print("Introduce tu nombre de usuario: ");
        String usuarioIngresado = t.nextLine();

        System.out.print("Introduce tu contrase?a: ");
        String passwordIngresada = t.nextLine();

        // Consulta SQL para verificar el usuario y la contrase?a
        String consultaSQL = "SELECT * FROM users WHERE username = ? AND password = ?";

        try (Connection conexion = DriverManager.getConnection(url, usuario, password);
             PreparedStatement stmt = conexion.prepareStatement(consultaSQL)) {

            // Establecer los valores
            stmt.setString(1, usuarioIngresado);
            stmt.setString(2, passwordIngresada);

            // Mostrar la consulta SQL
            System.out.println("Consulta ejecutada: " + stmt);

            // Ejecutar la consulta
            ResultSet rs = stmt.executeQuery();

            // Verificar si se encontró el usuario
            if (rs.next()) {
                System.out.println("Inicio de sesión correcto.");
            } else {
                System.out.println("Usuario o contrase?a incorrectos.");
            }

        } catch (Exception e) {
            System.err.println("Erro al conectarse a la base de datos");
            e.getMessage();
        }
    }
}
