/*==============================================================*/
/* DBMS name:      PostgreSQL 8                                 */
/* Created on:     07/03/2016 09:13:29                          */
/*==============================================================*/


drop index IDX_CODBANCO;

drop table BANCO;

drop index IDX_IDCLIENTE;

drop table CLIENTE;

drop index IDX_IDCLIENTECONTRATO;

drop table CLIENTECONTRATO;

drop index IDX_IDCLIENTELUTA;

drop table CLIENTELUTA;

drop index IDX_IDCLIENTESITUACAO;

drop table CLIENTESITUACAO;

drop index IDX_IDCONTRATOSITUACAO;

drop table CONTRATOSITUACAO;

drop index IDX_CDGRUPO;

drop table GRUPO;

drop index IDX_IDLOGPEDIDOSITUACAO;

drop table LOGPEDIDOSITUACAO;

drop index IDX_IDMODALIDADELUTA;

drop table MOLIDADELUTA;

drop index IDX_NUMCPFCNPJ;

drop index IDX_IDPESSOA;

drop table PESSOA;

drop index IDX_IDPESSOAACADEMIA;

drop table PESSOAACADEMIA;

drop index IDX_IDPESSOACONTA;

drop table PESSOACONTA;

drop index IDX_IDPESSOAENDERECO;

drop table PESSOAENDERECO;

drop index IDX_IDPESSOAINDICACAO;

drop table PESSOAINDICACAO;

drop index IDX_IDPESSOATELEFONE;

drop table PESSOATELEFONE;

drop index IDX_IDPLANOASSINATURA;

drop table PLANOASSINATURA;

drop index IDX_IDPLANOPAGAMENTO;

drop table PLANOPAGAMENTO;

drop index IDX_IDSITUACAOPARCELA;

drop table SITUACAOPARCELA;

drop index IDX_IDSTATUSPAGAMENTO;

drop table STATUSPAGAMENTO;

drop index IDX_IDTIPOCONTA;

drop table TIPOCONTA;

drop index IDX_IDTIPOENDERECO;

drop table TIPOENDERECO;

drop index IDX_IDTIPOTELEFONE;

drop table TIPOTELEFONE;

drop index IDX_CODUF;

drop table UF;

drop index IDX_UNQ_DSUSUARIO;

drop index IDX_IDUSUARIO;

drop table USUARIO;

drop index IDX_IDUSUARIOGRUPO;

drop table USUARIOGRUPO;

drop index IDX_IDUSUARIOPESSOA;

drop table USUARIOPESSOA;

drop sequence SEQCLIENTECONTRATO;

drop sequence SEQCLIENTELUTA;

drop sequence SEQLOGPEDIDOSITUACAO;

drop sequence SEQPESSOA;

drop sequence SEQPESSOACONTA;

drop sequence SEQPESSOAENDERECO;

drop sequence SEQPESSOAREDE;

drop sequence SEQPLANOASSINATURA;

drop sequence SEQTELEFONE;

drop sequence SEQUSUARIO;

drop sequence SEQUSUARIOGRUPO;

drop sequence SEQUSUARIOPESSOA;

create sequence SEQCLIENTECONTRATO
increment 1;

create sequence SEQCLIENTELUTA
increment 1;

create sequence SEQLOGPEDIDOSITUACAO
increment 1;

create sequence SEQPESSOA
increment 1;

create sequence SEQPESSOACONTA
increment 1;

create sequence SEQPESSOAENDERECO
increment 1;

create sequence SEQPESSOAREDE
increment 1;

create sequence SEQPLANOASSINATURA
increment 1;

create sequence SEQTELEFONE
increment 1;

create sequence SEQUSUARIO
increment 1;

create sequence SEQUSUARIOGRUPO
increment 1;

create sequence SEQUSUARIOPESSOA
increment 1;

/*==============================================================*/
/* Table: BANCO                                                 */
/*==============================================================*/
create table BANCO (
   CODBANCO             INT4                 not null,
   DSBANCO              VARCHAR(40)          not null,
   constraint PK_BANCO primary key (CODBANCO)
);

/*==============================================================*/
/* Index: IDX_CODBANCO                                          */
/*==============================================================*/
create unique index IDX_CODBANCO on BANCO (
CODBANCO
);

