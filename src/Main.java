package com.loja;

import com.loja.ui.MenuProdutos;
import com.loja.gerenciador.GerenciadorProdutos;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GerenciadorProdutos gerenciador = new GerenciadorProdutos();
        MenuProdutos menu = new MenuProdutos(scanner, gerenciador);
        menu.exibirMenu();
        scanner.close();
    }
}
