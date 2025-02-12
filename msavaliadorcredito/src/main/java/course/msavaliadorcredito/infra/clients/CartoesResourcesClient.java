package course.msavaliadorcredito.infra.clients;

import course.msavaliadorcredito.model.Cartao;
import course.msavaliadorcredito.model.CartaoCliente;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "mscards", path = "/cartoes")
public interface CartoesResourcesClient {

    @GetMapping(params = "cpf")
    ResponseEntity<List<CartaoCliente>> getCartoresByCliente(@RequestParam("cpf") String cpf);

    @GetMapping(params = "renda")
    public ResponseEntity <List<Cartao>> getCartoesRendaAte(@RequestParam("renda") Long renda);
}
