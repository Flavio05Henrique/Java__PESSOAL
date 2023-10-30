package br.com.primeiroProjeto.repository;

import br.com.primeiroProjeto.model.Evento;
import org.springframework.data.repository.CrudRepository;

public interface EventoRepository extends CrudRepository<Evento, String> {

}
