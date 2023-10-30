package haluva.banck.api.cliente;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoCliente(
        @NotNull
        Long id,
        String nome,
        TipoDeConta tipoDeConta) {
}
