package haluva.banck.api.agendamentos;

import haluva.banck.api.agendamentos.validacoes.ValidadorAgendamentoDeTransferencias;
import haluva.banck.api.cliente.ClienteRepository;
import haluva.banck.api.infra.exception.ExcepetionValidacaoDeAgendamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Agendar {

    @Autowired
    private AgendamentoRepository agendamentoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private List<ValidadorAgendamentoDeTransferencias> validadores;

    public void agendar(DadosAgendamentoTransferencia dados) {
        if (!clienteRepository.existsById(dados.idClienteDe())) {
            throw new ExcepetionValidacaoDeAgendamento("Seus dados estão incorretos ou inexistentes");
        }
        if (!clienteRepository.existsById(dados.idClienteDe())) {
            throw new ExcepetionValidacaoDeAgendamento("Os dados do destinatario estão incorretos ou inexistentes");
        }

        validadores.forEach( v -> v.validar(dados));

        var clienteDe = clienteRepository.findById(dados.idClienteDe()).get();
        var clientePara = clienteRepository.findById(dados.idClientePara()).get();
        var agendamento = new Agendamento(null, clienteDe, clientePara, dados.data());
        agendamentoRepository.save(agendamento);

    }
}
