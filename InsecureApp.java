import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class InsecureApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese usuario: ");
        String user = scanner.nextLine();
        
        System.out.print("Ingrese contraseña: ");
        String password = scanner.nextLine();

        try {
            // Conexión insegura con credenciales hardcodeadas
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb", "root", "password123");
            Statement stmt = conn.createStatement();

            // Inyección SQL: consulta construida con entrada del usuario
            String query = "SELECT * FROM users WHERE username = '" + user + "' AND password = '" + password + "'";
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                System.out.println("¡Acceso concedido!");
            } else {
                System.out.println("Acceso denegado.");
            }
            
            conn.close();
        } catch (Exception e) {
            // Manejo de errores deficiente
            System.out.println("Ocurrió un error");
        }
    }
