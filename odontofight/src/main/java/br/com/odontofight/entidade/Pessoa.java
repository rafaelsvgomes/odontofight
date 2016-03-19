package br.com.odontofight.entidade;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

import br.com.odontofight.enums.TipoPessoa;
import br.com.odontofight.enums.TipoSexo;

@Entity
@Table(name = "PESSOA")
@Inheritance(strategy = InheritanceType.JOINED)
@SequenceGenerator(name = "seqpessoa", sequenceName = "seqpessoa", allocationSize = 1)
@NamedQueries({
        @NamedQuery(name = Cliente.OBTER_POR_NUM_CPF_CNPJ, query = "SELECT p.id FROM Pessoa p WHERE p.numCpfCnpj = :numCpfCnpj"),
        @NamedQuery(name = Cliente.OBTER_POR_NUM_CPF_CNPJ_IGNORA_SELECIONADO, query = "SELECT p.id FROM Pessoa p WHERE p.numCpfCnpj = :numCpfCnpj and p.id not in (:idSelecionado)"), })
public abstract class Pessoa extends EntidadeGenerica {
    private static final long serialVersionUID = -8922414503953244338L;

    public static final String OBTER_POR_NUM_CPF_CNPJ = "obterPorNumCpfCnpj";
    public static final String OBTER_POR_NUM_CPF_CNPJ_IGNORA_SELECIONADO = "obterPorNumCpfCnpjIgnoraSelecionado";

    public Pessoa() {
    }

    /**
     * LISTAR_CLIENTES_INDICADORES
     */
    public Pessoa(Long id, String nomePessoa) {
        this.id = id;
        this.nomePessoa = nomePessoa;
    }

    public Pessoa(Long id, String numCpfCnpj, String nomePessoa) {
        this.id = id;
        this.numCpfCnpj = numCpfCnpj;
        this.nomePessoa = nomePessoa;
    }

    /**
     * OBTER_CLIENTE_EDITAR
     */
    public Pessoa(Long id, String nomePessoa, String descRazaoSocial, TipoPessoa tipoPessoa, String numCpfCnpj, TipoSexo tipoSexo, Date dataNascimento, String descEmail,
            Date dataCadastro) {
        this.id = id;
        this.nomePessoa = nomePessoa;
        this.descRazaoSocial = descRazaoSocial;
        this.tipoPessoa = tipoPessoa;
        this.numCpfCnpj = numCpfCnpj;
        this.tipoSexo = tipoSexo;
        this.dataNascimento = dataNascimento;
        this.descEmail = descEmail;
        this.dataCadastro = dataCadastro;
    }

