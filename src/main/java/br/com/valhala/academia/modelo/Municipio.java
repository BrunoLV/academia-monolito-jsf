package br.com.valhala.academia.modelo;

import br.com.valhala.academia.modelo.enums.EnumUnidadeFederacao;
import br.com.valhala.academia.validacao.marcadores.ValidaMunicipioNaoPertencenteEstado;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
@Table(name = "tb_municipio")
@ValidaMunicipioNaoPertencenteEstado
public class Municipio implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "seq_id_municipio", sequenceName = "seq_id_municipio", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_id_municipio")
    private Long id;

    @Column(name = "nome", length = 100, nullable = false)
    private String nome;

    @Column(name = "codigo_ibge", unique = true, nullable = false)
    private Integer codigoIbge;

    @Enumerated(EnumType.STRING)
    @Column(name = "uf", length = 2, nullable = false)
    private EnumUnidadeFederacao uf;

    @ManyToOne
    @JoinColumn(name = "id_estado", nullable = false, foreignKey = @ForeignKey(name = "fk_municipio_id_estado"))
    private Estado estado;

    public Municipio() {
        super();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Municipio other = (Municipio) obj;
        if (codigoIbge == null) {
            if (other.codigoIbge != null)
                return false;
        } else if (!codigoIbge.equals(other.codigoIbge))
            return false;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        if (uf != other.uf)
            return false;
        return true;
    }

    public Integer getCodigoIbge() {
        return codigoIbge;
    }

    public void setCodigoIbge(Integer codigoIbge) {
        this.codigoIbge = codigoIbge;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public EnumUnidadeFederacao getUf() {
        return uf;
    }

    public void setUf(EnumUnidadeFederacao uf) {
        this.uf = uf;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codigoIbge == null) ? 0 : codigoIbge.hashCode());
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        result = prime * result + ((uf == null) ? 0 : uf.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "Municipio [id=" + id + ", nome=" + nome + ", codigoIbge=" + codigoIbge + ", uf=" + uf + ", estado="
                + estado + "]";
    }

}
