package br.com.alura.comex.comex.controllers.form;

import java.math.BigDecimal;
import java.util.Optional;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.Length;

import br.com.alura.comex.comex.controllers.form.validation.ValidaIdCategoria;
import br.com.alura.comex.comex.models.Produto;
import br.com.alura.comex.comex.repository.ProdutoRepository;

public class AtualizacaoProdutoForm {
    @NotNull
    @NotEmpty
    @Length(min = 2)
    private String nome;
    @NotNull
    @Positive
    private BigDecimal precoUnitario;
    private String descricao;
    @NotNull
    private Integer quantidadeEstoque;
    @NotNull
    @ValidaIdCategoria
    private Long categoriaId;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(BigDecimal precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public Integer getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(Integer quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
    }

    public Produto atualizar(Long id, ProdutoRepository produtoRepository) {
        Optional<Produto> produtoOptional = produtoRepository.findById(id);
        if (!produtoOptional.isPresent()) {
            throw new IllegalArgumentException("Produto não encontrado");
        }

        Produto produto = produtoOptional.get();
        produto.setNome(nome);
        produto.setDescricao(descricao);
        produto.setPrecoUnitario(precoUnitario);
        produto.getCategoria().setId(categoriaId);
        produto.adicionarProdutoAoEstoque(quantidadeEstoque);

        return produto;
    }
}
