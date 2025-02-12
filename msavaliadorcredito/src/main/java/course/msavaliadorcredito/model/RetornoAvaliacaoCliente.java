package course.msavaliadorcredito.model;

import java.util.List;

public class RetornoAvaliacaoCliente {

    public RetornoAvaliacaoCliente() {
    }

    public RetornoAvaliacaoCliente(List<CartaoAprovado> cartoesCartaoAprovados) {
        this.cartoesCartaoAprovados = cartoesCartaoAprovados;
    }

    private List<CartaoAprovado> cartoesCartaoAprovados;

    public List<CartaoAprovado> getCartoesCartaoAprovados() {
        return cartoesCartaoAprovados;
    }

    public void setCartoesCartaoAprovados(List<CartaoAprovado> cartoesCartaoAprovados) {
        this.cartoesCartaoAprovados = cartoesCartaoAprovados;
    }
}
