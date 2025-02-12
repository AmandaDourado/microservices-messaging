package course.msavaliadorcredito.application.ex;

public class DadosClientesNotFoundException extends Exception {

    public DadosClientesNotFoundException() {
        super("Dados do cliente não encotrados para o CPF informado");
    }
}
