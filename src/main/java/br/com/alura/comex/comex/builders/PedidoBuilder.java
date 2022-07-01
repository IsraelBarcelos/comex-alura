package br.com.alura.comex.comex.builders;

import br.com.alura.comex.comex.models.Cliente;
import br.com.alura.comex.comex.models.ItemPedido;
import br.com.alura.comex.comex.models.Pedido;
import br.com.alura.comex.comex.models.descontos.descontoPedido.TipoDescontoPedido;
import br.com.alura.comex.comex.repository.PedidoRepository;

import java.time.LocalDate;
import java.util.List;

public class PedidoBuilder {

  private Pedido pedido;

  public PedidoBuilder() {
    this.pedido = new Pedido();
  }

  public PedidoBuilder comData(LocalDate data) {
    this.pedido.setData(data);
    return this;
  }

  public PedidoBuilder comCliente(Cliente cliente) {
    this.pedido.setCliente(cliente);
    return this;
  }

  public PedidoBuilder comDesconto(PedidoRepository pedidoRepository) {
    this.pedido.setDesconto(pedidoRepository);
    return this;
  }

  public PedidoBuilder comTipoDescontoPedido(
      TipoDescontoPedido tipoDescontoPedido) {
    this.pedido.setTipoDesconto(tipoDescontoPedido);
    return this;
  }

  public PedidoBuilder comItens(List<ItemPedido> itens) {
    itens.forEach(item -> {
      this.pedido.adicionarItemPedido(item);
    });
    return this;
  }

  public Pedido build() {
    return this.pedido;
  }
}
