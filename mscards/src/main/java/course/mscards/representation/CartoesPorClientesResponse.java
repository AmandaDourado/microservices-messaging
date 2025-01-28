package course.mscards.representation;

import course.mscards.domain.ClienteCartao;

import java.math.BigDecimal;

public class CartoesPorClientesResponse {

    private String nome;
    private String bandeira;
    private BigDecimal limiteLiberado;

    public CartoesPorClientesResponse(String nome, String bandeira, BigDecimal limiteLiberado) {
        this.nome = nome;
        this.bandeira = bandeira;
        this.limiteLiberado = limiteLiberado;
    }

    public CartoesPorClientesResponse() {
        this.nome = nome;
        this.bandeira = bandeira;
        this.limiteLiberado = limiteLiberado;
    }

    public static CartoesPorClientesResponse fromModel(ClienteCartao model) {
        return new CartoesPorClientesResponse(
                model.getCartao().getNome(),
                model.getCartao().getBandeira().toString(),
                model.getLimite());
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getBandeira() {
        return bandeira;
    }

    public void setBandeira(String bandeira) {
        this.bandeira = bandeira;
    }

    public BigDecimal getLimiteLiberado() {
        return limiteLiberado;
    }

    public void setLimiteLiberado(BigDecimal limiteLiberado) {
        this.limiteLiberado = limiteLiberado;
    }
}
