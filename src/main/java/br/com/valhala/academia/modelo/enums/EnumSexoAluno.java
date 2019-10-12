package br.com.valhala.academia.modelo.enums;

public enum EnumSexoAluno {

	MASCULINO("M", "Masculino"), FEMININO("F", "Feminino"), NAO_INFORMADO("N", "Não Informado");

	private String sigla;
	private String descricao;

	private EnumSexoAluno(String sigla, String descricao) {
		this.sigla = sigla;
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getSigla() {
		return sigla;
	}

}
