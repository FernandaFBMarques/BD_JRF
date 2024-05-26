
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
('66666666666', '10000000003', 'Ivete Sangalo')
;

INSERT INTO Dependente (fk_Contadores_fk_Funcionarios_cpf, cpf, nome)
SELECT fk_Funcionarios_cpf, '00000000000', 'Sem dependente'
FROM Contadores
WHERE fk_Funcionarios_cpf NOT IN (
    SELECT fk_Contadores_fk_Funcionarios_cpf FROM Dependente
);

INSERT INTO Equipe_de_vendas (fk_Funcionarios_cpf, cnpj, mat_gerente) VALUES
('11111111111', '11223344000185', NULL),
('55555555555', '55667788000125', NULL), -- Raphaela Santos (sem gerente)
('22222222222', '22334455000195', '11111111111'), -- Reginaldo Rossi (gerenciado por Gal Costa)
('33333333333', '33445566000105', '55555555555'), -- Caetano Veloso (gerenciado por Raphaela Santos)
('44444444444', '44556677000115', '55555555555'), -- Maria Bethânia (gerenciada por Raphaela Santos)
('88888888888', '66778899000135', '11111111111'), -- Tim Maia (gerenciado por Gal Costa)
('99999999999', '77889900110145', '55555555555'), -- Elis Regina (gerenciada por Raphaela Santos)
('10101010101', '88990011220155', '55555555555'), -- Chico Buarque (gerenciado por Raphaela Santos)
('11101010101', '99001122330165', '55555555555'), -- Jorge Ben Jor (gerenciado por Raphaela Santos)
('12121212121', '10111213140175', '55555555555'); -- Djavan (gerenciado por Raphaela Santos)


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

INSERT INTO Produtos (codigo_de_barras, nome_produto, modelo, cor, tamanho, categoria, preco, prazo, url_imagem) VALUES
('9876543210123', 'Tênis Olympikus Corre 3', 'Corre 3', 'Várias', 42, 'Tênis de Corrida', 229.99, '2024-12-31', 'https://www.centauro.com.br/tenis-olympikus-corre-3-unissex-980095.html'),
('1234567890124', 'Tênis Olympikus Corre Supra', 'Corre Supra', 'Roxa', 40, 'Tênis de Corrida', 249.99, '2024-11-30', 'https://www.centauro.com.br/tenis-olympikus-corre-supra-unissex-980096.html'),
('1234567890125', 'Tênis Casual Unissex Under Armour HOVR Infinite Summit 2', 'HOVR Infinite Summit 2', 'Cinza', 41, 'Tênis Casual', 279.99, '2024-10-31', 'https://www.centauro.com.br/tenis-under-armour-hovr-infinite-summit-2-unissex-980097.html'),
('1234567890126', 'Tênis Nike Revolution 5', 'Revolution 5', 'Preto', 43, 'Tênis de Corrida', 199.99, '2024-09-30', 'https://www.centauro.com.br/tenis-nike-revolution-5-unissex-980098.html'),
('1234567890127', 'Tênis Adidas Ultraboost 21', 'Ultraboost 21', 'Branco', 44, 'Tênis de Corrida', 399.99, '2024-08-31', 'https://www.centauro.com.br/tenis-adidas-ultraboost-21-unissex-980099.html'),
('1234567890128', 'Tênis Mizuno Wave Prophecy 9', 'Wave Prophecy 9', 'Azul', 39, 'Tênis de Corrida', 499.99, '2024-07-31', 'https://www.centauro.com.br/tenis-mizuno-wave-prophecy-9-unissex-980100.html'),
('1234567890129', 'Tênis Asics Gel-Kayano 27', 'Gel-Kayano 27', 'Verde', 38, 'Tênis de Corrida', 349.99, '2024-06-30', 'https://www.centauro.com.br/tenis-asics-gel-kayano-27-unissex-980101.html'),
('1234567890130', 'Tênis Puma Hybrid NX', 'Hybrid NX', 'Vermelho', 45, 'Tênis Casual', 219.99, '2024-05-31', 'https://www.centauro.com.br/tenis-puma-hybrid-nx-unissex-980102.html'),
('1234567890131', 'Tênis Reebok Nano 9', 'Nano 9', 'Amarelo', 37, 'Tênis de Treino', 299.99, '2024-04-30', 'https://www.centauro.com.br/tenis-reebok-nano-9-unissex-980103.html'),
('1234567890132', 'Tênis New Balance 1080v10', '1080v10', 'Laranja', 46, 'Tênis de Corrida', 329.99, '2024-03-31', 'https://www.centauro.com.br/tenis-new-balance-1080v10-unissex-980104.html'),
('1234567890133', 'Tênis Fila KR3', 'KR3', 'Rosa', 36, 'Tênis de Corrida', 189.99, '2024-02-29', 'https://www.centauro.com.br/tenis-fila-kr3-unissex-980105.html')
;


