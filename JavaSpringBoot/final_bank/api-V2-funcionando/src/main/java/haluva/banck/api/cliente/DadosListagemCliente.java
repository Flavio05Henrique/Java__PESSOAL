package haluva.banck.api.cliente;

public record DadosListagemCliente(Long id, String nome, String email, TipoDeConta tipoDeConta, Float valorNaConta){
    public DadosListagemCliente(Cliente cliente) {
        this(cliente.getId(),cliente.getNome(), cliente.getEmail(), cliente.getTipoDeConta(), cliente.getValorNaConta());
    }
}
