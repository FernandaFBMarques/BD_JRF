CREATE DATABASE bd_jrf_jdbc_api;

CREATE TABLE Funcionarios (
    cpf VARCHAR(255) NOT NULL,
    nome VARCHAR(255) NOT NULL,
    telefone VARCHAR(255) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL UNIQUE,
    rua VARCHAR(255),
    numero INTEGER,
    cidade VARCHAR(255),
    bairro VARCHAR(255),
    CONSTRAINT funcionario_pk PRIMARY KEY (cpf)
);

CREATE TABLE Contadores (
    fk_Funcionarios_cpf VARCHAR(255),
    clt VARCHAR(255),
    CONSTRAINT pk_Contadores PRIMARY KEY (fk_Funcionarios_cpf),
    CONSTRAINT clt_unique UNIQUE (clt),
    CONSTRAINT fk_Contadores_Funcionarios FOREIGN KEY (fk_Funcionarios_cpf) REFERENCES Funcionarios(cpf)
);

CREATE TABLE Dependente (
    fk_Contadores_fk_Funcionarios_cpf VARCHAR(255),
    cpf VARCHAR(255) NOT NULL,
    nome VARCHAR(255) NOT NULL,
    PRIMARY KEY (cpf),
    FOREIGN KEY (fk_Contadores_fk_Funcionarios_cpf) REFERENCES Contadores(fk_Funcionarios_cpf)
);


create  TABLE Equipe_de_vendas (
    fk_Funcionarios_cpf VARCHAR(255),
    cnpj VARCHAR(255) NOT NULL,
    mat_gerente VARCHAR(255),
    PRIMARY KEY (fk_Funcionarios_cpf),
    FOREIGN KEY (fk_Funcionarios_cpf) REFERENCES Funcionarios(cpf),
    FOREIGN KEY (mat_gerente) REFERENCES Equipe_de_vendas(fk_Funcionarios_cpf)
);

CREATE TABLE Clientes (
    cnpj VARCHAR(255) NOT NULL,
    nome_loja VARCHAR(255) NOT NULL,
    telefone VARCHAR(11),
    email VARCHAR(255),
    rua VARCHAR(255),
    numero INTEGER,
    cidade VARCHAR(255),
    bairro VARCHAR(255),
    estado VARCHAR(255),
    tipo_varejo BOOLEAN,
    tipo_boutique BOOLEAN,
    PRIMARY KEY (cnpj)
);

CREATE TABLE email (
    id_email INTEGER PRIMARY KEY AUTO_INCREMENT,
    fk_Clientes_cnpj VARCHAR(255),
    email VARCHAR(255),
    FOREIGN KEY (fk_Clientes_cnpj) REFERENCES Clientes(cnpj)
);

CREATE TABLE Telefone (
    id_telefone INTEGER PRIMARY KEY AUTO_INCREMENT,
    fk_Clientes_cnpj VARCHAR(255),
    numero_telefone VARCHAR (11),
    FOREIGN KEY (fk_Clientes_cnpj) REFERENCES Clientes(cnpj)
);


CREATE TABLE Produtos (
    codigo_de_barras VARCHAR(255),
    nome_produto VARCHAR(255),
    modelo VARCHAR(255),
    cor VARCHAR(255),
    tamanho INTEGER,
    categoria VARCHAR(255),
    preco DOUBLE,
    prazo DATE,
     PRIMARY KEY (codigo_de_barras)
);

INSERT INTO Funcionarios (cpf, nome, telefone, email, rua, numero, cidade, bairro) VALUES
('11111111111', 'Gal Costa', '1122334455', 'gal.costa@email.com', 'Rua da Harmonia', 123, 'Salvador', 'Centro'),
('22222222222', 'Reginaldo Rossi', '2233445566', 'reginaldo.rossi@email.com', 'Rua do Samba', 234, 'Recife', 'Boa Viagem'),
('33333333333', 'Caetano Veloso', '3344556677', 'caetano.veloso@email.com', 'Avenida Tropical', 345, 'Fortaleza', 'Coração'),
('44444444444', 'Maria Bethânia', '4455667788', 'maria.bethania@email.com', 'Rua dos Passaros', 456, 'Natal', 'Pelourinho'),
('55555555555', 'Raphaela Santos', '5566778899', 'raphasantos@email.com', 'Rua da Música', 567, 'Aracaju', 'Barra'),
('66666666666', 'Rita Lee', '6677889900', 'rita.lee@email.com', 'Alameda dos Anjos', 678, 'João Pessoa', 'Liberdade'),
('77777777777', 'Roberto Carlos', '7788990011', 'roberto.carlos@email.com', 'Avenida Rei', 789, 'Maceió', 'Itapemirim'),
('88888888888', 'Tim Maia', '8899001122', 'tim.maia@email.com', 'Boulevard do Samba', 890, 'São Luís', 'Tijuca'),
('99999999999', 'Elis Regina', '9900112233', 'elis.regina@email.com', 'Rua do Sol', 901, 'Teresina', 'Bom Fim'),
('10101010101', 'Chico Buarque', '1011121314', 'chico.buarque@email.com', 'Rua da Poesia', 102, 'Feira de Santana', 'Botafogo'),
('11101010101', 'Jorge Ben Jor', '1213141516', 'jorge.ben@email.com', 'Avenida Brasil', 212, 'Campina Grande', 'Maracanã'),
('12121212121', 'Djavan', '1314151617', 'djah@email.com', 'Rua das Flores', 313, 'Petrolina', 'Jatiúca')
;

INSERT INTO Contadores (fk_Funcionarios_cpf, clt) VALUES
('66666666666', '1203333444456'),
('77777777777', '1234567890123');

