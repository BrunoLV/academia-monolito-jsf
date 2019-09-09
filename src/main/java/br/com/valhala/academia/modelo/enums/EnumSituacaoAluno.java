package br.com.valhala.academia.modelo.enums;

public enum EnumSituacaoAluno {

	ATIVO("Ativo"), INATIVO("Inativo"), INADIMPLENTE("Inadimplente");

	private String descricao;

	private EnumSituacaoAluno(final String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
