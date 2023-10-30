package br.com.primeiroProjeto.controller;

import br.com.primeiroProjeto.model.Evento;
import br.com.primeiroProjeto.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class EventoController {
    @Autowired
    private EventoRepository repository;

    @RequestMapping(value = "/cadastrarEvento", method = RequestMethod.GET)
    public String form() {
        return "evento/formEvento";
    }

    @RequestMapping(value = "/cadastrarEvento", method = RequestMethod.POST)
    public String form(Evento evento) {
        repository.save(evento);
        return "evento/index";
    }

    @RequestMapping(value = "/listarEventos", method = RequestMethod.POST)
    public String listar() {
        return "repository.findAll().toString()";
    }

}
