create table agendamentos(

    id bigint not null auto_increment,
    cliente_de_id bigint not null,
    cliente_para_id bigint not null,
    data datetime not null,

    primary key(id),

    constraint fk_agendamentos_cliente_de_id foreign key(cliente_de_id) references clientes(id),
    constraint fk_agendamentos_cliente_para_id foreign key(cliente_para_id) references clientes(id)
);