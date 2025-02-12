package course.msavaliadorcredito.model;

import java.util.List;

public class SituacaoCliente {

    private DadosCliente cliente;
    private List<CartaoCliente> cartoes;

    public SituacaoCliente() {
    }

    public SituacaoCliente(DadosCliente cliente, List<CartaoCliente> cartoes) {
        this.cliente = cliente;
        this.cartoes = cartoes;
    }

    public DadosCliente getCliente() {
        return cliente;
    }

    public void setCliente(DadosCliente cliente) {
        this.cliente = cliente;
    }

    public List<CartaoCliente> getCartoes() {
        return cartoes;
    }

    public void setCartoes(List<CartaoCliente> cartoes) {
        this.cartoes = cartoes;
    }
}
