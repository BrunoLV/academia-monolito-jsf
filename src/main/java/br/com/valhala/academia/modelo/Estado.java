package br.com.valhala.academia.modelo;

import br.com.valhala.academia.modelo.enums.EnumUnidadeFederacao;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
@Table(name = "tb_estado")
public class Estado implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "seq_id_estado", sequenceName = "seq_id_estado", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_id_estado")
    private Long id;

    @Column(name = "codigo", nullable = false, unique = true)
    private Short codigo;

    @Column(name = "nome", nullable = false, unique = true)
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(name = "uf", nullable = false)
    private EnumUnidadeFederacao uf;

    @OneToMany(mappedBy = "estado")
    private Collection<Municipio> municipios;

    public Estado() {
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
        Estado other = (Estado) obj;
        if (codigo == null) {
            if (other.codigo != null)
                return false;
        } else if (!codigo.equals(other.codigo))
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

    public Short getCodigo() {
        return codigo;
    }

    public void setCodigo(Short codigo) {
        this.codigo = codigo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Collection<Municipio> getMunicipios() {
        return municipios;
    }

    public void setMunicipios(Collection<Municipio> municipios) {
        this.municipios = municipios;
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
        result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        result = prime * result + ((uf == null) ? 0 : uf.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "Estado [id=" + id + ", codigo=" + codigo + ", nome=" + nome + ", uf=" + uf + "]";
    }

}