/*==============================================================*/
/* Table: CLIENTE                                               */
/*==============================================================*/
create table CLIENTE (
   IDCLIENTE            BIGINT               not null,
   IDCLIENTELUTA        BIGINT               null,
   IDCLIENTESITUACAO    BIGINT               not null,
   IDPESSOAINDICACAO    BIGINT               null,
   DATAATUALIZACAO      TIMESTAMP            not null,
   constraint PK_CLIENTE primary key (IDCLIENTE)
);

/*==============================================================*/
/* Index: IDX_IDCLIENTE                                         */
/*==============================================================*/
create unique index IDX_IDCLIENTE on CLIENTE (
IDCLIENTE
);

/*==============================================================*/
/* Table: CLIENTECONTRATO                                       */
/*==============================================================*/
create table CLIENTECONTRATO (
   IDCLIENTECONTRATO    BIGINT               not null,
   IDCLIENTE            BIGINT               null,
   IDCONTRATOSITUACAO   BIGINT               null,
   IDPLANOASSINATURA    BIGINT               null,
   DIAVENCIMENTOPARCELA BIGINT               null,
   VLCONTRATO           NUMERIC(12,2)        null,
   VLPARCELA            NUMERIC(12,2)        null,
   QTDPARCELA           BIGINT               null,
   DATAINICIOCONTRATO   TIMESTAMP            null,
   DATAFIMCONTRATO      TIMESTAMP            null,
   constraint PK_CLIENTECONTRATO primary key (IDCLIENTECONTRATO)
);

/*==============================================================*/
/* Index: IDX_IDCLIENTECONTRATO                                 */
/*==============================================================*/
create  index IDX_IDCLIENTECONTRATO on CLIENTECONTRATO (
IDCLIENTECONTRATO
);

/*==============================================================*/
/* Table: CLIENTELUTA                                           */
/*==============================================================*/
create table CLIENTELUTA (
   IDCLIENTELUTA        BIGINT               not null,
   IDMODALIDADELUTA     BIGINT               null,
   IDPESSOAACADEMIA     BIGINT               null,
   DSGRADUACAO          VARCHAR(40)          null,
   DATAINICIOACADEMIA   TIMESTAMP            null,
   constraint PK_CLIENTELUTA primary key (IDCLIENTELUTA)
);

comment on column CLIENTELUTA.DSGRADUACAO is
'Descreve a graduação/faixa atual do cliente';

/*==============================================================*/
/* Index: IDX_IDCLIENTELUTA                                     */
/*==============================================================*/
create unique index IDX_IDCLIENTELUTA on CLIENTELUTA (
IDCLIENTELUTA
);

/*==============================================================*/
/* Table: CLIENTESITUACAO                                       */
/*==============================================================*/
create table CLIENTESITUACAO (
   IDCLIENTESITUACAO    BIGINT               not null,
   DSCLIENTESITUACAO    VARCHAR(30)          not null,
   constraint PK_CLIENTESITUACAO primary key (IDCLIENTESITUACAO)
);

comment on table CLIENTESITUACAO is
'CADASTRADO, PENDENTE, ATIVO, INATIVO, BLOQUEADO';

/*==============================================================*/
/* Index: IDX_IDCLIENTESITUACAO                                 */
/*==============================================================*/
create unique index IDX_IDCLIENTESITUACAO on CLIENTESITUACAO (
IDCLIENTESITUACAO
);

/*==============================================================*/
/* Table: CONTRATOSITUACAO                                      */
/*==============================================================*/
create table CONTRATOSITUACAO (
   IDCONTRATOSITUACAO   BIGINT               not null,
   DSCONTRATOSITUACAO   VARCHAR(40)          null,
   constraint PK_CONTRATOSITUACAO primary key (IDCONTRATOSITUACAO)
);

/*==============================================================*/
/* Index: IDX_IDCONTRATOSITUACAO                                */
/*==============================================================*/
create unique index IDX_IDCONTRATOSITUACAO on CONTRATOSITUACAO (
IDCONTRATOSITUACAO
);

