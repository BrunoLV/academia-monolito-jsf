package br.com.valhala.academia.web.rest.dto;

import java.io.Serializable;

public class MedidaDto implements Serializable {

    public static final class Builder {

        private String dataMedicao;
        private Double altura;
        private Double peso;
        private Double pescoco;
        private Double toraxSuperior;
        private Double toraxInferior;
        private Double busto;
        private Double cintura;
        private Double abdomen;
        private Double quadril;
        private Double coxaDireita;
        private Double coxaEsquerda;
        private Double panturrilhaDireita;
        private Double panturrilhaEsquerda;
        private Double bracoDireito;
        private Double bracoEsquerdo;
        private Double antebracoDireito;
        private Double antebracoEsquerdo;

        private Builder() {
        }

        public MedidaDto build() {
            return new MedidaDto(this);
        }

        public Builder comAbdomen(Double abdomen) {
            this.abdomen = abdomen;
            return this;
        }

        public Builder comAltura(Double altura) {
            this.altura = altura;
            return this;
        }

        public Builder comAntebracoDireito(Double antebracoDireito) {
            this.antebracoDireito = antebracoDireito;
            return this;
        }

        public Builder comAntebracoEsquerdo(Double antebracoEsquerdo) {
            this.antebracoEsquerdo = antebracoEsquerdo;
            return this;
        }

        public Builder comBracoDireito(Double bracoDireito) {
            this.bracoDireito = bracoDireito;
            return this;
        }

        public Builder comBracoEsquerdo(Double bracoEsquerdo) {
            this.bracoEsquerdo = bracoEsquerdo;
            return this;
        }

        public Builder comBusto(Double busto) {
            this.busto = busto;
            return this;
        }

        public Builder comCintura(Double cintura) {
            this.cintura = cintura;
            return this;
        }

        public Builder comCoxaDireita(Double coxaDireita) {
            this.coxaDireita = coxaDireita;
            return this;
        }

        public Builder comCoxaEsquerda(Double coxaEsquerda) {
            this.coxaEsquerda = coxaEsquerda;
            return this;
        }

        public Builder comDataMedicao(String dataMedicao) {
            this.dataMedicao = dataMedicao;
            return this;
        }

        public Builder comPanturrilhaDireita(Double panturrilhaDireita) {
            this.panturrilhaDireita = panturrilhaDireita;
            return this;
        }

        public Builder comPanturrilhaEsquerda(Double panturrilhaEsquerda) {
            this.panturrilhaEsquerda = panturrilhaEsquerda;
            return this;
        }

        public Builder comPescoco(Double pescoco) {
            this.pescoco = pescoco;
            return this;
        }

        public Builder comPeso(Double peso) {
            this.peso = peso;
            return this;
        }

        public Builder comQuadril(Double quadril) {
            this.quadril = quadril;
            return this;
        }

        public Builder comToraxInferior(Double toraxInferior) {
            this.toraxInferior = toraxInferior;
            return this;
        }

        public Builder comToraxSuperior(Double toraxSuperior) {
            this.toraxSuperior = toraxSuperior;
            return this;
        }
    }

    private static final long serialVersionUID = 1L;

    public static Builder builder() {
        return new Builder();
    }

    private String dataMedicao;
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
        super();
    }

    private MedidaDto(Builder builder) {
        this.dataMedicao = builder.dataMedicao;
        this.altura = builder.altura;
        this.peso = builder.peso;
        this.pescoco = builder.pescoco;
        this.toraxSuperior = builder.toraxSuperior;
        this.toraxInferior = builder.toraxInferior;
        this.busto = builder.busto;
        this.cintura = builder.cintura;
        this.abdomen = builder.abdomen;
        this.quadril = builder.quadril;
        this.coxaDireita = builder.coxaDireita;
        this.coxaEsquerda = builder.coxaEsquerda;
        this.panturrilhaDireita = builder.panturrilhaDireita;
        this.panturrilhaEsquerda = builder.panturrilhaEsquerda;
        this.bracoDireito = builder.bracoDireito;
        this.bracoEsquerdo = builder.bracoEsquerdo;
        this.antebracoDireito = builder.antebracoDireito;
        this.antebracoEsquerdo = builder.antebracoEsquerdo;
    }

    public Double getAbdomen() {
        return abdomen;
    }

    public Double getAltura() {
        return altura;
    }

    public Double getAntebracoDireito() {
        return antebracoDireito;
    }

    public Double getAntebracoEsquerdo() {
        return antebracoEsquerdo;
    }

    public Double getBracoDireito() {
        return bracoDireito;
    }

    public Double getBracoEsquerdo() {
        return bracoEsquerdo;
    }

    public Double getBusto() {
        return busto;
    }

    public Double getCintura() {
        return cintura;
    }

    public Double getCoxaDireita() {
        return coxaDireita;
    }

    public Double getCoxaEsquerda() {
        return coxaEsquerda;
    }

    public String getDataMedicao() {
        return dataMedicao;
    }

    public Double getPanturrilhaDireita() {
        return panturrilhaDireita;
    }

    public Double getPanturrilhaEsquerda() {
        return panturrilhaEsquerda;
    }

    public Double getPescoco() {
        return pescoco;
    }

    public Double getPeso() {
        return peso;
    }

    public Double getQuadril() {
        return quadril;
    }

    public Double getToraxInferior() {
        return toraxInferior;
    }

    public Double getToraxSuperior() {
        return toraxSuperior;
    }

    public void setAbdomen(Double abdomen) {
        this.abdomen = abdomen;
    }

    public void setAltura(Double altura) {
        this.altura = altura;
    }

    public void setAntebracoDireito(Double antebracoDireito) {
        this.antebracoDireito = antebracoDireito;
    }

    public void setAntebracoEsquerdo(Double antebracoEsquerdo) {
        this.antebracoEsquerdo = antebracoEsquerdo;
    }

    public void setBracoDireito(Double bracoDireito) {
        this.bracoDireito = bracoDireito;
    }

    public void setBracoEsquerdo(Double bracoEsquerdo) {
        this.bracoEsquerdo = bracoEsquerdo;
    }

    public void setBusto(Double busto) {
        this.busto = busto;
    }

    public void setCintura(Double cintura) {
        this.cintura = cintura;
    }

    public void setCoxaDireita(Double coxaDireita) {
        this.coxaDireita = coxaDireita;
    }

    public void setCoxaEsquerda(Double coxaEsquerda) {
        this.coxaEsquerda = coxaEsquerda;
    }

    public void setDataMedicao(String dataMedicao) {
        this.dataMedicao = dataMedicao;
    }

    public void setPanturrilhaDireita(Double panturrilhaDireita) {
        this.panturrilhaDireita = panturrilhaDireita;
    }

    public void setPanturrilhaEsquerda(Double panturrilhaEsquerda) {
        this.panturrilhaEsquerda = panturrilhaEsquerda;
    }

    public void setPescoco(Double pescoco) {
        this.pescoco = pescoco;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public void setQuadril(Double quadril) {
        this.quadril = quadril;
    }

    public void setToraxInferior(Double toraxInferior) {
        this.toraxInferior = toraxInferior;
    }

    public void setToraxSuperior(Double toraxSuperior) {
        this.toraxSuperior = toraxSuperior;
    }
}
