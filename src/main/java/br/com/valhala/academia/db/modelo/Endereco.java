package br.com.valhala.academia.db.modelo;

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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import br.com.valhala.academia.db.modelo.enums.EnumTipoEndereco;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
@Table(name = "tb_endereco")
public class Endereco implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "seq_id_endereco", sequenceName = "seq_id_endereco", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_id_endereco")
	private Long id;

	@NotBlank(message = "{endereco.logradouro.notnull}")
	@Column(name = "logradouro", length = 255, nullable = false)
	private String logradouro;

	@NotBlank(message = "{endereco.numero.notnull}")
	@Column(name = "numero", length = 5, nullable = false)
	private String numero;

	@Size(min = 2, message = "{endereco.complemento.tamanho.invalido.min}")
	@Size(max = 20, message = "{endereco.complemento.tamanho.invalido.max}")
	@Column(name = "complemento", length = 20)
	private String complemento;

	@NotBlank(message = "{endereco.bairro.notnull}")
	@Column(name = "bairro", length = 100, nullable = false)
	private String bairro;

	@NotBlank(message = "{endereco.cep.notnull}")
	@Column(name = "cep", length = 10, nullable = false)
	private String cep;

	@NotNull(message = "{endereco.tipo.notnul}")
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_endereco", nullable = false, length = 60)
	private EnumTipoEndereco tipoEndereco;

	@NotNull(message = "{endereco.tipo_logradouro.notnull}")
	@ManyToOne
	@JoinColumn(name = "id_tipo_logradouro", nullable = false, foreignKey = @ForeignKey(name = "fk_endereco_id_tipo_logradouro"))
	private TipoLogradouro tipoLogradouro;

	@NotNull(message = "{endereco.municipio.notnull}")
	@ManyToOne
	@JoinColumn(name = "id_municipio", nullable = false, foreignKey = @ForeignKey(name = "fk_endereco_id_municipio"))
	private Municipio municipio;

	public Endereco() {
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
		Endereco other = (Endereco) obj;
		if (bairro == null) {
			if (other.bairro != null)
				return false;
		} else if (!bairro.equals(other.bairro))
			return false;
		if (cep == null) {
			if (other.cep != null)
				return false;
		} else if (!cep.equals(other.cep))
			return false;
		if (complemento == null) {
			if (other.complemento != null)
				return false;
		} else if (!complemento.equals(other.complemento))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (logradouro == null) {
			if (other.logradouro != null)
				return false;
		} else if (!logradouro.equals(other.logradouro))
			return false;
		if (municipio == null) {
			if (other.municipio != null)
				return false;
		} else if (!municipio.equals(other.municipio))
			return false;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		if (tipoEndereco != other.tipoEndereco)
			return false;
		if (tipoLogradouro == null) {
			if (other.tipoLogradouro != null)
				return false;
		} else if (!tipoLogradouro.equals(other.tipoLogradouro))
			return false;
		return true;
	}

	public String getBairro() {
		return bairro;
	}

	public String getCep() {
		return cep;
	}

	public String getComplemento() {
		return complemento;
	}

	public Long getId() {
		return id;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public Municipio getMunicipio() {
		return municipio;
	}

	public String getNumero() {
		return numero;
	}

	public EnumTipoEndereco getTipoEndereco() {
		return tipoEndereco;
	}

	public TipoLogradouro getTipoLogradouro() {
		return tipoLogradouro;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bairro == null) ? 0 : bairro.hashCode());
		result = prime * result + ((cep == null) ? 0 : cep.hashCode());
		result = prime * result + ((complemento == null) ? 0 : complemento.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((logradouro == null) ? 0 : logradouro.hashCode());
		result = prime * result + ((municipio == null) ? 0 : municipio.hashCode());
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
		result = prime * result + ((tipoEndereco == null) ? 0 : tipoEndereco.hashCode());
		result = prime * result + ((tipoLogradouro == null) ? 0 : tipoLogradouro.hashCode());
		return result;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public void setTipoEndereco(EnumTipoEndereco tipoEndereco) {
		this.tipoEndereco = tipoEndereco;
	}

	public void setTipoLogradouro(TipoLogradouro tipoLogradouro) {
		this.tipoLogradouro = tipoLogradouro;
	}

	@Override
	public String toString() {
		return "Endereco [id=" + id + ", logradouro=" + logradouro + ", numero=" + numero + ", complemento="
				+ complemento + ", bairro=" + bairro + ", cep=" + cep + ", tipoEndereco=" + tipoEndereco
				+ ", tipoLogradouro=" + tipoLogradouro + ", municipio=" + municipio + "]";
	}

	public String enderecoFormatado() {
		return String.format("%s %s, nº %s - %s - Bairro: %s, Cep: %s - %s / %s",
				tipoEndereco != null ? tipoLogradouro.getDescricao() : null, logradouro, numero, complemento, bairro,
				cep, municipio != null ? municipio.getNome() : null,
				municipio != null && municipio.getUf() != null ? municipio.getUf().name() : null);
	}

}
