package haluva.banck.api.cliente;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name="clientes")
@Entity(name="cliente")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Cliente {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String cpf;

    @Enumerated(EnumType.STRING)
    private TipoDeConta tipoDeConta;
    @Embedded
    private Endereco endereco;

    private Float valorNaConta;

    private boolean ativo;

    public Cliente(DadosCadastroCliente dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.cpf = dados.cpf();
        this.tipoDeConta = dados.tipoDeConta();
        this.endereco = new Endereco(dados.endereco());
        this.valorNaConta = dados.valorNaConta();
        this.ativo = true;
    }

    public void atulizarInformacoes(DadosAtualizacaoCliente dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.tipoDeConta() != null) {
            this.tipoDeConta = dados.tipoDeConta();
        }
    }

    public void desativa() {
        this.ativo = false;
    }

    public void depositar(Float valor) {
        this.valorNaConta += valor;
    }

    public boolean retirar(Float valor) {
        if (this.valorNaConta - valor >= 0 || this.tipoDeConta == TipoDeConta.ESPECIAL) {
            this.valorNaConta -= valor;
            return true;
        }
        return false;
    }

    public void transferir(Float valor, Cliente clientePara) {
        if (retirar(valor)) {
            clientePara.depositar(valor);
        }
    }
}