/*==============================================================*/
/* Table: GRUPO                                                 */
/*==============================================================*/
create table GRUPO (
   CDGRUPO              VARCHAR(50)          not null,
   constraint PK_GRUPO primary key (CDGRUPO)
);

/*==============================================================*/
/* Index: IDX_CDGRUPO                                           */
/*==============================================================*/
create  index IDX_CDGRUPO on GRUPO (
CDGRUPO
);

/*==============================================================*/
/* Table: LOGPEDIDOSITUACAO                                     */
/*==============================================================*/
create table LOGPEDIDOSITUACAO (
   IDLOGPEDIDOSITUACAO  SERIAL               not null,
   IDPLANOPAGAMENTO     BIGINT               not null,
   IDSTATUSPAGAMENTO    BIGINT               null,
   CODTRANSACAO         VARCHAR(100)         null,
   DATAHORAPEDIDOSITUACAO TIMESTAMP            null,
   constraint PK_LOGPEDIDOSITUACAO primary key (IDLOGPEDIDOSITUACAO)
);

comment on table LOGPEDIDOSITUACAO is
'Se foi Em mãos, PagSeguro, etc.';

/*==============================================================*/
/* Index: IDX_IDLOGPEDIDOSITUACAO                               */
/*==============================================================*/
create unique index IDX_IDLOGPEDIDOSITUACAO on LOGPEDIDOSITUACAO (
IDLOGPEDIDOSITUACAO
);

/*==============================================================*/
/* Table: MOLIDADELUTA                                          */
/*==============================================================*/
create table MOLIDADELUTA (
   IDMODALIDADELUTA     BIGINT               not null,
   DSMODALIDADELUTA     VARCHAR(40)          null,
   constraint PK_MOLIDADELUTA primary key (IDMODALIDADELUTA)
);

/*==============================================================*/
/* Index: IDX_IDMODALIDADELUTA                                  */
/*==============================================================*/
create unique index IDX_IDMODALIDADELUTA on MOLIDADELUTA (
IDMODALIDADELUTA
);

/*==============================================================*/
/* Table: PESSOA                                                */
/*==============================================================*/
create table PESSOA (
   IDPESSOA             BIGINT               not null,
   IDPESSOACONTA        BIGINT               null,
   NOMEPESSOA           VARCHAR(50)          not null,
   DSRAZAOSOCIAL        VARCHAR(50)          null,
   CODTIPOPESSOA        CHAR(1)              not null
      constraint CKC_CODTIPOPESSOA_PESSOA check (CODTIPOPESSOA in ('F','J')),
   NUMCPFCNPJ           VARCHAR(14)          not null,
   CODSEXO              CHAR(1)              null
      constraint CKC_CODSEXO_PESSOA check (CODSEXO is null or (CODSEXO in ('M','F'))),
   DATANASCIMENTO       DATE                 null,
   DATACADASTRO         DATE                 not null,
   DSEMAIL              VARCHAR(50)          null,
   constraint PK_PESSOA primary key (IDPESSOA)
);

/*==============================================================*/
/* Index: IDX_IDPESSOA                                          */
/*==============================================================*/
create  index IDX_IDPESSOA on PESSOA (
IDPESSOA
);

/*==============================================================*/
/* Index: IDX_NUMCPFCNPJ                                        */
/*==============================================================*/
create unique index IDX_NUMCPFCNPJ on PESSOA (
NUMCPFCNPJ
);

/*==============================================================*/
/* Table: PESSOAACADEMIA                                        */
/*==============================================================*/
create table PESSOAACADEMIA (
   IDPESSOAACADEMIA     BIGINT               not null,
   constraint PK_PESSOAACADEMIA primary key (IDPESSOAACADEMIA)
);

/*==============================================================*/
/* Index: IDX_IDPESSOAACADEMIA                                  */
/*==============================================================*/
create  index IDX_IDPESSOAACADEMIA on PESSOAACADEMIA (
IDPESSOAACADEMIA
);

/*==============================================================*/
/* Table: PESSOACONTA                                           */
/*==============================================================*/
create table PESSOACONTA (
   IDPESSOACONTA        BIGINT               not null,
   IDPESSOA             BIGINT               not null,
   IDTIPOCONTA          BIGINT               null,
   CODBANCO             INT4                 not null,
   NUMAGENCIA           VARCHAR(10)          not null,
   NUMCONTA             BIGINT               not null,
   BOLCONTAPRINCIPAL    BOOL                 not null,
   constraint PK_PESSOACONTA primary key (IDPESSOACONTA)
);

