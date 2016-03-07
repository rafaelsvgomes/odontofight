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
insert into modalidadeluta values (1, 'Jiu Jitsu');
insert into modalidadeluta values (2, 'Boxe');
insert into modalidadeluta values (3, 'Judo');
insert into modalidadeluta values (4, 'Muay Tai');
insert into modalidadeluta values (5, 'MMA');
insert into modalidadeluta values (6, 'Karate');
insert into modalidadeluta values (7, 'Wrestling');
insert into modalidadeluta values (8, 'Kickboxing');
insert into modalidadeluta values (8, 'Capoeira');

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

--ClienteSituacao
insert into clientesituacao values (1,'Cadastrado');
insert into clientesituacao values (2,'Ativo');
insert into clientesituacao values (3,'Inativo');
insert into clientesituacao values (4,'Bloqueado');
insert into clientesituacao values (5,'Aguardando Pagamento');

--GRUPO
insert into grupo values ('ADMIN');
insert into grupo values ('USER');
insert into grupo values ('CLIENTE');
insert into grupo values ('GESTOR');

--INSERT PESSOA ADM
INSERT INTO pessoa values (nextval('seqpessoa'), 'Administrador', null, 'F', '00000000000', 'M', '2000-01-01', '2000-01-01', 'admin@odontofight.com.br');
INSERT INTO cliente select p.idpessoa, 1, 2, '2000-01-01' from pessoa p where p.nomepessoa = 'Administrador';
INSERT INTO usuario values (nextval('sequsuario'), 'admin', 'bdfb8ce799ed1782a38a47c8090f6941');--adminclay123
INSERT INTO usuariopessoa select nextval('sequsuariopessoa'), p.idpessoa, u.idusuario from pessoa p, usuario u where p.nomepessoa = 'Administrador' and u.dsusuario = 'admin';
INSERT INTO usuariogrupo select nextval('sequsuariogrupo'), 'ADMIN', u.idusuario from usuario u where u.dsusuario = 'admin';


