package br.com.alura.comex.comex.controllers.form;

import br.com.alura.comex.comex.builders.PedidoBuilder;
import br.com.alura.comex.comex.controllers.form.validation.ValidaIdCliente;
import br.com.alura.comex.comex.models.Pedido;
import br.com.alura.comex.comex.repository.ClienteRepository;
import br.com.alura.comex.comex.repository.PedidoRepository;
import br.com.alura.comex.comex.repository.ProdutoRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

public class PedidoForm {

    @NotNull
    @ValidaIdCliente
    private Long clienteId;
    @NotNull
    private List<AdicionarItemPedidoDto> itens;

    public Long getClienteId() {
        return this.clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public List<AdicionarItemPedidoDto> getItens() {
        return this.itens;
    }

    public void setItens(List<AdicionarItemPedidoDto> itens) {
        this.itens = itens;
    }

    public Pedido converter(ClienteRepository clienteRepository, ProdutoRepository produtoRepository,
            PedidoRepository pedidoRepository) {
        Pedido pedido = new PedidoBuilder()
                .comCliente(clienteRepository.findById(clienteId).get())
                .comItens(itens.stream().map(item -> item.converter(produtoRepository)).collect(Collectors.toList()))
                .comData(LocalDate.now())
                .comDesconto(pedidoRepository)
                .build();

        return pedido;
    }

}