/*==============================================================*/
/* Index: IDX_IDPESSOACONTA                                     */
/*==============================================================*/
create  index IDX_IDPESSOACONTA on PESSOACONTA (
IDPESSOACONTA
);

/*==============================================================*/
/* Table: PESSOAENDERECO                                        */
/*==============================================================*/
create table PESSOAENDERECO (
   IDPESSOAENDERECO     BIGINT               not null,
   IDPESSOA             BIGINT               not null,
   IDTIPOENDERECO       BIGINT               not null,
   DSENDERECO           VARCHAR(50)          not null,
   DSNUMERO             VARCHAR(10)          not null,
   DSCOMPLEMENTO        VARCHAR(50)          null,
   DSBAIRRO             VARCHAR(40)          not null,
   DSCIDADE             VARCHAR(40)          not null,
   NUMCEP               VARCHAR(10)          null,
   CODUF                VARCHAR(2)           not null,
   constraint PK_PESSOAENDERECO primary key (IDPESSOAENDERECO)
);

/*==============================================================*/
/* Index: IDX_IDPESSOAENDERECO                                  */
/*==============================================================*/
create unique index IDX_IDPESSOAENDERECO on PESSOAENDERECO (
IDPESSOAENDERECO
);

/*==============================================================*/
/* Table: PESSOAINDICACAO                                       */
/*==============================================================*/
create table PESSOAINDICACAO (
   IDPESSOAINDICACAO    BIGINT               not null,
   constraint PK_PESSOAINDICACAO primary key (IDPESSOAINDICACAO)
);

/*==============================================================*/
/* Index: IDX_IDPESSOAINDICACAO                                 */
/*==============================================================*/
create  index IDX_IDPESSOAINDICACAO on PESSOAINDICACAO (
IDPESSOAINDICACAO
);

/*==============================================================*/
/* Table: PESSOATELEFONE                                        */
/*==============================================================*/
create table PESSOATELEFONE (
   IDPESSOATELEFONE     BIGINT               not null,
   IDTIPOTELEFONE       BIGINT               null,
   IDPESSOA             BIGINT               not null,
   DSTELEFONE           VARCHAR(15)          not null,
   constraint PK_PESSOATELEFONE primary key (IDPESSOATELEFONE)
);

/*==============================================================*/
/* Index: IDX_IDPESSOATELEFONE                                  */
/*==============================================================*/
create unique index IDX_IDPESSOATELEFONE on PESSOATELEFONE (
IDPESSOATELEFONE
);

/*==============================================================*/
/* Table: PLANOASSINATURA                                       */
/*==============================================================*/
create table PLANOASSINATURA (
   IDPLANOASSINATURA    BIGINT               not null,
   NOMEPLANOASSINATURA  VARCHAR(30)          not null,
   DSPLANOASSINATURA    TEXT                 null,
   VLPLANOASSINATURA    NUMERIC(12,2)        null,
   BOLATIVO             BOOL                 not null,
   constraint PK_PLANOASSINATURA primary key (IDPLANOASSINATURA)
);

comment on column PLANOASSINATURA.DSPLANOASSINATURA is
'DESCRIÇÃO DETALHADA DO PLANO';

/*==============================================================*/
/* Index: IDX_IDPLANOASSINATURA                                 */
/*==============================================================*/
create unique index IDX_IDPLANOASSINATURA on PLANOASSINATURA (
IDPLANOASSINATURA
);

/*==============================================================*/
/* Table: PLANOPAGAMENTO                                        */
/*==============================================================*/
create table PLANOPAGAMENTO (
   IDPLANOPAGAMENTO     BIGINT               not null,
   IDCLIENTECONTRATO    BIGINT               null,
   IDSITUACAOPARCELA    BIGINT               null,
   DATAVENCIMENTOPARCELA TIMESTAMP            null,
   VALORPARCELA         NUMERIC(12,2)        null,
   DATAPAGAMENTOPARCELA TIMESTAMP            null,
   VALORPAGO            NUMERIC(12,2)        null,
   constraint PK_PLANOPAGAMENTO primary key (IDPLANOPAGAMENTO)
);

