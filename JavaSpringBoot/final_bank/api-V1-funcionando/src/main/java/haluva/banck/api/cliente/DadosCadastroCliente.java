package haluva.banck.api.cliente;

public record DadosCadastroCliente(
            String nome,
            String email,
            String cpf,
            TipoDeConta tipoDeConta,
            EnderecoCliente endereco,
            Float valorNaConta
        ) {
}
