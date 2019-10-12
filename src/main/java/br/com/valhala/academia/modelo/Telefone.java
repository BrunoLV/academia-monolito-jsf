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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import br.com.valhala.academia.modelo.enums.EnumTipoTelefone;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
@Table(name = "tb_telefone")
public class Telefone implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "{telefone.ddd.notnull}")
	@Size(min = 2, max = 2, message = "{telefone.ddd.tamanho.invalido}")
	@Column(name = "ddd", nullable = false)
	private String ddd;

	@NotBlank(message = "{telefone.numero.notnull}")
	@Column(name = "numero", nullable = false)
	private String numero;

	@NotNull(message = "{telefone.tipo.notnull}")
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo", nullable = false)
	private EnumTipoTelefone tipo;

	@ManyToOne
	@JoinColumn(name = "id_aluno", nullable = false, foreignKey = @ForeignKey(name = "fk_telefone_id_aluno"))
	private Aluno aluno;

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
		Telefone other = (Telefone) obj;
		if (ddd == null) {
			if (other.ddd != null) {
				return false;
			}
		} else if (!ddd.equals(other.ddd)) {
			return false;
		}
		if (numero == null) {
			if (other.numero != null) {
				return false;
			}
		} else if (!numero.equals(other.numero)) {
			return false;
		}
		if (tipo != other.tipo) {
			return false;
		}
		return true;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public String getDdd() {
		return ddd;
	}

	public Long getId() {
		return id;
	}

	public String getNumero() {
		return numero;
	}

	public EnumTipoTelefone getTipo() {
		return tipo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ddd == null) ? 0 : ddd.hashCode());
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		return result;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public void setTipo(EnumTipoTelefone tipo) {
		this.tipo = tipo;
	}

	public String telefoneFormatado() {
		final String primeiraParte = numero.length() == 8 ? numero.substring(0, 4) : numero.substring(0, 5);
		final String segundaParte = numero.length() == 8 ? numero.substring(4) : numero.substring(5);
		return String.format("(%s) %s-%s", ddd, primeiraParte, segundaParte);
	}

	@Override
	public String toString() {
		return "Telefone [id=" + id + ", ddd=" + ddd + ", numero=" + numero + ", tipo=" + tipo + "]";
	}

}
