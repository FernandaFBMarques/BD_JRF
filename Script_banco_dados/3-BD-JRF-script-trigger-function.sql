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

INSERT INTO fazvendaproduto (fk_Venda_numero_venda, fk_Produtos_codigo_de_barras, fk_atende_numero_atendimento, quantidade_de_produto) VALUES
(1, '1234567890133', 1, 3);

-- roda o select para verificar a alteração do valor 
SELECT calcular_valor_venda(1);
