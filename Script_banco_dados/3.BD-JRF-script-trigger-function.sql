DELIMITER //

CREATE FUNCTION calcular_valor_venda(venda_id INT) RETURNS DOUBLE
DETERMINISTIC
BEGIN
    DECLARE total DOUBLE DEFAULT 0;
    
    SELECT SUM(p.preco * fvp.quantidade_de_produto)
    INTO total
    FROM fazvendaproduto fvp
    JOIN Produtos p ON fvp.fk_Produtos_codigo_de_barras = p.codigo_de_barras
    WHERE fvp.fk_Venda_numero_venda = venda_id;
    
    RETURN total;
END //

DELIMITER ;
   
DELIMITER //

CREATE TRIGGER atualizar_valor_venda
AFTER INSERT ON fazvendaproduto
FOR EACH ROW
BEGIN
    DECLARE novo_valor DOUBLE;
    
    SET novo_valor = calcular_valor_venda(NEW.fk_Venda_numero_venda);
    
    UPDATE Venda
    SET valor_venda = novo_valor
    WHERE numero_venda = NEW.fk_Venda_numero_venda;
END //

DELIMITER ;

-- para testar a função o argumento é numero_venda de venda
SELECT calcular_valor_venda(1);

-- fazendo esse insert para atualizar o valor de venda de todas a vendas existentes de acordo com o trigger

INSERT INTO fazvendaproduto (fk_Venda_numero_venda, fk_Produtos_codigo_de_barras, fk_atende_numero_atendimento, quantidade_de_produto) VALUES
(1, '1234567890133', 1, 3)
;

INSERT INTO fazvendaproduto (fk_Venda_numero_venda, fk_Produtos_codigo_de_barras, fk_atende_numero_atendimento, quantidade_de_produto) VALUES
(2, '1234567890129', 2, 2)
;

INSERT INTO fazvendaproduto (fk_Venda_numero_venda, fk_Produtos_codigo_de_barras, fk_atende_numero_atendimento, quantidade_de_produto) VALUES
(3, '1234567890129', 2, 2)
;

INSERT INTO fazvendaproduto (fk_Venda_numero_venda, fk_Produtos_codigo_de_barras, fk_atende_numero_atendimento, quantidade_de_produto) VALUES
(4, '1234567890126', 2, 1),
(5, '1234567890128', 2, 1)
;

-- para testar a função o argumento é numero_venda de venda
SELECT calcular_valor_venda(1);

-- que loucura


DELIMITER //

CREATE TRIGGER trg_insert_nf_comissionamento
BEFORE INSERT ON NF_de_comissionamento
FOR EACH ROW
BEGIN
    DECLARE valor_venda_temp DOUBLE;
    DECLARE equipe_vendas_cpf VARCHAR(11);

    -- Inicializar variáveis
    SET valor_venda_temp = NULL;
    SET equipe_vendas_cpf = NULL;

    -- Obter valor da venda da tabela Venda
    SELECT valor_venda INTO valor_venda_temp
    FROM Venda
    WHERE numero_venda = NEW.fk_Venda_numero_venda;

    -- Verificar se valor_venda_temp foi encontrado
    IF valor_venda_temp IS NULL THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Valor da venda não encontrado';
    END IF;

    -- Obter o CPF da equipe de vendas da tabela atende
    SELECT a.fk_Equipe_de_vendas_fk_Funcionarios_cpf INTO equipe_vendas_cpf
    FROM atende a
    JOIN fazVendaProduto fvp ON a.numero_atendimento = fvp.fk_atende_numero_atendimento
    WHERE fvp.fk_Venda_numero_venda = NEW.fk_Venda_numero_venda
    LIMIT 1;

    -- Verificar se equipe_vendas_cpf foi encontrado
    IF equipe_vendas_cpf IS NULL THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'CPF da equipe de vendas não encontrado';
    END IF;

    -- Preencher os atributos restantes
    SET NEW.calculo_do_imposto = valor_venda_temp + (valor_venda_temp * 0.10);
    SET NEW.data_emissao = CURDATE();
    SET NEW.fk_Equipe_de_vendas_fk_Funcionarios_cpf = equipe_vendas_cpf;
    SET NEW.serie = 1;
    SET NEW.data_saida = DATE_ADD(CURDATE(), INTERVAL 10 DAY);

END;
//

DELIMITER ;


-- inserção dos dados de nf de comissionamentp após o trigger

INSERT INTO NF_de_comissionamento (fk_Contadores_fk_Funcionarios_cpf, fk_Venda_numero_venda)VALUES 
('66666666666', 1);

INSERT INTO NF_de_comissionamento (fk_Contadores_fk_Funcionarios_cpf, fk_Venda_numero_venda) VALUES
('66666666666', 2);

INSERT INTO NF_de_comissionamento (fk_Contadores_fk_Funcionarios_cpf, fk_Venda_numero_venda) VALUES
('77777777777', 3);

INSERT INTO NF_de_comissionamento (fk_Contadores_fk_Funcionarios_cpf, fk_Venda_numero_venda) VALUES
('77777777777', 4);

INSERT INTO NF_de_comissionamento (fk_Contadores_fk_Funcionarios_cpf, fk_Venda_numero_venda) VALUES
('77777777777', 5);

SELECT * FROM NF_de_comissionamento WHERE numero = 1;