INSERT INTO Dependente (fk_Contadores_fk_Funcionarios_cpf, cpf, nome) VALUES
('66666666666', '10000000001', 'Anitta'),
('66666666666', '10000000002', 'Cazuza'),
('66666666666', '10000000003', 'Ivete Sangalo'),
('77777777777', '20000000001', 'Marisa Monte'),
('77777777777', '20000000002', 'Gilberto Gil');

INSERT INTO Equipe_de_vendas (fk_Funcionarios_cpf, cnpj, mat_gerente) values ('11111111111', '11223344000185', null);
INSERT INTO Equipe_de_vendas (fk_Funcionarios_cpf, cnpj, mat_gerente) values ('22222222222', '22334455000195', '11111111111'); -- Reginaldo Rossi (gerenciado por Gal Costa)
INSERT INTO Equipe_de_vendas (fk_Funcionarios_cpf, cnpj, mat_gerente) values ('33333333333', '33445566000105', '55555555555'); -- Caetano Veloso (gerenciado por Raphaela Santos)
INSERT INTO Equipe_de_vendas (fk_Funcionarios_cpf, cnpj, mat_gerente) values ('44444444444', '44556677000115', '55555555555'); -- Maria Bethânia (gerenciada por Raphaela Santos)
INSERT INTO Equipe_de_vendas (fk_Funcionarios_cpf, cnpj, mat_gerente) values ('55555555555', '55667788000125', NULL); -- Raphaela Santos (sem gerente)
INSERT INTO Equipe_de_vendas (fk_Funcionarios_cpf, cnpj, mat_gerente) values ('88888888888', '66778899000135', '11111111111'); -- Tim Maia (gerenciado por Gal Costa)
INSERT INTO Equipe_de_vendas (fk_Funcionarios_cpf, cnpj, mat_gerente) values ('99999999999', '77889900110145', '55555555555'); -- Elis Regina (gerenciada por Raphaela Santos)
INSERT INTO Equipe_de_vendas (fk_Funcionarios_cpf, cnpj, mat_gerente) values ('10101010101', '88990011220155', '55555555555'); -- Chico Buarque (gerenciado por Raphaela Santos)
INSERT INTO Equipe_de_vendas (fk_Funcionarios_cpf, cnpj, mat_gerente) values ('11101010101', '99001122330165', '55555555555'); -- Jorge Ben Jor (gerenciado por Raphaela Santos)
INSERT INTO Equipe_de_vendas (fk_Funcionarios_cpf, cnpj, mat_gerente) values ('12121212121', '10111213140175', '55555555555'); -- Djavan (gerenciado por Raphaela Santos)


INSERT INTO Clientes (cnpj, nome_loja, telefone, email, rua, numero, cidade, bairro, estado, tipo_varejo, tipo_boutique) VALUES
('12345678000191', 'Moda Tropical', 81912345678, 'contato@modatropical.com', 'Rua das Palmeiras', 101, 'Recife', 'Boa Viagem', 'PE', TRUE, FALSE),
('23456789000182', 'Elegância Nordeste', 71323456789, 'vendas@elegancianordeste.com', 'Avenida do Sol', 202, 'Salvador', 'Pituba', 'BA', FALSE, TRUE),
('34567890000173', 'Sertão Chic', 87934567890, 'info@sertaochic.com', 'Travessa dos Cariris', 303, 'Petrolina', 'Centro', 'PE', FALSE, TRUE),
('45678901000164', 'Vento Leste', 84345678901, 'suporte@ventoleste.com', 'Boulevard dos Ventos', 404, 'Natal', 'Ponta Negra', 'RN', TRUE, FALSE),
('56789012000155', 'Artes do Mar', 82256789012, 'atendimento@artesdomar.com', 'Rua dos Navegantes', 505, 'Maceió', 'Jatiúca', 'AL', TRUE, FALSE),
('67890123000146', 'Cores do Nordeste', 98267890123, 'contact@coresdonordeste.com', 'Avenida Brasil', 606, 'São Luís', 'Renascença', 'MA', FALSE, TRUE),
('78901234000137', 'Tecidos Paraíso', 86378901234, 'sales@tecidosparaiso.com', 'Praça Central', 707, 'Teresina', 'Centro', 'PI', TRUE, FALSE),
('89012345000128', 'Estilo Tropical', 74389012345, 'style@estilotropical.com', 'Avenida dos Coqueiros', 808, 'Fortaleza', 'Meireles', 'CE', TRUE, FALSE);

INSERT INTO email (fk_Clientes_cnpj, email) VALUES
('12345678000191', 'financeiro@modatropical.com'),
('12345678000191', 'marketing@modatropical.com'),
('23456789000182', 'financeiro@elegancianordeste.com'),
('23456789000182', 'suporte@elegancianordeste.com'),
('34567890000173', 'rh@sertaochic.com'),
('34567890000173', 'marketing@sertaochic.com'),
('45678901000164', 'financeiro@ventoleste.com'),
('45678901000164', 'rh@ventoleste.com'),
('56789012000155', 'financeiro@artesdomar.com'),
('56789012000155', 'compras@artesdomar.com'),
('67890123000146', 'financeiro@coresdonordeste.com'),
('67890123000146', 'suporte@coresdonordeste.com'),
('78901234000137', 'financeiro@tecidosparaiso.com'),
('78901234000137', 'marketing@tecidosparaiso.com'),
('89012345000128', 'financeiro@estilotropical.com'),
('89012345000128', 'suporte@estilotropical.com');

INSERT INTO Telefone (fk_Clientes_cnpj, numero_telefone) VALUES
('12345678000191', 81912345679), 
('23456789000182', 71323456780),
('34567890000173', 87934567891),
('56789012000155', 82256789013), 
('67890123000146', 98267890124),
('89012345000128', 74389012346); 




