package haluva.banck.api.agendamentos.validacoes;

import haluva.banck.api.agendamentos.DadosAgendamentoTransferencia;
import haluva.banck.api.cliente.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidaSeClienteEstaAtivo implements ValidadorAgendamentoDeTransferencias{

    @Autowired
    private ClienteRepository repository;

    public void validar(DadosAgendamentoTransferencia dados) {

        var clienteDeEstaAtivo = repository.findAtivoById(dados.idClienteDe());
        var clienteParaEstaAtivo = repository.findAtivoById(dados.idClientePara());
        if (!clienteDeEstaAtivo || !clienteParaEstaAtivo) {
            throw new RuntimeException("Seus dados ou do remetente estão incorretos");
        }

    }

}
