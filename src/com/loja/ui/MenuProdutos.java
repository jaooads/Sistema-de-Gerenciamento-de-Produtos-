package com.loja.ui;

import com.loja.gerenciador.GerenciadorProdutos;
import com.loja.modelo.Produto;

import java.util.List;
import java.util.Scanner;

public class MenuProdutos {

    private Scanner scanner;
    private GerenciadorProdutos gerenciador;


    public MenuProdutos(Scanner scanner, GerenciadorProdutos gerenciador) {
        this.scanner = scanner;
        this.gerenciador = gerenciador;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public GerenciadorProdutos getGerenciador() {
        return gerenciador;
    }

    public void setGerenciador(GerenciadorProdutos gerenciador) {
        this.gerenciador = gerenciador;
    }

    public void exibirMenu() {
        int opcao;

        do {
            System.out.println("=== Menu de Gerenciamento de Produtos ===");
            System.out.println("1. Adicionar Produto");
            System.out.println("2. Listar Produtos");
            System.out.println("3. Buscar Produto por ID");
            System.out.println("4. Atualizar Produto");
            System.out.println("5. Deletar Produto");
            System.out.println("6. Buscar Produtos por Nome");
            System.out.println("7. Buscar Produtos por Categoria");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarProduto();
                    break;
                case 2:
                    listarProdutos();
                    break;
                case 3:
                    buscarProduto();
                    break;
                case 4:
                    atualizarProduto();
                    break;
                case 5:
                    deletarProduto();
                    break;
                case 6:
                    buscarPorNome();
                    break;
                case 7:
                    buscarPorCategoria();
                    break;
                case 0:
                    System.out.print("Saindo do sistema...");
                    break;
                default:
                    System.out.print("Opção inválida!");
            }

        } while (opcao != 0);
    }

    private void cadastrarProduto() {

        System.out.print("Digite o nome do produto: ");
        String nome = scanner.nextLine();

        System.out.print("Digite o preço do produto: ");
        double preco = Double.parseDouble(scanner.nextLine());

        System.out.print("Digite a quantidade em estoque: ");
        int quantidadeEstoque = Integer.parseInt(scanner.nextLine());

        System.out.print("Digite a categoria do produto: ");
        String categoria = scanner.nextLine();

        Produto produto = new Produto(nome, preco, quantidadeEstoque, categoria);

        gerenciador.criar(produto);
        System.out.println("Produto cadastrado com sucesso!");

    }

    private void buscarProduto() {

        System.out.print("Digite o ID do produto que deseja buscar: ");
        int id = Integer.parseInt(scanner.nextLine());

        Produto produto = gerenciador.buscarPorId(id);

        if (produto != null) {
            System.out.println("Dados do produto:");
            System.out.println("ID: " + produto.getId());
            System.out.println("Nome: " + produto.getNome());
            System.out.println("Preço: " + produto.getPreco());
            System.out.println("Quantidade em estoque: " + produto.getQuantidadeEstoque());
            System.out.println("Categoria: " + produto.getCategoria());
        } else {
            System.out.println("Produto inexistente");
        }
    }

    private void listarProdutos() {

        if (gerenciador.listarTodos().isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }

        System.out.println("Lista de ´produtos:");
        for (Produto produto : gerenciador.listarTodos()) {
            System.out.println("ID: " + produto.getId());
            System.out.println("Nome: " + produto.getNome());
            System.out.println("Preço: " + produto.getPreco());
            System.out.println("Quantidade em estoque: " + produto.getQuantidadeEstoque());
            System.out.println("Categoria: " + produto.getCategoria());
        }
    }

