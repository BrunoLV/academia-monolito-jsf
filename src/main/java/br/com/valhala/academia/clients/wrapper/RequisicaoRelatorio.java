package br.com.valhala.academia.clients.wrapper;

import java.io.Serializable;

public class RequisicaoRelatorio implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nomeRelatorio;
	private String json;

	public String getNomeRelatorio() {
		return nomeRelatorio;
	}

	public void setNomeRelatorio(String nomeRelatorio) {
		this.nomeRelatorio = nomeRelatorio;
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

}
