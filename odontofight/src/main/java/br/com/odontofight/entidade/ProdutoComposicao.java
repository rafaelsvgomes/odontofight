package br.com.odontofight.entidade;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUTOCOMPOSICAO")
@SequenceGenerator(name = "seqprodutocomposicao", sequenceName = "seqprodutocomposicao", allocationSize = 1)
@NamedQueries({ @NamedQuery(name = ProdutoComposicao.LISTAR_PRODUTOS_INCLUSOS, query = "SELECT pc FROM ProdutoComposicao pc WHERE pc.produtoPai.id = :idProduto") })
public class ProdutoComposicao extends EntidadeGenerica {
    private static final long serialVersionUID = 436366353944612330L;

    public static final String LISTAR_PRODUTOS_INCLUSOS = "ProdutoComposicao.listarProdutosInclusos";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqprodutocomposicao")
    @Column(name = "idprodutocomposicao", unique = true, nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "idProduto")
    private Produto produtoPai;

    @ManyToOne
    @JoinColumn(name = "idProdutoItemComp")
    private Produto produtoFilho;

    @Column(name = "qtProdutoComposicao")
    private BigInteger qtdProdutoComposicao;

    @Override
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Produto getProdutoPai() {
        return produtoPai;
    }

    public void setProdutoPai(Produto produtoPai) {
        this.produtoPai = produtoPai;
    }

    public Produto getProdutoFilho() {
        return produtoFilho;
    }

    public void setProdutoFilho(Produto produtoFilho) {
        this.produtoFilho = produtoFilho;
    }

    public BigInteger getQtdProdutoComposicao() {
        return qtdProdutoComposicao;
    }

    public void setQtdProdutoComposicao(BigInteger qtdProdutoComposicao) {
        this.qtdProdutoComposicao = qtdProdutoComposicao;
    }

}
