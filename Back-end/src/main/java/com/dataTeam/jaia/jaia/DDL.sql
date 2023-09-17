create table endereco(
    id_end number(2) constraint pk_id_end primary key
    ,logradouro_end varchar(50) not null
    ,numero_end number(10) not null
    ,bairro_end varchar(50) not null
    ,cidade_end varchar(50) not null
    ,uf_end varchar(2) not null
);



create table cliente(
    cnpj_cli number(14) constraint pk_cli_cnpj primary key
    ,nome_cli varchar(100) not null
    ,telefone_cli number(14) constraint uk_cli_tel unique not null
    ,senha_cli varchar(200) not null
    ,fk_id_end number(2)
    ,constraint fk_cli_id_end foreign key(fk_id_end) references endereco(id_end)
    );



create table requisicao(
    id_req number(2) constraint pk_req_id_req primary key
    ,inspecao_req varchar(20) not null
    ,descricao_req varchar(200) not null
    ,status_req varchar(20) not null
    ,data_abertura_req date default sysdate
);



create table solicitacao(
	cnpj_cli number(14)
    ,id_req number(2)
    ,constraint pk_soli_cnpj_req primary key(cnpj_cli, id_req)
    ,constraint fk_soli_cnpj foreign key(cnpj_cli) references cliente(cnpj_cli)
    ,constraint fk_soli_id_req foreign key(id_req) references requisicao(id_req)
);



create table departamento(
    cod_depart number(2) constraint pk_dept_cod_depart primary key
    ,nome_depart varchar(20) constraint uk_dept_nom_depart unique not null
);


create table funcionario(
    cpf_func number(11) constraint pk_func_cpf_func primary key
    ,nome_func varchar(100) not null
    ,email_func varchar(100) constraint uk_func_email_func unique not null
    ,supervisor number(11)
    ,cod_depart number(2)
    ,constraint fk_func_super foreign key(supervisor) references funcionario(cpf_func)
    ,constraint fk_funcde_cod_depart foreign key(cod_depart) references departamento(cod_depart)
);



create table administrador(
    nome_usuario varchar(10) constraint pk_adm_nom_adm primary key
    ,nome_adm varchar(50) not null
    ,senha_adm varchar(200) not null
);