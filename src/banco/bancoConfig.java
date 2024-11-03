package banco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class bancoConfig {

    private static final String URL = "jdbc:mysql://localhost:3306/sistemaproduto";
    private static final String USER = "root";
    private static final String PASSWORD = "joao200606";

    public static void initializeDatabase() {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {

            // Criar o banco de dados, se não existir
            statement.executeUpdate("CREATE DATABASE IF NOT EXISTS seu_banco");

            // Usar o banco de dados
            statement.executeUpdate("USE seu_banco");

            // Criar a tabela, se não existir
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS produtos (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "nome VARCHAR(100) NOT NULL," +
                    "preco DECIMAL(10, 2) NOT NULL," +
                    "quantidadeEstoque INT NOT NULL," +
                    "categoria VARCHAR(100) NOT NULL" +
                    ")");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
