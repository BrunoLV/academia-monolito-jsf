package br.com.valhala.academia.modelo.enums;

public enum EnumTipoTelefone {

	NAO_INFORMADO("Não Informado"), RESIDENCIAL("Residencial"), COMERCIAL("Comercial"), CELULAR("Celular");

	private String nome;

	private EnumTipoTelefone(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

}
