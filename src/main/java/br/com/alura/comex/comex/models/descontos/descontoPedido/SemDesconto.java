package br.com.alura.comex.comex.models.descontos.descontoPedido;

import java.math.BigDecimal;

import br.com.alura.comex.comex.models.Pedido;

public class SemDesconto extends AbstractDescontoPedido {

    public SemDesconto(AbstractDescontoPedido successor) {
        super(null);
    }

    @Override
    public BigDecimal calcula(Pedido pedido) {
        pedido.setTipoDesconto(TipoDescontoPedido.NENHUM);
        return BigDecimal.ZERO;
    }

    @Override
    public boolean deveAplicar(Pedido pedido) {
        return true;
    }

}