/*==============================================================*/
/* Index: IDX_IDPLANOPAGAMENTO                                  */
/*==============================================================*/
create unique index IDX_IDPLANOPAGAMENTO on PLANOPAGAMENTO (
IDPLANOPAGAMENTO
);

/*==============================================================*/
/* Table: SITUACAOPARCELA                                       */
/*==============================================================*/
create table SITUACAOPARCELA (
   IDSITUACAOPARCELA    BIGINT               not null,
   DSSITUACAOPARCELA    VARCHAR(40)          null,
   constraint PK_SITUACAOPARCELA primary key (IDSITUACAOPARCELA)
);

/*==============================================================*/
/* Index: IDX_IDSITUACAOPARCELA                                 */
/*==============================================================*/
create unique index IDX_IDSITUACAOPARCELA on SITUACAOPARCELA (
IDSITUACAOPARCELA
);

/*==============================================================*/
/* Table: STATUSPAGAMENTO                                       */
/*==============================================================*/
create table STATUSPAGAMENTO (
   IDSTATUSPAGAMENTO    BIGINT               not null,
   DSSTATUSPAGAMENTO    VARCHAR(100)         null,
   constraint PK_STATUSPAGAMENTO primary key (IDSTATUSPAGAMENTO)
);

/*==============================================================*/
/* Index: IDX_IDSTATUSPAGAMENTO                                 */
/*==============================================================*/
create unique index IDX_IDSTATUSPAGAMENTO on STATUSPAGAMENTO (
IDSTATUSPAGAMENTO
);

/*==============================================================*/
/* Table: TIPOCONTA                                             */
/*==============================================================*/
create table TIPOCONTA (
   IDTIPOCONTA          BIGINT               not null,
   DSTIPOCONTA          VARCHAR(30)          not null,
   constraint PK_TIPOCONTA primary key (IDTIPOCONTA)
);

/*==============================================================*/
/* Index: IDX_IDTIPOCONTA                                       */
/*==============================================================*/
create  index IDX_IDTIPOCONTA on TIPOCONTA (
IDTIPOCONTA
);

/*==============================================================*/
/* Table: TIPOENDERECO                                          */
/*==============================================================*/
create table TIPOENDERECO (
   IDTIPOENDERECO       BIGINT               not null,
   DSTIPOENDERECO       VARCHAR(30)          not null,
   constraint PK_TIPOENDERECO primary key (IDTIPOENDERECO)
);

/*==============================================================*/
/* Index: IDX_IDTIPOENDERECO                                    */
/*==============================================================*/
create unique index IDX_IDTIPOENDERECO on TIPOENDERECO (
IDTIPOENDERECO
);

/*==============================================================*/
/* Table: TIPOTELEFONE                                          */
/*==============================================================*/
create table TIPOTELEFONE (
   IDTIPOTELEFONE       BIGINT               not null,
   DSTIPOTELEFONE       VARCHAR(30)          not null,
   constraint PK_TIPOTELEFONE primary key (IDTIPOTELEFONE)
);

/*==============================================================*/
/* Index: IDX_IDTIPOTELEFONE                                    */
/*==============================================================*/
create unique index IDX_IDTIPOTELEFONE on TIPOTELEFONE (
IDTIPOTELEFONE
);

/*==============================================================*/
/* Table: UF                                                    */
/*==============================================================*/
create table UF (
   CODUF                VARCHAR(2)           not null,
   DSUF                 VARCHAR(40)          not null,
   constraint PK_UF primary key (CODUF)
);

/*==============================================================*/
/* Index: IDX_CODUF                                             */
/*==============================================================*/
create unique index IDX_CODUF on UF (
CODUF
);

/*==============================================================*/
/* Table: USUARIO                                               */
/*==============================================================*/
create table USUARIO (
   IDUSUARIO            BIGINT               not null,
   DSUSUARIO            VARCHAR(150)         not null,
   DSSENHA              VARCHAR(150)         not null,
   constraint PK_USUARIO primary key (IDUSUARIO)
);

