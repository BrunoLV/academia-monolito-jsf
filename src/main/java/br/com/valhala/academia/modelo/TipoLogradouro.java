package br.com.valhala.academia.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
@Table(name = "tb_tipo_logradouro")
public class TipoLogradouro implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "abreviatura", unique = true, length = 5)
	private String abreviatura;

	@Column(name = "descricao", unique = true, length = 40)
	private String descricao;

	public TipoLogradouro() {
		super();
	}
	
	private TipoLogradouro(Builder builder) {
		this.id = builder.id;
		this.abreviatura = builder.abreviatura;
		this.descricao = builder.descricao;
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
		TipoLogradouro other = (TipoLogradouro) obj;
		if (abreviatura == null) {
			if (other.abreviatura != null) {
				return false;
			}
		} else if (!abreviatura.equals(other.abreviatura)) {
			return false;
		}
		if (descricao == null) {
			if (other.descricao != null) {
				return false;
			}
		} else if (!descricao.equals(other.descricao)) {
			return false;
		}
		return true;
	}

	public String getAbreviatura() {
		return abreviatura;
	}

	public String getDescricao() {
		return descricao;
	}

	public Long getId() {
		return id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((abreviatura == null) ? 0 : abreviatura.hashCode());
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		return result;
	}

	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "TipoLogradouro [id=" + id + ", abreviatura=" + abreviatura + ", descricao=" + descricao + "]";
	}

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder {
		private Long id;
		private String abreviatura;
		private String descricao;

		private Builder() {
		}

		public Builder withId(Long id) {
			this.id = id;
			return this;
		}

		public Builder withAbreviatura(String abreviatura) {
			this.abreviatura = abreviatura;
			return this;
		}

		public Builder withDescricao(String descricao) {
			this.descricao = descricao;
			return this;
		}

		public TipoLogradouro build() {
			return new TipoLogradouro(this);
		}
	}

}
