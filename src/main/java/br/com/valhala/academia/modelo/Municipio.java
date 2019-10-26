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

	public static final class Builder {
		private Long id;
		private String nome;
		private Integer codigoIbge;
		private EnumUnidadeFederacao uf;
		private Estado estado;

		private Builder() {
		}

		public Municipio build() {
			return new Municipio(this);
		}

		public Builder withCodigoIbge(Integer codigoIbge) {
			this.codigoIbge = codigoIbge;
			return this;
		}

		public Builder withEstado(Estado estado) {
			this.estado = estado;
			return this;
		}

		public Builder withId(Long id) {
			this.id = id;
			return this;
		}

		public Builder withNome(String nome) {
			this.nome = nome;
			return this;
		}

		public Builder withUf(EnumUnidadeFederacao uf) {
			this.uf = uf;
			return this;
		}
	}

	private static final long serialVersionUID = 1L;

	public static Builder builder() {
		return new Builder();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	private Municipio(Builder builder) {
		this.id = builder.id;
		this.nome = builder.nome;
		this.codigoIbge = builder.codigoIbge;
		this.uf = builder.uf;
		this.estado = builder.estado;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Municipio other = (Municipio) obj;
		if (codigoIbge == null) {
			if (other.codigoIbge != null) {
				return false;
			}
		} else if (!codigoIbge.equals(other.codigoIbge)) {
			return false;
		}
		if (nome == null) {
			if (other.nome != null) {
				return false;
			}
		} else if (!nome.equals(other.nome)) {
			return false;
		}
		if (uf != other.uf) {
			return false;
		}
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
