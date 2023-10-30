package haluva.banck.api.cliente;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {

    private String bairro;
    private String cep;
    private  String cidade;
    private String numero;

    public Endereco(DadosEnderecoCliente endereco) {
        this.bairro = endereco.bairro();
        this.cep = endereco.cep();
        this.cidade = endereco.cidade();
        this.numero = endereco.numero();
    }
}
