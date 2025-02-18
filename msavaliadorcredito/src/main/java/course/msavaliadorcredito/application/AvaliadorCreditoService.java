package course.msavaliadorcredito.application;

import course.msavaliadorcredito.application.ex.DadosClientesNotFoundException;
import course.msavaliadorcredito.application.ex.ErroComunicacaoMicroservicesException;
import course.msavaliadorcredito.application.ex.ErroSolicitacaoCartaoException;
import course.msavaliadorcredito.infra.mq.SolicitacaoEmissaoCartaoPublisher;
import course.msavaliadorcredito.model.*;
import course.msavaliadorcredito.infra.clients.CartoesResourcesClient;
import course.msavaliadorcredito.infra.clients.ClienteResourceClient;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AvaliadorCreditoService {

    @Autowired
    private ClienteResourceClient clienteResourceClient;
    @Autowired
    private CartoesResourcesClient cartoesResourcesClient;
    @Autowired
    private SolicitacaoEmissaoCartaoPublisher emissaoCartaoPublisher;

    public SituacaoCliente obterSituacaoCliente(String cpf) throws DadosClientesNotFoundException, ErroComunicacaoMicroservicesException {
        try {
            ResponseEntity<DadosCliente> dadosClienteResponse = clienteResourceClient.dadosCliente(cpf);
            ResponseEntity<List<CartaoCliente>> cartoesResponse = cartoesResourcesClient.getCartoresByCliente(cpf);

            SituacaoCliente situacaoCliente = new SituacaoCliente();
            situacaoCliente.setCliente(dadosClienteResponse.getBody());
            situacaoCliente.setCartoes(cartoesResponse.getBody());

            return situacaoCliente;
        } catch (FeignException.FeignClientException e) {
            int status = e.status();
            if(HttpStatus.NOT_FOUND.value() == status) {
                throw new DadosClientesNotFoundException();
            }

            throw new ErroComunicacaoMicroservicesException(e.getMessage(),status);
        }
    }

    public RetornoAvaliacaoCliente realizarAvaliacao(String cpf, Long renda) throws DadosClientesNotFoundException, ErroComunicacaoMicroservicesException {
        try {
            ResponseEntity<DadosCliente> dadosClienteResponse = clienteResourceClient.dadosCliente(cpf);
            ResponseEntity<List<Cartao>> cartoesResponse = cartoesResourcesClient.getCartoesRendaAte(renda);

            List<Cartao> listCartao = cartoesResponse.getBody();
            var listaCartoesAprovados = listCartao.stream().map(cartao -> {
                DadosCliente dadosCliente = dadosClienteResponse.getBody();

                BigDecimal limiteBasico = cartao.getLimiteBasico();
                BigDecimal idadeBD = BigDecimal.valueOf(dadosCliente.getIdade());
                var fator = idadeBD.divide(BigDecimal.valueOf(10));
                BigDecimal limiteAprovado = fator.multiply(limiteBasico);

                CartaoAprovado aprovado = new CartaoAprovado();
                aprovado.setCartao(cartao.getNome());
                aprovado.setBandeira(cartao.getBandeira());
                aprovado.setLimiteAprovado(limiteAprovado);

                return aprovado;
            }).collect(Collectors.toList());

            return new RetornoAvaliacaoCliente(listaCartoesAprovados);

        } catch (FeignException.FeignClientException e) {
            int status = e.status();
            if(HttpStatus.NOT_FOUND.value() == status) {
                throw new DadosClientesNotFoundException();
            }

            throw new ErroComunicacaoMicroservicesException(e.getMessage(),status);
        }

    }

    public ProtocoloSolicitacaoCartao solicitarEmissaoCartao(DadosSolicitacaoEmissaoCartao dados) {
        try {
            emissaoCartaoPublisher.solicitarCartao(dados);
            var protocolo = UUID.randomUUID().toString();
            return new ProtocoloSolicitacaoCartao(protocolo);
        } catch (Exception e) {
            throw  new ErroSolicitacaoCartaoException(e.getMessage());
        }
    }

}
