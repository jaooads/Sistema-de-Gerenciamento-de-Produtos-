package com.loja.gerenciador;

import com.loja.modelo.Produto;

import java.util.ArrayList;
import java.util.List;

public class GerenciadorProdutos {

    private List<Produto> produtos;

    private int proximoId;

    /**
     * Constrói um novo GerenciadorProdutos.
     * Inicializa a lista de produtos e define o próximo ID a ser atribuído.
     */

    public GerenciadorProdutos() {
        this.produtos = new ArrayList<>();
        this.proximoId = 1;
    }

    /**
     * Cria um novo produto e adiciona na lista de produtos
     *
     * @param produto o produto a ser criado, onde ele precisa ser validados antes de ser criado
     */

    public void criar(Produto produto) {
        if (validarDados(produto)) {
            produto.setId(proximoId++);
            produtos.add(produto);
        }
    }

    /**
     * Valida os dados de um produto.
     * <p>
     * Verifica se o nome do produto não é nulo ou vazio, e o preço é maior que zero
     * e se a quantidade em estoque é maior ou igual a zero.
     *
     * @param produto o produto a ser validado
     * @return true se o produto for validado, false  caso contrario.
     */

    private boolean validarDados(Produto produto) {
        return produto.getNome() != null && !produto.getNome().isEmpty() &&
                produto.getPreco() > 0 && produto.getQuantidadeEstoque() >= 0;
    }
}
