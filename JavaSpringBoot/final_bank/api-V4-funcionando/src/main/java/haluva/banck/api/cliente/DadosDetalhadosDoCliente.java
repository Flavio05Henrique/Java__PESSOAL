package haluva.banck.api.cliente;

public record DadosDetalhadosDoCliente(
        Long id,
        String nome,
        String email,
        String cpf,
        TipoDeConta tipoDeConta,
        Endereco endereco,
        Float valorNaConta
) {
    public DadosDetalhadosDoCliente(Cliente cliente) {
        this(cliente.getId(),cliente.getEmail(), cliente.getCpf(), cliente.getNome(), cliente.getTipoDeConta(), cliente.getEndereco(), cliente.getValorNaConta());
    }

}