    private void atualizarProduto() {

        System.out.print("Digite o ID do produto que deseja atualizar: ");
        int id = Integer.parseInt(scanner.nextLine());

        Produto produtoExiste = gerenciador.buscarPorId(id);

        if (produtoExiste == null) {
            System.out.println("Produto inexistente");
            return;
        }

        System.out.print("Digite o novo nome do produto: ");
        String novoNome = scanner.nextLine();

        System.out.print("Digite o novo preço do produto: ");
        String precoEntrada = scanner.nextLine();

        System.out.print("Digite a nova quantidade em estoque: ");
        String quantidadeEntrada = scanner.nextLine();

        if (!novoNome.isEmpty()) {
            produtoExiste.setNome(novoNome);
        }

        if (!precoEntrada.isEmpty()) {
            double novoPreco;
            try {
                novoPreco = Double.parseDouble(precoEntrada);
                produtoExiste.setPreco(novoPreco);
            } catch (NumberFormatException e) {
                System.out.println("Preço inválido, não foi alterado.");
            }
        }

        if (!quantidadeEntrada.isEmpty()) {
            int novaQuanditdade;
            try {
                novaQuanditdade = Integer.parseInt(quantidadeEntrada);
                produtoExiste.setQuantidadeEstoque(novaQuanditdade);
            } catch (NumberFormatException e) {
                System.out.println("Quantidade inválido, não foi alterado.");
            }
        }

        boolean atualizado = gerenciador.atualizar(produtoExiste);
        if (atualizado) {
            System.out.println("Produto atualizado com sucesso!");
        } else {
            System.out.println("Erro ao atualizar o produto.");
        }
    }

    private void deletarProduto() {

        System.out.print("Digite o ID do produto que deseja deletar: ");
        int id = Integer.parseInt(scanner.nextLine());

        Produto produtoExiste = gerenciador.buscarPorId(id);

        if (produtoExiste == null) {
            System.out.println("Produto inexistente");
            return;
        }

        System.out.print("Tem certeza que deseja excluir o produto (s/n)? ");
        String confirmacao = scanner.nextLine();

        if (confirmacao.equalsIgnoreCase("s")) {
            boolean removido = gerenciador.deletar(id);
            if (removido) {
                System.out.println("Produto excluído com sucesso!");
            } else {
                System.out.println("Erro ao excluir o produto.");
            }
        } else {
            System.out.println("Voc~e escolheu sair");
        }
    }

    private void buscarPorNome() {

        System.out.print("Digite o nome do produto que deseja buscar: ");
        String nome = scanner.nextLine();

        List<Produto> produtosEncontrados = gerenciador.buscarPorNome(nome);

        if (produtosEncontrados.isEmpty()) {
            System.out.println("Nenhum produto encontrado");
        } else {
            System.out.println("Produtos encontrados:");
            for (Produto produto : produtosEncontrados) {
                System.out.println(produto);
            }
        }
    }

    private void buscarPorCategoria() {

        System.out.print("Digite o nome da categoria que deseja buscar: ");
        String nome = scanner.nextLine();

        List<Produto> produtosEncontrados = gerenciador.buscarPorCategoria(nome);

        if (produtosEncontrados.isEmpty()) {
            System.out.println("Nenhum produto encontrado");
        } else {
            System.out.println("Produtos encontrados:");
            for (Produto produto : produtosEncontrados) {
                System.out.println(produto);
            }
        }
    }

    private String lerEntradaString(String mensagem) {
        System.out.print(mensagem);
        return scanner.nextLine();
    }

    private double lerEntradaDouble(String mensagem) {
        double valor = 0.0;
        boolean entradaValida = false;

        while (!entradaValida) {
            System.out.print(mensagem);
            String entrada = scanner.nextLine();

            try {
                valor = Double.parseDouble(entrada);
                entradaValida = true;
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida, digite um numero válido");
            }
        }
        return valor;
    }

    private int lerEntradaInteira(String mensagem) {
        int valor = 0;
        boolean entradaValida = false;

        while (!entradaValida) {
            System.out.print(mensagem);
            String entrada = scanner.nextLine();

            try {
                valor = Integer.parseInt(entrada);
                entradaValida = true;
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida, digite um numero válido");
            }
        }
        return valor;
    }

}
