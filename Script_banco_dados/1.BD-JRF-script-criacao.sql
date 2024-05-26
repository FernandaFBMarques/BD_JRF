CREATE DATABASE bd_jrf_jdbc_api;

CREATE TABLE Funcionarios (
    cpf VARCHAR(255) NOT NULL,
    nome VARCHAR(255) NOT NULL,
    telefone VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    rua VARCHAR(255) NOT NULL,
    numero INTEGER NOT NULL,
    cidade VARCHAR(255) NOT NULL,
    bairro VARCHAR(255) NOT NULL,
    esta_ativo BOOLEAN DEFAULT TRUE,
    CONSTRAINT funcionario_pk PRIMARY KEY (cpf),
    CONSTRAINT chk_email CHECK (email LIKE '%_@__%.__%'), 
    CONSTRAINT chk_cpf CHECK (LENGTH(cpf) = 11 AND cpf NOT LIKE '%[^0-9]%')
);

CREATE TABLE Contadores (
    fk_Funcionarios_cpf VARCHAR(255)NOT NULL,
    clt VARCHAR(255)NOT NULL,
    CONSTRAINT pk_Contadores PRIMARY KEY (fk_Funcionarios_cpf),
    CONSTRAINT clt_unique UNIQUE (clt),
    CONSTRAINT fk_Contadores_Funcionarios FOREIGN KEY (fk_Funcionarios_cpf) REFERENCES Funcionarios(cpf)

);

CREATE TABLE Dependente (
    cpf VARCHAR(255)NOT NULL,
    fk_Contadores_fk_Funcionarios_cpf VARCHAR(255)NOT NULL,
    nome VARCHAR(255)NOT NULL,
    CONSTRAINT pk_Dependentes PRIMARY KEY (cpf),
    CONSTRAINT fk_Dependentes_Contadores FOREIGN KEY (fk_Contadores_fk_Funcionarios_cpf) REFERENCES Contadores(fk_Funcionarios_cpf) ON DELETE cascade,
	CONSTRAINT chk_cpf_dependente CHECK (LENGTH(cpf) = 11 AND cpf NOT LIKE '%[^0-9]%')
);


create  TABLE Equipe_de_vendas (
    fk_Funcionarios_cpf VARCHAR(255),
    cnpj VARCHAR(255) NOT NULL,
    mat_gerente VARCHAR(255),
    PRIMARY KEY (fk_Funcionarios_cpf),
    FOREIGN KEY (fk_Funcionarios_cpf) REFERENCES Funcionarios(cpf),
    FOREIGN KEY (mat_gerente) REFERENCES Equipe_de_vendas(fk_Funcionarios_cpf),
    CONSTRAINT chk_cnpj CHECK (LENGTH(cnpj) = 14 AND cnpj NOT LIKE '%[^0-9]%')
);

CREATE TABLE Clientes (
    cnpj VARCHAR(255) NOT NULL,
    nome_loja VARCHAR(255) NOT NULL,
    telefone VARCHAR(11)NOT NULL,
    email VARCHAR(255)NOT NULL,
    rua VARCHAR(255)NOT NULL,
    numero INTEGER NOT NULL,
    cidade VARCHAR(255)NOT NULL,
    bairro VARCHAR(255)NOT NULL,
    estado VARCHAR(255)NOT NULL,
    tipo_varejo BOOLEAN NOT NULL,
    tipo_boutique BOOLEAN NOT NULL,
    PRIMARY KEY (cnpj),
    CONSTRAINT chk_email_principal_cliente CHECK (email LIKE '%_@__%.__%'),
    CONSTRAINT chk_cnpj_clientes CHECK (LENGTH(cnpj) = 14 AND cnpj NOT LIKE '%[^0-9]%')
  );
    

CREATE TABLE email (
    id_email INTEGER PRIMARY KEY AUTO_INCREMENT,
    fk_Clientes_cnpj VARCHAR(255), -- Sem not null pq pode nao ter email adicional
    email VARCHAR(255),
    FOREIGN KEY (fk_Clientes_cnpj) REFERENCES Clientes(cnpj),
    CONSTRAINT chk_email_opcional_cliente CHECK (email LIKE '%_@__%.__%') 
);

