package haluva.banck.api.agendamentos;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosAgendamentoTransferencia(
        @NotNull
        Long idClienteDe,
        @NotNull
        Long idClientePara,
        @NotNull
        @Future
        LocalDateTime data
) {
}
