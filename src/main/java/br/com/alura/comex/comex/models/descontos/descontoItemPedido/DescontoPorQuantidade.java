package br.com.alura.comex.comex.models.descontos.descontoItemPedido;

import java.math.BigDecimal;

import br.com.alura.comex.comex.models.ItemPedido;

public class DescontoPorQuantidade extends AbstractDescontoItemPedido {

    public DescontoPorQuantidade(AbstractDescontoItemPedido successor) {
        super(successor);
    }

    public BigDecimal calcula(ItemPedido itemPedido) {
        itemPedido.setTipoDesconto(TipoDescontoItemPedido.QUANTIDADE);
        return itemPedido.getValorTotal().multiply(new BigDecimal(0.1));
    }

    @Override
    public boolean deveAplicar(ItemPedido itemPedido) {
        return itemPedido.getQuantidade() > 10;
    }
}