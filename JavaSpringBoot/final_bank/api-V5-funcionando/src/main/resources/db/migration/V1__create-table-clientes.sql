create table clientes(

    id bigint not null auto_increment,
    nome varchar(100) not null,
    email varchar(100) not null unique,
    cpf varchar(20) not null unique,
    tipo_de_conta varchar(100) not null,
    bairro varchar(100) not null,
    cep varchar(9) not null,
    cidade varchar(100),
    numero varchar(20),
    valor_na_conta varchar(20) not null,

    primary key(id)

);