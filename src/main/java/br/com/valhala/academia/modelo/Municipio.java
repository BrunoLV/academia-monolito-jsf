package br.com.valhala.academia.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import br.com.valhala.academia.modelo.enums.EnumUnidadeFederacao;
import br.com.valhala.academia.validacao.marcadores.ValidaMunicipioNaoPertencenteEstado;

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

	public Estado getEstado() {
		return estado;
	}

	public Long getId() {
		return id;
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
		result = prime * result + ((codigoIbge == null) ? 0 : codigoIbge.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((uf == null) ? 0 : uf.hashCode());
		return result;
	}

	public void setCodigoIbge(Integer codigoIbge) {
		this.codigoIbge = codigoIbge;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setUf(EnumUnidadeFederacao uf) {
		this.uf = uf;
	}

	@Override
	public String toString() {
		return "Municipio [id=" + id + ", nome=" + nome + ", codigoIbge=" + codigoIbge + ", uf=" + uf + ", estado="
				+ estado + "]";
	}

}
