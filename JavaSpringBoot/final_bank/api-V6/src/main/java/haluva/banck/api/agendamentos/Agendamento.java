package haluva.banck.api.agendamentos;

import haluva.banck.api.cliente.Cliente;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "agendamentos")
@Entity(name = "agendamento")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_de_id")
    private Cliente clienteDe;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_para_id")
    private Cliente clientePara;

    private LocalDateTime data;
}
