package course.mscards.application;

import course.mscards.domain.ClienteCartao;
import course.mscards.infra.repository.ClienteCartaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteCartaoService {

    @Autowired
    private ClienteCartaoRepository repository;

    public List<ClienteCartao> listarCartoesByCpf(String cpf) {
        return repository.findByCpf(cpf);
    }
}
