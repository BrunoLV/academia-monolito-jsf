package br.com.valhala.academia.clients.wrapper;

import java.io.Serializable;
import java.util.Arrays;

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

	@Override
	public String toString() {
		return "Relatorio [formato=" + formato + ", nomeArquivo=" + nomeArquivo + ", arquivo="
				+ Arrays.toString(arquivo) + "]";
	}

	public static class Builder {

		private String formato;
		private String nomeArquivo;
		private byte[] arquivo;

		public Builder formato(final String formato) {
			this.formato = formato;
			return this;
		}

		public Builder nomeArquivo(final String nomeArquivo) {
			this.nomeArquivo = nomeArquivo;
			return this;
		}

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

	}

}
