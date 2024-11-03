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

    /**
     * Busca um produto pelo ID.
     *
     * @param id o ID do produto a ser buscado
     * @return o produto correspondente ao ID ou null se não encontrado
     */

    public Produto buscarPorId(int id) {

        for (Produto prod : produtos) {
            if (prod.getId().equals(id)) {
                return prod;
            }
        }
        return null;
    }

    /**
     * Lista todos os produtos.
     *
     * @return uma lista com todos os produtos
     */

    public List<Produto> listarTodos() {
        return new ArrayList<>(produtos);
    }

    /**
     * Atualiza os dados de um produto existente.
     *
     * @param produto o produto com os novos dados
     * @return true se o produto foi atualizado com sucesso, false caso contrário
     */

    public boolean atualizar(Produto produto) {
        for (Produto p : produtos) {
            if (p.getId().equals(produto.getId())) {
                p.setNome(produto.getNome());
                p.setPreco(produto.getPreco());
                return true;
            }
        }
        return false;
    }

    /**
     * Remove um produto da lista pelo ID.
     *
     * @param id o ID do produto a ser removido
     * @return true se o produto foi removido com sucesso, false caso contrário
     */

    public boolean deletar(int id) {
        for (Produto p : produtos) {
            if (p.getId() == id) {
                produtos.remove(p);
                return true;
            }
        }
        return false;
    }

    /**
     * Busca produtos que contêm o nome especificado.
     * A busca é feita de forma case insensitive.
     *
     * @param nome o nome a ser buscado
     * @return uma lista de produtos que contêm o nome especificado
     */

    public List<Produto> buscarPorNome(String nome) {
        List<Produto> produtosEncontrados = new ArrayList<>();
        String nomeEmMinusculas = nome.toLowerCase();

        for (Produto p : produtos) {
            if (p.getNome().toLowerCase().contains(nomeEmMinusculas)) {
                produtosEncontrados.add(p);
            }
        }
        return produtosEncontrados;
    }

    /**
     * Busca produtos que pertencem à categoria especificada.
     * A busca é feita de forma case insensitive.
     *
     * @param categoria o nome da categoria a ser buscada
     * @return uma lista de produtos que pertencem à categoria especificada
     */

    public List<Produto> buscarPorCategoria(String categoria) {
        List<Produto> produtosEncontrados = new ArrayList<>();
        String categoriaEmMinusculas = categoria.toLowerCase();

        for (Produto p : produtos) {
            if (p.getCategoria().toLowerCase().equals(categoriaEmMinusculas)) { // Equals pq ele traz a categoria exata
                produtosEncontrados.add(p);
            }
        }
        return produtosEncontrados;
    }

    /**
     * Valida os dados de um produto antes de operações de criação ou atualização.
     *
     * @param produto o produto a ser validado
     * @throws IllegalArgumentException se os dados do produto não forem válidos
     */

    private void validarProduto(Produto produto) {

        if (produto.getNome() == null || produto.getNome().isEmpty()) {
            throw new IllegalArgumentException("O nome do produto não pode estar vazio");

        }
        if (produto.getPreco() <= 0) {
            throw new IllegalArgumentException("O pre~ço do produto não pode ser negativo");
        }

        if (produto.getQuantidadeEstoque() < 0) {
            throw new IllegalArgumentException("A quantidade em estoque não pode ser negativa");
        }

    }
}
