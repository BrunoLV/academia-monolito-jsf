package br.com.valhala.academia.modelo;

import br.com.valhala.academia.db.entities.listeners.MedidaCorporalEntityListener;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
@EntityListeners(value = {MedidaCorporalEntityListener.class})
@Table(name = "tb_medida_corporal")
public class MedidaCorporal implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "seq_id_medida_corporal", sequenceName = "seq_id_medida_corporal", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_id_medida_corporal")
    private Long id;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_medicao", nullable = false)
    private Date dataMedicao;

    @Column(precision = 3, scale = 2, nullable = false, columnDefinition = "decimal(3,2) not null default 0")
    private Double altura = 0.0;

    @Column(precision = 5, scale = 2, nullable = false, columnDefinition = "decimal(3,2) not null default 0")
    private Double peso = 0.0;

    @Column(precision = 5, scale = 2, nullable = false, columnDefinition = "decimal(5,2) not null default 0")
    private Double pescoco = 0.0;

    @Column(name = "torax_superior", precision = 5, scale = 2, nullable = false, columnDefinition = "decimal(5,2) not null default 0")
    private Double toraxSuperior = 0.0;

    @Column(name = "torax_inferior", precision = 5, scale = 2, nullable = false, columnDefinition = "decimal(5,2) not null default 0")
    private Double toraxInferior = 0.0;

    @Column(precision = 5, scale = 2, nullable = false, columnDefinition = "decimal(5,2) not null default 0")
    private Double busto = 0.0;

    @Column(precision = 5, scale = 2, nullable = false, columnDefinition = "decimal(5,2) not null default 0")
    private Double cintura = 0.0;

    @Column(precision = 5, scale = 2, nullable = false, columnDefinition = "decimal(5,2) not null default 0")
    private Double abdomen = 0.0;

    @Column(precision = 5, scale = 2, nullable = false, columnDefinition = "decimal(5,2) not null default 0")
    private Double quadril = 0.0;

    @Column(name = "coxa_direita", precision = 5, scale = 2, nullable = false, columnDefinition = "decimal(5,2) not null default 0")
    private Double coxaDireita = 0.0;

    @Column(name = "coxa_esquerda", precision = 5, scale = 2, nullable = false, columnDefinition = "decimal(5,2) not null default 0")
    private Double coxaEsquerda = 0.0;

    @Column(name = "panturrilha_direita", precision = 5, scale = 2, nullable = false, columnDefinition = "decimal(5,2) not null default 0")
    private Double panturrilhaDireita = 0.0;

    @Column(name = "panturrilha_esquerda", precision = 5, scale = 2, nullable = false, columnDefinition = "decimal(5,2) not null default 0")
    private Double panturrilhaEsquerda = 0.0;

    @Column(name = "braco_direito", precision = 5, scale = 2, nullable = false, columnDefinition = "decimal(5,2) not null default 0")
    private Double bracoDireito = 0.0;

    @Column(name = "braco_esquerdo", precision = 5, scale = 2, nullable = false, columnDefinition = "decimal(5,2) not null default 0")
    private Double bracoEsquerdo = 0.0;

    @Column(name = "antebraco_direito", precision = 5, scale = 2, nullable = false, columnDefinition = "decimal(5,2) not null default 0")
    private Double antebracoDireito = 0.0;

    @Column(name = "antebraco_esquerdo", precision = 5, scale = 2, nullable = false, columnDefinition = "decimal(5,2) not null default 0")
    private Double antebracoEsquerdo = 0.0;

    @Column(length = 255, nullable = false)
    private String uuid;

    @ManyToOne
    @JoinColumn(name = "id_aluno", nullable = false, foreignKey = @ForeignKey(name = "fk_medida_aluno_id_aluno"))
    private Aluno aluno;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataMedicao() {
        return dataMedicao;
    }

    public void setDataMedicao(Date dataMedicao) {
        this.dataMedicao = dataMedicao;
    }

    public Double getPescoco() {
        return pescoco;
    }

    public void setPescoco(Double pescoco) {
        this.pescoco = pescoco;
    }

    public Double getToraxSuperior() {
        return toraxSuperior;
    }

    public void setToraxSuperior(Double toraxSuperior) {
        this.toraxSuperior = toraxSuperior;
    }

    public Double getToraxInferior() {
        return toraxInferior;
    }

    public void setToraxInferior(Double toraxInferior) {
        this.toraxInferior = toraxInferior;
    }

    public Double getBusto() {
        return busto;
    }

    public void setBusto(Double busto) {
        this.busto = busto;
    }

    public Double getCintura() {
        return cintura;
    }

    public void setCintura(Double cintura) {
        this.cintura = cintura;
    }

    public Double getAbdomen() {
        return abdomen;
    }

    public void setAbdomen(Double abdomen) {
        this.abdomen = abdomen;
    }

    public Double getQuadril() {
        return quadril;
    }

    public void setQuadril(Double quadril) {
        this.quadril = quadril;
    }

    public Double getCoxaDireita() {
        return coxaDireita;
    }

    public void setCoxaDireita(Double coxaDireita) {
        this.coxaDireita = coxaDireita;
    }

    public Double getCoxaEsquerda() {
        return coxaEsquerda;
    }

    public void setCoxaEsquerda(Double coxaEsquerda) {
        this.coxaEsquerda = coxaEsquerda;
    }

    public Double getPanturrilhaDireita() {
        return panturrilhaDireita;
    }

    public void setPanturrilhaDireita(Double panturrilhaDireita) {
        this.panturrilhaDireita = panturrilhaDireita;
    }

    public Double getPanturrilhaEsquerda() {
        return panturrilhaEsquerda;
    }

    public void setPanturrilhaEsquerda(Double panturrilhaEsquerda) {
        this.panturrilhaEsquerda = panturrilhaEsquerda;
    }

    public Double getBracoDireito() {
        return bracoDireito;
    }

    public void setBracoDireito(Double bracoDireito) {
        this.bracoDireito = bracoDireito;
    }

    public Double getBracoEsquerdo() {
        return bracoEsquerdo;
    }

    public void setBracoEsquerdo(Double bracoEsquerdo) {
        this.bracoEsquerdo = bracoEsquerdo;
    }

    public Double getAntebracoDireito() {
        return antebracoDireito;
    }

    public void setAntebracoDireito(Double antebracoDireito) {
        this.antebracoDireito = antebracoDireito;
    }

    public Double getAntebracoEsquerdo() {
        return antebracoEsquerdo;
    }

    public void setAntebracoEsquerdo(Double antebracoEsquerdo) {
        this.antebracoEsquerdo = antebracoEsquerdo;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Double getAltura() {
        return altura;
    }

    public void setAltura(Double altura) {
        this.altura = altura;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }
}
