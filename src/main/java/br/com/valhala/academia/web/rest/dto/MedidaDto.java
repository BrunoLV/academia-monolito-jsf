package br.com.valhala.academia.web.rest.dto;

import java.io.Serializable;
import java.util.Date;

public class MedidaDto implements Serializable {

    private Date dataMedicao;
    private Double altura = 0.0;
    private Double peso = 0.0;
    private Double pescoco = 0.0;
    private Double toraxSuperior = 0.0;
    private Double toraxInferior = 0.0;
    private Double busto = 0.0;
    private Double cintura = 0.0;
    private Double abdomen = 0.0;
    private Double quadril = 0.0;
    private Double coxaDireita = 0.0;
    private Double coxaEsquerda = 0.0;
    private Double panturrilhaDireita = 0.0;
    private Double panturrilhaEsquerda = 0.0;
    private Double bracoDireito = 0.0;
    private Double bracoEsquerdo = 0.0;
    private Double antebracoDireito = 0.0;
    private Double antebracoEsquerdo = 0.0;

    public MedidaDto() {
    }

    public MedidaDto(Date dataMedicao, Double altura, Double peso, Double pescoco, Double toraxSuperior, Double toraxInferior, Double busto, Double cintura, Double abdomen, Double quadril, Double coxaDireita, Double coxaEsquerda, Double panturrilhaDireita, Double panturrilhaEsquerda, Double bracoDireito, Double bracoEsquerdo, Double antebracoDireito, Double antebracoEsquerdo) {
        this.dataMedicao = dataMedicao;
        this.altura = altura;
        this.peso = peso;
        this.pescoco = pescoco;
        this.toraxSuperior = toraxSuperior;
        this.toraxInferior = toraxInferior;
        this.busto = busto;
        this.cintura = cintura;
        this.abdomen = abdomen;
        this.quadril = quadril;
        this.coxaDireita = coxaDireita;
        this.coxaEsquerda = coxaEsquerda;
        this.panturrilhaDireita = panturrilhaDireita;
        this.panturrilhaEsquerda = panturrilhaEsquerda;
        this.bracoDireito = bracoDireito;
        this.bracoEsquerdo = bracoEsquerdo;
        this.antebracoDireito = antebracoDireito;
        this.antebracoEsquerdo = antebracoEsquerdo;
    }

    public Date getDataMedicao() {
        return dataMedicao;
    }

    public void setDataMedicao(Date dataMedicao) {
        this.dataMedicao = dataMedicao;
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
}
