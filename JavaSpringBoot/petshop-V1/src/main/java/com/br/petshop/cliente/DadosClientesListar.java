package com.br.petshop.cliente;

public record DadosClientesListar(Long id, String nome, String email, String telefone) {
    public DadosClientesListar(Cliente cliente) {
        this(cliente.getId(), cliente.getNome(), cliente.getEmail(), cliente.getTelefone());
    }
}
