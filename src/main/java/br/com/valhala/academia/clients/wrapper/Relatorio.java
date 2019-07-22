package br.com.valhala.academia.clients.wrapper;

import java.io.Serializable;

public class Relatorio implements Serializable {

	private static final long serialVersionUID = 1L;

	private String formato;
	private String nomeArquivo;
	private byte[] arquivo;

	public String getFormato() {
		return formato;
	}

	public void setFormato(String formato) {
		this.formato = formato;
	}

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

	public byte[] getArquivo() {
		return arquivo;
	}

	public void setArquivo(byte[] arquivo) {
		this.arquivo = arquivo;
	}

}
