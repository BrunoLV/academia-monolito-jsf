package br.com.valhala.academia.clients.wrapper;

import java.io.Serializable;

public class RequisicaoRelatorio implements Serializable {

	public static class Builder {

		private String nomeRelatorio;
		private String json;

		public RequisicaoRelatorio build() {
			RequisicaoRelatorio requisicao = new RequisicaoRelatorio();
			requisicao.setNomeRelatorio(this.nomeRelatorio);
			requisicao.setJson(this.json);
			return requisicao;
		}

		public Builder json(final String json) {
			this.json = json;
			return this;
		}

		public Builder nomeRelatorio(final String nomeRelatorio) {
			this.nomeRelatorio = nomeRelatorio;
			return this;
		}

	}

	private static final long serialVersionUID = 1L;
	private String nomeRelatorio;

	private String json;

	public String getJson() {
		return json;
	}

	public String getNomeRelatorio() {
		return nomeRelatorio;
	}

	public void setJson(String json) {
		this.json = json;
	}

	public void setNomeRelatorio(String nomeRelatorio) {
		this.nomeRelatorio = nomeRelatorio;
	}

}
