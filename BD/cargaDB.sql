INSERT INTO uf values ('AC','Acre (AC)');
INSERT INTO uf values ('AL','Alagoas (AL)');
INSERT INTO uf values ('AP','Amapá (AP)');
INSERT INTO uf values ('AM','Amazonas (AM)');
INSERT INTO uf values ('BA','Bahia (BA)');
INSERT INTO uf values ('CE','Ceará (CE)');
INSERT INTO uf values ('DF','Distrito Federal (DF)');
INSERT INTO uf values ('ES','Espírito Santo (ES)');
INSERT INTO uf values ('GO','Goiás (GO)');
INSERT INTO uf values ('MA','Maranhão (MA)');
INSERT INTO uf values ('MT','Mato Grosso (MT)');
INSERT INTO uf values ('MS','Mato Grosso do Sul (MS)');
INSERT INTO uf values ('MG','Minas Gerais (MG)');
INSERT INTO uf values ('PA','Pará (PA) ');
INSERT INTO uf values ('PB','Paraíba (PB)');
INSERT INTO uf values ('PR','Paraná (PR)');
INSERT INTO uf values ('PE','Pernambuco (PE)');
INSERT INTO uf values ('PI','Piauí (PI)');
INSERT INTO uf values ('RJ','Rio de Janeiro (RJ)');
INSERT INTO uf values ('RN','Rio Grande do Norte (RN)');
INSERT INTO uf values ('RS','Rio Grande do Sul (RS)');
INSERT INTO uf values ('RO','Rondônia (RO)');
INSERT INTO uf values ('RR','Roraima (RR)');
INSERT INTO uf values ('SC','Santa Catarina (SC)');
INSERT INTO uf values ('SP','São Paulo (SP)');
INSERT INTO uf values ('SE','Sergipe (SE)');
INSERT INTO uf values ('TO','Tocantins (TO)');

INSERT INTO tipoendereco values (1, 'Residencial');
INSERT INTO tipoendereco values (2, 'Comercial');

INSERT INTO tipotelefone values (1, 'Residencial');
INSERT INTO tipotelefone values (2, 'Celular');
INSERT INTO tipotelefone values (3, 'Comercial');

--TipoConta
INSERT INTO tipoconta values (1, 'Conta Corrente');
INSERT INTO tipoconta values (2, 'Poupança');

--Banco
insert into banco values (001, 'Banco do Brasil');
insert into banco values (033, 'Santander');
insert into banco values (104, 'Caixa Econômica Federal');
insert into banco values (237, 'Bradesco');
insert into banco values (341, 'Itaú Unibanco');
insert into banco values (070, 'BRB');
insert into banco values (756, 'Banco Cooperativo do Brasil');

--ModalidadeLuta
insert into modalidadeluta values (1, 'Jiu-Jítsu');
insert into modalidadeluta values (2, 'Boxe');
insert into modalidadeluta values (3, 'Judo');
insert into modalidadeluta values (4, 'Muay Tai');
insert into modalidadeluta values (5, 'MMA');
insert into modalidadeluta values (6, 'Karate');
insert into modalidadeluta values (7, 'Wrestling');
insert into modalidadeluta values (8, 'Kickboxing');
insert into modalidadeluta values (9, 'Capoeira');

--SituacaoParcela
insert into situacaoparcela values (1,'Aberto');
insert into situacaoparcela values (2,'Cancelado');
insert into situacaoparcela values (3,'Baixado');
insert into situacaoparcela values (4,'Liquidado');

--StatusPagamento (PagSeguro)
insert into statuspagamento values (1,'Aguardando pagamento');
insert into statuspagamento values (2,'Em análise');
insert into statuspagamento values (3,'Pago');
insert into statuspagamento values (4,'Disponível');
insert into statuspagamento values (5,'Em disputa');
insert into statuspagamento values (6,'Devolvida');
insert into statuspagamento values (7,'Cancelada');

--Situacao
insert into situacao values (1,'Cadastrado');
insert into situacao values (2,'Ativo');
insert into situacao values (3,'Inativo');
insert into situacao values (4,'Bloqueado');
insert into situacao values (5,'Aguardando Pagamento');

--ContratoSituacao
insert into contratosituacao values (1,'Vigente');
insert into contratosituacao values (2,'Finalizado');
insert into contratosituacao values (3,'Cancelado');
insert into contratosituacao values (4,'Bloqueado');

--Parentesco
insert into parentesco values (1,'Esposo(a)');
insert into parentesco values (2,'Filho(a)');
insert into parentesco values (3,'Pai');
insert into parentesco values (4,'Mãe');
insert into parentesco values (5,'Outros');


--GRUPO
insert into grupo values ('ADMIN');
insert into grupo values ('CLIENTE');
insert into grupo values ('GESTOR');

--PlanoAssinatura
insert into planoassinatura values (nextval('seqplanoassinatura'),'Padrão 12X','Plano de 12X R$40,00',480.00,12,15,True);
insert into planoassinatura values (nextval('seqplanoassinatura'),'Padrão 3X','Plano de 3X R$160,00',480.00,3,15,True);

--INSERT PESSOA GESTOR
INSERT INTO pessoa values (nextval('seqpessoa'), null, 'Gestor Odontofight', null, 'F', '00000000000', '0000', 'SSPDF', 'M', '2000-01-01', '2000-01-01', 'gestor@odontofight.com.br');
INSERT INTO USUARIO values (nextval('sequsuario'), 'gestor@odontofight.com.br', '39dce46dfe47195cc172948533d2e2d3');--gestorodonto123
INSERT INTO usuariopessoa select nextval('sequsuariopessoa'), p.idpessoa, u.idusuario from pessoa p, usuario u where p.nomepessoa = 'Gestor Odontofight' and u.dsusuario = 'gestor@odontofight.com.br';
INSERT INTO USUARIOGRUPO select nextval('sequsuariogrupo'), 'GESTOR', u.idusuario from usuario u where u.dsusuario = 'gestor@odontofight.com.br';
INSERT INTO PESSOAINDICACAO VALUES (1, 2, now());