INSERT INTO Atende (fk_Equipe_de_vendas_fk_Funcionarios_cpf, fk_Clientes_cnpj) VALUES
('11111111111', '12345678000191'), -- 1 Gal Costa atende Moda Tropical 
('55555555555', '23456789000182'), -- 2 Raphaela Santos atende Elegância Nordeste
('22222222222', '34567890000173'), -- 3 Reginaldo Rossi atende Sertão Chic
('33333333333', '45678901000164'), -- 4 Caetano Veloso atende Vento Leste
('44444444444', '56789012000155'), -- 5 Maria Bethânia atende Artes do Mar
('88888888888', '67890123000146'), -- 6 Tim Maia atende Cores do Nordeste
('99999999999', '78901234000137'), -- 7 Elis Regina atende Tecidos Paraíso
('10101010101', '89012345000128'); -- 8 Chico Buarque atende Estilo Tropical


INSERT INTO Venda (data) VALUES
('2024-01-15'), -- venda 1
('2024-01-20'), -- venda 2
('2024-01-25'), -- venda 3
('2024-01-30'), -- venda 4
('2024-02-05') -- venda 5
;

INSERT INTO fazvendaproduto (fk_Venda_numero_venda,fk_Produtos_codigo_de_barras,fk_atende_numero_atendimento, quantidade_de_produto) VALUES
(1,'9876543210123', 1, 2),
(1,'1234567890124', 1, 3),
(1,'1234567890125', 1, 5),
(2,'1234567890126', 2, 1),
(2,'1234567890127', 2, 3),
(2,'1234567890128', 2, 1),
(4,'1234567890129', 4, 3),
(4,'1234567890130', 4, 4),
(5,'1234567890131', 5, 1),
(5,'1234567890132', 5, 1),
(5,'1234567890133', 5, 1),
(3,'9876543210123', 6, 15)
;

INSERT INTO NF_de_comissionamento (
    numero, serie, data_emissao, data_saida, calculo_do_imposto, fk_Contadores_fk_Funcionarios_cpf
) VALUES
    (1001, 1, '2024-01-15', '2024-01-15', 2617.901, '66666666666'),
    (1002, 1, '2024-01-20', '2024-01-20', 2089.945, '66666666666'),
    (1003, 1, '2024-01-25', '2024-01-25', 3794.35, '77777777777'),
    (1004, 1, '2024-01-30', '2024-01-30', 2229.92, '77777777777'),
    (1005, 1, '2024-02-05', '2024-02-05', 581.878, '77777777777');


INSERT INTO geranf (
    fk_Venda_numero_venda, fk_NF_de_comissionamento_numero, fk_Equipe_de_vendas_fk_Funcionarios_cpf
) VALUES
    (1, 1001, '11111111111'),
    (2, 1002, '22222222222'),
    (3, 1003, '88888888888'),
    (4, 1004, '33333333333'),
    (5, 1005, '44444444444');