    /**
     * LISTAR_CLIENTES_SIMPLES
     */
    public Pessoa(Long id, String nomePessoa, TipoPessoa tipoPessoa, String numCpfCnpj) {
        this.id = id;
        this.nomePessoa = nomePessoa;
        this.tipoPessoa = tipoPessoa;
        this.numCpfCnpj = numCpfCnpj;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqpessoa")
    @Column(name = "idPessoa", unique = true, nullable = false)
    protected Long id;

    @NotEmpty(message = "{pessoa.nome.vazio}")
    private String nomePessoa;

    @Column(name = "dsRazaoSocial")
    private String descRazaoSocial;

    @Enumerated(EnumType.STRING)
    @Column(name = "codTipoPessoa")
    private TipoPessoa tipoPessoa;

    @Column(nullable = false)
    private String numCpfCnpj;

    @Column(nullable = false)
    private String numRg;

    @Column(name = "dsorgaoEmissor", nullable = false)
    private String descOrgaoEmissorRg;

    @Enumerated(EnumType.STRING)
    @Column(name = "codSexo", nullable = false)
    private TipoSexo tipoSexo;

    @Column(name = "dataNascimento", nullable = false)
    private Date dataNascimento;

    @NotEmpty
    @Pattern(regexp = ".+@.+\\.[a-z]+", message = "{email.invalido}")
    @Column(name = "dsEmail", nullable = false)
    private String descEmail;

    @Column(name = "dataCadastro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCadastro;

    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL)
    private List<PessoaEndereco> listaEndereco;

    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL)
    private List<PessoaTelefone> listaTelefone;

    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL)
    private List<PessoaConta> listaPessoaConta;

    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL)
    private List<UsuarioPessoa> listaUsuarioPessoa;

    @Transient
    private Situacao situacao;

    @Transient
    private Date dataAtualizacao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomePessoa() {
        return nomePessoa;
    }

    public void setNomePessoa(String nomePessoa) {
        this.nomePessoa = nomePessoa;
    }

    public String getDescRazaoSocial() {
        return descRazaoSocial;
    }

    public void setDescRazaoSocial(String descRazaoSocial) {
        this.descRazaoSocial = descRazaoSocial;
    }

    public TipoPessoa getTipoPessoa() {
        return tipoPessoa;
    }

    public void setTipoPessoa(TipoPessoa tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

    public String getNumCpfCnpj() {
        return numCpfCnpj;
    }

    public void setNumCpfCnpj(String numCpfCnpj) {
        this.numCpfCnpj = numCpfCnpj;
    }

    public TipoSexo getTipoSexo() {
        return tipoSexo;
    }

    public void setTipoSexo(TipoSexo tipoSexo) {
        this.tipoSexo = tipoSexo;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getDescEmail() {
        return descEmail;
    }

    public void setDescEmail(String descEmail) {
        this.descEmail = descEmail;
    }

    public List<PessoaEndereco> getListaEndereco() {
        if (listaEndereco == null) {
            listaEndereco = new ArrayList<PessoaEndereco>();
        }
        return listaEndereco;
    }

    public void setListaEndereco(List<PessoaEndereco> listaEndereco) {
        this.listaEndereco = listaEndereco;
    }

    public List<PessoaTelefone> getListaTelefone() {
        if (listaTelefone == null) {
            listaTelefone = new ArrayList<PessoaTelefone>();
        }
        return listaTelefone;
    }

    public void setListaTelefone(List<PessoaTelefone> listaTelefone) {
        this.listaTelefone = listaTelefone;
    }

    public List<PessoaConta> getListaPessoaConta() {
        if (listaPessoaConta == null) {
            listaPessoaConta = new ArrayList<PessoaConta>();
        }
        return listaPessoaConta;
    }

    public void setListaPessoaConta(List<PessoaConta> listaPessoaConta) {
        this.listaPessoaConta = listaPessoaConta;
    }

    public List<UsuarioPessoa> getListaUsuarioPessoa() {
        if (listaUsuarioPessoa == null) {
            listaUsuarioPessoa = new ArrayList<UsuarioPessoa>();
        }
        return listaUsuarioPessoa;
    }

    public void setListaUsuarioPessoa(List<UsuarioPessoa> listaUsuarioPessoa) {
        this.listaUsuarioPessoa = listaUsuarioPessoa;
    }

    public Situacao getSituacao() {
        return situacao;
    }

    public void setSituacao(Situacao situacao) {
        this.situacao = situacao;
    }

    public Date getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(Date dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public PessoaTelefone getTelefoneCelular() {
        for (PessoaTelefone tel : this.getListaTelefone()) {
            if (tel.getTipoTelefone().getId().equals(TipoTelefone.CELULAR)) {
                return tel;
            }
        }
        return null;
    }

    public PessoaTelefone getTelefoneResidencial() {
        for (PessoaTelefone tel : this.getListaTelefone()) {
            if (tel.getTipoTelefone().getId().equals(TipoTelefone.RESIDENCIAL)) {
                return tel;
            }
        }
        return null;
    }

    public void addPessoaTelefone(PessoaTelefone telefone) {
        getListaTelefone().add(telefone);
        telefone.setPessoa(this);
    }

    public void removePessoaTelefone(PessoaTelefone telefone) {
        this.getListaTelefone().remove(telefone);
        telefone.setPessoa(null);
    }

    public void addPessoaEndereco(PessoaEndereco endereco) {
        getListaEndereco().add(endereco);
        endereco.setPessoa(this);
    }

    public void removePessoaEndereco(PessoaEndereco endereco) {
        this.getListaEndereco().remove(endereco);
        endereco.setPessoa(null);
    }

    public void addPessoaConta(PessoaConta conta) {
        getListaPessoaConta().add(conta);
        conta.setPessoa(this);
    }

    public String getNumRg() {
        return numRg;
    }

    public void setNumRg(String numRg) {
        this.numRg = numRg;
    }

    public String getDescOrgaoEmissorRg() {
        return descOrgaoEmissorRg;
    }

    public void setDescOrgaoEmissorRg(String descOrgaoEmissorRg) {
        this.descOrgaoEmissorRg = descOrgaoEmissorRg;
    }

}