CREATE TABLE Telefone (
    id_telefone INTEGER PRIMARY KEY AUTO_INCREMENT,
    fk_Clientes_cnpj VARCHAR(255), -- Sem not null pq pode nao ter telefone adicional
    numero_telefone VARCHAR (11),
    FOREIGN KEY (fk_Clientes_cnpj) REFERENCES Clientes(cnpj)
);

CREATE TABLE Produtos (
    codigo_de_barras VARCHAR(255) NOT NULL ,
    nome_produto VARCHAR(255) NOT NULL,
    modelo VARCHAR(255) NOT NULL,
    cor VARCHAR(255)NOT NULL,
    tamanho INTEGER NOT NULL,
    categoria VARCHAR(255) NOT NULL,
    preco DOUBLE NOT NULL,
    prazo DATE,
    url_imagem VARCHAR(2048)NOT NULL,
    esta_ativo BOOLEAN DEFAULT TRUE,
    PRIMARY KEY (codigo_de_barras),
    CONSTRAINT chk_codigo_barras CHECK (LENGTH(codigo_de_barras) = 13 AND codigo_de_barras NOT LIKE '%[^0-9]%')
);

CREATE TABLE Venda (
    numero_venda INTEGER AUTO_INCREMENT,
    data DATE NOT NULL,
    valor_venda DOUBLE default 0.0,
    PRIMARY KEY (numero_venda)
);

CREATE TABLE atende (
    numero_atendimento INTEGER AUTO_INCREMENT,
    fk_Equipe_de_vendas_fk_Funcionarios_cpf VARCHAR(255),
    fk_Clientes_cnpj VARCHAR(255),
    PRIMARY KEY (numero_atendimento),
    FOREIGN KEY (fk_Equipe_de_vendas_fk_Funcionarios_cpf) REFERENCES Equipe_de_vendas(fk_Funcionarios_cpf),
    FOREIGN KEY (fk_Clientes_cnpj) REFERENCES Clientes(cnpj)
);

CREATE TABLE fazVendaPRoduto (
    fk_Venda_numero_venda INTEGER,
    fk_Produtos_codigo_de_barras VARCHAR(255),
    fk_atende_numero_atendimento INTEGER,
    quantidade_de_produto INTEGER NOT NULL,
    PRIMARY KEY (fk_Venda_numero_venda, fk_atende_numero_atendimento, fk_Produtos_codigo_de_barras),
    FOREIGN KEY (fk_Venda_numero_venda) REFERENCES Venda(numero_venda),
    FOREIGN KEY (fk_Produtos_codigo_de_barras) REFERENCES Produtos(codigo_de_barras),
    FOREIGN KEY (fk_atende_numero_atendimento) REFERENCES Atende(numero_atendimento)
);


CREATE TABLE NF_de_comissionamento (
    numero INTEGER AUTO_INCREMENT ,
    fk_Contadores_fk_Funcionarios_cpf VARCHAR(11) NOT NULL,
    fk_Venda_numero_venda INTEGER NOT NULL,
    fk_Equipe_de_vendas_fk_Funcionarios_cpf VARCHAR(11) NOT NULL,
    serie INTEGER,
    data_emissao DATE,
    data_saida DATE,
    calculo_do_imposto DOUBLE NOT NULL,
    PRIMARY KEY (numero),
    CONSTRAINT chk_fk_Contadores_fk_Funcionarios_cpf CHECK (LENGTH(fk_Contadores_fk_Funcionarios_cpf) = 11 AND fk_Contadores_fk_Funcionarios_cpf NOT LIKE '%[^0-9]%'),
    CONSTRAINT chk_fk_Equipe_de_vendas_fk_Funcionarios_cpf CHECK (LENGTH(fk_Equipe_de_vendas_fk_Funcionarios_cpf) = 11 AND fk_Equipe_de_vendas_fk_Funcionarios_cpf NOT LIKE '%[^0-9]%'),
    CONSTRAINT fk_Contadores FOREIGN KEY (fk_Contadores_fk_Funcionarios_cpf) REFERENCES Funcionarios(cpf),
    CONSTRAINT fk_Venda FOREIGN KEY (fk_Venda_numero_venda) REFERENCES Venda(numero_venda),
    CONSTRAINT fk_Equipe_de_vendas FOREIGN KEY (fk_Equipe_de_vendas_fk_Funcionarios_cpf) REFERENCES Funcionarios(cpf)
);

