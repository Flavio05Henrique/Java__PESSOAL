package haluva.banck.api.controller;

import haluva.banck.api.agendamentos.Agendar;
import haluva.banck.api.agendamentos.DadosAgendamentoTransferencia;
import haluva.banck.api.cliente.ClienteRepository;
import haluva.banck.api.cliente.DadosDepositoRetiradaCliente;
import haluva.banck.api.cliente.DadosTransferenciaCliente;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transacoes")
public class TransacoesController {

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private Agendar agendar;

    @PostMapping("/depositar")
    @Transactional
    public void depositar(@RequestBody DadosDepositoRetiradaCliente dados) {
        var cliente = repository.getReferenceById(dados.id());
        var valorAntes = cliente.getValorNaConta();

        cliente.depositar(dados.valorDoDeposito());

        System.out.println("valor antes: " + valorAntes + "|| valor depois: " + cliente.getValorNaConta());
    }

    @PostMapping("/retirar")
    @Transactional
    public void retirar(@RequestBody DadosDepositoRetiradaCliente dados) {
        var cliente = repository.getReferenceById(dados.id());
        var valorAntes = cliente.getValorNaConta();


        cliente.retirar(dados.valorDoDeposito());
        System.out.println("valor antes: " + valorAntes + "|| valor depois: " + cliente.getValorNaConta());

    }

    @PostMapping("/transferir")
    @Transactional
    public void transferir(@RequestBody DadosTransferenciaCliente dados) {
        var clienteDe = repository.getReferenceById(dados.idClienteDe());
        var clientePara = repository.getReferenceById(dados.idClientePara());

        clienteDe.transferir(dados.valorDoDeposito(), clientePara);
    }

    @PostMapping("/agendarTransferencia")
    @Transactional
    public ResponseEntity agendar(@RequestBody @Valid DadosAgendamentoTransferencia dados) {
        agendar.agendar(dados);
        return ResponseEntity.ok().body("Marcado para " + dados.data());
    }

}
