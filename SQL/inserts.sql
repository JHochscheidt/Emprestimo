/* INSERT SETORES */
INSERT INTO `emprestimo`.`setor` (`nome`) 
VALUES 	('ADMINISTRATIVO'), 	
		('FINANCEIRO'), 
		('RECURSOS HUMANOS'), 
		('COMERCIAL'), 
        ('JURIDICO');
        
/*INSERT CARGOS */
INSERT INTO `emprestimo`.`cargo` (`nome`, `setor_codigo`)
VALUES 	('GERENTE',1),
		('COORDENADOR',1),
		('DIRETOR',1),
		('SUPERVISOR',1),
		('DIRETOR FINANCEIRO',2),
		('GERENTE DE AUDITORIA',2),
		('AUDITOR',2),
		('CONTADOR',2),
		('DIRETOR DE RECURSOS HUMANOS',3),
		('GERENTE DE RECURSOS HUMANOS',3),
		('ANALISTA DE RECURSOS HUMANOS',3),
		('VENDEDOR',4),
		('GERENTE COMERCIAL',4),
		('PROMOTOR DE VENDAS',4),
		('ANALISTA JURÍDICO',5),
		('ADVOGADO',5),
		('ASSESSOR JURÍDICO',5);
        
/*INSERTS FUNCIONARIOS */
INSERT INTO `emprestimo`.`funcionario`(`nome`,`cpf`,`dataAdmissao`,`observacao`,`setor_codigo`,`cargo_codigo`)
VALUES 	('FUNCIONÁRIO1','111.111.111-11','2017-07-17','Teste F1',1,1),
		('FUNCIONÁRIO2','222.222.222-22','2017-07-17','Teste F2',2,5),
		('FUNCIONÁRIO3','333.333.333-33','2017-07-17','Teste F3',3,9),
		('FUNCIONÁRIO4','444.444.444-44','2017-07-17','Teste F4',4,13),
		('FUNCIONÁRIO5','555.555.555-55','2017-07-17','Teste F5',5,17);

