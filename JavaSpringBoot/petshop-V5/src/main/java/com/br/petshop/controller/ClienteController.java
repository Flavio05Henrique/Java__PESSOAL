package com.br.petshop.controller;

import com.br.petshop.domain.cliente.*;
import com.br.petshop.domain.cliente.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("cadastraCliente")
@CrossOrigin(origins = "*")
public class ClienteController {
    @Autowired
    private ClienteRepository repository;
    @PostMapping(consumes = {"application/json"})
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroCliente dados, UriComponentsBuilder URIBuider){
        var cliente = new Cliente(dados);
        repository.save(cliente);

        var URI = URIBuider.path("/medicos/{id}").buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity.created(URI).body(cliente);
    }

    @GetMapping
    public ResponseEntity<Page<DadosClientesListar>> listar(Pageable paginacao) {
        var page = repository.findAll(paginacao).map(DadosClientesListar::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atulizar(@RequestBody @Valid DadosAtualizaCliente dados) {
        var cliente = repository.getReferenceById(dados.id());
        cliente.atualizarDados(dados);

        return ResponseEntity.ok(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}
