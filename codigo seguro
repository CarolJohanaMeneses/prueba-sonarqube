import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class SecureApp {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Ingrese usuario: ");
            String user = scanner.nextLine();
            
            System.out.print("Ingrese contraseña: ");
            String password = scanner.nextLine();
            
            // Nunca hardcodear credenciales (deben estar en variables de entorno o archivos seguros)
            String url = "jdbc:mysql://localhost:3306/testdb";
            String dbUser = System.getenv("DB_USER");
            String dbPassword = System.getenv("DB_PASSWORD");

            try (Connection conn = DriverManager.getConnection(url, dbUser, dbPassword);
                 PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?")) {
                
                pstmt.setString(1, user);
                pstmt.setString(2, password);

                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        System.out.println("¡Acceso concedido!");
                    } else {
                        System.out.println("Acceso denegado.");
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
