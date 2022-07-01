package br.com.alura.comex.comex.controllers.form;

import javax.validation.constraints.NotNull;

import br.com.alura.comex.comex.builders.ItemPedidoBuilder;
import br.com.alura.comex.comex.models.ItemPedido;
import br.com.alura.comex.comex.models.Produto;
import br.com.alura.comex.comex.repository.ProdutoRepository;

public class AdicionarItemPedidoDto {

    @NotNull
    private Long produtoId;
    @NotNull
    private Integer quantidade;

    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public ItemPedido converter(ProdutoRepository produtoRepository) {
        Produto produto = produtoRepository.findById(produtoId).get();
        return new ItemPedidoBuilder()
                .comProduto(produto)
                .comPrecoUnitario(produto.getPrecoUnitario())
                .comQuantidade(quantidade)
                .comDesconto()
                .build();
    }
}
