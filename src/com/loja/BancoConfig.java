package com.loja;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BancoConfig {

    private static final String URL = "jdbc:mysql://localhost:3306/";
    private static final String USER = "root"; // seu usuário do MySQL
    private static final String PASSWORD = "sua_senha"; // sua senha do MySQL
    private static final String DATABASE_NAME = "sistemaproduto";

    public static void initializeDatabase() {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {

            // Cria o banco de dados se não existir
            statement.executeUpdate("CREATE DATABASE IF NOT EXISTS " + DATABASE_NAME);
            System.out.println("Banco de dados criado ou já existente.");

            // Seleciona o banco de dados
            statement.executeUpdate("USE " + DATABASE_NAME);

            // Criação da tabela de produtos se não existir
            String createTableSQL = "CREATE TABLE IF NOT EXISTS produtos (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "nome VARCHAR(255) NOT NULL," +
                    "categoria VARCHAR(255) NOT NULL," +
                    "preco DECIMAL(10, 2) NOT NULL" +
                    ")";
            statement.executeUpdate(createTableSQL);
            System.out.println("Tabela de produtos criada ou já existente.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
