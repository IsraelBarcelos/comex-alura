package br.com.alura.comex.comex.models.descontos.descontoPedido;

import java.math.BigDecimal;

import br.com.alura.comex.comex.models.Pedido;
import br.com.alura.comex.comex.repository.PedidoRepository;

public class CalculadoraDeDescontosPedido {
    public BigDecimal calcular(Pedido pedido, PedidoRepository pedidoRepository) {
        AbstractDescontoPedido desconto = new DescontoFidelidade(new SemDesconto(null), pedidoRepository);
        return desconto.calcula(pedido);
    }
}