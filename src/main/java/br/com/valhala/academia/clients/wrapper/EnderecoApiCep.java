package br.com.valhala.academia.clients.wrapper;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EnderecoApiCep implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String cep;
	private String logradouro;
	private String complemento;
	private String bairro;
	private String cidade;
	private String uf;
	private String ibge;

	private EnderecoApiCep(Builder builder) {
		this.cep = builder.cep;
		this.logradouro = builder.logradouro;
		this.complemento = builder.complemento;
		this.bairro = builder.bairro;
		this.cidade = builder.cidade;
		this.uf = builder.uf;
		this.ibge = builder.ibge;
	}
	
	public EnderecoApiCep() {
		super();
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	
	public String getCidade() {
		return cidade;
	}
	
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getIbge() {
		return ibge;
	}

	public void setIbge(String ibge) {
		this.ibge = ibge;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder {
		private String cep;
		private String logradouro;
		private String complemento;
		private String bairro;
		private String cidade;
		private String uf;
		private String ibge;

		private Builder() {
		}

		public Builder withCep(String cep) {
			this.cep = cep;
			return this;
		}

		public Builder withLogradouro(String logradouro) {
			this.logradouro = logradouro;
			return this;
		}

		public Builder withComplemento(String complemento) {
			this.complemento = complemento;
			return this;
		}

		public Builder withBairro(String bairro) {
			this.bairro = bairro;
			return this;
		}

		public Builder withCidade(String cidade) {
			this.cidade = cidade;
			return this;
		}

		public Builder withUf(String uf) {
			this.uf = uf;
			return this;
		}

		public Builder withIbge(String ibge) {
			this.ibge = ibge;
			return this;
		}

		public EnderecoApiCep build() {
			return new EnderecoApiCep(this);
		}
	}

}
