package br.com.valhala.academia.modelo;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import br.com.valhala.academia.modelo.enums.EnumUnidadeFederacao;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
@Table(name = "tb_estado")
public class Estado implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	public Long getId() {
		return id;
	}

	public Collection<Municipio> getMunicipios() {
		return municipios;
	}

	public String getNome() {
		return nome;
	}

	public EnumUnidadeFederacao getUf() {
		return uf;
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

	public void setCodigo(Short codigo) {
		this.codigo = codigo;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setMunicipios(Collection<Municipio> municipios) {
		this.municipios = municipios;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setUf(EnumUnidadeFederacao uf) {
		this.uf = uf;
	}

	@Override
	public String toString() {
		return "Estado [id=" + id + ", codigo=" + codigo + ", nome=" + nome + ", uf=" + uf + "]";
	}

}
