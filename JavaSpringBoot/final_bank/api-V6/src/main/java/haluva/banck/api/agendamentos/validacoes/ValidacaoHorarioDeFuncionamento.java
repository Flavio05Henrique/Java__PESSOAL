package haluva.banck.api.agendamentos.validacoes;

import haluva.banck.api.agendamentos.DadosAgendamentoTransferencia;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidacaoHorarioDeFuncionamento implements ValidadorAgendamentoDeTransferencias{

    public void validar(DadosAgendamentoTransferencia dados) {
        var dataAgendada = dados.data();
        var domingo = dataAgendada.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var antesDoHorarioDeFuncionamento = dataAgendada.getHour() < 6;
        var depoisDoHorarioDeFuncionamento = dataAgendada.getHour() >  23;

        if (domingo || antesDoHorarioDeFuncionamento || depoisDoHorarioDeFuncionamento) {
            throw new RuntimeException("Data fora dos horarios de funcionamento!");
        }
    }
}