/*==============================================================*/
/* Index: IDX_IDUSUARIO                                         */
/*==============================================================*/
create  index IDX_IDUSUARIO on USUARIO (
IDUSUARIO
);

/*==============================================================*/
/* Index: IDX_UNQ_DSUSUARIO                                     */
/*==============================================================*/
create unique index IDX_UNQ_DSUSUARIO on USUARIO (
DSUSUARIO
);

/*==============================================================*/
/* Table: USUARIOGRUPO                                          */
/*==============================================================*/
create table USUARIOGRUPO (
   IDUSUARIOGRUPO       BIGINT               not null,
   CDGRUPO              VARCHAR(50)          not null,
   IDUSUARIO            BIGINT               not null,
   constraint PK_USUARIOGRUPO primary key (IDUSUARIOGRUPO)
);

/*==============================================================*/
/* Index: IDX_IDUSUARIOGRUPO                                    */
/*==============================================================*/
create  index IDX_IDUSUARIOGRUPO on USUARIOGRUPO (
IDUSUARIOGRUPO
);

/*==============================================================*/
/* Table: USUARIOPESSOA                                         */
/*==============================================================*/
create table USUARIOPESSOA (
   IDUSUARIOPESSOA      BIGINT               not null,
   IDPESSOA             BIGINT               not null,
   IDUSUARIO            BIGINT               not null,
   constraint PK_USUARIOPESSOA primary key (IDUSUARIOPESSOA)
);

/*==============================================================*/
/* Index: IDX_IDUSUARIOPESSOA                                   */
/*==============================================================*/
create  index IDX_IDUSUARIOPESSOA on USUARIOPESSOA (
IDUSUARIOPESSOA
);

alter table CLIENTE
   add constraint FK_CLIENTE_FK_CLIENT_CLIENTEL foreign key (IDCLIENTELUTA)
      references CLIENTELUTA (IDCLIENTELUTA)
      on delete restrict on update restrict;

alter table CLIENTE
   add constraint FK_CLIENTE_FK_CLIENT_CLIENTES foreign key (IDCLIENTESITUACAO)
      references CLIENTESITUACAO (IDCLIENTESITUACAO)
      on delete restrict on update restrict;

alter table CLIENTE
   add constraint FK_CLIENTE_FK_CLIENT_PESSOA foreign key (IDCLIENTE)
      references PESSOA (IDPESSOA)
      on delete restrict on update restrict;

alter table CLIENTE
   add constraint FK_CLIENTE_FK_CLIENT_PESSOAIN foreign key (IDPESSOAINDICACAO)
      references PESSOAINDICACAO (IDPESSOAINDICACAO)
      on delete restrict on update restrict;

alter table CLIENTECONTRATO
   add constraint FK_CLIENTEC_FK_CLIENT_CLIENTE foreign key (IDCLIENTE)
      references CLIENTE (IDCLIENTE)
      on delete restrict on update restrict;

alter table CLIENTECONTRATO
   add constraint FK_CLIENTEC_FK_CLIENT_CONTRATO foreign key (IDCONTRATOSITUACAO)
      references CONTRATOSITUACAO (IDCONTRATOSITUACAO)
      on delete restrict on update restrict;

alter table CLIENTECONTRATO
   add constraint FK_CLIENTEC_FK_CLIENT_PLANOASS foreign key (IDPLANOASSINATURA)
      references PLANOASSINATURA (IDPLANOASSINATURA)
      on delete restrict on update restrict;

alter table CLIENTELUTA
   add constraint FK_CLIENTEL_FK_CLIENT_MOLIDADE foreign key (IDMODALIDADELUTA)
      references MOLIDADELUTA (IDMODALIDADELUTA)
      on delete restrict on update restrict;

alter table CLIENTELUTA
   add constraint FK_CLIENTEL_FK_CLIENT_PESSOAAC foreign key (IDPESSOAACADEMIA)
      references PESSOAACADEMIA (IDPESSOAACADEMIA)
      on delete restrict on update restrict;

alter table LOGPEDIDOSITUACAO
   add constraint FK_LOGPEDID_FK_LOGPED_PLANOPAG foreign key (IDPLANOPAGAMENTO)
      references PLANOPAGAMENTO (IDPLANOPAGAMENTO)
      on delete restrict on update restrict;

