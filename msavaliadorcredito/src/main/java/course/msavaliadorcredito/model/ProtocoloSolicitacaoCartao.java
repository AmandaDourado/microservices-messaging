package course.msavaliadorcredito.model;

public class ProtocoloSolicitacaoCartao {

    private String protocolo;

    public ProtocoloSolicitacaoCartao() {};

    public ProtocoloSolicitacaoCartao(String protocolo) {
        this.protocolo = protocolo;
    }

    public String getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(String protocolo) {
        this.protocolo = protocolo;
    }
}
