package com.br.petshop.controller;

import ch.qos.logback.core.net.server.Client;
import com.br.petshop.cliente.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cadastraCliente")
@CrossOrigin(origins = "*")
public class ClienteController {
    @Autowired
    private ClienteRepository repository;
    @PostMapping(consumes = {"application/json"})
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroCliente dados){
        repository.save(new Cliente(dados));
    }

    @GetMapping
    public Page<DadosClientesListar> listar(Pageable paginacao) {
        return repository.findAll(paginacao).map(DadosClientesListar::new);
    }

    @PutMapping
    @Transactional
    public void atulizar(@RequestBody @Valid DadosAtualizaCliente dados) {
        var cliente = repository.getReferenceById(dados.id());
        cliente.atualizarDados(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public  void excluir(@PathVariable Long id) {
        repository.deleteById(id);
    }

}