alter table LOGPEDIDOSITUACAO
   add constraint FK_LOGPEDID_FK_LOGPED_STATUSPA foreign key (IDSTATUSPAGAMENTO)
      references STATUSPAGAMENTO (IDSTATUSPAGAMENTO)
      on delete restrict on update restrict;

alter table PESSOA
   add constraint FK_PESSOA_FK_PESSOA_PESSOACO foreign key (IDPESSOACONTA)
      references PESSOACONTA (IDPESSOACONTA)
      on delete restrict on update restrict;

alter table PESSOAACADEMIA
   add constraint FK_PESSOAAC_FK_PESSOA_PESSOA foreign key (IDPESSOAACADEMIA)
      references PESSOA (IDPESSOA)
      on delete restrict on update restrict;

alter table PESSOACONTA
   add constraint FK_PESSOACO_FK_PESSOA_BANCO foreign key (CODBANCO)
      references BANCO (CODBANCO)
      on delete restrict on update restrict;

alter table PESSOACONTA
   add constraint FK_PESSOACO_FK_PESSOA_TIPOCONT foreign key (IDTIPOCONTA)
      references TIPOCONTA (IDTIPOCONTA)
      on delete restrict on update restrict;

alter table PESSOAENDERECO
   add constraint FK_PESSOAEN_FK_PESSOA_PESSOA foreign key (IDPESSOA)
      references PESSOA (IDPESSOA)
      on delete restrict on update restrict;

alter table PESSOAENDERECO
   add constraint FK_PESSOAEN_FK_PESSOA_TIPOENDE foreign key (IDTIPOENDERECO)
      references TIPOENDERECO (IDTIPOENDERECO)
      on delete restrict on update restrict;

alter table PESSOAENDERECO
   add constraint FK_PESSOAEN_FK_PESSOA_UF foreign key (CODUF)
      references UF (CODUF)
      on delete restrict on update restrict;

alter table PESSOAINDICACAO
   add constraint FK_PESSOAIN_FK_PESSOA_PESSOA foreign key (IDPESSOAINDICACAO)
      references PESSOA (IDPESSOA)
      on delete restrict on update restrict;

alter table PESSOATELEFONE
   add constraint FK_PESSOATE_FK_PESSOA_PESSOA foreign key (IDPESSOA)
      references PESSOA (IDPESSOA)
      on delete restrict on update restrict;

alter table PESSOATELEFONE
   add constraint FK_PESSOATE_FK_PESSOA_TIPOTELE foreign key (IDTIPOTELEFONE)
      references TIPOTELEFONE (IDTIPOTELEFONE)
      on delete restrict on update restrict;

alter table PLANOPAGAMENTO
   add constraint FK_PLANOPAG_FK_PLANOP_CLIENTEC foreign key (IDCLIENTECONTRATO)
      references CLIENTECONTRATO (IDCLIENTECONTRATO)
      on delete restrict on update restrict;

alter table PLANOPAGAMENTO
   add constraint FK_PLANOPAG_FK_PLANOP_SITUACAO foreign key (IDSITUACAOPARCELA)
      references SITUACAOPARCELA (IDSITUACAOPARCELA)
      on delete restrict on update restrict;

alter table USUARIOGRUPO
   add constraint FK_USUARIOGRUPO_GRUPO foreign key (CDGRUPO)
      references GRUPO (CDGRUPO)
      on delete restrict on update restrict;

alter table USUARIOGRUPO
   add constraint FK_USUARIOG_FK_USUARI_USUARIO foreign key (IDUSUARIO)
      references USUARIO (IDUSUARIO)
      on delete restrict on update restrict;

alter table USUARIOPESSOA
   add constraint FK_USUARIOP_FK_USUARI_PESSOA foreign key (IDPESSOA)
      references PESSOA (IDPESSOA)
      on delete restrict on update restrict;

alter table USUARIOPESSOA
   add constraint FK_USUARIOPESSOA_USUARIO foreign key (IDUSUARIO)
      references USUARIO (IDUSUARIO)
      on delete restrict on update restrict;

