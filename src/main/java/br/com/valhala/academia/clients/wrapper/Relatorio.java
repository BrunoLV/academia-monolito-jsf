package br.com.valhala.academia.clients.wrapper;

import java.io.Serializable;
import java.util.Arrays;

public class Relatorio implements Serializable {

	public static class Builder {

		private String formato;
		private String nomeArquivo;
		private byte[] arquivo;

		public Builder arquivo(final byte[] arquivo) {
			this.arquivo = arquivo;
			return this;
		}

		public Relatorio build() {
			Relatorio relatorio = new Relatorio();
			relatorio.setFormato(this.formato);
			relatorio.setArquivo(this.arquivo);
			relatorio.setNomeArquivo(this.nomeArquivo);
			return relatorio;
		}

		public Builder formato(final String formato) {
			this.formato = formato;
			return this;
		}

		public Builder nomeArquivo(final String nomeArquivo) {
			this.nomeArquivo = nomeArquivo;
			return this;
		}

	}

	private static final long serialVersionUID = 1L;
	private String formato;
	private String nomeArquivo;

	private byte[] arquivo;

	public byte[] getArquivo() {
		return arquivo;
	}

	public String getFormato() {
		return formato;
	}

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setArquivo(byte[] arquivo) {
		this.arquivo = arquivo;
	}

	public void setFormato(String formato) {
		this.formato = formato;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

	@Override
	public String toString() {
		return "Relatorio [formato=" + formato + ", nomeArquivo=" + nomeArquivo + ", arquivo="
				+ Arrays.toString(arquivo) + "]";
	}

}
