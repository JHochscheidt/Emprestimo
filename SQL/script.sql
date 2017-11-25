/* CREATE DATABASE */
CREATE DATABASE `emprestimo` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

/* CREATE TABLES */
/* SETOR */
CREATE TABLE `emprestimo`.`setor` (
  `codigo` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) NOT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoparcelaDB DEFAULT CHARSET=utf8mb4;

/* CARGO */
CREATE TABLE `emprestimo`.`cargo` (
  `codigo` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) NOT NULL,
  `setor_codigo` bigint(20) NOT NULL,
  PRIMARY KEY (`codigo`),
  KEY `FKjqhv72ydbx4u1f53sqweqt2e1` (`setor_codigo`),
  CONSTRAINT `FKjqhv72ydbx4u1f53sqweqt2e1` FOREIGN KEY (`setor_codigo`) REFERENCES `setor` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/* FUNCIONARIO */
CREATE TABLE `emprestimo`.`funcionario` (
  `codigo` bigint(20) NOT NULL AUTO_INCREMENT,
  `cpf` varchar(255) NOT NULL,
  `dataAdmissao` date NOT NULL,
  `nome` varchar(255) NOT NULL,
  `observacao` varchar(255) NOT NULL,
  `cargo_codigo` bigint(20) NOT NULL,
  `setor_codigo` bigint(20) NOT NULL,
  PRIMARY KEY (`codigo`),
  UNIQUE KEY `UK_iq7jks9kmtnmm74tjfp3b80ha` (`cpf`),
  KEY `FKn0dg2ajrhmxn2f3m0bh2fe01e` (`cargo_codigo`),
  KEY `FK4tr1hqubu628f8noh624ivgok` (`setor_codigo`),
  CONSTRAINT `FK4tr1hqubu628f8noh624ivgok` FOREIGN KEY (`setor_codigo`) REFERENCES `setor` (`codigo`),
  CONSTRAINT `FKn0dg2ajrhmxn2f3m0bh2fe01e` FOREIGN KEY (`cargo_codigo`) REFERENCES `cargo` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/* EMPRESTIMO */
CREATE TABLE `emprestimo`.`emprestimo` (
  `codigo` bigint(20) NOT NULL AUTO_INCREMENT,
  `dataOperacao` date NOT NULL,
  `primeiraParcela` date NOT NULL,
  `quantidadeParcelas` int(11) NOT NULL,
  `status` varchar(25) NOT NULL,
  `valor` decimal(8,2) NOT NULL,
  `funcionario_codigo` bigint(20) NOT NULL,
  PRIMARY KEY (`codigo`),
  KEY `FKn9vhn3weoe9t5tth07idw89hg` (`funcionario_codigo`),
  CONSTRAINT `FKn9vhn3weoe9t5tth07idw89hg` FOREIGN KEY (`funcionario_codigo`) REFERENCES `funcionario` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/* PARCELA */
CREATE TABLE `emprestimo`.`parcela` (
  `codigo` bigint(20) NOT NULL AUTO_INCREMENT,
  `dataVencimento` date NOT NULL,
  `numeroParcela` int(11) NOT NULL,
  `valorPago` decimal(8,2) NOT NULL,
  `valorParcela` decimal(8,2) NOT NULL,
  `emprestimo_codigo` bigint(20) NOT NULL,
  PRIMARY KEY (`codigo`),
  KEY `FKtnisddc6ix78cucjhrpgep8k3` (`emprestimo_codigo`),
  CONSTRAINT `FKtnisddc6ix78cucjhrpgep8k3` FOREIGN KEY (`emprestimo_codigo`) REFERENCES `emprestimo` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


/* POPULANDO SETORES, CARGOS e FUNCIONARIOS */
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






