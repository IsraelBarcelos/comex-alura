package br.com.alura.comex.comex.models.descontos.descontoItemPedido;

import java.math.BigDecimal;

import br.com.alura.comex.comex.models.ItemPedido;

public class DescontoPorPromocao extends AbstractDescontoItemPedido {

    private BigDecimal valorDesconto;

    public DescontoPorPromocao(AbstractDescontoItemPedido successor, BigDecimal valorDesconto) {
        super(successor);
        this.valorDesconto = valorDesconto;
    }

    public BigDecimal calcula(ItemPedido itemPedido) {
        itemPedido.setTipoDesconto(TipoDescontoItemPedido.PROMOCAO);
        return valorDesconto;
    }

    @Override
    public boolean deveAplicar(ItemPedido itemPedido) {
        return valorDesconto.compareTo(new BigDecimal(0)) > 0;
    }
}
